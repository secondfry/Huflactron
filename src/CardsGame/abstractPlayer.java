package CardsGame;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 21:21
 */
public abstract class abstractPlayer implements ICardHolder {

	final ArrayList<Card> cards = new ArrayList<Card>();
	int score = 0;
	boolean roundDone = false;
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

	public abstract void takeCard();

	public String getCardsText() {
		int i = 0;
		String temp = name + " cards are the following";

		while (i < 5) {
			temp += "\n" + (i + 1) + ". " + getCardAt(i++).getTextOfCard();
		}

		return temp;
	}

}
