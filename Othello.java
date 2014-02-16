/*
Matt DeMartino
Jonathan Schoeller
CSC 242 - Artificial Intelligence
Othello Project
*/

import java.util.Scanner;

public class Othello
{
	//Constants
	final int BOARDSIZE = 8;
	final int FREE = 'f';
	char PLAYER;
	char COMPUTER;
	char[][] board;

	public static void main(String[] args)
	{
		//Initialize Scanner and get input
		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();

		//Parse the input string
		String[] input = inputStr.split(" ");
		boolean computerGoesFirst = (input[1].charAt(0).toLower() == 'b');
		int depthLimit = Integer.parseInt(input[2]);
		int timeLimit1 = Integer.parseInt(input[3]);
		int timeLimit2 = Integer.parseInt(input[4]);

		//Set the pieces for the computer and player
		if(computerGoesFirst)
		{
			COMPUTER = 'b';
			PLAYER = 'w';
		} else {
			COMPUTER = 'w';
			PLAYER = 'b';
		}

		//Initialize the gameboard
		board = new char[BOARDSIZE][BOARDSIZE];
		initializeboard();

		//Let the computer go first if it's supposed to
		if(computerGoesFirst)
		{
			int[] coords = computerMove();
			placepiece(coords[0], coords[1], COMPUTER);
		}

		int x, y;

		//Loop through oop through game here
		while(!gameover())
		{
			//Player moves
			String pMoveInput = scan.nextLine();
			String[] move = pMoveInput.split(" ");
			x = Integer.parseInt(move[0]);
			y = Integer.parseInt(move[1]);

			if(!pMoveInput.equalsIgnoreCase("pass") && isValidMove(x, y, PLAYER))
			{
				placepiece(x, y, PLAYER);
			}

			//Computer Move
			int[] coords = computerMove();
			placepiece(coords[0], coords[1], COMPUTER);
		}

		announceGameWinner();
	}

	public static int[] computerMove()
	{
		int x;
		int y;
		int[] coords = new int[2];

		//Set x and y to the coordinates of the computer's next move here
		    //NOTE: How do we consider if the computer wants to pass?

		coords[0] = x; coords[1] = y;
		return coords;
	}

	public static void initializeboard()
	{ //Sets the middle initial pieces on the board

	}

	public static boolean gameover()
	{ //Returns true if the game is over
	  //NOTE: We might need more arguments (whose turn is it? Has the other player passed?)

	}

	public static void announceGameWinner()
	{ //Figures out and prints who won the game (We probably won't need this after we get through the first parts)

	}

	public static boolean isValidMove(int x, int y, char player)
	{ //Returns whether a specific move is valid for a player

	}

	public static void placepiece(int x, int y, char player)
	{ //Places a piece for a specific player at the x,y coordinate given on the board.

	  //At some point, call a helper function "flippieces(x, y, player, board)" that flips over the pieces
	  //that are flipped by placing the player's piece.
	}
}