package edu.gatech.seclass.words6300;

public class GameStat {
    private int score;
    private int turns;
    private double scorePerTurn;
    private GameSettings settings;

    public GameStat (int score, int turns, GameSettings settings){
        this.score = score;
        this.turns = turns;
        this.scorePerTurn = score/turns;
        this.settings = settings;
    }

    public GameStat (int score, int turns){
        this.score = score;
        this.turns = turns;
        this.scorePerTurn = score/turns;
    }

    //getters
    public int getScore() {        return score;    }
    public int getTurns() {        return turns;    }
    public double getScorePerTurn() {        return scorePerTurn;    }
    public GameSettings getSettings() {        return settings;    }

}
