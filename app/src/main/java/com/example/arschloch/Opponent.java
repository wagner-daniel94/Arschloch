package com.example.arschloch;

import java.util.ArrayList;
import java.util.List;

public class Opponent extends Player
{
    @Override
    public void play_card()
    {/*
        if (amountCardsPlayed == 0){
            List<Card> combination = new ArrayList<Card>();
            Card_value cv = p.getCards().get(0).getValue();
            while (p.getCards().get(0).getValue() == cv){
                combination.add(p.getCards().get(0));
                p.getCards().remove(0);
            }
            if(check_combination(combination)){
                amountCardsPlayed = combination.size();
                cardValuePlayed = cv;
            }
        }
        else {
            int index = 0;

            while (index != p.getCards().size()-1){
                if(p.getCards().get(index).getValue().compareTo(cardValuePlayed) >= 0)
                    index++;
            }
        }*/
    }
}
