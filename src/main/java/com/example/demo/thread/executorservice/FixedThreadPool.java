package com.example.demo.thread.executorservice;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            executorService.submit(() -> {
                Thread.currentThread().setName("Thread-"+finalI);
                System.out.println("inside thread: " + finalI);
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        // Prevent executor execute any futher tasks
        executorService.shutdown();
        try {
            // true if this executor terminated and false if the timeout elapsed before termination
            if(!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
                System.out.println("Timeout when awaitTerminal then perform shutdownNow");
                //Attempts to stop all actively executing tasks,
                List<Runnable> listPending = executorService.shutdownNow();
                System.out.println("Have " + listPending.size() + " tasks are not executed!!");
            }
        } catch (InterruptedException e) {
            System.out.println("Interup after await");
            throw new RuntimeException(e);
        }
        // Nếu muốn đợi các task chạy xong mới gọi tới đây thì comment lại shutdownNow và tăng timeout lên
        System.out.println("Perform code after awaitTermination and shutdownNow");
    }
}
