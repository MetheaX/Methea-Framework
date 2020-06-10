package io.methea.domain.configuration.display.filter;

import io.methea.domain.basefilter.AbstractMetheaFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 08/06/2020
 */
public class DataTableFilter extends AbstractMetheaFilter<DataTableFilter> {
    private static final long serialVersionUID = -78396861930892083L;

    private String viewName;
    private String labelColumnHead;
    private String allowFilter;
    private String columnKey;
    private String sequence;
    private String status;

    public DataTableFilter() {
        this.viewName = StringUtils.EMPTY;
        this.labelColumnHead = StringUtils.EMPTY;
        this.allowFilter = StringUtils.EMPTY;
        this.columnKey = StringUtils.EMPTY;
        this.sequence = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
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

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
