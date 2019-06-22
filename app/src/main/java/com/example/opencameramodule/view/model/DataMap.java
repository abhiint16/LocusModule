package com.example.opencameramodule.view.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataMap implements Serializable {

    @SerializedName("options")
    private List<String> options = null;

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

}