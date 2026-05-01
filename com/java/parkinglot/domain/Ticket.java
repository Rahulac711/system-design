package com.java.parkinglot.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private UUID slotId;
    private UUID vehicleId;
    private LocalDateTime entryTime;
    private boolean isActive;
}
