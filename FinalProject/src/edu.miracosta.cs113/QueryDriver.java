package edu.miracosta.cs113;

/**
 * QueryDriver.java
 * This program is responsible for creating a new AutoSuggestController object and setting its visibility. The
 * controller itself displays the GUI itself from which input is handled by the keyboard.
 *
 * @author Reesha Rajen
 * @author Erik Anderson
 * @author Danny Lee
 */

import controllers.AutoSuggestController;

public class QueryDriver {
    /*
     * Algorithm
     * 1. Build the word graph that is directed and weighted.
     * 2. Wait for user input.
     * 3. If a user presses the space bar, get the latest input word.
     * 4. Use that input word as a source vertex and do a breadth first search.
     * 5. Get the parent array which stores the predecessors of the vertex that are adjacent to the input word.
     * 6. Go through the parent array and connect the adjacent words to form a phrase.
     * 7. Using PhraseWeightComparator, store each phrase into a priority queue by max-heap order based on the total
     * weight of each phrase.
     * 8. Poll top three phrases and suggest them to the user.
     * 9. Repeat step 2 to 8 until user exits.
     */
    public static void main(String[] args) {
        /** Instantiate temporary object */
        new AutoSuggestController().setVisible(true);
    }
}
