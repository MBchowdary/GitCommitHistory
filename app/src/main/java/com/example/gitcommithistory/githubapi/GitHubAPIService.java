package com.example.gitcommithistory.githubapi;

import com.example.gitcommithistory.models.GitHubCommitModel;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

// The Aimm of this service is to provide Raw data from Remote
public class GitHubAPIService {

    // Below are two dependencies
    Retrofit retrofit;
    Gson gson;

    public GitHubAPIService(Gson gson, Retrofit retrofit) {
        this.retrofit = retrofit;
        this.gson = gson;
    }

    // A public method to return List of Observables
    public Observable<List<GitHubCommitModel>> getCommitHistory(){
        GitHubAPI gitHubAPI = retrofit.create(GitHubAPI.class);
        Observable<List<GitHubCommitModel>> observableCommitList = gitHubAPI.getRepoFromRemote();
        return observableCommitList;
    }
}
