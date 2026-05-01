package com.java.objectCreationPatterns.abstractFactory;

public class InvoiceUS implements Invoice {

    @Override
    public void generateInvoice() {
        System.out.println("Generating Invoice for US");
    }

}
