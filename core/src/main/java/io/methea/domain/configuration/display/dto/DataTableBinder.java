package io.methea.domain.configuration.display.dto;

import io.methea.domain.basebinder.BaseBinder;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 18/01/2020
 */
public class DataTableBinder extends BaseBinder<DataTableBinder> {
    private static final long serialVersionUID = 913896882019600220L;
    private List<String> labelColumnHead;
    private List<String> columnKey;

    public List<String> getLabelColumnHead() {
        return labelColumnHead;
    }

    public void setLabelColumnHead(List<String> labelColumnHead) {
        this.labelColumnHead = labelColumnHead;
    }

    public List<String> getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(List<String> columnKey) {
        this.columnKey = columnKey;
    }
}
