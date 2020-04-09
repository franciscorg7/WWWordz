package wwwordz.puzzle;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import puzzle.Dictionary;

@DisplayName("Dictionary")
public class DictionaryTest {
	
	static Dictionary dictionary;
	
	@BeforeAll
	public static void before() {
		dictionary = Dictionary.getInstance();
	}
	
	/**
	 * Check if Dictionary is singleton
	 */
	@Test
	@DisplayName("Is singleton")
	public void testSingleton() {
		assertNotNull(dictionary);
		Dictionary copy = Dictionary.getInstance();
		assertEquals(dictionary,copy,"Multiples instances of singleton");
	}
	
	private Pattern allLetterPattern = Pattern.compile("[A-Z]+");
	
	/**
	 * Check a large number of words in the dictionary
	 * 1) wall larger than 3 letters
	 * 2) just with capital letters
	 * @throws IOException 
	 */
	@RepeatedTest(1000)
	@DisplayName("Random word")
	public void testGetRandomWord() throws IOException {
		String word = dictionary.getRandomLargeWord();
		assertTrue(word.length() >= 3,"Word size must be larger than 3");
		assertTrue(allLetterPattern.matcher(word).matches(),"Words only with capitals");
	}

}
