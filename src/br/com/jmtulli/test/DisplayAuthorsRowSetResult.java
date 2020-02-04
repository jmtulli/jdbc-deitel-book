package br.com.jmtulli.test;

import br.com.jmtulli.dao.DisplayAuthorsWithRowSet;

/**
 * Test class for DisplayAuthorsWithRowSet.<br>
 * Show the result of the defult_query
 * 
 */
public class DisplayAuthorsRowSetResult {
	private static final String URL = "jdbc:mysql://localhost/db_books?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "root";
	private static final String DEFAULT_QUERY = "select * from Authors";

	public static void main(String[] args) {
		DisplayAuthorsWithRowSet authorsWithRowSet = new DisplayAuthorsWithRowSet(URL, USER, PASS, DEFAULT_QUERY);
		authorsWithRowSet.ShowAuthors();
	}
}
