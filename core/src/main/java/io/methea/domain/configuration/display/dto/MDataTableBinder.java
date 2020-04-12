package io.methea.domain.configuration.display.dto;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 18/01/2020
 */
public class MDataTableBinder {
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
