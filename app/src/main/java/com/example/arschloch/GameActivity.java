package com.example.arschloch;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    //Spieler
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    //Karten
    Cards card_deck;
    //Spieler an der Reihe
    int playersTurn;
    int turnCount = 1;
    //Anzahl der Karten die zuvor gespielt wurden
    int amountCardsPlayed = 0;
    //Kartenwert der zuvor gespielten KArten
    Card_value cardValuePlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button austeilenBtn =(Button) findViewById(R.id.austeilenBtn);
        Button playBtn = (Button) findViewById(R.id.playBtn);

        cards_distributing();
        austeilenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cards_distributing();
                playersTurn = get_firstPlayer();
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_card(player1);
            }
        });
    }

/*
* Das austeielen der Karten
* */
    private void cards_distributing(){
        //Zähler für Spieler 1-4 (0-3)
        int i = 0;
        //Zähler für ausgeteilte Karten
        int c = 0;
        //Variable um eine Karte zu erstellen
        card_deck = new Cards();

        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();

        Collections.shuffle(card_deck.getCards());

        /* Karten erzeugen
        for(int k= 0;k<=12;k++){
            for(int l = 0;l<=3;l++){
                create_card = new Card(Card_value.values()[k], Card_symbol.values()[l]);//weil sich der Konstruktor geändert hat
                card_deck.add(create_card);
            }
        }
        */

        while(true)
        {
                switch (i) {
                    case 0:
                        player1.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 1:
                        player2.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 2:
                        player3.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 3:
                        player4.getCards().add(card_deck.getCards().get(c));
                       break;
                }
                if(c!=card_deck.getCards().size()-1)
                c++;
                else
                    break;

                //Auswahl des nächsten Spielers, der eine Karte erhält. Nach Spieler4(i==3) ist Spieler1 wieder an der Reihe
                if(i==3)
                    i=0;
                else
                    i++;
            }

        //Sortieren der Kartendecks
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());
        Collections.sort(player3.getCards());
        Collections.sort(player4.getCards());
        set_card_imageView();

    }


    /*
    Karte einem ImageView zur Laufzeit zuweisen
     */

    public void set_card_imageView(){

        List<ImageView> handCardsImageViews = new ArrayList<ImageView>();

        handCardsImageViews.add((ImageView)findViewById(R.id.card1));
        handCardsImageViews.add((ImageView)findViewById(R.id.card2));
        handCardsImageViews.add((ImageView)findViewById(R.id.card3));
        handCardsImageViews.add((ImageView)findViewById(R.id.card4));
        handCardsImageViews.add((ImageView)findViewById(R.id.card5));
        handCardsImageViews.add((ImageView)findViewById(R.id.card6));
        handCardsImageViews.add((ImageView)findViewById(R.id.card7));
        handCardsImageViews.add((ImageView)findViewById(R.id.card8));
        handCardsImageViews.add((ImageView)findViewById(R.id.card9));
        handCardsImageViews.add((ImageView)findViewById(R.id.card10));
        handCardsImageViews.add((ImageView)findViewById(R.id.card11));
        handCardsImageViews.add((ImageView)findViewById(R.id.card12));
        handCardsImageViews.add((ImageView)findViewById(R.id.card13));





        for(int i = 0; i < player1.getCards().size();i++){
            handCardsImageViews.get(i).setImageResource(player1.getCards().get(i).getResourceId());
            //handCardsImageViews.get(i).setTag(player1.getCards().get(i).getResourceId());
        }

    }

    public void test(View v){
        Toast.makeText(this,v.getTag().toString(), Toast.LENGTH_SHORT).show();
        System.out.println(v.getTag().toString());
    }

    /*
    public void markCard(View v){

        for(Card c: player1.getCards()){
            if(c.getResourceId() == v.getTag()){
                c.setClicked(true);
                System.out.println("Card Marked");
            }else if(c.isClicked()){
                c.setClicked(false);
                System.out.println("Card Demarked");
            }
        }
    }

*/



/*
* Festlegung des ersten Spielers
* */
    private int get_firstPlayer(){
        //Wenn Spieler Arschloch in der Runde zuvor war beginnt er das Spiel. Wenn es die erste Runde ist entscheidet der Zufall
        if(player1.isArschloch()){

            return 1;}
        else if(player2.isArschloch())
            return  2;
        else if(player3.isArschloch())
            return  3;
        else if(player4.isArschloch())
            return  4;
        else
            return (int)(Math.random()*4)+1;
    }



    private Player get_winner(){
        int points =0;
        if(player1.isWinner()){
            points = player1.getPoints();
            points++;
            player1.setPoints(points);
            return player1;}
        else if(player2.isWinner()){
            points = player2.getPoints();
            points++;
            player2.setPoints(points);
            return player2;}
        else if(player3.isWinner()){
            points = player3.getPoints();
            points++;
            player3.setPoints(points);
            return player3;}
        else if(player4.isWinner()){
            points = player4.getPoints();
            points++;
            player4.setPoints(points);
            return player4;}
        else
        return null;
    }




/*
* Überprüfung ob es eine gültige Kombintion ist
* */
    private boolean check_combination(List<Card> choosen_cards){
        boolean value_statement =true;
        for(int i = 0; i < choosen_cards.size()-1;i++){
            //Die ausgewählten Karten dürfen nur den gleichen Wert besitzen
            if(choosen_cards.get(i).getValue() != choosen_cards.get(i+1).getValue())
                value_statement = false;
        }
        //Die Anzahl der Karten muss mit der Anzahl zuvor gespielter Karten übereinstimmen
        if((choosen_cards.size() == amountCardsPlayed || amountCardsPlayed == 0) && value_statement == true)
            return true;
        else
            return false;
    }

/*
* Das spielen von Karten
* */
    private void play_card(Player p){
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
        }

    }
    //Zählen von Zügen
    private int turnCount (){
        if (playersTurn==0) {
            turnCount++;}
            return turnCount;

    }
    //Zählen des Spielers der an der Reihe ist
    private int playerPlayer () {
        if (playersTurn == 3)

            return 0;
        else
            return playersTurn++;

    }
}
