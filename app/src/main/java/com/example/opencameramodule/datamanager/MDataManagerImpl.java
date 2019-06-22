package com.example.opencameramodule.datamanager;

import com.example.opencameramodule.datamanager.api.APIHelper;
import com.example.opencameramodule.datamanager.database.DBHelper;
import com.example.opencameramodule.datamanager.localjson.LocalJsonHelper;
import com.example.opencameramodule.datamanager.sharedpref.SharedPrefHelper;
import com.example.opencameramodule.view.model.Parent;

import javax.inject.Inject;

import io.reactivex.Observable;

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
    public Observable<Parent> getData() {
        return localJsonHelper.getData();
    }
}
