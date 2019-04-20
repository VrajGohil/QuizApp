package io.github.vrajgohil.quizapp;

public class Score {
    String scoreId;
    String scoreName;
    int scoreValue;
    public Score(){

    }
    public Score(String scoreId, String scoreName, int scoreValue) {
        this.scoreId = scoreId;
        this.scoreName = scoreName;
        this.scoreValue = scoreValue;
    }

    public String getScoreId() {
        return scoreId;
    }

    public String getScoreName() {
        return scoreName;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }
}
