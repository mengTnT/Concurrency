package com.mmall.concurrency.example.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * lock的测试
 */
public  class LockTest {
    public static int threadTotal = 200;
    public static int clientTotal = 5000;
    public static int count = 0;


    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        ExecutorService execut = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch= new CountDownLatch(5000);
        for (int i = 0; i < clientTotal; i++) {
            execut.execute(()->{
                add();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        execut.shutdown();
        System.out.println(count);


    }

    private static synchronized void add (){
        count++;
    }

}
