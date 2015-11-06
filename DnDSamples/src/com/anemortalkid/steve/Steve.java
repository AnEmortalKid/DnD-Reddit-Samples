package com.anemortalkid.steve;

import java.awt.BorderLayout;
import java.awt.TextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Steve {

	String input;
	String[] steve = { "you", "can", "start", "by" };
	JFrame frame;
	JTextField textField;

	public Steve() {
		frame = new JFrame();
		textField = new JTextField();
		// we set the columns to 20 at least so we can see something
		textField.setColumns(20);

		// we use this nice panel to put everything in its center
		JPanel myPanel = new JPanel();
		myPanel.add(textField, BorderLayout.CENTER);
		frame.add(myPanel, BorderLayout.CENTER);
		frame.setSize(300, 300);
		frame.setVisible(true);

		// you gotta call speech for it to ask
		speech1();
	}

	public void speech1() {
		for (String say : steve) {
			input = JOptionPane.showInputDialog("Complete Neil's phrase");
			if (input.equals(say)) {
				String previousText = textField.getText();
				textField.setText(previousText + " " + input);
			}

			else {
				JOptionPane.showMessageDialog(null, "Incorrect, you typed " + input);
				break;
			}
		}
	}

	public static void main(String[] args) {
		new Steve();
	}
}