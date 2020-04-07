package shared;

import java.util.Iterator;
import java.util.List;

public class Table
extends java.lang.Object
implements java.lang.Iterable<Table.Cell>, java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2548293690897888580L;
	
	private static final int tableBeggining = 1;
	private static final int tableSize = 4;
	
	Cell[][] table = new Cell[5][5];
	
	public Table() {
		for(int i = tableBeggining; i < tableSize; i++) {
			for(int j = tableBeggining; j < tableSize; j++) {
				table[i][j] = new Cell(i, j);
			}
		}
	}
	
	public Table(String[] data) {
		int col = tableBeggining, row = tableBeggining;
		for(String word : data) {
			for(int i = 1; i <= word.length(); i++) {
				char letter = word.charAt(i-1);
				Cell c = new Cell(row, col, letter);
				table[row][col] = c;
				col++;
			}
			row++; // Word processed iterate row
			col = tableBeggining; // Reset col for each word processed
		}
	}
	
	public List<Cell> getEmptyCells(){
		return null;
	}
	
	public List<Cell> getNeighbors(Cell cell){
		return null;
	}
	
	public Cell getCell(int row, int column) {
		return this.table[row][column];
	}
	
	@Override
	public String toString() {
		String str = "";
		for(int i = tableBeggining; i <= tableSize; i++) {
			for(int j = tableBeggining; j <= tableSize; j++) {
				str += table[i][j].toString(); 
			}
			str += "\n";
		}
		
		return str;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	// Table Cells
	public static class Cell
	extends java.lang.Object
	implements java.io.Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		int column;
		char letter;
		int row;
		
		public Cell() {
		}
		
		public Cell(int row, int column) {
			this.row = row;
			this.column = column;
			this.letter = ' ';
		}
		
		public Cell(int row, int column, char letter) {
			this.row = row;
			this.column = column;
			this.letter = letter;
		}
		
		@Override
		public boolean equals(Object obj) {
			return false;
		}
		
		public char getLetter() {
			return this.letter;
		}
		
		@Override
		public int hashCode() {
			return 0;
		}
		
		public void setLetter(char letter) {
			this.letter = letter;
		}
		
		@Override
		public String toString() {
			String charToStr = String.valueOf(this.letter);
			return charToStr;
		}
		
		public boolean isEmpty() {
			if(this.letter == ' ') return true;
			return false;
		}
	}
	
	private class CellIterator
	extends java.lang.Object
	implements java.util.Iterator<Table.Cell>{
		int column;
		int row;
		
		public CellIterator() {
			this.column = tableBeggining;
			this.row = tableBeggining;
		}
		
		@Override
		public boolean hasNext() {
			if(this.column == tableSize && this.row == tableSize) return false;
			return true;
		}
		
		@Override
		public Cell next() {
			
			if(this.column != (tableSize)) {
				Cell c = getCell(this.row, this.column+1);
				this.column++;
				return c;
			}
			
			else if(this.row != (tableSize) && this.column == (tableSize)) {
				Cell c = getCell(this.row+1, 1); // Reset column to the beggining of the line
				this.row++; this.column = tableBeggining;
				return c;
			}
			
			// hasNext() failsafe
			return null;
		}
		
		@Override
		public void remove() {
			table[this.row][this.column] = new Cell(this.row, this.column);
		}
	}

	@Override
	public Iterator<Cell> iterator() {
		return new CellIterator();
	}
	
	// Just for Table methods debugging
	public static void main(String[] args) {
		String[] data = new String[] {"PATO", "SACO", "BOLA", "TITA"};
		Table table = new Table(data);
		System.out.println(table.toString());
		
		for(Iterator<Cell> it = table.iterator(); it.hasNext();) {
			System.out.println(it.next());
		} 
		
		Iterator<Cell> it = table.iterator();
		it.next();
		it.remove();
		System.out.println(table.toString());
	}
}
