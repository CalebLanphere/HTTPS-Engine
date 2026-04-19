/**
 * EOCPValues class
 *
 * All values that are associated with the Expirī Object Communication Protocol Version 1 (EOCPv1)
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.GCOP;

import java.util.ArrayList;

public class EOCPValues {
    private String htmlType;
    private String htmlContent = null;
    private String htmlClass = null;
    private String htmlId = null;
    private String htmlStyle = null;
    private ArrayList<EOCPElement> htmlChildren = null;

    public String getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getHtmlClass() {
        return htmlClass;
    }

    public void setHtmlClass(String htmlClass) {
        this.htmlClass = htmlClass;
    }

    public String getHtmlId() {
        return htmlId;
    }

    public void setHtmlId(String htmlId) {
        this.htmlId = htmlId;
    }

    public String getHtmlStyle() {
        return htmlStyle;
    }

    public void setHtmlStyle(String htmlStyle) {
        this.htmlStyle = htmlStyle;
    }

    public ArrayList<EOCPElement> getHtmlChildren() {
        return htmlChildren;
    }

    public void setHtmlChildren(ArrayList<EOCPElement> htmlChildren) {
        this.htmlChildren = htmlChildren;
    }
}
