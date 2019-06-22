package com.example.opencameramodule.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opencameramodule.R;
import com.example.opencameramodule.databinding.ActivityMainBinding;
import com.example.opencameramodule.di.ViewModelProviderFactory;
import com.example.opencameramodule.view.adapter.MainRecyclerAdapter;
import com.example.opencameramodule.view.di.MainViewModelFactory;
import com.example.opencameramodule.view.viewmodel.MainActivityViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    ViewModelProviderFactory mainViewModelFactory;
    @Inject
    MainActivityViewModel mainViewModel;
    ActivityMainBinding binding;
    MainRecyclerAdapter mainRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory)
                .get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainRecyclerAdapter = new MainRecyclerAdapter();
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.mainRecycler.setLayoutManager(linearLayoutManager);
        binding.mainRecycler.setAdapter(mainRecyclerAdapter);

        mainViewModel.getData();

    }
}
