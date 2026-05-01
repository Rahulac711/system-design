package com.java.mutithreading.Syncronisation;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


/*
synchronized used Object Monitor Locking.
In Java every object has an intrinsic lock
only one thread can acquire that lock at a time.
 */
class PurchaseCounterSyncMethod {
    static int count = 0;

    // method
    public synchronized void increment() {
        count++;
    }

    // block
    public void incrementSyncBlock() {
        synchronized (this) {
            count++;
        }
    }

    public void print() {
        System.out.println(count);
    }
}

/*
volatile keyword ensures visibility not atomicity (single operation)
volatile keyword only ensure that the latest value of the count is visible across thread
c++ is still not atomic

Use it only when -> one thread writes, others only read.

Core guarantees if volatile
Visibility : change made by one thread are immediately visible to other thread
No Caching : value is always read and write to main memory (not cached copy in CPU registered)
No Atomicity : Does not make operation atomic
 */
class VolatileKeyword {
    static volatile int count = 0;

    // method
    public void increment() {
        // read, increment and write
        count++;
    }

    public void print() {
        System.out.println(count);
    }
}

/*
AtomicInteger and AtomicBoolean
Both of them use Compare and Set at hardware level.
It is lock free and highly performant.

CAS concept:
    - Think of like "If value is what I expected, then set it to new value"
    - Prevents race condition without locking

    Pros-
        High performance
        Non-blocking
    Cons-
        May fall under high contention (too many retires)

What enabled the atomicity?
    - The magic is in hardware level lock-free instruction and Java's unsafe class under the hood
    - Only one thread can win and this is guaranteed by the atomicity at the hardware level
 */
class AtomicIntegerDemo {
    private AtomicInteger like = new AtomicInteger(0);
    void increment() {
        int prev, next;

        do {
            prev = like.get();
            next = prev + 1;
        } while (!like.compareAndSet(prev, next)); // atomic task, hardware allows only one thread at a
        // time to preform CAS
    }

    void printLikes() {
        System.out.println("Likes: " +  like.get());
    }

}


public class Synronization {
    public static void main(String [] args) throws InterruptedException {
        PurchaseCounterSyncMethod purchaseCounter = new PurchaseCounterSyncMethod();
        VolatileKeyword volatileKeyword = new VolatileKeyword();
        AtomicIntegerDemo atomicIntegerDemo = new AtomicIntegerDemo();
        Runnable task = () -> {
            for(int i=0; i<1000; i++) {
//                purchaseCounter.incrementSyncBlock();
//                volatileKeyword.increment();
                atomicIntegerDemo.increment();
            }
        };

        Thread thread = new Thread(task);
        Thread thread1 = new Thread(task);

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

//        purchaseCounter.print();
//        volatileKeyword.print();
        atomicIntegerDemo.printLikes();

    }
}
