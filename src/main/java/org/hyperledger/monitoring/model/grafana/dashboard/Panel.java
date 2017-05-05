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
        "cacheTimeout",
        "colorBackground",
        "colorValue",
        "colors",
        "datasource",
        "format",
        "gauge",
        "id",
        "interval",
        "links",
        "mappingType",
        "mappingTypes",
        "maxDataPoints",
        "nullPointMode",
        "nullText",
        "postfix",
        "postfixFontSize",
        "prefix",
        "prefixFontSize",
        "rangeMaps",
        "span",
        "sparkline",
        "targets",
        "thresholds",
        "title",
        "type",
        "valueFontSize",
        "valueMaps",
        "valueName",
        "decimals",
        "columns",
        "filterNull",
        "fontSize",
        "pageSize",
        "scroll",
        "showHeader",
        "sort",
        "styles",
        "transform"
})
public class Panel implements Cloneable {

    @JsonProperty("cacheTimeout")
    private Object cacheTimeout;
    @JsonProperty("colorBackground")
    private Boolean colorBackground;
    @JsonProperty("colorValue")
    private Boolean colorValue;
    @JsonProperty("colors")
    private List<String> colors = null;
    @JsonProperty("datasource")
    private Object datasource;
    @JsonProperty("format")
    private String format;
    @JsonProperty("gauge")
    private Gauge gauge;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("interval")
    private Object interval;
    @JsonProperty("links")
    private List<Object> links = null;
    @JsonProperty("mappingType")
    private Integer mappingType;
    @JsonProperty("mappingTypes")
    private List<MappingType> mappingTypes = null;
    @JsonProperty("maxDataPoints")
    private Integer maxDataPoints;
    @JsonProperty("nullPointMode")
    private String nullPointMode;
    @JsonProperty("nullText")
    private Object nullText;
    @JsonProperty("postfix")
    private String postfix;
    @JsonProperty("postfixFontSize")
    private String postfixFontSize;
    @JsonProperty("prefix")
    private String prefix;
    @JsonProperty("prefixFontSize")
    private String prefixFontSize;
    @JsonProperty("rangeMaps")
    private List<RangeMap> rangeMaps = null;
    @JsonProperty("span")
    private Integer span;
    @JsonProperty("sparkline")
    private Sparkline sparkline;
    @JsonProperty("targets")
    private List<Target> targets = null;
    @JsonProperty("thresholds")
    private String thresholds;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("valueFontSize")
    private String valueFontSize;
    @JsonProperty("valueMaps")
    private List<ValueMap> valueMaps = null;
    @JsonProperty("valueName")
    private String valueName;
    @JsonProperty("decimals")
    private Object decimals;
    @JsonProperty("columns")
    private List<Object> columns = null;
    @JsonProperty("filterNull")
    private Boolean filterNull;
    @JsonProperty("fontSize")
    private String fontSize;
    @JsonProperty("pageSize")
    private Object pageSize;
    @JsonProperty("scroll")
    private Boolean scroll;
    @JsonProperty("showHeader")
    private Boolean showHeader;
    @JsonProperty("sort")
    private Sort sort;
    @JsonProperty("styles")
    private List<Style> styles = null;
    @JsonProperty("transform")
    private String transform;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cacheTimeout")
    public Object getCacheTimeout() {
        return cacheTimeout;
    }

    @JsonProperty("cacheTimeout")
    public void setCacheTimeout(Object cacheTimeout) {
        this.cacheTimeout = cacheTimeout;
    }

    @JsonProperty("colorBackground")
    public Boolean getColorBackground() {
        return colorBackground;
    }

    @JsonProperty("colorBackground")
    public void setColorBackground(Boolean colorBackground) {
        this.colorBackground = colorBackground;
    }

    @JsonProperty("colorValue")
    public Boolean getColorValue() {
        return colorValue;
    }

    @JsonProperty("colorValue")
    public void setColorValue(Boolean colorValue) {
        this.colorValue = colorValue;
    }

    @JsonProperty("colors")
    public List<String> getColors() {
        return colors;
    }

    @JsonProperty("colors")
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    @JsonProperty("datasource")
    public Object getDatasource() {
        return datasource;
    }

    @JsonProperty("datasource")
    public void setDatasource(Object datasource) {
        this.datasource = datasource;
    }

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("gauge")
    public Gauge getGauge() {
        return gauge;
    }

