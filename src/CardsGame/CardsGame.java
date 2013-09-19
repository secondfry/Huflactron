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
	private static final ArrayList<ControlledPlayer> PCs = new ArrayList<ControlledPlayer>();
	private static final String NPCNames[] = {"Crazy", "Wicked", "Normal"};
	private static final ArrayList<BotPlayer> NPCs = new ArrayList<BotPlayer>();
//	private static final int NUMBER_OF_PLAYERS = 6;

	static final Deck myDeck = new Deck();
	private static int roundID = 1;
	static boolean doEndGame = false;

	private static void output(String toOutput) {
		System.out.println(toOutput);
	}

	private static void outputScores() {
		int i = 0;
		while(i < PCs.size()) {
			output(PCs.get(i++).getScore());
		}

		i = 0;
		while(i < NPCs.size()) {
			output(NPCs.get(i++).getScore());
		}
	}

	private static void allTakeCards() {
		int i = 0, q, cardAmount;

		if (roundID == 1)
			cardAmount = 5;
		else
			cardAmount = 1;

		while(i < PCs.size()) {
			q = 0;
			while(q < cardAmount) {
				PCs.get(i).takeCard();
				q++;
			}
			i++;
		}

		i = 0;
		while(i < NPCs.size()) {
			q = 0;
			while(q < cardAmount) {
				NPCs.get(i).takeCard();
				q++;
			}
			i++;
		}
	}

	private static void makeStep(abstractPlayer nowPlaying) {

		output("----------------");

		if(nowPlaying instanceof ControlledPlayer) {
			output(nowPlaying.getCardsText());
		}

		if (doEndGame)
			return;

		nowPlaying.giveCardTo();
	}

	private static abstractPlayer checkWin() {
		abstractPlayer whoWon;
		ArrayList<Card> temp = new ArrayList<Card>();

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

		if(PCs.size() > 1) {
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

		int i = 0;
		for(String playerName : PCNames) {
			PCs.add(new ControlledPlayer(playerName));

			if(i != 0)
				PCs.get(i-1).nextPlayer = PCs.get(i);

			i++;
		}

		i = 0;
		for(String playerName : NPCNames) {
			NPCs.add(new BotPlayer(playerName));

			if(i != 0)
				NPCs.get(i-1).nextPlayer = NPCs.get(i);

			i++;
		}

		PCs.get(PCs.size()-1).nextPlayer = NPCs.get(0);
		NPCs.get(NPCs.size()-1).nextPlayer = PCs.get(0);

		abstractPlayer whoWon = PCs.get(0);

		do {
			whoWon = startRound(whoWon);
			roundID++;
		} while (!doEndGame);

		output("");
		output(whoWon.name + " decided to stop the game.");
	}

}
