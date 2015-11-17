package com.anemortalkid.keys;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A demo where pressing certain keys will cause the buttons to toggle on or off
 * Requires java 8
 *
 */
public class KeysButtons extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7225143311390401362L;
	/**
	 * IF java 7: Map<Integer, JButton> buttonsByKeyCode = new HashMap<Integer,
	 * JButton>();
	 */
	Map<Integer, JButton> buttonsByKeyCode = new HashMap<>();

	public KeysButtons() {
		JPanel panel = new JPanel(new GridLayout(9, 3));

		// Gotta add this to each button since they will have the focus
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("keyTyped");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("keyReleased");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = KeyEvent.getExtendedKeyCodeForChar(e.getKeyChar());
				JButton button = buttonsByKeyCode.get(keyCode);
				if (button != null) {
					button.setVisible(!button.isVisible());
					KeysButtons.this.repaint();
				}
			}
		};

		char[] alphabet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		for (char letter : alphabet) {
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(letter);
			JButton button = new JButton(letter + "");
			buttonsByKeyCode.put(keyCode, button);
			button.addKeyListener(keyListener);
			panel.add(button);
		}
		this.add(panel);
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Button demo");
		// let swing tell us how to live our life
		this.pack();

	}

	public static void main(String[] args) {
		new KeysButtons().setVisible(true);
	}

}
