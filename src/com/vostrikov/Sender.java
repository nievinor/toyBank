package com.vostrikov;

import com.vostrikov.test.Data;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Sender {

    private Front front;
    private final ArrayList<Request> clientList;

    public Sender(Front front, ArrayList<Request> clientList) {
        this.front = front;
        this.clientList = clientList;
    }

    // standard constructors

    public void run() {
        String packets[] = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"
        };

        for (String packet : packets) {
            front.send(packet);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
//                Log.error("Thread interrupted", e);
            }
        }
    }
}
