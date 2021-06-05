package io.github.metheax.domain.view;

import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 18/04/2020
 */
@SelectFrom(fromClause = "FROM TDataTableView o", orderBy = "ORDER BY o.viewName ASC")
public class DataTableView extends BaseView<DataTableView> {
    private static final long serialVersionUID = 2335231132063689758L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.viewName", key = "viewName", where = "AND LOWER(o.viewName) LIKE :viewName")
    private String viewName;
    @Column(name = "o.labelColumnHead", key = "labelColumnHead", where = "AND LOWER(o.labelColumnHead) LIKE :labelColumnHead")
    private String labelColumnHead;
    @Column(name = "o.allowFilter", key = "allowFilter", where = "AND LOWER(o.allowFilter) LIKE :allowFilter")
    private String allowFilter;
    @Column(name = "o.columnKey", key = "columnKey", where = "AND LOWER(o.columnKey) LIKE :columnKey")
    private String columnKey;
    @Column(name = "o.sequence")
    private Integer sequence;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public DataTableView(String id, String viewName, String labelColumnHead, String allowFilter,
                         String columnKey, Integer sequence, String status) {
        this.id = id;
        this.viewName = viewName;
        this.labelColumnHead = labelColumnHead;
        this.allowFilter = allowFilter;
        this.columnKey = columnKey;
        this.sequence = sequence;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getViewName() {
        return viewName;
    }

    public String getLabelColumnHead() {
        return labelColumnHead;
    }

    public String getAllowFilter() {
        return allowFilter;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public Integer getSequence() {
        return sequence;
    }

    public String getStatus() {
        return status;
    }
}
