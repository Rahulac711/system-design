package com.java.structuralDesignPattern.facadePattern;


/*
Facade Pattern
The Facade Pattern is a structural design pattern that provides a simplified, unified interface to a
complex subsystem or group of classes.

It acts as a single entry point for clients to interact with the system, hiding the underlying complexity
and making the system easier to use.
 */

class PaymentService {
    public void makePayment(double cost) {
        System.out.println("Payment made for "+cost);
    }
}

class SeatreservationService {
    public void reserveSeat(String seatDetails){
        System.out.println("Seat reserved "+seatDetails);
    }
}

class GenerateTicketService {
    public void generateTicketService(int movieId, int seatNumber) {
        System.out.println("Seat is generated for movie "+movieId+ " and seat "+seatNumber);
    }
}

class LoyaltiPointService {
    public void createLoyalityPoints(String accountNumber) {
        System.out.println("Loyalti point created "+ accountNumber);
    }
}

class NotificationService {
    public void sendNotification() {
        System.out.println("Notiifcation sent to user");
    }
}

class BookMovieTicketFacade {
    private final SeatreservationService seatreservationService;
    private final GenerateTicketService generateTicketService;
    private final LoyaltiPointService loyaltiPointService;
    private final NotificationService notificationService;
    private final PaymentService paymentService;

    public BookMovieTicketFacade() {
        this.paymentService = new PaymentService();
        this.generateTicketService = new GenerateTicketService();
        this.notificationService = new NotificationService();
        this.seatreservationService = new SeatreservationService();
        this.loyaltiPointService = new LoyaltiPointService();
    }

    public void bookMoviewTicket(String accountNumber, double cost, int moviewId, int seatNumber, String seatDetailsDto) {
        paymentService.makePayment(cost);
        seatreservationService.reserveSeat(seatDetailsDto);
        generateTicketService.generateTicketService(moviewId, seatNumber);
        loyaltiPointService.createLoyalityPoints(accountNumber);
        notificationService.sendNotification();
    }
}

public class FacadePattern {
    public static void main(String args[]) {
        BookMovieTicketFacade bookMovieTicketFacade = new BookMovieTicketFacade();
        bookMovieTicketFacade.bookMoviewTicket("123", 50, 5323, 7,
        "Row 7 seat 7");

    }
}