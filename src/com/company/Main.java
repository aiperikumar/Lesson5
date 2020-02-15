package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    private static final int PASSENGER_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(4, true);
        CountDownLatch latch = new CountDownLatch(PASSENGER_COUNT);
        for (int i = 1; i <=PASSENGER_COUNT ; i++){
            new Passenger(sem, i , latch).start();
            Thread.sleep(1000);
        }
        while (latch.getCount() > 1) {
            Thread.sleep(100);
        }
        Thread.sleep(1000);
        System.out.println("Автобус выехал");
        latch.countDown();

    }
}
