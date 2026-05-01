package com.java.parkinglot.service;

import com.java.parkinglot.domain.Floor;
import com.java.parkinglot.domain.ParkingSlot;
import com.java.parkinglot.domain.Vehicle;
import com.java.parkinglot.repository.FloorRepository;
import com.java.parkinglot.repository.PricingRuleRepository;
import com.java.parkinglot.repository.SlotRespository;

import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private FloorRepository floorRepository;
    private PricingRuleRepository pricingRuleRepository;
    private SlotRespository slotRespository;

    public AdminService(FloorRepository floorRepository,
                        PricingRuleRepository pricingRuleRepository,
                        SlotRespository slotRespository) {
        this.floorRepository = floorRepository;
        this.pricingRuleRepository = pricingRuleRepository;
        this.slotRespository = slotRespository;
    }

    public void initializeParkinglot() {
        // ParkingSlot create 10
        // create 3 floors initialize with parking above slots
        ParkingSlot bikeParkingSlot1 = new ParkingSlot(Vehicle.VehicleType.BIKE, 1);
        ParkingSlot bikeParkingSlot2 = new ParkingSlot(Vehicle.VehicleType.BIKE, 2);
        ParkingSlot bikeParkingSlot3 = new ParkingSlot(Vehicle.VehicleType.BIKE, 3);

        List<ParkingSlot> parkingSlotList = new ArrayList<>();
        parkingSlotList.add(bikeParkingSlot1);
        parkingSlotList.add(bikeParkingSlot1);
        parkingSlotList.add(bikeParkingSlot1);
        parkingSlotList.add(bikeParkingSlot1);

        Floor floor = new Floor(1, parkingSlotList);
        List<Floor> floors = new ArrayList<>();
        floors.add(floor);


    }
}
