package puzzle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Trie 
extends java.lang.Object
implements java.lang.Iterable<String>{

	private Node root;
	
	public Trie() {
		root = new Node();
	}
	
	public String getRandomLargeWord() {
		String largeWord = "";
		HashMap<Character, Node> children = root.children;
		
		Random generator = new Random();
		Object[] nodes = children.values().toArray();
		Node n = (Node) nodes[generator.nextInt(nodes.length)];
		largeWord += n.val;
		
		while(!n.isLeaf()) {
			children = n.children;
			generator = new Random();
			nodes = children.values().toArray();
			n = (Node) nodes[generator.nextInt(nodes.length)];
			
			largeWord += n.val;
		}
		
		// Make sure the word's length is at least 3 before returning 
		if(largeWord.length() < 3) return getRandomLargeWord();
		return largeWord;
	}
	
	public void put(String word) {
		root.put(word, 0);
	}
	
	public Search startSearch() {
		Search search = new Search(root);
		return search;
	}
	
	// Check if word exists in the trie
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
		return new NodeIterator();
	}
	
	public class Node{
		char val;
		String wordVal;
		HashMap<Character, Node> children = new HashMap<Character, Node>();
		Node parent;
		boolean isWord;
		
		public Node() {
			this.wordVal = "";
		}
		
		public Node(char c) {
			this.val = c;
			this.wordVal = Character.toString(c);
		}
		
		// Check if node is a leaf
		public boolean isLeaf() {
			if(this.children.isEmpty()) return true;
			return false;
		}
		
		public void put(String word, int level) {
			
			if(level >= word.length()) {
				this.isWord = true;
				return;
			}
			
			HashMap<Character, Node> curChildren = this.children;
			
			if(this.children.containsKey(word.charAt(level))) {
				Node n = curChildren.get(word.charAt(level));
				n.put(word, level+1);
			}
			
			else {
				Node child = new Node();
				child.val = word.charAt(level);
				child.wordVal = this.wordVal + Character.toString(word.charAt(level));
				this.children.put(word.charAt(level), child);
				child.put(word, level+1);
			}
		}
	}
	
	public class NodeIterator
	extends java.lang.Object
	implements java.util.Iterator<java.lang.String>, java.lang.Runnable{
		private String nextWord;
		private boolean terminated;
		private Thread thread;
		
		public NodeIterator() {
			thread = new Thread(this, "Node Iterator");
			thread.start();
		}
		
		private void handshake() {
			notify();
            try {
                wait();
            } catch (InterruptedException cause) {
                throw new RuntimeException("Unexpected interruption while waiting",cause);
            }
		}
		
		public boolean hasNext() {
			synchronized(this) {
				if(!terminated) handshake();
			}
			
			return nextWord != null;
		}
		
		public String next() {
			String word = nextWord;
			
			synchronized(this) {
				nextWord = null;
			}
			
			return word;
		}
		
		private void visitValues(Node node) {
			
			if(node.isWord == true) {
				synchronized(this) {
					if(nextWord != null)
						handshake();
					nextWord = node.wordVal;
					handshake();
				}
			}
			
			for(Node n : node.children.values()) {
				visitValues(n);
			}
        }
		
		public void run() {
			
			terminated = false;
			
			visitValues(root);
			
			synchronized (this) {
                terminated = true;
                handshake();
            }
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
