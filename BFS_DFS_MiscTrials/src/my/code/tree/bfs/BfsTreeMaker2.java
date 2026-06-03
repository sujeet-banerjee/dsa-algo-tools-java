/**
 * 
 */
package my.code.tree.bfs;

import java.util.Queue;
import java.util.LinkedList;

/**
 * 
 */
class TreeNode {
	int val;
	TreeNode left, right;
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	TreeNode(int val) {
		this(val, null, null);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append(this.val);
		if(this.left != null) {
			sb.append(this.left.toString());
		} else if(this.right != null) {
			sb.append("()");
		}
		if(this.right != null) {
			sb.append(this.right.toString());
		}
		sb.append(")");
		
		return sb.toString();
	}
}

public class BfsTreeMaker2 {
	private static final String NULL = "null";
	
	public String[] tokenize(String spec) {
		if(spec ==null) {
			throw new IllegalArgumentException("Spec cannot be null");
		}
		
		return spec.split(",");
	}
	
	public int tokenToInt(String token) {
		return Integer.valueOf(token);
	}
	
	public TreeNode createBfsTree(String treeSpec) {
		TreeNode ret = null;
		String[]tokens = tokenize(treeSpec);
		int size = tokens.length;
		if(size==0) {
			return null;
		}
		
		if(tokens[0].equals(NULL)) {
			return null;
		}
		
		// BFS init
		int nextToken = 1;
		Queue<TreeNode> q = new LinkedList<>();
		ret = new TreeNode(tokenToInt(tokens[0]));
		q.offer(ret);
		int level = 0;
		
		// BFS Build
		while(q.size()>0) {
			int lSize = q.size();
			
			/*
			 *  Checking for has-more-tokens here (additional optimization)
			 *  will cause the external while to be non-terminate, unless
			 *  the same check is done to the external while loop as well
			 */
			for(int i=0; i<lSize /*&& nextToken<size*/; i++) {
				TreeNode lNode = q.poll();
				
				// left
				if(nextToken < size) {
					String leftTk = tokens[nextToken++];
					
					if(!leftTk.equals(NULL)) {
						// Form left
						lNode.left = new TreeNode(tokenToInt(leftTk));
						q.offer(lNode.left);
					} 
				}
				
				// right
				if(nextToken < size) {
					String rightTk = tokens[nextToken++];
					if(!rightTk.equals(NULL)) {
						lNode.right = new TreeNode(tokenToInt(rightTk));
						q.offer(lNode.right);
					}
				}
			}
			
			
			level++;
			// End Level
		}
		
		return ret;
	}
	
	public static void main(String []args) {
		   
       BfsTreeMaker2 maker = new BfsTreeMaker2();
       TreeNode root = maker.createBfsTree("10,null,20,30,40,null,12,14,null,7,null,5");
       System.out.println("Root: "+ root);
   }

}
