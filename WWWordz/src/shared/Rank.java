package shared;

public class Rank
extends java.lang.Object
implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int accumulated;
	String nick;
	int points;
	
	public Rank() {
	}
	
	public Rank(String nick, int points, int accumulated) {
		this.nick = nick;
		this.points = points;
		this.accumulated = accumulated;
	}
	
	public int getAccumulated() {
		return this.accumulated;
	}
	
	public String getNick() {
		return this.nick;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void setAccumulated(int accumulated) {
		this.accumulated = accumulated;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
}
