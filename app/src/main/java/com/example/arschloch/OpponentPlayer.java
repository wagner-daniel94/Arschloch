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

    @Override
    public void wuenschen (Player arschloch, Player winner, Card wishCard) {
        //erst ab 2.Runde
        if(wishCard != null && arschloch.getCards().contains(wishCard) ){
            winner.getCards().add(wishCard);
            arschloch.getCards().remove(wishCard);
            Collections.sort(arschloch.getCards());
            Collections.sort(winner.getCards());
        }
    }

    @Override
    public void tauschen (Player arschloch, Player winner){
        //erst ab 2.Runde

            Card lowestCard1 = winner.getCards().get(0);
            Card highestCard1 = arschloch.getCards().get(arschloch.getCards().size()-1);
        /*
        Wenn level2 aktiviert
        und die Karte in einer Kombination vorhanden ist,
        wird diese nicht weggegben
        Boy diese Code ist hässlich
        */

            if(level2){
                Card otherCard = winner.getCards().get(0);

                //wenn die Karte i nicht mehrfach vorhanden ist wird sie, gedrückt
                boolean containsCardValue = false;
                for( int i = 0; i<winner.getCards().size();i++){
                    otherCard = winner.getCards().get(i);
                    winner.getCards().remove(otherCard);
                    for(int j = 1; i<winner.getCards().size();j++){
                        if(otherCard.getValue() == winner.getCards().get(j).getValue()){
                            containsCardValue = true;
                            break;
                        }
                    }
                    if(!containsCardValue){
                        break;
                    }
                    winner.getCards().add(otherCard);
                }
                //wenn die Karte mehrfach vorkommt und höher als 10 ist, wird die niedrigste karte gedrückt
                if(otherCard.getValue().compareTo(Card_value.ten)>0){
                    otherCard = winner.getCards().get(0);
                }
                lowestCard1 = otherCard;
            }//Ende Level 2

            //Karten Tausch und Sortieren der Handkarten
            arschloch.getCards().remove(highestCard1);
            winner.getCards().add(highestCard1);
            arschloch.getCards().add(lowestCard1);
            winner.getCards().remove(lowestCard1);


        Collections.sort(arschloch.getCards());
        Collections.sort(winner.getCards());
    }
}
