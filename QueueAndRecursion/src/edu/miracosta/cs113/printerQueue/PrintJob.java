package edu.miracosta.cs113.printerQueue;

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

public class PrintJob {

    //CONSTANTS
    public static final int DEFAULT_PAGES = 0;
    public static final int DEFAULT_JOBNUM = 0;

    public static final char HIGH = 'H';
    public static final char MED = 'M';
    public static final char LOW = 'L';

    //INSTANCE VARIABLES
    private int numPages;
    private int jobNumber;
    private char priority;

    //METHODS

    /**
     * Default constructor for PrintJob
     */
    public PrintJob() {
        this.setAll(DEFAULT_PAGES, DEFAULT_JOBNUM);
    }

    /**
     * PrintJob constructor, does not allow the user to set priority
     * @param numPages
     * @param jobNumber
     */
    public PrintJob(int numPages, int jobNumber) {
        this.setAll(numPages, jobNumber);
        this.setPriority();
    }

    /**
     * Sets the number of pages
     * @param numPages
     */
    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    /**
     * Sets the job number
     * @param jobNumber
     */
    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * Sets the priority of a print job (assigned based on number of pages)
     */
    public void setPriority() {
        if(this.numPages < 10) {
            this.priority = HIGH;
        }
        else if (this.numPages < 20) {
            this.priority = MED;
        }
        else {
            this.priority = LOW;
        }
    }

    /**
     * Sets all parameters for a PrintJob
     * @param numPages
     * @param jobNumber
     */
    public void setAll(int numPages, int jobNumber) {
        this.setNumPages(numPages);
        this.setJobNumber(jobNumber);
        this.setPriority();
    }

    /**
     * Gets the number of pages
     * @return int with pages in PrintJob
     */
    public int getNumPages() {
        return this.numPages;
    }

    /**
     * Gets the job number
     * @return int with PrintJob assignment number
     */
    public int getJobNumber() {
        return this.jobNumber;
    }

    /**
     * Gets the priority of a PrintJob
     * @return char with priority
     */
    public char getPriority() {
        return this.priority;
    }

    /**
     * toString method
     * @return string containing all instance variables
     */
    @Override
    public String toString() {
        return "Job Number: " + this.jobNumber + ", Pages: " + this.numPages + ", Priority: " + this.priority;
    }

    /**
     * Returns true if the PrintJob is finished, otherwise returns false
     * @return boolean
     */
    public boolean isDone() {
        if(this.numPages > 0) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Simulates printing of 10 pages
     */
    public void print() {
        this.numPages -= 10;
    }


}
