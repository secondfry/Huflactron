package CardsGame;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 22:25
 */
public class BotPlayer extends abstractPlayer {

	BotPlayer(String name) {
		super(name);
	}

	private static void output(String toOutput) {
		System.out.println(toOutput);
	}

	@Override
	public void giveCardTo() {
		Deck myDeck = CardsGame.myDeck;

		Random myRandom = new Random();
		int choise = myRandom.nextInt(5);

		output("[B] " + name + " played " + getCardAt(choise).getTextOfCard());

		myDeck.onTable.add(getCardAt(choise));
		cards.remove(choise);
	}

	@Override
	public String getName() {
		return "[B] " + name;
	}

	@Override
	public String getCardsText() {
		return null;
	}

}
