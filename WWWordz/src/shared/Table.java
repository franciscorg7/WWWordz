package shared;

import java.util.Iterator;
import java.util.List;

import puzzle.Graph;

import java.util.LinkedList;

public class Table extends java.lang.Object implements java.lang.Iterable<Table.Cell>, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private static final int tableBeggining = 1;
	private static final int tableSize = 4;

	Cell[][] table = new Cell[6][6];

	public Table() {
		for (int i = tableBeggining; i <= tableSize; i++) {
			for (int j = tableBeggining; j <= tableSize; j++) {
				table[i][j] = new Cell(i, j);
			}
		}
	}

	public Table(String[] data) {
		int col = tableBeggining, row = tableBeggining;
		for (String word : data) {
			for (int i = 1; i <= word.length(); i++) {
				char letter = word.charAt(i - 1);
				Cell c = new Cell(row, col, letter);
				table[row][col] = c;
				col++;
			}
			row++; // Word processed iterate row
			col = tableBeggining; // Reset col for each word processed
		}
	}

	public List<Cell> getEmptyCells() {
		List<Cell> emptyCells = new LinkedList<>();

		for (int i = tableBeggining; i <= tableSize; i++) {
			for (int j = tableBeggining; j <= tableSize; j++) {
				if (table[i][j].letter == ' ')
					emptyCells.add(table[i][j]);
			}
		}

		return emptyCells;
	}

	public List<Cell> getNeighbors(Cell cell) {
		List<Cell> neighbors = new LinkedList<>();

		for (int i = cell.row - 1; i <= cell.row + 1; i++) {
			for (int j = cell.column - 1; j <= cell.column + 1; j++) {

				if (table[i][j] != null && table[i][j] != cell) {
					neighbors.add(table[i][j]);
					;
				}
			}
		}
		return neighbors;
	}

	public Cell getCell(int row, int column) {
		return this.table[row][column];
	}
	
	public void editCell(Cell c) {
		this.table[c.row][c.column] = c;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = tableBeggining; i <= tableSize; i++) {
			for (int j = tableBeggining; j <= tableSize; j++) {
				str += table[i][j].toString();
			}
			str += "\n";
		}
		return str;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true; // Comparing object to itself
		if (obj == null)
			return false; // Comparing to a null object
		if (this.getClass() != obj.getClass())
			return false; // Comparing both classes

		// typecast obj to Table to compare data members
		Table tb = (Table) obj;

		for (int i = tableBeggining; i <= tableSize; i++) {
			for (int j = tableBeggining; j <= tableSize; j++) {
				if (!table[i][j].equals(tb.table[i][j]))
					return false;
			}
		}
		return true;
	}

	// Table Cells
	public static class Cell extends java.lang.Object implements java.io.Serializable {

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
			if (this == obj)
				return true; // Comparing object to itself
			if (obj == null)
				return false; // Comparing to a null object
			if (this.getClass() != obj.getClass())
				return false; // Comparing both classes

			// typecast obj to Cell to compare data members
			Cell c = (Cell) obj;

			// Unic condition to cell equality
			if (row == c.row && column == c.column && letter == c.letter)
				return true;
			return false;
		}

		public char getLetter() {
			return this.letter;
		}
		
		public int getRow() {
			return this.row;
		}
		
		public int getCol() {
			return this.column;
		}

		@Override
		public int hashCode() {
			return 1;
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
			if (this.letter == ' ')
				return true;
			return false;
		}
	}

	private class CellIterator extends java.lang.Object implements java.util.Iterator<Table.Cell> {
		int column;
		int row;

		public CellIterator() {
			this.column = tableBeggining - 1; // We must start counting from (1,0) null index since the first cell must
												// be counted as well
			this.row = tableBeggining;
		}

		@Override
		public boolean hasNext() {
			if (this.column == tableSize && this.row == tableSize)
				return false;
			return true;
		}

		@Override
		public Cell next() {

			if (this.column != (tableSize)) {
				Cell c = getCell(this.row, this.column + 1);
				this.column++;
				return c;
			}

			else if (this.row != (tableSize) && this.column == (tableSize)) {
				Cell c = getCell(this.row + 1, 1); // Reset column to the beggining of the line
				this.row++;
				this.column = tableBeggining;
				return c;
			}

			// hasNext() failsafe
			return null;
		}
		
		public void edit(char letter) {
			table[this.row][this.column] = new Cell(this.row, this.column, letter);
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
		String[] data = new String[] { "PATO", "SACO", "BOLA", "TITA" };
		String[] data2 = new String[] { "GOLO", "CACA", "LATA", "FRIA" };

		Table table = new Table(data);
		Table table2 = new Table(data2);

		System.out.println(table.toString());

		Cell c1 = table.getCell(2, 2);
		Cell c2 = table2.getCell(2, 2);

		int l = 0;
		for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
			l++;
			System.out.println((it.next().toString()));
			((CellIterator) it).edit('a');
		}

		System.out.println(l);

		if (c1.equals(c2))
			System.out.println("Iguais");
		else
			System.out.println("Diferentes");

		List<Cell> list = table.getNeighbors(table.getCell(2, 2));

		for (Cell c : list) {
			System.out.println(c.toString());
		}

		System.out.println(table.toString());
		

	}
}
