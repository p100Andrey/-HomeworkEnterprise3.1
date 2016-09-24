package com.company;

public class MySemaphore implements Semaphore {
    private int numberOfFreeThreads;
    private int maxNumberOfFreeThreads;

    public MySemaphore(int i) {
        if (i <= 0) throw
                new IllegalArgumentException(i + " < 0");
        numberOfFreeThreads = i;
        maxNumberOfFreeThreads = i;
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        while (numberOfFreeThreads == 0) {
            this.wait();
        }
        numberOfFreeThreads--;
        this.notify();
    }

    @Override
    public synchronized void acquire(int permits) throws InterruptedException {
        if (permits > maxNumberOfFreeThreads){
            System.out.println("Number of permits more then possible!");
            return;
        }
        System.out.println("Thread no=" + permits + " enter to take function  semaphore signals=" + this.numberOfFreeThreads);
        while (this.numberOfFreeThreads < permits) {
            wait();
        }
        this.numberOfFreeThreads -= permits;
        this.notifyAll();
        System.out.println("Thread no=" + permits + " taken  semaphore signals=" + this.numberOfFreeThreads);
    }

    @Override
    public synchronized void release() {
        numberOfFreeThreads++;
        this.notify();
    }

    @Override
    public synchronized void release(int permits) throws InterruptedException {
        System.out.println("Thread no=" + permits + " enter to release function semaphore signals=" + this.numberOfFreeThreads);
        this.numberOfFreeThreads += permits;
        this.notifyAll();
        System.out.println("Thread no=" + permits + " released semaphore signals=" + this.numberOfFreeThreads);
    }

    @Override
    public int getAvailablePermits() {
        return numberOfFreeThreads;
    }
}
