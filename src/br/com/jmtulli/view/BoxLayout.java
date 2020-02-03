package br.com.jmtulli.view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class BoxLayout {
	public static void main(String[] args) {
		{
			BoxLayoutFrame boxLayoutFrame = new BoxLayoutFrame();
			boxLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			boxLayoutFrame.setSize(400, 220);
			boxLayoutFrame.setVisible(true);
		}
	}
}

class BoxLayoutFrame extends JFrame {
	public BoxLayoutFrame() {
		super("Demonstrating BoxLayout");

		Box horizontal1 = Box.createHorizontalBox();
		Box vertical1 = Box.createVerticalBox();
		Box horizontal2 = Box.createHorizontalBox();
		Box vertical2 = Box.createVerticalBox();

		final int SIZE = 3;

		for (int count = 0; count < SIZE; count++)
			horizontal1.add(new JButton("Button " + count));

		for (int count = 0; count < SIZE; count++) {
			vertical1.add(Box.createVerticalStrut(25));
			vertical1.add(new JButton("Button " + count));
		}

		for (int count = 0; count < SIZE; count++) {
			horizontal2.add(Box.createHorizontalGlue());
			horizontal2.add(new JButton("Button " + count));
		}

		for (int count = 0; count < SIZE; count++) {
			vertical2.add(Box.createRigidArea(new Dimension(12, 8)));
			vertical2.add(new JButton("Button " + count));
		}

		JPanel panel = new JPanel();
		panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));

		for (int count = 0; count < SIZE; count++) {
			panel.add(Box.createGlue());
			panel.add(new JButton("Button " + count));
		}

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

		tabs.addTab("Horizontal Box", horizontal1);
		tabs.addTab("Vertical Box with Struts", vertical1);
		tabs.addTab("Horizontal Box with Glue", horizontal2);
		tabs.addTab("Vertical Box with Rigid Areas", vertical2);
		tabs.addTab("Vertical Box with Glue", panel);

		add(tabs);
	}
}