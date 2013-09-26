package mover;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;

/**
 * @author Lucas
 */
public class Mover {
	private static final boolean DEBUG = false;
	private static final XRandom rand = new XRandom(8);
	private Robot robot;

	public Mover(){
		//Initialize robot.
		robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			System.err.println("Problem creating robot.");
		}
	}
	
	/**
	 * If you want to use your own Robot, go ahead.
	 * @param robot your Robot.
	 */
	public Mover(Robot robot){ 
		if(robot == null) throw new IllegalArgumentException("Cannot be null");
		this.robot = robot;
	}
	public void run(){
		wasdRandomly(robot, 100);
	}
	private void wasdRandomly(Robot robot, int times){
		for(int i = 0; i < 100; i++){
			if(DEBUG)System.out.println((char)randomWASD());
			int wasdKey = randomWASD();
			robot.keyPress(wasdKey);
			robot.delay((int) rand.nextExponential(500));
			robot.keyRelease(wasdKey);
		}
	}
	private static int randomWASD() {
		int randInt = rand.nextInt(4)+1;
		switch(randInt){
			case 1:
				return KeyEvent.VK_W;
			case 2:
				return KeyEvent.VK_A;
			case 3:
				return KeyEvent.VK_S;
			case 4:
				return KeyEvent.VK_D;
			default:
				throw new NoSuchElementException("Check WASD generator.");
		}
	}
	public static void main(String[] args) {
		Mover mover = new Mover();
		mover.run();
	}

}
