package edu.miracosta.cs113.printerQueue;

import java.util.LinkedList;
import java.util.Queue; //necessary to utilize built in Queue interface

/**
 * OperatingSystem.Java
 *
 * Assigns jobs to print queues based on the number of pages to be printed
 * (less than 10 pages, less than 20 pages, or more than 20 pages)
 *
 * @author Reesha Rajen <rajen.reese@gmail.com>
 * @version  1.0
 * Last Modified: 3/17/2018
 */

public class OperatingSystem {

    //CONSTANTS
    public static final int STARTING_NUM = 1;

    //INSTANCE VARIABLES
    private int numPrinters;
    private int jobCounter = STARTING_NUM;
    private Printer[] printers;

    private Queue<PrintJob> highPriority = new LinkedList<>();
    private Queue<PrintJob> priority = new LinkedList<>();
    private Queue<PrintJob> lowPriority = new LinkedList<>();

    //METHODS
    /**
     * Operating System constructor which instantiates all printers
     * @param numPrinters
     */
    public OperatingSystem(int numPrinters) {
        this.setNumPrinters(numPrinters);
        printers = new Printer[numPrinters];

        //Instantiates all printers
        for(int i = 0; i < getNumPrinters(); i++) {
            printers[i] = new Printer("Printer " + (i+1));
        }
    }

    /**
     * Gets the number of printers
     * @return int with number of printers
     */
    public int getNumPrinters() {
        return this.numPrinters;
    }

    /**
     * Sets the number of printers
     * @param numPrinters
     */
    public void setNumPrinters(int numPrinters) {
        this.numPrinters = numPrinters;
    }

    /**
     * Creates a print job and offers it to the correct poll
     * @param numPages
     */
    public void creatingJob(int numPages) {
        PrintJob newJob = new PrintJob(numPages, jobCounter);
        //System.out.println("Receiving job " + newJob);

        if(newJob.getPriority() == 'H') {
            highPriority.offer(newJob);
        }
        else if(newJob.getPriority() == 'M') {
            priority.offer(newJob);
        }
        else {
            lowPriority.offer(newJob);
        }

        jobCounter += 1; //Incrementing the job count
    }

    /**
     * Runs the print job according to priority
     */
    public void runningJob() {
        for(int i = 0; i < this.numPrinters; i++) {
            if (highPriority.size() >= 1) {
                PrintJob temp;
                temp = highPriority.poll();
                printers[i].setCurrent(temp);

                while(!temp.isDone()) {
                    printers[i].setCurrent(temp);
                }
            }
            else if(priority.size() >= 1) {
                PrintJob temp;
                temp = priority.poll();
                printers[i].setCurrent(temp);

                while(!temp.isDone()) {
                    printers[i].setCurrent(temp);
                }
            }
            else if(lowPriority.size() >= 1) {
                PrintJob temp;
                temp = lowPriority.poll();
                printers[i].setCurrent(temp);

                while(!temp.isDone()) {
                    printers[i].setCurrent(temp);
                }
            }
        }
    }
}
