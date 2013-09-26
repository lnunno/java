package ver2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FieldPanel extends JPanel {
	private static final long serialVersionUID = -7066150352897698435L;
	
	private int cellSize = 5;
	
	private Field field;
	private int delay = 250;
	
	public class FieldListener implements ActionListener{
		
		private Field f;
		private JPanel panel;
		
		public FieldListener(Field f, JPanel panel){
			this.f = f;
			this.panel = panel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			f.nextIteration();
			panel.repaint();
		}
		
	}
	
	public FieldPanel(Field field){
		super();
		this.field = field;
		setPreferredSize(new Dimension(field.getWidth()*cellSize, field.getHeight()*cellSize));
		JFrame containingFrame = new JFrame();
		containingFrame.add(this);
		containingFrame.pack();
		containingFrame.setTitle("FieldPanel");
		containingFrame.setResizable(false);
		containingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		containingFrame.setLocationRelativeTo(null);
		containingFrame.setVisible(true);
	}
	
	public void run(){
		Timer timer = new Timer(delay, new FieldListener(field,this));
		timer.start();
	}
	
	private int colorAcceleration = 10;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		for(int i = 0; i < field.getHeight(); i++){
			for(int j = 0; j < field.getWidth(); j++){
				int gridValue = field.getFieldIndex(j, i);
				if(gridValue > 0 ){
					int v = 255-((gridValue-1)*colorAcceleration);
					int newColorVal =  v < 0 ? 0 : v; 
					Color c = new Color(0, 0, newColorVal);
					g.setColor(c);
					g.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int ruleNum = Field.randomRuleNumber();
		Field f = new Field(300,150);
		System.out.println("Using rule #"+ruleNum);
		f.randomize();
		FieldPanel fp = new FieldPanel(f);
		fp.run();
	}

}
