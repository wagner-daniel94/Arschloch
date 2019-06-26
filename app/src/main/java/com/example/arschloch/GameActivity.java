package com.example.arschloch;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
Änderung: Austeilalgorithmus
Datum: 21.04.2019
Autor: Wagner
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //

    //region Variablen
    //Spielerobjkte
    HumanPlayer humanPlayer;
    Player opponentPlayer1;
    Player opponentPlayer2;
    Player opponentPlayer3;
    //Kartenstapelobjekte der ImageViews
    static List<ImageView> handCardsImageViews;
    static List<ImageView> middleCardsImageViews;
    //Textviews für Kartenanzhal Anzeige
    TextView TVacop3;
    TextView TVacop1;
    TextView TVacop2;

    TextView TVCardsPlayedBy;
    //Kartenstapel mit allen Karten
    CardDeck card_deck;
    //Spieler an der Reihe
    int playersTurn;
    //Anzahl Spielzüge
    int turnCount = 1;
    //Anzahl an hintereinander folgenden Skips
    int amountSkipped = 0;
    //Anzahl der Karten die zuvor gespielt wurden
    static int amountCardsPlayed = 0;
    //Kartenwert der zuvor gespielten KArten
    static Card_value cardValuePlayed;
    //Rundenanzahl
    int amountRounds = 1;
    //Rundenanzahl für die Statistik
    int amountRoundsStatistic = 0;
    //gewonnene Runden
    int amountRoundsWon = 0;
    // anzahl gespielter Runden
    int amountTurnsPlayed = 0;
    //Anzahl verlorener Spiele
    int amountGamesLost = 0;
    //endregion.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button passBtn = (Button) findViewById(R.id.passBtn);
        card_deck = new CardDeck();

        humanPlayer = new HumanPlayer();
        opponentPlayer1 = new OpponentPlayer();
        opponentPlayer2 = new OpponentPlayer();
        opponentPlayer3 = new OpponentPlayer();


        TVacop3 = (TextView)findViewById(R.id.TVacop3) ;
        TVacop1 = (TextView)findViewById(R.id.TVacop1) ;
        TVacop2 = (TextView)findViewById(R.id.TVacop2) ;

        TVCardsPlayedBy = (TextView)findViewById(R.id.TVCardsPlayedBy);

        playBtn.setOnClickListener(this);
        passBtn.setOnClickListener(this);

        handCardsImageViews = new ArrayList<>();
        middleCardsImageViews = new ArrayList<>();

        //Zuweisung der Karten-IDs
        int[] array = new int[]{R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6, R.id.card7, R.id.card8, R.id.card9, R.id.card10, R.id.card11, R.id.card12, R.id.card13};

        for (int i = 0; i < array.length; i++) {
            handCardsImageViews.add((ImageView)findViewById(array[i]));
        }
        array = new int[]{R.id.middlecard1,R.id.middlecard2,R.id.middlecard3,R.id.middlecard4};

        for (int i = 0; i < array.length; i++) {
            middleCardsImageViews.add((ImageView)findViewById(array[i]));
        }

        resetRound();


    }
