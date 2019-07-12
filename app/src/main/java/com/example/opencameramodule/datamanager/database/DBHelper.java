package com.example.opencameramodule.datamanager.database;

import androidx.lifecycle.LiveData;

import com.example.opencameramodule.datamanager.database.database.MappedData;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import io.reactivex.Single;

public interface DBHelper {

    Single<Long> saveDataToDB(Map<String,String> stringMap);

    Single<List<MappedData>> getDataFromDB();

    Single<String> getDataWithId(String id);

    Single<Boolean> clearDatabase();

    Single<Integer> removeDataById(String id);
}
