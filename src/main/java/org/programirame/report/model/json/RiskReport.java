package org.programirame.report.model.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hosts",
        "ports",
        "host_info",
        "risk_info"
})
public class RiskReport {

    @JsonProperty("hosts")
    private Integer hosts;
    @JsonProperty("ports")
    private Integer ports;
    @JsonProperty("host_info")
    private List<HostInfo> hostInfo = null;
    @JsonProperty("risk_info")
    private List<RiskInfo> riskInfo = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("hosts")
    public Integer getHosts() {
        return hosts;
    }

    @JsonProperty("hosts")
    public void setHosts(Integer hosts) {
        this.hosts = hosts;
    }

    @JsonProperty("ports")
    public Integer getPorts() {
        return ports;
    }

    @JsonProperty("ports")
    public void setPorts(Integer ports) {
        this.ports = ports;
    }

    @JsonProperty("host_info")
    public List<HostInfo> getHostInfo() {
        return hostInfo;
    }

    @JsonProperty("host_info")
    public void setHostInfo(List<HostInfo> hostInfo) {
        this.hostInfo = hostInfo;
    }

    @JsonProperty("risk_info")
    public List<RiskInfo> getRiskInfo() {
        return riskInfo;
    }

    @JsonProperty("risk_info")
    public void setRiskInfo(List<RiskInfo> riskInfo) {
        this.riskInfo = riskInfo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
