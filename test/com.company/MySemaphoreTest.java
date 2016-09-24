package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class MySemaphoreTest {
    @Test
    public void testAcquire() throws Exception {
        Semaphore mySemaphore = new MySemaphore(5);
        mySemaphore.acquire();
        assertEquals(4, mySemaphore.getAvailablePermits());
    }

    @Test
    public void testAcquirePermits() throws Exception {
        Semaphore mySemaphore = new MySemaphore(5);
        mySemaphore.acquire(4);
        assertEquals(1, mySemaphore.getAvailablePermits());
    }

    @Test
    public void testAcquirePermitsNegative() throws Exception {
        Semaphore mySemaphore = new MySemaphore(5);
        mySemaphore.acquire(6);
        assertEquals(5, mySemaphore.getAvailablePermits());
    }

    @Test
    public void testRelease() throws Exception {
        Semaphore mySemaphore = new MySemaphore(5);
        mySemaphore.acquire();
        mySemaphore.release();
        assertEquals(5, mySemaphore.getAvailablePermits());
    }

    @Test
    public void testReleasePermits() throws Exception {
        Semaphore mySemaphore = new MySemaphore(5);
        mySemaphore.acquire(5);
        mySemaphore.release(5);
        assertEquals(5, mySemaphore.getAvailablePermits());
    }

}