package com.example.opencameramodule.datamanager.localjson;


import com.example.opencameramodule.view.model.Data;
import com.example.opencameramodule.view.model.Parent;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by abhishek on 09-06-2018.
 */

public class JsonService {
    JsonToString jsonToString;

    public JsonService(JsonToString jsonToString) {
        this.jsonToString = jsonToString;
    }

    public Observable<List<Parent>> getData() {
        String dataString = jsonToString.loadJSONFromAsset();
        List<Parent> data = new ArrayList<>();

        Gson gson = new Gson();

        /*List<Parent> response = (List<Parent>) gson.fromJson(dataString, Parent.class);
        return Observable.fromIterable((Iterable<? extends Parent>) response);*/

        try {
            JSONArray jsonArray = new JSONArray(dataString);
            for (int i = 0; i < jsonArray.length(); i++) {
                Parent parent = new Parent();

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String type = jsonObject.getString("type");
                String id = jsonObject.getString("id");
                String title = jsonObject.getString("title");
                JSONObject dataMap = jsonObject.getJSONObject("dataMap");

                parent.setType(type);
                parent.setId(id);
                parent.setTitle(title);

                if (dataMap.has("options")) {
                    JSONArray option = dataMap.getJSONArray("options");
                    for (int j = 0; j < option.length(); j++) {
                        parent.getDataMap().getOptions().add((String) option.get(j));
                    }
                }
                data.add(parent);

            }
            return Observable.just(data);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
