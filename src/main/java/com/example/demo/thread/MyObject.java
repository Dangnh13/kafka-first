package com.example.demo.thread;

class Test {
    public void procedure() throws InterruptedException {
        synchronized (this) {
            System.out.println("running procedure");
            wait();
            System.out.println("again procedure");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000L );
        synchronized (this) {
            System.out.println("running consume method");
            notify();
            System.out.println("CONSUMER notify");
            Thread.sleep(5000L);
            System.out.println("Consumer after 5s");
        }
    }
}
public class MyObject {
    public static void main(String[] args) {
        Test test = new Test();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.procedure();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}