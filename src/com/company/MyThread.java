package com.company;

public class MyThread extends Thread {
    final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();

    @Override
    public void run() {
        try {
            semaphoreDemo.test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