@Override
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.playBtn:
                // Methoden die beim Klick auf Play gestartet werden sollen
                //PlayHumanCards
                try {

                    checkSkipAmount(getAmountPlayersInGame());
                    //play_card gibt einen boolean zurück ob Karten gespielt wurden
                    if(humanPlayer.getCards().size() != 0) {
                        if (humanPlayer.play_card()) {
                            TVCardsPlayedBy.setText("You played:");
                            amountSkipped = 0;
                            amountTurnsPlayed++;
                        }
                        else
                            //Der Spieler kann durch drücken auf Play nicht skippen
                            throw new Exception("Invalid move!");
                    }

                    set_card_imageView();
                    Thread.sleep(500);

                    checkSkipAmount(getAmountPlayersInGame());
                    if(opponentPlayer1.getCards().size() != 0) {
                        if (opponentPlayer1.play_card()) {
                            amountSkipped = 0;
                            TVCardsPlayedBy.setText("OpponentPlayer1 played:");
                            TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
                        } else
                            amountSkipped++;
                    }
                    //Thread.sleep(500);
                    checkSkipAmount(getAmountPlayersInGame());
                    if(opponentPlayer2.getCards().size() != 0) {
                        if (opponentPlayer2.play_card()) {
                            TVCardsPlayedBy.setText("OpponentPlayer2 played:");
                            amountSkipped = 0;
                            TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                        } else
                            amountSkipped++;
                    }
                    //Thread.sleep(500);

                    checkSkipAmount(getAmountPlayersInGame());
                    if(opponentPlayer3.getCards().size() != 0) {
                        if (opponentPlayer3.play_card()) {
                            TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                            amountSkipped = 0;
                            TVacop3.setText(String.valueOf(opponentPlayer2.getCards().size()));
                        } else
                            amountSkipped++;
                    }

                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(e.getMessage());
                }
                //PlayAICards
                break;
            case R.id.passBtn:
                // Methoden die beim Klick auf Pass gestartet werden sollen
                //PlayAICards
                try {
                    checkSkipAmount(getAmountPlayersInGame());

                //Player hat geskippt
                amountSkipped++;

                    checkSkipAmount(getAmountPlayersInGame());
                if(opponentPlayer1.getCards().size() != 0) {
                    if (opponentPlayer1.play_card()) {
                        TVCardsPlayedBy.setText("OpponentPlayer1 played:");
                        amountSkipped = 0;
                        TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
                    } else
                        amountSkipped++;
                }

                //Thread.sleep(5000);
                    checkSkipAmount(getAmountPlayersInGame());
                if(opponentPlayer2.getCards().size() !=0) {
                    if (opponentPlayer2.play_card()) {
                        TVCardsPlayedBy.setText("OpponentPlayer2 played:");
                        amountSkipped = 0;
                        TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                    } else
                        amountSkipped++;
                }

                //Thread.sleep(5000);
                    checkSkipAmount(getAmountPlayersInGame());
                if(opponentPlayer3.getCards().size() !=0) {
                    if (opponentPlayer3.play_card()) {
                        TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                        amountSkipped = 0;
                        TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                    } else
                        amountSkipped++;
                }
        }
                catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println(e.getMessage());
            }
                break;
        }

        System.out.println("Log: Before Game Loop");
        if(humanPlayer.getCards().size() == 0) {
            finishing_Gameloop();
            resetRound();
        }
    }

    //check, wie viele Spieler noch in game sind
    private int getAmountPlayersInGame(){

        int playersInGame = 0;
        if(humanPlayer.getCards().size() > 0)
            playersInGame++;
        if(opponentPlayer1.getCards().size() > 0)
            playersInGame++;
        if(opponentPlayer2.getCards().size() > 0)
            playersInGame++;
        if(opponentPlayer3.getCards().size() > 0)
            playersInGame++;

        return playersInGame;
    }

    //check, ob viermal geskippt wurde. setzt dann die Variablen zurück
    private void checkSkipAmount(int i){
        // -1 da immer "Anzahl Spieler - 1" Skips notwendig sind um eine Runde zu beenden
        if (amountSkipped == i - 1) {
            amountCardsPlayed = 0;
            cardValuePlayed = null;
            amountSkipped = 0;
            set_card_imageView_middleCards(null);
        }
        checkRoundOver();
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


        humanPlayer.getCards().clear();
        opponentPlayer1.getCards().clear();
        opponentPlayer2.getCards().clear();
        opponentPlayer3.getCards().clear();

        Collections.shuffle(card_deck.getCards());


        while(c <= card_deck.getCards().size()-1)
        {
                switch (i) {
                    case 0:
                        humanPlayer.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 1:
                        opponentPlayer1.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 2:
                        opponentPlayer2.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 3:
                        opponentPlayer3.getCards().add(card_deck.getCards().get(c));
                       break;
                }

                //Zähler hochzählen
                c++;


                //Auswahl des nächsten Spielers, der eine Karte erhält. Nach Spieler4(i==3) ist Spieler1 wieder an der Reihe
                if(i==3)
                    i=0;
                else
                    i++;
            }

        //Sortieren der Kartendecks
        Collections.sort(humanPlayer.getCards());
        Collections.sort(opponentPlayer1.getCards());
        Collections.sort(opponentPlayer2.getCards());
        Collections.sort(opponentPlayer3.getCards());
        set_card_imageView();

    }

    /*
    Karte einem ImageView zur Laufzeit zuweisen
     */
    public void set_card_imageView(){

        for(int i = 0; i < handCardsImageViews.size();i++){
            if(i<= humanPlayer.getCards().size()-1) {

                handCardsImageViews.get(i).setVisibility(View.VISIBLE);
                handCardsImageViews.get(i).setImageResource(humanPlayer.getCards().get(i).getResourceId());
                handCardsImageViews.get(i).setTag(humanPlayer.getCards().get(i).getResourceId());
                if (humanPlayer.getCards().get(i).isMarked())
                {
                    handCardsImageViews.get(i).setForeground(getDrawable(R.drawable.marked));
                }
                else
                {
                    handCardsImageViews.get(i).setForeground(null);
                }
            }
            else {
                handCardsImageViews.get(i).setVisibility(View.GONE);
            }

        }


    }

    //belegt die ImageViews in der Mitte mit Karten
    public static void set_card_imageView_middleCards(List<Card> combination){

        if(combination == null){
            for (ImageView iv:middleCardsImageViews){
                iv.setVisibility(View.GONE);
            }
            return;
        }

        for(int i = 0;i < middleCardsImageViews.size();i++){

            if(i <= combination.size()-1){
                middleCardsImageViews.get(i).setImageResource(combination.get(i).getResourceId());
                middleCardsImageViews.get(i).setVisibility(View.VISIBLE);

            }
            else
                middleCardsImageViews.get(i).setVisibility(View.GONE);


        }
    }

    //wird bei Klick auf eine Karte ausgelöst: triggert die Methode markCard in humanPlayer
    public void markCard(View v){
        humanPlayer.markCard(v);
        set_card_imageView();
    }
