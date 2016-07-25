import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Renderer extends JPanel {
	int s_x = 0;
	int s_y = 0;
	Logic gamelogic;
	Queue<Segment> seg;
	
	public Renderer(){
		JFrame frame = new JFrame("Snake game!");
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setResizable(false);
		setPreferredSize(new Dimension(605, 506));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocusInWindow();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		gamelogic = new Logic(this);
		seg =gamelogic.getAllSegments();
		
	}
	
	
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.YELLOW);
		if (gamelogic != null)
		g.fillRect(gamelogic.a.x, gamelogic.a.y, 10, 10);			
		g.setColor(Color.RED);
		if (seg != null){
			
			for (Segment s:seg){
				s_x = s.x; s_y = s.y;
				g.fillRect(s.x, s.y, s.h, s.w);
				
			}
			g.setColor(Color.GREEN);
			g.fillRect(s_x, s_y, 10, 10);
			
		}
			
		Toolkit.getDefaultToolkit().sync();
		
	}

	
	

}
