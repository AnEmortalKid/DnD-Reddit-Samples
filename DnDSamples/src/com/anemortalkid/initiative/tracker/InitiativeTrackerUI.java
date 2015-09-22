package com.anemortalkid.initiative.tracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

/**
 * Shows how one could do an initiative tracker ui
 * 
 * @author JMonterrubio
 *
 */
public class InitiativeTrackerUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5569003624092384188L;
	private InitiativeInputPanel inputPanel;

	private JList sortedListModelList;

	/**
	 * Constructs a new UI with the given width and height
	 */
	public InitiativeTrackerUI(int width, int height) {
		this.setTitle("Initiative Tracker Sample");
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponent();
	}

	/**
	 * Adds the data to the sortedListModelList, via its model
	 * 
	 * @param data
	 *            the data to add
	 */
	public void addInitiativeData(InitiativeData data) {
		SortedListModel model = (SortedListModel) sortedListModelList.getModel();
		model.addElement(data);
	}

	private void initComponent() {
		inputPanel = new InitiativeInputPanel(this);

		List<InitiativeData> initialData = Stream.of(new InitiativeData("Test1", 15), new InitiativeData("test2", 12))
				.collect(Collectors.toList());

		sortedListModelList = new JList<>(new SortedListModel(initialData));

		// if selected and we press delete, we can remove the element
		sortedListModelList.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int index = sortedListModelList.getSelectedIndex();

				// nothing selected
				if (index == -1)
					return;

				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					SortedListModel model = (SortedListModel) sortedListModelList.getModel();
					model.removeElementAt(index);
				}
			}
		});

		JScrollPane pane = new JScrollPane(sortedListModelList);
		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),
				"Initiative List", TitledBorder.CENTER, TitledBorder.TOP));
		this.add(pane, BorderLayout.CENTER);
		this.add(inputPanel, BorderLayout.NORTH);
	}

	/**
	 * Quick hackaround for a sorted list model, for better approach look at:
	 * http://www.oracle.com/us/technologies/java/sorted-jlist-136883.html
	 * 
	 * @author JMonterrubio
	 *
	 */
	private class SortedListModel extends DefaultListModel<InitiativeData> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3790682051675465962L;
		private List<InitiativeData> innerData;

		private SortedListModel() {
			this.innerData = new ArrayList<>();
		}

		private SortedListModel(List<InitiativeData> initElements) {
			this.innerData = new ArrayList<>(initElements);
			updateElementsWithInnerData();
		}

		@Override
		public void addElement(InitiativeData element) {
			this.innerData.add(element);
			updateElementsWithInnerData();
		}

		@Override
		public void removeElementAt(int index) {
			this.innerData.remove(index);
			updateElementsWithInnerData();
		}

		private void updateElementsWithInnerData() {
			Collections.sort(this.innerData);
			this.removeAllElements();
			this.innerData.forEach(super::addElement);
		}

	}

	/**
	 * A panel for our inputs, holds the buttons and text fields it needs
	 * 
	 * @author JMonterrubio
	 *
	 */
	private class InitiativeInputPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6524433127725511843L;
		private JButton addButton;
		private JTextField labelField;
		private JTextField initiativeField;
		private InitiativeTrackerUI parent;

		// regular expression for only parsing ints
		final String regex = "\\d+";
		final Pattern digitPattern = Pattern.compile(regex);

		private InitiativeInputPanel(InitiativeTrackerUI parent) {
			this.parent = parent;
			this.setLayout(new GridLayout(1, 5, 5, 5));
			initComponent();
		}

		private void initComponent() {
			this.add(new JLabel("Label:"));
			labelField = new JTextField();
			this.add(labelField);

			this.add(new JLabel("Initiative:"));
			initiativeField = new JTextField();
			this.add(initiativeField);

			addButton = new JButton(new AddInitiativeAction(this));
			this.add(addButton);
		}

		/**
		 * Adds the data to the model
		 * 
		 * @param data
		 */
		public void addDataToModel(InitiativeData data) {
			this.parent.addInitiativeData(data);
		}

		/**
		 * Returns the data contained in labelField
		 * 
		 * @return the data in the label field
		 */
		public String getLabelData() {
			return labelField.getText();
		}

		/**
		 * Returns the data from the initiative field, as an int. If the data is
		 * invalid, we will get a 0
		 * 
		 * @return the initiative value, or 0 if nothing was specified
		 */
		public int getInitiativeData() {
			String initativeFieldText = initiativeField.getText();
			Matcher matcher = digitPattern.matcher(initiativeField.getText());
			if (matcher.matches()) {
				return Integer.parseInt(initiativeField.getText());
			}

			// ideally, the ui would not let us press add
			return 0;
		}

		public void clearData() {
			labelField.setText("");
			initiativeField.setText("");
		}
	}

	/**
	 * The add initiative action lets us take the data from the input panel,
	 * construct a new InitiativeData and add it to the model
	 * 
	 * @author JMonterrubio
	 *
	 */
	private class AddInitiativeAction extends AbstractAction {

		private InitiativeInputPanel inputPanel;

		private AddInitiativeAction(InitiativeInputPanel inputPanel) {
			super("+");
			this.inputPanel = inputPanel;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// get the values from the fields, assume is valid
			String label = inputPanel.getLabelData();
			int initiative = inputPanel.getInitiativeData();

			InitiativeData data = new InitiativeData(label, initiative);
			inputPanel.clearData();

			inputPanel.addDataToModel(data);
		}

	}

	public static void main(String[] args) {
		InitiativeTrackerUI ui = new InitiativeTrackerUI(600, 600);
		ui.setVisible(true);
	}

}
