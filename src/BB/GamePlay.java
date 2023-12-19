package BB;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JPanel;
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
	
	// value for the Gameplay initialization
	
	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	//Creating Graphics method where we'll display ball, pedal and controls all the things that are displayed in our game
	
	public void print(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillRect(1, 1, 692, 592);
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 562);
		g.fillRect(0, 0, , height);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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
