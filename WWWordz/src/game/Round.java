package game;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;

import shared.Configs;
import shared.Puzzle;
import shared.Rank;
import shared.WWWordzException;

public class Round
extends java.lang.Object {
	 static Date end ;
	public  static Date join;
	 public static Date play;
	 Puzzle puzzle;
	public static Date ranking;
	 public static Date report;
	 Map<String, Player> roundPlayers;
	public Round() {
		
	}
	@BeforeAll
	public static void init() {
		setjoin
		
	}
	
	public static long getJoinStageDuration(){
		return Configs.JOIN_STAGE_DURATION;
		
	}
	/*
	 * return join.getTime();
	 *  ou por tudo a static dada as diferenças de tipos , confuso ///
	 * 
*/	
	public static long getPlayStageDuration() {
		return Configs.PLAY_STAGE_DURATION;
	}
	
	public Puzzle getPuzzle() throws WWWordzException {
		return this.puzzle;
	}
	
	
	public List<Rank> getRanking() throws WWWordzException {
		return null;
	}
	
	public static long getRankingStageDuration() {
		return Configs.RANKING_STAGE_DURATION;
	}
	
	public static long getRoundStageDuration() {
		return 0;
	}
	
	public long getTimetoNextPlay() {
		return 0;
	}
	
	public long register(String nick, String password) throws WWWordzException {
		Player player=new Player(nick,password);
		
		return 0;
	}
	
	public static void setJoinStageDuration(long joinStageDuration) {
		
	
		
	}
	
	public static void setPlayStageDuration(long playStageDuration) {
		
		
	}
	
	public void setPoints(String nick, int points) throws WWWordzException {
		
		
	}
	
	public static void setRankingStageDuration(long rankingStageDuration) {
		
	}
	
	public static void setReportStageDuration(long reportStageDuration) {
		
	}
	
	public static void main(String[] args) {
		getJoinStageDuration();
		
	}
}
