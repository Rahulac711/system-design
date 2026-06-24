package com.java.objectCreationPatterns.singleton;

public class SingletonStaticClassHolder {
    // Private constructor
    private SingletonStaticClassHolder() {
    }

    // Static inner class to hold the Singleton instance
    private static class Holder {
        private static final SingletonStaticClassHolder INSTANCE = new SingletonStaticClassHolder();
    }


    // Public method to return the Singleton instance
    public static SingletonStaticClassHolder getInstance() {
        return Holder.INSTANCE;
    }
}