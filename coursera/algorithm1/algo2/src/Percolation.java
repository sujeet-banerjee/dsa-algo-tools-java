/**
 *
 * @author Sujeet
 *
 */
public class Percolation {

    /**
     *
     */

    /**
     * @author Sujeet
     *
     */
    private static final class WtQFU {
        private final int[] parent;
        private int setCount;
        /**
         * Represents the size of the subtree rooted at this node.
         */
        private final int[] weight;

        /**
         *
         * @param size
         */
        WtQFU(int size) {
            this.setCount = size;
            this.parent = new int[size];
            this.weight = new int[size];
            this.init();
        }

        private void init() {
            for (int i = 0; i < this.parent.length; i++) {
                this.parent[i] = i;
                this.weight[i] = 1;
            }
        }

        public int getSetCount() {
            return this.setCount;
        }

        public int find(int p) {
            int size = this.parent.length;
            if (p < 0) {
                throw new IllegalArgumentException(
                        "The index cannot be less than 0");
            }

            if (p >= size) {
                throw new IllegalArgumentException(
                        "The index cannot be more than" + (size - 1));
            }

            int i = p;
            while (this.parent[i] != i) {
                i = this.parent[i];
            }

            return i;
        }

        public int findAndOptimize(int p) {
            int i = this.find(p);
            this.parent[p] = i;
            return i;
        }

        public boolean isConnected(int p, int q) {
            // return find(p) == find(q);
            return findAndOptimize(p) == findAndOptimize(q);
        }

        public void union(int p, int q) {
            // int pRoot = find(p);
            // int qRoot = find(q);
            int pRoot = findAndOptimize(p);
            int qRoot = findAndOptimize(q);

            if (qRoot == pRoot) {
                return;
            }

            // System.out.print(String.format("%d --><-- %d ", pRoot, qRoot));

            if (this.weight[pRoot] >= this.weight[qRoot]) {
                this.parent[qRoot] = pRoot;
                this.weight[pRoot] += this.weight[qRoot];
            } else {
                this.parent[pRoot] = qRoot;
                this.weight[qRoot] += this.weight[pRoot];
            }

            this.setCount--;
        }
    }

    private final int size;
    private final boolean[] opened;
    private int numOpenSites = 0;
    private final WtQFU uf;

    public Percolation(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative");
        }
        this.size = size;
        this.opened = new boolean[size * size + 2];
        this.uf = new WtQFU(size * size + 2);

        // Top virtual layer
        this.opened[size * size] = true;

        // Bottom virtual layer
        this.opened[size * size + 1] = true;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > this.size) {
            throw new IllegalArgumentException("Invalid row: " + row);
        }

        if (col < 1 || col > this.size) {
            throw new IllegalArgumentException("Invalid col: " + col);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.size * this.size) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
    }

    private int getIndexOf(int row, int col) {
        validate(row, col);
        return (row - 1) * size + (col - 1);
    }

    private int getUp(int index) {
        validateIndex(index);
        int up = index - this.size;
        up = up < 0 ? this.size * this.size : up;
        return up;
    }

    private int getDown(int index) {
        validateIndex(index);
        int down = index + this.size;
        down = down >= this.size * this.size ? this.size * this.size + 1 : down;
        return down;
    }

    /**
     *
     * @param index
     * @return a negative value if the given index is the left most
     */
    private int getLeft(int index) {
        validateIndex(index);
        int left = (index % this.size) - 1;
        return left < 0 ? -1 : index - 1;
    }

    private int getRight(int index) {
        validateIndex(index);
        int right = (index % this.size) + 1;
        return right >= this.size ? -1 : index + 1;
    }

    public void open(int row, int col) {
        int index = this.getIndexOf(row, col);
        if (this.opened[index]) {
            return;
        }

        this.opened[index] = true;
        // System.out.print(String.format("[%d,%d]: ", row, col));

        /*
         * Open UP
         */
        int up = this.getUp(index);
        if (this.opened[up]) {
            this.uf.union(index, up);
            // System.out.print("UP ");
        }

        int down = this.getDown(index);
        if (this.opened[down]) {
            this.uf.union(index, down);
            // System.out.print("DN ");
        }

        int left = this.getLeft(index);
        if (left >= 0 && this.opened[left]) {
            this.uf.union(index, left);
            // System.out.print("LF ");
        }

        int right = this.getRight(index);
        if (right >= 0 && this.opened[right]) {
            this.uf.union(index, right);
            // System.out.print("RT ");
        }
        // System.out.println();
        this.numOpenSites++;
    }

    /**
     * Is the site (row, col) open?
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        return this.opened[this.getIndexOf(row, col)];
    }

    /**
     * is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        int index = this.getIndexOf(row, col);

        // Are virtual-top and the specified site connected?
        return this.uf.isConnected(this.size * this.size, index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // Check if top and bottom are connected.
        return this.uf.isConnected(this.size * this.size,
                this.size * this.size + 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int j = 0; j < this.size; j++) {
            sb.append("* ");
        }
        sb.append("\n");
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                boolean open = this.opened[i * this.size + j];
                sb.append(open ? "* " : "X ");
            }
            sb.append("\n");
        }
        for (int j = 0; j < this.size; j++) {
            sb.append("* ");
        }
        sb.append("\n");
        sb.append(this.percolates() ? "--- YES ---" : "--- NOP ---");
        sb.append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
    }

    private static void test1() {
        Percolation p = new Percolation(4);
        System.out.println(p);

        p.open(1, 2);
        p.open(2, 1);
        System.out.println(p);

        p.open(3, 3);
        p.open(4, 3);
        p.open(2, 4);
        p.open(1, 4);
        System.out.println(p);

        p.open(2, 3);
        System.out.println(p);
    }

    private static void test2() {
        Percolation p = new Percolation(4);
        System.out.println(p);

        p.open(1, 2);
        p.open(2, 1);
        System.out.println(p);

        p.open(3, 3);
        p.open(4, 3);
        p.open(2, 4);
        p.open(1, 4);
        System.out.println(p);

        p.open(3, 2);
        System.out.println(p);

        p.open(2, 2);
        System.out.println(p);
    }

    private static void test3() {
        Percolation p = new Percolation(4);
        System.out.println(p);

        p.open(1, 2);
        p.open(2, 1);
        System.out.println(p);

        p.open(3, 3);
        p.open(4, 3);
        p.open(2, 4);
        p.open(1, 4);
        System.out.println(p);

        p.open(3, 2);
        System.out.println(p);

        p.open(3, 1);
        System.out.println(p);

        p.open(1, 1);
        System.out.println(p);

        System.out.println("Number of sites open: " + p.numberOfOpenSites());

        p.open(3, 4);
        System.out.println(p);

        p.open(4, 4);
        System.out.println(p);
    }

}
