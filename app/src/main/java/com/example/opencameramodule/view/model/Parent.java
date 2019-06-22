package com.example.opencameramodule.view.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent implements Serializable {

    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("dataMap")
    private DataMap dataMap;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

}