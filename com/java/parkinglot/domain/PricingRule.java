package com.java.parkinglot.domain;

import java.util.UUID;

public class PricingRule {
    private UUID id;
    private Vehicle.VehicleType vehicleType;
    private int ratePerHour;
    private int flatRate;
}
