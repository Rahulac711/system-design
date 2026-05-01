package com.java.mutithreading.Runnable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SMSTask implements Runnable {
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("SMS task complete");
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}

class EmailTask implements Runnable {
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("Email task complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RunnableTest {
    public static void main(String args[]) throws Exception {
        SMSTask smsTask = new SMSTask();
        EmailTask emailTask = new EmailTask();

        Thread smsThread = new Thread(smsTask);
        Thread emailThread = new Thread(emailTask);
        System.out.println("Execution started");

        smsThread.start();
        System.out.println("Sms task started");
        emailThread.start();
        System.out.println("Email task started");


        try {
            smsThread.join();
            emailThread.join();
        } catch (InterruptedException e) {
            e.getCause().getMessage();
        }
    }
}