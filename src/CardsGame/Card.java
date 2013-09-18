package CardsGame;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 21:10
 */
public class Card implements Comparable<Card> {

	final ECardSuits suit;
	private final int value;
	String ownage;

	private static final String[] cardNames = {
		"Ace",
		"Two",
		"Three",
		"Four",
		"Five",
		"Six",
		"Seven",
		"Eight",
		"Nine",
		"Ten",
		"Jack",
		"Queen",
		"King"
	};

	private static final String[] cardSuits = {
		"Clubs",
		"Diamonds",
		"Hearts",
		"Spades"
	};


	Card(ECardSuits suit, int value) {
		this.suit = suit;
		this.value = value;
		this.ownage = "";
	}

//	Card() {
//		Random myRandom = new Random();
//
//		switch(myRandom.nextInt(4)) {
//			case 0:
//				this.suit = ECardSuits.CLUBS;
//				break;
//			case 1:
//				this.suit = ECardSuits.DIAMONDS;
//				break;
//			case 2:
//				this.suit = ECardSuits.HEARTS;
//				break;
//			case 3:
//				this.suit = ECardSuits.SPADES;
//				break;
//		}
//
//		this.value = myRandom.nextInt(14);
//	}

	public String getTextOfCard() {
		return cardNames[value] + " of " + cardSuits[ECardSuits.valueOf(suit.toString()).ordinal()];
	}

	public int compareTo(Card next) {

		return next.value - this.value;

	}

}
