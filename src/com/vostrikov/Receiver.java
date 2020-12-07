package com.vostrikov;

import com.vostrikov.test.Data;

import java.util.concurrent.ThreadLocalRandom;

public class Receiver {

    private Front load;

    public Receiver(Front front) {
        this.load = front;
    }

    // standard constructors

    public void run() {
        for(String receivedMessage = load.receive();
            !"End".equals(receivedMessage);
            receivedMessage = load.receive()) {

            System.out.println(receivedMessage);

            // ...
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
//                Log.error("Thread interrupted", e);
            }
        }
    }

}
