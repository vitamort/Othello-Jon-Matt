/*
Matt DeMartino
Jonathan Schoeller
CSC 242 - Artificial Intelligence
Othello Project
*/

import java.util.Scanner;

public class Othello
{
	public static void main(String[] args)
	{
		//Constants
		final int BOARDSIZE = 8;
		final int PLAYER1 = 1;
		final int PLAYER2 = 2;
		final int FREE = 0;

		//Initialize Scanner and get input
		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();

		//Parse the input string
		String[] input = inputStr.explode(" ");
		boolean computerGoesFirst = (input[0].charAt(0).toLower() == 'b');
		int depthLimit = Integer.parseInt(input[1]);
		int timeLimit1 = Integer.parseInt(input[2]);
		int timeLimit2 = Integer.parseInt(input[3]);

		//Initialize the gameboard
		char[][] board = new char[BOARDSIZE][BOARDSIZE];
		initializeboard(board);

		//Loop through game here
		while(!gameover(board))
		{
			if(isValidMove(x, y, PLAYER1, board))
			{
				placepiece(x, y, PLAYER1, board);
			}

			if(isValidMove(x, y, PLAYER2, board))
			{
				placepiece(x, y, PLAYER2, board);
			}
		}

		announceGameWinner();

	}

	public static void initializeboard(char[][] board)
	{ //Sets the middle initial pieces on the board

	}

	public static boolean gameover(char[][] board)
	{ //Returns true if the game is over
	  //NOTE: We might need more arguments (whose turn is it? Has the other player passed?)

	}

	public static void announceGameWinner(char[][] board)
	{ //Figures out and prints who won the game (We probably won't need this after we get through the first parts)

	}

	public static boolean isValidMove(int x, int y, int player, char[][] board)
	{ //Returns whether a specific move is valid for a player

	}

	public static void placepiece(int x, int y, int player, char[][] board)
	{ //Places a piece for a specific player at the x,y coordinate given on the board.

	  //At some point, call a helper function "flippieces(x, y, player, board)" that flips over the pieces
	  //that are flipped by placing the player's piece.
	}
}