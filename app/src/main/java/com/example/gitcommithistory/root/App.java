package com.example.gitcommithistory.root;

import android.app.Application;

import com.example.gitcommithistory.githubapi.GitHubAPIService;
import com.google.gson.Gson;

import retrofit2.Retrofit;


public class App extends Application {
    private static final String TAG = "App";

    GitHubAPIService gitHubAPIService;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public GitHubAPIService getGitHubAPIService(Gson gson, Retrofit retrofit) {
        gitHubAPIService =  getGitHubAPIService(gson, retrofit);
        return gitHubAPIService;
    }
}
