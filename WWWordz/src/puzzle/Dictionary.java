package puzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;

import puzzle.Trie.Node;
import puzzle.Trie.Search;

public class Dictionary
extends java.lang.Object {
	
	private static Dictionary single_instance = null;
	
	// Makes sure it only creates one and only instance of Dictionary
	public static Dictionary getInstance() {
		
		if(single_instance == null) 
            single_instance = new Dictionary(); 
  
        return single_instance;
	}
	
	public String getRandomLargeWord() {
		return null;
		
	}
	
	public Search startSearch() {
		return null;
	}
	
	public static Trie parseDictionary() throws IOException {
		
		Trie trie = new Trie();
		
		BufferedReader myBuffer = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/puzzle/pt-PT-AO.dic"), "UTF-8"));

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
	
	public static void main(String[] args) throws IOException {
		
        BufferedReader myBuffer = new BufferedReader(new InputStreamReader(
          new FileInputStream("src/puzzle/pt-PT-AO.dic"), "UTF-8"));
         
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
        Node n = t.searchNode("OTORRINOLARINGOLOGIA");
        System.out.println("Word: " + n.isWord);
        System.out.println("Leaf: " + n.isLeaf());
        
        String test = t.getRandomLargeWord();
        System.out.println(test);
	}
}
