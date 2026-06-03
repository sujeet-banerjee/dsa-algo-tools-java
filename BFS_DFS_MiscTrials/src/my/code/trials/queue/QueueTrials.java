package my.code.trials.queue;

import java.util.Queue;
import java.util.logging.Logger;
import java.util.LinkedList;

// Access restricted class
class Node{
	int val;
	Node left, right;
	
	// Always make a constructor with values;
	public Node(int val) {
		this.val = val;
	}
	
	public Node (int val, Node left, Node right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return String.format("<%s> ", this.val);
	}
}

/**
 * 
 */
class LevelMarker extends Node {
	public LevelMarker(int val) {
		super(val);
	}
	
	public LevelMarker(int val, Node left, Node right) {
		super(val, left, right);
	}
	
	@Override
	public String toString() {
		return String.format(" |%s| ", this.val);
	}
}

class NullNode extends Node {
	public NullNode() {
		super(0);
	}
}

public class QueueTrials {
	
	private static final String NULL_TOKEN = "null";
	Logger logger = Logger.getLogger("-->");
	
	private Queue<Node> queue = new LinkedList<>();
	
	public boolean isLevelNode(Node node) {
		return node instanceof LevelMarker;
	}
	
//	public void createFew() {
//		this.queue.add(new LevelMarker(0));
//		this.queue.add(new Node(100));
//		this.queue.add(new Node(200));
//		this.queue.add(new Node(300));
//		
//		System.out.println("Queue: "+ this.queue);
//		
//		Node next = this.queue.peek();
//		System.out.println("Next: "+ next);
//		
//		System.out.println("Queue after Peek: "+ this.queue);
//	}
	
	
	public Node createTree(String treeSpec) {
		if(treeSpec == null) {
			throw new IllegalArgumentException("Input spec cannon be null");
		}
		
		if(treeSpec.isEmpty()) {
			return null;
		}
		
		String[] tokens = treeSpec.split(",");
		if(tokens.length==0 || tokens[0].equals(NULL_TOKEN)) {
			return null;
		}
		
		// Start with root
		Node root = new Node(Integer.valueOf(tokens[0]));
		int index = 1;
		Queue<Node> bfs = new LinkedList<>();
		bfs.add(root);
		
		// Use a queue to build the tree iteratively (BFS)
		while(bfs.size() > 0) {
			
			// dequeue one, and create left and right children
			// if there are tokens left.
			
			Node current = bfs.poll();
			System.out.println("Index="+ index);
			System.out.println ("Current -->" + current);
			
			// pick left if non null
			if(index < tokens.length) {
				String leftStr = tokens[index++];
				if(!leftStr.equals(NULL_TOKEN)) {
					System.out.println("Adding Left");
					current.left = new Node(Integer.valueOf(leftStr));
					bfs.add(current.left);
				}				
			}
			
			// pick right if non null
			if(index < tokens.length) {
				String rightStr = tokens[index++];
				if(!rightStr.equals(NULL_TOKEN)) {
					System.out.println("Adding Right");
					current.right = new Node(Integer.valueOf(rightStr));
					bfs.add(current.right);
				}
			}
			
			System.out.println("Current.L=" + current.left);
			System.out.println("Current.R=" + current.right + "\n\n");
		}
		
		return root;
	}
	
	public static void main(String args[]) {
		QueueTrials qt = new QueueTrials();
//		qt.createFew();
//		// Exor:
//		System.out.println(2^3);
		
		
	}
}

