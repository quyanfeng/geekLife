package tk.mybatis.mapper.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.MessageFormat;
import java.util.Properties;
import java.util.Set;

/**
 * 添加swagger 功能
 * @Author qyf 
 * @Date 2019/6/6 9:58
 **/
public class MapperCommentGenerator implements CommentGenerator
{
  private String beginningDelimiter = "";
  private String endingDelimiter = "";
  private boolean forceAnnotation;
  
  /**
   * 是否使用swagger
   **/
  private boolean isSwagger=true;
  @Override
  public void addJavaFileComment(CompilationUnit compilationUnit) {}
  @Override
  public void addComment(XmlElement xmlElement)
  {
//    xmlElement.addElement(new TextElement("<!--"));
//    StringBuilder sb = new StringBuilder();
//    sb.append("  WARNING - ");
//    sb.append("@mbg.generated");
//    xmlElement.addElement(new TextElement(sb.toString()));
//    xmlElement.addElement(new TextElement("-->"));
  }
  @Override
  public void addRootComment(XmlElement rootElement) {}
  @Override
  public void addConfigurationProperties(Properties properties)
  {
    String beginningDelimiter = properties.getProperty("beginningDelimiter");
    if (StringUtility.stringHasValue(beginningDelimiter)) {
      this.beginningDelimiter = beginningDelimiter;
    }
    String endingDelimiter = properties.getProperty("endingDelimiter");
    if (StringUtility.stringHasValue(endingDelimiter)) {
      this.endingDelimiter = endingDelimiter;
    }
    String forceAnnotation = properties.getProperty("forceAnnotation");
    if (StringUtility.stringHasValue(forceAnnotation)) {
      this.forceAnnotation = "TRUE".equalsIgnoreCase(forceAnnotation);
    }

//    String isSwagger = properties.getProperty("isSwagger");
//    if (StringUtility.stringHasValue(isSwagger)) {
//      this.isSwagger = "TRUE".equalsIgnoreCase(forceAnnotation);
//    }
  }
  
  public String getDelimiterName(String name)
  {
    StringBuilder nameBuilder = new StringBuilder();
    nameBuilder.append(this.beginningDelimiter);
    nameBuilder.append(name);
    nameBuilder.append(this.endingDelimiter);
    return nameBuilder.toString();
  }
  
  protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(" * ");
    sb.append("@mbg.generated");
    if (markAsDoNotDelete) {
      sb.append(" do_not_delete_during_merge");
    }
    javaElement.addJavaDocLine(sb.toString());
  }
  @Override
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {}
  @Override
  public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {}

  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn)
  {
    StringBuilder sb;
    //如果使用swagger
    if (isSwagger) {
      field.addAnnotation("@ApiModelProperty(\""+introspectedColumn.getRemarks()+"\")");
    } else if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
      field.addJavaDocLine("/**");
      sb = new StringBuilder();
      sb.append(" * ");
      sb.append(introspectedColumn.getRemarks());
      field.addJavaDocLine(sb.toString());
      field.addJavaDocLine(" */");
    }
    if (field.isTransient()) {
      field.addAnnotation("@Transient");
    }
    for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
      if (introspectedColumn == column)
      {
        field.addAnnotation("@Id");
        break;
      }
    }
    String column = introspectedColumn.getActualColumnName();
    if ((StringUtility.stringContainsSpace(column)) || (introspectedTable.getTableConfiguration().isAllColumnDelimitingEnabled())) {
      column = introspectedColumn.getContext().getBeginningDelimiter() + column + introspectedColumn.getContext().getEndingDelimiter();
    }
    if (!column.equals(introspectedColumn.getJavaProperty())) {
      field.addAnnotation("@Column(name = \"" + getDelimiterName(column) + "\")");
    } else if ((StringUtility.stringHasValue(this.beginningDelimiter)) || (StringUtility.stringHasValue(this.endingDelimiter))) {
      field.addAnnotation("@Column(name = \"" + getDelimiterName(column) + "\")");
    } else if (this.forceAnnotation) {
      field.addAnnotation("@Column(name = \"" + getDelimiterName(column) + "\")");
    }
    if (introspectedColumn.isIdentity())
    {
      if ("JDBC".equals(introspectedTable.getTableConfiguration().getGeneratedKey().getRuntimeSqlStatement())) {
        field.addAnnotation("@GeneratedValue(generator = \"JDBC\")");
      } else {
        field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
      }
    }
    else if (introspectedColumn.isSequenceColumn())
    {
      String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
      String sql = MessageFormat.format(introspectedTable.getTableConfiguration().getGeneratedKey().getRuntimeSqlStatement(), new Object[] { tableName, tableName.toUpperCase() });
      field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY, generator = \"" + sql + "\")");
    }
  }
  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable) {}
  public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {}
  @Override
  public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {}
  @Override
  public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn)
  {
    StringBuilder sb = new StringBuilder();
    method.addJavaDocLine("/**");
    if (StringUtility.stringHasValue(introspectedColumn.getRemarks()))
    {
      sb.append(" * 获取");
      sb.append(introspectedColumn.getRemarks());
      method.addJavaDocLine(sb.toString());
      method.addJavaDocLine(" *");
    }
    sb.setLength(0);
    sb.append(" * @return ");
    sb.append(introspectedColumn.getActualColumnName());
    if (StringUtility.stringHasValue(introspectedColumn.getRemarks()))
    {
      sb.append(" - ");
      sb.append(introspectedColumn.getRemarks());
    }
    method.addJavaDocLine(sb.toString());
    method.addJavaDocLine(" */");
  }
  @Override
  public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn)
  {
    StringBuilder sb = new StringBuilder();
    method.addJavaDocLine("/**");
    if (StringUtility.stringHasValue(introspectedColumn.getRemarks()))
    {
      sb.append(" * 设置");
      sb.append(introspectedColumn.getRemarks());
      method.addJavaDocLine(sb.toString());
      method.addJavaDocLine(" *");
    }
    Parameter parm = (Parameter)method.getParameters().get(0);
    sb.setLength(0);
    sb.append(" * @param ");
    sb.append(parm.getName());
    if (StringUtility.stringHasValue(introspectedColumn.getRemarks()))
    {
      sb.append(" ");
      sb.append(introspectedColumn.getRemarks());
    }
    method.addJavaDocLine(sb.toString());
    method.addJavaDocLine(" */");
  }
  @Override
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {}
  
  public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {}
  
  public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {}
  
  public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {}
  
  public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {}
  
  public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {}
}
