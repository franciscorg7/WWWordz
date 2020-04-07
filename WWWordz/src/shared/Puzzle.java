package shared;

import java.util.List;

public class Puzzle 
extends java.lang.Object
implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Table table;
	List<Solution> solutions;
	
	public static class Solution{
		
	}
	
	public Puzzle() {
		
	}
	
	List<Solution> getSolutions(){
		return null;
	}
	
	public Table getTable() {
		return null;	
	}
	
	public void setSolutions(List<Solution> solutions) {
		
	}
	
	public void setTable(Table table) {
		
	}


}
