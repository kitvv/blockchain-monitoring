
package org.blockchain_monitoring.model.grafana.dashboard;

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
    "dateFormat",
    "pattern",
    "type",
    "colorMode",
    "colors",
    "decimals",
    "thresholds",
    "unit"
})
public class Style {

    @JsonProperty("dateFormat")
    private String dateFormat;
    @JsonProperty("pattern")
    private String pattern;
    @JsonProperty("type")
    private String type;
    @JsonProperty("colorMode")
    private Object colorMode;
    @JsonProperty("colors")
    private List<String> colors = null;
    @JsonProperty("decimals")
    private Integer decimals;
    @JsonProperty("thresholds")
    private List<Object> thresholds = null;
    @JsonProperty("unit")
    private String unit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dateFormat")
    public String getDateFormat() {
        return dateFormat;
    }

    @JsonProperty("dateFormat")
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @JsonProperty("pattern")
    public String getPattern() {
        return pattern;
    }

    @JsonProperty("pattern")
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("colorMode")
    public Object getColorMode() {
        return colorMode;
    }

    @JsonProperty("colorMode")
    public void setColorMode(Object colorMode) {
        this.colorMode = colorMode;
    }

    @JsonProperty("colors")
    public List<String> getColors() {
        return colors;
    }

    @JsonProperty("colors")
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    @JsonProperty("decimals")
    public Integer getDecimals() {
        return decimals;
    }

    @JsonProperty("decimals")
    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    @JsonProperty("thresholds")
    public List<Object> getThresholds() {
        return thresholds;
    }

    @JsonProperty("thresholds")
    public void setThresholds(List<Object> thresholds) {
        this.thresholds = thresholds;
    }

    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
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
