package com.mmall.concurrency.example.lock;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * lock的测试
 */
public  class LockTest2 {
    public static int threadTotal = 20;
    public static int clientTotal = 5000;
    public static int count = 0;
    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {


        ExecutorService execut = Executors.newCachedThreadPool();

        Semaphore semaphore=new Semaphore(1);
        for (int i = 0; i < clientTotal; i++) {
            execut.execute(()->{
                try {
                    semaphore.acquire(1);
                    add();
                    semaphore.release(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        execut.shutdown();
        System.out.println(count);

    }

    private static void add () {

        lock.lock();
        count++;
        lock.unlock();


    }

}
