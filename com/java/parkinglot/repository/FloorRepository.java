package com.java.parkinglot.repository;

import com.java.parkinglot.domain.Floor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FloorRepository {

    Map<UUID, Floor> floors = new ConcurrentHashMap<>();
    Map<Integer, UUID> floorNumberToId = new ConcurrentHashMap<>();

    public Floor addFloor(Floor floor) {
        floors.put(floor.getId(), floor);
        floorNumberToId.put(floor.getFloorNumber(), floor.getId());
        return floor;
    }



}
