package com.java.mutithreading.Syncronisation;

class PurchaseCounter {
    static int count = 0;

    public void increment() {
        count++;
    }

    static public void print() {
        System.out.println(count);
    }
}

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        PurchaseCounter purchaseCounter = new PurchaseCounter();

        Runnable task = () -> {
                for(int i=0; i<1000; i++) {
                    purchaseCounter.increment();
                }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        PurchaseCounter.print();
    }
}
