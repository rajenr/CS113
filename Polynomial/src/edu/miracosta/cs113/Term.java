package edu.miracosta.cs113;

/**
 * Term.java : This class contains all methods for Term objects,
 *             which Polynomial objects are composed of. This class
 *             is an ADT class, and also contains all required
 *             methods.
 *
 * @author Reesha Rajen
 * @version 1.0
 *
 * Last modified: 16 Feb 2018
 */

public class Term implements Cloneable, Comparable<Term>{

    /** CONSTANTS **/
    public static final int DEFAULT_COEFF = 1;
    public static final int DEFAULT_EXP = 1;

    /** INSTANCE VARIABLES **/
    private int exponent;
    private int coefficient;

    /** METHODS **/

    /*CONSTRUCTORS */
    public Term(){
        this.setAll(DEFAULT_COEFF, DEFAULT_EXP);
    }

    public Term(int coefficient, int exponent){
        this.setAll(coefficient, exponent);
    }

    public Term(Term original){
        if(original != null){
            this.setAll(original.coefficient, original.exponent);
        }
        else{
            System.out.println("Cannot copy null object. Now exiting...");
            System.exit(0);
        }
    }

    public Term(String term){
        Term convertTerm = new Term();

        convertTerm.setCoefficient(term.charAt(0));
        convertTerm.setExponent(term.charAt(term.length()-1));
    }

    /* SETTERS */
    public void setExponent(int exponent){
        this.exponent = exponent;
    }

    public void setCoefficient(int coefficient){
        this.coefficient = coefficient;
    }

    public void setAll(int coefficient, int exponent){
        this.setCoefficient(coefficient);
        this.setExponent(exponent);
    }

    /* GETTERS */
    public int getExponent(){
        return this.exponent;
    }

    public int getCoefficient(){
        return this.coefficient;
    }

    /* TO STRING */
    public String toString(){
        if(this.coefficient != 0){ //independent
            if(this.exponent > 1){
                if(this.coefficient > 1){
                    return "+" + this.coefficient + "x^" + this.exponent;
                }
                else if(this.coefficient == 1){
                    return "+x^" + this.exponent;
                }
                else if(this.coefficient == -1){
                    return "-x^" + this.exponent;
                }
                else{
                    return this.coefficient + "x^" + this.exponent;
                }
            }
            else if(this.exponent == 1)
            {
                if(this.coefficient > 1){
                    return "+" + this.coefficient + "x";
                }
                else if(this.coefficient == 1){
                    return "+x";
                }
                else if(this.coefficient == -1){
                    return "-x";
                }
                else{
                    return this.coefficient + "x";
                }
            }
            else if(this.exponent == 0){
                if(this.coefficient > 1){
                    return "+" + this.coefficient;
                }
                else if(this.coefficient == 1){
                    return "" + this.coefficient;
                }
                else{
                    return "" + this.coefficient;
                }
            }
            else{
                if(this.coefficient > 1){
                    return "+" + this.coefficient + "x^" + this.exponent;
                }
                else if(this.coefficient == 1){
                    return "+x^" + this.exponent;
                }
                else if(this.coefficient == -1){
                    return "-x^" + this.exponent;
                }
                else{
                    return this.coefficient + "x^" + this.exponent;
                }
            }
        }
        else{
            return "";
        }
    }

    /* EQUALS */
    @Override
    public boolean equals(Object other){

        Term otherTerm;

        if(other == null){
            return false;
        }
        else if(this.getClass() != other.getClass()){
            return false;
        }
        else{
            otherTerm = (Term) other;
            return this.coefficient == otherTerm.coefficient &&
                   this.exponent == otherTerm.exponent;
        }
    }

    /* CLONE */
    @Override
    public Term clone(){

        Term term = new Term();
        term.setCoefficient(this.coefficient);
        term.setExponent(this.exponent);

        return term;
    }

    /* COMPARE TO */
    @Override
    public int compareTo(Term term){
        if(getCoefficient() < term.getCoefficient()){
            return -1;
        }
        else if(getCoefficient() > term.getCoefficient()){
            return 1;
        }
        else if(getExponent() < term.getExponent()){
            return -1;
        }
        else if(getExponent() > term.getExponent()){
            return 1;
        }
        else{
            return 0;
        }
    }
}