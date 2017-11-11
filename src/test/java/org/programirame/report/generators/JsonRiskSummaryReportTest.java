package org.programirame.report.generators;

import org.junit.Test;
import org.programirame.utilities.FileUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class JsonRiskSummaryReportTest {
    @Test
    public void getTextReport() throws Exception {

        JsonRiskSummaryReport jsonReport = new JsonRiskSummaryReport();
        String json = jsonReport.getTextReport(FileUtils.getFile(getClass(), "simpleSample.xml"));

        assertThat(json).isEqualToIgnoringWhitespace("{" +
                "  \"hosts\": 1," +
                "  \"ports\": 1," +
                "  \"hostInfo\": [" +
                "    {" +
                "      \"name\": \"Dc2-windows2k3.internal.example.com\"," +
                "      \"ports\": [" +
                "        445" +
                "      ]," +
                "      \"additionalProperties\": {}" +
                "    }" +
                "  ]," +
                "  \"riskInfo\": [" +
                "    {" +
                "      \"level\": 2," +
                "      \"cve\": [" +
                "        \"CVE-2010-0719\"," +
                "        \"CVE-2007-1912\"," +
                "        \"CVE-2015-1702\"," +
                "        \"CVE-2015-0006\"" +
                "      ]," +
                "      \"totalCve\": 4," +
                "      \"additionalProperties\": {}" +
                "    }," +
                "    {" +
                "      \"level\": 4," +
                "      \"cve\": [" +
                "        null" +
                "      ]," +
                "      \"totalCve\": 1," +
                "      \"additionalProperties\": {}" +
                "    }" +
                "  ]," +
                "  \"additionalProperties\": {}" +
                "}");
    }
}