package com.java.behavioralDesignPattern.statePattern;

class OrderContext {
    OrderState currentState;

    OrderContext() {
       currentState = new PlacedState();
    }

    void setState(OrderState orderState) {
        currentState = orderState;
    }

    void next() {
        currentState.next(this);
    }

    void cancel() {
        currentState.cancel(this);
    }

    public String getCurrentState() {
        return currentState.getStateName();
    }
}

interface OrderState {
    void next(OrderContext orderContext);
    void cancel(OrderContext orderContext);
    String getStateName();
}

class PlacedState implements OrderState {
    public void next(OrderContext orderContext) {
        orderContext.setState(new PreaparingState());
        System.out.println("Order is now being prepared.");
    }
    public void cancel(OrderContext orderContext) {
        orderContext.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }
    public String getStateName() {
        return "ORDER_PLACED";
    }
}

class PreaparingState implements OrderState {
    public void next(OrderContext orderContext) {
        orderContext.setState(new OutForDeliveryState());
        System.out.println("Order is out for delivery");
    }
    public void cancel(OrderContext orderContext) {
        orderContext.setState(new CancelledState());
        System.out.println("Order has been cancelled");
    }
    public String getStateName() {
        return "ORDER_PREPARING";
    }
}

class OutForDeliveryState implements OrderState {
    public void next(OrderContext orderContext) {
        orderContext.setState(new DeliveredState());
        System.out.println("Order is delivered");
    }
    public void cancel(OrderContext orderContext) {
        System.out.println("Cannot cancel. Order is out for delivery.");
    }
    public String getStateName() {
        return "OUT_FOR_DELIVERY";
    }
}

class DeliveredState implements OrderState {
    public void next(OrderContext orderContext) {
        System.out.println("Order is already delivered.");
    }
    public void cancel(OrderContext orderContext) {
        System.out.println("Cannot cancel. Order is out for delivery.");
    }
    public String getStateName() {
        return "_DELIVERED";
    }
}

class CancelledState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Cancelled order cannot move to next state.");
    }

    public void cancel(OrderContext context) {
        System.out.println("Order is already cancelled.");
    }

    public String getStateName() {
        return "CANCELLED";
    }
}


public class StatePattern {
    public static void main(String []args) {
        OrderContext order = new OrderContext();

        order.next();
        order.next();
        order.next();
        order.cancel();
    }

}
