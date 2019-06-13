package com.example.gitcommithistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

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
                .baseUrl("https://api.github.com/repos/mojombo/grit/commits")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        commits.add("Git");
        commits.add("Git Lab");
        commits.add("BitBucket");
        recyclerAdapter.notifyDataSetChanged();
    }
}
