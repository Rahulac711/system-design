package com.java.mutithreading.ConcurrentProgramming;


import java.time.LocalDateTime;

// quick sort -> find pivot element -> shift all the elements less that pivot to the left of pivot and
// grater to right, after that send left part and right part separate

class RideMatchingService {
    void matchRide(String riderId) {
        Thread matchThread = new Thread(() -> {
            System.out.println(LocalDateTime.now() + " Matching rider "+ riderId + " to the driver");
            try {
                 Thread.sleep(2000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println(LocalDateTime.now()+ " Ride matched for "+ riderId);
        });
        matchThread.start();
    }
}

public class ConcurrentProgramming {
    public static void main(String args[]) {
        for(int i=0; i<10; i++) {
            RideMatchingService rideMatchingService = new RideMatchingService();
            rideMatchingService.matchRide("Raj-"+i);
        }
    }
}
