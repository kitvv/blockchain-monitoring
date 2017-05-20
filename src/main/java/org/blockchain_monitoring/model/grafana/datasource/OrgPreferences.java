package org.blockchain_monitoring.model.grafana.datasource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "theme",
        "homeDashboardId",
        "timezone"
})
public class OrgPreferences {

    @JsonProperty("theme")
    private String theme;
    @JsonProperty("homeDashboardId")
    private Integer homeDashboardId;
    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("theme")
    public String getTheme() {
        return theme;
    }

    @JsonProperty("theme")
    public void setTheme(String theme) {
        this.theme = theme;
    }

    @JsonProperty("homeDashboardId")
    public Integer getHomeDashboardId() {
        return homeDashboardId;
    }

    @JsonProperty("homeDashboardId")
    public void setHomeDashboardId(Integer homeDashboardId) {
        this.homeDashboardId = homeDashboardId;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
