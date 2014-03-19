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
    static int depthLimit=-1;
    static int timeLimit1;
    static int timeLimit2;
    
	public static void main(String[] args)
	{
		//Initialize Scanner and get input
		Scanner scan = new Scanner(System.in);
		String inputStr = scan.nextLine();
        
		//Parse the input string
		String[] input = inputStr.split(" ");
		boolean computerGoesFirst = (input[1].toLowerCase().charAt(0) == 'b');
		depthLimit = Integer.parseInt(input[2]);
		timeLimit1 = Integer.parseInt(input[3]);
		timeLimit2 = Integer.parseInt(input[4]);
        
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
		//board.print();
        
		//Let the computer go first if it's supposed to
		if(computerGoesFirst)
		{
			int[] coords = computerMove();
			board.input(coords[0], coords[1], COMPUTER);
            System.out.println(coords[0] + " " + coords[1]);
			//board.print();
		}
        
		int x=0, y=0;
		boolean playerPass=false;
		boolean computerPass=false;
		boolean legalMove = true;
        
		//Loop through game here
		while(!Board.gameover(board))
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
					//System.out.println("That move is not legal. Choose another move.");
				}
			}while(!legalMove);
			//board.print();
            
			//Computer Move
			int[] coords = computerMove();
			computerPass = (coords[0] == -1);
			if(!computerPass)
			{
				board.input(coords[0], coords[1], COMPUTER);
                System.out.println(coords[0] + " " + coords[1]);
			}else{
				System.out.println("pass");
			}
			//board.print();
		}
        
		announceGameWinner();
	}
    
	public static int[] computerMove()
	{
		int[] coords = new int[2];
        
		//Set x and y to the coordinates of the computer's next move here
        //NOTE: How do we consider if the computer wants to pass?
        if(Board.moveCount(board, COMPUTER)==0)
        {
            coords[0] = -1; coords[1]=0;
            return coords;
        }
        
        String move = "";
        //while(true)
        //{
        //  String temp = board.AlphaBeta(board, 0, depthLimit, timeSinceStart, timeLimit1, Integer.MIN_VALUE, Integer.MAX_VALUE, COMPUTER);
        //  if(temp.equals("time")
        //      break;
        //  move=temp;
        //}
        String[] strings = move.split(" ");
        coords[0]=Integer.parseInt(strings[1]);
        coords[1]=Integer.parseInt(strings[2]);
        return coords;
	}
    
	
    
	public static void announceGameWinner()
	{ //Figures out and prints who won the game (We probably won't need this after we get through the first parts)
        if(board.getCount(PLAYER)>board.getCount(COMPUTER))
            System.out.println("Player wins");
        else if(board.getCount(PLAYER)>board.getCount(COMPUTER))
            System.out.println("Computer wins");
        else
            System.out.println("It is a tie");
        
	}
    
}