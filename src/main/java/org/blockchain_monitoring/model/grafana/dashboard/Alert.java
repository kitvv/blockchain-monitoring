
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
    "conditions",
    "executionErrorState",
    "frequency",
    "handler",
    "message",
    "name",
    "noDataState",
    "notifications"
})
public class Alert {

    @JsonProperty("conditions")
    private List<Condition> conditions = null;
    @JsonProperty("executionErrorState")
    private String executionErrorState;
    @JsonProperty("frequency")
    private String frequency;
    @JsonProperty("handler")
    private Integer handler;
    @JsonProperty("message")
    private String message;
    @JsonProperty("name")
    private String name;
    @JsonProperty("noDataState")
    private String noDataState;
    @JsonProperty("notifications")
    private List<Object> notifications = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("conditions")
    public List<Condition> getConditions() {
        return conditions;
    }

    @JsonProperty("conditions")
    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @JsonProperty("executionErrorState")
    public String getExecutionErrorState() {
        return executionErrorState;
    }

    @JsonProperty("executionErrorState")
    public void setExecutionErrorState(String executionErrorState) {
        this.executionErrorState = executionErrorState;
    }

    @JsonProperty("frequency")
    public String getFrequency() {
        return frequency;
    }

    @JsonProperty("frequency")
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @JsonProperty("handler")
    public Integer getHandler() {
        return handler;
    }

    @JsonProperty("handler")
    public void setHandler(Integer handler) {
        this.handler = handler;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("noDataState")
    public String getNoDataState() {
        return noDataState;
    }

    @JsonProperty("noDataState")
    public void setNoDataState(String noDataState) {
        this.noDataState = noDataState;
    }

    @JsonProperty("notifications")
    public List<Object> getNotifications() {
        return notifications;
    }

    @JsonProperty("notifications")
    public void setNotifications(List<Object> notifications) {
        this.notifications = notifications;
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
