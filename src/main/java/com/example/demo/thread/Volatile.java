package com.example.demo.thread;


class Demo {
    int count = 0;

    public void increment() {
        synchronized (this ) {
            count++;
        }

    }
}

public class Volatile {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Demo d = new Demo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("thread name: " + Thread.currentThread().getName() + "- " + Thread.currentThread().isDaemon());
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep((long) (10 * Math.random()));
                        d.increment();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep((long) (10 * Math.random()));
                        d.increment();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("FINISH THREAD");
        System.out.println("thread name: " + Thread.currentThread().getName() + "- " + Thread.currentThread().isDaemon());
        System.out.println("Count is: " + d.count);
    }

}
