package org.programirame;

import org.programirame.report.generators.JsonRiskSummaryReport;
import org.programirame.report.generators.SimpleRiskSummaryReport;
import org.programirame.report.generators.TextReporter;
import org.programirame.utility.XmlDownloader;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws JAXBException, FileNotFoundException, XMLStreamException {
        XmlDownloader xmlDownloader = new XmlDownloader();
        Optional<File> xmlFile = xmlDownloader.downloadUrl(args[0]);

        if(xmlFile.isPresent()) {
            TextReporter reporter = new JsonRiskSummaryReport();
            System.out.println(reporter.getTextReport(xmlFile.get()));
        }
    }

}
