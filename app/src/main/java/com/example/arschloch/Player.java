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

    public void markCard(View v){

    }

    public abstract boolean play_card() throws Exception;
    public abstract void wuenschen(Player arschloch, Card_value wishCardValue);

    public void move_cards_to_middle(){
        //Karten spielen
        GameActivity.showMiddleCards(combination);
        this.cards.removeAll(combination);
        GameActivity.amountCardsPlayed =combination.size();
        GameActivity.cardValuePlayed = combination.get(0).getValue();
        combination.clear();
    }

    public static boolean check_combination(List<Card> chosen_cards) {

        for (int i = 0; i < chosen_cards.size() - 1; i++) {
            //Die ausgewählten Karten dürfen nur den gleichen Wert besitzen
            if (chosen_cards.get(i).getValue() != chosen_cards.get(i + 1).getValue())
                return false;
        }

            return true;
    }

    //region Getter & Setter




    public boolean isArschloch() {
        return arschloch;
    }

    public boolean isWinner() {
        return winner;
    }

    public List<Card> getCards() {
        return cards;
    }

    /*public int getPoints() {
        return points;
    }*/
    //evntuell Player markieren, wenn er Arschloch ist
    //handCardsImageViews.get(i).setForeground(getDrawable(R.drawable.marked));
    public void setArschloch(boolean arschloch) {
        this.arschloch = arschloch;
    }
    //eventuell Player markieren, wenn er Winner ist
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
/*
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setPoints(int punkte) {
        this.points = punkte;
    }*/
    //endregion
}
