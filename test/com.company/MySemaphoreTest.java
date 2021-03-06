package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MySemaphoreTest {
    Semaphore mySemaphore;

    @Before
    public void testBefore(){
        mySemaphore = new MySemaphore(5);
    }

    @Test
    public void testAcquire() throws Exception {
        mySemaphore.acquire();
        assertEquals(4, mySemaphore.getAvailablePermits());
    }

    @Test
    public void testAcquirePermits() throws Exception {
        mySemaphore.acquire(4);
        assertEquals(1, mySemaphore.getAvailablePermits());
    }

    @Test
    public void testAcquirePermitsNegative() throws Exception {
        mySemaphore.acquire(6);
        assertEquals(5, mySemaphore.getAvailablePermits());
    }

    @Test
    public void testRelease() throws Exception {
        mySemaphore.acquire();
        mySemaphore.release();
        assertEquals(5, mySemaphore.getAvailablePermits());
    }

    @Test
    public void testReleasePermits() throws Exception {
        mySemaphore.acquire(5);
        mySemaphore.release(5);
        assertEquals(5, mySemaphore.getAvailablePermits());
    }

}