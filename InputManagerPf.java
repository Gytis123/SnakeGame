import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;



public class InputManagerPf {

	private LinkedList<Integer> pressedKeys = null;
	private Logic gamelogic;
	private KeyEvent event;
	
	public InputManagerPf(Logic gamelogic){
		pressedKeys = new LinkedList<Integer>();
		this.gamelogic = gamelogic;
		
	}
	
	public KeyboardInput getKeyboardInput(){
		return new KeyboardInput();
	}
	
	public void update(){
		pressedKeys.clear();
		
		
	}
	
	public boolean getKeyPressed(int keyCode){
		return pressedKeys.contains(keyCode);
	}
	
	public class KeyboardInput extends KeyAdapter{
		
		
		public void keyPressed(KeyEvent e){
			event = e;
			int key = e.getKeyCode();
			
			
			if (KeyEvent.VK_SPACE == key){
				if (gamelogic.getGameRunningCondition()){
					gamelogic.setGameRunningCondition(false);
				}else{
					gamelogic.setGameRunningCondition(true);
				}
			}
			
			if (gamelogic.getGameRunningCondition()){
				 Logic.Direction snakedirection = gamelogic.getDirection();	
				if (KeyEvent.VK_W == key && snakedirection != Logic.Direction.DOWN){
					pressedKeys.offer(key);
				}else if (KeyEvent.VK_D == key && snakedirection != Logic.Direction.LEFT){
					pressedKeys.offer(key);
				}else if(KeyEvent.VK_S == key && snakedirection != Logic.Direction.UP){
					pressedKeys.offer(key);
				}else if(KeyEvent.VK_A == key && snakedirection != Logic.Direction.RIGHT){
					pressedKeys.offer(key);
				}
				
				
			}
			
			
		}


		
	}
}
