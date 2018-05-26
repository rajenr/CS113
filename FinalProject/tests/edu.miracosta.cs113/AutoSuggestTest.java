/**
 * AutoSuggestTest.java
 * This class contains tester methods for AutoSuggestModel. It imports the AutoSuggestModel itself and generates text
 * to be fed to the method, as well as a testFile found in the resources folder.
 *
 * @author Erik Anderson
 * @author Danny Lee
 * @author Reesha Rajen
 *
 * @version 1.0
 */

package edu.miracosta.cs113;

import models.AutoSuggestModel;
import models.graphClasses.Edge;
import models.graphClasses.ListGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutoSuggestTest {

    private AutoSuggestModel autoSuggestModelTest;
    private ListGraph testWordGraph;
    private int[][] bfsTestArray;

    private static String TEST_TEXT_FILE_NAME = "resources/testFile.txt";

    //to be fed to addSorted() method
    public static final String[] UNSORTED_LIST = {
            "dog",
            "frog",
            "zebra",
            "ant",
            "moose",
            "pig",
            "dog",
            "cat",
            "kangaroo",
            "bear",
            "snake",
            "cat",
            "frog",
            "zebra",
            "moose",
            "dog",
            "bear",
            "elephant",
            "bear",
            "bear",
            "bear",
            "bear",
            "bear"
    };

    //should be how uniqueWordList should look
    public static final String[] SORTED_LIST = {
            "ant",
            "bear",
            "cat",
            "dog",
            "elephant",
            "frog",
            "kangaroo",
            "moose",
            "pig",
            "snake",
            "zebra"
    };

    //should be what the sentenceList resembles
    public static final String[] TEXT_FILE_SENTENCE_LIST = {
            "the dog jumped",
            "the dog jumped",
            "the dog jumped",
            "the dog jumped",
            "the dog jumped high",
            "the dog jumped high",
            "the dog jumped high",
            "the dog jumped high today",
            "the dog jumped high today",
            "the end"
    };
    //should be what the uniqueWordList resembles
    public static final String[] TEXT_FILE_VERTEX_LIST = {
            "dog",
            "end",
            "high",
            "jumped",
            "the",
            "today",
    };

    //2d array for BFS testing of vertices
    public static final int[][] TEXT_FILE_BFS_ARRS = {

        {-1, -1, 3, 0, -1, 2}, //dog
        {-1, -1, -1, -1, -1, -1}, //end
        {-1, -1, -1, -1, -1, 2}, //high
        {-1, -1, 3, -1, -1, 2}, //jumped
        {4, 4, 3, 0, -1, 2}, //the
        {-1, -1, -1, -1, -1, -1}, //today
    };

    public static final int[] TEXT_FILE_EDGE_WEIGHT = {

         // edge        u, v
      9, //"dog jumped" 0, 3
      2, //"high today" 2, 5
      4, //"jumped high" 3, 2
      9, //"the dog"    4, 0
      1, //"the end"    4, 1

    };

    public static final Edge[] TEXT_FILE_EDGE_ARRAY = {
            new Edge(0, 3, 9), //"dog jumped"
            new Edge(2, 5, 2), //"high today"
            new Edge(3, 2, 4), //"jumped high"
            new Edge(4, 0 ,9), //"the dog"
            new Edge(4, 1, 1), //"the end"
    };

    //used for testing the phrasePriorityQueue
    public static final String[][] STRING_ARRAY_FOR_PRIORITY_QUEUE ={

            {"jumped high today ", "jumped high "}, //"dog"
            {}, //"end"
            {"today "}, //"high"
            {"high today ", "high "}, //"jumped"
            {"dog jumped high today ", "dog jumped high ", "dog jumped ", "end " }, //"the"
            {}, //"today"
    };

    //used for testing the array of strings produced by generate() method
    public static final String[][] STRING_ARRAY_FOR_GENERATE_METHOD ={

            {"jumped high today ", "jumped high "}, //"dog"
//            {}, //"end"
            {"today "}, //"high"
            {"high today ", "high "}, //"jumped"
            {"dog jumped high today ", "dog jumped high ", "dog jumped " }, //"the"
//            {}, //"today"
    };



    @Before
    public void constructAutoSuggestModelTest(){

        autoSuggestModelTest = new AutoSuggestModel();
    }

    private void buildWordList(){

        for(int i = 0; i < UNSORTED_LIST.length; i++){

            autoSuggestModelTest.addToWordListSorted( UNSORTED_LIST[i]);
        }
    }

    private void buildSentenceList(){

        autoSuggestModelTest.buildSentenceList(TEST_TEXT_FILE_NAME);

    }

    private void buildWordGraph(){

        autoSuggestModelTest.buildWordGraph(TEST_TEXT_FILE_NAME);
        testWordGraph = autoSuggestModelTest.getWordGraph();

    }

    private void buildBFSArray(){
        buildWordGraph();
        bfsTestArray = new int[6][];

        for(int i = 0; i < bfsTestArray.length; i++){

            bfsTestArray [i] = autoSuggestModelTest.breadthFirstSearch(testWordGraph, i);

        }
    }

    /*The following two tests are for the method addToWordListSorted. A handmade
    array is compared to to a uniqueWordList built from an unsorted array with repeats.
    The first tests by size, the second tests the actual content of the list.
    Tests addToWordListSorted as well as binarySearch*/
    @Test
    public void testAddToWordListSortedBySize(){

        buildWordList();
        assertEquals("SORTED_LIST length and numUniqueWords should be equal",
                SORTED_LIST.length, autoSuggestModelTest.getNumUniqueWords());

    }
    @Test
    public void testAddToWordListSortedByContent(){

        buildWordList();
        for(int i = 0; i < SORTED_LIST.length; i++){

            assertEquals("Index values should be equal: ",
                    i, autoSuggestModelTest.binarySearch(SORTED_LIST[i]));

        }
    }

    //This tests the sentenceList that is built using the testFile.txt
    @Test
    public void testBuildSentenceListBySentences(){

        buildSentenceList();
        LinkedList<String> tempLinkedList = autoSuggestModelTest.getSentenceList();
        for ( int i = 0; i < TEXT_FILE_SENTENCE_LIST.length; i++){

            assertEquals("Sentences should be equal: ",
                    TEXT_FILE_SENTENCE_LIST[i], tempLinkedList.get(i));

        }
    }

    //This tests the uniqueWordList that is built using the testFile.txt
    @Test //Comparing uniqueWordList built using text file
    public void testBuildSentenceListByWordList(){

        buildSentenceList();
        ArrayList<String> tempList = autoSuggestModelTest.getUniqueWordList();
        for (int i = 0; i < TEXT_FILE_VERTEX_LIST.length; i++){

            assertEquals("Word list should be equal: ",
                    TEXT_FILE_VERTEX_LIST[i], tempList.get(i));

        }
    }

    //edge tests
    @Test //first test if the edge is present
    public void testGraphByIsEdge(){

        buildWordGraph();
        int u, v;
        for (int i = 0; i < TEXT_FILE_EDGE_ARRAY.length; i++){

            u = TEXT_FILE_EDGE_ARRAY[i].getSource();
            v = TEXT_FILE_EDGE_ARRAY[i].getDest();
            assertTrue("edge should be present", testWordGraph.isEdge(u, v));

        }
    }

    @Test //then see if the weight between edges is accurate
    public void testGraphByWeight(){

        buildWordGraph();
        double wExpected, wActual;
        int u, v;
        for(int i = 0; i < TEXT_FILE_EDGE_ARRAY.length; i++){

            u = TEXT_FILE_EDGE_ARRAY[i].getSource();
            v = TEXT_FILE_EDGE_ARRAY[i].getDest();
            wExpected = TEXT_FILE_EDGE_ARRAY[i].getWeight();
            wActual = testWordGraph.getEdge(u, v).getWeight();
            assertEquals("weight should be equal", wExpected, wActual, wActual - wExpected);

        }
    }

    //builds a 2d array based on a BFS search of the textFile word graph, then compares to a pre-made 2d array
    @Test
    public void testBFS(){
        buildBFSArray();
        for (int i = 0; i < TEXT_FILE_VERTEX_LIST.length; i++){
            for(int j = 0; j < TEXT_FILE_VERTEX_LIST.length; j++){
                assertEquals("source vertices should be equal", TEXT_FILE_BFS_ARRS[i][j], bfsTestArray[i][j]);
            }
        }
    }

    //tests priority queue as well as generate() method
    @Test
    public void testGenerateMethod(){
        buildWordGraph();
        for (int i = 0; i < STRING_ARRAY_FOR_GENERATE_METHOD.length; i++){
            String[] stringArray = autoSuggestModelTest.generate(autoSuggestModelTest, TEXT_FILE_VERTEX_LIST[i]);
            for (int j = 0; j < STRING_ARRAY_FOR_PRIORITY_QUEUE[i].length; j++){
                assertEquals("Phrases should match test array",
                       TEXT_FILE_VERTEX_LIST[i] +  " " + STRING_ARRAY_FOR_PRIORITY_QUEUE[i][j], stringArray[j]);
            }
        }
    }
}