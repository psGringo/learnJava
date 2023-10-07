package org.example;

import java.util.Collections;
import java.util.concurrent.*;

public class InvokeExample {

    static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void execute() {
        Callable<String> task = () -> {
            Thread.sleep(100);
            return "Done";
        };

        try {
            invokeMillionTasks(task);
            printStatistics();
            shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void invokeMillionTasks(Callable<String> task) throws Exception {

//        var tasks = new ArrayList<Callable<String>>();
//        for (int i = 0; i < 10000; i++) {
//            tasks.add(task);
//        }

        var futures = executorService.invokeAll(Collections.nCopies(100, task));
        futures.forEach((f) -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static void printStatistics() {
        var pool = (ThreadPoolExecutor) executorService;
        System.out.printf("getLargestPoolSize: %s%n", pool.getLargestPoolSize());
        System.out.printf("getMaximumPoolSize: %s%n", pool.getMaximumPoolSize());
        System.out.printf("getPoolSize: %s%n", pool.getPoolSize());
        System.out.printf("getTaskCount: %s%n", pool.getTaskCount());
    }

    static void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
