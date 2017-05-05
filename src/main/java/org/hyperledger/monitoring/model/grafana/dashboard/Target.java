package org.hyperledger.monitoring.model.grafana.dashboard;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dsType",
        "groupBy",
        "hide",
        "measurement",
        "policy",
        "query",
        "rawQuery",
        "refId",
        "resultFormat",
        "select",
        "tags"
})
public class Target implements Cloneable {

    @JsonProperty("dsType")
    private String dsType;
    @JsonProperty("groupBy")
    private List<Object> groupBy = null;
    @JsonProperty("hide")
    private Boolean hide;
    @JsonProperty("measurement")
    private String measurement;
    @JsonProperty("policy")
    private String policy;
    @JsonProperty("query")
    private String query;
    @JsonProperty("rawQuery")
    private Boolean rawQuery;
    @JsonProperty("refId")
    private String refId;
    @JsonProperty("resultFormat")
    private String resultFormat;
    @JsonProperty("select")
    private List<List<Select>> select = null;
    @JsonProperty("tags")
    private List<Tag> tags = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dsType")
    public String getDsType() {
        return dsType;
    }

    @JsonProperty("dsType")
    public void setDsType(String dsType) {
        this.dsType = dsType;
    }

    @JsonProperty("groupBy")
    public List<Object> getGroupBy() {
        return groupBy;
    }

    @JsonProperty("groupBy")
    public void setGroupBy(List<Object> groupBy) {
        this.groupBy = groupBy;
    }

    @JsonProperty("hide")
    public Boolean getHide() {
        return hide;
    }

    @JsonProperty("hide")
    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    @JsonProperty("measurement")
    public String getMeasurement() {
        return measurement;
    }

    @JsonProperty("measurement")
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @JsonProperty("policy")
    public String getPolicy() {
        return policy;
    }

    @JsonProperty("policy")
    public void setPolicy(String policy) {
        this.policy = policy;
    }

    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(String query) {
        this.query = query;
    }

    @JsonProperty("rawQuery")
    public Boolean getRawQuery() {
        return rawQuery;
    }

    @JsonProperty("rawQuery")
    public void setRawQuery(Boolean rawQuery) {
        this.rawQuery = rawQuery;
    }

    @JsonProperty("refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("resultFormat")
    public String getResultFormat() {
        return resultFormat;
    }

    @JsonProperty("resultFormat")
    public void setResultFormat(String resultFormat) {
        this.resultFormat = resultFormat;
    }

    @JsonProperty("select")
    public List<List<Select>> getSelect() {
        return select;
    }

    @JsonProperty("select")
    public void setSelect(List<List<Select>> select) {
        this.select = select;
    }

    @JsonProperty("tags")
    public List<Tag> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public Target clone() throws CloneNotSupportedException {
        return (Target) super.clone();
    }
}
