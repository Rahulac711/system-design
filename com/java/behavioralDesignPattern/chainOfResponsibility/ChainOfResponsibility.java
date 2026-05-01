package com.java.behavioralDesignPattern.chainOfResponsibility;

/*
Behavioral design patterns are focused on the interaction and delegation of responsibilities between objects.
One such pattern is the Chain of Responsibility Pattern, which allows a request to be passed along a chain of
potential handlers until one of them processes it. This pattern is particularly useful for scenarios where
multiple handlers might be responsible for processing a request, and we want to avoid tightly coupling the
sender of the request to the receivers.

Imagine you're building a customer support system with different levels of support, such as basic inquiries,
technical issues, and advanced troubleshooting. A customer can send in a request, and depending on its complexity,
 the request should be forwarded to the appropriate team. Instead of each team individually checking if they can
 handle every possible request, the Chain of Responsibility Pattern sets up a chain where each team can either
 process the request or pass it to the next team in the chain. This enables a flexible and extensible system where
  adding new handlers (teams) is easy and doesn't require changes to the existing code.

Chain of Responsibility

Formal Definition
The Chain of Responsibility Pattern is a behavioral design pattern that transforms particular behaviors into
standalone objects called handlers. It allows a request to be passed along a chain of handlers, where each
handler decides whether to process the request or pass it to the next handler in the chain.

This pattern decouples the sender of a request from its receivers, giving multiple objects a chance to handle the object.
Key Components
This pattern consists of the following components:
Handler: An abstract class or interface that defines the method for handling requests and a reference to the next handler in the chain.
Concrete Handler: A class that implements the handler and processes the request if it can. Otherwise, it forwards the request to the next handler.
Client: The object that sends the request, typically unaware of the specific handler that will process it.
*/

abstract class SupportHandler {
    SupportHandler nextHandler;

    void setNextHandler(SupportHandler supportHandler) {
        nextHandler = supportHandler;
    }

    abstract void handleRequest(String request);
}
//General, Billing, Technical, Delivery
class GeneralSupport extends SupportHandler {
    public void handleRequest(String request) {
        if(request.equalsIgnoreCase("General")) {
            System.out.println("[General] Request is handled");
        } else if(nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class BillingSupport extends SupportHandler {
    public void handleRequest(String request) {
        if(request.equalsIgnoreCase("Billing")) {
            System.out.println("[Billing] Request is handled");
        } else if(nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class TechnicalRequest extends SupportHandler {
    public void handleRequest(String request) {
        if(request.equalsIgnoreCase("Technical")) {
            System.out.println("[Technical] Request is handled");
        } else if(nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class DeliveryRequest extends SupportHandler {
    public void handleRequest(String request) {
        if(request.equalsIgnoreCase("Delivery")) {
            System.out.println("[Delivery] Request is handled");
        } else if(nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Unknown request cannot be handled");
        }
    }
}

public class ChainOfResponsibility {
    public static void main(String argsp[]) {
        SupportHandler generalSupport = new GeneralSupport();
        SupportHandler billingSupport = new BillingSupport();
        SupportHandler technicalSupport = new TechnicalRequest();
        SupportHandler deliverySupport = new DeliveryRequest();

        generalSupport.setNextHandler(billingSupport);
        billingSupport.setNextHandler(technicalSupport);
        technicalSupport.setNextHandler(deliverySupport);
        deliverySupport.setNextHandler(null);

        generalSupport.handleRequest("billing");
        generalSupport.handleRequest("delivery");
        generalSupport.handleRequest("general");
        deliverySupport.handleRequest("delivery");
        generalSupport.handleRequest("unknown");

    }
}
/*
How Chain of Responsibility Fixes the Previously Discussed Issues
Issue	Solution in Refactored Code
Violation of the Open-Closed Principle:	Now, new types of requests can be handled by adding a new handler class without
 modifying the existing code. Each handler is open for extension and closed for modification.
Monolithic Code:	The logic is now separated into individual handler classes, each responsible for one type of
request, making the code more modular and easier to maintain.
Scalability and Flexibility:	The chain of responsibility allows new handlers to be easily added without changing
 the existing logic. The order of handling can be changed by simply rearranging the chain.
*/