/*
* Festlegung des ersten Spielers
* */
    private int set_firstPlayer(){
        //Wenn Spieler Arschloch in der Runde zuvor war beginnt er das Spiel. Wenn es die erste Runde ist entscheidet der Zufall
        if(humanPlayer.isArschloch()){
            return 1;}
        else if(opponentPlayer1.isArschloch())
            return  2;
        else if(opponentPlayer2.isArschloch())
            return  3;
        else if(opponentPlayer3.isArschloch())
            return  4;
        else
            return (int)(Math.random()*4)+1;
    }


    private void checkRoundOver(){
        int i = 0;
        int a = 0;
        if(humanPlayer.getCards().size() == 0){
            i++;
            if(!checkSomeoneIsWinner())
                humanPlayer.setWinner(true);
        }
        else{
            a=1;
        }
        if(opponentPlayer1.getCards().size() == 0){
            i++;
            if(!checkSomeoneIsWinner())
                opponentPlayer1.setWinner(true);
        }
        else{
            a=2;
        }
        if(opponentPlayer2.getCards().size() == 0){
            i++;
            if(!checkSomeoneIsWinner())
                opponentPlayer2.setWinner(true);
        }
        else{
            a=3;
        }
        if(opponentPlayer3.getCards().size() == 0){
            i++;
            if(!checkSomeoneIsWinner())
                opponentPlayer3.setWinner(true);
        }
        else{
            a=4;
        }

        if(i>=3){
            //Runde vorbei
            switch (a){
                case 1:
                    humanPlayer.setArschloch(true);
                    Toast.makeText(this,"Du bist Arschloch!!",Toast.LENGTH_LONG).show();
                    MediaPlayer player = MediaPlayer.create(this,R.raw.arschloch);
                    player.start();
                    player.reset();
                    player.release();

                    break;
                case 2:
                    opponentPlayer1.setArschloch(true);
                    break;
                case 3:
                    opponentPlayer2.setArschloch(true);
                    break;
                case 4:
                    opponentPlayer3.setArschloch(true);
                    break;
            }

            resetRound();
        }
    }

    private Player getWinner(){
        if(humanPlayer.isWinner()){
            amountRoundsWon++;
            amountRoundsStatistic++;
            return humanPlayer;}
        else if(opponentPlayer1.isWinner()){
            amountRoundsStatistic++;
            return opponentPlayer1;}
        else if(opponentPlayer2.isWinner()){
            amountRoundsStatistic++;
            return opponentPlayer2;}
        else if(opponentPlayer3.isWinner()){
            amountRoundsStatistic++;
            return opponentPlayer3;}
        else
            return null;
    }

    private Player getArschloch(){
        if(humanPlayer.isArschloch()){
            amountGamesLost++;
            return humanPlayer;}
        else if(opponentPlayer1.isArschloch())
            return opponentPlayer1;
        else if(opponentPlayer2.isArschloch())
            return opponentPlayer2;
        else if(opponentPlayer3.isArschloch())
            return opponentPlayer3;
        else
            return null;
    }

    private boolean checkSomeoneIsWinner(){
        if(humanPlayer.isWinner())
            return true;
        else if(opponentPlayer1.isWinner())
            return true;
        else if(opponentPlayer2.isWinner())
            return true;
        else if(opponentPlayer3.isWinner())
            return true;
        else
            return false;
    }

    //Nach Ende der Runde wird alles zurückgesetzt. Wird auch vor der 1. Runde aufgerufen
    private void resetRound(){
        updateStatistics();
        cards_distributing();
        set_card_imageView();
        set_card_imageView_middleCards(null);
        amountSkipped = 0;
        amountCardsPlayed = 0;
        cardValuePlayed = null;
        playersTurn = set_firstPlayer();

        if(amountRounds != 1){
            //Drücken
            if(getWinner() == humanPlayer){

            }

        }

        humanPlayer.setArschloch(false);
        humanPlayer.setWinner(false);
        opponentPlayer1.setArschloch(false);
        opponentPlayer1.setWinner(false);
        opponentPlayer2.setArschloch(false);
        opponentPlayer2.setWinner(false);
        opponentPlayer3.setArschloch(false);
        opponentPlayer3.setWinner(false);
        TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
        TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
        TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
        TVCardsPlayedBy.setText("");

        //Je nachdem wer zuerst dran ist

        //Wenn Opponent Player 1 zuerst dran ist
        if(playersTurn == 2) {
            Toast.makeText(this,"Opponent Player 1 begins",Toast.LENGTH_LONG).show();
            try {
                if (opponentPlayer1.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer1 played:");
                    amountSkipped = 0;
                    TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
                } else
                    amountSkipped++;
                //Thread.sleep(5000);
                if (opponentPlayer2.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer2 played:");
                    amountSkipped = 0;
                    TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                } else
                    amountSkipped++;
                //Thread.sleep(500);
                if (opponentPlayer3.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                    amountSkipped = 0;
                    TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                } else
                    amountSkipped++;
            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        //Wenn Opponent Player 2 zuerst dran ist
        else if(playersTurn == 3){
            Toast.makeText(this,"Opponent Player 2 begins",Toast.LENGTH_LONG).show();
            try {
                if (opponentPlayer2.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer2 played:");
                    amountSkipped = 0;
                    TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                } else
                    amountSkipped++;
                //Thread.sleep(500);
                if (opponentPlayer3.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                    amountSkipped = 0;
                    TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                } else
                    amountSkipped++;
            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

        //Wenn Opponent Player 3 zuerst dran ist
        else if(playersTurn == 4){
            Toast.makeText(this,"Opponent Player 3 begins",Toast.LENGTH_LONG).show();
            try {
                if (opponentPlayer3.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                    amountSkipped = 0;
                    TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                } else
                    amountSkipped++;
            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

    //Methode die dafür sorgt, dass die Runde zuende gespielt wird wenn humanPlayer keine Karten mehr hat
    public void finishing_Gameloop(){

            while(getAmountPlayersInGame() > 1){

                System.out.println("Log: Finishing Game Loop Initiated");
                checkSkipAmount(getAmountPlayersInGame());
                try {
                    if (opponentPlayer1.getCards().size() > 0) {
                        if (opponentPlayer1.play_card()) {
                            amountSkipped = 0;
                            TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
                            System.out.println("Log: Player 1 played Card");
                        } else
                            amountSkipped++;
                    }

                    checkSkipAmount(getAmountPlayersInGame());
                    if (opponentPlayer2.getCards().size() > 0) {
                        if (opponentPlayer2.play_card()) {
                            amountSkipped = 0;
                            TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                            System.out.println("Log: Player 2 played Card");
                        } else
                            amountSkipped++;
                    }
                    checkSkipAmount(getAmountPlayersInGame());
                    if (opponentPlayer3.getCards().size() > 0) {
                        if (opponentPlayer3.play_card()) {
                            amountSkipped = 0;
                            TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                            System.out.println("Log: Player 3 played Card");
                        } else
                            amountSkipped++;
                    }
                }catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

    }

    public void updateStatistics(){

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        for (int j = 1; j<6;j++) {
            Statistic statistics = db.getStatistic(j);
            int zwischenspeicher = Integer.parseInt(statistics.getStatisticNumber());

            if (j == 1){
                zwischenspeicher=zwischenspeicher+amountRoundsStatistic;
                statistics.setStatisticNumber(String.valueOf(zwischenspeicher));
                db.updateStatistics(statistics);
            }else if (j == 2){
                zwischenspeicher=zwischenspeicher+amountRoundsWon;
                statistics.setStatisticNumber(String.valueOf(zwischenspeicher));
                db.updateStatistics(statistics);
            }else if (j == 3){
                int winPercentage = Integer.parseInt(statistics.getStatisticNumber());
                Statistic help= db.getStatistic(1);
                int helpAmountTurns = Integer.parseInt(help.getStatisticNumber());
                Statistic help2= db.getStatistic(2);
                int helpWonTurns = Integer.parseInt(help.getStatisticNumber());
                if(helpAmountTurns == 0){
                    helpAmountTurns = 1;
                }else {
                    winPercentage = helpWonTurns/helpAmountTurns+1;
                }
                statistics.setStatisticNumber(String.valueOf(winPercentage));
                db.updateStatistics(statistics);
            }else if (j == 4){
                zwischenspeicher=zwischenspeicher+amountTurnsPlayed;
                statistics.setStatisticNumber(String.valueOf(zwischenspeicher));
                db.updateStatistics(statistics);
            }else if (j == 5){
                zwischenspeicher=zwischenspeicher+amountGamesLost;
                statistics.setStatisticNumber(String.valueOf(zwischenspeicher));
                db.updateStatistics(statistics);
            }

        }
    }




}
