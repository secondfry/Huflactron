package CardsGame;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 21:21
 */
public abstract class abstractPlayer implements ICardHolder {

	final ArrayList<Card> cards = new ArrayList<>();
	int score = 0;
	String name;
	abstractPlayer nextPlayer;

	abstractPlayer(String name) {
		this.name = name;
	}

	@Override
	public int getCardsAmount() {
		return cards.size();
	}

	@Override
	public Card getCardAt(int index) {
		return cards.get(index);
	}

	public String getName() {
		return name;
	}

	public String getScore() {
		return getName() + " has " + score + " points";
	}

	public abstract void giveCardTo();

	public void takeCard(int cardAmount) {
		int i = 0;
		Deck myDeck = CardsGame.myDeck;

		while (i < cardAmount) {
			myDeck.getCardAt(0).owner = this;
			cards.add(myDeck.getCardAt(0));
			Deck.cards.remove(0);
			i++;
		}
	}

	public abstract String getCardsText();

}
