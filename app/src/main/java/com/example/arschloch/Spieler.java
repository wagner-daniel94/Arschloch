package com.example.arschloch;

import java.util.List;

public class Spieler {
    private boolean arschloch;
    private  boolean winner;
    private List<Spielkarte> spielkarten;
    private int punkte;

    public  Spieler(List<Spielkarte> spielkarten){
        this.arschloch = false;
        this.winner = false;
        this.spielkarten = spielkarten;
        this.punkte = 0;
    }


    public boolean isArschloch() {
        return arschloch;
    }

    public boolean isWinner() {
        return winner;
    }

    public List<Spielkarte> getSpielkarten() {
        return spielkarten;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setArschloch(boolean arschloch) {
        this.arschloch = arschloch;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void setSpielkarten(List<Spielkarte> spielkarten) {
        this.spielkarten = spielkarten;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
}
