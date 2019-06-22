package com.example.opencameramodule.datamanager.localjson;


import com.example.opencameramodule.view.model.Data;
import com.example.opencameramodule.view.model.Parent;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;


/**
 * Created by abhishek on 09-06-2018.
 */

public class JsonService {
    JsonToString jsonToString;

    public JsonService(JsonToString jsonToString) {
        this.jsonToString = jsonToString;
    }

    public Observable<Parent> getData() {
        String dataString = jsonToString.loadJSONFromAsset();
        Parent data = new Parent();

        Gson gson = new Gson();

        Parent response = gson.fromJson(dataString, Parent.class);
        return Observable.fromIterable((Iterable<? extends Parent>) response);

        /*try {
            JSONArray jsonArray = new JSONArray(dataString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
            }

            JSONObject obj = new JSONObject(dataString);
            JSONArray jArray = obj.getJSONArray("Contact");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo_inside = jArray.getJSONObject(i);
                String firstname = jo_inside.getString("firstname");
                String lastname = jo_inside.getString("lastname");
                Long phoneno = jo_inside.getLong("phoneno");

                data.firstname.add(firstname);
                data.lastname.add(lastname);
                data.phone.add(phoneno);
            }
            return Observable.just(data);
        } catch (JSONException e) {
            e.printStackTrace();
            return Observable.fromIterable((Iterable<? extends Data>) data);
        }*/
    }
}
