package com.suz.test;

import java.util.concurrent.Callable;

public class FibbonaciTask implements Runnable {

    private final int n;
    private final String name;

    public FibbonaciTask(String name, int n) {
        this.name = name;
        this.n = n;
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();
        String msg =
                String.format("\n\n [th-%s] [%s] [Fib(%d)] Start: %s ",
                Thread.currentThread().getName(),
                this.name, this.n, start);
        System.out.println(msg);

        int res = fibb(n);

        long end = System.currentTimeMillis();
        msg =
                String.format(" [th-%s] [%s] [Fib(%d)]   End: %s ",
                Thread.currentThread().getName(),
                this.name, this.n, end);
        System.out.println(msg);

        System.out.println(String.format(" [th-%s] [%s] [Fib(%d)] Time: %s", 
        		Thread.currentThread().getName(),
                this.name, this.n, (end-start)));

    }
    
    public String toString() {
    	return this.name;
    }

    public static int fibb(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return fibb(n - 1) + fibb(n - 2);
    }

}
