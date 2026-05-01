package com.java.parkinglot.domain;

import java.util.List;
import java.util.UUID;

public class Floor {
    private UUID id;
    private int floorNumber;
    private List<ParkingSlot> parkingSlotList;

    public Floor(int floorNumber, List<ParkingSlot> parkingSlotList) {
        this.id = UUID.randomUUID();
        this.floorNumber = floorNumber;
        this.parkingSlotList = parkingSlotList;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public UUID getId() {
        return this.id;
    }
}
