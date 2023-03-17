package com.example.demo.thread.collection.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityBlockingQueueExample {
    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>();
        AtomicInteger count = new AtomicInteger(0);
        new Thread(() -> {
            try {
                blockingQueue.add(1);
                blockingQueue.add(1);
                blockingQueue.add(5);
                Thread.sleep(3000L);
                blockingQueue.add(4);
                Thread.sleep(2000L);
                blockingQueue.add(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
                try {
                    Thread.sleep(10000L);
                    System.out.println( blockingQueue.take());
                    System.out.println( blockingQueue.take());
                    System.out.println( blockingQueue.take());
                    System.out.println( blockingQueue.take());
                    System.out.println( blockingQueue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

        }).start();

    }
}
