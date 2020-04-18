package game;

import java.io.File;
import java.util.HashMap;

import game.Player;
import shared.WWWordzException;

public class Players extends java.lang.Object implements java.io.Serializable {

	/**
	 * 
	 */
	private static final String DATA_FILE = "players.ser";
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	static HashMap<String, Player> theplayers;
=======
>>>>>>> branch 'master' of https://github.com/franciscorg7/WWWordz.git
	private static Players single_instance = null;
<<<<<<< HEAD

=======
	private static File home = new File(System.getProperty("user.dir"));
	private static File data = new File(home,DATA_FILE);
	 HashMap<String, Player> theplayers = new HashMap<>();
>>>>>>> branch 'master' of https://github.com/franciscorg7/WWWordz.git
	private Players() {
	}

	public void addPoints(String nick, int points) throws WWWordzException {
		if (theplayers.get(nick) != null) {
			Player player = theplayers.get(nick);
			int cur = player.getPoints() + points;
			player.setPoints(cur);
		} else {
			throw new WWWordzException("Player should be created");
		}
	}
<<<<<<< HEAD

	@SuppressWarnings("unused")
=======
	
>>>>>>> branch 'master' of https://github.com/franciscorg7/WWWordz.git
	public void cleanup() {
		theplayers.clear();
	}

	public static File getHome() {
<<<<<<< HEAD
		File home = new File(System.getProperty("user.dir"));
=======
>>>>>>> branch 'master' of https://github.com/franciscorg7/WWWordz.git
		return home;
	}

	public static Players getInstance() {
		if (single_instance == null)
			single_instance = new Players();

		return single_instance;
	}

	public Player getPlayer(String nick) {
		Player player = theplayers.get(nick);
		return player;
	}
<<<<<<< HEAD

	public static void resetPoints(String nick) throws WWWordzException {
		if (theplayers.get(nick) != null) {
			theplayers.get(nick).setPoints(0);
		} else {
=======
	
	public void resetPoints(String nick) throws WWWordzException {
		if(theplayers.get(nick)!=null) {
		theplayers.get(nick).setPoints(0);
	}
		else {
>>>>>>> branch 'master' of https://github.com/franciscorg7/WWWordz.git
			throw new WWWordzException("Player should be created");
		}
	}

	public static void setHome(File home) {
<<<<<<< HEAD
		File home1 = new File(System.getProperty("user.dir"));
		File Data = new File(home1, "Data");

=======
		 Players.home=home;
		 Players.data = new File(home,DATA_FILE);
		
>>>>>>> branch 'master' of https://github.com/franciscorg7/WWWordz.git
	}

	/* true if passwords match */
	public boolean verify(String nick, String password) {
		if (getPlayer(nick) == null) {
			theplayers.put(nick, new Player(nick, password));
			return true;
		} else {

			return getPlayer(nick).getPassword().equals(password);
		}
	}

<<<<<<< HEAD
	public static void main(String[] args) throws WWWordzException {
		Player player = new Player("ola", "asd");
		int points = 100;
		player.setPoints(points);
		System.out.println(player.getPoints());
		resetPoints("ola");
		System.out.println(player.getPoints());

		// Boolean verify =verify("ola","asd");
		// System.out.println(verify);
	}
=======
>>>>>>> branch 'master' of https://github.com/franciscorg7/WWWordz.git

}
