package com.java.mutithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SMSTask implements Callable<String> {
    public String call() throws InterruptedException {
        Thread.sleep(5000);
        return "SMS task complete";
    }
}

class EmailTask implements Runnable {
    public void run() {
        try {
            Thread.sleep(4000);
            System.out.println("Email task complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ETACalculate implements Runnable {
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("ETA calculated");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CallablePractice {
    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        SMSTask smsTask = new SMSTask();
        EmailTask emailTask = new EmailTask();
        ETACalculate etaCalculate = new ETACalculate();

        Future<String> future = executorService.submit(smsTask);
        System.out.println("SmsTask started");
        executorService.submit(emailTask);
        System.out.println("Email Task started");
        executorService.submit(etaCalculate);
        System.out.println("ETA Task started");

        try {
            System.out.println("[SMS] "+ future.get());
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        }
        executorService.shutdown();
    }
}
