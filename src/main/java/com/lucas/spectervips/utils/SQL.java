package com.lucas.spectervips.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.lucas.spectervips.Main;

public class SQL {
	
	public SQL() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:mysql://51.81.69.7:3306/s11_rspecter", "u11_m2LTrqd5Ck", ".mLRZzNe5V8o=yzNmONqJonH");
			Statement stmt = c.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS SpecterVips (Player TEXT, Tipo TEXT, Time TEXT, Inicio TEXT)");
			Main.plugin.info("SQL Connected sucess!");
			c.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getNewConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://51.81.69.7:3306/s11_rspecter", "u11_m2LTrqd5Ck", ".mLRZzNe5V8o=yzNmONqJonH");
	}
}
