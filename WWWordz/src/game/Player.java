package game;

public class Player 
extends java.lang.Object
implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int accumulated;
	String nick;
	String password;
	int points;
	
	public Player(String nick, String password) {
		this.setNick(nick);
		this.setPassword(password);
		
	}

	public int getAccumulated() {
		return accumulated;
	}

	public void setAccumulated(int accumulated) {
		this.accumulated += accumulated;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public  String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
