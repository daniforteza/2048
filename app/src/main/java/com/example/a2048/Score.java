package com.example.a2048;

public class Score {
    private Integer id;
    private Integer scoreNum;
    private String username;
    private Float gamelength;

    public Score() {
    }

    public Score(Integer id, Integer scoreNum, String username, Float gamelength) {
        this.id = id;
        this.scoreNum = scoreNum;
        this.username = username;
        this.gamelength = gamelength;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getGamelength() {
        return gamelength;
    }

    public void setGamelength(Float gamelength) {
        this.gamelength = gamelength;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", scoreNum=" + scoreNum +
                ", username='" + username + '\'' +
                ", gamelength=" + gamelength +
                '}';
    }
}
