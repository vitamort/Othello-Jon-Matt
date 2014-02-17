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
	final static int BOARDSIZE = 8;
	final static int BLANK = '0';
	static char PLAYER;
	static char COMPUTER;
	static Board board;

	public static void main(String[] args)
	{
		//Initialize Scanner and get input
		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();

		//Parse the input string
		String[] input = inputStr.split(" ");
		boolean computerGoesFirst = (input[1].toLowerCase().charAt(0) == 'b');
		int depthLimit = Integer.parseInt(input[2]);
		int timeLimit1 = Integer.parseInt(input[3]);
		int timeLimit2 = Integer.parseInt(input[4]);

		//Set the pieces for the computer and player
		if(computerGoesFirst)
		{
			COMPUTER = 'B';
			PLAYER = 'W';
		} else {
			COMPUTER = 'W';
			PLAYER = 'B';
		}

		//Initialize the gameboard
		board = new Board(PLAYER, COMPUTER, BOARDSIZE);
		board.print();

		//Let the computer go first if it's supposed to
		if(computerGoesFirst)
		{
			int[] coords = computerMove();
			board.input(coords[0], coords[1], COMPUTER);
			board.print();
		}

		int x=0, y=0;
		boolean playerPass=false;
		boolean computerPass=false;
		boolean legalMove = true;

		//Loop through game here
		while(!gameover(playerPass, computerPass))
		{
			playerPass = false;
			computerPass = false;
			//Player moves
			do{
				legalMove = true;
				String pMoveInput = scan.nextLine();
				playerPass = pMoveInput.equalsIgnoreCase("pass");
				if(!playerPass){
					String[] move = pMoveInput.split(" ");
					x = Integer.parseInt(move[0]);
					y = Integer.parseInt(move[1]);
				}else{
					break;
				}

				if(board.isLegalMove(x,y,PLAYER)){
					legalMove = true;
					board.input(x,y,PLAYER);
				}else{
					legalMove = false;
					System.out.println("That move is not legal. Choose another move.");
				}
			}while(!legalMove);
			board.print();

			//Computer Move
			int[] coords = computerMove();
			computerPass = (coords[0] == -1);
			if(!computerPass)
			{
				board.input(coords[0], coords[1], COMPUTER);
			}else{
				System.out.println("pass");
			}
			board.print();
		}

		announceGameWinner();
	}

	public static int[] computerMove()
	{
		int x=3;
		int y=5;
		int[] coords = new int[2];

		//Set x and y to the coordinates of the computer's next move here
		    //NOTE: How do we consider if the computer wants to pass?

		for(int i = 0; i<BOARDSIZE; i++)
		{
			for(int j=0; j<BOARDSIZE; j++)
			{
				System.out.println("Checking (i,j) = "+"("+i+","+j+")");
				if(board.isLegalMove(i,j,COMPUTER))
				{
					coords[0] = i;
					coords[1] = j;
					return coords;
				}
			}
		}

		coords[0] = -1; coords[1]=0;
		return coords;
	}

	public static boolean gameover(boolean pPass, boolean cPass)
	{ //Returns true if the game is over
	  //NOTE: We might need more arguments (whose turn is it? Has the other player passed?)

		return false;
	}

	public static void announceGameWinner()
	{ //Figures out and prints who won the game (We probably won't need this after we get through the first parts)

	}
}