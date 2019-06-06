package org.mybatis.generator.internal;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.util.messages.Messages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.mybatis.generator.api.dom.OutputUtilities.newLine;

public class DefaultShellCallback
  implements ShellCallback
{
  private boolean overwrite;
  
  public DefaultShellCallback(boolean overwrite)
  {
    this.overwrite = overwrite;
  }
  
  public File getDirectory(String targetProject, String targetPackage)
    throws ShellException
  {
    File project = new File(targetProject);
    if (!project.isDirectory()) {
      throw new ShellException(Messages.getString("Warning.9", targetProject));
    }
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(targetPackage, ".");
    while (st.hasMoreTokens())
    {
      sb.append(st.nextToken());
      sb.append(File.separatorChar);
    }
    File directory = new File(project, sb.toString());
    if (!directory.isDirectory())
    {
      boolean rc = directory.mkdirs();
      if (!rc) {
        throw new ShellException(Messages.getString("Warning.10", directory.getAbsolutePath()));
      }
    }
    return directory;
  }
  
  public void refreshProject(String project) {}
  
  public boolean isMergeSupported()
  {
    return true;
  }
  
  public boolean isOverwriteEnabled()
  {
    return this.overwrite;
  }
  
  public String mergeJavaFile(String newFileSource, String existingFileFullPath, String[] javadocTags, String fileEncoding)
    throws ShellException
  {
    try {
      return getNewJavaFile(newFileSource,existingFileFullPath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getNewJavaFile(String newFileSource, String existingFileFullPath) throws FileNotFoundException {
    CompilationUnit newCompilationUnit = JavaParser.parse(newFileSource);
    CompilationUnit existingCompilationUnit = JavaParser.parse(new File(existingFileFullPath));
    return mergerFile(newCompilationUnit, existingCompilationUnit);
  }

  public String mergerFile(CompilationUnit newCompilationUnit, CompilationUnit existingCompilationUnit) {

    System.out.println("合并java代码");
    StringBuilder sb = new StringBuilder(newCompilationUnit.getPackageDeclaration().get().toString());
    newCompilationUnit.removePackageDeclaration();

    //合并imports
    NodeList<ImportDeclaration> imports = newCompilationUnit.getImports();
    imports.addAll(existingCompilationUnit.getImports());
    Set importSet = new HashSet<ImportDeclaration>();
    importSet.addAll(imports);

    NodeList<ImportDeclaration> newImports = new NodeList<ImportDeclaration>();
    newImports.addAll(importSet);
    newCompilationUnit.setImports(newImports);
    for (ImportDeclaration i : newCompilationUnit.getImports()) {
      sb.append(i.toString());
    }
    newLine(sb);
    NodeList<TypeDeclaration<?>> types = newCompilationUnit.getTypes();
    NodeList<TypeDeclaration<?>> oldTypes = existingCompilationUnit.getTypes();

    for (int i = 0; i < types.size(); i++) {
      //截取Class
      String classNameInfo = types.get(i).toString().substring(0, types.get(i).toString().indexOf("{") + 1);
      sb.append(classNameInfo);
      newLine(sb);
      newLine(sb);
      //合并fields
      List<FieldDeclaration> fields = types.get(i).getFields();
      List<FieldDeclaration> oldFields = oldTypes.get(i).getFields();
      List<FieldDeclaration> newFields = new ArrayList<FieldDeclaration>();
      HashSet<FieldDeclaration> fieldDeclarations = new HashSet<FieldDeclaration>();
      fieldDeclarations.addAll(fields);
      fieldDeclarations.addAll(oldFields);
      newFields.addAll(fieldDeclarations);
      for (FieldDeclaration f : newFields) {
//        sb.append("\t" + f.toString());
        String res = "\t"+f.toString().replaceFirst( "(?s)" + "\r\n" + "(?!.*?" + "\r\n" + ")", "\r\n\t" );
        sb.append(res);
        newLine(sb);
        newLine(sb);
      }

      //合并methods
      List<MethodDeclaration> methods = types.get(i).getMethods();
      List<MethodDeclaration> existingMethods = oldTypes.get(i).getMethods();

      for (MethodDeclaration f : methods) {
        String res = "\t"+f.toString().replaceAll("\r\n", "\r\n\t");
        sb.append(res);
        newLine(sb);
        newLine(sb);
      }

      List<String> methodList = new ArrayList<String>();
      for (MethodDeclaration m : methods) {
        methodList.add(m.getName().toString());
      }
      methodList.add("toString");
      methodList.add("hashCode");
      methodList.add("equals");

      for (MethodDeclaration m : existingMethods) {
        if (methodList.contains(m.getName().toString())) {
          continue;
        }

        boolean flag = true;
        for (String tag : MergeConstants.OLD_ELEMENT_TAGS) {
          if (m.toString().contains(tag)) {
            flag = false;
            break;
          }
        }
        if (flag) {
          String res = "\t"+m.toString().replaceFirst( "(?s)" + "\r\n" + "(?!.*?" + "\r\n" + ")", "\r\n\t" );
          sb.append(res);
          newLine(sb);
          newLine(sb);
        }
      }

      //判断是否有内部类
      types.get(i).getChildNodes();
      for (Node n : types.get(i).getChildNodes()) {
        if (n.toString().contains("static class")) {
//          String res = n.toString().replaceAll("\r\n", "\r\n\t");
//          sb.append("\t" + res);
          String res = "\t"+n.toString().replaceFirst( "(?s)" + "\r\n" + "(?!.*?" + "\r\n" + ")", "\r\n\t" );
          sb.append(res);
        }
      }

    }

    return sb.append(System.getProperty("line.separator") + "}").toString();
  }

}
