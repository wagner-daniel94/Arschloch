package com.example.arschloch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OpponentPlayer extends Player
{

    boolean level2 =  false;

    @Override
    public boolean play_card() throws Exception
    {
        if (this.getCards().size() == 0){
            throw new Exception("Player has no cards left!!");
        }
        if (GameActivity.amountCardsPlayed == 0){
            //List<Card> combination = new ArrayList<Card>();
            Card_value cv = this.getCards().get(0).getValue();
            int i = 0;
            while (this.getCards().get(i).getValue() == cv){
                combination.add(this.getCards().get(i));
                if(i<this.getCards().size()-1) {
                    i++;
                }
                else
                    break;
                //this.getCards().remove(0);
            }
            if(this.check_combination(combination)){
                move_cards_to_middle();
                return true;

            }
        }
        else {
            for(int i = 0;i<this.getCards().size();i++) {
                if(this.getCards().get(i).getValue().compareTo(GameActivity.cardValuePlayed) > 0) {
                    if (get_cards_marked_by_amount_value(this.getCards().get(i).getValue())) {
                        if (this.check_combination(combination)) {
                            move_cards_to_middle();
                            return  true;
                        }
                    }
                }


            }

        }
        return false;
    }

    private boolean get_cards_marked_by_amount_value(Card_value cv){
        List<Card> tmpList = new ArrayList<>();
        for(Card c:this.getCards()){
            if(c.getValue() == cv){
                tmpList.add(c);
            }
        }
        if(tmpList.size()==GameActivity.amountCardsPlayed){
            this.combination = tmpList;
            return true;
        }
        else
            return false;
    }

    public void wuenschen (Player arschloch, Card_value wishCardValue) {
        //erst ab 2.Runde



        double probability = Math.random();
        //Wahrscheinlichkeiten mit denen die Kartenwerte gewünscht werden
        if(probability <  0.6){
            wishCardValue = Card_value.ace;
        }else if (probability < 0.8){
            wishCardValue = Card_value.king;
        }else if (probability < 0.9){
            wishCardValue = Card_value.queen;
        }else{
            //höchste Karte die der Gewinner hat wird standarmäßig von ihm gewünscht
            wishCardValue = this.getCards().get(this.getCards().size()-1).getValue();

            //wenn die höchste Karte vom Gewinner kleiner als Bube ist, wird ein Ass gewünscht
            if(wishCardValue.compareTo(Card_value.jack)<0){
                wishCardValue = Card_value.ace;
            }
        }

        //durchsuchen der Karten des Arschlochs nach dem gewünschten Wertes
        for(int i = arschloch.getCards().size()-1; i>= 0;i--){
            if(wishCardValue.compareTo(arschloch.getCards().get(i).getValue()) == 0){
                //gewünschte Karte tauschen
                Card c = arschloch.getCards().get(i);
                this.getCards().add(c);
                arschloch.getCards().remove(c);
                //niedrigste Karte tauschen
                c = this.getCards().get(0);
                this.getCards().remove(c);
                arschloch.getCards().add(c);
                break;

            }
        }
        Collections.sort(arschloch.getCards());
        Collections.sort(this.getCards());
    }


}
