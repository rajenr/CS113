package models;

/**
 * AutoInterface.java
 * This class is the interface for the AutoSuggestModel, meaning that the class must contain this method.
 *
 * @version 1.0
 * @author Reesha Rajen
 * @author Erik Anderson
 * @author Danny Lee
 */
public interface AutoInterface {

    /**
     * This method generates the Markov chain based off of the given word and the given AutoSuggest Model object.
     * It determines the nodes adjacent to the word, and then performs a breadth-first search in order to display
     * the given paths.
     * @param autoSuggestModel Object containing sentences from the text file itself.
     * @param word String parameter containing the relevant word.
     * @return String array for use with the JTextFields that will display the given sentences based off of the
     *         word. This itself is the predictive feature displayed to the user.
     */
    String[] generate(AutoSuggestModel autoSuggestModel, String word);
}
