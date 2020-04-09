package game;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import puzzle.Dictionary;
import shared.Puzzle;
import shared.Rank;
import shared.WWWordzException;

public class Manager {
	private static long INITIAL_TIME = 0L;
	private static Manager single_instance = null;
	private Round round;
	private static ScheduledExecutorService worker;
	
	
	public static Manager getInstance() {
		if(single_instance == null) 
            single_instance = new Manager(); 
  
        return single_instance;
	}
	
	public Puzzle getPuzzle() throws WWWordzException{
		return null;
	}
	
	public List<Rank> getRanking() throws WWWordzException{
		return null;
	}
	
	public long register(String nick, String password) throws WWWordzException{
		return 0;
	}
	
	public void setPoints(String nick, int points) throws WWWordzException{
		
	}
	
	public long timeToNextPlay() {
		return 0;
		
	}
}
