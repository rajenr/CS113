package edu.miracosta.cs113.printerQueue;

/**
 * PrinterQueue.Java
 *
 * Driver program for the Printer Queues
 *
 * @author Reesha Rajen <rajen.reese@gmail.com>
 * @version  1.0
 * Last Modified: 3/17/2018
 */

public class PrinterQueue {

    public static void main(String[] args) {

        int randomInt;
        OperatingSystem printerQueue;

        randomInt = 1 + (int)(Math.random() * 3);
        printerQueue = new OperatingSystem(randomInt);

        System.out.println("Number of printers: " + printerQueue.getNumPrinters());

        for(int i = 0; i < 100; i++) {
            printerQueue.creatingJob(1 + (int)(Math.random() * 50));
        }

        for(int i = 0; i < 100; i++) {
            printerQueue.runningJob();
        }

    }
}
