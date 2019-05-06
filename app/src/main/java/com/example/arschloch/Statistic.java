package com.example.arschloch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Statistic extends AppCompatActivity {

    private int gamesWon = 1;               // gewonnene Spiele
    private int gamesPlayed =2;            // gespielte Spiele
    private double winPercentage = 3;       // Gewinnrate
    private int turnsMade = 4;              // Anzahl der Züge insgesamt
    private int gamesLost = 5;              // Anzahl verlorener Spiele
    // playtime ?
    private double averageRoundLength = 6; //anzahl gespielter karten im durchschnitt


    ListView statistiacsView;

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }

    public int getTurnsMade() {
        return turnsMade;
    }

    public void setTurnsMade(int turnsMade) {
        this.turnsMade = turnsMade;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public double getAverageRoundLength() {
        return averageRoundLength;
    }

    public void setAverageRoundLength(double averageRoundLength) {
        this.averageRoundLength = averageRoundLength;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

    }
}
