package puzzle;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import puzzle.Trie.Search;
import shared.Puzzle;
import shared.Puzzle.Solution;
import shared.Table;
import shared.Table.Cell;

public class Generator extends java.lang.Object {

	public Generator() {
	}

	public static Puzzle generate() throws IOException {
		Puzzle puzzle = new Puzzle();
		Table table = puzzle.getTable();

		while (puzzle.getTable().getEmptyCells().size() != 0) {
			Dictionary dic = Dictionary.getInstance();
			String word = dic.getRandomLargeWord();
			List<Cell> emptyCells = puzzle.getTable().getEmptyCells();

			int index = 0;
			for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
				Cell c = it.next();
				List<Cell> neighbors = table.getNeighbors(c);

				for (Cell neighbor : neighbors) {

					if (index == word.length() - 1)
						break;

					if (emptyCells.contains(neighbor)) {
						neighbor.setLetter(word.charAt(index));
						table.editCell(neighbor);
						index++;
					}
				}

				if (index == word.length() - 1)
					break;
			}
		}

		
		 puzzle.setTable(table);
		  
		 List<Solution> sols = getSolutions(table);
		  
		 puzzle.setSolutions(sols);
		 

		return puzzle;
	}

	public static Puzzle random() throws IOException {
		
		Random rnd = new Random();
		Puzzle puzzle = new Puzzle();
		Table table = puzzle.getTable();

		for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
			char c = (char) (rnd.nextInt(26) + 'a');
			Cell cell = it.next();
			cell.setLetter(Character.toUpperCase(c));
		}

		
		 List<Solution> sols = getSolutions(table); if (sols.size() == 0) random();
		  
		 puzzle.setSolutions(sols);
		 

		return puzzle;
	}

	public static List<Solution> getSolutions(Table table) throws IOException {
		List<Solution> solutions = new ArrayList<>();
		Dictionary dic = Dictionary.getInstance();

		for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
			Cell cell = it.next();
			Search search = dic.startSearch();
			List<Cell> used = new ArrayList<>();
			StringBuffer prefix = new StringBuffer();
			getSolutions(dic, table, cell, search, prefix, used, solutions);
		}

		return solutions;
	}

	private static void getSolutions(Dictionary dic, Table table, Cell cell, Search search, StringBuffer prefix, List<Cell> visited,
			List<Solution> solutions) {

		if (visited.contains(cell)) {
			return;
		}

		visited.add(cell);
		
		if (!search.continueWith(cell.getLetter()))
			return;

		prefix.append(cell.getLetter());
		
		String word = prefix.toString();
		if (search.isWord() && word.length() >= 3 && !contains(solutions, word))
			solutions.add(new Solution(word, visited));

		List<Cell> neighbors = table.getNeighbors(cell);

		for (Cell neigbor : neighbors) {
			List<Cell> usedCopy = new ArrayList<Cell>(visited);
			StringBuffer prefixCopy = new StringBuffer(prefix);
			Search searchCopy = new Search(search);
			getSolutions(dic, table, neigbor, searchCopy, prefixCopy, usedCopy, solutions);
		}
	}

	/**
	 * Check if given list of solutions already contains a word
	 * 
	 * @param solutions
	 * @param word
	 * @return
	 */
	private static boolean contains(List<Solution> solutions, String word) {
		for (Solution solution : solutions)
			if (solution.getWord().equals(word))
				return true;
		return false;
	}

	public static void main(String[] args) throws IOException {

		Puzzle puzzle = generate();
		
		System.out.println(puzzle.getTable().toString());
		List<Solution> solutions = getSolutions(puzzle.getTable());
		for (Solution sol : solutions) {
			System.out.println(sol.getWord());

			for (Cell c : sol.getCells()) {
				System.out.print("(" + c.getRow() + ", " + c.getCol() + ")");
				System.out.println();
			}

		}
	}

}
