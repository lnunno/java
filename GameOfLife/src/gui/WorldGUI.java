package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import logic.World;

public class WorldGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = -2078726180446271356L;
	private static final Dimension GUI_SIZE = new Dimension(800,800);
	private World world;
	private WorldPanel panel;
	private int cellSize;
	private static final int TIMER_DELAY_MILLIS = 250;

	public WorldGUI(World world, int cellSize){
		super();
		this.setResizable(false);
		this.world = world;
		this.cellSize = cellSize;
	}
	
	public void init(){
		this.setSize(GUI_SIZE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		panel = constructPanel();
		this.add(panel);
	}
	
	private WorldPanel constructPanel() {
		WorldPanel jp = new WorldPanel(world,cellSize);
		jp.setSize(GUI_SIZE);
		return jp;
	}

	public static void main(String[] args) {
		int cellSize = 5;
		World world = randomWorld(cellSize);
		WorldGUI gui = new WorldGUI(world,cellSize);
		gui.init();
		Timer timer = new Timer(TIMER_DELAY_MILLIS, gui);
		timer.start();
		gui.nextIteration();
	}

	private static World randomWorld(int cellSize) {
		int dim = GUI_SIZE.width/cellSize;
		World w = new World(dim);
		Random rand = new Random();
		for(int i = 0; i < dim; i++) 
			{
				for(int j = 0; j < dim; j++)
					{
						boolean makeCell = rand.nextBoolean();
						if(makeCell)w.makeCell(i, j);
					}
			}
		return w;
	}

	public void nextIteration(){
		world.nextIteration();
		panel.setWorld(world);
		panel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		nextIteration();
	}
}
