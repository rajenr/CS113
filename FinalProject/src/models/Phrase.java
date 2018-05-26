package models;

/**
 * Phrase.java : This class stores the String values that builds the phrase used for auto suggestion.
 * It also stores the weightTotal which is the sum of all the weights in edges between two words.
 *
 * @author Erik Anderson, Danny Lee, Reesha Rajen
 * @version 1.0
 **/
public class Phrase {

    String stringPhrase = "";
    double weightTotal = -1.0;

    /**
     * Default constructor
     */
    public Phrase(){
        this.stringPhrase = "";
        this.weightTotal = -1.0;
    }

    /**
     * Full constructor
     * @param givenPhrase String value to store as a phrase
     * @param givenWeight double value to store as a weightTotal
     */
    public Phrase(String givenPhrase, double givenWeight){
        this.stringPhrase = givenPhrase;
        this.weightTotal = givenWeight;
    }

    /**
     * Getter method for weightTotal
     * @return weightTotal sum of weights within the phrase
     */
    public double getWeightTotal(){
        return this.weightTotal;
    }

    /**
     * Getter method for stringPhrase
     * @return stringPhrase String value of the phrase
     */
    public String getStringPhrase(){
        return stringPhrase;
    }

    /**
     * Returns String explanation of the phrase
     * @return stringPhrase String value of the phrase
     */
    public String toString(){
        return (this.stringPhrase);
    }
}
