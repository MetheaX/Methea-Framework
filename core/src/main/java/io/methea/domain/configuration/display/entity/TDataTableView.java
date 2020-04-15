package io.methea.domain.configuration.display.entity;

import io.methea.domain.baseentity.BaseEntity;

import javax.persistence.*;

/**
 * Author : DKSilverX
 * Date : 18/01/2020
 */
@Entity
@Table(name = "tbl_core_datatable_view")
public class TDataTableView extends BaseEntity {

    @Id
    private String id;
    private String viewName;
    private String labelColumnHead;
    private String allowFilter;
    private String columnKey;
    private Integer sequence;

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
