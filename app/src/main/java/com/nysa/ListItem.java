package com.nysa;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListItem {
    private String title;
    private String description;
    private JSONArray toArt;

    public ListItem(String title, String description, JSONArray toArt) {
        this.title = title;
        this.description = description;
        this.toArt = toArt;
    }
    public String getTitle() {
        return title;
    }
    public String getDesc() {
        return description;
    }
    public JSONArray getArt() {
        return toArt;
    }
}
