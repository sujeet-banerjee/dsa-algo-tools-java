package my.code.tree.bfs;

import java.util.Queue;
import java.util.LinkedList;

class Node {

   // TODO make private later
   int val;
   
   Node left;
   Node right;
   
   public Node(int val) {
       this.val = val;
   }
   
   public String toString() {
       return String.format(" (%s (%s)(%s))", this.val, this.left, this.right); 
   }
}

public class BfsTreeMaker {

   public static final String NULL = "null";

   public Node makeTree(String treeSpec) {
        // Validations
        if(treeSpec == null) {
            throw new IllegalArgumentException("spec cannot be null");
        }
        
        // Base case
        if(treeSpec.isEmpty()) {
            return null;
        }
   
        // 1. tokenize
        String []tokens = treeSpec.split(",");
        
        if(tokens[0].equals(NULL)) {
            return null;
        }
        
        // ASSUMPTION: the tokens are 'null' or Integer parseable
        
        // 2. Start-off with root node
        int index = 1;
        Node root = new Node(Integer.valueOf(tokens[0]));
        Queue<Node> bfs = new LinkedList<>();
        bfs.add(root);
        
        // 3. Iterate: dequeue and add children as queue,
        // to build the tree
        
        while(bfs.size() > 0) {
        
            System.out.println("Index="+ index);
            System.out.println("Q="+ bfs);
            
            // dequeue; and for the current node
            Node current = bfs.poll();
            System.out.println("curr="+ current);
            
            // form left if more tokens left; index+; enqueue
            if(index < tokens.length) {
                String lf = tokens[index++];
                if(! lf.equals(NULL) ) {
                    current.left = new Node(Integer.valueOf(lf));
                    bfs.add(current.left);
                    System.out.println("Adding Left "+ current.left);
                }
            }
            
            // form right if more tokens left; index++; enqueue
            if(index < tokens.length) {
                String rt = tokens[index++];
                if(! rt.equals(NULL) ) {
                    current.right = new Node(Integer.valueOf(rt));
                    bfs.add(current.right);
                     System.out.println("Adding Right "+ current.right);
                }
            }
        }
        
       return root;
   }
   
   
   public static void main(String []args) {
   
       BfsTreeMaker maker = new BfsTreeMaker();
       
       Node root = maker.makeTree("10,null,20,30,40,null,12,14,null,7,null,5");
       
       System.out.println("Root: "+ root);
   }
}