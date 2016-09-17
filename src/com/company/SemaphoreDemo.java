package com.company;

public class SemaphoreDemo {
    MySemaphore mySemaphore = new MySemaphore(10);

    public static void main(String[] args) {
        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    semaphoreDemo.printLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private void printLock() throws InterruptedException {
        try {
            mySemaphore.acquire();
            System.out.println("Locks acquired");
            System.out.println("Locks remaining >> " + mySemaphore.getAvailablePermits());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            mySemaphore.release();
            System.out.println("Locks Released");
        }

        try {
            mySemaphore.acquire(2);
            System.out.println("Locks acquired");
            System.out.println("Locks remaining >> " + mySemaphore.getAvailablePermits());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            mySemaphore.release(2);
            System.out.println("Locks Released");
        }
    }
}
