import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxAttempts = 7;
        int roundsWon = 0;
        int roundsPlayed = 0;

        System.out.println(" Welcome to the Number Guessing Game!");

        boolean playAgain = true;

        while (playAgain) {
            int targetNumber = random.nextInt(100) + 1;  // 1 to 100
            int attemptsLeft = maxAttempts;
            boolean guessedCorrectly = false;

            System.out.println("\n🔹 Round " + (roundsPlayed + 1) + " begins!");
            System.out.println("I have selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts. Good luck!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                
                // Validate user input
                if (!scanner.hasNextInt()) {
                    System.out.println(" Please enter a valid number.");
                    scanner.next();  // Clear invalid input
                    continue;
                }

                int guess = scanner.nextInt();

                if (guess < 1 || guess > 100) {
                    System.out.println(" Guess must be between 1 and 100.");
                    continue;
                }

                attemptsLeft--;

                if (guess == targetNumber) {
                    System.out.println(" Correct! You've guessed the number!");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("Too high! Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all attempts. The number was: " + targetNumber);
            }

            roundsPlayed++;

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("\n Game Over!");
        System.out.println("Total rounds played: " + roundsPlayed);
        System.out.println("Rounds won: " + roundsWon);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}
