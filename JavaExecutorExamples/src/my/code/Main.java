package my.code;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // execSequential();
        execUsingExecutor();
    }

    private static void execSequential() {
        long start = System.currentTimeMillis();
        System.out.println("Take 1: " + start);
        FibbonaciTask.fibb(50);
        long end = System.currentTimeMillis();
        System.out.println("Take 2: " + end);
    }

    private static void execUsingExecutor() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<FibbonaciTask> tasks = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            tasks.add(new FibbonaciTask("" + ((char) (65 + i)), i * 8 + 10));
        }

        try {
            executor.invokeAny(tasks);

            // Try with 'invokeAll' - and see
            // when the message "AWAITING RESULTS..." gets printed!
        } catch (InterruptedException | ExecutionException ie) {
            ie.printStackTrace(System.out);
        } finally {
            executor.shutdown();
        }

        System.out.println("AWAITING RESULTS...");
    }
}
