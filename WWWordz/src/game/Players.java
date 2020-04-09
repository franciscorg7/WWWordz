package game;

import java.io.File;

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
	
	public void cleanup() {
		
	}
	
	public static File getHome() {
		return null;
	}
	
	public static Players getInstance() {
		if(single_instance == null) 
            single_instance = new Players(); 
  
        return single_instance;
	}
	
	public Player getPlayer(String nick) {
		return null;
	}
	
	public void resetPoints(String nick) throws WWWordzException {
		
	}
	
	public static void setHome(File home) {
		
	}
	
	public boolean verify(String nick, String password) {
		return false;
	}

}
