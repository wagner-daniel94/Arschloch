package com.example.arschloch;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private boolean arschloch;
    private  boolean winner;
    private List<Card> cards;
    private int points;

    public Player(){
        this.arschloch = false;
        this.winner = false;
        this.cards = new ArrayList<Card>();
        this.points = 0;
    }


    public boolean isArschloch() {
        return arschloch;
    }

    public boolean isWinner() {
        return winner;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getPoints() {
        return points;
    }

    public void setArschloch(boolean arschloch) {
        this.arschloch = arschloch;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setPoints(int punkte) {
        this.points = punkte;
    }

    public abstract void play_card();
}
