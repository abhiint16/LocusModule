package com.example.opencameramodule.datamanager.localjson;

import com.example.opencameramodule.view.model.Parent;

import java.util.List;

import io.reactivex.Observable;


public interface LocalJsonHelper {
    Observable<List<Parent>> getData();
}
