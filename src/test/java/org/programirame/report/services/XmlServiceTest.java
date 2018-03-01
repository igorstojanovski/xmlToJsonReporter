package org.programirame.report.services;

import org.assertj.core.api.SoftAssertions;
import org.igorski.report.services.XmlService;
import org.junit.Test;
import org.igorski.report.ReportGenerationException;
import org.igorski.report.model.xml.DetailType;
import org.igorski.report.model.xml.MainType;
import org.programirame.utilities.FileUtils;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class XmlServiceTest {

    @Test
    public void shouldReturnTheCorrectMainTypeModel() throws ReportGenerationException {
        XmlService xmlService = new XmlService();
        MainType mainType = xmlService.getMainType(FileUtils.getFile(getClass(), "simpleSample.xml"));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mainType.getReportinfo().getId()).isEqualTo("CA1A26C8932B9852C5BC01849677B532");
        softAssertions.assertThat(mainType.getReportinfo().getSelectedschedulejob()).isEqualTo("DC2 schedule");
        softAssertions.assertThat(mainType.getReportinfo().getType()).isEqualTo("Vulnerability");
        softAssertions.assertAll();

        List<DetailType> details = mainType.getDetaillist().getDetail();
        assertThat(details.size()).isEqualTo(5);

        DetailType detailType = details.get(2);
        softAssertions.assertThat(detailType.getCve().getId()).isEqualTo("CVE-2007-1912");
        softAssertions.assertThat(detailType.getPortinfo().getPortnumber()).isEqualTo(445);
        softAssertions.assertThat(detailType.getPortinfo().getProtocol()).isEqualTo("TCP");
        softAssertions.assertAll();
    }

}