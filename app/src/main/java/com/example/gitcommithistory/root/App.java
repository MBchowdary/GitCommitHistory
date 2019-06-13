package com.example.gitcommithistory.root;

import android.app.Application;

import com.example.gitcommithistory.githubapi.GitHubAPIService;


public class App extends Application {
    private static final String TAG = "App";

    GitHubAPIService gitHubAPIService;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule())
                .build();
    }

    public GitHubAPIService getGitHubAPIService() {
       return appComponent.getGitHubAPIService();
    }
}
