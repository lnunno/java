package clicker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * @author Lucas
 */
public class Clicker {
	Robot robot;
	public Clicker(){
		robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			System.err.println("Error making Robot.");
			System.exit(1);
		}
	}
	
	/**
	 * If you want to use your own Robot.
	 * @param robot
	 */
	public Clicker(Robot robot){
		if(robot == null) throw new IllegalArgumentException("Cannot be null");
		this.robot = robot;
	}
	public void run(){
		for(int i = 0; i < 10; i++){
			// Simulate a mouse click
		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(700);
		}
	}
	public static void main(String[] args) {
		Clicker clicker = new Clicker();
		clicker.run();
	}

}
