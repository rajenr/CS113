package models.graphClasses;

import java.util.*;

/**
 * ListGraph.java
 * A ListGraph is an extension of the AbstractGraph abstract class that uses an array of lists to represent the edges.
 * This class contains a data field to keep track of the edges that originate from each vertex. This is useful
 * when generating the Markov chain itself.
 *
 * @author Erik Anderson,
 * @author Danny Lee
 * @author Reesha Rajen
 * @version 1.0
 */
public class ListGraph extends AbstractGraph {

    //Data Field
    /** An array of Lists to contain the edges that originate with each vertex.*/
    private List<Edge>[] edges;

    //Constructor
    /**
     * Construct a graph with the specified number of vertices and directionality.
     * @param numV The number of vertices.
     * @param directed The directionality flag.
     */
    public ListGraph(int numV, boolean directed) {
        super(numV, directed);
        edges = new List[numV];
        for(int i = 0; i < numV; i++){
            edges[i] = new LinkedList<Edge>();
        }
    }

    //Methods
    /**
     * Determine whether an edge exists between the two vertices
     * @param source The source vertex
     * @param dest The destination vertex
     * @return true if there is an edge from source to dest
     */
    public boolean isEdge(int source, int dest){
        return edges[source].contains(new Edge(source, dest));
    }

    /**
     * Insert a new edge into the graph.
     * @param edge The new edge
     */
    public void insert(Edge edge){
        edges[edge.getSource()].add(edge);
        System.out.println(edge);
        //System.out.println();
        if(!isDirected()){
            edges[edge.getDest()].add(new Edge(edge.getDest(), edge.getSource(), edge.getWeight()));
        }
    }

    /**
     * Returns an iterator to the edges that originates from a given source vertex.
     * @param source The source vertex
     * @return an iterator
     */
    public Iterator<Edge> edgeIterator(int source){
        return edges[source].iterator();
    }


    /**
     * Get the edge between two vertices. If an edge does not exist, an Edge with a weight of Double.POSITIVE_INFINITY
     * is returned.
     * @param source The source vertex
     * @param dest The destination vertex
     * @return the edge between two vertices.
     */
    public Edge getEdge(int source, int dest){
        Edge target = new Edge(source, dest, Double.POSITIVE_INFINITY);
        for(Edge edge : edges[source]){
            if(edge.equals(target)){
                return edge; //Desired edge found, return it.
            }
        }
        //Assert : All edges for source checked.
        return target; // Desired edge not found.
    }


    /**
     * *Erik*
     * Instead of using a combination of isEdge, iterator, and insert, this method combines
     * these methods' functionality to prevent backtracking, unnecessary returns to graph.
     * The method searches for an edge given a source and dest and updates the weight
     * of the edge if it matches or it adds a new edge if it reaches the end of the list.
     * @param source integer representing source
     * @param dest integer representing dest
     */
    public void addOrUpdate(int source, int dest){

        if(edges[source].size() == 0){
            edges[source].add(new Edge(source, dest));


        }
        else{
            boolean addEdge = true;
            ListIterator<Edge> listIterator = edges[source].listIterator();
            Edge tempEdge;
            while (listIterator.hasNext()){

                tempEdge = listIterator.next();

                if (tempEdge.getDest() == dest){

                    tempEdge.incrementWeight();
                    listIterator.set(tempEdge);
                    addEdge = false;
                }
            }
            if(addEdge){
                edges[source].add(new Edge(source, dest));

            }
        }

    }

}
