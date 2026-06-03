
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class TestingWeightQuickUF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(16);
		System.out.println("I>  Connected 3 and 4: " + uf.connected(3, 4));
		System.out.println("II> Connected 3 and 4: " + isConnected(uf, 3, 4));

		uf.union(3, 4);
		System.out.println("III>  Connected 3 and 4: " + uf.connected(3, 4));
		System.out.println("IV>   Connected 3 and 4: " + isConnected(uf, 3, 4));

		WtQFU2 myUF = new WtQFU2(16);
		System.out.println("V> Connected 3 and 4: " + myUF.isConnected(3, 4));
		System.out.println("V> #Islands: " + myUF.getSetCount());
		myUF.union(3, 4);
		System.out.println("VI> Connected 3 and 4: " + myUF.isConnected(3, 4));
		System.out.println("VI> #Islands: " + myUF.getSetCount());

		System.out.println(print());

	}

	private static String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int j = 0; j < 16; j++) {
			sb.append("* ");
		}
		sb.append("\n");
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				sb.append("X ");
			}
			sb.append("\n");
		}
		for (int j = 0; j < 16; j++) {
			sb.append("* ");
		}
		sb.append("\n");

		return sb.toString();
	}

	public static boolean isConnected(WeightedQuickUnionUF uf, int x, int y) {
		return uf.find(x) == uf.find(y);
	}

}
