package com.example.opencameramodule.datamanager.localjson;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;

/**
 * Created by abhishek on 09-06-2018.
 */

public class JsonToString {
    Context context;
    public JsonToString(Context context) {
        this.context=context;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("local.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
