package com.example.gitcommithistory.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitcommithistory.R;
import com.example.gitcommithistory.RecyclerAdapter;
import com.example.gitcommithistory.githubapi.GitHubAPIService;
import com.example.gitcommithistory.models.DataClass;
import com.example.gitcommithistory.models.GitHubCommitModel;
import com.example.gitcommithistory.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Inject
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

        // As API calls are used application wide moved Network Module to App
        App app = (App)getApplication();
        GitHubAPIService gitHubAPIService = app.getGitHubAPIService();

        HomeComponent homeComponent = DaggerHomeComponent
        .builder()
        .recyclerAdapterModule(new RecyclerAdapterModule(commits))
        .appComponent((((App)getApplication()).getAppComponent()))
        .build();

        homeComponent.inject(this);

        recyclerView.setAdapter(recyclerAdapter);



        // RXjava
                gitHubAPIService.getCommitHistory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubCommitModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<GitHubCommitModel> gitHubCommitModels) {
                        //for(GitHubCommitModel commit: observableCommitList)
                        for (GitHubCommitModel gitcommits : gitHubCommitModels) {
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
