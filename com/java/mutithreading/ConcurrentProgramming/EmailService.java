package com.java.mutithreading.ConcurrentProgramming;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailService {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    static Thread sendEmail(int id) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalDateTime.now() +" Email send to: "+ id);

        });
        return thread;
    }

    public static void main(String args[]) {
        for(int i=0; i<100; i++) {
            executorService.execute(sendEmail(i));
        }
        executorService.shutdown();
    }
}
