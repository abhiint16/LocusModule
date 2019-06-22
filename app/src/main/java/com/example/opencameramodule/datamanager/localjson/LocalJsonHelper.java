package com.example.opencameramodule.datamanager.localjson;

import com.example.opencameramodule.view.model.Parent;

import io.reactivex.Observable;


public interface LocalJsonHelper {
    Observable<Parent> getData();
}
