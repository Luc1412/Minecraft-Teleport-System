package de.luc1412.tp.bungee.utils;

import me.creepplays.asyncable.mysql.AsyncableMySQL;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class DatabaseManager {

	private static final String TABLENAME = "player_homes";
	private static final String TABLENAME_2 = "warps";
	private AsyncableMySQL asyncableMySQL;

	public DatabaseManager(String host, int port, String database, String username, String password) {

		asyncableMySQL = new AsyncableMySQL(host, port, username, password, database);

		asyncableMySQL.update("CREATE TABLE IF NOT EXISTS " + TABLENAME + " (UUID VARCHAR(20),PlayerName VARCHAR(20),Locations VARCHAR)").run();
		asyncableMySQL.update("CREATE TABLE IF NOT EXISTS " + TABLENAME_2 + " (Name VARCHAR,Location VARCHAR)").run();
	}

	private boolean isUserExists(ProxiedPlayer player) {

		boolean[] result = new boolean[1];

		asyncableMySQL.query("SELECT Playername FROM " + TABLENAME + " WHERE UUID = " + player.getUniqueId().toString())
				.then(rs -> {
					result[0] = rs.next();
					return null;
				}).run();
		return result[0];
	}

	public void addPlayer(ProxiedPlayer player) {
		if (!isUserExists(player)) {
			asyncableMySQL.update("INSERT INTO " + TABLENAME + " (ID,UUID,PlayerName,Locations) VALUES (" + player.getUniqueId().toString() + "," + player.getName() + ",null:null)")
					.run();
		}
	}

	public void setHome(ProxiedPlayer player, int home_P, String location) {
		if (isUserExists(player)) {
			asyncableMySQL.update("UPDATE " + TABLENAME + " SET Playername = " + player.getName() + " WHERE UUID = " + player.getUniqueId()).run();

			String homes = getHomes(player);

			String[] splitHomes = homes.split(":");

			if (splitHomes.length < (home_P - 1)) {
				int var1 = (home_P - 1) - splitHomes.length;
				StringBuilder builder = new StringBuilder(homes);
				for (int i = 0; i < var1; i++) {
					builder.append(":").append("null");
				}
				homes = builder.toString();
				splitHomes = homes.split(":");
			}

			splitHomes[home_P - 1] = location;

			StringBuilder builder = new StringBuilder();

			boolean first = false;

			for (String home : splitHomes) {
				if (first) {
					builder.append(":");
				} else first = true;

				builder.append(home);
			}

			asyncableMySQL.update("UPDATE " + TABLENAME + " SET Locations = " + builder.toString() + " WHERE UUID = " + player.getUniqueId()).run();
		}
	}

	private String getHomes(ProxiedPlayer player) {
		String[] result = new String[1];
		if (isUserExists(player)) {
			asyncableMySQL.update("UPDATE " + TABLENAME + " SET Playername = " + player.getName() + " WHERE UUID = " + player.getUniqueId()).run();

			asyncableMySQL.query("SELECT Locations FROM " + TABLENAME + " WHERE UUID = " + player.getUniqueId().toString())
					.then(rs -> {
						rs.next();
						result[0] = rs.getString(1);
						return null;
					}).run();
		}
		return result[0];
	}

	public String getHome(ProxiedPlayer player, int home) {
		String homes = getHomes(player);

		String[] splitHomes = homes.split(":");
		return splitHomes[home - 1];
	}
}
