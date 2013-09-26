package robot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.Timer;

import misc_bots.ButtonHolder;
import mover.Mover;
import clicker.Clicker;

/**
 * Runs the Robot applications with a GUI representation.
 * @author Lucas
 */
public class RobotRunner {
	private int delayTime = 10; //Num of seconds to delay.
	private int currentTime = delayTime;
	private Timer timer = new Timer(1000, new TimerActionListener());
	private RunnerGUI gui = new RunnerGUI("Bot Interface");
	private String runningName;
	/** Indicates the countdown is done and the bot is ready to start. */
	private boolean readyToStart = false; 
	public class RunnerGUI extends ButtonGUI{
		private static final long serialVersionUID = 8831134367325402234L;
		private MessagePanel messagePanel;
		private int border = 20; //Num pixel border.
		private int heldButton = KeyEvent.VK_C; //Button to hold down.
		private int countdownTime = 20;
		private int runTime = 60*15; //Time for the bots to run in seconds.
		public void printMessage(String msg){
			messagePanel.displayMessage(msg);
		}
		public RunnerGUI(String title) {
			super(title);
			setUpButtons();
			addMessagePanel();
//			this.pack();
			setLocationRelativeTo(null);
		}

		private void addMessagePanel() {
			Dimension containerSize = getSize(); //Get size of the frame.
			Dimension messagePanelDim = new Dimension(containerSize.width-border*2, 
					(containerSize.height/4)-border*2);
			messagePanel = new MessagePanel(messagePanelDim); 
			this.add(messagePanel, BorderLayout.SOUTH);
		}

		private void setUpButtons() {
			JButton[] inputs = super.getButtons();
			inputs[0].setText("Start Clicker");
			inputs[1].setText("Start Mover");
			inputs[2].setText("Start Combined #1");
			inputs[3].setText("Start Combined #2");
			configureButtonActions(inputs);
		}

		private void configureButtonActions(JButton[] inputs) {
			inputs[0].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					resetTimer();
					runningName = "Clicker";
					printMessage("Clicker Clicked.");
					Clicker clicker = new Clicker();
					//Wait for 10 seconds. Then start bot.
					countdown(countdownTime);
//					clicker.run();
				}
			});
			inputs[1].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					resetTimer();
					runningName = "Mover";
					printMessage("Mover clicked.");
					Mover mover = new Mover();
					countdown(countdownTime);
				}
			});
			inputs[2].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					resetTimer();
					currentTime = countdownTime;
					readyToStart = false;
					runningName = "Combined #1";
					printMessage("Combined #1 clicked.");
					countdown(countdownTime);
				}
			});
			inputs[3].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					resetTimer();
					currentTime = countdownTime;
					runningName = "Combined #2";
//					printMessage("Combined #2 clicked.");
//					countdown(runTime);
					ButtonHolder holder = new ButtonHolder(heldButton, runTime);
					holder.run();
				}
			});
		}
	}
	/**
	 * Wait for <code>numSeconds</code> seconds.
	 * @param numSeconds the number of seconds to wait.
	 */
	@SuppressWarnings("unused")
	private void delay(int numSeconds){
		try {
			Thread.sleep(numSeconds*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	private void resetTimer(){
		currentTime = delayTime;
		readyToStart = false;
	}
	private class TimerActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(currentTime >= 0) 
				gui.printMessage("Starting in " + currentTime-- + " seconds.");
			else{
				gui.printMessage("Running " + runningName);
				currentTime = delayTime;
				readyToStart = true;
				timer.stop();
			}
		}
		
	}
	private void countdown(int start){
		timer.start();
	}
	public void run(){
		gui.setVisible(true);
	}
	public static void main(String[] args) {
		RobotRunner runner = new RobotRunner();
		runner.run();
	}

}
