package com.example.demo.thread.executorservice;

import java.util.concurrent.*;

public class LatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Thread getCountTask = new Thread(new Runnable() {
            @Override
            public void run() {
                while (countDownLatch.getCount() != 0) {
                    System.out.println("Current Countdown = " + countDownLatch.getCount());
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Thread.currentThread().interrupt();
            }
        });
        getCountTask.start();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Latch(i, countDownLatch));
        }
        // Nếu ko có dòng shutdown thì app chạy mãi mặc dù LatchCountDown đã = zero
        executorService.shutdown();

        countDownLatch.await(10000, TimeUnit.SECONDS);
        System.out.println("Finish all task muộn nhất là 10s");

    }
}

class Latch implements Runnable {

    private int id;
    private CountDownLatch countDownLatch;

    public Latch(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Executing thread: " + this.id);
        this.countDownLatch.countDown();
    }
}