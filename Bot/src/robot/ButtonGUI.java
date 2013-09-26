package robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Lucas
 *
 */
public class ButtonGUI extends SimpleGUI {
	private static final long serialVersionUID = 6052877475218203772L;
	private JPanel mainPanel;
	private static final Dimension buttonSize = new Dimension(50,50);
	private static final Dimension mainPanelSize = new Dimension(300,300);
	private JButton[] buttons;

	public ButtonGUI(String title) {
		super(title);
		constructPanel();
		constructButtons();
	}
	
	private void constructPanel() {
		mainPanel = new JPanel(new FlowLayout());
		mainPanel.setPreferredSize(mainPanelSize);
		mainPanel.setBackground(Color.BLACK);
		add(mainPanel);
	}

	private void constructButtons() {
		buttons = new JButton[4];
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton("Button " + i);
			buttons[i].setSize(buttonSize);
		}
		mainPanel.add(buttons[0]);
		mainPanel.add(buttons[1]);
		mainPanel.add(buttons[2]);
		mainPanel.add(buttons[3]);
	}

	public static void main(String[] args){
		new ButtonGUI("ButtonGUI").setVisible(true);
	}

	/**
	 * @return the buttons
	 */
	public JButton[] getButtons() {
		return buttons;
	}

	public Dimension getSize() {
		return mainPanelSize;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

}
