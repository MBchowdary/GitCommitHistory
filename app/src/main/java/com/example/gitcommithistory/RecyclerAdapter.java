package com.example.gitcommithistory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitcommithistory.models.DataClass;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RViewHolder> {

    //data set
    // For testing purpose we will assume data is of type sting
    List<DataClass> dataset;

    public RecyclerAdapter(List<DataClass> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // We need to return a view-holder object so we will create the view first
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.git_commit_list_item,parent,false);
        return new RViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        // Binding Text view with data sets
        holder.author.setText(dataset.get(position).getAuthor());
        holder.commitmsg.setText(dataset.get(position).getMessage());
        holder.commitid.setText(dataset.get(position).getCommitid());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    //View holder -> Implementation
    public static class RViewHolder extends RecyclerView.ViewHolder{

        public TextView author;
        public TextView commitmsg;
        public TextView commitid;

        public RViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            commitmsg = itemView.findViewById(R.id.commit_msg);
            commitid = itemView.findViewById(R.id.commit_id);
        }
    }
}