    @JsonProperty("gauge")
    public void setGauge(Gauge gauge) {
        this.gauge = gauge;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("interval")
    public Object getInterval() {
        return interval;
    }

    @JsonProperty("interval")
    public void setInterval(Object interval) {
        this.interval = interval;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @JsonProperty("mappingType")
    public Integer getMappingType() {
        return mappingType;
    }

    @JsonProperty("mappingType")
    public void setMappingType(Integer mappingType) {
        this.mappingType = mappingType;
    }

    @JsonProperty("mappingTypes")
    public List<MappingType> getMappingTypes() {
        return mappingTypes;
    }

    @JsonProperty("mappingTypes")
    public void setMappingTypes(List<MappingType> mappingTypes) {
        this.mappingTypes = mappingTypes;
    }

    @JsonProperty("maxDataPoints")
    public Integer getMaxDataPoints() {
        return maxDataPoints;
    }

    @JsonProperty("maxDataPoints")
    public void setMaxDataPoints(Integer maxDataPoints) {
        this.maxDataPoints = maxDataPoints;
    }

    @JsonProperty("nullPointMode")
    public String getNullPointMode() {
        return nullPointMode;
    }

    @JsonProperty("nullPointMode")
    public void setNullPointMode(String nullPointMode) {
        this.nullPointMode = nullPointMode;
    }

    @JsonProperty("nullText")
    public Object getNullText() {
        return nullText;
    }

    @JsonProperty("nullText")
    public void setNullText(Object nullText) {
        this.nullText = nullText;
    }

    @JsonProperty("postfix")
    public String getPostfix() {
        return postfix;
    }

    @JsonProperty("postfix")
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    @JsonProperty("postfixFontSize")
    public String getPostfixFontSize() {
        return postfixFontSize;
    }

    @JsonProperty("postfixFontSize")
    public void setPostfixFontSize(String postfixFontSize) {
        this.postfixFontSize = postfixFontSize;
    }

    @JsonProperty("prefix")
    public String getPrefix() {
        return prefix;
    }

    @JsonProperty("prefix")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @JsonProperty("prefixFontSize")
    public String getPrefixFontSize() {
        return prefixFontSize;
    }

    @JsonProperty("prefixFontSize")
    public void setPrefixFontSize(String prefixFontSize) {
        this.prefixFontSize = prefixFontSize;
    }

    @JsonProperty("rangeMaps")
    public List<RangeMap> getRangeMaps() {
        return rangeMaps;
    }

    @JsonProperty("rangeMaps")
    public void setRangeMaps(List<RangeMap> rangeMaps) {
        this.rangeMaps = rangeMaps;
    }

    @JsonProperty("span")
    public Integer getSpan() {
        return span;
    }

    @JsonProperty("span")
    public void setSpan(Integer span) {
        this.span = span;
    }

    @JsonProperty("sparkline")
    public Sparkline getSparkline() {
        return sparkline;
    }

    @JsonProperty("sparkline")
    public void setSparkline(Sparkline sparkline) {
        this.sparkline = sparkline;
    }

    @JsonProperty("targets")
    public List<Target> getTargets() {
        return targets;
    }

    @JsonProperty("targets")
    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    @JsonProperty("thresholds")
    public String getThresholds() {
        return thresholds;
    }

    @JsonProperty("thresholds")
    public void setThresholds(String thresholds) {
        this.thresholds = thresholds;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("valueFontSize")
    public String getValueFontSize() {
        return valueFontSize;
    }

    @JsonProperty("valueFontSize")
    public void setValueFontSize(String valueFontSize) {
        this.valueFontSize = valueFontSize;
    }

    @JsonProperty("valueMaps")
    public List<ValueMap> getValueMaps() {
        return valueMaps;
    }

    @JsonProperty("valueMaps")
    public void setValueMaps(List<ValueMap> valueMaps) {
        this.valueMaps = valueMaps;
    }

    @JsonProperty("valueName")
    public String getValueName() {
        return valueName;
    }

    @JsonProperty("valueName")
    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    @JsonProperty("decimals")
    public Object getDecimals() {
        return decimals;
    }

    @JsonProperty("decimals")
    public void setDecimals(Object decimals) {
        this.decimals = decimals;
    }

    @JsonProperty("columns")
    public List<Object> getColumns() {
        return columns;
    }

    @JsonProperty("columns")
    public void setColumns(List<Object> columns) {
        this.columns = columns;
    }

    @JsonProperty("filterNull")
    public Boolean getFilterNull() {
        return filterNull;
    }

    @JsonProperty("filterNull")
    public void setFilterNull(Boolean filterNull) {
        this.filterNull = filterNull;
    }

    @JsonProperty("fontSize")
    public String getFontSize() {
        return fontSize;
    }

    @JsonProperty("fontSize")
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    @JsonProperty("pageSize")
    public Object getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(Object pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("scroll")
    public Boolean getScroll() {
        return scroll;
    }

    @JsonProperty("scroll")
    public void setScroll(Boolean scroll) {
        this.scroll = scroll;
    }

    @JsonProperty("showHeader")
    public Boolean getShowHeader() {
        return showHeader;
    }

    @JsonProperty("showHeader")
    public void setShowHeader(Boolean showHeader) {
        this.showHeader = showHeader;
    }

    @JsonProperty("sort")
    public Sort getSort() {
        return sort;
    }

    @JsonProperty("sort")
    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @JsonProperty("styles")
    public List<Style> getStyles() {
        return styles;
    }

    @JsonProperty("styles")
    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    @JsonProperty("transform")
    public String getTransform() {
        return transform;
    }

    @JsonProperty("transform")
    public void setTransform(String transform) {
        this.transform = transform;
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
    public Panel clone() throws CloneNotSupportedException {
        return (Panel) super.clone();
    }
}
