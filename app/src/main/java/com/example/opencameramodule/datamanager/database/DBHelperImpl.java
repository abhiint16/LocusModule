package com.example.opencameramodule.datamanager.database;


import com.example.opencameramodule.datamanager.database.database.LocusDatabase;
import com.example.opencameramodule.datamanager.database.database.MappedData;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

public class DBHelperImpl implements DBHelper {

    LocusDatabase locusDatabase;

    @Inject
    DBHelperImpl(LocusDatabase locusDatabase) {
        this.locusDatabase = locusDatabase;
    }

    @Override
    public Single<Long> saveDataToDB(Map<String, String> stringMap) {
        MappedData mappedData = new MappedData();
        mappedData.setId(stringMap.keySet().toString());
        mappedData.setValue(stringMap.values().toString());
        return Single.fromCallable(() -> locusDatabase.getLocusDao().insertData(mappedData));
    }

    @Override
    public Single<List<MappedData>> getDataFromDB() {
        return locusDatabase.getLocusDao().getListLiveData();
    }

    @Override
    public Single<String> getDataWithId(String id) {
        return null;/*locusDatabase.getLocusDao().getDataWithId(id)*/
    }

    @Override
    public Single<Integer> removeDataById(String id) {
        return Single.fromCallable(() -> locusDatabase.getLocusDao().removeDataById(id));
    }

    @Override
    public Single<Boolean> clearDatabase() {
        return Single.fromCallable(locusDatabase::clearDatabase);
    }
}
