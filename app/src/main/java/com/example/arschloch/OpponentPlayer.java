package com.example.arschloch;

import java.util.ArrayList;
import java.util.List;

public class OpponentPlayer extends Player
{
    @Override
    public void play_card(int amountCardsPlayed, Card_value cardValuePlayed)
    {
        if (amountCardsPlayed == 0){
            List<Card> combination = new ArrayList<Card>();
            Card_value cv = this.getCards().get(0).getValue();
            while (this.getCards().get(0).getValue() == cv){
                combination.add(this.getCards().get(0));
                this.getCards().remove(0);
            }
            if(GameActivity.check_combination(combination)){
                amountCardsPlayed = combination.size();
                cardValuePlayed = cv;
            }
        }
        else {
            int index = 0;

            while (index != this.getCards().size()-1){
                if(this.getCards().get(index).getValue().compareTo(cardValuePlayed) >= 0)
                    index++;
            }
        }
    }
}
