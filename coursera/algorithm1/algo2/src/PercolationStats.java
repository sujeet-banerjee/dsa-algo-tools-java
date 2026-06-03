/**
 *
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author Sujeet
 *
 */
public class PercolationStats {
    private final int size;
    private final int trials;
    private final double[] result;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int size, int trials) {
        if (size <= 0 || trials <= 0) {
            throw new IllegalArgumentException("invalid argument");
        }

        this.size = size;
        this.trials = trials;
        this.result = new double[trials];

        doMonteCarloSimulation();
    }

    private void doMonteCarloSimulation() {
        for (int i = 0; i < this.trials; i++) {
            doIteration(i);
        }
    }

    private void doIteration(int itr) {
        Percolation p = new Percolation(this.size);
        while (!p.percolates()) {
            int row = StdRandom.uniform(this.size) + 1;
            int col = StdRandom.uniform(this.size) + 1;
            p.open(row, col);
        }
        result[itr] = (1.0 * p.numberOfOpenSites()) / (this.size * this.size);
        // System.out.println(p);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.result);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double interval = 1.96 * stddev() / Math.sqrt(trials);
        return mean() - interval;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double interval = 1.96 * stddev() / Math.sqrt(trials);
        return mean() + interval;
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Need two arguments");
        }

        PercolationStats ps = new PercolationStats(Integer.valueOf(args[0]),
                Integer.valueOf(args[1]));
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + String
                .format("[%16f, %16f]", ps.confidenceLo(), ps.confidenceHi()));
    }
}
