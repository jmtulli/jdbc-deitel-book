package br.com.jmtulli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayAuthorsTable {

	public static void main(String[] args) {

		final String DB_URL = "jdbc:mysql://localhost/db_books?useSSL=false";
		final String SEL_QUERY = "SELECT authorID, firstName, lastName FROM AUTHORS";

		try (Connection connection = DriverManager.getConnection(DB_URL, "root", "root");
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SEL_QUERY)) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			System.out.printf("Authors Table of books database %n%n");
			for (int i = 1; i <= columnCount; i++) {
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			}
			System.out.println();
			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.printf("%-8s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
