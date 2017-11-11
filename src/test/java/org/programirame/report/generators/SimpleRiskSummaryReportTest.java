package org.programirame.report.generators;

import org.junit.Test;
import org.programirame.utilities.FileUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SimpleRiskSummaryReportTest {
    @Test
    public void getTextReport() throws Exception {

        SimpleRiskSummaryReport jsonReport = new SimpleRiskSummaryReport();
        String json = jsonReport.getTextReport(FileUtils.getFile(getClass(), "simpleSample.xml"));

        assertThat(json).isEqualTo("Number of hosts: 1\n" +
                "Number of ports: 1\n" +
                "Host: Dc2-windows2k3.internal.example.com\n" +
                "      445\n" +
                "Risk level: 2\n" +
                "Total number: 4\n" +
                "      CVE-2010-0719\n" +
                "      CVE-2007-1912\n" +
                "      CVE-2015-1702\n" +
                "      CVE-2015-0006\n" +
                "Risk level: 4\n" +
                "Total number: 1\n" +
                "      null\n");
    }
}