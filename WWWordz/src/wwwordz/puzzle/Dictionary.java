package wwwordz.puzzle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;

import wwwordz.puzzle.Trie.Node;
import wwwordz.puzzle.Trie.Search;

public class Dictionary
extends java.lang.Object {
	
	Trie trie;
	private static final String DIC = "src/puzzle/pt-PT-AO.dic";
	private static Dictionary single_instance = null;
	
	/**
	 * Makes sure there is one and only instance of Dictionary
	 * @return single instance of Dictionary
	 */
	public static Dictionary getInstance() throws IOException {
		
		if(single_instance == null) 
            single_instance = new Dictionary(); 
  
        return single_instance;
	}
	
	public Dictionary() throws IOException {
		this.trie = parseDictionary();
	}
	
	public String getRandomLargeWord() throws IOException {
		return this.trie.getRandomLargeWord();
		
	}
	
	/**
	 * Read all the words from dictionary file and places them into the trie
	 * Makes sure that every word is capitalized and has its accent marks removed
	 * @return Parsed dictionary in trie from
	 */
	public static Trie parseDictionary() throws IOException {
		
		Trie trie = new Trie();
		
		BufferedReader myBuffer = new BufferedReader(
				new InputStreamReader(new FileInputStream(DIC), "UTF-8"));

		String line = myBuffer.readLine();

		while(line != null) {
			String word = "";
			for(int i = 0; i < line.length(); i++) {
				if(!Character.isLetter(line.charAt(i)))
					break;
				word += line.charAt(i);
			}

			word = Normalizer.normalize(word.toUpperCase(Locale.ENGLISH), Form.NFD)
					.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
			
			trie.put(word);
			
			line = myBuffer.readLine();
		}

		myBuffer.close();
		
		return trie;
	}
	
	public Search startSearch() {
		Search search = new Search(this.trie.startSearch());
		return search;
	}
	
	public static void main(String[] args) throws IOException {
		
        BufferedReader myBuffer = new BufferedReader(new InputStreamReader(
          new FileInputStream(DIC), "UTF-8"));
         
        String line = myBuffer.readLine();
        
        while (line != null) {
        	String word = "";
        	for(int i = 0; i < line.length(); i++) {
        		if(!Character.isLetter(line.charAt(i))) break;
        		word += line.charAt(i); 
        	}
        	
        	word = Normalizer.normalize(word.toUpperCase(Locale.ENGLISH),Form.NFD).
            replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            System.out.println(word);
            line = myBuffer.readLine();
        }
 
        myBuffer.close();
        
        Trie t = parseDictionary();
        Node n = t.searchNode("SAN");
        if(n == null) System.out.println("No word.");
        else {
        	System.out.println("Word: " + n.isWord);
            System.out.println("Leaf: " + n.isLeaf());
            
            String test = t.getRandomLargeWord();
            System.out.println(test);
        }
        
	}
}
