 package models;

/**
 * AutoSuggestModel.java
 * Class containing methods for an AutoSuggestModel object. This class is what implements AutoInterface and therefore
 * contains a generate method responsible for dealing with the actual Markov implementation itself.
 *
 * @version 1.0
 * @author Reesha Rajen
 * @author Danny Lee
 * @author Erik Anderson
 */

import models.graphClasses.Edge;
import models.graphClasses.Graph;
import models.graphClasses.ListGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.*;


 /**
  * AutoSuggestModel.java : This class is used for reading in the text file that is used to build the ListGraph.
  * While reading in the text file, it cleans up the sentence then stores each of them in a LinkedList. Using
  * binary search, the program stores every unique words into an ArrayList. Then it generates an array that stores
  * phrases to be suggested based on the user's input.
  *
  * @author Erik Anderson, Danny Lee, Reesha Rajen
  * @version 1.0
  **/

public class AutoSuggestModel implements AutoInterface {
    //Scanner for reading text file
    private Scanner inputFile;
    //will contain all unique words scanned from a given text file
    private ArrayList<String> uniqueWordList;
    //will contain all clean sentences in a given text file
    private LinkedList<String> sentenceList;
    //will be a directed, weighted graph of words for a give text file
    private ListGraph wordGraph;
    //integer for total unique words within a text file
    private int numUniqueWords;

    private static PriorityQueue<Phrase> queue; //TODO Reesha add documentation for these
    private Comparator<Phrase> comparator;

     /**
      * Default constructor
      */
    public AutoSuggestModel(){
        uniqueWordList = new ArrayList<>();
        sentenceList = new LinkedList<>();
        numUniqueWords = 0;
        comparator = new PhraseWeightComparator<>();
        queue = new PriorityQueue<>(50, comparator);
    }

