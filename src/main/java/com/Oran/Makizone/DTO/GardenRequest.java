package com.Oran.Makizone.DTO;

import com.Oran.Makizone.Utilities.Enums.GardenTheme;

import java.util.Map;

public class GardenRequest {
    private GardenTheme theme;
    private int rowIndex;
    private int colIndex;

    private Map<String, Object> actionItem;

    public GardenRequest(GardenTheme theme, int rowIndex, int colIndex, Map<String, Object> actionItem){
        this.theme = theme;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.actionItem = actionItem;
    }

    public GardenTheme getTheme() {
        return theme;
    }

    public void setTheme(GardenTheme theme) {
        this.theme = theme;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public Map<String, Object> getActionItem() {
        return actionItem;
    }

    public void setActionItem(Map<String, Object> actionItem) {
        this.actionItem = actionItem;
    }
}
