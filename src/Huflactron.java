/**
 * Created with IntelliJ IDEA.
 * User: Rustam Second_Fry Gubaydullin
 * Date: 18.09.13 3:57
 */
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Huflactron {

    public static void output(String toOutput) {
        System.out.println(toOutput);
    }

    public static void lecture1() {
        Scanner myScanner;
        String firstInput;
        String secondInput;
        int firstInt;

        // Simple output
        System.out.println("Hello, Huflactron!");
        System.out.println("Enter string and integer, man.");

        //Simple input
        myScanner = new Scanner(System.in);
        firstInput = myScanner.next();
        secondInput = myScanner.next();
        try {
            firstInt = Integer.parseInt(secondInput);
        } catch (java.lang.NumberFormatException exception) {
            output("Dont try to fool me!");
            firstInt = 0;
        }

        // Output simple input
        System.out.println(firstInput + " : " + firstInt);
    }

    public static boolean lecture2_check(int toCheck) {
        return toCheck > 0 && toCheck < 14;
    }

    public static void lecture2() {
        int a1, a2, a3, operation, a4;
        boolean q1, q2, q3, x1, x2, x3;
        Scanner myScanner;
        Random myRandom;

        // Comment to user
        System.out.println("Hello, Huflactron!\nChoose 3 cards by it integer value (from 1 to 13)");

        // Read input
        myScanner = new Scanner(System.in);
        try {
            a1 = myScanner.nextInt();
            a2 = myScanner.nextInt();
            a3 = myScanner.nextInt();
        } catch (java.util.InputMismatchException exception) {
            output("Dont try to fool me!");
            a1 = 1;
            a2 = 2;
            a3 = 3;
        }

        // Check if cards actually
        if(!lecture2_check(a1)) {
            output("Dont try to fool me!");
            a1 = 1;
        }
        if(!lecture2_check(a2)) {
            output("Dont try to fool me!");
            a2 = 2;
        }
        if(!lecture2_check(a3)) {
            output("Dont try to fool me!");
            a3 = 3;
        }

        // Math is saving our day
        output("BTW maximum is: " + Math.max(Math.max(a1, a2), a3));

        // XOR
        x1 = true;
        x2 = false;
        x3 = (x1 && !x2) || (!x1 && x2);
        output("Sick: " + x3);

        // Check pairs
        q1 = a1 == a2;
        q2 = a2 == a3;
        q3 = a3 == a1;
        if(q1 && q2) {
            output("You got three-of-a-kind " + a1 + ", lal!");
        } else if(q1) {
            output("You got pair " + a1 + ", lal!");
        } else if(q2) {
            output("You got pair " + a2 + ", lal!");
        } else if(q3) {
            output("You got pair " + a3 + ", lal!");
        } else {
            output("Dont play anymore.");
        }

        // Now math
        output("We need to check if you are adult. Solve this:");
        myRandom = new Random();
        operation = myRandom.nextInt(5);
        a1 = myRandom.nextInt(10);
        a2 = myRandom.nextInt(10);
        switch(operation) {
            case 0:
                output(a1 + " + " + a2 + " ? ");
                a3 = a1 + a2;
                break;
            case 1:
                output(a1 + " - " + a2 + " ? ");
                a3 = a1 - a2;
                break;
            case 2:
                output(a1 + " * " + a2 + " ? ");
                a3 = a1 * a2;
                break;
            case 3:
                while(a2 == 0) {
                    a2 = myRandom.nextInt(10);
                }
                output(a1 + " / " + a2 + " ? ");
                a3 = a1 / a2;
                break;
            case 4:
                output(a1 + " % " + a2 + " ? ");
                a3 = a1 % a2;
                break;
        }

        try {
            a4 = myScanner.nextInt();
            if(a3 == a4) {
                output("Good job! You are adult.");
            } else {
                output("Dumb? Result is " + a3 + " and not " + a4);
            }
        } catch (java.util.InputMismatchException exception) {
            output("Dont try to fool me! And go away.");
        }
    }

    public static void lecture3_part1() {
        int i = 0;
        Scanner myScanner = new Scanner(System.in);
        int[] intArray = new int[10];

        output("Input 10 integers.");
        while(i < 10) {
            intArray[i] = myScanner.nextInt();
            i++;
        }

        Arrays.sort(intArray);

        output("Sick array:");
        for(int a : intArray) {
            output("" + a);
        }
    }

    public static void lecture3_part2(String[] args) {
        String name = "", pass = "";
        boolean doName = false, doPass = false;
        Scanner myScanner = new Scanner(System.in);

        for(String arg : args) {
            if(doName) {
                name = arg;
                doName = false;
            }
            if(doPass) {
                pass = arg;
                doPass = false;
            }
            if(arg.equals("name")) {
                doName = true;
            }
            if(arg.equals("pass")) {
                doPass = true;
            }
        }

        if(name.length() == 0) {
            output("Enter username:");
            name = myScanner.next();
        }

        if(pass.length() == 0) {
            output("Enter password:");
            pass = myScanner.next();
        }

        output(name + ":" + pass);
    }

    public static void lecture3(String[] args) {
//        lecture3_part1();
        lecture3_part2(args);
    }

    public static void main(String[] args) {
//        lecture1();
//        lecture2();
//        lecture3(args);
    }
}
