package puzzle;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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

		List<Solution> sols = getSolutions(table);
		if (sols.size() == 0)
			random();

		puzzle.setSolutions(sols);

		return puzzle;
	}

	public static List<Solution> getSolutions(Table table) throws IOException {
		List<Solution> solutions = new LinkedList<>();
		Dictionary dic = Dictionary.getInstance();

		for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
			Cell c = it.next();
			List<Cell> visited = new ArrayList<Cell>();
			List<Cell> path = new ArrayList<Cell>();
			Search search = dic.startSearch();
			String word = "";
			
			if (search.continueWith(c.getLetter())) {
				word += c.getLetter();
				visited.add(c);
				path.add(c);
				search = new Search(dic.trie.searchNode(word));
				Solution sol = new Solution(word, path);
				if (search.node.isWord && !solutions.contains(sol)) {
					solutions.add(sol);
				}
			}
			
			solutions = createSol(solutions, dic, table, c, word, visited, path, search);
		}

		return solutions;
	}

	public static List<Solution> createSol(List<Solution> solutions, Dictionary dic, Table table, Cell c, String word,
			List<Cell> visited, List<Cell> path, Search search) {

			List<Cell> neighbors = table.getNeighbors(c);

			for (Cell n : neighbors) {
				List<Cell> auxPath = path;
				Search auxSearch = new Search(search);
				String aux = word;
				if (!visited.contains(n) && auxSearch.continueWith(n.getLetter())) {
					aux += n.getLetter();
					auxSearch = new Search(dic.trie.searchNode(aux));
					visited.add(n);
					if (auxSearch.node.isWord) {
						auxPath.add(n);
						Solution sol = new Solution(aux, auxPath);
						
						if (!solutions.contains(sol))
							solutions.add(sol);
						createSol(solutions, dic, table, n, aux, visited, auxPath, auxSearch);
					}
					
					else {
						createSol(solutions, dic, table, n, aux, visited, path, auxSearch);
					}	
				}
			}

		return solutions;

	}

	public static void main(String[] args) throws IOException {

		Puzzle puzzle = random();
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
