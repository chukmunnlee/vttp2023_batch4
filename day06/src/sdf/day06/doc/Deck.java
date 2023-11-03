package sdf.day06.doc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck {

   private List<Card> deck;

   public Deck() {
      // Create the deck
      deck = new ArrayList<>();
      for (String suit: Constants.SUITS) {
         for (int i = 0; i < Constants.NAMES.length; i++) {
            // suit, name, value
            Card card = new Card(suit, Constants.NAMES[i], Constants.VALUES[i]);
            deck.add(card);
         }
      }
   }

   public void shuffle() {

   }

   public Card take() {
      List<Card> cards = take(1);
      return cards.get(0);

   }

   public List<Card> take(int count) {
      List<Card> toReturn = new LinkedList<>();
      int i = 0;
      while (!deck.isEmpty() && (i < count)) {
         toReturn.add(deck.remove(0));
         i++;
      }
      return toReturn;
   }
   
}
