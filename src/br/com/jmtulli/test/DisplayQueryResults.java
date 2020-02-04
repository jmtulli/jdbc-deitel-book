package br.com.jmtulli.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.jmtulli.dao.ResultSetTableModel;

/**
 * Test class for ResultSetTableModel.<br>
 * Show a table with the result of the defult_query
 * 
 */
public class DisplayQueryResults extends JFrame {

	private static ResultSetTableModel tableModel;
	private static final String URL = "jdbc:mysql://localhost/db_books?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "root";
	private static final String DEFAULT_QUERY = "select * from Authors";

	public static void main(String[] args) {

		try {
			tableModel = new ResultSetTableModel(URL, USER, PASS, DEFAULT_QUERY);
			JTable jTable = new JTable(tableModel);
			JButton submitButton = new JButton("Submit");

			JFrame window = new JFrame("Display Query Results");
			window.add(new JScrollPane(jTable), BorderLayout.CENTER);
			window.add(submitButton, BorderLayout.SOUTH);
			window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			window.setLocationRelativeTo(null);
			window.setSize(500, 250);
			window.setVisible(true);

			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						tableModel.executeQuery(DEFAULT_QUERY);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			window.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					tableModel.disconnectDB();
					System.exit(0);
				}
			});

		} catch (SQLException e) {
			e.printStackTrace();
			tableModel.disconnectDB();
			System.exit(1);
		}
	}
}
