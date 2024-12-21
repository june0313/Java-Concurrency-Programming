package io.concurrency.chapter02.exam03;

public class StatusTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("hahaha"));
        System.out.println(thread.getState());
    }
}
