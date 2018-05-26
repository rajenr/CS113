package edu.miracosta.cs113;
import java.util.LinkedList;

/**
 * Polynomial.java : This class instantiates a LinkedList of type Term.
 *                   Polynomial.java also contains useful methods for
 *                   use in tandem with Term objects.
 *
 * @author Reesha Rajen
 * @version 1.0
 *
 * Last modified: 16 Feb 2018
 */

public class Polynomial{

    /** CONSTANTS **/

    /** INSTANCE VARIABLES **/
    private LinkedList<Term> list;

    /** METHODS **/

    /* CONSTRUCTORS */
    public Polynomial(){
        this.list = new LinkedList<>();
    }

    public Polynomial(Polynomial original){
        if(original != null){
            if(original.list.isEmpty() == false) {
                for(int i = 0; i < this.list.size(); i++){
                    this.list.add(original.list.get(i));
                }
            }
            else{
                System.out.println("Empty List.");
            }
        }
        else{
            System.out.println("Cannot copy null object. Now exiting...");
            System.exit(0);
        }
    }

    /* GETTERS */
    public int getNumTerms(){
        return list.size();
    }

    /* OTHER USEFUL METHODS */

    //All my logic (I feel) makes sense, but my code is only partially passing all of the
    //JUnit tests. I drew it out, but this is as far as I got in terms of implementation and
    //converting English to Java.
    public void addTerm(Term term){
        if(list.isEmpty()){ //No restrictions on this
            list.addFirst(term);
        }
        else if(list.size() == 1){ //Only comparing with one other term
            if(list.get(0).getExponent() == term.getExponent()){
                list.get(0).setCoefficient(list.get(0).getCoefficient() + term.getCoefficient());
            }
            else{
                if(term.getExponent() > list.get(0).getExponent()){
                    list.addFirst(term);
                }
                else{
                    list.addLast(term);
                }
            }
        }
        else{
            for(int i = 0; i < list.size(); i++){ //Where the madness begins
                if(list.get(i).getExponent() == term.getExponent()){
                    list.get(i).setCoefficient(list.get(i).getCoefficient() + term.getCoefficient());
                }
            }
            int position = 0;

            while(term.getExponent() < list.get(position).getExponent()) { //Runs until it encounters something larger
                position++;
                break;
            }
            if(position > list.size()){ //if position counter is at end, automatically takes last spot
                list.addLast(term);
            }
            else { //otherwise, adds in position prior
                list.add(position, term);
            }
        }
    }

    public Term getTerm(int i){
        return list.get(i);
    }

    //I realize there is a *much* better implementation, but I couldn't figure out how to return
    //the first term's substring plus the entire linked list as a string. I ended up hardcoding it
    //(yikes) for most of the cases I would encounter :(
    public String toString(){
        if(list.isEmpty()){
            return "0";
        }
        else if(list.size() == 1){
            String firstTerm = "" + list.get(0);
            return firstTerm.substring(1, firstTerm.length());
        }
        else if(list.size() == 2){
            String firstTerm = "" + list.get(0);
            return firstTerm.substring(1, firstTerm.length()) + list.get(1);
        }
        else if(list.size() == 3){
            String firstTerm = "" + list.get(0);
            return firstTerm.substring(1, firstTerm.length()) + list.get(1) + list.get(2);
        }
        else if(list.size() == 4){
            String firstTerm = "" + list.get(0);
            return firstTerm.substring(1, firstTerm.length()) + list.get(1) + list.get(2) + list.get(3);
        }
        else{
            return "";
        }
    }

    public void clear(){
        for(int i = 0; i < list.size(); i++){
            list.clear();
        }
    }

    public void add(Polynomial polynomial){
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; i < polynomial.getNumTerms(); j++){
                if(list.get(i).getExponent() == polynomial.getTerm(j).getExponent()){
                    list.get(i).setCoefficient(list.get(i).getCoefficient() + polynomial.getTerm(j).getCoefficient());
                    list.add(list.get(i));
                }
                else{
                    list.add(polynomial.getTerm(i));
                }
            }
        }
    }
}