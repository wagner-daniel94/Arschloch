package com.example.arschloch;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected boolean arschloch;
    protected boolean winner;
    protected List<Card> cards;
    protected int points;
    protected List<Card> combination;

    public Player() {
        this.arschloch = false;
        this.winner = false;
        this.cards = new ArrayList<>();
        this.points = 0;
        this.combination = new ArrayList<>();
    }


    public abstract boolean play_card();

    public void move_cards_to_middle(){
        //Karten spielen
        GameActivity.set_card_imageView_middleCards(combination);
        this.cards.removeAll(combination);

        GameActivity.amountCardsPlayed =combination.size();
        GameActivity.cardValuePlayed = combination.get(0).getValue();
        combination.clear();
    }

    public static boolean check_combination(List<Card> choosen_cards) {

        for (int i = 0; i < choosen_cards.size() - 1; i++) {
            //Die ausgewählten Karten dürfen nur den gleichen Wert besitzen
            if (choosen_cards.get(i).getValue() != choosen_cards.get(i + 1).getValue())
                return false;
        }

            return true;
    }

    public abstract void wuenschen(Player arschloch, Player winner, Card wishCard);

    public abstract void tauschen(Player arschloch, Player winner);


    //region Getter & Setter

    public List<Card> getCombination() {
        return combination;
    }

    public void setCombination(List<Card> combination) {
        this.combination = combination;
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
    //endregion
}
