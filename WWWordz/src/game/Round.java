package game;

import java.util.Date;
import java.util.List;
import java.util.Map;

import shared.Puzzle;
import shared.Rank;
import shared.WWWordzException;

public class Round
extends java.lang.Object {
	private Date end;
	private Date join;
	private Date play;
	private Puzzle puzzle;
	private Date ranking;
	private Date report;
	private Map<String, Player> roundPlayers;
	
	public Round() {
		
	}
	
	public static long getJoinStageDuration() {
		return 0;
	}
	
	public static long getPlayerStageDuration() {
		return 0;
	}
	
	public Puzzle getPuzzle() throws WWWordzException {
		return this.puzzle;
	}
	
	public List<Rank> getRanking() throws WWWordzException {
		return null;
	}
	
	public static long getRankingStageDuration() {
		return 0;
	}
	
	public static long getRoundStageDuration() {
		return 0;
	}
	
	public long getTimeNextPlay() {
		return 0;
	}
	
	public long register(String nick, String password) throws WWWordzException {
		return 0;
	}
	
	public static void setJoinStageDuration(long joinStageDuration) {
		
	}
	
	public static void setPlayerStageDuration(long playStageDuration) {
		
	}
	
	public void setPoints(String nick, int points) throws WWWordzException {
		
	}
	
	public static void setRankingStageDuration(long rankingStageDuration) {
		
	}
	
	public static void setReportsStageDuration(long reportStageDuration) {
		
	}
}
