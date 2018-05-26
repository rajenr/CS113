package models.graphClasses;

/**
 * Edge.java
 * This class has data of Edges. It stores a source and destination vertex as int values and weight of the
 * edge as a double.
 *
 * @author Erik Anderson
 * @author Danny Lee
 * @author Reesha Rajen
 * @version 1.0
 */

public class Edge {

    //Data field
    /**The destination vertex for an edge*/
    private int dest;
    /**The source vertex for an edge*/
    private int source;
    /**The weight*/
    private double weight;

    //Constructor
    /**Constructs an Edge from source to dest. Sets the weight to 1.0*/
    public Edge(int source, int dest){
        this.source = source;
        this.dest = dest;
        this.weight = 1.0;
    }

    /**Constructs an Edge from source to dest. Sets the weight to w*/
    public Edge(int source, int dest, double weight){
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    //Methods
    /**
     * Compares two edges for equality. Edges are equal if their source and destination vertices are the same.
     * The weight is not considered.
     * @return true if two Edges are equal
     */
    public boolean equals(Object anObject){
        if(anObject == null || getClass() != anObject.getClass()){
            return false;
        }
        Edge anotherEdge = (Edge)anObject;
        return(source == anotherEdge.getSource() && dest == anotherEdge.getDest());
    }

    /**
     * Returns the destination vertex
     * @return the destination vertex
     */
    public int getDest(){
        return this.dest;
    }

    /**
     * Returns the source vertex
     * @return the source vertex
     */
    public int getSource(){
        return this.source;
    }

    /**
     * Returns the weight.
     * @return the weight
     */
    public double getWeight(){
        return this.weight;
    }

    /**
     * Returns the hash code for an edge. The hash code depends only on the source and destination.
     * @return
     */
    public int hashCode(){
        String sourceToString = Integer.toString(source);
        String destToString = Integer.toString(dest);
        int hashCode = sourceToString.hashCode() + destToString.hashCode();
        return hashCode;
    }

    /**
     * Increments the weight of this edge by one
     */
    public void incrementWeight(){
        this.weight += 1.0;
        weight++;
    }

    /**
     * Returns a string representation of the edge.
     * @return a String representation of the edge.
     */
    public String toString(){
        return ("The edge starts from source vertex : " + source + " to destination vertex : " + dest + " and its " +
                "weight is : " + weight);
    }

}
