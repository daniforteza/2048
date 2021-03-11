package com.example.a2048.Game;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2048.R;
import com.example.a2048.Score;
import com.example.a2048.ScoreDB;


public class GameActivity extends AppCompatActivity implements View.OnTouchListener {

    private MatrixView matrixView;
    private SwipeListener swipeListener;
    private OptionsListener optionsListener;
    private Boolean gameOver;
    private ScoreDB scoreDB;
    private Chronometer chronometer;
    private TextView textViewGameTitle, scoreView, bestscoreView, usernameView;
    private String username;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        scoreDB = ScoreDB.getInstance(getApplicationContext());
        gameOver = false;
        textViewGameTitle = findViewById(R.id.main_text);
        String first = "Join the number to get the ";
        String next = "<font color=\"#57534F\">2048!</font>";
        textViewGameTitle.setText(Html.fromHtml(first + next));
        scoreView = findViewById(R.id.scoregame);
        usernameView = findViewById(R.id.usernamegame);
        //TODO: Cargar el username
        Bundle extras = this.getIntent().getExtras();
        username = extras.getString("USERNAME");
        usernameView.setText(String.valueOf("Username: "+username));
        //TODO: Obtener el topScore de la base de datos y fijarlo en bestScoreView
        bestscoreView = findViewById(R.id.bestscoregame);
        chronometer = findViewById(R.id.chronometer);
        matrixView = findViewById(R.id.matrix_view);
        matrixView.setMoveListener(new MoveListener() {
            @Override
            public void onMove() {
                if (matrixView.isGameOver()) {
                    Score finalScore = new Score();
                    finalScore.setGamelength((float) (SystemClock.elapsedRealtime()- chronometer.getBase()));
                    finalScore.setScoreNum(matrixView.getScoreValue());
                    finalScore.setUsername(username);
                    System.out.println(scoreDB.addScoreDB(finalScore));
                        //TODO: Hacer un insert en la base de datos de Score, User y Duration
                        //Maybe usar para duration -> Float duration = SystemClock.elapsedRealTime() - chronometer.getBase();
                    displayGameOverDialog();
                }
                setScore();
            }
        });



        optionsListener = new OptionsListener() {
            @Override
            public void resetGame() {
                matrixView.reset();
                matrixView.setGameOver(false);
                setScore();
                chronometer.setBase(SystemClock.elapsedRealtime());
            }

            @Override
            public void undoMovement() {
                matrixView.restoreMatrix();
                setScore();
            }
        };
        matrixView.setOptionsListener(optionsListener);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        Button buttonNewGame = findViewById(R.id.main_button_new_game);


        LinearLayout linearLayout = findViewById(R.id.main_linearLayout);
        linearLayout.setOnTouchListener(this);
        swipeListener = new SwipeListener(this, new SwipeDirection() {
            @Override
            public void onSwipeLeft() {
                matrixView.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                matrixView.onSwipeRight();
            }

            @Override
            public void onSwipeUp() {
                matrixView.onSwipeUp();
            }

            @Override
            public void onSwipeDown() {
                matrixView.onSwipeDown();
            }
        });



    }

    private void setScore() {
        scoreView.setText("Score: "+String.valueOf(matrixView.getScoreValue()));
    }


    void displayGameOverDialog() {
        final Dialog dialog = new Dialog(GameActivity.this);
        String gameOverText = "You lost, want another try? ";
        String cancelButton = "Cancel";
        String newGameButton = "New Game";

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_dialog);
        TextView tv = dialog.findViewById(R.id.message);

        EditText name = dialog.findViewById(R.id.name_user);


        tv.setText(gameOverText);
        Button left = dialog.findViewById(R.id.game_dialog_button_left);
        left.setText(cancelButton);

        Button right = dialog.findViewById(R.id.game_dialog_button_right);
        right.setText(newGameButton);


    }

    private void displayCongratsDialog() {
        final Dialog dialog = new Dialog(GameActivity.this);
        String gameOverText = "You win, want another try? ";
        String cancelButton = "Cancel";
        String newGameButton = "New Game";

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_dialog);
        TextView tv = dialog.findViewById(R.id.message);

        EditText name = dialog.findViewById(R.id.name_user);

        tv.setText(gameOverText);
        Button left = dialog.findViewById(R.id.game_dialog_button_left);
        left.setText(cancelButton);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button right = dialog.findViewById(R.id.game_dialog_button_right);
        right.setText(newGameButton);

        dialog.setCancelable(true);
        dialog.show();
    }




    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return swipeListener.getGestureDetector().onTouchEvent(event);
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_button_new_game:
                optionsListener.resetGame();
                break;
            case R.id.main_button_undo:
                optionsListener.undoMovement();

        }
    }
}
