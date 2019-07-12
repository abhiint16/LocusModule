package com.example.opencameramodule.datamanager.database.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.opencameramodule.view.AppValues;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface LocusDao {

    @Query("SELECT * FROM " + AppValues.Constants.SAVED_TABLE)
    Single<List<MappedData>> getListLiveData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertData(MappedData data);

    @Query("DELETE FROM " + AppValues.Constants.SAVED_TABLE + " WHERE " + AppValues.Constants.ID + "=:id ")
    int removeDataById(String id);

    /*@Query("SELECT * FROM " + AppValues.Constants.SAVED_TABLE + " WHERE " + AppValues.Constants.ID + "=:id ")
    Single<String> getDataWithId(String id);*/

}