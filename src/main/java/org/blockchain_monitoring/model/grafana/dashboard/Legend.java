
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
    "alignAsTable",
    "avg",
    "current",
    "hideEmpty",
    "hideZero",
    "max",
    "min",
    "rightSide",
    "show",
    "sideWidth",
    "total",
    "values"
})
public class Legend {

    @JsonProperty("alignAsTable")
    private Boolean alignAsTable;
    @JsonProperty("avg")
    private Boolean avg;
    @JsonProperty("current")
    private Boolean current;
    @JsonProperty("hideEmpty")
    private Boolean hideEmpty;
    @JsonProperty("hideZero")
    private Boolean hideZero;
    @JsonProperty("max")
    private Boolean max;
    @JsonProperty("min")
    private Boolean min;
    @JsonProperty("rightSide")
    private Boolean rightSide;
    @JsonProperty("show")
    private Boolean show;
    @JsonProperty("sideWidth")
    private Object sideWidth;
    @JsonProperty("total")
    private Boolean total;
    @JsonProperty("values")
    private Boolean values;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("alignAsTable")
    public Boolean getAlignAsTable() {
        return alignAsTable;
    }

    @JsonProperty("alignAsTable")
    public void setAlignAsTable(Boolean alignAsTable) {
        this.alignAsTable = alignAsTable;
    }

    @JsonProperty("avg")
    public Boolean getAvg() {
        return avg;
    }

    @JsonProperty("avg")
    public void setAvg(Boolean avg) {
        this.avg = avg;
    }

    @JsonProperty("current")
    public Boolean getCurrent() {
        return current;
    }

    @JsonProperty("current")
    public void setCurrent(Boolean current) {
        this.current = current;
    }

    @JsonProperty("hideEmpty")
    public Boolean getHideEmpty() {
        return hideEmpty;
    }

    @JsonProperty("hideEmpty")
    public void setHideEmpty(Boolean hideEmpty) {
        this.hideEmpty = hideEmpty;
    }

    @JsonProperty("hideZero")
    public Boolean getHideZero() {
        return hideZero;
    }

    @JsonProperty("hideZero")
    public void setHideZero(Boolean hideZero) {
        this.hideZero = hideZero;
    }

    @JsonProperty("max")
    public Boolean getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(Boolean max) {
        this.max = max;
    }

    @JsonProperty("min")
    public Boolean getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(Boolean min) {
        this.min = min;
    }

    @JsonProperty("rightSide")
    public Boolean getRightSide() {
        return rightSide;
    }

    @JsonProperty("rightSide")
    public void setRightSide(Boolean rightSide) {
        this.rightSide = rightSide;
    }

    @JsonProperty("show")
    public Boolean getShow() {
        return show;
    }

    @JsonProperty("show")
    public void setShow(Boolean show) {
        this.show = show;
    }

    @JsonProperty("sideWidth")
    public Object getSideWidth() {
        return sideWidth;
    }

    @JsonProperty("sideWidth")
    public void setSideWidth(Object sideWidth) {
        this.sideWidth = sideWidth;
    }

    @JsonProperty("total")
    public Boolean getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Boolean total) {
        this.total = total;
    }

    @JsonProperty("values")
    public Boolean getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(Boolean values) {
        this.values = values;
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
