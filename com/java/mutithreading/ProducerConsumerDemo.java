package com.java.mutithreading;


import java.util.LinkedList;
import java.util.Queue;


class ShutdownController {
    volatile boolean running = true;
}

class BoundedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(int item) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait();
        }
        buffer.add(item);
        System.out.println(Thread.currentThread().getName() +
                " produced: " + item);
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        int item = buffer.poll();
        System.out.println(Thread.currentThread().getName() +
                " consumed: " + item);
        notifyAll();
        return item;
    }
}


class Producer implements Runnable {
    private final BoundedBuffer buffer;
    private final ShutdownController controller;
    private static int value = 0;

    public Producer(BoundedBuffer buffer, ShutdownController controller) {
        this.buffer = buffer;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            while (controller.running) {
                buffer.put(++value);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " stopped");
    }
}


class Consumer implements Runnable {
    private final BoundedBuffer buffer;
    private final ShutdownController controller;

    public Consumer(BoundedBuffer buffer, ShutdownController controller) {
        this.buffer = buffer;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            while (controller.running) {
                buffer.take();
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " stopped");
    }
}


public class ProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer buffer = new BoundedBuffer(5);
        ShutdownController controller = new ShutdownController();

        Thread p1 = new Thread(new Producer(buffer, controller), "com.java.mutithreading.Producer-1");
        Thread p2 = new Thread(new Producer(buffer, controller), "com.java.mutithreading.Producer-2");
        Thread c1 = new Thread(new Consumer(buffer, controller), "com.java.mutithreading.Consumer-1");
        Thread c2 = new Thread(new Consumer(buffer, controller), "com.java.mutithreading.Consumer-2");

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        // Let system run for 10 seconds
        Thread.sleep(10_000);

        // Trigger shutdown
        controller.running = false;

        // Wake up any waiting threads
        synchronized (buffer) {
            buffer.notifyAll();
        }

        // Wait for threads to finish
        p1.join();
        p2.join();
        c1.join();
        c2.join();

        System.out.println("All threads stopped. System shutdown cleanly.");
    }
}
