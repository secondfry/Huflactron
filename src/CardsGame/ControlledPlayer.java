package CardsGame;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 22:15
 */
public class ControlledPlayer extends abstractPlayer {

	private static void output(boolean doEndLine, String toOutput) {
		if(doEndLine)
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

		output(true, "It's your time to play, please pick a card.");
		output(false, "> ");

		do {
			try {
				choise = myScanner.nextInt();
				choise--;
				goodToGo = choise > -1 && choise < 5;
			} catch (InputMismatchException exception) {
				goodToGo = false;
			}
		} while(!goodToGo);

		output(true, "You played " + getCardAt(choise).getTextOfCard());

		myDeck.onTable.add(getCardAt(choise));
		cards.remove(choise);

	}

	@Override
	public String getScore() {
		return "Player has " + score + " points";
	}

	@Override
	public void takeCard() {
		Deck myDeck = CardsGame.myDeck;

		myDeck.getCardAt(0).ownage = "Player";
		cards.add(myDeck.getCardAt(0));
		Deck.cards.remove(0);
	}
}
