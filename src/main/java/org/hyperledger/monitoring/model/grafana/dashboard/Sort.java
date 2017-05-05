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
        "col",
        "desc"
})
public class Sort {

    @JsonProperty("col")
    private Integer col;
    @JsonProperty("desc")
    private Boolean desc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("col")
    public Integer getCol() {
        return col;
    }

    @JsonProperty("col")
    public void setCol(Integer col) {
        this.col = col;
    }

    @JsonProperty("desc")
    public Boolean getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(Boolean desc) {
        this.desc = desc;
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
