package models;

import java.util.Comparator;


/**
 * PhraseComparator.java : This class implements Comparator and overrides compare method.
 *
 * @author Erik Anderson, Danny Lee, Reesha Rajen
 * @version 1.0
 **/

public class PhraseWeightComparator<E> implements Comparator<E> {


    @Override
    /**
     * This method compares two Phrase objects by their total weight.
     * @param o1 A phrase object that is being compared.
     * @param o2 A phrase object that is being compared.
     * @return 1 if first phrase object has less total weight.
     *        -1 if first phrase object has greater total weight
     *         0 if both phrase objects have the same total weights.
     */
    public int compare(E o1, E o2) {

        Phrase aPhrase = new Phrase();

        if ( (o1.getClass() != aPhrase.getClass()) | (o2.getClass() != aPhrase.getClass())){

            System.out.println("Tried to compare weight. Cannot compare non-Phrases");
            System.exit(0);

        }

        //cast
        Phrase phraseOne = (Phrase) o1;
        Phrase phraseTwo = (Phrase) o2;

        if(phraseOne.getWeightTotal() < phraseTwo.getWeightTotal()){
            return 1;
        }
        else if(phraseOne.getWeightTotal() > phraseTwo.getWeightTotal()){
            return -1;
        }
        else{ //equal

            return 0;

        }

    }

}
