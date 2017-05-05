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
        "fillColor",
        "full",
        "lineColor",
        "show"
})
public class Sparkline {

    @JsonProperty("fillColor")
    private String fillColor;
    @JsonProperty("full")
    private Boolean full;
    @JsonProperty("lineColor")
    private String lineColor;
    @JsonProperty("show")
    private Boolean show;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fillColor")
    public String getFillColor() {
        return fillColor;
    }

    @JsonProperty("fillColor")
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    @JsonProperty("full")
    public Boolean getFull() {
        return full;
    }

    @JsonProperty("full")
    public void setFull(Boolean full) {
        this.full = full;
    }

    @JsonProperty("lineColor")
    public String getLineColor() {
        return lineColor;
    }

    @JsonProperty("lineColor")
    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    @JsonProperty("show")
    public Boolean getShow() {
        return show;
    }

    @JsonProperty("show")
    public void setShow(Boolean show) {
        this.show = show;
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
