package io.github.metheax.domain.binder;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 18/01/2020
 */
public class DataTableBinder extends BaseBinder<DataTableBinder> {
    private static final long serialVersionUID = 913896882019600220L;

    private String id = StringUtils.EMPTY;
    private String viewName = StringUtils.EMPTY;
    private String labelColumnHead = StringUtils.EMPTY;
    private String allowFilter = StringUtils.EMPTY;
    private String columnKey = StringUtils.EMPTY;
    private Integer sequence = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getLabelColumnHead() {
        return labelColumnHead;
    }

    public void setLabelColumnHead(String labelColumnHead) {
        this.labelColumnHead = labelColumnHead;
    }

    public String getAllowFilter() {
        return allowFilter;
    }

    public void setAllowFilter(String allowFilter) {
        this.allowFilter = allowFilter;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
