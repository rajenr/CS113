package views;

/**
 * AutoSuggestView.java
 * Class containing methods for an AutoSuggestView object
 *
 * @version 1.0
 * @author Reesha Rajen
 * @author Danny Lee
 * @author Erik Anderson
 */

import models.AutoSuggestModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class AutoSuggestView extends JPanel implements KeyListener {

    // CONSTANTS
    public static final String HEADER = "Query";
    public static final String DISPLAY_START = "markov chain predictive text algorithm\n\nerik anderson\n" +
                                               "danny lee\nreesha rajen\n\nc. 2018";
    public static final String DISPLAY_FIRST_PANEL = "top suggestion";
    public static final String DISPLAY_SECOND_THIRD_PANEL = "next suggestion";

    // INSTANCE VARIABLES
    private JLabel header;
    private JTextArea displayLabel;
    private JTextArea firstGuess;
    private JTextArea secondGuess;
    private JTextArea thirdGuess;

    /** Model instance variable */
    private AutoSuggestModel model;

    // METHODS
    /**
     * This method constructs the AutoSuggestView object. It calls the super constructor and sets the background
     * to a specified color. It then sets a JLabel, as well as the JTextArea which implements the key listener.
     * This is the area that the user will type in.
     */
    public AutoSuggestView() {
        super();

        this.setBackground(new Color(41, 39, 39));

        this.header = new JLabel(HEADER);
        this.header.setForeground(Color.LIGHT_GRAY);

        this.displayLabel = new JTextArea(DISPLAY_START,26, 60);
        this.displayLabel.addKeyListener(this);
        this.displayLabel.setForeground(Color.DARK_GRAY);
        this.displayLabel.setBackground(new Color(41, 39, 39));
        this.displayLabel.setLineWrap(true);

        this.firstGuess = new JTextArea(DISPLAY_FIRST_PANEL, 1, 60);
        this.firstGuess.setForeground(Color.DARK_GRAY);
        this.firstGuess.setBackground(new Color(44, 42, 42));

        this.secondGuess = new JTextArea(DISPLAY_SECOND_THIRD_PANEL, 1, 60);
        this.secondGuess.setForeground(Color.DARK_GRAY);
        this.secondGuess.setBackground(new Color(44, 42, 42));

        this.thirdGuess = new JTextArea(DISPLAY_SECOND_THIRD_PANEL, 1, 60);
        this.thirdGuess.setForeground(Color.DARK_GRAY);
        this.thirdGuess.setBackground(new Color(44, 42, 42));

        this.buildPanelLook();
    }

    /**
     * This method constructs the graph utilized by the program. It instantiates the model instance varaible and builds
     * the graph off of the given text file. This is such that the program is able to have a text file to parse
     * from.
     */
    private void buildGraph() {
        model = AutoSuggestModel.buildOffTextFile();
    }

    /**
     * This method builds the look and layout of the panel, specifically. It utilizes BorderLayout of Java's Swing.
     */
    private void buildPanelLook() {
        this.add(this.headerPanel(), BorderLayout.NORTH);
        this.add(this.buildDisplayPanel(), BorderLayout.CENTER);
        this.add(this.buildFirstPanel(), BorderLayout.CENTER);
        this.add(this.buildSecondPanel(), BorderLayout.CENTER);
        this.add(this.buildThirdPanel(), BorderLayout.CENTER);
    }

    /**
     * This method builds the header panel itself and utilizes JPanel.
     * @return displayPanel object containing the JPanel as well as the specified font.
     */
    private JPanel headerPanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.setBackground(new Color(41, 39, 39));
        displayPanel.add(this.header);

        // Adding custom font
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Product Sans Bold.ttf"));
            this.header.setFont(font.deriveFont(45f));
            this.header.setBorder(new CompoundBorder( this.header.getBorder(),
                    new EmptyBorder(10, 10, 10, 10)));
        } catch(IOException ioe) {
            System.err.println("ERROR: Font file not found. Did you import correctly? Shutting down...");
            System.exit(0);
        } catch(FontFormatException ffe) {
            System.err.println("ERROR: Bad font. Did you replace the file? Corrupted in clone? Shutting down...");
            System.exit(0);
        }

        return displayPanel;
    }

    /**
     * This method builds the display panel itself and utilizes JPanel.
     * @return displayPanel object containing the JPanel as well as the specified font.
     */
    private JPanel buildDisplayPanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.setBackground(new Color(41, 39, 39));
        displayPanel.add(this.displayLabel);

        // Adding custom font
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Mechanical.otf"));
            this.displayLabel.setFont(font.deriveFont(18f));
            this.displayLabel.setBorder(new CompoundBorder( this.displayLabel.getBorder(),
                    new EmptyBorder(10, 10, 10, 10)));
        } catch(IOException ioe) {
            System.err.println("ERROR: Font file not found. Did you import correctly? Shutting down...");
            System.exit(0);
        } catch(FontFormatException ffe) {
            System.err.println("ERROR: Bad font. Did you replace the file? Corrupted in clone? Shutting down...");
            System.exit(0);
        }
        return displayPanel;
    }

    /**
     * This method builds the first panel itself and utilizes JPanel.
     * @return firstPanel object containing the JPanel as well as the specified font.
     */
    private JPanel buildFirstPanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.setBackground(new Color(41, 39, 39));
        displayPanel.add(this.firstGuess);

        // Adding custom font
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Mechanical.otf"));
            this.firstGuess.setFont(font.deriveFont(18f));
            this.firstGuess.setBorder(new CompoundBorder( this.firstGuess.getBorder(),
                    new EmptyBorder(10, 10, 10, 10)));
        } catch(IOException ioe) {
            System.err.println("ERROR: Font file not found. Did you import correctly? Shutting down...");
            System.exit(0);
        } catch(FontFormatException ffe) {
            System.err.println("ERROR: Bad font. Did you replace the file? Corrupted in clone? Shutting down...");
            System.exit(0);
        }

        return displayPanel;
    }

    /**
     * This method builds the second panel itself and utilizes JPanel.
     * @return secondPanel object containing the JPanel as well as the specified font.
     */
    private JPanel buildSecondPanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.setBackground(new Color(41, 39, 39));
        displayPanel.add(this.secondGuess);

        // Adding custom font
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Mechanical.otf"));
            this.secondGuess.setFont(font.deriveFont(18f));
            this.secondGuess.setBorder(new CompoundBorder( this.secondGuess.getBorder(),
                    new EmptyBorder(10, 10, 10, 10)));
        } catch(IOException ioe) {
            System.err.println("ERROR: Font file not found. Did you import correctly? Shutting down...");
            System.exit(0);
        } catch(FontFormatException ffe) {
            System.err.println("ERROR: Bad font. Did you replace the file? Corrupted in clone? Shutting down...");
            System.exit(0);
        }

        return displayPanel;
    }

    /**
     * This method builds the third panel itself and utilizes JPanel.
     * @return thirdPanel object containing the JPanel as well as the specified font.
     */
    private JPanel buildThirdPanel() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.setBackground(new Color(41, 39, 39));
        displayPanel.add(this.thirdGuess);

        // Adding custom font
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Mechanical.otf"));
            this.thirdGuess.setFont(font.deriveFont(18f));
            this.thirdGuess.setBorder(new CompoundBorder( this.thirdGuess.getBorder(),
                    new EmptyBorder(10, 10, 10, 10)));
        } catch(IOException ioe) {
            System.err.println("ERROR: Font file not found. Did you import correctly? Shutting down...");
            System.exit(0);
        } catch(FontFormatException ffe) {
            System.err.println("ERROR: Bad font. Did you replace the file? Corrupted in clone? Shutting down...");
            System.exit(0);
        }

        return displayPanel;
    }

    /**
     * This method is the getter method for the contents of the display. This is needed in order to display
     * keyboard input on the screen.
     * @return display contents
     */
    public String getDisplay() {
        return this.displayLabel.getText();
    }

    /**
     * This method gets the word that was last typed. This is in order to pass the word into the generate method
     * and then determine the adjacent paths from this vertex itself.
     * @return lastWord typed by the user.
     */
    public String getWordLastTyped() {
        int lastDelimiterPosition = this.displayLabel.getText().lastIndexOf(" ");
        String lastWord = this.displayLabel.getText().substring(lastDelimiterPosition + " ".length());

        return lastWord;
    }

    /**
     * Setter for display contents.
     * @param display set Query display to this value
     */
    public void setDisplay(String display) {
        this.displayLabel.setText(display);
        this.displayLabel.setForeground(Color.GREEN);
    }

    /**
     * Setter for display contents.
     * @param display set Query display to this value
     */
    public void setFirstDisplay(String display) {
        this.firstGuess.setText(display);
        this.firstGuess.setForeground(Color.GREEN);
    }

    /**
     * Setter for display contents.
     * @param display set Query display to this value
     */
    public void setSecondDisplay(String display) {
        this.secondGuess.setText(display);
        this.secondGuess.setForeground(Color.GREEN);
    }

    /**
     * Setter for display contents.
     * @param display set Query display to this value
     */
    public void setThirdDisplay(String display) {
        this.thirdGuess.setText(display);
        this.thirdGuess.setForeground(Color.GREEN);
    }

    /**
     * Setter for display contents. Concatenates the display with previous text, if necessary.
     * @param value set Query display to this value
     */
    private void concatDisplay(String value) {
        String currentDisplay = this.getDisplay();

        if (currentDisplay.equals(DISPLAY_START) || currentDisplay.length() == 0 || currentDisplay.equals("")) {
            this.setDisplay(value);
        }
        else {
            this.setDisplay(this.getDisplay() + "" + value);
        }
    }

    /**
     * Setter for display contents.
     * @param value set Query display to this value
     */
    private void concatFirstDisplay(String value) {
        this.setFirstDisplay(value);
    }

    /**
     * Setter for display contents.
     * @param value set Query display to this value
     */
    private void concatSecondDisplay(String value) {
        this.setSecondDisplay(value);
    }

    /**
     * Setter for display contents.
     * @param value set Query display to this value
     */
    private void concatThirdDisplay(String value) {
        this.setThirdDisplay(value);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // STUBBED METHOD
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_SPACE || keycode == KeyEvent.CHAR_UNDEFINED) {
            this.buildGraph();
            String[] temps = model.generate(model, getWordLastTyped());
            this.concatDisplay("");
            this.concatFirstDisplay(temps[0]);
            this.concatSecondDisplay(temps[1]);
            this.concatThirdDisplay(temps[2]);
        }
        else {
            this.concatDisplay( "");
            this.concatFirstDisplay("generating...");
            this.concatSecondDisplay("generating...");
            this.concatThirdDisplay("generating...");
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // STUBBED METHOD
    }
}
