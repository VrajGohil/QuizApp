package io.github.vrajgohil.quizapp;

public class Score {
    String scoreId;
    String scoreName;
    String scoreValue;
    public Score(){

    }
    public Score(String scoreId, String scoreName, String scoreValue) {
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

    public String getScoreValue() {
        return scoreValue;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public void setScoreValue(String scoreValue) {
        this.scoreValue = scoreValue;
    }
}
