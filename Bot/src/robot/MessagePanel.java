package robot;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;
/**
 * Displays messages, capable of changing the message displayed.
 * @author Lucas
 *
 */
public class MessagePanel extends JTextArea {
	private static final long serialVersionUID = -8876534466024233006L;
	private Font messageFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	public MessagePanel(Dimension size){
		super();
		setMinimumSize(size);
		setPreferredSize(size);
		constructTextArea();
	}

	private void constructTextArea() {
		setEditable(false);
		setLineWrap(true);
		setFont(messageFont);
		setText("Waiting for an action.");
	}
	
	public void displayMessage(String message){
		setText(message);
	}

}
