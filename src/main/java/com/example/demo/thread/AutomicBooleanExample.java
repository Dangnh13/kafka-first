package com.example.demo.thread;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutomicBooleanExample {
    static HaiDang haiDang = new HaiDang();

    public static void main(String[] args) {
        int searchValue =7;
        List<Integer> list1 = IntStream.range(1, 500).boxed().collect(Collectors.toList());
        new Thread(() -> {
            while (!haiDang.find.get()) {
                for (Integer e : list1) {
                    System.out.println("T1 search: " + e);
                    if (e == searchValue) {
                        haiDang.find.set(true);
                        System.out.println("Thread 1 tim thay");
                        break;
                    }
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        }).start();
        new Thread(() -> {
            boolean kq = haiDang.find.get();
            while (!kq) {
                if(kq != haiDang.find.get()) {
                    System.out.println("T2 FOUND VALUE: " + haiDang.find.get());
                    kq = haiDang.find.get();
                }
            }
            System.out.println("THoat khoai while T2");
        }).start();


    }


}

class HaiDang {
    public AtomicBoolean find = new AtomicBoolean(false);
}
