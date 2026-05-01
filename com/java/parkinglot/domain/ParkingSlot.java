package com.java.parkinglot.domain;

import java.util.UUID;

public class ParkingSlot {
    private UUID id;
    private boolean isOccupied;
    private Vehicle.VehicleType vehicleType;
    private int floorNumber;

    public ParkingSlot(Vehicle.VehicleType vehicleType, int floorNumber) {
        id = UUID.randomUUID();
        isOccupied = false;
        this.vehicleType = vehicleType;
        this.floorNumber = floorNumber;
    }
}
