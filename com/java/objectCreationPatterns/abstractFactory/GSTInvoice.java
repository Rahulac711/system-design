package com.java.objectCreationPatterns.abstractFactory;

public class GSTInvoice implements Invoice {

    @Override
    public void generateInvoice() {
        System.out.println("Generating Invoice for India");
    }

}
