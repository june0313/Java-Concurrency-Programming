package io.concurrency.chapter04.exam01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultExceptionHandlerExample {

    public static void main(String[] args) {

        // 모든 스레드의 예외에 대한 기본 핸들러 설정
        Thread.setDefaultUncaughtExceptionHandler(defaultThreadExceptionHandler());

        // 예외를 발생시키는 여러 스레드
        Thread thread1 = new Thread(() -> {
            throw new RuntimeException("스레드 1 예외!");
        });

        Thread thread2 = new Thread(() -> {
            throw new IllegalStateException("스레드 2 예외!");
        });

        thread1.start();
        thread2.start();
    }

    private static Thread.UncaughtExceptionHandler defaultThreadExceptionHandler() {
        return (thread, exception) -> log.info("{}에서 예외 발생 : {}", thread.getName(), exception.getMessage());
    }
}
