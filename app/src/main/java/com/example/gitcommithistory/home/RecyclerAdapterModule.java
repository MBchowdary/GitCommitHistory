package com.example.gitcommithistory.home;

import com.example.gitcommithistory.RecyclerAdapter;
import com.example.gitcommithistory.di.ActivityScope;
import com.example.gitcommithistory.models.DataClass;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class RecyclerAdapterModule {

    List<DataClass> dataset;

    public RecyclerAdapterModule(List<DataClass> dataset) {
        this.dataset = dataset;
    }

    @Provides
    @ActivityScope
    RecyclerAdapter providesRecyclerAdapter(){
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(dataset);
        return recyclerAdapter;
    }
}

