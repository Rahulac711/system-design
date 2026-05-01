package com.java.parkinglot.domain;

import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID ticketId;
    private double amount;
    private PaymentGateway paymentGateway;
    private PaymentStatus paymentStatus;

    public enum PaymentGateway {
        RAZORPAY, STRIPE;
    }

    public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }

}
