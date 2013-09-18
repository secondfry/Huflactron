package CardsGame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: i8.09.13 21:34
 */
public class Deck implements ICardHolder {

	static final ArrayList<Card> cards = new ArrayList<Card>();
	final ArrayList<Card> onTable = new ArrayList<Card>();

	@Override
	public int getCardsAmount() {
		return cards.size();
	}

	@Override
	public Card getCardAt(int index) {
		return cards.get(index);
	}

	public void takeCards() {
		int i = 0;
		while(i < 4) {
			cards.add(onTable.get(0));
			onTable.remove(0);
			i++;
		}

		shuffle();
	}

	void shuffle() {
		Collections.shuffle(cards);
	}

	Deck() {
		int i = 0;

		while(i < 13) {
			cards.add(new Card(ECardSuits.CLUBS, i));
			cards.add(new Card(ECardSuits.DIAMONDS, i));
			cards.add(new Card(ECardSuits.HEARTS, i));
			cards.add(new Card(ECardSuits.SPADES, i));
			i++;
		}

		shuffle();
	}

}
