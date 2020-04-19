package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import game.Players;
import shared.Configs;
import shared.Puzzle;
import puzzle.Generator;
import shared.Rank;
import shared.WWWordzException;

public class Round extends java.lang.Object {

	enum Stage {
		join, play, report, ranking
	};

	enum Relative {
		before, after
	};

	private static long join_config = Configs.JOIN_STAGE_DURATION;
	private static long play_config = Configs.PLAY_STAGE_DURATION;
	private static long ranking_config = Configs.RANKING_STAGE_DURATION;
	private static long report_config = Configs.REPORT_STAGE_DURATION;

	Date join = new Date();
	Date play = new Date(join.getTime() + join_config);
	Date report = new Date(play.getTime() + play_config);
	Date ranking = new Date(report.getTime() + report_config);
	Date end = new Date(ranking.getTime() + ranking_config);

	Puzzle puzzle;
	private Players theplayers = Players.getInstance();
	Map<String, Player> roundPlayers = new HashMap<>();

	public Round() {
	}

	public static long getJoinStageDuration() {
		return join_config;
	}

	public static long getPlayStageDuration() {
		return play_config;
	}

	public Puzzle getPuzzle() throws WWWordzException, IOException {
		puzzle = Generator.generate();
		
		Date cur = new Date();
		
		if (cur.before(play)) {
			throw new WWWordzException("Expected before play stage");
		}

		else if (cur.after(report))
			throw new WWWordzException("Expected after play stage");

		return puzzle;
	}

	public List<Rank> getRanking() throws WWWordzException {
		Date cur = new Date();
		if(cur.before(ranking))
			throw new WWWordzException("Expected in stage");
		
		List<Rank> ranking = new ArrayList<>();
		List<Player> players = new ArrayList<>();

		Iterator<Entry<String, Player>> it = roundPlayers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Player> pair = (Map.Entry<String, Player>) it.next();
			Player player = pair.getValue();
			players.add(player);
		}
		
		Collections.sort(players,new Comparator<Player>() {
			@Override
			public int compare(Player player, Player other) {
				return other.points - player.points;
			}
		});
		
		for(Player p : players) {
			Rank rank = new Rank(p.getNick(), p.getPoints(), p.getAccumulated());
			ranking.add(rank);
		}

		return ranking;
	}

	public static long getRankingStageDuration() {
		return ranking_config;
	}

	public static long getRoundStageDuration() {
		return play_config + join_config + ranking_config + report_config;
	}

	public long getTimetoNextPlay() {
		Date cur = new Date();
		if (cur.before(play))
			return play.getTime() - cur.getTime();
		else
			return end.getTime() - cur.getTime() + join_config;
	}

	public long register(String nick, String password) throws WWWordzException {
		Date cur = new Date();

		if (cur.after(play))
			throw new WWWordzException("Set points at different stages");
		else if (!theplayers.verify(nick, password))
			throw new WWWordzException("Invalid User");
		
		Player player = theplayers.getPlayer(nick);
		roundPlayers.put(nick, player);
		return play.getTime() - cur.getTime();
	}

	public static void setJoinStageDuration(long joinStageDuration) {
		Round.join_config = joinStageDuration;

	}

	public static void setPlayStageDuration(long playStageDuration) {
		Round.play_config = playStageDuration;
	}

	public void setPoints(String nick, int points) throws WWWordzException {
		Date cur = new Date();
		if (cur.before(report))
			throw new WWWordzException("Set points at different stages");
		else if (cur.after(ranking))
			throw new WWWordzException("Exception expected in rankings stage");
		
		roundPlayers.get(nick).setPoints(points);
	}

	public static void setRankingStageDuration(long rankingStageDuration) {
		Round.ranking_config = rankingStageDuration;
	}

	public static void setReportStageDuration(long reportStageDuration) {
		Round.report_config = reportStageDuration;
	}

	public static void main(String[] args) throws IOException {
		long c = getJoinStageDuration();
		System.out.print(c);
	}
}
