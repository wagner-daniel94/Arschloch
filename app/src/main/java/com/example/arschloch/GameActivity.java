package com.example.arschloch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/*
Änderung: Austeilalgorithmus
Datum: 21.04.2019
Autor: Wagner
 */

public class GameActivity extends AppCompatActivity {

    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Collection<Card> distributed_cards;
    int playersTurn;
    int amountCardsPlayed = 0;

    TextView spieler1TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button austeilenBtn =(Button) findViewById(R.id.austeilenBtn);
        spieler1TV = (TextView)findViewById(R.id.spieler1TV);

        cards_distributing();
        austeilenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spieler1TV.setText("");

                cards_distributing();
                playersTurn = get_firstPlayer();
                Card c1 = new Card(Card_value.four,Card_symbol.symbol1);
                Card c2 = new Card(Card_value.four,Card_symbol.symbol1);
                Card c3 = new Card(Card_value.five,Card_symbol.symbol1);
                List<Card> examples = new ArrayList<Card>();
                examples.add(c1);
                examples.add(c2);
                examples.add(c3);

                check_combination(examples);
            }
        });

    }

    private void cards_distributing(){
        int i = 0;
        int random_value;
        int random_symbol;
        Card choosen_card;
        distributed_cards = new ArrayList<Card>();
        player1 = new Player(new ArrayList<Card>());
        player2 = new Player(new ArrayList<Card>());
        player3 = new Player(new ArrayList<Card>());
        player4 = new Player(new ArrayList<Card>());
        while(true)
        {

            random_value = (int)(Math.random()*13);
            random_symbol = (int)(Math.random()*4);

            choosen_card = new Card(Card_value.values()[random_value], Card_symbol.values()[random_symbol]);
            if(!check_card_already_distributed(choosen_card));
            {
                distributed_cards.add(choosen_card);
                switch (i) {
                    case 0:
                        player1.getCards().add(choosen_card);
                        break;
                    case 1:
                        player2.getCards().add(choosen_card);
                        break;
                    case 2:
                        player3.getCards().add(choosen_card);
                        break;
                    case 3:
                        player4.getCards().add(choosen_card);
                       break;
                }
                if(i==3)
                    i=0;
                else
                    i++;
            }
            if(distributed_cards.size() >=52)
                break;
        }
        String st;
        Collections.sort(player1.getCards());
        for(Card c:player1.getCards()){
            st = spieler1TV.getText().toString() + c.getValue() + " " + c.getSymbol() + "\n";
            spieler1TV.setText(st);
        }

    }

    private boolean check_card_already_distributed(Card card){
        for(Card c: distributed_cards){
            if(c.getValue() == card.getValue() && c.getSymbol() == card.getSymbol()){
                return true;
            }
        }
        return false;
    }

    private int get_firstPlayer(){
        if(player1.isArschloch())
            return 1;
        else if(player2.isArschloch())
            return  2;
        else if(player3.isArschloch())
            return  3;
        else if(player4.isArschloch())
            return  4;
        else
            return (int)(Math.random()*4)+1;
    }

    private boolean check_combination(List<Card> choosen_cards){
        boolean value_statement =true;
        for(int i = 0; i < choosen_cards.size()-1;i++){
            if(choosen_cards.get(i).getValue() != choosen_cards.get(i+1).getValue())
                value_statement = false;
        }
        if((choosen_cards.size() == amountCardsPlayed || amountCardsPlayed == 0) && value_statement == true)
            return true;
        else
            return false;
    }
}
