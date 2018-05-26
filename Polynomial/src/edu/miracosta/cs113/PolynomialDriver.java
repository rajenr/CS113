package edu.miracosta.cs113;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * PolynomialDriver.java : This class serves as the driver program for
 *                         Polynomial.
 *
 * @author Reesha Rajen
 * @version 1.0
 *
 * Last modified: 16 Feb 2018
 */

public class PolynomialDriver {

    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);
        int userChoice, coefficient, exponent;
        boolean isValid = true;
        LinkedList<Polynomial> list = new LinkedList<>();
        Polynomial firstPoly = new Polynomial();
        Term firstTerm;

        System.out.println("POLYNOMIAL BUILDER c. 2018");
        System.out.println("\nHello and welcome to Polynomial Builder.");

        do{
            System.out.println("MENU:\n1. Create a Polynomial\n2. Add Terms to Polynomial\n3. Delete a Polynomial");
            System.out.println("4. Exit Program");

            System.out.print("\nPlease make a selection: ");
            userChoice = keyboard.nextInt();

            while (userChoice > 4 || userChoice < 1) {
                System.out.print("Please enter again: ");
                userChoice = keyboard.nextInt();
            }
            if (userChoice == 1) {
                list.add(firstPoly);
                System.out.println("Your polynomial is " + firstPoly.toString());
            }
            if(userChoice == 2){
                System.out.print("Enter the coefficient of your term: ");
                coefficient = keyboard.nextInt();
                System.out.print("Enter the exponent of your term: ");
                exponent = keyboard.nextInt();

                firstTerm = new Term(coefficient, exponent);
                System.out.println("You entered " + firstTerm.toString() + "\n");
                firstPoly.addTerm(firstTerm);
                System.out.println("Your polynomial is " + firstPoly.toString());
            }
            if(userChoice == 3){
                System.out.println("Polynomials:");
                for(int i = 0; i < list.size(); i++){
                    System.out.println((i+1) + ": " + list.get(i));
                }

                System.out.print("Enter the number of the polynomial you wish to delete: ");
                int selection = keyboard.nextInt();
                list.remove(selection-1);
                System.out.println("Success!");
            }
            if(userChoice == 4){
                System.out.println("Thank you!");
                isValid = false;
            }
        }while(isValid);
    }
}
