package edu.miracosta.cs113.printerQueue;

/**
 * Printer.Java
 *
 * Builds an individual Printer object with an identification and a current PrintJob object
 *
 * @author Reesha Rajen <rajen.reese@gmail.com>
 * @version  1.0
 * Last Modified: 3/17/2018
 */

public class Printer {
    //CONSTANTS
    public static final String DEFAULT_NAME = "Printer 0";
    public static final int STARTING_TIME = 0;

    //INSTANCE VARIABLES
    private String printerName;
    private PrintJob current;
    private int timeElapsed = STARTING_TIME;

    //METHODS

    /**
     * Constructor for Printer class
     * @param printerName specifies the name of the printer
     */
    public Printer(String printerName) {
        this.printerName = printerName;
    }

    /**
     * Sets the current PrintJob and calls the printNow() method, increments elapsed time
     * @param current PrintJob object
     */
    public void setCurrent(PrintJob current) {
        this.current = current;
        this.printNow();
        timeElapsed += 1;
    }

    /**
     * Calls print() method of PrintJob and simulates print function, formats output
     */
    public void printNow() {
        System.out.printf("%s is now printing:  JOB %-3s  %2s PAGES  PRIORITY %s  (%d mins elapsed)%n", this.printerName, this.current.getJobNumber(), this.current.getNumPages(), this.current.getPriority(), this.timeElapsed);
        this.current.print();

    }
}
