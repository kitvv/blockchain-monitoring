
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
    "__inputs",
    "__requires",
    "annotations",
    "editable",
    "gnetId",
    "graphTooltip",
    "hideControls",
    "id",
    "links",
    "refresh",
    "rows",
    "schemaVersion",
    "style",
    "tags",
    "templating",
    "time",
    "timepicker",
    "timezone",
    "title",
    "version"
})
public class DashboardInfo {

    @JsonProperty("__inputs")
    private List<Input> inputs = null;
    @JsonProperty("__requires")
    private List<Require> requires = null;
    @JsonProperty("annotations")
    private Annotations annotations;
    @JsonProperty("editable")
    private Boolean editable;
    @JsonProperty("gnetId")
    private Object gnetId;
    @JsonProperty("graphTooltip")
    private Integer graphTooltip;
    @JsonProperty("hideControls")
    private Boolean hideControls;
    @JsonProperty("id")
    private Object id;
    @JsonProperty("links")
    private List<Object> links = null;
    @JsonProperty("refresh")
    private String refresh;
    @JsonProperty("rows")
    private List<Row> rows = null;
    @JsonProperty("schemaVersion")
    private Integer schemaVersion;
    @JsonProperty("style")
    private String style;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("templating")
    private Templating templating;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("timepicker")
    private Timepicker timepicker;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("title")
    private String title;
    @JsonProperty("version")
    private Integer version;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("__inputs")
    public List<Input> getInputs() {
        return inputs;
    }

    @JsonProperty("__inputs")
    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @JsonProperty("__requires")
    public List<Require> getRequires() {
        return requires;
    }

    @JsonProperty("__requires")
    public void setRequires(List<Require> requires) {
        this.requires = requires;
    }

    @JsonProperty("annotations")
    public Annotations getAnnotations() {
        return annotations;
    }

    @JsonProperty("annotations")
    public void setAnnotations(Annotations annotations) {
        this.annotations = annotations;
    }

    @JsonProperty("editable")
    public Boolean getEditable() {
        return editable;
    }

    @JsonProperty("editable")
    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    @JsonProperty("gnetId")
    public Object getGnetId() {
        return gnetId;
    }

    @JsonProperty("gnetId")
    public void setGnetId(Object gnetId) {
        this.gnetId = gnetId;
    }

    @JsonProperty("graphTooltip")
    public Integer getGraphTooltip() {
        return graphTooltip;
    }

    @JsonProperty("graphTooltip")
    public void setGraphTooltip(Integer graphTooltip) {
        this.graphTooltip = graphTooltip;
    }

    @JsonProperty("hideControls")
    public Boolean getHideControls() {
        return hideControls;
    }

    @JsonProperty("hideControls")
    public void setHideControls(Boolean hideControls) {
        this.hideControls = hideControls;
    }

    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Object id) {
        this.id = id;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @JsonProperty("refresh")
    public String getRefresh() {
        return refresh;
    }

    @JsonProperty("refresh")
    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    @JsonProperty("rows")
    public List<Row> getRows() {
        return rows;
    }

    @JsonProperty("rows")
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @JsonProperty("schemaVersion")
    public Integer getSchemaVersion() {
        return schemaVersion;
    }

    @JsonProperty("schemaVersion")
    public void setSchemaVersion(Integer schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    @JsonProperty("style")
    public String getStyle() {
        return style;
    }

    @JsonProperty("style")
    public void setStyle(String style) {
        this.style = style;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("templating")
    public Templating getTemplating() {
        return templating;
    }

    @JsonProperty("templating")
    public void setTemplating(Templating templating) {
        this.templating = templating;
    }

    @JsonProperty("time")
    public Time getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Time time) {
        this.time = time;
    }

    @JsonProperty("timepicker")
    public Timepicker getTimepicker() {
        return timepicker;
    }

    @JsonProperty("timepicker")
    public void setTimepicker(Timepicker timepicker) {
        this.timepicker = timepicker;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
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
