package com.example.demo.thread;

import java.util.ArrayList;
import java.util.List;

public class ConsumerProducer {
    public static void main(String[] args) {
        TestPrdCsm test = new TestPrdCsm();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
    }

}


class TestPrdCsm {
    public List<Integer> list = new ArrayList<>();
    public int value = 0;
    public Object lock = new Object();

    public void producer() throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println("Inside producer");

        synchronized (lock) {
            while (true) {
                if (list.size() == 5) {
                    System.out.println(">>>>>>Enough size =5, waiting  consumer remove items .....");
                    lock.notify();
                    lock.wait();
                } else {
                    System.out.println(">>>>>>producer adding: " + value);
                    list.add(value++);
                    Thread.sleep(1000L);
                }
            }
        }
    }

    public void consumer() throws InterruptedException {
        System.out.println("Inside consumer");
        synchronized (lock) {
            while (true) {
                if (list.size() == 0) {
                    lock.notify();
                    lock.wait();
                } else{
                    System.out.println("Consume value: " + list.remove(list.size() -1));
                    Thread.sleep(1000L);
                }
            }
        }
    }
}