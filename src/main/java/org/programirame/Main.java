package org.programirame;

import org.programirame.report.ReportGenerationException;
import org.programirame.report.generators.JsonRiskSummaryReport;
import org.programirame.report.generators.TextReporter;
import org.programirame.utility.XmlDownloader;

import java.io.File;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        XmlDownloader xmlDownloader = new XmlDownloader();
        Optional<File> xmlFile = xmlDownloader.downloadUrl(args[0]);

        if(xmlFile.isPresent()) {
            TextReporter reporter = new JsonRiskSummaryReport();
            try {
                System.out.println(reporter.getTextReport(xmlFile.get()));
            } catch (ReportGenerationException e) {
                System.out.println("There was an error generating the report.");
                e.printStackTrace();
            }
        }
    }

}
