package com.example.arschloch;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private boolean arschloch;
    private boolean winner;
    private List<Card> cards;
    private int points;

    public Player() {
        this.arschloch = false;
        this.winner = false;
        this.cards = new ArrayList<Card>();
        this.points = 0;
    }


    public abstract void play_card();

    public static boolean check_combination(List<Card> choosen_cards) {
        boolean value_statement = true;
        for (int i = 0; i < choosen_cards.size() - 1; i++) {
            //Die ausgewählten Karten dürfen nur den gleichen Wert besitzen
            if (choosen_cards.get(i).getValue() != choosen_cards.get(i + 1).getValue())
                value_statement = false;
        }
        //Die Anzahl der Karten muss mit der Anzahl zuvor gespielter Karten übereinstimmen
        if ((choosen_cards.size() == GameActivity.amountCardsPlayed || GameActivity.amountCardsPlayed == 0)
                && value_statement == true)
            return true;
        else
            return false;
    }

    public abstract void wuenschen(Player arschloch, Player winner, Card wishCard);

    public abstract void tauschen(Player arschloch, Player winner);


    //Getter & Setter

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
