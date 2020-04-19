package wwwordz.game;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import wwwordz.shared.Puzzle;
import wwwordz.shared.Rank;
import wwwordz.shared.WWWordzException;

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
	
	public Puzzle getPuzzle() throws WWWordzException, IOException{
		return round.getPuzzle();
	}
	
	public List<Rank> getRanking() throws WWWordzException{
		return round.getRanking();
	}
	
	public long register(String nick, String password) throws WWWordzException{
		
		return round.register(nick, password);
	}
	
	public void setPoints(String nick, int points) throws WWWordzException{
		round.setPoints(nick, points);
	}
	
	public long timeToNextPlay() {
		return round.getTimetoNextPlay();
		
	}
}
