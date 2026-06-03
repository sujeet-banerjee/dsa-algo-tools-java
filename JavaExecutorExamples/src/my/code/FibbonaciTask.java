package my.code;

import java.util.concurrent.Callable;

public class FibbonaciTask implements Callable<Integer> {

    private final int n;
    private final String name;

    public FibbonaciTask(String name, int n) {
        this.name = name;
        this.n = n;
    }

    @Override
    public Integer call() throws StackOverflowError {

        long start = System.currentTimeMillis();
        String msg =
                String.format("\n\n [%s] [Fib(%d)] Start: %s ",
                this.name, this.n, start);
        System.out.println(msg);

        int res = fibb(n);

        long end = System.currentTimeMillis();
        msg =
                String.format(" [%s] [Fib(%d)]   End: %s ",
                this.name, this.n, end);
        System.out.println(msg);

        System.out.println(String.format(" [%s] [Fib(%d)] Time: %s", 
                this.name, this.n, (end-start)));

        return res;
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
