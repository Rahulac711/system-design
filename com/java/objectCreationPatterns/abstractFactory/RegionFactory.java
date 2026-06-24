package com.java.objectCreationPatterns.abstractFactory;

public interface RegionFactory {
    PaymentGateway getPaymentGateWay(String type);

    void generateInvoice();
}
