package game;

import java.io.File;
import game.Player;
import shared.WWWordzException;

public class Players 
extends java.lang.Object
implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Players single_instance = null;
	
	public void addPoints(String nick, int points) throws WWWordzException {
		
	}
	
	@SuppressWarnings("unused")
	private void cleanup() {
		
	}
	
	public static File getHome() {
		File file = new File(System.getProperty("user.home"));
		return file;
	}
	
	public static Players getInstance() {
		if(single_instance == null) 
            single_instance = new Players(); 
  
        return single_instance;
	}
	
	public Player getPlayer(String nick) {
		Player player = new Player(nick);
		return player;
	}
	
	public void resetPoints(String nick) throws WWWordzException {
		player.points=0;
		
	}
	
	public static void setHome(File home) {
		home.mkdir();
		
	}
	
	public boolean verify(String nick, String password) {
		String pass = nick.getPassword();
		return false;
	}
	public static void main(String[] args) {
		File file= getHome();
		System.out.println(file);
		setHome(file);
	}

}
