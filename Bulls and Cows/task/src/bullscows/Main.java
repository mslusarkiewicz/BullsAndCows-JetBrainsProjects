package bullscows;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int codeLength = 0;
        try {
            codeLength = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: It isn't a valid number.");
        }
        if(codeLength > 36) {
            System.out.println("Error: maximum length of code is 36");
        }
        else if(codeLength < 1) {
                System.out.println("Error: minimum length of code is 1");
        }else{


        System.out.println("Input the number of possible symbols in the code:");
            int symbolsNumber = 0;
            try {
                symbolsNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: It isn't a valid number.");
            }
            if (symbolsNumber > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        }else if ((codeLength > symbolsNumber)) {
                System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
            }
        else if(symbolsNumber < 1) {
                    System.out.println("Error: minimum number of possible symbols in the code is 1 (0-1).");
        } else {


            int[] secretCodeIntArray = new int[codeLength];
            StringBuilder sb = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < codeLength; i++) {


                secretCodeIntArray[i] = random.nextInt(symbolsNumber);


                char temp = Character.forDigit(secretCodeIntArray[i], symbolsNumber);

                String temp2 = Character.toString(temp);

                if (sb.toString().contains(temp2)) {
                    i--;
                } else {
                    sb = sb.append(temp2);
                }

            }

            System.out.println(sb.toString());
            String secretCode = sb.toString();

            String[] secretCodeArray = secretCode.split("");
            int cows = 0;
            int bulls = 0;
            int turnCounter = 0;
            Scanner scanner2 = new Scanner(System.in);

            String[] stars = new String[codeLength];
            Arrays.fill(stars, "*");
            System.out.print("The secret code is prepared: ");
            Arrays.stream(stars).forEach(System.out::print);
            if (symbolsNumber < 10) {
                System.out.println(" (0-" + symbolsNumber + ").");
            } else {
                System.out.println(" (0-9, a-" + Character.forDigit(symbolsNumber - 1, symbolsNumber) + ").");
            }
            System.out.println("Okay, let's start a game!");
            do {
                System.out.println("Turn " + turnCounter + ":");
                turnCounter++;
                String turnOne = scanner2.nextLine();
                String[] turnOneArray = turnOne.split("");
                for (int i = 0; i < turnOne.length(); i++) {
                    if (secretCode.contains(turnOneArray[i])) {
                        cows++;
                    }
                    if (turnOneArray[i].equals(secretCodeArray[i])) {
                        bulls++;
                        cows--;
                    }

                }

                if (bulls == codeLength) {
                    System.out.println("Grade: " + bulls + " bulls. Congratulations! You guessed the secret code " + secretCode + ".");
                } else if (cows > 1 && bulls > 1) {
                    System.out.println("Grade: " + bulls + " bulls and " + cows + " cows.");
                    bulls = 0;
                    cows = 0;
                } else if ((cows > 1 && bulls == 0)) {
                    System.out.println("Grade: " + cows + " cows.");
                    bulls = 0;
                    cows = 0;
                } else if ((cows > 1 && bulls == 1)) {
                    System.out.println("Grade: " + bulls + " bull and " + cows + " cows.");
                    bulls = 0;
                    cows = 0;
                } else if ((cows == 0 && bulls > 1)) {
                    System.out.println("Grade: " + bulls + " bulls.");
                    bulls = 0;
                    cows = 0;
                } else if ((cows == 1 && bulls > 1)) {
                    System.out.println("Grade: " + bulls + " bulls and " + cows + " cow.");
                    bulls = 0;
                    cows = 0;
                } else if ((cows == 1 && bulls == 1)) {
                    System.out.println("Grade: " + bulls + " bull and " + cows + " cow.");
                    bulls = 0;
                    cows = 0;
                } else if ((cows == 1 && bulls == 0)) {
                    System.out.println("Grade: " + cows + " cow.");
                    bulls = 0;
                    cows = 0;
                } else if ((cows == 0 && bulls == 1)) {
                    System.out.println("Grade: " + bulls + " bull.");
                    bulls = 0;
                    cows = 0;
                } else {
                    System.out.println("Grade: None.");

                }
            } while (!(bulls == codeLength));
        }

        }
    }
}
