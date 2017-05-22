
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
    "shared",
    "sort",
    "value_type"
})
public class Tooltip {

    @JsonProperty("shared")
    private Boolean shared;
    @JsonProperty("sort")
    private Integer sort;
    @JsonProperty("value_type")
    private String valueType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("shared")
    public Boolean getShared() {
        return shared;
    }

    @JsonProperty("shared")
    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    @JsonProperty("sort")
    public Integer getSort() {
        return sort;
    }

    @JsonProperty("sort")
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @JsonProperty("value_type")
    public String getValueType() {
        return valueType;
    }

    @JsonProperty("value_type")
    public void setValueType(String valueType) {
        this.valueType = valueType;
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
