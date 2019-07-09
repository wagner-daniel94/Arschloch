package com.example.arschloch;


import android.view.View;


import java.util.Collections;

public class HumanPlayer extends Player
{

    @Override
    public void markCard(View v){
        for(Card c: this.getCards())
            if(c.getResourceId() == (int)v.getTag() && !c.isMarked()){


                combination.add(c);
                if(check_combination(combination)) c.setMarked(true);
                else
                    combination.remove(c);


            }else if(c.getResourceId() ==(int)v.getTag() && c.isMarked()){
                c.setMarked(false);
                combination.remove(c);
            }



    }




    @Override
    public boolean play_card() throws Exception
    {
        if(this.getCards().size() ==0 ){
            throw new Exception("Player has no cards left!!");
        }

        boolean move;

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

    public void wuenschen(Player arschloch, Card_value wishCardValue){
        //winner hat gewünschte Karte aus allen Kartenwerten ausgewählt



        //durchsuchen der Karten des Arschlochs nach dem gewünschten Wertes
        for(int i = arschloch.getCards().size()-1; i>= 0;i--)
            if(wishCardValue.compareTo(arschloch.getCards().get(i).getValue()) == 0){
                //gewünschte Karte tauschen
                Card c1 = arschloch.getCards().get(i);
                this.getCards().add(c1);
                arschloch.getCards().remove(c1);
                //niedrigste Karte tauschen
                Card c2 = this.getCards().get(0);
                this.getCards().remove(c2);
                arschloch.getCards().add(c2);
                break;
            }

        Collections.sort(arschloch.getCards());
        Collections.sort(this.getCards());
    }
}