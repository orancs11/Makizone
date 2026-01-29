package com.Oran.Makizone.DTO;


/*
    Response of garden (Spring part)
    It is exactly the same as GardenRequest however due to slight changes in the future
    We may need a separate class instead
    This is why this class is created

 */

import com.Oran.Makizone.Utilities.Enums.GardenTheme;

import java.util.Map;

public class GardenResponse {
    private GardenTheme theme;
    private Map<String, Object> layoutData;

    public GardenResponse(GardenTheme theme, Map<String, Object> layoutData){
        this.theme = theme;
        this.layoutData = layoutData;
    }

    public GardenTheme getTheme() {
        return theme;
    }

    public void setTheme(GardenTheme theme) {
        this.theme = theme;
    }

    public Map<String, Object> getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(Map<String, Object> layoutData) {
        this.layoutData = layoutData;
    }
}
