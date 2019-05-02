package com.example.arschloch;

import java.util.List;

public class Player {
    private boolean arschloch;
    private  boolean winner;
    private List<Card> cards;
    private int points;

    public Player(List<Card> cards){
        this.arschloch = false;
        this.winner = false;
        this.cards = cards;
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
}
