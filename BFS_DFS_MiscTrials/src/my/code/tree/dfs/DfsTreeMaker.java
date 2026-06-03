/**
 * 
 */
package my.code.tree.dfs;

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
			sb.append(this.left);
		} else if(this.right != null) {
			sb.append("()");
		}
		if(this.right != null) {
			sb.append(this.right);
		}
		sb.append(")");
		
		return sb.toString();
	}
}

class Pair<T, V> {
	T t;
	V v;
	
	public Pair(T t, V v) {
		this.t = t;
		this.v = v;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(t);
		sb.append(", I=");
		sb.append(v);
		sb.append(">");
		
		return sb.toString();
	}
}

public class DfsTreeMaker {
	
	public boolean isLP(char ch) {
		return ch == '(';
	}
	public boolean isRP(char ch) {
		return ch == ')';
	}
	public boolean isParen(char ch) {
		return isRP(ch) || isLP(ch);
	}
	
	public Pair<TreeNode, Integer> createDfsTree(String spec, int start, String msg) {
		if(!spec.startsWith("(")) {
			return this.createDfsTreeInternal("("+spec+")", start, msg);
		} else {
			return this.createDfsTreeInternal(spec, start, msg);
		}
	}
	
	
	public Pair<TreeNode, Integer> createDfsTreeInternal(String spec, int start, String msg) {
		if(spec==null || spec.isEmpty()) {
			return new Pair<>(null, start);
		}
		
		System.out.println("\nCreating "+ msg + " Tree from spec: "+ (spec.substring(start)));
		
		int size = spec.length();
		if(start >= size) {
			return new Pair<>(null, start);
		}
		
		// Consume '(' + <Number>
		int begin = start;
		if(spec.charAt(start) == '(') {
			// '('
			begin++;
			int end = begin;
			while(end < size && !isParen(spec.charAt(end))) {
				end++;
			}
			
			if(begin == end) {
				// Null Node again - e.g "()"
				// But consumed two characters.
				return new Pair<>(null, end+1);
			}
			
			int num = Integer.valueOf(spec.substring(begin, end));
			TreeNode root = new TreeNode(num);
			
			
			// if next '(' ==> recursion For Left
			//Pair left = 
			if(isLP(spec.charAt(end))) {
				Pair<TreeNode, Integer> left = createDfsTree(spec, end, "Lt");
				System.out.println(">>> Left Pair: "+ left);
				root.left = left.t;
				end = left.v;
			}
			
			
			// if one more next '(' ==> recursion For Right
			if(isLP(spec.charAt(end))) {
				Pair<TreeNode, Integer> right = createDfsTree(spec, end, "Rt");
				System.out.println(">>> Right Pair: "+ right);
				root.right = right.t;
				end = right.v;
			}
			
			// else if next ')' ==> return
			if(isRP(spec.charAt(end))) {
				return new Pair<>(root, end+1);
			}
			
			System.out.println("------ Def ---");
			return new Pair<>(root, end+1);
			
		} else {
			throw new IllegalStateException("Expecting '(' at: "+ start);
		}
	}
	
	
}
