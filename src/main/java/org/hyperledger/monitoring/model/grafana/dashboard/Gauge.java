package org.hyperledger.monitoring.model.grafana.dashboard;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "maxValue",
        "minValue",
        "show",
        "thresholdLabels",
        "thresholdMarkers"
})
public class Gauge {

    @JsonProperty("maxValue")
    private Integer maxValue;
    @JsonProperty("minValue")
    private Integer minValue;
    @JsonProperty("show")
    private Boolean show;
    @JsonProperty("thresholdLabels")
    private Boolean thresholdLabels;
    @JsonProperty("thresholdMarkers")
    private Boolean thresholdMarkers;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("maxValue")
    public Integer getMaxValue() {
        return maxValue;
    }

    @JsonProperty("maxValue")
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    @JsonProperty("minValue")
    public Integer getMinValue() {
        return minValue;
    }

    @JsonProperty("minValue")
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    @JsonProperty("show")
    public Boolean getShow() {
        return show;
    }

    @JsonProperty("show")
    public void setShow(Boolean show) {
        this.show = show;
    }

    @JsonProperty("thresholdLabels")
    public Boolean getThresholdLabels() {
        return thresholdLabels;
    }

    @JsonProperty("thresholdLabels")
    public void setThresholdLabels(Boolean thresholdLabels) {
        this.thresholdLabels = thresholdLabels;
    }

    @JsonProperty("thresholdMarkers")
    public Boolean getThresholdMarkers() {
        return thresholdMarkers;
    }

    @JsonProperty("thresholdMarkers")
    public void setThresholdMarkers(Boolean thresholdMarkers) {
        this.thresholdMarkers = thresholdMarkers;
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
