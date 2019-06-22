package com.example.opencameramodule.datamanager;

import com.example.opencameramodule.datamanager.api.APIHelper;
import com.example.opencameramodule.datamanager.database.DBHelper;
import com.example.opencameramodule.datamanager.sharedpref.SharedPrefHelper;

public interface MDataManager extends SharedPrefHelper, DBHelper, APIHelper {
}
