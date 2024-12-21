package io.concurrency.chapter04.exam01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadExceptionExample {

    public static void main(String[] args) {

        try {
            new Thread(() -> {
                throw new RuntimeException("스레드 1 예외!");
            }).start();
        } catch (Exception e) {
            sendNotificationToAdmin(e);
        }

    }

    private static void sendNotificationToAdmin(Throwable e) {
        log.info("Notify to admin", e);
    }
}
