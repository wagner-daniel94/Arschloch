package com.example.arschloch;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HumanPlayer extends Player
{
    private List<Card> markedCards;

    public HumanPlayer(){
        super();
        markedCards =new ArrayList<>();
    }

    public void markCard(View v){
        for(Card c: this.getCards()){
            if(c.getResourceId() == (int)v.getTag() && !c.isMarked()){
                markedCards = new ArrayList<>();
                for(Card c1: this.getCards()){
                    if(c1.isMarked()){
                        markedCards.add(c1);
                    }
                }
                markedCards.add(c);
                if(check_combination(markedCards)) {
                    c.setMarked(true);
                    System.out.println("Card Marked");
                    //Toast.makeText(this,"new combination",Toast.LENGTH_LONG).show();
                }

                    //else Toast.makeText(this,"invalid card selected",Toast.LENGTH_LONG).show();

            }else if(c.getResourceId() ==(int)v.getTag() && c.isMarked()){
                c.setMarked(false);
                markedCards.remove(c);
                System.out.println("Card Demarked");
            }
        }


    }




    @Override
    public void play_card()
    {
        boolean move =true;

        //check ob Karten markiert wurden
        if (markedCards.size() == 0)
            move = false;
            //wenn amountCardsPlayed 0 ist wurde noch keine Karte gespielt
        else if (GameActivity.amountCardsPlayed == 0) {
            move = true;
            //markierte Karten müssen vom Wert größer sein als die zuvor gespielten Karten
        } else if (markedCards.get(0).getValue().compareTo(GameActivity.cardValuePlayed) >= 1) {
            //die Anzahl der markierten Karten muss mit der Anzahl der zuvor gespielten Karten übereinstimmen
            if (markedCards.size() == GameActivity.amountCardsPlayed)
                move = true;

            else
                move = false;

        } else
            move = false;








        if(move){
            //Karten spielen

            throw new RuntimeException("Valid move");
        }
        else
            throw new RuntimeException("Invalid move");
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
