package com.java.behavioralDesignPattern.strategyPattern;

interface RideMathingStrategy {
    void match(String location);
}

class AirportRideMatchingStrategy implements RideMathingStrategy {
    public void match(String location) {
        System.out.println("Matching rider at " + location + " from airport queue.");
    }
}

class NearestRideMatchingStategy implements RideMathingStrategy {
    public void match(String location) {
        System.out.println("Matching rider at " + location + " with nearest driver.");
    }
}

class SurgePriorityRideMathingStrategy implements RideMathingStrategy {
    public void match(String location) {
        System.out.println("Matching rider at " + location + " based on surge pricing priority.");
    }
}

class RideMatchingService {
    RideMathingStrategy rideMathingStrategy;

    RideMatchingService(RideMathingStrategy rideMathingStrategy) {
        this.rideMathingStrategy = rideMathingStrategy;
    }

    void setRideMathingStrategy(RideMathingStrategy rideMathingStrategy) {
        this.rideMathingStrategy = rideMathingStrategy;
    }

    void matchRide(String location) {
        rideMathingStrategy.match(location);
    }
}

public class StrategyPattern {
    public static void main(String []args) {
        RideMatchingService rideMatchingService = new RideMatchingService(new AirportRideMatchingStrategy());
        rideMatchingService.matchRide("Near gate 1");

        RideMatchingService rideMatchingService1 = new RideMatchingService(new SurgePriorityRideMathingStrategy());
        rideMatchingService1.matchRide("S B Patil road");
        rideMatchingService1.setRideMathingStrategy(new NearestRideMatchingStategy());
        rideMatchingService1.matchRide("S B Patil road");
    }
}
