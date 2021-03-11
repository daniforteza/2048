package com.example.a2048;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ScoreDB extends SQLiteOpenHelper {

        private static final String TABLE_SCORES = "SCORES_TB";
        private static final String DB = "Scores";
        private static final String COLUMN_ID = "ID";
        private static final String COLUMN_USERNAME = "NAME";
        private static final String COLUMN_SCORE = "SCORE";
        private static final String COLUMN_DURATION = "DURATION";
        private static ScoreDB dbInstance = null;

        public static ScoreDB getInstance(Context activityContext){
            if(dbInstance == null){
                dbInstance = new ScoreDB(activityContext.getApplicationContext());
            }
            return dbInstance ;
        }

        private ScoreDB(@Nullable Context context) {
            super(context, DB, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableStatement = "CREATE TABLE " + TABLE_SCORES + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + COLUMN_SCORE + " INT," + COLUMN_USERNAME + " TEXT," + COLUMN_DURATION + " INT) ";
            db.execSQL(createTableStatement);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE " + TABLE_SCORES);
            onCreate(db);
        }

        /**
         * Method to add a Score in DB
         * @param score Score to add
         */
        public long addScoreDB(Score score){
            long success = 0;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_USERNAME, score.getUsername());
            cv.put(COLUMN_SCORE, score.getScoreNum());
            cv.put(COLUMN_DURATION, score.getGamelength());
            success = db.insert(TABLE_SCORES, null, cv);
            return success;
        }

        /**
         * Get all scores in DB
         * @return ArrayList
         */
        public List<Score> getScores(){
            List<Score> getAllScores = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            String queryAllScores = "SELECT * FROM " + TABLE_SCORES + " ORDER BY " + COLUMN_SCORE +" DESC";
            Cursor cursor = db.rawQuery(queryAllScores, null);
            if (cursor.moveToNext()) {
                do {
                    int scoreId = cursor.getInt(0);
                    Integer scoreNum = cursor.getInt(1);
                    String username = cursor.getString(2);
                    Float duration = cursor.getFloat(3);
                    Score score = new Score(scoreId, scoreNum, username, duration);
                    getAllScores.add(score);
                }
                while (cursor.moveToNext());
            }
            else{
                //There aren't scores. No scores will be displayed
            }
            cursor.close();
            return getAllScores;
        }

        /**
         * Find top 10 scores by a determinated user
         * @param userTop is the User we will use in query
         * @return ArrayList10
         */
        public List<Score> getByUsername(String userTop){
            List<Score> getByUsername = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            String queryByUsername = "SELECT * FROM " + TABLE_SCORES + " WHERE "+ COLUMN_USERNAME + " = '"+ userTop +"' ORDER BY " + COLUMN_SCORE;
            Cursor cursor = db.rawQuery(queryByUsername, null);
            if (cursor.moveToNext()) {
                do {
                    int scoreId = cursor.getInt(0);
                    Integer scoreNum = cursor.getInt(1);
                    String username = cursor.getString(2);
                    Float duration = cursor.getFloat(3);
                    Score score = new Score(scoreId, scoreNum, username, duration);
                    getByUsername.add(score);
                }
                while (cursor.moveToNext());
            }
            else{
                //There aren't scores. No scores will be displayed
            }
            cursor.close();
            return getByUsername;
        }

        public Score getById(Integer id) {
            SQLiteDatabase db = this.getWritableDatabase();
            Score score = new Score();
            String queryById = "SELECT * FROM " + TABLE_SCORES + " WHERE "+ COLUMN_ID + " = "+ id;
            Cursor cursor = db.rawQuery(queryById, null);
            cursor.moveToNext();
            score.setId(cursor.getInt(0));
            score.setScoreNum(cursor.getInt(1));
            score.setUsername(cursor.getString(2));
            score.setGamelength(cursor.getFloat(3));
            return score;
        }

        /**
         * We delete a Score by ID
         * Method used on RecyclerView
         * @param id to be deleted
         */
        public void deleteScoreById(Integer id){
            SQLiteDatabase db = this.getWritableDatabase();
            String queryDeleteByID = "DELETE FROM " + TABLE_SCORES + " WHERE " + COLUMN_ID + " = " + id;
            db.execSQL(queryDeleteByID);
        }

        /**
         * BEST SCORE
         * @return best score
         */
        public int bestScore(){
            SQLiteDatabase db = this.getWritableDatabase();
            Integer bestScore;
            String queryTopScore = "SELECT MAX(" + COLUMN_SCORE + ") FROM "+ TABLE_SCORES;
            Cursor cursor = db.rawQuery(queryTopScore, null);
            cursor.moveToFirst();
            bestScore = cursor.getInt(0);
            if (bestScore == null){
                return 0;
            }
            else{
                return bestScore;
            }
        }


        /**
         * Update an item of DB
         */
        public void updateScore(int id, Integer scoreNum, String username, Float duration){
            SQLiteDatabase db = this.getWritableDatabase();

            String updateScoreByID = "UPDATE " + TABLE_SCORES
                    + " SET " + COLUMN_USERNAME + " = '" + username + "' , " +
                    COLUMN_SCORE + " = " + scoreNum + ", " +
                    COLUMN_DURATION + " = " + duration +
                    " WHERE " + COLUMN_ID + " = " + id;
            db.execSQL(updateScoreByID);
        }


}
