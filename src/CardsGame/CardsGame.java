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
	static boolean doEndGame = false;

	private static void output(String toOutput) {
		System.out.println(toOutput);
	}

	private static abstractPlayer startRound(abstractPlayer whoFirst) {
		ArrayList<Card> temp = new ArrayList<Card>();

		output("Round number " + roundID);
		output(player.getScore());
		output(bot1.getScore());
		output(bot2.getScore());
		output(bot3.getScore());

		if (roundID == 1) {
			for (int i = 0; i < 5; i++) {
				player.takeCard();
				bot1.takeCard();
				bot2.takeCard();
				bot3.takeCard();
			}
		}

		output("");
		output(player.getCardsText());

		output("");
		whoFirst.giveCardTo();
		player.giveCardTo();
		if (doEndGame)
			return player;
		bot1.giveCardTo();
		bot2.giveCardTo();
		bot3.giveCardTo();

		temp.add(myDeck.onTable.get(0));
		for (int i = 1; i < 4; i++) {
			if (myDeck.onTable.get(i).suit == myDeck.onTable.get(0).suit)
				temp.add(myDeck.onTable.get(i));
		}

		Collections.sort(temp);

		switch (temp.get(0).ownage) {
			case "Player":
				player.score++;
				whoFirst = player;
				break;
			case "Bot 1":
				bot1.score++;
				whoFirst = bot1;
				break;
			case "Bot 2":
				bot2.score++;
				whoFirst = bot2;
				break;
			case "Bot 3":
				bot3.score++;
				whoFirst = bot3;
				break;
		}

		temp.clear();

		myDeck.takeCards();

		player.takeCard();
		bot1.takeCard();
		bot2.takeCard();
		bot3.takeCard();

		return whoFirst;
	}

	public static void main(String[] args) {
		abstractPlayer whoFirst = player;
		do {
			output("");
			whoFirst = startRound(whoFirst);
			roundID++;
		} while (!doEndGame);
	}

}
