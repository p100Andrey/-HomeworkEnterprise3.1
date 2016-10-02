package com.company;

import java.util.Random;

public class SemaphoreDemo {
    MySemaphore mySemaphore = new MySemaphore(5);

    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
        Thread thread1 = new MyThread();
        thread1.start();
        Thread thread2 = new MyThread();
        thread2.start();
    }

    public void test() throws InterruptedException {
        Random random = new Random();
        int numberOfAcquires = random.nextInt(6);
        try {
            if (numberOfAcquires == 1) {
                mySemaphore.acquire();
            } else {
                mySemaphore.acquire(numberOfAcquires);
            }
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            if (numberOfAcquires == 1) {
                mySemaphore.release();
            } else {
                mySemaphore.release(numberOfAcquires);
            }
            System.out.println("Locks remaining >> " + mySemaphore.getAvailablePermits());
        }
    }
}
