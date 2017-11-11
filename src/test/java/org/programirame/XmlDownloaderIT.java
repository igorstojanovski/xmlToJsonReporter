package org.programirame;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.programirame.report.generators.JsonRiskSummaryReport;
import org.programirame.report.generators.SimpleRiskSummaryReport;
import org.programirame.report.generators.TextReporter;
import org.programirame.utility.XmlDownloader;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class XmlDownloaderIT {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }

    @Test
    public void shouldGenerateSimpleCorrectStringReport() throws IOException, JAXBException, XMLStreamException {

        String report = getReport("simpleSample.xml", new SimpleRiskSummaryReport());
        assertThat(report).isEqualTo("Number of hosts: 1\n" +
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

    @Test
    public void shouldGenerateComplexCorrectJsonReport() throws IOException, JAXBException, XMLStreamException {

        String report = getReport("complexSample.xml", new JsonRiskSummaryReport());

        assertThat(report).isEqualToIgnoringWhitespace("{\n" +
                "  \"hosts\": 1,\n" +
                "  \"ports\": 2,\n" +
                "  \"hostInfo\": [\n" +
                "    {\n" +
                "      \"name\": \"Dc2-windows2k3.internal.example.com\",\n" +
                "      \"ports\": [\n" +
                "        448,\n" +
                "        445\n" +
                "      ],\n" +
                "      \"additionalProperties\": {}\n" +
                "    }\n" +
                "  ],\n" +
                "  \"riskInfo\": [\n" +
                "    {\n" +
                "      \"level\": 1,\n" +
                "      \"cve\": [\n" +
                "        \"CVE-2015-0A06\"\n" +
                "      ],\n" +
                "      \"totalCve\": 1,\n" +
                "      \"additionalProperties\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"level\": 2,\n" +
                "      \"cve\": [\n" +
                "        \"CVE-2010-0719\",\n" +
                "        \"CVE-2007-1912\",\n" +
                "        \"CVE-2015-1702\",\n" +
                "        \"CVE-2015-0006\"\n" +
                "      ],\n" +
                "      \"totalCve\": 4,\n" +
                "      \"additionalProperties\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"level\": 4,\n" +
                "      \"cve\": [\n" +
                "        null\n" +
                "      ],\n" +
                "      \"totalCve\": 1,\n" +
                "      \"additionalProperties\": {}\n" +
                "    }\n" +
                "  ],\n" +
                "  \"additionalProperties\": {}\n" +
                "}");
    }

    private String getReport(String sampleReportLocation, TextReporter reporter) throws IOException, JAXBException, XMLStreamException {
        stubFor(get(urlEqualTo("/my/resource"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody(readFile(ClassLoader.getSystemResource(sampleReportLocation).getPath()))));

        XmlDownloader xmlDownloader = new XmlDownloader();
        Optional<File> optional = xmlDownloader.downloadUrl("http://localhost:8089/my/resource");
        String report = "";
        if (optional.isPresent()) {
            report = reporter.getTextReport(optional.get());
        }
        return report;
    }
}
