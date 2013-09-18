package CardsGame;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 22:25
 */
public class BotPlayer extends abstractPlayer {

	private final int botID;

	BotPlayer(int botID) {
		this.botID = botID;
	}

	private static void output(String toOutput) {
		System.out.println(toOutput);
	}

	@Override
	public void giveCardTo() {
		Deck myDeck = CardsGame.myDeck;

		Random myRandom = new Random();
		int choise = myRandom.nextInt(5);

		output("Bot " + botID + " played " + getCardAt(choise).getTextOfCard());

		myDeck.onTable.add(getCardAt(choise));
		cards.remove(choise);
	}

	@Override
	public String getScore() {
		return "Bot " + botID + " has " + score + " points";
	}

	@Override
	public void takeCard() {
		Deck myDeck = CardsGame.myDeck;

		myDeck.getCardAt(0).ownage = "Bot " + botID;
		cards.add(myDeck.getCardAt(0));
		Deck.cards.remove(0);
	}

}
