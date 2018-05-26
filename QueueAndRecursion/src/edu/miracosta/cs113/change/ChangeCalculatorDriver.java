package edu.miracosta.cs113.change;

import static edu.miracosta.cs113.change.ChangeCalculator.combinations;

public class ChangeCalculatorDriver {

    public static void main(String[] args) {
        int change;

        change = 5;
        System.out.println(combinations(change, 0, 0, 0, 0));

        change = 10;
        System.out.println(combinations(change, 0, 0, 0, 0));

        change = 15;
        System.out.println(combinations(change, 0, 0, 0, 0));

        change = 20;
        System.out.println(combinations(change, 0, 0, 0, 0));

        change = 25;
        System.out.println(combinations(change, 0, 0, 0, 0));

        change = 30;
        System.out.println(combinations(change, 0, 0, 0, 0));

        //Challenge case
        change = 75;
        System.out.println(combinations(change, 0, 0, 0, 0));

    }
}
