package org.programirame.report.model.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "level",
        "cve",
        "total_cve"
})
public class RiskInfo {

    @JsonProperty("level")
    private Integer level;
    @JsonProperty("cve")
    private Set<String> cve = null;
    @JsonProperty("total_cve")
    private Integer totalCve;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("level")
    public Integer getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(Integer level) {
        this.level = level;
    }

    @JsonProperty("cve")
    public Set<String> getCve() {
        return cve;
    }

    @JsonProperty("cve")
    public void setCve(Set<String> cve) {
        this.cve = cve;
    }

    @JsonProperty("total_cve")
    public Integer getTotalCve() {
        return totalCve;
    }

    @JsonProperty("total_cve")
    public void setTotalCve(Integer totalCve) {
        this.totalCve = totalCve;
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
