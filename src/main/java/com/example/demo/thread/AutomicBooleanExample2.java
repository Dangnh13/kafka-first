package com.example.demo.thread;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutomicBooleanExample2 {
    static  int counter = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            int localCounter = counter;
            while (localCounter < 10) {
//                if(localCounter != counter) {
                    System.out.println("T1 Counter is changed");
                    localCounter = counter;
//                }
//                System.out.println("T1 counter: "+ counter);
//                try {
//                    Thread.sleep(500L);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }

            }
        }).start();
        new Thread(() -> {
            int localCounter = counter;
            while (localCounter < 10) {
                System.out.println("T2 increment: " + (localCounter+1));
                counter = ++localCounter;
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }).start();


    }

    public static <T> Collection<List<T>> prepareChunks(List<T> inputList, int chunkSize) {
        AtomicInteger counter = new AtomicInteger();
        return inputList.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize)).values();
    }
}

