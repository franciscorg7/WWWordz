package game;

import java.io.File;
import java.util.HashMap;

import game.Player;
import shared.WWWordzException;

public class Players 
extends java.lang.Object
implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,Player> theplayers;
	private static Players single_instance = null;
	private Players() {
		theplayers = new HashMap<>();
	}
	public void addPoints(String nick, int points) throws WWWordzException {
		Player player = theplayers.get(nick);
		int cur =player.getPoints();
		player.setPoints(points + cur);
	}
	
	@SuppressWarnings("unused")
	private void cleanup() {
		
	}
	
	public static File getHome() {
		File homedir = new File(System.getProperty("user.dir"));
		return file;
	}
	
	public static Players getInstance() {
		if(single_instance == null) 
            single_instance = new Players(); 
  
        return single_instance;
	}
	
	public Player getPlayer(String nick) {
		Player player = theplayers.get(nick);
		return player;
	}
	
	public void resetPoints(String nick) throws WWWordzException {
		Player player = theplayers.get(nick);
		player.setPoints(0);
		
	}
	
	public static void setHome(File home) {
		
	}
	/**truee if passwords match */
	public  boolean verify(String nick, String password) {
		return getPlayer(nick).getPassword().equals(password);
	}
	public static void main(String[] args) {
		Player player = new Player("ola","asd");
	//	Boolean verify =verify("ola","asd");
	//System.out.println(verify);
		/** cant test**/
		
		/**System.out.println(file);
		setHome(file);**/
	}

}
