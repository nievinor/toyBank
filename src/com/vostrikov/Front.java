package com.vostrikov;

import java.util.PriorityQueue;

public class Front {

    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;
//    private Queue transfer1 = ;


    public Front(String packet, boolean transfer) {
        this.packet = packet;
        this.transfer = transfer;
    }

    PriorityQueue<Integer> myPriorityQueue = new PriorityQueue<Integer>(2);

    public Front(PriorityQueue<Integer> myPriorityQueue) {
        this.myPriorityQueue = myPriorityQueue;
    }

    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
//                Log.error("Thread interrupted", e);
            }
        }
        transfer = false;

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
//                Log.error("Thread interrupted", e);
            }
        }
        transfer = true;

        notifyAll();
        return packet;
    }

}
