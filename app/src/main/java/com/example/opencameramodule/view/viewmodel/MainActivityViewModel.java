package com.example.opencameramodule.view.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.opencameramodule.datamanager.MDataManager;

public class MainActivityViewModel extends ViewModel {
    MDataManager mDataManager;

    public MainActivityViewModel() {
        //do nothing
    }

    public MainActivityViewModel(MDataManager mDataManager) {
        this.mDataManager = mDataManager;
    }
}
