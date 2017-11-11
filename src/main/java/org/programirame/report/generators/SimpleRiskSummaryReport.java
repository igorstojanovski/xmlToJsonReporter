package org.programirame.report.generators;

import org.programirame.report.model.xml.DetailType;
import org.programirame.report.model.xml.MainType;
import org.programirame.report.services.XmlService;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.System.lineSeparator;

/**
 * Generates a simple report that hold the following information:
 *
 *      Number of high risks
 *      List of CVE
 *      Number of medium risks
 *      List of CVE
 *      Number of low risks
 *      List of CVE
 *      Number of ports open
 *      List of open ports
 *      Number of hosts
 *      List of hosts
 *
 */
public class SimpleRiskSummaryReport implements TextReporter {

    private final HashMap<Integer, Set<String>> riskLevelToCve = new HashMap<>();
    private final HashMap<String, Set<Integer>> hostToPortInfo = new HashMap<>();
    private static final XmlService XML_SERVICE = new XmlService();

    /**
     * The input file is expected to be an xml file valid against the given sample.xsd schema in the reports folder.
     *
     * @param dataSource the file to base the report on
     * @return string based report
     */
    @Override
    public String getTextReport(File dataSource) {
        MainType mainType  = XML_SERVICE.getMainType(dataSource);

        setReportInfo(mainType);
        getRiskBuilder();

        return getHostInfoBuilder().append(getRiskBuilder()).toString();

    }

    private void setReportInfo(MainType mainType) {
        List<DetailType> details = mainType.getDetaillist().getDetail();

        for(DetailType detailType : details) {
            addCveInfo(detailType);
            addPortHostInfo(detailType);
        }
    }

    private StringBuilder getRiskBuilder() {
        final StringBuilder riskInfoBuilder = new StringBuilder();
        for(Map.Entry<Integer, Set<String>> entry : riskLevelToCve.entrySet()) {
            riskInfoBuilder.append("Risk level: ").append(entry.getKey()).append(lineSeparator());
            riskInfoBuilder.append("Total number: ").append(entry.getValue().size()).append(lineSeparator());
            entry.getValue().forEach(a -> riskInfoBuilder.append("      ").append(a).append(lineSeparator()));
        }

        return riskInfoBuilder;
    }

    private StringBuilder getHostInfoBuilder() {
        final StringBuilder hostInfoBuilder = new StringBuilder();
        int hosts = 0;
        int ports = 0;
        for(Map.Entry<String, Set<Integer>> entry : hostToPortInfo.entrySet()) {
            hosts++;
            ports = ports + entry.getValue().size();
            hostInfoBuilder.append("Host: ").append(entry.getKey()).append(lineSeparator());
            entry.getValue().forEach(a -> hostInfoBuilder.append("      ").append(a).append(lineSeparator()));
        }
        hostInfoBuilder.insert(0, "Number of ports: " + ports + lineSeparator());
        hostInfoBuilder.insert(0, "Number of hosts: " + hosts + lineSeparator());

        return hostInfoBuilder;
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
