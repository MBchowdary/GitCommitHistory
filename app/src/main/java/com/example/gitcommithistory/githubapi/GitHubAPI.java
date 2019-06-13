package com.example.gitcommithistory.githubapi;

import com.example.gitcommithistory.models.GitHubCommitModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GitHubAPI {
    @GET("/repos/mojombo/grit/commits")
    Observable<List<GitHubCommitModel>> getRepoFromRemote();
}
