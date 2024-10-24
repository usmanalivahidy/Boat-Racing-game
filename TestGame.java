package GroupAssignment;

import java.util.Scanner;

public class TestGame {

	public static void main(String[] args) {
		
		System.out.println("\n\n\n_______WELCOME TO JAVA BOAT RACING GAME________\n\n\n");
		Game g = new Game();
		
		while(!g.checkGameStatus()) {
			
		g.runGame();

		}
		
		System.out.println("\n\n\n\n\n_____Thanks for playing_____");
		

	}

}
