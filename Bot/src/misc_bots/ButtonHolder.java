package misc_bots;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Holds down the button given for the specified amount of time.
 * @author Lucas
 */
public class ButtonHolder {

	private static final boolean BUTTON_SWITCH = false;
	private Robot robot;
	private int heldButton;
	private int timeToHold;
	
	public ButtonHolder(int button, int seconds){
		robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			System.err.println("Error making Robot.");
			System.exit(1);
		}
		heldButton = button;
		timeToHold = seconds;
	}
	
	public void run(){
		for( int i = 0; i < timeToHold; i++){
			if(BUTTON_SWITCH && i == timeToHold/2){
				robot.keyPress(KeyEvent.VK_2);
				robot.delay(250);
				robot.keyRelease(KeyEvent.VK_2);
			}
			robot.keyPress(heldButton);
			robot.delay(1000);
			robot.keyRelease(heldButton); //Release the held button.
		}
		
	}
}
