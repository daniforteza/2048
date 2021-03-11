package com.example.a2048;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2048.scores.ScoreDisplay;

public class EditActivity extends AppCompatActivity {
    private ScoreDB scoreDB;
    private int idToModify;
    private EditText editScore, editUser, editDuration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editScore = findViewById(R.id.editScoreCamp);
        editUser = findViewById(R.id.editNameCamp);
        editDuration = findViewById(R.id.editDurationCamp);

        scoreDB = ScoreDB.getInstance(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        idToModify = extras.getInt("SCOREID");
        Score score = scoreDB.getById(idToModify);
        System.out.println(score);

        editScore.setText(String.valueOf(score.getScoreNum()));
        editUser.setText(score.getUsername());
        editDuration.setText(String.valueOf(score.getGamelength()));

    }

    public void onButtonClick(View view){
        switch(view.getId()){
            case R.id.saveButton:
                scoreDB.updateScore(idToModify, Integer.parseInt(editScore.getText().toString()), editUser.getText().toString(), Float.parseFloat(editDuration.getText().toString()));
                Intent intent = new Intent(this, ScoreDisplay.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
