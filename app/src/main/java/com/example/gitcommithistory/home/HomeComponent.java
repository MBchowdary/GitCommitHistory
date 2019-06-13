package com.example.gitcommithistory.home;

import com.example.gitcommithistory.RecyclerAdapter;
import com.example.gitcommithistory.root.AppComponent;

import dagger.Component;

@Component (modules = {RecyclerAdapterModule.class}, dependencies = {AppComponent.class})
public interface HomeComponent {
    // As This module is closely coupled to Main Activity we can inject this
    void inject(MainActivity target);
}
