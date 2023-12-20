package BB;

import javax.swing.JFrame;

// Here the game window is created, i used JFrame to create the 
//windows bounds and its size
public class Main {

	public static void main(String[] args) {
		
		JFrame obj = new JFrame(); // here we create a new object from the cklass JFrame
		GamePlay gamePlay = new GamePlay(); // creating the Gameplay object
		obj.setBounds(10,10,700,600); // the boundaries of the Game
		obj.setTitle("Franck Brick breaker");
		obj.setResizable(false); // to make sure the windows is resizable
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// adding gamePlay
		obj.add(gamePlay);
	}

}
