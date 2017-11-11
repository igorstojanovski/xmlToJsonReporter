package org.programirame.report;

public class ReportGenerationException extends RuntimeException {
    public ReportGenerationException(String s, Exception e) {
        super(s, e);
    }
}
