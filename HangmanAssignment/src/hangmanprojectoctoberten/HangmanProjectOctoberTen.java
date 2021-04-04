//Nivetha Sathish - Hangman Assignment
package hangmanprojectoctoberten;

import java.util.Scanner;

public class HangmanProjectOctoberTen {

    public static void hangmanString(int varCounter) {//where the hangman pictures are
        String hmString = "";
        switch (varCounter) {
            case 0:
                hmString = "------------\n |    |\n |    O\n |   -|-\n |    |\n |   ( )\n |\n------------";//picture that prints out when guesses run out
                break;
            case 1:
                hmString = "------------\n |    |\n |    O\n |   -|-\n |    |\n |   (\n |\n------------";//picture that prints out when guesses left is 1
                break;
            case 2:
                hmString = "------------\n |    |\n |    O\n |   -|-\n |    |\n |\n |\n------------";//picture that prints out when guesses left is 2
                break;
            case 3:
                hmString = "------------\n |    |\n |    O\n |   -|-\n |   \n |\n |\n------------";//picture that prints out when guesses left is 3
                break;
            case 4:
                hmString = "------------\n |    |\n |    O\n |   -|\n |   \n |\n |\n------------";//picture that prints out when guesses left is 4
                break;
            case 5:
                hmString = "------------\n |    |\n |    O\n |    |\n |\n |\n |\n------------";//picture that prints out when guesses left is 5
                break;
            case 6:
                hmString = "------------\n |    |\n |    O\n |\n |\n |\n |\n------------";//picture that prints out when guesses left is 6
                break;
            case 7:
                hmString = "------------\n |    |\n |\n |\n |\n |\n |\n------------";//picture that prints out when guesses left is 7
                break;
            case 8:
                hmString = "------------\n |\n |\n |\n |\n |\n |\n------------";//picture that prints out when guesses left is 8
                break;
            default:
                hmString = "Error";
                break;
        }
        System.out.println(hmString);//in order to print out the hangman picture
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        char playDecision = 'y';//set play decision to y to start
        while (playDecision == 'y') {//while the play decision is y, run this loop
            System.out.println("************************************************************** ");//to make the beginning of a new game clear
            System.out.println("************************************************************** ");
            System.out.println("Welcome to Hangman!");
            System.out.print("Player 1, Enter Your Word: ");
            String secretWord;//player 1's word
            secretWord = keyboard.next();//let player 1 type in the word

            for (int spaces = 1; spaces <= 15; spaces++) {//for loop to generate spaces in order to hide the secret word immediately after player 1 has typed it
                System.out.println(" ");
            }

            int numDashes = secretWord.length();
            numDashes = numDashes - (secretWord.length() - 1);//set number of dashes to the number of letters in the secret word

            int numGuesses = 8;//player 2 has 8 guesses left
            String dashVersion;//make a string named dashVersion to store the dash version of the secret word

            int foundPos = 0;//start looking for the guessed letter at the beginning of the secret word
            int letterPos = 0;//start looking for the guessed letter at the beginning of the alphabet
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            System.out.println("Okay Player 2, Guess Letters Until the Dashes Turn Into a Word");
            System.out.println(alphabet);//print out the alphabet string
            System.out.println(" ");
            dashVersion = "";

            while (numDashes <= secretWord.length()) {//generate the dash version of the word
                dashVersion = (dashVersion + "-");
                numDashes = numDashes + 1;
            }

            System.out.println(dashVersion);//makes the first print of the dash word
            hangmanString(numGuesses);//call the hangman picture section to print out the appropriate hangman picture for the beginning of the game

            //REPEATING PART OF GAME STARTS HERE
            while ((!secretWord.equals(dashVersion)) && (numGuesses != 0)) {//while the secret word does not equal the dash version
                System.out.println(" ");
                System.out.print("Player 2, Enter Your Guess Here: ");
                char guessedLetter;//store the guess
                guessedLetter = keyboard.next().charAt(0);//let player 2 enter the guess
                foundPos = 0;//start off with foundPos being 0 to start looking at the beginning of the word
                foundPos = secretWord.indexOf(guessedLetter, 0);//search for guessed letter in the secret word
                boolean goodGuess = false;//to see if the guess is in the word or not

                while (foundPos >= 0) {//if the guessed letter is found in the secret word
                    goodGuess = true;//guessed letter is in the word
                    dashVersion = dashVersion.substring(0, foundPos) + guessedLetter + dashVersion.substring(foundPos + 1);//update dash version of word      
                    foundPos = secretWord.indexOf(guessedLetter, foundPos + 1);//add one to keep looking
                }

                letterPos = alphabet.indexOf(guessedLetter, 0);//search for guessed letter in the alphabet
                if (letterPos <= -1) {//if the letter is not in the alphabet string (if it has already been guessed)
                    System.out.println(alphabet);//print out the updated alphabet
                    System.out.println("You Have Already Guessed the Letter " + guessedLetter);//tell player 2 that he/she has already guessed that letter
                }
                if (letterPos >= 0) {//if the letter is in the alphabet string (if it has not been guessed yet)
                    alphabet = alphabet.substring(0, letterPos) + "/" + alphabet.substring(letterPos + 1);//update alphabet
                }
                System.out.println(dashVersion);//print out the updated dash version of the word
                System.out.println(alphabet);//print out the updated alphabet

                if (goodGuess == false) {//print out the updated dash version of the word
                    numGuesses = numGuesses - 1;//subtract a guess
                    System.out.println("That letter is not in the secret word. You have " + numGuesses + " guesses left!");
                    System.out.println(dashVersion);
                }

                //Appropriate Hangman Picture is Printed Below
                System.out.println("");
                hangmanString(numGuesses);         

                // CODE FOR THE END OF THE GAME (IF WORD IS GUESSED OR IF NUMGUESSES=0) IS HERE
                if (numGuesses == 0) {//if player 2 runs out of guesses
                    System.out.println("Hangman! Player 1, You Win!");//player 1 wins

                }
                if ((secretWord.equals(dashVersion)) && (numGuesses >= 0)) {//if player 2 guesses the word and he/she has more than 0 guesses left
                    System.out.println("The Governor Called! You’re Saved! Player 2, You Win!");

                }
            }
            //Ask Players If They Wish to Play Again
            System.out.print("Would You Like to Play Again? Enter y to Play Again and n to Stop Playing: ");
            playDecision = keyboard.next().charAt(0);//let the play decision be the user's entry, if the user decides to play again, the game will restart, if not the commands below will execute
            if (playDecision == 'n') {//if the user decides not to play
                System.out.print("Thank You For Playing!");//End of game
            }
        }
    }

}
