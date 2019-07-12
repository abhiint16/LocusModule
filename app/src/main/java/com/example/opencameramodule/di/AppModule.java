package com.example.opencameramodule.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.opencameramodule.datamanager.MDataManager;
import com.example.opencameramodule.datamanager.MDataManagerImpl;
import com.example.opencameramodule.datamanager.api.APIHelper;
import com.example.opencameramodule.datamanager.api.APIHelperImpl;
import com.example.opencameramodule.datamanager.database.DBHelper;
import com.example.opencameramodule.datamanager.database.DBHelperImpl;
import com.example.opencameramodule.datamanager.database.database.LocusDatabase;
import com.example.opencameramodule.datamanager.localjson.JsonService;
import com.example.opencameramodule.datamanager.localjson.JsonToString;
import com.example.opencameramodule.datamanager.localjson.LocalJsonHelper;
import com.example.opencameramodule.datamanager.localjson.LocalJsonHelperImpl;
import com.example.opencameramodule.datamanager.sharedpref.SharedPrefHelper;
import com.example.opencameramodule.datamanager.sharedpref.SharedPrefHelperImpl;
import com.example.opencameramodule.view.AppValues;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
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

    @Provides
    @Singleton
    LocalJsonHelper providesLocalJson(LocalJsonHelperImpl localJsonHelper) {
        return localJsonHelper;
    }

    @Provides
    @Singleton
    JsonService providesJsonService(JsonToString jsonToString) {
        return new JsonService(jsonToString);
    }

    @Provides
    @Singleton
    JsonToString providesJsonToString(Context context) {
        return new JsonToString(context);
    }

    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    LocusDatabase providesDataBase(Context context, String databaseName) {
        return Room.databaseBuilder(context, LocusDatabase.class, databaseName).fallbackToDestructiveMigration().build();
    }

    @Provides
    Integer providesDatabaseVersion() {
        return AppValues.Constants.DB_VERSION;
    }

    @Provides
    String providesDatabaseName() {
        return AppValues.Constants.DB_NAME;
    }

}
