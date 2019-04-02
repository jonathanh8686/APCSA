//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Deck {

	public static final int NUMCARDS = 52;
	public static String[] SUITS = "CLUBS HEARTS DIAMONDS SPADES".split(" ");

	private List<Card> cards;
	private int top;

	//make a Deck constructor
	//refer cards to new ArrayList
	//set top to the top of the deck 51

	//loop through all suits
	//loop through all faces 1 to 13

	public Deck(){
		top = 51;
		cards = new ArrayList<Card>();

		for(int i = 0; i < SUITS.length; i++){
			for(int j = 1; j <= 13; j++)
				cards.add(new Card(SUITS[i], j)); // populate cards arraylist
		}
	}

	public Deck(List<Card> c){
		cards = c;
	}


	//add a new TwentyOneCard to the deck


	//make a dealCard() method that returns the top card
	public Card dealCard(){
		return cards.get(top--);
	}

	//write a shuffle() method
	//use Colletions.shuffle
	//reset the top card

	public void shuffle(){
		top = 51;
		Collections.shuffle(cards);
	}

}