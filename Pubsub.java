//import java.util.LinkedList;
//import java.util.Queue;
//
//// Shared Buffer
//class com.java.mutithreading.BoundedBuffer {
//    private final Queue<Integer> buffer = new LinkedList<>();
//    private final int capacity;
//
//    public com.java.mutithreading.BoundedBuffer(int capacity) {
//        this.capacity = capacity;
//    }
//
//    // com.java.mutithreading.Producer puts item
//    public synchronized void put(int item) throws InterruptedException {
//        while (buffer.size() == capacity) {
//            wait(); // wait until space is available
//        }
//        buffer.add(item);
//        System.out.println(Thread.currentThread().getName() +
//                " produced: " + item + " | Buffer size: " + buffer.size());
//        notifyAll(); // notify waiting consumers
//    }
//
//    // com.java.mutithreading.Consumer takes item
//    public synchronized int take() throws InterruptedException {
//        while (buffer.isEmpty()) {
//            wait(); // wait until item is available
//        }
//        int item = buffer.poll();
//        System.out.println(Thread.currentThread().getName() +
//                " consumed: " + item + " | Buffer size: " + buffer.size());
//        notifyAll(); // notify waiting producers
//        return item;
//    }
//}
//
//// com.java.mutithreading.Producer Thread
//class com.java.mutithreading.Producer implements Runnable {
//    private final com.java.mutithreading.BoundedBuffer buffer;
//    private static int value = 0;
//
//    public com.java.mutithreading.Producer(com.java.mutithreading.BoundedBuffer buffer) {
//        this.buffer = buffer;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                buffer.put(++value);
//                Thread.sleep(500); // simulate production time
//            }
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//}
//
//// com.java.mutithreading.Consumer Thread
//class com.java.mutithreading.Consumer implements Runnable {
//    private final com.java.mutithreading.BoundedBuffer buffer;
//
//    public com.java.mutithreading.Consumer(com.java.mutithreading.BoundedBuffer buffer) {
//        this.buffer = buffer;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                buffer.take();
//                Thread.sleep(800); // simulate processing time
//            }
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//}
//
//// Main Class
//public class com.java.mutithreading.ProducerConsumerDemo {
//    public static void mainn(String[] args) {
//        com.java.mutithreading.BoundedBuffer buffer = new com.java.mutithreading.BoundedBuffer(5);
//
//        Thread p1 = new Thread(new com.java.mutithreading.Producer(buffer), "com.java.mutithreading.Producer-1");
//        Thread p2 = new Thread(new com.java.mutithreading.Producer(buffer), "com.java.mutithreading.Producer-2");
//        Thread c1 = new Thread(new com.java.mutithreading.Consumer(buffer), "com.java.mutithreading.Consumer-1");
//        Thread c2 = new Thread(new com.java.mutithreading.Consumer(buffer), "com.java.mutithreading.Consumer-2");
//
//        p1.start();
//        p2.start();
//        c1.start();
//        c2.start();
//    }
//}
