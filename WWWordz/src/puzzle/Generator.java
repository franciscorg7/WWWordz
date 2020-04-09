package puzzle;

import java.io.IOException;
import java.util.List;

import shared.Puzzle;
import shared.Puzzle.Solution;
import shared.Table;
import shared.Table.Cell;

public class Generator
extends java.lang.Object {
	
	public Generator() {
	}
	
	public Puzzle generate() throws IOException {
		Puzzle puzzle = new Puzzle();
		
		/*while(puzzle.getTable().getEmptyCells().size() != 0) {
			Dictionary dictionary = Dictionary.getInstance();
			String word = dictionary.getRandomLargeWord();
			List<Cell> emptyCells = puzzle.getTable().getEmptyCells();
			Cell c = emptyCells.get(0);
			for(int i = 0; i < word.length(); i++) {
				Table table = new Table();
				c.setLetter(word.charAt(i));
				List<Cell> neighbors = puzzle.getTable().getNeighbors(c);
				
			}
		}*/
		
		return puzzle;
	}
	
	public Puzzle random() {
		return null;
	}
	
	public List<Solution> getSolutions(Table table){
		return null;
	}
}
