
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
    "evaluator",
    "operator",
    "query",
    "reducer",
    "type"
})
public class Condition {

    @JsonProperty("evaluator")
    private Evaluator evaluator;
    @JsonProperty("operator")
    private Operator operator;
    @JsonProperty("query")
    private Query query;
    @JsonProperty("reducer")
    private Reducer reducer;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("evaluator")
    public Evaluator getEvaluator() {
        return evaluator;
    }

    @JsonProperty("evaluator")
    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    @JsonProperty("operator")
    public Operator getOperator() {
        return operator;
    }

    @JsonProperty("operator")
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @JsonProperty("query")
    public Query getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(Query query) {
        this.query = query;
    }

    @JsonProperty("reducer")
    public Reducer getReducer() {
        return reducer;
    }

    @JsonProperty("reducer")
    public void setReducer(Reducer reducer) {
        this.reducer = reducer;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
