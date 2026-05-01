package com.java.objectCreationPatterns.abstractFactory;

import java.math.BigDecimal;

public interface PaymentGateway {
    void processPayment(BigDecimal amount);
}
