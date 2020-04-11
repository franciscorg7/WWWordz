package game;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import puzzle.Dictionary;
import shared.Puzzle;
import shared.Rank;
import shared.Table;
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
		Puzzle puzzle = new Puzzle();
		return puzzle;
	}
	
	public List<Rank> getRanking() throws WWWordzException{
		return null;
	}
	
	public long register(String nick, String password) throws WWWordzException{
		Player player= new Player(nick,password);
		
		return 0;
	}
	
	public void setPoints(String nick, int points) throws WWWordzException{
		
		
	}
	
	public long timeToNextPlay() {
		return 0;
		
	}
}
