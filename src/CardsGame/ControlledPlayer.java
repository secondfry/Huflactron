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
			return;
		}

		output(true, "----------------");
		output(true, name + " played " + getCardAt(choise).getTextOfCard());

		myDeck.onTable.add(getCardAt(choise));
		cards.remove(choise);
	}

	@Override
	public String getCardsText() {
		int i = 0;
		String temp = name + " cards are the following";

		while (i < 5) {
			temp += "\n" + (i + 1) + ". " + getCardAt(i++).getTextOfCard();
		}

		return temp;
	}
}
