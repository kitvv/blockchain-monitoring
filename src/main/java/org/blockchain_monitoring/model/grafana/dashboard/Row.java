
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
    "collapse",
    "height",
    "panels",
    "repeat",
    "repeatIteration",
    "repeatRowId",
    "showTitle",
    "title",
    "titleSize"
})
public class Row {

    @JsonProperty("collapse")
    private Boolean collapse;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("panels")
    private List<Panel> panels = null;
    @JsonProperty("repeat")
    private Object repeat;
    @JsonProperty("repeatIteration")
    private Object repeatIteration;
    @JsonProperty("repeatRowId")
    private Object repeatRowId;
    @JsonProperty("showTitle")
    private Boolean showTitle;
    @JsonProperty("title")
    private String title;
    @JsonProperty("titleSize")
    private String titleSize;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("collapse")
    public Boolean getCollapse() {
        return collapse;
    }

    @JsonProperty("collapse")
    public void setCollapse(Boolean collapse) {
        this.collapse = collapse;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("panels")
    public List<Panel> getPanels() {
        return panels;
    }

    @JsonProperty("panels")
    public void setPanels(List<Panel> panels) {
        this.panels = panels;
    }

    @JsonProperty("repeat")
    public Object getRepeat() {
        return repeat;
    }

    @JsonProperty("repeat")
    public void setRepeat(Object repeat) {
        this.repeat = repeat;
    }

    @JsonProperty("repeatIteration")
    public Object getRepeatIteration() {
        return repeatIteration;
    }

    @JsonProperty("repeatIteration")
    public void setRepeatIteration(Object repeatIteration) {
        this.repeatIteration = repeatIteration;
    }

    @JsonProperty("repeatRowId")
    public Object getRepeatRowId() {
        return repeatRowId;
    }

    @JsonProperty("repeatRowId")
    public void setRepeatRowId(Object repeatRowId) {
        this.repeatRowId = repeatRowId;
    }

    @JsonProperty("showTitle")
    public Boolean getShowTitle() {
        return showTitle;
    }

    @JsonProperty("showTitle")
    public void setShowTitle(Boolean showTitle) {
        this.showTitle = showTitle;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("titleSize")
    public String getTitleSize() {
        return titleSize;
    }

    @JsonProperty("titleSize")
    public void setTitleSize(String titleSize) {
        this.titleSize = titleSize;
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
