package edu.miracosta.cs113.change;

/**
 * ChangeCalculator.Java
 *
 * This is a recursive method that dispenses change for a given amount of money.
 * Always dispenses the highest coin first. The method displays the total number of change combinations possible.
 *
 * @author Reesha Rajen <rajen.reese@gmail.com>
 * @version  1.0
 * Last Modified: 3/17/2018
 */

public class ChangeCalculator {
    //METHOD
    /**
     * Combination calculation method
     * @param change given amount
     * @param numQuarters number of quarters
     * @param numDimes number of dimes
     * @param numNickels number of nickels
     * @param numPennies number of pennies
     * @return integer with number of possible change combinations
     */
    public static int combinations(int change, int numQuarters, int numDimes, int numNickels, int numPennies) {
        if (change == numPennies) {
            System.out.printf("%10s %10s %10s %10s%n", numQuarters, numDimes, numNickels, numPennies);
            return 1;
        }
        else {
            int tempChange;
            tempChange = change;

            if (numQuarters == 0 && numDimes == 0 && numNickels == 0 && numPennies == 0) {
                System.out.printf("%10s %10s %10s %10s%n", "Quarters", "Dimes", "Nickels", "Pennies");
                numQuarters = change / 25;

                change -= numQuarters * 25;
                numDimes = change / 10;

                change -= numDimes * 10;
                numNickels = change / 5;

                change -= numNickels * 5;
                numPennies += change;

                //Recursive method call
                return combinations(tempChange, numQuarters, numDimes, numNickels, numPennies);
            } else {
                //Prints previous combinations
                System.out.printf("%10s %10s %10s %10s%n", numQuarters, numDimes, numNickels, numPennies);

                if (numNickels > 0) {
                    //Reduces nickel into five pennies
                    numNickels -= 1;
                    numPennies += 5;

                    //Recursive method call
                    return combinations(tempChange, numQuarters, numDimes, numNickels, numPennies) + 1;
                } else if (numDimes > 0) {
                    int remainderNickels;

                    //Reduces dime into two nickels
                    numDimes -= 1;
                    numNickels += 2;

                    remainderNickels = numPennies / 5;
                    numPennies -= remainderNickels * 5;
                    numNickels += remainderNickels;

                    //Recursive method call
                    return combinations(tempChange, numQuarters, numDimes, numNickels, numPennies) + 1;
                } else if (numQuarters > 0) {
                    int remainderNickels, remainderDimesOfNickels;

                    //Reduces quarter into two dimes and one nickel
                    numQuarters -= 1;
                    numDimes += 2;
                    numNickels += 1;

                    remainderNickels = numPennies / 5;
                    numNickels -= remainderNickels * 5;
                    numNickels += remainderNickels;

                    remainderDimesOfNickels = numNickels / 2;
                    numNickels -= remainderDimesOfNickels * 2;
                    numDimes += remainderDimesOfNickels;

                    //Recursive method call
                    return combinations(tempChange, numQuarters, numDimes, numNickels, numPennies) + 1;
                }
            }
        }
        //Keeps compiler happy
        return 0;
    }
}



