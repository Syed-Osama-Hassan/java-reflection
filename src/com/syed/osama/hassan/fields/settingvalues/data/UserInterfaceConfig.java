package com.syed.osama.hassan.fields.settingvalues.data;

import java.util.Arrays;

public class UserInterfaceConfig {
    private String titleColor;
    private String titleText;
    private int[] titleFontSizes;
    private int footerFontSize;


    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int[] getTitleFontSizes() {
        return titleFontSizes;
    }

    public void setTitleFontSizes(int[] titleFontSizes) {
        this.titleFontSizes = titleFontSizes;
    }

    public int getFooterFontSize() {
        return footerFontSize;
    }

    public void setFooterFontSize(int footerFontSize) {
        this.footerFontSize = footerFontSize;
    }

    @Override
    public String toString() {
        return "UserInterfaceConfig{" +
                "titleColor='" + titleColor + '\'' +
                ", titleText='" + titleText + '\'' +
                ", titleFontSizes=" + Arrays.toString(titleFontSizes) +
                ", footerFontSize=" + footerFontSize +
                '}';
    }
}
