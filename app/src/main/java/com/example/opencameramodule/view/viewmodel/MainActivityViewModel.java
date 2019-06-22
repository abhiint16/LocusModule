package com.example.opencameramodule.view.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.opencameramodule.datamanager.MDataManager;
import com.example.opencameramodule.view.model.Parent;

import io.reactivex.functions.Consumer;

public class MainActivityViewModel extends ViewModel {
    MDataManager mDataManager;

    public MainActivityViewModel() {
        //do nothing
    }

    public MainActivityViewModel(MDataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public void getData() {
        mDataManager.getData().subscribe(new Consumer<Parent>() {
            @Override
            public void accept(Parent parent) throws Exception {
                Log.e("parent data", "" + parent);
            }
        });
    }
}
