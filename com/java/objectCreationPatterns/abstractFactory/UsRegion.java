package com.java.objectCreationPatterns.abstractFactory;

public class UsRegion implements RegionFactory {

    @Override
    public PaymentGateway getPaymentGateWay(String type) {
        return switch (type) {
            case ("paypal") -> new Paypal();
            case ("stripe") -> new Stripe();
            default -> throw new IllegalStateException(type + " Is not present in US");
        };
    }

    @Override
    public void generateInvoice() {
        Invoice invoiceUS = new InvoiceUS();
        invoiceUS.generateInvoice();
    }

}
