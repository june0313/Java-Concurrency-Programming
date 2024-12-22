package io.concurrency.chapter11.exam12;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Slf4j
public class ForkJoinSumExample extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10;

    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumExample(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Small task : calculate directly
            return Arrays.stream(numbers, start, end).sum();
        } else {
            // For Big Task :
            int middle = (start + end) / 2;
            ForkJoinSumExample leftTask = new ForkJoinSumExample(numbers, start, middle);
            ForkJoinSumExample rightTask = new ForkJoinSumExample(numbers, middle, end);

            // Run async
            leftTask.fork();

            // Run in current thread
            Long rightResult = rightTask.compute();

            // Merge results
            Long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 1_000_000_000).toArray();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSumExample task = new ForkJoinSumExample(numbers, 0, numbers.length);


        Instant startTime = Instant.now();
        Long sum = pool.invoke(task);
        log.info("### Sum = {}", sum);
        log.info("With ForkJoinPool = {}ms", Duration.between(startTime, Instant.now()).toMillis());


        Instant startTime2 = Instant.now();
        long sum2 = Arrays.stream(numbers, 0, numbers.length).sum();
        log.info("### Sum2 = {}", sum2);
        log.info("Without ForkJoinPool = {}ms", Duration.between(startTime2, Instant.now()).toMillis());
    }
}
