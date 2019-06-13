package com.example.gitcommithistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.gitcommithistory.githubapi.GitHubAPI;
import com.example.gitcommithistory.models.DataClass;
import com.example.gitcommithistory.models.GitHubCommitModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GIT";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter recyclerAdapter;
    List<String> commits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.commits_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        commits = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(commits);
        recyclerView.setAdapter(recyclerAdapter);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GitHubAPI gitHubAPI = retrofit.create(GitHubAPI.class);
        Observable<List<GitHubCommitModel>> observableCommitList = gitHubAPI.getRepoFromRemote();

        // RXjava
        observableCommitList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubCommitModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<GitHubCommitModel> gitHubCommitModels) {
                        for (GitHubCommitModel gitcommits : gitHubCommitModels) {

                            Log.d(TAG, "onNext: "+ gitcommits.getSha());
                            Log.d(TAG, "onNext: "+ gitcommits.getCommit().getMessage());
                            Log.d(TAG, "onNext: "+ gitcommits.getCommit().getCommitter().getName());

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



        commits.add("Git");
        commits.add("Git Lab");
        commits.add("BitBucket");
        recyclerAdapter.notifyDataSetChanged();
    }
}
