package CardsGame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 21:47
 */
public class CardsGame {

	static final Deck myDeck = new Deck();
	private static final ControlledPlayer player = new ControlledPlayer();
	private static final BotPlayer bot1 = new BotPlayer(1);
	private static final BotPlayer bot2 = new BotPlayer(2);
	private static final BotPlayer bot3 = new BotPlayer(3);
	private static int roundID = 1;

	private static void output(String toOutput) {
		System.out.println(toOutput);
	}

	private static void startRound() {
		ArrayList<Card> temp = new ArrayList<Card>();

		output("Round number " + roundID + "; cards left " + myDeck.getCardsAmount());
		output(player.getScore());
		output(bot1.getScore());
		output(bot2.getScore());
		output(bot3.getScore());

		if(roundID == 1) {
			for(int i = 0; i < 5; i++) {
				player.takeCard();
				bot1.takeCard();
				bot2.takeCard();
				bot3.takeCard();
			}
		}

		output("");
		output(player.getCardsText());

		output("");
		player.giveCardTo();
		bot1.giveCardTo();
		bot2.giveCardTo();
		bot3.giveCardTo();

		temp.add(myDeck.onTable.get(0));
		for(int i = 1; i < 4; i++) {
			if(myDeck.onTable.get(i).suit == myDeck.onTable.get(0).suit)
				temp.add(myDeck.onTable.get(i));
		}

		Collections.sort(temp);

		output(temp.get(0).getTextOfCard());

		temp.clear();

		myDeck.takeCards();

		player.takeCard();
		bot1.takeCard();
		bot2.takeCard();
		bot3.takeCard();
	}

	public static void main(String[] args) {
		do {
			output("");
			startRound();
			roundID++;
		} while (roundID != 15);
	}

}
