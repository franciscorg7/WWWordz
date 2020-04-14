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

		return puzzle;
	}

	public static Puzzle random() {
		Random rnd = new Random();
		Puzzle puzzle = new Puzzle();
		Table table = puzzle.getTable();

		for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
			char c = (char) (rnd.nextInt(26) + 'a');
			Cell cell = it.next();
			cell.setLetter(Character.toUpperCase(c));
		}

		// if(getSolutions(puzzle.table).size == 0) random();

		return puzzle;
	}

	public static List<Solution> getSolutions(Table table) throws IOException {
		List<Solution> solutions = new LinkedList<>();
		Dictionary dic = Dictionary.getInstance();

		for (Iterator<Cell> it = table.iterator(); it.hasNext();) {
			Cell c = it.next();
			List<Cell> visited = new ArrayList<Cell>();
			List<Cell> path = new ArrayList<Cell>();
			String word = "";
			Search search = dic.startSearch();

			if (search.continueWith(c.getLetter())) {
				word += c.getLetter();
				search = new Search(dic.trie.searchNode(word));
				Solution sol = new Solution(word, path);
				if (search.node.isWord)
					solutions.add(sol);
				visited.add(c);
				List<Cell> neighbors = table.getNeighbors(c);
				
				for (Cell n : neighbors) {
					Search auxSearch = new Search(search);
					String aux = word;
					if (!visited.contains(n) && auxSearch.continueWith(n.getLetter())) {
						aux += n.getLetter();
						auxSearch = new Search(dic.trie.searchNode(aux));
						visited.add(n);
						path.add(n);
						sol = new Solution(aux, path);
						if (auxSearch.node.isWord)
							solutions.add(sol);
					}
				}
			}
		}

		return solutions;

	}

	public static void main(String[] args) throws IOException {

		Puzzle puzzle = generate();
		System.out.println(puzzle.getTable().toString());
		List<Solution> solutions = getSolutions(puzzle.getTable());
		for (Solution sol : solutions) {
			System.out.println(sol.getWord()); // lol
		}
	}

}
