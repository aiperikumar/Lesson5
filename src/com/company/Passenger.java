package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Passenger extends Thread{
    Semaphore semaphore;
    int id;
    CountDownLatch cdl;

    public Passenger(Semaphore semaphore, int id, CountDownLatch cdl) {
        this.semaphore = semaphore;
        this.id = id;
        this.cdl = cdl;
    }


    public synchronized void run(){
        try{
            semaphore.acquire();
            System.out.println("Пасажир "+ id + " приобретает билет");
            Thread.sleep(1000);

            System.out.println(id + " купил билет и идет к автобусу ");
            semaphore.release();
            Thread.sleep(1000);
            System.out.println(id + " садиться в автобус");
            cdl.countDown();
            cdl.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