     /**
      * Builds a word graph when given a name of the input text file.
      * @param inputFileName the name of the input text file.
      */
    public void buildWordGraph(String inputFileName){

        buildSentenceList(inputFileName);
        wordGraph = new ListGraph(uniqueWordList.size(), true);

        ListIterator<String> sentenceListIterator = sentenceList.listIterator();
        String currentSentence;
        //iterate through sentence list
        while(sentenceListIterator.hasNext()){
            currentSentence = sentenceListIterator.next();
            String[] sentenceArray = currentSentence.split(" ");
            String currentWord;
            String nextWord;
            int currentWordIndex;
            int nextWordIndex;

            for(int i = 0; i < sentenceArray.length; i++ ){

                currentWord = sentenceArray[i];

                currentWordIndex = binarySearch(currentWord);
                //not at end of sentence
                if(i < sentenceArray.length - 1){
                    nextWord = sentenceArray[i + 1];
                    nextWordIndex = binarySearch(nextWord);

                    wordGraph.addOrUpdate(currentWordIndex, nextWordIndex);
                }
            }
        }
    }
    /**
     * First pass
     * Opens up a text file with the given name. The file is then scanned sentence by sentence
     * line by line, adding to the sentenceList instance variable every time a "." is detected.
     * The word list is also updated using the method addToWordListSorted.
     * Finally, the file connection is closed.
     * @param inputFileName for a text file
     */
    public void buildSentenceList(String inputFileName){

        inputFile = null; //"fileIn" made available
        try {
            inputFile = new Scanner(new FileInputStream(inputFileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        String currentLine = "";
        String sentence;

        while(inputFile.hasNext()){

            //accounting for multiple line breaks
            while(currentLine.length() == 0){
                currentLine = inputFile.nextLine();
            }

            //accounting for line-sized sentences that wrap to next line
            while(!reachedEndOfSentence(currentLine) && inputFile.hasNextLine()){
                currentLine += " " + inputFile.nextLine();
            }

            int endingPunctuationIndex;

            //accounting for multiple sentences per line
            while(getEndingPunctuationIndex(currentLine) > 0){

                //accounting for sentences that start in middle of paragraph
                if(currentLine.charAt(0) == ' '){
                    currentLine = currentLine.substring(1);
                }
                endingPunctuationIndex = getEndingPunctuationIndex(currentLine);

                sentence = currentLine.substring(0, endingPunctuationIndex);
                sentence = cleanUpSentence(sentence);
                sentenceList.add(sentence);
                addToWordListSorted(sentence);

                currentLine = currentLine.substring(endingPunctuationIndex + 1);
            }
        }
        inputFile.close();
    }

    /**
     * returns the index of the first terminal punctuation
     * @param currentLine
     * @return
     */
    private int getEndingPunctuationIndex(String currentLine){

        char currentChar;
        for(int i = 0; i < currentLine.length(); i++){

            currentChar = currentLine.charAt(i);

            if ( currentChar == '?' ||
                    currentChar == '.' ||
                    currentChar == '!'){

                return i;
            }

        }
        return -1;
    }

    /**
     * returns true if the current line hold any terminal punctuation
     * @param currentLine being processed
     * @return a boolean that represents presence of certain punctuation
     */
    private boolean reachedEndOfSentence(String currentLine){

        return (currentLine.contains("?") ||
                currentLine.contains("!") ||
                currentLine.contains("."));
    }

    /**
     * Takes in a sentence String and removes any unwanted characters.
     * @param sentence to be cleaned
     * @return a sentence free of undesired characters
     */
    public String cleanUpSentence(String sentence){
        sentence = sentence.toLowerCase();

        sentence = sentence.replaceAll("'s", "");
        sentence = sentence.replaceAll("\"", "");
        sentence = sentence.replaceAll("\" ", "");

        return sentence;
    }

    //Adding words in the sentence to the arrayList uniqueWordList.
    /**
     * First the method breaks the sentence up into words. If the uniqueWordList is empty,
     * the word is added to the list. Otherwise, the binarySearch method is used to determine
     * where to place the word in the list.
     * @param sentence containing words to add to list
     */
    public void addToWordListSorted(String sentence){

        String [] wordArray = sentence.split(" ");
        String currentWord;
        for(int i = 0; i < wordArray.length; i++){
            currentWord = wordArray[i];

            if(uniqueWordList.size() == 0){ //first word

                uniqueWordList.add(currentWord);
                numUniqueWords++;
            }
            else{

                int binarySearchPosition = binarySearch(currentWord);

                if ( binarySearchPosition == uniqueWordList.size() ){ //if adding to end of list

                    uniqueWordList.add(currentWord);
                    numUniqueWords++;

                }
                else if( !uniqueWordList.get(binarySearchPosition).equals(currentWord)) { //if the word is not in the list

                    uniqueWordList.add(binarySearchPosition, currentWord);
                    numUniqueWords++;

                }
            }
        }
    }

     /**
      * Returns the priorityQueue that stores phrases
      * @return queue the priorityQueue that stores phrases
      */
    public PriorityQueue getQueue() {
        return queue;
    }

    /**
     * Getter for numUniqueWords
     * @return an integer for the number of unique words
     */
    public int getNumUniqueWords(){
        return numUniqueWords;
    }

    /**
     * Getter for uniqueWordList. Used for testing.
     * @return the uniqueWordList
     */
    public ArrayList<String> getUniqueWordList() {
        return uniqueWordList;
    }

    /**
     * Getter for sentence list. Used for testing.
     * @return the sentence list
     */
    public LinkedList<String> getSentenceList() {
        return sentenceList;
    }


    public int getIndexOfUniqueWordList(String targetWord){
        return uniqueWordList.indexOf(targetWord);
    }

    /**
     * getter for wordGraph. Used for testing.
     * @return htis instance of wordGraph
     */
    public ListGraph getWordGraph() {
        return wordGraph;
    }

    /**
     * This method perform a binary search of the uniqueWord list given a word. If the word
     * exists in the list, an integer value for its index is returned. Otherwise, an integer
     * value is returned that represents where the word should go.
     * @param currentWord being searched for
     * @return an integer for the location of a word in the uniqueWordList, or where
     * a word should be placed if it is not in the list.
     */
    public int binarySearch(String currentWord){

        int firstWordIndex = 0;
        int lastWordIndex = uniqueWordList.size() - 1;
        int middleIndex;
        int i = 0;


        while(firstWordIndex <= lastWordIndex){

            middleIndex = (firstWordIndex + lastWordIndex) / 2;

            if(currentWord.compareTo(uniqueWordList.get(middleIndex)) < 0){
                lastWordIndex = middleIndex - 1;
                i = middleIndex;
            }
            else if(currentWord.compareTo(uniqueWordList.get(middleIndex)) > 0){
                firstWordIndex = middleIndex + 1;
                i = firstWordIndex;
            }
            else {
                return middleIndex;
            }
        }
        return i;
    }

     /**
      * Builds and initializes a word graph.
      */
    public void buildWordGraph(){
        //System.out.println("numUniqueWord checked in buildWordGraph method : " + numUniqueWords);
        wordGraph = new ListGraph(numUniqueWords, true);
    }

    /**
     * Perform a breadth-first search of a graph that is given.
     * post : The array parent will contain the predecessor of each vertex in the breadth-first search tree
     *
     * @param graph The graph to be searched
     * @param start The start vertex
     * @return The array of parents.
     */
    public static int[] breadthFirstSearch(Graph graph, int start){
        /** theQueue is used to store nodes that are waiting to be visited.*/
        Queue<Integer> theQueue = new LinkedList<>();
        /** Declare array[] parent and initialize its element to -1
            This array parent could be used to construct the breadth-first search tree.
            The element parent[v] contains the parent of vertex v in the tree. */
        int[] parent = new int[graph.getNumV()];
        for(int i = 0; i < graph.getNumV(); i++){
            parent[i] = -1;
        }

        /** Declare array[] identified and initialize its elements to false.
            The array identified is used to keep track of the nodes that have been previously encountered.*/
        boolean[] identified = new boolean[graph.getNumV()];
        // Mark the start vertex as identified and insert it into the queue.
        identified[start] = true;
        theQueue.offer(start);

        // Perform breadth-first-search until done
        while(!theQueue.isEmpty()){
            //Take(poll or remove) a vertex, current, out of the queue. (Begin visiting current)
            int current = theQueue.remove();
            //Examine each vertex, neighbor, adjacent to current.
            Iterator<Edge> iterator = graph.edgeIterator(current);
            while(iterator.hasNext()){
                Edge edge = iterator.next();
                int neighbor = edge.getDest();
                //If neighbor has not been identified
                if(!identified[neighbor]){
                    //Mark it identified
                    identified[neighbor] = true;
                    //Place it into the queue
                    theQueue.offer(neighbor);
                    //Insert the edge (current, neighbor) into the breadth-first search tree
                    parent[neighbor] = current;
                }
            }
            //Finished visiting current.
        }
        return parent;
    }

     /**
      * This method builds the graph using the buildWordGraph method that utilizes a file name parameter.
      * It then creates an AutoSuggestModel object based off of the words found in the text file. We built this
      * method in order to optimize our entire program so it would be able to run more efficiently, rather
      * than creating a new graph each time.
      * @return AutoSuggestModel object that contains built text file.
      */
     public static AutoSuggestModel buildOffTextFile() {
         AutoSuggestModel autoSuggestModel = new AutoSuggestModel();

         for(String sentence : autoSuggestModel.getSentenceList()){
             autoSuggestModel.addToWordListSorted(sentence);
         }

         autoSuggestModel.buildWordGraph("resources/google.txt");
         return autoSuggestModel;
     }

    /**
     * This method takes in the last word that user typed as a parameter. Then it uses this word to do a breadth first
     * search to find the words that are adjacent to it. It finds the phrases that comes after the given word. Then it
     * stores the phrases into a PriorityQueue which is in max-heap order. At the end, it polls the top-three weighted
     * phrases and stores into a String array and returns the array.
     * @param word the last word user typed
     * @return phrases[] the array that stores phrases
     */
    public String[] generate(AutoSuggestModel autoSuggestModel, String word) {
        ListGraph wordGraph;
        int[] parentArray;
        Stack<String> wordStack = new Stack<>();
        String[] splitPhrase;
        String[] splitSentence;
        int sourceWordIndex;
        int destWordIndex;
        int nextWordIndex;
        double totalWeight;
        String stringPhrase;
        String[] phrases = new String[3];

        wordGraph = autoSuggestModel.getWordGraph();

        for(String sentence : autoSuggestModel.getSentenceList()){
            splitSentence = sentence.split("\\s+");

            for(int i = 0; i < splitSentence.length - 1; i ++){
                sourceWordIndex = autoSuggestModel.binarySearch(splitSentence[i]);
                destWordIndex =  autoSuggestModel.binarySearch(splitSentence[i + 1]);

                if(wordGraph.isEdge(sourceWordIndex, destWordIndex)){
                    wordGraph.getEdge(sourceWordIndex,destWordIndex).incrementWeight();
                }else{
                    wordGraph.insert(new Edge(sourceWordIndex,destWordIndex));
                }
            }
        }
        parentArray = breadthFirstSearch(wordGraph, autoSuggestModel.binarySearch(word));

        for(int i = 0; i < parentArray.length; i++){
            stringPhrase = "";
            totalWeight = 0.0;
            if(parentArray[i] != -1){// i == 2 (first case)
                wordStack.push(autoSuggestModel.getUniqueWordList().get(i));
                nextWordIndex = parentArray[i];
                while(nextWordIndex != -1){
                    wordStack.push(autoSuggestModel.getUniqueWordList().get(nextWordIndex));
                    nextWordIndex = parentArray[nextWordIndex];
                }
                while(!wordStack.empty()){
                    stringPhrase +=  wordStack.pop() + " ";
                }
                splitPhrase = stringPhrase.split("\\s+");

                for(int j = 0; j < splitPhrase.length - 1; j ++) {
                    totalWeight += wordGraph.getEdge(autoSuggestModel.binarySearch(splitPhrase[j]),
                            autoSuggestModel.binarySearch(splitPhrase[j + 1])).getWeight();
                }
                queue.add(new Phrase(stringPhrase, totalWeight));
            }
        }

        for (int i = 0; i < phrases.length; i++) {
            phrases[i] = "" + queue.poll();
        }
        return phrases;
    }
}


