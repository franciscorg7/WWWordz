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

	/**
	 * Fill the puzzle table with large random words
	 * @return Puzzle (well generated)
	 */
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
	
	/**
	 * Fill the puzzle table with random letters
	 * Before returning, makes sure that puzzle has at least 5 solutions
	 * @return Puzzle (randomized)
	 */
	public static Puzzle random() throws IOException {
		Random rnd = new Random();
		Puzzle puzzle = new Puzzle();
		Table table = puzzle.getTable();

		for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
			char c = (char) (rnd.nextInt(26) + 'a');
			Cell cell = it.next();
			cell.setLetter(Character.toUpperCase(c));
		}

		List<Solution> sols = getSolutions(table);
		if (sols.size() < 5)
			random();

		puzzle.setSolutions(sols);

		return puzzle;
	}
	
	/**
	 * Get all the possible solutions in a puzzle table
	 * Makes use of auxiliar recursive function to be able to search for large words
	 */
	public static List<Solution> getSolutions(Table table) throws IOException {
		List<Solution> solutions = new ArrayList<>();
		Dictionary dic = Dictionary.getInstance();

		for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
			Cell cell = it.next();
			Search search = dic.startSearch();
			List<Cell> used = new ArrayList<>();
			String word = "";
			getSolutions(dic, table, cell, search, word, used, solutions);
		}

		return solutions;
	}

	private static void getSolutions(Dictionary dic, Table table, Cell cell, Search search, String word,
			List<Cell> visited, List<Solution> solutions) {

		if (visited.contains(cell) || !search.continueWith(cell.getLetter()))
			return;

		visited.add(cell);
		word += cell.getLetter();

		Solution sol = new Solution(word, visited);
		
		// Solutions must be words with at leat 3 characters and should not me considered more than once
		// The same word can be in multiple solutions, since can exist different paths to it
		if (word.length() >= 3 && search.isWord() && !solutions.contains(sol))
			solutions.add(new Solution(word, visited));

		List<Cell> neighbors = table.getNeighbors(cell);

		for (Cell neighbor : neighbors) {
			String auxWord = new String(word);
			List<Cell> auxPath = new ArrayList<Cell>(visited);
			Search searchCopy = new Search(search);
			getSolutions(dic, table, neighbor, searchCopy, auxWord, auxPath, solutions);
		}
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
