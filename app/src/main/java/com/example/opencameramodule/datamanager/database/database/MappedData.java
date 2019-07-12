package com.example.opencameramodule.datamanager.database.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.opencameramodule.view.AppValues;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = AppValues.Constants.SAVED_TABLE)
public class MappedData {

    /*@PrimaryKey(autoGenerate = true)
    private int uid;*/
    @PrimaryKey
    @ColumnInfo(name = AppValues.Constants.ID)
    @Expose
    @SerializedName("id")
    private @NonNull
    String id;

    @ColumnInfo(name = AppValues.Constants.VALUE)
    @Expose
    @SerializedName("value")
    private String value;

    /*public int getUid() {
        return uid;
    }*/

    @NonNull
    public String getId() {
        return id;
    }

    /*public void setUid(int uid) {
        this.uid = uid;
    }*/

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
