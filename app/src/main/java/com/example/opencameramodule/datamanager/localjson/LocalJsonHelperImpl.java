package com.example.opencameramodule.datamanager.localjson;

import com.example.opencameramodule.view.model.Parent;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class LocalJsonHelperImpl implements LocalJsonHelper {

    JsonService jsonService;

    @Inject
    LocalJsonHelperImpl(JsonService jsonService) {
        this.jsonService = jsonService;
    }


    @Override
    public Observable<Parent> getData() {
        return jsonService.getData();
    }
}
