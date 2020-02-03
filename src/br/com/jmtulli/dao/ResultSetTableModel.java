package br.com.jmtulli.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {

	private Statement statement;
	private Connection connection;
	private ResultSet resultSet;
	private ResultSetMetaData data;
	private int rows;

	public ResultSetTableModel(String url, String user, String password, String query) throws SQLException {
		connection = DriverManager.getConnection(url, user, password);
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		executeQuery(query);
	}

	public void executeQuery(String query) throws SQLException {
		resultSet = statement.executeQuery(query);
		data = resultSet.getMetaData();
		resultSet.last();
		rows = resultSet.getRow();
		fireTableStructureChanged();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Object.class;
	}

	@Override
	public int getRowCount() {
		return rows;
	}

	@Override
	public int getColumnCount() {
		try {
			return data.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			resultSet.absolute(rowIndex + 1);
			return resultSet.getObject(columnIndex + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void disconnectDB() {
		try {
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
