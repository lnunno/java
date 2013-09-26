package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logic.Cell;
import logic.World;

public class WorldPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = -5601411246211286733L;
	private World world;
	private int cellSize;

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Cell[][] arr = world.getBoard();
		//Draw grid.
		g.setColor(Color.gray);
		for(int i = cellSize; i < this.getHeight(); i+=cellSize) g.drawLine(0, i, this.getWidth(), i);
		for(int i = cellSize; i < this.getWidth(); i+=cellSize) g.drawLine(i, 0, i, this.getHeight());
		//Draw cells.
		g.setColor(Color.black);
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[0].length; j++){
				if(arr[i][j] != null) g.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
			}
			
		}
	}

	public WorldPanel(World world, int cellSize){
		super();
		this.world = world;
		this.cellSize = cellSize;
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("Clicked at x="+x+" y="+y);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param world the world to set
	 */
	public void setWorld(World world) {
		this.world = world;
	}

}
