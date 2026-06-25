package com.java.strategyAndObserver;

public interface PaymentStrategy {
    
    public void pay(); 

}

class CreditCardPayment implements PaymentStrategy{

    @Override
    public void pay(){
        System.out.println("Payment done using Credit Card");
    }

    public void CreditCardPayment(int amount){
        System.out.println("Credit Card Payment");
    }


}

class DebitCardPayment implements PaymentStrategy{

    @Override
    public void pay(){
        System.out.println("Payment done using Debit Card");
    }

    public void DebitCardPayment(int amount){
        System.out.println("Debit Card Payment");
    }


}

class UPIPayment implements PaymentStrategy{

    @Override
    public void pay(){
        System.out.println("Payment done using Debit Card");
    }

    public void UPIPayment(int amount){
        System.out.println("UPI  Payment");
    }

}

class PaymentProcessor{
    private PaymentStrategy paymentStrategy;

    PaymentProcessor(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void setpay(){
        paymentStrategy.pay();
    }
}

class PaymentSystem{
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor(new CreditCardPayment());
        paymentProcessor.setpay();
    }
}