package com.example.gitcommithistory.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.gitcommithistory.R;
import com.example.gitcommithistory.RecyclerAdapter;
import com.example.gitcommithistory.githubapi.GitHubAPI;
import com.example.gitcommithistory.githubapi.GitHubAPIService;
import com.example.gitcommithistory.models.DataClass;
import com.example.gitcommithistory.models.GitHubCommitModel;
import com.example.gitcommithistory.root.App;
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
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GIT";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter recyclerAdapter;
    List<DataClass> commits;

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

        // As API calls are used application wide moved Network Module to App
        App app = (App)getApplication();
        GitHubAPIService gitHubAPIService = app.getGitHubAPIService();

        /*GitHubAPI gitHubAPI = retrofit.create(GitHubAPI.class);
        Observable<List<GitHubCommitModel>> observableCommitList = gitHubAPI.getRepoFromRemote();*/

        // RXjava
        gitHubAPIService.getCommitHistory().subscribeOn(Schedulers.io())
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
                            DataClass dataClass = new DataClass();
                            dataClass.setAuthor(gitcommits.getCommit().getCommitter().getName());
                            dataClass.setCommitid(gitcommits.getSha());
                            dataClass.setMessage(gitcommits.getCommit().getMessage());
                            commits.add(dataClass);
                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
