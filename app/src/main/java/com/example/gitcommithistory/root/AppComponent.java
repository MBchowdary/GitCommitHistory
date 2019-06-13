package com.example.gitcommithistory.root;

import com.example.gitcommithistory.di.AppScope;
import com.example.gitcommithistory.githubapi.GitHubAPIService;

import dagger.Component;

@Component (modules = {AppModule.class})
@AppScope
public interface AppComponent {
    GitHubAPIService getGitHubAPIService();
}
