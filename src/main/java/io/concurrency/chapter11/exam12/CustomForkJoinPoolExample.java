package io.concurrency.chapter11.exam12;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

@Slf4j
public class CustomForkJoinPoolExample {

    public static void main(String[] args) {

        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        int parallelism = Runtime.getRuntime().availableProcessors();
        log.info("Available processors: {}", parallelism);


        ForkJoinPool pool = new ForkJoinPool(parallelism);

        CustomRecursiveTask task = new CustomRecursiveTask(array, 0, array.length);
        long result = pool.invoke(task);

        log.info("result: {}", result);
        log.info("pool = {}", pool);
        log.info("stealing = {}", pool.getStealCount());

        pool.shutdown();
    }
}
