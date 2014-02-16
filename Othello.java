/*
Matt DeMartino
Jonathan Schoeller
CSC 242 - Artificial Intelligence
Othello Project
*/

import java.util.Scanner;

public class Othello
{
	char[][] board;
	char PLAYER;
	char COMPUTER;

	public static void main(String[] args)
	{
		//Constants
		final int BOARDSIZE = 8;
		final int FREE = 'f';

		//Initialize Scanner and get input
		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();

		//Parse the input string
		String[] input = inputStr.explode(" ");
		boolean computerGoesFirst = (input[1].charAt(0).toLower() == 'b');
		int depthLimit = Integer.parseInt(input[2]);
		int timeLimit1 = Integer.parseInt(input[3]);
		int timeLimit2 = Integer.parseInt(input[4]);

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

		if(computerGoesFirst)
		{
			int[] coords = computerMove();
			placepiece(coords[0], coords[1], COMPUTER);
		}

		//Loop through game here
		while(!gameover(board))
		{
			//Player moves
			if(isValidMove(x, y, PLAYER))
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

		//Set x and y to the coordinates of the computer's next move

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