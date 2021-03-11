package com.example.a2048.scores;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2048.EditActivity;
import com.example.a2048.R;
import com.example.a2048.Score;
import com.example.a2048.ScoreDB;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ScoreDisplay extends AppCompatActivity {
    private ScoreCardviewAdapter cardviewAdapter;
    private RecyclerView recyclerView;
    private List<Score> scoresList;
    private ScoreDB scoreDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_scores);
        scoreDB = ScoreDB.getInstance(getApplicationContext());
        scoresList = new ArrayList<>();
        scoresList = scoreDB.getScores();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this.recyclerView);
        cardviewAdapter = new ScoreCardviewAdapter(scoresList);
        recyclerView.setAdapter(cardviewAdapter);
        cardviewAdapter.setItemClickListener(new ItemClickListener(){
            @Override
            public void editClick(int position) {
                Intent intent = new Intent(ScoreDisplay.this, EditActivity.class);
                intent.putExtra("SCOREID", scoresList.get(position).getId());
                startActivity(intent);
                finish();
            }
        });
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) { //Type of movement, DIRECTION
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            scoreDB.deleteScoreById(scoresList.get(viewHolder.getAdapterPosition()).getId());
            scoresList.remove(viewHolder.getAdapterPosition());
            cardviewAdapter.notifyDataSetChanged();
        }
    };
}
