package org.programirame.report.generators;

import org.programirame.report.ReportGenerationException;

import java.io.File;

/**
 * Creates a text report.
 */
public interface TextReporter {

    /**
     * Returns a string report generated based on the content of the given file.
     *
     * @param dataSource the file to base the report on
     * @return the string report
     * @throws ReportGenerationException if there was any problem in the process of generation the report and/or <br>
     *     working with the given file.
     */
    String getTextReport(File dataSource) throws ReportGenerationException;

}
