package puzzle;

import java.util.Iterator;
import java.util.List;

public class Trie 
extends java.lang.Object
implements java.lang.Iterable<String>{

	public Trie() {
		
	}
	
	public String getRandomLargeWord() {
		return null;
	}
	
	public void put(String word) {
		
	}
	
	public Search startSearch() {
		return null;
	}
	
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class Node{
		int depth;
		String value;
		List<Node> children;
		Node parent;
		
		public Node() {
		}
		
		public Node(int depth, String val, List<Node> children, Node parent) {
			this.depth = depth;
			this.value = val;
			this.children = children;
			this.parent = parent;
		}
	}
	
	public class NodeIterator{
		
	}
	
	public class Search	{
		Node node;
		
		public Search(Node node) {
			
		}
		
		public Search(Search search) {
			
		}
		
		private boolean continueWith(char letter) {
			return false;
		}
		
		private boolean isWord() {
			return false;
		}
		
	}

}
