package com.example.opencameramodule.di;

import com.example.opencameramodule.datamanager.MDataManager;
import com.example.opencameramodule.datamanager.MDataManagerImpl;
import com.example.opencameramodule.datamanager.api.APIHelper;
import com.example.opencameramodule.datamanager.api.APIHelperImpl;
import com.example.opencameramodule.datamanager.database.DBHelper;
import com.example.opencameramodule.datamanager.database.DBHelperImpl;
import com.example.opencameramodule.datamanager.sharedpref.SharedPrefHelper;
import com.example.opencameramodule.datamanager.sharedpref.SharedPrefHelperImpl;

import javax.inject.Singleton;

import dagger.Provides;

public class AppModule {
    /**
     * Provides Data Manager instance
     *
     * @param dataManager
     * @return DataManager {@link com.example.opencameramodule.datamanager.MDataManager}
     */
    @Provides
    @Singleton
    MDataManager providesDataManager(MDataManagerImpl dataManager) {
        return dataManager;
    }

    /**
     * Provides ApiHelper instance
     *
     * @param apiHelper
     * @return ApiHelper {@link com.example.opencameramodule.datamanager.api.APIHelper}
     */
    @Provides
    @Singleton
    APIHelper providesApiHelper(APIHelperImpl apiHelper) {
        return apiHelper;
    }

    /**
     * Provides DB Helper instance
     *
     * @param dbHelper
     * @return NLearnDBHelper {@link com.example.opencameramodule.datamanager.database.DBHelper}
     */
    @Provides
    @Singleton
    DBHelper providesDBHelper(DBHelperImpl dbHelper) {
        return dbHelper;
    }

    /**
     * Provides Preference Helper
     *
     * @param preferenceHelper
     * @return Preference Helper {@link com.example.opencameramodule.datamanager.sharedpref.SharedPrefHelper}
     */
    @Provides
    @Singleton
    SharedPrefHelper providesPreferenceHelper(SharedPrefHelperImpl preferenceHelper) {
        return preferenceHelper;
    }
}
