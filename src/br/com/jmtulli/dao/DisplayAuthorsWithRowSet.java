package br.com.jmtulli.dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

/**
 * Class to use JDBC Row Set methods to show results of the select query.<br>
 * Based on example from Deitel's Java HTP book
 */
public class DisplayAuthorsWithRowSet {

	private final String url;
	private final String user;
	private final String password;
	private final String query;

	public DisplayAuthorsWithRowSet(String url, String user, String password, String query) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.query = query;
	}

	public void ShowAuthors() {
		try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
			rowSet.setUrl(url);
			rowSet.setUsername(user);
			rowSet.setPassword(password);
			rowSet.setCommand(query);
			rowSet.execute();
			ResultSetMetaData authors = rowSet.getMetaData();
			int columnCount = authors.getColumnCount();

			System.out.printf("Authors Table of books database %n%n");
			for (int i = 1; i <= columnCount; i++) {
				System.out.printf("%-8s\t", authors.getColumnName(i));
			}
			System.out.println();
			while (rowSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.printf("%-8s\t", rowSet.getObject(i));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
