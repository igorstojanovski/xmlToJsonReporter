package org.programirame.report.generators;

import org.programirame.report.ReportGenerationException;
import org.programirame.report.model.json.HostInfo;
import org.programirame.report.model.json.RiskInfo;
import org.programirame.report.model.json.RiskReport;
import org.programirame.report.model.xml.DetailType;
import org.programirame.report.model.xml.MainType;
import org.programirame.report.services.XmlService;
import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generates a simple JSON valid against the schema in the  json_report_schema.json file.
 *
 */
public class JsonRiskSummaryReport implements TextReporter {

    private final HashMap<Integer, Set<String>> riskLevelToCve = new HashMap<>();
    private final HashMap<String, Set<Integer>> hostToPortInfo = new HashMap<>();
    private static final XmlService XML_SERVICE = new XmlService();
    /**
     * The input file is expected to be an xml file valid against the given sample.xsd schema in the reports folder.
     *
     * Using JAXB the file content is turned into a data model. That data model is then processed to extract all
     * needed information. From that information a Json model is created and as the last step from the Json data model,
     * a json string is generated and returned.
     *
     * @param dataSource the file to base the report on
     * @return string based report
     */
    @Override
    public String getTextReport(File dataSource) {
        MainType mainType  = XML_SERVICE.getMainType(dataSource);

        setReportInfo(mainType);
        RiskReport riskReport = getJsonReport();

        return getJsonStringFromObject(riskReport);

    }

    private String getJsonStringFromObject(RiskReport riskReport) {
        ObjectMapper objectMapper = new ObjectMapper();

        String s;
        try {
            s = objectMapper.writeValueAsString(riskReport);
        } catch (JsonProcessingException e) {
            throw new ReportGenerationException("Problem when generation JSON.", e);
        }
        return s;
    }

    private RiskReport getJsonReport() {
        RiskReport riskReport = new RiskReport();

        setHostsInfo(riskReport);
        setRisksInfo(riskReport);

        return riskReport;
    }

    private void setHostsInfo(RiskReport riskReport) {
        List<HostInfo> hostsInfo = new ArrayList<>();

        int hostCount = 0;
        int portCount = 0;
        for(Map.Entry<String, Set<Integer>> entry : hostToPortInfo.entrySet()) {
            hostCount++;
            HostInfo hostInfo = new HostInfo();
            hostInfo.setName(entry.getKey());
            portCount = portCount + entry.getValue().size();
            hostInfo.setPorts(entry.getValue());
            hostsInfo.add(hostInfo);
        }

        riskReport.setHosts(hostCount);
        riskReport.setPorts(portCount);
        riskReport.setHostInfo(hostsInfo);
    }

    private void setRisksInfo(RiskReport riskReport) {
        List<RiskInfo> risksInfo = new ArrayList<>();
        for(Map.Entry<Integer, Set<String>> entry : riskLevelToCve.entrySet()) {
            RiskInfo riskInfo = new RiskInfo();
            riskInfo.setCve(entry.getValue());
            riskInfo.setLevel(entry.getKey());
            risksInfo.add(riskInfo);
            riskInfo.setTotalCve(entry.getValue().size());
        }

        riskReport.setRiskInfo(risksInfo);
    }

    private void setReportInfo(MainType mainType) {
        List<DetailType> details = mainType.getDetaillist().getDetail();

        for(DetailType detailType : details) {
            addCveInfo(detailType);
            addPortHostInfo(detailType);
        }
    }

    private void addPortHostInfo(DetailType detailType) {
        Set<Integer> ports = hostToPortInfo.computeIfAbsent(detailType.getHostname(), k -> new HashSet<>());
        ports.add(detailType.getPortinfo().getPortnumber());
    }

    private void addCveInfo(DetailType detailType) {
        Set<String> cves = riskLevelToCve.computeIfAbsent(detailType.getRisk(), k -> new HashSet<>());
        cves.add(detailType.getCve().getId());
    }

}
