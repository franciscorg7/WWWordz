package shared;

import java.util.LinkedList;
import java.util.List;

import shared.Table.Cell;

public class Puzzle 
extends java.lang.Object
implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Table table;
	List<Solution> solutions;
	
	public Puzzle() {
		this.table = new Table();
	}
	
	public List<Solution> getSolutions(){
		return this.solutions;
	}
	
	public Table getTable() {
		return this.table;	
	}
	
	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}
	
	public static class Solution
	extends java.lang.Object
	implements java.io.Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private List<Cell> cells;
		private String word;
		
		public Solution() {
		}
		
		public Solution(String word, List<Cell> cells) {
			this.word = word;
			this.cells = cells;
		}
		
		public List<Cell> getCells(){
			return this.cells;
		}
		
		public int getPoints() {
			String sol = this.getWord(); int solLength = sol.length();
			
			// Calls an auxiliar recursive method
			return getPoints(solLength);
		}
		
		// Recursive auxiliar to calculate solution points
		private int getPoints(int n) {
			if(n == 3) return 1;
			return getPoints(n-1) * 2 + 1;
		}
		
		public String getWord() {
			return this.word;
		}
	}
	
	public static void main(String[] args) {
		String[] data = new String[] {"PATO", "SACO", "BOLA", "TITA"};
		Table table = new Table(data);
		Puzzle puzzle = new Puzzle();
		List<Cell> word = new LinkedList<>();
		
		for(int i = 1; i < 4; i++) {
			for(int j = 1; j < 4; j++) {
				if(i==j) {
					Cell c = table.getCell(i,j);
					word.add(c);
				}
				
			}
		}
		
		Solution sol = new Solution("PATA", word);
		List<Solution> solutions = new LinkedList<>();
		solutions.add(sol);
		puzzle.setTable(table);
		puzzle.setSolutions(solutions);
		
		System.out.println(sol.getPoints());
	}

}
