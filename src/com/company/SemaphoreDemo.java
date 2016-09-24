package com.company;

public class SemaphoreDemo {
    MySemaphore mySemaphore = new MySemaphore(5);

    public static void main(String[] args) {
        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 5; i > 0; i--) {
                        semaphoreDemo.semaphoreDemo(i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    private void semaphoreDemo(int numberOfAcquires) throws InterruptedException {
        try {
            if (numberOfAcquires == 1) {
                mySemaphore.acquire();
            }else {
                mySemaphore.acquire(numberOfAcquires);
            }
            System.out.println("Locks acquired");
            System.out.println("Locks remaining >> " + mySemaphore.getAvailablePermits());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            if (numberOfAcquires == 1){
                mySemaphore.release();
            }else {
                mySemaphore.release(numberOfAcquires);
            }
            System.out.println("Locks Released");
        }
    }
}
