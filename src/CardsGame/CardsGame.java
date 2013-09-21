package CardsGame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 21:47
 */
public class CardsGame {

	private static final String PCNames[] = {"Second_Fry", "Steve", "Alaneee"};
	private static final String NPCNames[] = {"Crazy", "Wicked", "Normal"};
	private static final ArrayList<abstractPlayer> players = new ArrayList<>();
//	private static final int NUMBER_OF_PLAYERS = 6;

	static final Deck myDeck = new Deck();
	private static int roundID = 1;
	static boolean doEndGame = false;

	private static void output(String toOutput) {
		if (toOutput != null)
			System.out.println(toOutput);
	}

	private static void outputScores() {
		for (abstractPlayer player : players) {
			output(player.getScore());
		}
	}

	private static void allTakeCards() {
		int cardAmount;

		if (roundID == 1)
			cardAmount = 5;
		else
			cardAmount = 1;

		for (abstractPlayer player : players) {
			player.takeCard(cardAmount);
		}
	}

	private static void makeStep(abstractPlayer nowPlaying) {
		output("----------------");
		output(nowPlaying.getCardsText());
		nowPlaying.giveCardTo();
	}

	private static abstractPlayer checkWin() {
		abstractPlayer whoWon;
		ArrayList<Card> temp = new ArrayList<>();

		temp.add(myDeck.onTable.get(0));
		for (int i = 1; i < 4; i++) {
			if (myDeck.onTable.get(i).suit == myDeck.onTable.get(0).suit)
				temp.add(myDeck.onTable.get(i));
		}

		Collections.sort(temp);

		whoWon = temp.get(0).owner;
		whoWon.score++;

		temp.clear();

		output("----------------");
		output(whoWon.getName() + " won the round!");

		return whoWon;
	}

	private static abstractPlayer startRound(abstractPlayer whoWon) {
		abstractPlayer nowPlaying;

		if (PCNames.length > 1) {
			output("----------------");
			output("This is hotseat game so be kind to others!");
		}

		output("----------------");
		output("Round number " + roundID);
		outputScores();

		allTakeCards();

		nowPlaying = whoWon;
		do {
			makeStep(nowPlaying);

			if (doEndGame)
				return nowPlaying;

			nowPlaying = nowPlaying.nextPlayer;
		} while (nowPlaying != whoWon);

		whoWon = checkWin();

		myDeck.takeCards();

		return whoWon;
	}

	public static void main(String[] args) {
		boolean notFirst = false;
		int i = 0;

		// Init PCs
		for (String playerName : PCNames) {
			players.add(new ControlledPlayer(playerName));
		}

		// Init NPCs
		for (String playerName : NPCNames) {
			players.add(new BotPlayer(playerName));
		}

		// Link all of them
		while (i < players.size()) {
			if (notFirst)
				players.get(i - 1).nextPlayer = players.get(i);
			else
				notFirst = true;

			i++;
		}
		players.get(players.size() - 1).nextPlayer = players.get(0);

		// Set who is first
		abstractPlayer whoWon = players.get(0);

		// Start the game :)
		do {
			whoWon = startRound(whoWon);
			roundID++;
		} while (!doEndGame);

		// Output who stopped the game
		output("");
		output(whoWon.name + " decided to stop the game.");
	}

}
