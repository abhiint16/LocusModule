package com.example.opencameramodule.datamanager;

import androidx.lifecycle.LiveData;

import com.example.opencameramodule.datamanager.api.APIHelper;
import com.example.opencameramodule.datamanager.database.DBHelper;
import com.example.opencameramodule.datamanager.database.database.MappedData;
import com.example.opencameramodule.datamanager.localjson.LocalJsonHelper;
import com.example.opencameramodule.datamanager.sharedpref.SharedPrefHelper;
import com.example.opencameramodule.view.model.Parent;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * This class acts as Single Source of truth where all the data goes through.
 */
public class MDataManagerImpl implements MDataManager {
    APIHelper apiHelper;
    SharedPrefHelper sharedPrefHelper;
    DBHelper dbHelper;
    LocalJsonHelper localJsonHelper;

    @Inject
    public MDataManagerImpl(APIHelper apiHelper, SharedPrefHelper sharedPrefHelper, DBHelper dbHelper,
                            LocalJsonHelper localJsonHelper) {
        this.apiHelper = apiHelper;
        this.sharedPrefHelper = sharedPrefHelper;
        this.dbHelper = dbHelper;
        this.localJsonHelper = localJsonHelper;
    }

    @Override
    public Observable<List<Parent>> getData() {
        return localJsonHelper.getData();
    }

    @Override
    public Single<Long> saveDataToDB(Map<String, String> stringMap) {
        return dbHelper.saveDataToDB(stringMap);
    }

    @Override
    public Single<List<MappedData>> getDataFromDB() {
        return dbHelper.getDataFromDB();
    }

    @Override
    public Single<String> getDataWithId(String id) {
        return dbHelper.getDataWithId(id);
    }

    @Override
    public Single<Boolean> clearDatabase() {
        return dbHelper.clearDatabase();
    }

    @Override
    public Single<Integer> removeDataById(String id) {
        return dbHelper.removeDataById(id);
    }
}
