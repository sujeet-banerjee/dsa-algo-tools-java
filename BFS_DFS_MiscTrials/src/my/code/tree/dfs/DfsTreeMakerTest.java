package my.code.tree.dfs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DfsTreeMakerTest {

	@Test
	void testCreateDfsTree() {
		DfsTreeMaker maker = new DfsTreeMaker();
		Pair<TreeNode, Integer> rr = maker.createDfsTree("(10)", 0, "Root");
		System.out.println(rr);
	}
	
	@Test
	void testCreateDfsTree2() {
		DfsTreeMaker maker = new DfsTreeMaker();
		Pair<TreeNode, Integer> rr = maker.createDfsTree("(10(3)(9))", 0, "Root");
		System.out.println(rr);
	}
	
	@Test
	void testCreateDfsTree3() {
		DfsTreeMaker maker = new DfsTreeMaker();
		Pair<TreeNode, Integer> rr = maker.createDfsTree("(10(20(30(12(7)))(40(14(5)))))", 0, "Root");
		System.out.println(rr);
	}
	
	//(10()(20(30()(12(7)))(40(14(5)))))
	@Test
	void testCreateDfsTree4() {
		DfsTreeMaker maker = new DfsTreeMaker();
		Pair<TreeNode, Integer> rr = maker.createDfsTree("(10()(20(30()(12(7)))(40(14(5)))))", 0, "Root");
		System.out.println(rr);
	}
	
	@Test
	void testCreateDfsTree5() {
		DfsTreeMaker maker = new DfsTreeMaker();
		Pair<TreeNode, Integer> rr1 = maker.createDfsTree("10()(20(30()(12(7)))(40(14(5))))", 0, "Root");
		Pair<TreeNode, Integer> rr2 = maker.createDfsTree("(10()(20(30()(12(7)))(40(14(5)))))", 0, "Root");
		
		Assert.assertEquals(rr2.t.toString(), rr1.t.toString());
		
		System.out.println(rr1);
	}

}
