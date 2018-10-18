package com.tft.framework.common.bean;

import org.hibernate.internal.util.StringHelper;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/8/22 11:06
 *
 * @ClassName TftXml
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class TftXml {

    private Document doc;
    private XPath path;

    TftXml(Document doc){
        XPathFactory xPathFactory = XPathFactory.newInstance();
        path = xPathFactory.newXPath();
        this.doc = doc;
    }

    public static TftXml createDocument(String path)throws Exception{
        File f = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(f);
        return new TftXml(doc);
    }

    public String[] readXml2XPath(String expression)throws Exception{
        String allContent = path.evaluate(expression,this.doc);
        if(StringHelper.isEmpty(allContent)) return null;
        String[] strArr = allContent.split("\n");
        for(int i=0;i<strArr.length;i++){
            String str = strArr[i];
            byte[] b = str.getBytes();
            strArr[i] = str.trim();
        }
        return strArr;
    }

    public int getNodeSize(String expression)throws Exception{
        NodeList list = (NodeList) path.evaluate(expression,doc,XPathConstants.NODESET);
        return list.getLength();
    }
}
