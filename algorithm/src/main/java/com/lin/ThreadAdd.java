package com.lin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadAdd {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int taskNum = 50;

        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < taskNum; i++) {
            Future<Integer> future = executorService.submit(new MyThread());
            futureList.add(future);
        }

        int sum = 0;
        for (Future future : futureList) {
            sum += Integer.valueOf(future.get().toString());
        }

        System.out.println(sum);
    }

}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        return random.nextInt(100);
    }

}
