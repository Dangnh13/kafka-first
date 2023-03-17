package com.example.demo.thread.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        List<Future<String>> features = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            features.add(executorService.submit(new DemoCallable(i)));
        }
        // Prevent executor execute any futher tasks
        executorService.shutdown();
        System.out.println("Mặc dù đoạn này sau đoạn fori nhưng nó vẫn in ra trước." );

        try {
            Thread.sleep(5000L);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("ĐOạn này chạy sau 5s");
        features.forEach(f -> {

            try {
                System.out.println("Return: " + f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Đoạn này sẽ chạy sau khi thực thi các task thread xong, " +
                "Đặt ở đây để đảm bảo all task đã xong thật sự");
        System.out.println("FINNISH");
    }
}

class DemoCallable implements Callable<String> {

    private int id;

    public DemoCallable(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(6000L);
        System.out.println("Executing thread: " + this.id);
        return "ID: " + this.id;
    }
}