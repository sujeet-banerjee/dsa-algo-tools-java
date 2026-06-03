/**
 *
 */

/**
 * @author Sujeet
 *
 */
public class WtQFU2 {
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
	public WtQFU2(int size) {
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
		if(p < 0)
		{
			throw new IllegalArgumentException(
					"The index cannot be less than 0");
		}

		if (p >= size)
		{
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
		//		int pRoot = find(p);
		//		int qRoot = find(q);
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
