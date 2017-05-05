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
        "refresh_intervals",
        "time_options"
})
public class Timepicker {

    @JsonProperty("refresh_intervals")
    private List<String> refreshIntervals = null;
    @JsonProperty("time_options")
    private List<String> timeOptions = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("refresh_intervals")
    public List<String> getRefreshIntervals() {
        return refreshIntervals;
    }

    @JsonProperty("refresh_intervals")
    public void setRefreshIntervals(List<String> refreshIntervals) {
        this.refreshIntervals = refreshIntervals;
    }

    @JsonProperty("time_options")
    public List<String> getTimeOptions() {
        return timeOptions;
    }

    @JsonProperty("time_options")
    public void setTimeOptions(List<String> timeOptions) {
        this.timeOptions = timeOptions;
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
