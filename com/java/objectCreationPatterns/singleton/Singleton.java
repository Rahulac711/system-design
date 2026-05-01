package com.java.objectCreationPatterns.singleton;

/*
1. Eager Loading -> takes more memory, thread safe
2. Lazy Loading -> take less memory, on demand created, not thread safe
    a. use Synchronized keyword on getInstanceMethod()
    b. use Volatile keyword and synchronize keyword when creating the instance with Double Checked Locking
    c. use static block in the class which will be called in getInstanceMethod() method one time only.
        Most optimized way in lazy loading.
*/

class ParentSingleton {

    public static class SynchronizedSingleton {
        // Object declaration
        private static SynchronizedSingleton instance;

        // Private constructor
        private SynchronizedSingleton() {
        }

        // Synchronized keyword used
        public static synchronized SynchronizedSingleton getInstance() {
            if (instance == null) {
                instance = new SynchronizedSingleton();
            }
            return instance;
        }
    }

    public static class DoubleCheckedLockingSingleton {
        // Volatile object declaration
        private static volatile DoubleCheckedLockingSingleton instance;

        // Private constructor
        private DoubleCheckedLockingSingleton() {}

        // Thread-safe method using double-checked locking
        public static DoubleCheckedLockingSingleton getInstance() {
            if (instance == null) {
                synchronized (DoubleCheckedLockingSingleton.class) {
                    if (instance == null) {
                        instance = new DoubleCheckedLockingSingleton();
                    }
                }
            }
            return instance;
        }
    }

    //Bill Pugh Singleton (Best Practice for Lazy Loading)
    public class BillPughSingleton {
        private BillPughSingleton() {
        }

        public static class BillPughHolder {
            private static final BillPughHolder INSTANCE = new BillPughHolder();
        }

        public BillPughHolder getInstance() {
            return BillPughHolder.INSTANCE;
        }
    }
}