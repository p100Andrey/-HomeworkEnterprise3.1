package com.company;

public class MySemaphore implements Semaphore {
    private int numberOfFreeThreads;
    private int maxNumberOfFreeThreads;

    public MySemaphore(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(i + " < 0");
        }
        numberOfFreeThreads = i;
        maxNumberOfFreeThreads = i;
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        acquire(1);
    }

    @Override
    public synchronized void acquire(int permits) {
        if (permits > maxNumberOfFreeThreads) {
            System.out.println("Number of permits more then possible!");
            return;
        }
        while (numberOfFreeThreads < permits) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numberOfFreeThreads -= permits;
        notifyAll();
        System.out.println(Thread.currentThread().getName());
        System.out.println("Getting of " + permits + " threads.");
    }

    @Override
    public synchronized void release() {
        release(1);
    }

    @Override
    public synchronized void release(int permits) {
        numberOfFreeThreads += permits;
        notifyAll();
        System.out.println(Thread.currentThread().getName());
        System.out.println("Returned " + permits + " threads.");
    }

    @Override
    public int getAvailablePermits() {
        return numberOfFreeThreads;
    }
}
