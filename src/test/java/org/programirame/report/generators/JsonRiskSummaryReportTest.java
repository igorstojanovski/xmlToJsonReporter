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
                "  \"host_info\": [" +
                "    {" +
                "      \"name\": \"Dc2-windows2k3.internal.example.com\"," +
                "      \"ports\": [" +
                "        445" +
                "      ]" +
                "    }" +
                "  ]," +
                "  \"risk_info\": [" +
                "    {" +
                "      \"level\": 2," +
                "      \"cve\": [" +
                "        \"CVE-2010-0719\"," +
                "        \"CVE-2007-1912\"," +
                "        \"CVE-2015-1702\"," +
                "        \"CVE-2015-0006\"" +
                "      ]," +
                "      \"total_cve\": 4" +
                "    }," +
                "    {" +
                "      \"level\": 4," +
                "      \"cve\": [" +
                "        null" +
                "      ]," +
                "      \"total_cve\": 1" +
                "    }" +
                "  ]" +
                "}");
    }
}