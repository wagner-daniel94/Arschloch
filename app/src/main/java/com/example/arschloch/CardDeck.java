package com.example.arschloch;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private List<Card> cards;

    public CardDeck(){
        cards = new ArrayList<Card>();
        //region Hearts cards
        cards.add(new Card(Card_value.two,Card_symbol.hearts,R.drawable.heartstwo));
        cards.add(new Card(Card_value.three,Card_symbol.hearts,R.drawable.heartsthree));
        cards.add(new Card(Card_value.four,Card_symbol.hearts,R.drawable.heartsfour));
        cards.add(new Card(Card_value.five,Card_symbol.hearts,R.drawable.heartsfive));
        cards.add(new Card(Card_value.six,Card_symbol.hearts,R.drawable.heartssix));
        cards.add(new Card(Card_value.seven,Card_symbol.hearts,R.drawable.heartsseven));
        cards.add(new Card(Card_value.eight,Card_symbol.hearts,R.drawable.heartseight));
        cards.add(new Card(Card_value.nine,Card_symbol.hearts,R.drawable.heartsnine));
        cards.add(new Card(Card_value.ten,Card_symbol.hearts,R.drawable.heartsten));
        cards.add(new Card(Card_value.jack,Card_symbol.hearts,R.drawable.heartsjack));
        cards.add(new Card(Card_value.queen,Card_symbol.hearts,R.drawable.heartsqueen));
        cards.add(new Card(Card_value.king,Card_symbol.hearts,R.drawable.heartsking));
        cards.add(new Card(Card_value.ace,Card_symbol.hearts,R.drawable.heartsace));
        //endregion

        //region Diamonds cards
        cards.add(new Card(Card_value.two,Card_symbol.diamonds,R.drawable.diamondstwo));
        cards.add(new Card(Card_value.three,Card_symbol.diamonds,R.drawable.diamondsthree));
        cards.add(new Card(Card_value.four,Card_symbol.diamonds,R.drawable.diamondsfour));
        cards.add(new Card(Card_value.five,Card_symbol.diamonds,R.drawable.diamondsfive));
        cards.add(new Card(Card_value.six,Card_symbol.diamonds,R.drawable.diamondssix));
        cards.add(new Card(Card_value.seven,Card_symbol.diamonds,R.drawable.diamondsseven));
        cards.add(new Card(Card_value.eight,Card_symbol.diamonds,R.drawable.diamondseight));
        cards.add(new Card(Card_value.nine,Card_symbol.diamonds,R.drawable.diamondsnine));
        cards.add(new Card(Card_value.ten,Card_symbol.diamonds,R.drawable.diamondsten));
        cards.add(new Card(Card_value.jack,Card_symbol.diamonds,R.drawable.diamondsjack));
        cards.add(new Card(Card_value.queen,Card_symbol.diamonds,R.drawable.diamondsqueen));
        cards.add(new Card(Card_value.king,Card_symbol.diamonds,R.drawable.diamondsking));
        cards.add(new Card(Card_value.ace,Card_symbol.diamonds,R.drawable.diamondsace));
        //endregion

        //region Clubs cards
        cards.add(new Card(Card_value.two,Card_symbol.clubs,R.drawable.clubstwo));
        cards.add(new Card(Card_value.three,Card_symbol.clubs,R.drawable.clubsthree));
        cards.add(new Card(Card_value.four,Card_symbol.clubs,R.drawable.clubsfour));
        cards.add(new Card(Card_value.five,Card_symbol.clubs,R.drawable.clubsfive));
        cards.add(new Card(Card_value.six,Card_symbol.clubs,R.drawable.clubssix));
        cards.add(new Card(Card_value.seven,Card_symbol.clubs,R.drawable.clubsseven));
        cards.add(new Card(Card_value.eight,Card_symbol.clubs,R.drawable.clubseight));
        cards.add(new Card(Card_value.nine,Card_symbol.clubs,R.drawable.clubsnine));
        cards.add(new Card(Card_value.ten,Card_symbol.clubs,R.drawable.clubsten));
        cards.add(new Card(Card_value.jack,Card_symbol.clubs,R.drawable.clubsjack));
        cards.add(new Card(Card_value.queen,Card_symbol.clubs,R.drawable.clubsqueen));
        cards.add(new Card(Card_value.king,Card_symbol.clubs,R.drawable.clubsking));
        cards.add(new Card(Card_value.ace,Card_symbol.clubs,R.drawable.clubsace));
        //endregion

        //region Spades cards
        cards.add(new Card(Card_value.two,Card_symbol.spades,R.drawable.spadestwo));
        cards.add(new Card(Card_value.three,Card_symbol.spades,R.drawable.spadesthree));
        cards.add(new Card(Card_value.four,Card_symbol.spades,R.drawable.spadesfour));
        cards.add(new Card(Card_value.five,Card_symbol.spades,R.drawable.spadesfive));
        cards.add(new Card(Card_value.six,Card_symbol.spades,R.drawable.spadessix));
        cards.add(new Card(Card_value.seven,Card_symbol.spades,R.drawable.spadesseven));
        cards.add(new Card(Card_value.eight,Card_symbol.spades,R.drawable.spadeseight));
        cards.add(new Card(Card_value.nine,Card_symbol.spades,R.drawable.spadesnine));
        cards.add(new Card(Card_value.ten,Card_symbol.spades,R.drawable.spadesten));
        cards.add(new Card(Card_value.jack,Card_symbol.spades,R.drawable.spadesjack));
        cards.add(new Card(Card_value.queen,Card_symbol.spades,R.drawable.spadesqueen));
        cards.add(new Card(Card_value.king,Card_symbol.spades,R.drawable.spadesking));
        cards.add(new Card(Card_value.ace,Card_symbol.spades,R.drawable.spadesace));
        //endregion
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
