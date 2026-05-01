package com.java.mutithreading.PublisherConsumer;


import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PublisherConsumerdemo {

    // Buffer, publish, consume, blockingQueue/syncronized method

    static class Buffer {
        static final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);
    }

    static class Publish implements Runnable {
        int n = 0;

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Buffer.buffer.put(n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                n++;
                System.out.println(LocalDateTime.now() + ": Add in buffer");
            }
        }
    }

    static class Consume implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Buffer.buffer.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Remove from buffer");
            }
        }
    }

    static class fiveThreadAtATime {
        CountDownLatch startGate = new CountDownLatch(1);

        void example() {
            Callable<?> task = () -> {
                try {
                    startGate.await(); // wait
                    System.out.println(Thread.currentThread().getName() + " started");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return null;
            };
        }

    }
}

class PublisherConsumerMain {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new PublisherConsumerdemo.Publish());
        executorService.submit(new PublisherConsumerdemo.Publish());
        executorService.submit(new PublisherConsumerdemo.Consume());
        executorService.submit(new PublisherConsumerdemo.Consume());

        Thread.sleep(10_000);
        executorService.shutdownNow();
        System.out.println("Shutdown executor service");

        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Shutdown executor awaitTermination");
    }

}