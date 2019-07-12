package com.example.opencameramodule.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opencameramodule.R;
import com.example.opencameramodule.databinding.ActivityMainBinding;
import com.example.opencameramodule.datamanager.database.database.MappedData;
import com.example.opencameramodule.view.adapter.MainRecyclerAdapter;
import com.example.opencameramodule.view.model.Parent;
import com.example.opencameramodule.view.viewmodel.MainActivityViewModel;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory mainViewModelFactory;
    MainActivityViewModel mainViewModel;
    ActivityMainBinding binding;
    MainRecyclerAdapter mainRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
        initViewModel();
        initDataBinding();
        initRecyclerView();
        initObersers();
        initBtnClick();
        mainViewModel.getData();
    }

    private void initBtnClick() {
        binding.toolbarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.getDataFromDB();
            }
        });
    }

    private void initObersers() {
        mainViewModel.getFinalData().observe(this, parents -> mainRecyclerAdapter.setData(parents));

        mainViewModel.getSavedDataStatus().observe(this, aLong -> {
            binding.toolbarBtn.setEnabled(true);
            binding.toolbarBtn.setAlpha(1);
        });
        mainViewModel.getMappedDataList().observe(this, mappedData ->
                Log.e("finalDataToBeLogged", mappedData));
    }

    private void initRecyclerView() {
        mainRecyclerAdapter = new MainRecyclerAdapter(this);
        mainRecyclerAdapter.setViewModel(mainViewModel);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.mainRecycler.setLayoutManager(linearLayoutManager);
        binding.mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    private void initViewModel() {
        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory)
                .get(MainActivityViewModel.class);
    }

    private void initDagger() {
        AndroidInjection.inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppValues.Constants.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mainRecyclerAdapter.setImage(imageBitmap, AppValues.Constants.ITEM_POS);
        }
    }

    @Override
    protected void onStop() {
        //mainViewModel.clearDB();
        super.onStop();
    }
}
