package com.example.demo.thread.collection.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        AtomicInteger  count = new AtomicInteger(0);
        new Thread(() -> {
            while(true) {
                try {
                    int value = count.incrementAndGet();
                    System.out.println("Put value: "+ value+" to queue:");
                    blockingQueue.put(value);

                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        }).start();
        new Thread(() -> {
            while(true) {
               try {
                       int value = blockingQueue.take();
                       System.out.println("Take value: "+ value);
                       Thread.sleep(3000L);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000L);
                    System.out.println(">>>>> Size: " + blockingQueue.size());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }
}
