package com.example.gitcommithistory.root;

import com.example.gitcommithistory.githubapi.GitHubAPIService;

import dagger.Component;

@Component (modules = {AppModule.class})
public interface AppComponent {
    GitHubAPIService getGitHubAPIService();
}
