package com.example.cy.listviewadapter;

/**
 * Created by cy on 21-1-13.
 */

public class Icon {
    private String name;
    private int icon;

    public Icon() {
    }

    public Icon(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
