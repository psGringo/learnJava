package org.example;

import java.util.concurrent.*;

public class CallableFutureExample {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void execute() {
        Future<String> callableFuture = executorService.submit(new SmthCallable());
        var callableFuture2 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "another callable future";
            }
        });

        try {
            String futureResult = callableFuture.get(3000, TimeUnit.SECONDS);
            System.out.println(futureResult);
            var futureResult2 = callableFuture2.get(3000, TimeUnit.SECONDS);
            System.out.println(futureResult2);
            if (callableFuture.isDone() && callableFuture2.isDone())
                executorService.shutdownNow();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}

class SmthCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Result of callable";
    }
}
