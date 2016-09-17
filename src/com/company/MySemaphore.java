package com.company;

public class MySemaphore implements Semaphore {
    private int counter;

    public MySemaphore() {
        this(10);
    }

    public MySemaphore(int i) {
        if (i < 0) throw
                new IllegalArgumentException(i + " < 0");
        counter = i;
    }

    /**
     * Уменьшает счетчик или блокирует,
     * если тот равен 0
     *
     * @throws InterruptedException передается из this.wait().
     */
    @Override
    public synchronized void acquire() throws InterruptedException {
        while (counter == 0) {
            this.wait();
        }
        counter--;
    }

    @Override
    public synchronized void acquire(int permits) throws InterruptedException {
        System.out.println("Thread no=" + permits + " enter to take function  semaphore signals=" + this.counter);
        while (this.counter == 9) wait();
        this.counter++;
        this.notify();
        System.out.println("Thread no=" + permits + " taken  semaphore signals=" + this.counter);
    }

    /**
     * Увеличивает внутренний счетчик,
     * возможно активирует нить
     * wait()ing in acquire().
     */
    @Override
    public synchronized void release() {
        if (counter == 0) {
            this.notify();
        }
        counter++;
    }

    @Override
    public synchronized void release(int permits) throws InterruptedException {
        System.out.println("Thread no=" + permits + " enter to release function semaphore signals=" + this.counter);
        while (this.counter == 0) wait();
        this.counter--;
        this.notify();
        System.out.println("Thread no=" + permits + " released semaphore signals=" + this.counter);
    }

    @Override
    public int getAvailablePermits() {
        return counter;
    }
}
