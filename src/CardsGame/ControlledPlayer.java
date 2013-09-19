package CardsGame;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 22:15
 */
public class ControlledPlayer extends abstractPlayer {

	ControlledPlayer(String name) {
		super(name);
	}

	private static void output(boolean doEndLine, String toOutput) {
		if (doEndLine)
			System.out.println(toOutput);
		else
			System.out.print(toOutput);
	}

	@Override
	public void giveCardTo() {
		if (!roundDone) {
			Deck myDeck = CardsGame.myDeck;

			int choise = -1;
			Scanner myScanner = new Scanner(System.in);
			boolean goodToGo;

			do {
				output(true, "It's your time to play, please pick a card. 6: Exit.");
				output(false, "> ");

				try {
					choise = myScanner.nextInt();
					choise--;
					goodToGo = choise > -1 && choise < 6;
				} catch (InputMismatchException exception) {
					goodToGo = false;
				}
			} while (!goodToGo);

			if (choise == 5) {
				CardsGame.doEndGame = true;
				roundDone = true;
				return;
			}

			output(true, "----------------");
			output(true, name + " played " + getCardAt(choise).getTextOfCard());

			myDeck.onTable.add(getCardAt(choise));
			cards.remove(choise);
			roundDone = true;
		}
	}

	@Override
	public void takeCard() {
		Deck myDeck = CardsGame.myDeck;

		myDeck.getCardAt(0).owner = this;
		cards.add(myDeck.getCardAt(0));
		Deck.cards.remove(0);
		roundDone = false;
	}
}
