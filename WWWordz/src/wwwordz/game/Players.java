package wwwordz.game;

import java.io.File;
import java.util.HashMap;

import wwwordz.game.Player;
import wwwordz.shared.WWWordzException;

public class Players extends java.lang.Object implements java.io.Serializable {

	/**
	 * 
	 */
	private static final String DATA_FILE = "players.ser";
	private static final long serialVersionUID = 1L;

	static HashMap<String, Player> theplayers = new HashMap<>();

	private static Players single_instance = null;

	static File home = new File(System.getProperty("user.dir"));
	static File data = new File(home,DATA_FILE);

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

	public void cleanup() {
		theplayers.clear();
	}

	public static File getHome() {
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

	public static void resetPoints(String nick) throws WWWordzException {
		if(theplayers.get(nick)!=null) {
		theplayers.get(nick).setPoints(0);
	}
		else {
			throw new WWWordzException("Player should be created");
		}
	}

	public static void setHome(File home) {

		Players.home = home;
		Players.data = new File(home, DATA_FILE);
		
	}

	/* true if passwords match  */
	public boolean verify(String nick, String password) {
		if (getPlayer(nick) == null) {
			theplayers.put(nick, new Player(nick, password));
			return true;
		} else {

			return getPlayer(nick).getPassword().equals(password);
		}
	}
	
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

}
