package GroupAssignment;

import java.util.Random;
import java.util.Scanner;

public class Game {

	private int diceNum;
	private static String rollTurn;
	private River river;
	private Boat boat1;
	private Boat boat2;
	private boolean hasWinner;
	private static int gameMode;
	protected static String player1;
	protected static String player2;
	private static int player1Score;
	private static int player2Score;
	private boolean gameOver; // variable to keep playing the game
	
	public Game() { //creates a game object
		
		gameOver = false;
		hasWinner = false;
		player1Score = 0;
		player2Score = 0;

		setPlayerName();
		setGameMode();
		boat1 = new Boat("p1",1, player1);
		boat2 = new Boat("p2",1, player2);
		river = new River(boat1, boat2);
		Start();
		
		
	}	
	
	public void rollDice() { //rolls dice for player to move
		
		Random r = new Random();
		diceNum = 1 + r.nextInt(6);
	}
	
	public int getDiceNum() { //returns the dice number
		
		return diceNum;
	}
	
	
	public void Start() { //displays the starting locations of the players
		
		river.display();
	}
	
	public void runGame() {	// runs the game until there is a winner

		while(!checkGameStatus()) {
		
			if (Boat.getPlayerTurn()==1) {
				
				rollTurn = player1;
			}
			else {
				rollTurn= player2;
			}
		
		System.out.printf("%s Press Enter to roll the dice:", rollTurn);
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.nextLine();
		if(input == "") {
		rollDice();
		System.out.printf("\n%s rolled %d on the dice\n", rollTurn, getDiceNum());
		moveBoat(getDiceNum() + river.checkCurrentHit() - river.checkTrapHit());
		river.display();
		checkWinner();
		
	}
		else;
		}
		showScores();
	}
	
	
	public static void setGameMode() { //sets the mode of the game
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Choose Game Mode (1 for easy, 2 for hard):");
		int gm = keyboard.nextInt();
		
		if(gm == 1 || gm == 2) {
			
		gameMode = gm;
		
	}
		
	}
	
	public static int getGameMode() { //returns the game mode
		
		return gameMode;
	}
	
	public static void setPlayerName() { // sets the name of the players
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Set Player 1 (p1) Name:");
		player1 = keyboard.next().toUpperCase();
		System.out.print("Set Player 2 (p2) Name:");
		player2 = keyboard.next().toUpperCase();
		
	}
	
	public static String getRollTurn() { // gets the name of the player who's current turn it is
		
		return rollTurn;
	}
	
	public  boolean checkGameStatus() { //checks if the game over and returns true or false
		
		if(hasWinner) {
			
		gameOver = true;
		
		}
		else {
			gameOver = false;
		}
			
		return gameOver;
	}
	
	
	
	public int moveBoat(int steps) { // moves the boats based on the player turn
		
		int s = 0;
		if(Boat.getPlayerTurn() == 1) {
			
			rollTurn = player1;
			s += steps;
			boat1.setLocation( boat1.getLocation() + steps);
		}
		
		else {
			
			rollTurn = player2;
			s += steps;
			boat2.setLocation(boat2 .getLocation()+steps);
		}
		
		Boat.addTurn();
		return s;
	}
	
	
	public void updateScores(int score) {
		
		
		if (Boat.getPlayerTurn() == 1){
		
		player1Score += score;
		}
		
		else {
			player2Score +=score;
	}
		

		
	}
	
	public void showScores() {
		
		System.out.printf("\n%s score is: %d, %s score is: %d", player1, player1Score,player2,player2Score);
	}
	
	/*public boolean restart() {
		
		Scanner keyboard = new Scanner(System.in);
		String choice;
		
		System.out.print("\nWould you like to play again (yes/no):");
		choice = keyboard.next();
		
		if (choice.equals("yes")) {
			
			gameOver = false;
			hasWinner = false;
			return true;
		}
		
		else return false;
			
	}*/

	public boolean checkWinner() { // checks if any boat has reached the river end and returns true or false
		
		if (boat1.getLocation() >=100 || boat2.getLocation() >=100) {
			
		if (boat1.getLocation() >=100) {
				
				boat1.setLocation(100);
				System.out.printf("%s IS THE WINNER", boat1.getName());
			}
			else {
				boat2.setLocation(100);
				System.out.printf("%s IS THE WINNER", boat2.getName());
			} 
			
			gameOver = true;
			return hasWinner = true;
		}
		else {
			return hasWinner = false;
		}
	}
	
}
