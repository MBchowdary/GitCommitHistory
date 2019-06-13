package com.example.gitcommithistory.home;


import com.example.gitcommithistory.di.ActivityScope;
import com.example.gitcommithistory.root.AppComponent;

import dagger.Component;

@Component (modules = {RecyclerAdapterModule.class}, dependencies = {AppComponent.class})
@ActivityScope
public interface HomeComponent {
    void inject(MainActivity target);
}
