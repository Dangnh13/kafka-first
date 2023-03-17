package com.example.demo.thread.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Perform task as sequence with the same thread
 * Value: 0 with threadId: pool-1-thread-1
 * Value: 1 with threadId: pool-1-thread-1
 * Value: 2 with threadId: pool-1-thread-1
 * Value: 3 with threadId: pool-1-thread-1
 * Value: 4 with threadId: pool-1-thread-1
 * Value: 5 with threadId: pool-1-thread-1
 * */
public class SingleThread {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                System.out.println("Value: " + finalI + " with threadId: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}
