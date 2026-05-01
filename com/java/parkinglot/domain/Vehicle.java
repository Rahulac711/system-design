package com.java.parkinglot.domain;

import java.util.UUID;

public class Vehicle {
    private UUID id;
    private String licensePlate;
    private VehicleType vehicleType;

    public enum VehicleType {
        BIKE, CAR, TRUCK, EV
    }
}
