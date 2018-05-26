package edu.miracosta.cs113;

/**
 * LogicalClue.java : Asking AssistantJack, Detective Jill will be able to
 * solve the murder mystery, getting the correct answer in <= 20 tries.
 * While LogicalClue is ONE solution to the problem, it will consistently
 * guess the correct answer in 20 tries, maximum, and one try, minimum, 
 * with an average of ~10 tries.
 *
 * This is my solution, a driver using three separate IF statements
 * with respect to the implementation. Commenting is provided as
 * necessary with respect to the driver program.
 *
 * Class Invariant: Weapon guess, location guess, and murderer guess
 * are all unchecked due to the logic of the algorithm, e.g., if the
 * true location is the tenth location, the algorithm stops guessing
 * when the tenth location is guessed.
 *
 * @author Reesha Rajen (material from Professor Chapeton-Lamas)
 * @version 1.1
 *
 */

import java.util.Scanner;
import model.Theory;
import model.AssistantJack;

public class LogicalClue {

    private static final int WRONG_WEAPON = 1; //int returned by checkAnswer
    private static final int WRONG_LOCATION = 2; //int returned by checkAnswer
    private static final int WRONG_MURDERER = 3; //int returned by checkAnswer

    private static final int ROPE = 1; //First weapon listed
    private static final int KITCHEN = 1; //First location of Clue map
    private static final int MS_SCARLET = 1; //Typically, the first player in a Clue game

    private static final int INVALID_PLUS_RANGE = 3;
    private static final int INVALID_MINUS_RANGE = 1;
    
    /*
     * ALGORITHM:
     *
     * PROMPT "Which theory to test? (1, 2, 3[random]): "
     * READ answerSet
     *      WHILE answerSet is INVALID
     *          PROMPT "Which theory to test? (1, 2, 3[random]): "
     *
     * INSTANTIATE jack = new AssistantJack(answerSet)
     *      INITIALIZE weapon to first weapon listed
     *      INITIALIZE location to first location listed
     *      INITIALIZE murderer to first murderer listed
     *
     * DO
     *      solution = jack.checkAnswer(weapon, location, murder)
     *          IF solution indicates incorrect weapon
     *              INCREMENT weapon selection
     *              (note: this will never exceed range (1-6))
     *          IF solution indicates incorrect location
     *              INCREMENT location selection
     *              (note: this will never exceed range (1-10))
     *          IF solution indicates incorrect murderer
     *              INCREMENT murderer selection
     *              (note: this will never exceed range (1-6))
     * WHILE solution != 0
     *
     * OUTPUT "Total checks = " + jack.getTimesAsked()
     * IF jack.getTimesAsked() is greater than 20 THEN
     *      OUTPUT "FAILED"
     * ELSE
     *      OUTPUT "PASSED"
     * END IF
     */

    /**
     * Driver method for logically guessing approach
     *
     * @param args not used for driver
     */
    public static void main(String[] args) {
        // DECLARATION + INITIALIZATION
        int answerSet, solution, murder, weapon, location;
        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);

        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();

        while(answerSet > INVALID_PLUS_RANGE || answerSet < INVALID_MINUS_RANGE)
        {
            System.out.print("That's a far fetched theory! ");
            System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
            answerSet = keyboard.nextInt();
        }

        keyboard.close();

        // PROCESSING
        jack = new AssistantJack(answerSet);

        weapon = ROPE; //Initialize guess at first weapon
        location = KITCHEN; //Begin at first location
        murder = MS_SCARLET; //Begin at first murderer

        do {
            solution = jack.checkAnswer(weapon, location, murder); //Assistant Jack checks initializations

            if (solution == WRONG_WEAPON) {
                weapon++; //if incorrect, Detective Jill asks about the next weapon
            }
            if (solution == WRONG_LOCATION) {
                location++; //if incorrect, Detective Jill asks about the next location
            }
            if (solution == WRONG_MURDERER) {
                murder++; //if incorrect, Detective Jill asks about the next murderer
            }
        } while (solution != 0);

        answer = new Theory(weapon, location, murder);

        // OUTPUT
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well be called Batman! *Na na na na na na na na BATMAN*");
        }
    }
}
