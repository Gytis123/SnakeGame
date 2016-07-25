import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;

public class Logic implements ActionListener {
	public enum Direction{UP, RIGHT, DOWN, LEFT};
	private Direction direction;
	private Renderer render_pointer;
	private boolean running;
	private Segment head;
	public Apple a;
	private int bb = 0;
	private Queue<Segment> segments = null;
	private LinkedList<Direction> queueofdirections = null;
	
	public Logic(Renderer r){
		a = new Apple();
		segments = new LinkedList<Segment>();
		queueofdirections = new LinkedList<Direction>();
		head = createSegment(0,0);
		segments.offer(head);
		render_pointer = r;
		running = true;
		direction = Direction.RIGHT;
		render_pointer.addKeyListener(new KeyboardInput());
		Timer timer = new Timer(80, this);
		timer.start();
		
	}
	



	public Segment createSegment(int locx, int locy){
		Segment s = new Segment();
		s.w = s.h = 10;
		s.x = locx; s.y = locy;
		return s;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (running){
			
			render_pointer.repaint();
			
			
			move();
			
		}
	}
	
	
	public Queue<Segment> getAllSegments(){
		return segments;
	}
	
	
	
	public void move(){
		
		head = createSegment(head.x, head.y);
		if (head.x == a.x && head.y == a.y){
			allocateApple();
			bb += 2;
		}else if (bb == 0){
			
			segments.poll();
		}else if (bb > 0){
			bb--;
			
		}
		
		if(!queueofdirections.isEmpty()){
			setDirection(queueofdirections.remove());
			direction = getDirection();
		}
		
		switch(direction){
		case UP:
			head.y -= 11;
			break;
		case RIGHT:
			head.x += 11;
			break;
		case DOWN:
			head.y += 11;
			break;
		case LEFT:
			head.x -= 11;
			break;
		}
		
		
		
		segments.offer(head);
		
		
		
		
		
		
	}
	
	public  class Apple{
		public int x = (int)(Math.random() * 55) * 11;
		public  int y = (int)(Math.random() * 46) * 11;
		
	}
	
	
	private boolean containsInQueue(){
		
		for (Segment ss : segments){
			if (ss.x == a.x && ss.y == a.y){
				return true;
			}
		}
		return false;
	}
	
	private void allocateApple(){
		
		
		do {
			a.x = (int)(Math.random() * 55);
			a.y = (int)(Math.random() * 46);
			a.x *= 11; a.y *= 11;
			
		}while(containsInQueue());
		
		
	}
	
	private class KeyboardInput extends KeyAdapter{
		
		
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			
			if (KeyEvent.VK_SPACE == key){
				if (running){
					running = false;
				}else{
					running = true;
				}
			}	
				
			if (running){
				if (!queueofdirections.isEmpty())
				direction = queueofdirections.peekLast();
				
				if ((KeyEvent.VK_W == key || KeyEvent.VK_UP == key) && direction != Direction.DOWN){
					
					queueofdirections.offer(Direction.UP);
				}else if ((KeyEvent.VK_D == key || KeyEvent.VK_RIGHT == key) && direction != Direction.LEFT){
				
					queueofdirections.offer(Direction.RIGHT);
				}else if((KeyEvent.VK_S == key || KeyEvent.VK_DOWN == key) && direction != Direction.UP){
					
					queueofdirections.offer(Direction.DOWN);
				}else if((KeyEvent.VK_A == key || KeyEvent.VK_LEFT == key) && direction != Direction.RIGHT){
					
					queueofdirections.offer(Direction.LEFT);
				}
				System.out.println(queueofdirections);
			}
			
			
		}


		
	}

	public Direction getDirection() {
		return direction;
	}
	
	public void setGameRunningCondition(boolean running){
		this.running = running;
	}
	
	public boolean getGameRunningCondition(){
		return running;
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
}
