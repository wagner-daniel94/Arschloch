package com.example.arschloch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HumanPlayer extends Player
{
    @Override
    public void play_card(int amountCardsPlayed, Card_value cardvaluePlayed)
    {

    }

    @Override
    public void druecken(Player arschloch, Player winner, Card wishCard){
        //erst ab 2.Runde

        //wish Anweisung is gleich
        if(wishCard != null && arschloch.getCards().contains(wishCard) ){
            winner.getCards().add(wishCard);
            arschloch.getCards().remove(wishCard);
            Collections.sort(arschloch.getCards());
            Collections.sort(winner.getCards());
        }
        /*
        Card choosenCard1;

        // winner kann 1 Karten auswählen

        arschloch.getCards().add(choosenCard1);
        winner.getCards().remove(choosenCard1);
        Collections.sort(arschloch.getCards());
        Collections.sort(winner.getCards());

        //Karten neu anzeigen

        //Bildschirm updaten
        //invalidate() bei UI Thread
        //postInvalidate() bei non UI Thread
         */
    }
}
