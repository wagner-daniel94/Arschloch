package com.example.arschloch;

public class Statistics {
    public int gamesWon;               // gewonnene Spiele
    public int gamesPlayed;            // gespielte Spiele
    public double winPercentage;       // Gewinnrate
    public int turnsMade;              // Anzahl der Züge insgesamt
    public int gamesLost;              // Anzahl verlorener Spiele
    // playtime ?
    public double averageRoundLength; //anzahl gespielter karten im durchschnitt




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
}
