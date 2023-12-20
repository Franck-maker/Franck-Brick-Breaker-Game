package BB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;
import javax.swing.Timer;
// in order to be a panel, this class will extend JPanel, implement directions of the keyboard
// and also the movement of the ball that's why i'll add keyListener and ActionListener

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	
	private boolean play = false;
	private int score = 0; 
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	
	// for the program to know how to move the ball and which is the next location of the ball
	//once the pedal hits or interacts with wall
	
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	// to display our map
	
	private MapGenerator map;
	
	// value for the Gameplay initialization
	
	public GamePlay() {
		map = new MapGenerator(3,7); // for generating map
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	//Creating Graphics method where we'll display ball, pedal and controls all the things that are displayed in our game
	
	public void paint(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillRect(1, 1, 692, 592);
		
		// displaying map generator
		
		map.draw((Graphics2D)g);
		
		// borders of the screen
		
		g.setColor(Color.yellow); 
		g.fillRect(0, 0, 3, 592); // bottom border
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592); // top border
		
		// attributes of the pedal
		
		g.setColor(Color.white);
		g.fillRect(playerX, 550, 100, 8); //playerX will allows us to connect the value of the pedal to the arrows of the keyboard, and then change it so that we can be able to move the pedal on the screen
		
		// adding ball
		
		g.setColor(Color.green);
		g.fillOval(ballposX, ballposY, 20, 20); // fillOval because we want the ball to be a circle, ballposX because we need the ball to move to teh right and to the left
		
		// to see the score on the game 
		
		g.setColor(Color.black);
		g.setFont(new Font("serif", Font.BOLD, 25));
		
		// updating score
		
		g.drawString("" + score,  590, 30);
		
		// when winning
		
		if (totalBricks <=0) {
			
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won, Score: " +score, 190,300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to restart ;" + score,  230, 350);
		}
		
		// displaying the gameover message if the ball didn't bounce from the pedal
		
		if(ballposY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Score:" + score,  190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to restart." + score,  230, 350);
		}
		g.dispose(); // to make sure that once this function(paint) is executed, the system will release the resources used for it
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		
		if(play) { // i create a functional rectangle which has the X and Y position of the ball and this will represent the functional ball, as an object of interaction 
			// and the rectangular interacts with the pedal so we use interact
			if(new Rectangle(ballposX, ballposY, 20,30).intersects(new Rectangle(playerX,550,100,8))) {
				ballYdir = -ballYdir; // the ball goes back to his direction when intersecting another rectangle
			}
			
			// interaction bricks-ball
			//map.map == object map. attribute map
			for(int i=0; i< map.map.length; i++) {
				for(int j =0; j<map.map[0].length; j++) { // here i'm accessing the brick width and height from the map generator class assigned to the object map
					if(map.map[i][j] > 0) {
						int brickX = j*map.brickWidth +80;
						int brickY = i*map.brickHeight +50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						
						// controling values of bricks and ball
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						// make the objects interacts between them since we have them
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0,i,j);
							totalBricks--;
							score +=5;
							
							// bounce bricks with the  ball
							if(ballposX +19 <= brickRect.x || ballposX+1 >= brickRect.x +brickRect.width) {
								ballXdir = -ballXdir;
							}else {
								ballYdir = - ballYdir; 
							}
						}
					}
				}
			}
			
			// allowing ball to move 
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) {
				ballXdir = - ballXdir; // change the X direction of the ball when hitting the wall
				
			}
			if(ballposY < 0) {
				ballYdir = - ballYdir;
				
		    }
			if(ballposX > 670) {
				ballXdir = - ballXdir; // 
			}
		}
		repaint(); // after a certain amount of time which is defined by a timer, will automatically update our window 
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600 ) {
				playerX = 600; // to make sure our pedal doesn't go further than the border of our map and go to the right when pressing the right arrow of the keyboard
			} else {
				moveRight();
			}
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10 ) {
				playerX = 10; // to make sure our pedal don't crossed 600 and go to the left when pressing the left arrow on the keyboard
			} else {
				moveLeft();
			}
		}
		
		if(arg0.getKeyCode()== KeyEvent.VK_ENTER) { // to restart the game when pressing the enter button
		if(!play) {
			play = true;
			ballposX = 120;
			ballposY= 350;
			ballXdir = -1;
			ballYdir = -2;
			score =0;
			totalBricks = 21;
			map = new MapGenerator(3,7);
			repaint();
		}
		}
	}
	
	public void moveRight() {
		play = true;
		playerX += 20;
		
	}
	
	public void moveLeft() {
		play = true;
		playerX -= 20;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
