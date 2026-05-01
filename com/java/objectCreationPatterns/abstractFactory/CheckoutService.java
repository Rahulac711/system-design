package com.java.objectCreationPatterns.abstractFactory;

import java.math.BigDecimal;

public class CheckoutService {

    String paymentGateway;

    public CheckoutService() {}

    public CheckoutService(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    void checkoutOld(int amount) {
        PaymentGateway paymentGateway1 = PaymentGatewayFactory.getPaymentGateway(this.paymentGateway);
        paymentGateway1.processPayment(java.math.BigDecimal.valueOf(amount));

        Invoice invoice = new GSTInvoice();
        invoice.generateInvoice();
    }

    void checkout(RegionFactory region, String paymentGatewayType, int amount) {
        PaymentGateway paymentGateway = region.getPaymentGateWay(paymentGatewayType);
        paymentGateway.processPayment(BigDecimal.valueOf(amount));
        region.generateInvoice();
    }

}
