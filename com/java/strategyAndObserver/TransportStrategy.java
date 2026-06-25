package com.java.strategyAndObserver;

import java.util.Scanner;
public interface TransportStrategy {
    public int calculateFare(int distance);   
    public boolean isAvailable();
}

class Bus implements TransportStrategy{
    @Override
    public int calculateFare(int distance){
        return distance*10;
    }
    public boolean isAvailable(){
        return false;
    }
}


class Train implements TransportStrategy{
    @Override
    public int calculateFare(int distance){
        return distance*5;
    }

    public boolean isAvailable(){
        return true;
    }
}

class Flight implements TransportStrategy{
    @Override
    public int calculateFare(int distance){
        return distance*25;
    }
    public boolean isAvailable(){
        return false;
    }

    public boolean isnotAvailable(){
        return false;
    }

}

class TransportProcessor{
    private TransportStrategy transportStrategy;

    public void setTransportStrategy(TransportStrategy transportStrategy)
    {
    this.transportStrategy = transportStrategy;
    }

    public int setFare(int distance){
        return transportStrategy.calculateFare(distance);
    }

    public boolean isAvailable(){
        return transportStrategy.isAvailable();
    }
    

}

class StrategyPattern{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        TransportProcessor transportProcessor = new TransportProcessor();
        String transportType = in.nextLine();
        int distanceTravelled = in.nextInt();
        switch (transportType) {
            case "Bus":
                transportProcessor.setTransportStrategy(new Bus());
                if(transportProcessor.isAvailable()){
                    System.out.println(transportProcessor.setFare(distanceTravelled));
                }
                else{
                    System.out.println("Bus not available");
                }
                break;
            case "Train":
                transportProcessor.setTransportStrategy(new Train());
                System.out.println(transportProcessor.setFare(distanceTravelled));
                break;
            case "Flight":
                transportProcessor.setTransportStrategy(new Flight());
                System.out.println(transportProcessor.setFare(distanceTravelled));
                System.out.println(transportProcessor.setFare(distanceTravelled));
                break;
            default:
                System.out.println("Invalid transport type");
                break;
        }
        
    }
}