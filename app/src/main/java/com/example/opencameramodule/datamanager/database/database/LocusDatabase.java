package com.example.opencameramodule.datamanager.database.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Transaction;

import javax.inject.Singleton;

@Database(entities = {MappedData.class}, version = 2, exportSchema = false)
@Singleton
public abstract class LocusDatabase extends RoomDatabase {

    //Note : Add | Define All the tables here like

    public abstract LocusDao getLocusDao();

    @Transaction
    public boolean clearDatabase() {

        LocusDatabase database = this;

        try {
            database.clearAllTables();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            database.endTransaction();
        }
    }

}