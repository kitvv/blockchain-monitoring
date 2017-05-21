
package org.blockchain_monitoring.model.grafana.dashboard;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "format",
    "label",
    "logBase",
    "max",
    "min",
    "show"
})
public class Yaxis {

    @JsonProperty("format")
    private String format;
    @JsonProperty("label")
    private Object label;
    @JsonProperty("logBase")
    private Integer logBase;
    @JsonProperty("max")
    private Object max;
    @JsonProperty("min")
    private Object min;
    @JsonProperty("show")
    private Boolean show;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("label")
    public Object getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(Object label) {
        this.label = label;
    }

    @JsonProperty("logBase")
    public Integer getLogBase() {
        return logBase;
    }

    @JsonProperty("logBase")
    public void setLogBase(Integer logBase) {
        this.logBase = logBase;
    }

    @JsonProperty("max")
    public Object getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(Object max) {
        this.max = max;
    }

    @JsonProperty("min")
    public Object getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(Object min) {
        this.min = min;
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
