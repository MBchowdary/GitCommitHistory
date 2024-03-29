package com.example.gitcommithistory.root;

import com.example.gitcommithistory.di.AppScope;
import com.example.gitcommithistory.githubapi.GitHubAPIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static final String BaseURL = "https://api.github.com";
    @Provides
    @AppScope
    Gson providesGson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson;
    }
    @Provides
    @AppScope
    Retrofit providesRetrofit(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
    @Provides
    @AppScope
    GitHubAPIService providesGitHubAPIService(Gson gson, Retrofit retrofit) {
        GitHubAPIService gitHubAPIService = new GitHubAPIService(gson, retrofit);
        return gitHubAPIService;
    }
}
