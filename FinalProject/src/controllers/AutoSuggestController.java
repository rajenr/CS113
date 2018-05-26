package controllers;

/**
 * AutoSuggestController.java
 * Class containing methods for an AutoSuggestController object
 *
 * @version 1.0
 * @author Reesha Rajen
 * @author Danny Lee
 * @author Erik Anderson
 */

import views.AutoSuggestView;

import javax.swing.*;

public class AutoSuggestController extends JFrame {

    // CONSTANTS
    public static final int WIDTH = 800;
    public static final int HEIGHT = 850;


    /**
     * This method generates a controller for an AutoSuggestView object. It will add the AutoSuggestView object
     * to a JFrame, that displays the GUI on the screen for the user to interact with. The GUI is not resizeable,
     * as indicated.
     */
    public AutoSuggestController() {
        super();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Query");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new AutoSuggestView());
    }
}
