package puzzle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Trie 
extends java.lang.Object
implements java.lang.Iterable<String>{

	private Node root;
	
	public Trie() {
		root = new Node();
	}
	
	public String getRandomLargeWord() {
		return null;
	}
	
	public void put(String word) {
		HashMap<Character, Node> children = root.children;
		 
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
 
            Node t;
            if(children.containsKey(c)) {
            	t = children.get(c);
            } else {
                t = new Node(c);
                children.put(c, t);
            }
 
            children = t.children;
 
            // Set node as word terminator
            if(i == word.length()-1)
                t.isWord = true;  
        }
	}
	
	public Search startSearch() {
		return null;
	}
	
	public Node searchNode(String str){
        Map<Character, Node> children = root.children; 
       
        Node t = null;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            } else{
            	return null;
            }
        }
 
        return t;
    }
	
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class Node{
		char val;
		HashMap<Character, Node> children = new HashMap<Character, Node>();
		Node parent;
		boolean isWord;
		
		public Node() {
		}
		
		public Node(char c) {
			this.val = c;
		}
		
		// Check if node is a leaf
		public boolean isLeaf() {
			if(this.children.isEmpty()) return true;
			return false;
		}
	}
	
	public class NodeIterator
	extends java.lang.Object
	implements java.util.Iterator<java.lang.String>, java.lang.Runnable{
		private String nextWord;
		private boolean terminated;
		private Thread thread;
		
		public NodeIterator() {
		}
		
		public boolean hasNext() {
			return this.terminated;
		}
		
		public String next() {
			return null;
		}
		
		public void run() {
		}
	}
	
	public static class Search	
	extends java.lang.Object {
		Node node;
		
		public Search(Node node) {
			this.node = node;
		}
		
		public Search(Search search) {
			this.node = search.node;
		}
		
		private boolean continueWith(char letter) {
			if(this.node.children != null) return true;
			return false;
		}
		
		private boolean isWord() {
			return this.node.isWord; 
		}
		
	}

}
