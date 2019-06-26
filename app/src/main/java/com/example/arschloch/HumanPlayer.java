package com.example.arschloch;

import android.view.View;

import java.util.Collections;

public class HumanPlayer extends Player
{

    @Override
    public void markCard(View v){
        for(Card c: this.getCards()){
            if(c.getResourceId() == (int)v.getTag() && !c.isMarked()){


                combination.add(c);
                if(check_combination(combination)) {
                    c.setMarked(true);
                }
                else
                    combination.remove(c);


            }else if(c.getResourceId() ==(int)v.getTag() && c.isMarked()){
                c.setMarked(false);
                combination.remove(c);
            }
        }


    }




    @Override
    public boolean play_card() throws Exception
    {
        if(this.getCards().size() ==0 ){
            throw new Exception("Player has no cards left!!");
        }

        boolean move =true;

        //check ob Karten markiert wurden
        if (combination.size() == 0)
            move = false;
            //wenn amountCardsPlayed 0 ist wurde noch keine Karte gespielt
        else if (GameActivity.amountCardsPlayed == 0) {
            move = true;
            //markierte Karten müssen vom Wert größer sein als die zuvor gespielten Karten
        } else if (combination.get(0).getValue().compareTo(GameActivity.cardValuePlayed) >= 1) {
            //die Anzahl der markierten Karten muss mit der Anzahl der zuvor gespielten Karten übereinstimmen
            if (combination.size() == GameActivity.amountCardsPlayed)
                move = true;

            else
                move = false;

        } else
            move = false;

        if(move){
            //Karten spielen
            move_cards_to_middle();
            return true;
        }
        else
            return false;
    }

    @Override
    public void wuenschen(Player arschloch, Player winner, Card wishCard){
        //winner wählt Karte aus

        if(arschloch.getCards().contains(wishCard) ){
            winner.getCards().add(wishCard);
            arschloch.getCards().remove(wishCard);
            Collections.sort(arschloch.getCards());
            Collections.sort(winner.getCards());
        }
    }

    @Override
    public void tauschen(Player arschloch, Player winner){
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
