
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
    "colorMode",
    "fill",
    "line",
    "op",
    "value"
})
public class Threshold {

    @JsonProperty("colorMode")
    private String colorMode;
    @JsonProperty("fill")
    private Boolean fill;
    @JsonProperty("line")
    private Boolean line;
    @JsonProperty("op")
    private String op;
    @JsonProperty("value")
    private Integer value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("colorMode")
    public String getColorMode() {
        return colorMode;
    }

    @JsonProperty("colorMode")
    public void setColorMode(String colorMode) {
        this.colorMode = colorMode;
    }

    @JsonProperty("fill")
    public Boolean getFill() {
        return fill;
    }

    @JsonProperty("fill")
    public void setFill(Boolean fill) {
        this.fill = fill;
    }

    @JsonProperty("line")
    public Boolean getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(Boolean line) {
        this.line = line;
    }

    @JsonProperty("op")
    public String getOp() {
        return op;
    }

    @JsonProperty("op")
    public void setOp(String op) {
        this.op = op;
    }

    @JsonProperty("value")
    public Integer getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Integer value) {
        this.value = value;
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
