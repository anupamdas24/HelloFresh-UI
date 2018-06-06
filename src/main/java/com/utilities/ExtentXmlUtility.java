package com.utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ExtentXmlUtility {
public static void extentXmlGenerate() throws ParserConfigurationException, TransformerException {
DocumentBuilderFactory dbFactory =
        DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("extentreports");
        doc.appendChild(rootElement);

        Element configuration = doc.createElement("configuration");
        rootElement.appendChild(configuration);

        Element theme = doc.createElement("theme");
        configuration.appendChild(theme);
        theme.appendChild(doc.createTextNode("standard"));

        Element encoding = doc.createElement("encoding");
        configuration.appendChild(encoding);
        encoding.appendChild(doc.createTextNode("UTF-8"));

        Element protocol = doc.createElement("protocol");
        configuration.appendChild(protocol);
        protocol.appendChild(doc.createTextNode("https"));

        Element documentTitle = doc.createElement("documentTitle");
        configuration.appendChild(documentTitle);
        documentTitle.appendChild(doc.createTextNode("Automation Report"));

        Element reportName = doc.createElement("reportName");
        configuration.appendChild(reportName);
        reportName.appendChild(doc.createTextNode(System.getProperty("env.product")));
        // reportName.appendChild(doc.createTextNode("report title"));

        Element reportHeadline = doc.createElement("reportHeadline");
        configuration.appendChild(reportHeadline);
        reportHeadline.appendChild(doc.createTextNode(" ("+ Getconfig.getProperties("URL")+" )"));

        Element dateFormat = doc.createElement("dateFormat");
        configuration.appendChild(dateFormat);
        dateFormat.appendChild(doc.createTextNode("yyyy-MM-dd"));

        Element timeFormat = doc.createElement("timeFormat");
        configuration.appendChild(timeFormat);
        timeFormat.appendChild(doc.createTextNode("HH:mm:ss"));


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + "/src/main/resources/extent-config.xml"));

        transformer.transform(source, result);


        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
}

}