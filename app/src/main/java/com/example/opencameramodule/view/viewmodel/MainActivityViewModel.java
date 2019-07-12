package com.example.opencameramodule.view.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.opencameramodule.datamanager.MDataManager;
import com.example.opencameramodule.datamanager.database.database.MappedData;
import com.example.opencameramodule.view.PhotoClickListener;
import com.example.opencameramodule.view.model.DataMap;
import com.example.opencameramodule.view.model.Parent;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel implements PhotoClickListener {
    MDataManager mDataManager;
    MutableLiveData<List<Parent>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> photoClickLiveData = new MutableLiveData<>();
    MutableLiveData<Long> saveDataLiveData = new MutableLiveData<>();
    MutableLiveData<String> mappedDataListLiveData = new MutableLiveData<>();
    MutableLiveData<String> dataByIdLiveData = new MutableLiveData<>();

    public MainActivityViewModel() {
        //do nothing
    }

    public MainActivityViewModel(MDataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public void getData() {
        mDataManager.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<Parent>, ObservableSource<DataMap>>() {
                    @Override
                    public ObservableSource<DataMap> apply(List<Parent> parents) throws Exception {
                        return Observable.just(parents.get(0).getDataMap());
                    }
                })
                .subscribe(new Consumer<DataMap>() {

                    @Override
                    public void accept(DataMap o) throws Exception {

                    }
                });
                /*.map(new Function<List<Parent>, DataMap>() {
                    @Override
                    public DataMap apply(List<Parent> parents) throws Exception {
                        return parents.get(0).getDataMap();
                    }
                })*/
                /*.subscribe(new Consumer<List<Parent>>() {
                    @Override
                    public void accept(List<Parent> parents) throws Exception {
                        Log.e("parent data", "" + parents);
                        listMutableLiveData.setValue(parents);
                    }
                });*/
    }

    public void getDataFromDB() {
        mDataManager.getDataFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mappedData -> {
                    Gson gson = new Gson();
                    mappedDataListLiveData.setValue(gson.toJson(mappedData));
                });
    }

    public void getDataWithId(String id) {
        mDataManager.getDataWithId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    dataByIdLiveData.setValue(s);
                });
    }

    public void saveDataToDB(Map<String, String> stringMap) {
        mDataManager.saveDataToDB(stringMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> saveDataLiveData.setValue(aLong));
    }

    public void removeDataById(String id) {
        mDataManager.removeDataById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Integer aLong) -> {
                    //do nothing
                });
    }

    public void clearDB() {
        mDataManager.clearDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    //Do nothing
                });
    }

    public LiveData<List<Parent>> getFinalData() {
        return listMutableLiveData;
    }

    public LiveData<Long> getSavedDataStatus() {
        return saveDataLiveData;
    }

    public LiveData<String> getMappedDataList() {
        return mappedDataListLiveData;
    }

    public LiveData<Boolean> getPhotoClickToggle() {
        return photoClickLiveData;
    }

    @Override
    public void photoClick(Boolean isEnabled) {
        if (isEnabled) {
            photoClickLiveData.setValue(true);
        } else {
            photoClickLiveData.setValue(false);
        }

    }

    @Override
    public void removePhoto() {

    }
}
