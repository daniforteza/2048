package com.example.a2048.scores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2048.R;
import com.example.a2048.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreCardviewAdapter extends RecyclerView.Adapter<ScoreCardviewHolder> {

    public List<Score> scoreArrayList;
    public ItemClickListener listener;

    public ScoreCardviewAdapter(List<Score> scoreList){
        this.scoreArrayList = scoreList;
    }
    @NonNull
    @Override
    public ScoreCardviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_final,null,false);
        return new ScoreCardviewHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull ScoreCardviewHolder holder, int position) {
        holder.idcamp.setText(String.valueOf(scoreArrayList.get(position).getId()));
        holder.scorecamp.setText(String.valueOf(scoreArrayList.get(position).getScoreNum()));
        holder.usernamecamp.setText(scoreArrayList.get(position).getUsername());
        holder.timecamp.setText(String.valueOf(scoreArrayList.get(position).getGamelength()));
    }

    @Override
    public int getItemCount() {
        return scoreArrayList.size();
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }
}
