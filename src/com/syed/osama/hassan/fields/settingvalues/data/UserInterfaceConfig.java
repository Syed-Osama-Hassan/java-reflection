package com.syed.osama.hassan.fields.settingvalues.data;

public class UserInterfaceConfig {
    private String titleColor;
    private String titleText;
    private int titleFontSize;
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

    public int getTitleFontSize() {
        return titleFontSize;
    }

    public void setTitleFontSize(int titleFontSize) {
        this.titleFontSize = titleFontSize;
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
                ", titleFontSize=" + titleFontSize +
                ", footerFontSize=" + footerFontSize +
                '}';
    }
}
