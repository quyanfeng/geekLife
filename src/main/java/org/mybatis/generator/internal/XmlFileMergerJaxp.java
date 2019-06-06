package org.mybatis.generator.internal;


import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.util.messages.Messages;
import org.w3c.dom.*;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlFileMergerJaxp
{
  private static class NullEntityResolver
    implements EntityResolver
  {
    public InputSource resolveEntity(String publicId, String systemId)
      throws SAXException, IOException
    {
      StringReader sr = new StringReader("");

      return new InputSource(sr);
    }
  }

  public static String getMergedSource(GeneratedXmlFile generatedXmlFile, File existingFile)
    throws ShellException
  {
    try
    {
      return getMergedSource(new InputSource(new StringReader(generatedXmlFile.getFormattedContent())), new InputSource(new InputStreamReader(new FileInputStream(existingFile), "UTF-8")), existingFile.getName());
    }
    catch (IOException e)
    {
      throw new ShellException(Messages.getString("Warning.13", existingFile.getName()), e);
    }
    catch (SAXException e)
    {
      throw new ShellException(Messages.getString("Warning.13", existingFile.getName()), e);
    }
    catch (ParserConfigurationException e)
    {
      throw new ShellException(Messages.getString("Warning.13", existingFile.getName()), e);
    }
  }

  public static String getMergedSource(InputSource newFile, InputSource existingFile, String existingFileName)
    throws IOException, SAXException, ParserConfigurationException, ShellException
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    factory.setExpandEntityReferences(false);
    DocumentBuilder builder = factory.newDocumentBuilder();
    builder.setEntityResolver(new NullEntityResolver());

    Document existingDocument = builder.parse(existingFile);
    Document newDocument = builder.parse(newFile);

    DocumentType newDocType = newDocument.getDoctype();
    DocumentType existingDocType = existingDocument.getDoctype();
    if (!newDocType.getName().equals(existingDocType.getName())) {
      throw new ShellException(Messages.getString("Warning.12", existingFileName));
    }
    Element existingRootElement = existingDocument.getDocumentElement();
    Element newRootElement = newDocument.getDocumentElement();

    NamedNodeMap attributes = existingRootElement.getAttributes();
    int attributeCount = attributes.getLength();
    for (int i = attributeCount - 1; i >= 0; i--)
    {
      Node node = attributes.item(i);
      existingRootElement.removeAttribute(node.getNodeName());
    }
    attributes = newRootElement.getAttributes();
    attributeCount = attributes.getLength();
    for (int i = 0; i < attributeCount; i++)
    {
      Node node = attributes.item(i);
      existingRootElement.setAttribute(node.getNodeName(), node.getNodeValue());
    }

    //获取新生成的所有xml，所有element的id列表，删除之前同名的结点
    NodeList newMethods = newRootElement.getChildNodes();
    List<String> methods = new ArrayList<String>();
    for (int i = 0; i < newMethods.getLength(); i++) {
      Node node = newMethods.item(i);
      try {
        if (node instanceof DeferredTextImpl) {
          continue;
        }
        Element ele = (Element) node;
        methods.add(ele.getAttribute("id"));
      } catch (Exception e) {
        //#text节点转换会异常
        continue;
      }
      if (i == newMethods.getLength() - 1) {
        if (isWhiteSpace(node)) {
          break;
        }
      }
    }

    List<Node> nodesToDelete = new ArrayList();
    NodeList children = existingRootElement.getChildNodes();
    int length = children.getLength();
    for (int i = 0; i < length; i++)
    {
      Node node = children.item(i);
      if (isGeneratedNode(node,methods)) {
        nodesToDelete.add(node);
      } else if ((isWhiteSpace(node)) && (isGeneratedNode(children.item(i + 1),methods))) {
        nodesToDelete.add(node);
      }
    }
    for (Node node : nodesToDelete) {
      existingRootElement.removeChild(node);
    }
    children = newRootElement.getChildNodes();
    length = children.getLength();
    Node firstChild = existingRootElement.getFirstChild();
    for (int i = 0; i < length; i++)
    {
      Node node = children.item(i);
      if ((i == length - 1) &&
        (isWhiteSpace(node))) {
        break;
      }
      Node newNode = existingDocument.importNode(node, true);
      if (firstChild == null) {
        existingRootElement.appendChild(newNode);
      } else {
        existingRootElement.insertBefore(newNode, firstChild);
      }
    }
    return prettyPrint(existingDocument);
  }

  private static String prettyPrint(Document document)
    throws ShellException
  {
    DomWriter dw = new DomWriter();
    String s = dw.toString(document);
    return s;
  }
  private static boolean isGeneratedNode(Node node, List<String> methods) {
    boolean rc = false;

    if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
      Element element = (Element) node;
      String id = element.getAttribute("id"); //$NON-NLS-1$
      if (methods.contains(id)) {
        return true;
      }

      if (id != null) {
        for (String prefix : MergeConstants.OLD_XML_ELEMENT_PREFIXES) {
          if (id.startsWith(prefix)) {
            rc = true;
            break;
          }
        }
      }

    }
    return rc;
  }
//  private static boolean isGeneratedNode(Node node)
//  {
//    boolean rc = false;
//    if ((node != null) && (node.getNodeType() == 1))
//    {
//      Element element = (Element)node;
//      String id = element.getAttribute("id");
//      if (id != null) {
//        for (String prefix : MergeConstants.OLD_XML_ELEMENT_PREFIXES) {
//          if (id.startsWith(prefix))
//          {
//            rc = true;
//            break;
//          }
//        }
//      }
//      if (!rc)
//      {
//        NodeList children = node.getChildNodes();
//        int length = children.getLength();
//        for (int i = 0; i < length; i++)
//        {
//          Node childNode = children.item(i);
//          if (!isWhiteSpace(childNode))
//          {
//            if (childNode.getNodeType() != 8) {
//              break;
//            }
//            Comment comment = (Comment)childNode;
//            String commentData = comment.getData();
//            for (String tag : MergeConstants.OLD_ELEMENT_TAGS) {
//              if (commentData.contains(tag))
//              {
//                rc = true;
//                break;
//              }
//            }
//          }
//        }
//      }
//    }
//    return rc;
//  }
private static boolean isWhiteSpace(Node node)
{
  boolean rc = false;
  if ((node != null) && (node.getNodeType() == 3))
  {
    Text tn = (Text)node;
    if (tn.getData().trim().length() == 0) {
      rc = true;
    }
  }
  return rc;
}
}

