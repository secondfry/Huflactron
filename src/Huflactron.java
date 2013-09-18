/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 3:57
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Huflactron {

	private static final String[][] actions = {
			{"addItem",
			"Add an item to the shopping basket"},
			{"viewBasket",
			"View the content of the shopping basket"},
			{"pay",
			"Pay and exit the shop"},
			{"back",
			"Go back"},
			{"removeItem",
			"Remove item"}
	};

	private static final int SIZE = 5;
	private static final String[] itemNames = {"Broken computer", "Cookie", "Unknown video game", "Modern art", "Useless button"};
	private static final int[] itemPrices = {40, 3, 25, 80, 10};

	// Basket class
	static public class Basket {

		// Items in basket
		final int[][] items = new int[SIZE][2];

		// Proper constructor
		Basket() {
			int i = 0;
			while(i < SIZE) {
				items[i][0] = i;
				items[i][1] = 0;
				i++;
			}
		}

		// Count items
		public int size() {
			int i = 0, size = 0;
			while(i < SIZE) {
				size += items[i][1];
				i++;
			}
			return size;
		}

		// Count price
		public int price() {
			int i = 0, price = 0;
			while(i < SIZE) {
				price += items[i][1] * itemPrices[i];
				i++;
			}
			return price;
		}

		// Export items
		// Returns true if items exist, false - if not
		public boolean viewItems() {
			int i = 0;
			if(this.size() == 0) {
				output(true, "Your shopping basket is empty.");
				return false;
			}
			else {
				while(i < SIZE) {
					if(items[i][1] != 0)
						output(true, itemNames[i] + " [" + items[i][1] + "]");
					i++;
				}
				return true;
			}
		}

		// Adds item to basket
		public void addItem(int itemID) {
			int amount = 0;
			boolean goodToGo;
			Scanner myScanner = new Scanner(System.in);

			do {
				output(true, itemNames[itemID] + " costs $" + itemPrices[itemID] + ". How many would you like?");
				output(false, "> ");
				try {
					amount = myScanner.nextInt();

					// Check if entered value is asked
					goodToGo = amount > 0;
				} catch (InputMismatchException exception) {
					goodToGo = false;
				}
			} while (!goodToGo);

			items[itemID][1] += amount;
		}

		// Remove item from basket
		public void removeItem(int itemID) {
			int amount = 0;
			boolean goodToGo;
			Scanner myScanner = new Scanner(System.in);

			do {
				output(true, "How many? [" + items[itemID][1] + "]");
				output(false, "> ");
				try {
					amount = myScanner.nextInt();

					// Check if entered value is asked
					goodToGo = amount > 0 && amount <= items[itemID][1];
				} catch (InputMismatchException exception) {
					goodToGo = false;
				}
			} while (!goodToGo);

			items[itemID][1] -= amount;
		}
	}

	private static final Basket myBasket = new Basket();
	private static boolean canExit = false;

	// Output helper function
	private static void output(boolean doEndLine, String toOutput) {
		if(doEndLine)
			System.out.println(toOutput);
		else
			System.out.print(toOutput);
	}

	// Checking how much items inside basket
	private static void checkBasket() {
		output(true, "Welcome customer. You currently have " + myBasket.size() + " items in your shopping basket.");
	}

	// Returns text for action code
	private static String getActionText(String action) {
		for(String[] actionBlock : actions) {
			if(actionBlock[0].equals(action))
				return actionBlock[1];
		}
		return "Dunno";
	}

	// Asks what to do
	private static int askAction(String... actionsOnLevel) {
		int i, choise = 0;
		boolean goodToGo = false;
		Scanner myScanner = new Scanner(System.in);

		// Loop for derpy input
		do {
			output(true, "What would you like to do?");

			i = 0;
			for(String action : actionsOnLevel) {
				output(true, i+1 + ". " + getActionText(action));
				i++;
			}

			output(false, "> ");
			try {
				choise = myScanner.nextInt();

				// Human start from 1 and developer start from 0
				choise--;

				// Check if entered value is asked
				if(choise > -1 && choise < actionsOnLevel.length)
					goodToGo = true;
			} catch (InputMismatchException exception) {
				goodToGo = false;
			}
		} while (!goodToGo);

//		There is no switch over String in Java? T_T
//		return actions[choise][0];
		return choise;
	}

	// Main function
	public static void main(String[] args) {
		int choise;
		do {
			output(true, "");
			checkBasket();
			choise = askAction("addItem","viewBasket","pay");
			switch(choise) {
//				case "addItem":
				case 0:
					output(true, "");
					addItem();
					break;
//				case "viewBasket":
				case 1:
					viewBasket();
					break;
				case 2:
					output(true, "");
					pay();
					canExit = true;
					break;
			}
		} while (!canExit);
	}

	// Adds item to basket
	private static void addItem() {
		int i, choise = -1;
		boolean goodToGo = false;
		Scanner myScanner = new Scanner(System.in);

		// Loop for derpy input
		do {
			i = 0;
			output(true, "What would you like to add to your shopping basket?");
			for(String name : itemNames) {
				output(true, i+1 + ". " + name + " $" + itemPrices[i]);
				i++;
			}
			output(true, i+1 + ". Go back");

			output(false, "> ");
			try {
				choise = myScanner.nextInt();

				// Human start from 1 and developer start from 0
				choise--;

				// Check if entered value is asked
				if(choise > -1 && choise <= SIZE)
					goodToGo = true;
			} catch (InputMismatchException exception) {
				goodToGo = false;
			}
		} while (!goodToGo);

		if(choise != i) {
			output(true, "");
			myBasket.addItem(choise);
		}

	}

	// View basket and choise next action
	private static void viewBasket() {
		int choise;
		boolean isNotEmpty;

		do {
			output(true, "");
			output(true, "Items in shopping basket:");
			isNotEmpty = myBasket.viewItems();
			output(true, "");
			if(isNotEmpty) {
				choise = askAction("removeItem","back");
				switch(choise) {
//					case "removeItem":
					case 0:
						output(true, "");
						removeItem();
						break;
//					case "back":
					case 1:
						return;
				}
			}
			else {
				choise = askAction("back");
				switch(choise) {
//					case "back":
					case 0:
						return;
				}
			}
		} while (true);
	}

	// Removes item from basket
	private static void removeItem() {
		int i, q, choise = -1;
		boolean goodToGo = false;
		Scanner myScanner = new Scanner(System.in);

		// Loop for derpy input
		do {
			i = q = 0;
			output(true, "What item would you like to remove?");
			for(String name : itemNames) {
				if(myBasket.items[i][1] != 0) {
					output(true, q+1 + ". " + name + " $" + itemPrices[i]);
					q++;
				}
				i++;
			}
			output(true, q+1 + ". Go back");

			output(false, "> ");
			try {
				choise = myScanner.nextInt();

				// Human start from 1 and developer start from 0
				choise--;

				// Check if entered value is asked
				if(choise > -1 && choise <= q)
					goodToGo = true;
			} catch (InputMismatchException exception) {
				goodToGo = false;
			}
		} while (!goodToGo);

		if(choise != q) {
			output(true, "");

			i = 0;
			q = 0;
			while(i < SIZE) {
				if(myBasket.items[i][1] != 0) {
					if(choise == q) {
						choise = i;
						break;
                    }
					q++;
				}
				i++;
			}

			myBasket.removeItem(choise);
		}

	}

	// Pay and exit
	private static void pay() {
		while(myBasket.price() > 100) {
			output(true, "Your basket is too expensive - $" + myBasket.price() + ".");
			removeItem();
		}

		int left = 100-myBasket.price();
		output(true, "You payed $" + myBasket.price() + " for your items and therefore have $" + left + " left.");
		output(true, "You bought the following items:");
		myBasket.viewItems();
	}
}
