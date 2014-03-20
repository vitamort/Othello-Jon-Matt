/*
 Matt DeMartino
 Jonathan Schoeller
 CSC 242 - Artificial Intelligence
 Othello Project
 */

public class Board
{
	char[][] chart;
	final char BLANK = '+';
	final char PLAYER;
	final char COMPUTER;
	final int BOARDSIZE;
    
	public Board(char playerColor, char computerColor, int size)
	{
		this.PLAYER = playerColor;
		this.COMPUTER = computerColor;
		this.BOARDSIZE = size;
        
		chart = new char[size][size];
        
		for(int a = 0; a<size; a++)
		{
			for(int b = 0; b<size; b++)
			{
				chart[a][b]=BLANK;
			}
		}
        
		//Initial positions
		chart[3][3]='W';
		chart[4][4]='W';
		chart[3][4]='B';
		chart[4][3]='B';
	}
    public Board(Board b)
    {
        this.PLAYER=b.PLAYER;
        this.COMPUTER=b.COMPUTER;
        this.BOARDSIZE=b.BOARDSIZE;
        chart=new char[BOARDSIZE][BOARDSIZE];
        for(int x = 0; x<BOARDSIZE; x++)
		{
			for(int y = 0; y<BOARDSIZE; y++)
			{
				chart[x][y]=b.chart[x][y];
			}
		}
    }
    
	//Makes a move at the (x,y) position given for a given player.
	public void input(int x, int y, char color)
	{
		chart[x][y]=color;
		flip(x, y, color);
	}
	private void flip(int x, int y, char color)
	{
		//First check what directions can be flipped
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		boolean upright = false;
		boolean upleft = false;
		boolean downright = false;
		boolean downleft = false;
		//variables saying weather or not a given direction should
		//have pieces flipped
		for(int a = x-1; a>=0; a--)
		{
			if(chart[a][y]==BLANK)
				break;
			if(chart[a][y]==color)
			{
				left = true;
				break;
			}
		}
		for(int a = x+1; a<8; a++)
		{
			if(chart[a][y]==BLANK)
				break;
			if(chart[a][y]==color)
			{
				right = true;
				break;
			}
		}
		for(int a = y-1; a>=0; a--)
		{
			if(chart[x][a]==BLANK)
				break;
			if(chart[x][a]==color)
			{
				down = true;
				break;
			}
		}
		for(int a = y+1; a<8; a++)
		{
			if(chart[x][a]==BLANK)
				break;
			if(chart[x][a]==color)
			{
				up = true;
				break;
			}
		}
		int b = x+1;
		int c = y+1;
		while(b<8&&c<8)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				upright=true;
				break;
			}
			b++;
			c++;
		}
		b=x-1;
		c=y+1;
		while(b>=0&&c<8)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				upleft=true;
				break;
			}
			b--;
			c++;
		}
		b=x+1;
		c=y-1;
		while(b<8&&c>=0)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				downright=true;
				break;
			}
			b++;
			c--;
		}
		b=x-1;
		c=y-1;
		while(b>=0&&c>=0)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				downleft=true;
				break;
			}
			b--;
			c--;
		}
        
        
		//Flip each direction that we've found can be flipped.
		if(left)
			for(int a = x-1; a>=0; a--)
			{
				if(chart[a][y]==color)
				{
					break;
				}
                chart[a][y] = color;
			}
		if(right)
			for(int a = x+1; a<8; a++)
			{
				if(chart[a][y]==color)
				{
					break;
				}
                chart[a][y] = color;
			}
		if(down)
			for(int a = y-1; a>=0; a--)
			{
				if(chart[x][a]==color)
				{
					break;
				}
                chart[x][a]=color;
			}
		if(up)
			for(int a = y+1; a<8; a++)
			{
				if(chart[x][a]==color)
				{
					break;
				}
                chart[x][a]=color;
			}
		if(upright)
		{
			b = x+1;
			c = y+1;
			while(b<8&&c<8)
			{
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
				b++;
				c++;
			}
		}
		if(upleft)
		{
			b=x-1;
			c=y+1;
			while(b>=0&&c<8)
			{
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
				b--;
				c++;
			}
		}
		if(downright)
		{
			b=x+1;
			c=y-1;
			while(b<8&&c>=0)
			{
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
				b++;
				c--;
			}
		}
		if(downleft)
		{
			b=x-1;
			c=y-1;
			while(b>=0&&c>=0)
			{
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
				b--;
				c--;
			}
		}
	}
	
	public static int utilityOf(Board x)
	{
        int value = 0;
        int computer =0;
        int player =0;
        computer+=10 * Board.onWallsAndCorners(x, x.COMPUTER);
        player+=10 * Board.onWallsAndCorners(x, x.PLAYER);
        computer+=3 * moveCount(x, x.COMPUTER);
        player+=3 * moveCount(x, x.PLAYER);
        int numCompPieces = x.getCount(x.COMPUTER);
        int numPlayerPieces= x.getCount(x.PLAYER);
        int numTotalPieces = numCompPieces + numPlayerPieces;
        
        //It becomes more important to have more pieces as we get later into the game.
        int turnWeight = ((int) (Math.log(numTotalPieces)/Math.log(2)) ) - 1;
        
        player += turnWeight*numPlayerPieces;
        computer += turnWeight*numCompPieces;
        
        value = computer-player;
		/*
		 * Assuming that white is the player, and black is the opponent, here are the good and bad things:
		 *
		 * GOOD
		 * 	W piece can capture Black-i feel this is the same as
         *                           subtracting the # of black moves
         *                           from the white moves
		 * 	W Wins-feel this is a given, but does not need to be stressed
		 *
		 * BAD
		 * 	B can capture W
		 * 	B Wins-feel this is a given, but does not need to be stressed
		 *
		 */
        return value;
		
	}
    private static int onWallsAndCorners(Board board, char color)
    {
        int count = 0;
        for(int x = 0; x<8; x++)
        {
            if(board.chart[x][0]==color)
                count++;
            if(board.chart[x][7]==color)
                count++;
        }
        for(int y = 0; y<8; y++)
        {
            if(board.chart[0][y]==color)
                count++;
            if(board.chart[7][y]==color)
                count++;
        }
        if(board.chart[0][0]==color)
            count++;
        if(board.chart[0][7]==color)
            count++;
        if(board.chart[7][0]==color)
            count++;
        if(board.chart[7][7]==color)
            count++;
        return count;
    }
    public static int moveCount(Board board, char color)
    {
        int count = 0;
        for(int i = 0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				//System.out.println("Checking (i,j) = "+"("+i+","+j+")");
				if(board.isLegalMove(i,j,board.COMPUTER))
				{
					count++;
				}
			}
		}
        return count;
    }
    
	public char not(char color)
	{//Returns the "other" color.
		if(color == PLAYER) return COMPUTER;
		else return PLAYER;
	}
    
	public boolean isLegalMove(int x, int y, char color)
	{
		if(chart[x][y] != BLANK) return false;
        if(x!=0)
            if(chart[x-1][y]!=BLANK&&chart[x-1][y]!=color)
                for(int a = x-1; a>=0; a--)
                {
                    if(chart[a][y]==BLANK)
                        break;
                    if(chart[a][y]==color)
                    {
                        return true;
                    }
                }
        if(x!=7)
            if(chart[x+1][y]!=BLANK&&chart[x+1][y]!=color)
                for(int a = x+1; a<8; a++)
                {
                    if(chart[a][y]==BLANK)
                        break;
                    if(chart[a][y]==color)
                    {
                        return true;
                    }
                }
        if(y!=0)
            if(chart[x][y-1]!=BLANK&&chart[x][y-1]!=color)
                for(int a = y-1; a>=0; a--)
                {
                    if(chart[x][a]==BLANK)
                        break;
                    if(chart[x][a]==color)
                    {
                        return true;
                    }
                }
        if(y!=7)
            if(chart[x][y+1]!=BLANK&&chart[x][y+1]!=color)
                for(int a = y+1; a<8; a++)
                {
                    if(chart[x][a]==BLANK)
                        break;
                    if(chart[x][a]==color)
                    {
                        return true;
                    }
                }
		int b = x+1;
		int c = y+1;
        if(x!=7&&y!=7)
            if(chart[b][c]!=BLANK&&chart[b][c]!=color)
                while(b<8&&c<8)
                {
                    if(chart[b][c]==BLANK)
                        break;
                    if(chart[b][c]==color)
                    {
                        return true;
                    }
                    b++;
                    c++;
                }
		b=x-1;
		c=y+1;
        if(x!=0&&y!=7)
            if(chart[b][c]!=BLANK&&chart[b][c]!=color)
                while(b>=0&&c<8)
                {
                    if(chart[b][c]==BLANK)
                        break;
                    if(chart[b][c]==color)
                    {
                        return true;
                    }
                    b--;
                    c++;
                }
        b=x+1;
        c=y-1;
        if(x!=7&&y!=0)
            if(chart[b][c]!=BLANK&&chart[b][c]!=color)
                while(b<8&&c>=0)
                {
                    
                    if(chart[b][c]==BLANK)
                        break;
                    if(chart[b][c]==color)
                    {
                        return true;
                    }
                    b++;
                    c--;
                }
		b=x-1;
		c=y-1;
        if(x!=0&&y!=0)
            if(chart[b][c]!=BLANK&&chart[b][c]!=color)
                while(b>=0&&c>=0)
                {
                    if(chart[b][c]==BLANK)
                        break;
                    if(chart[b][c]==color)
                    {
                        return true;
                    }
                    b--;
                    c--;
                }
		return false;
	}
    
	//Returns the number of pieces on the board of a given color.
	public int getCount(char color)
	{
		int count = 0;
		for(int x = 0; x<8; x++)
		{
			for(int y = 0; y<8; y++)
			{
				if(chart[x][y]==color)
					count++;
			}
		}
		return count;
	}
	
	public static boolean gameover(Board b)
	{ //Returns true if the game is over
        if(b.getCount(b.PLAYER)==0)
            return true;
        if(b.getCount(b.COMPUTER)==0)
            return true;
        for(int x = 0; x<8; x++)
        {
            for(int y = 0; y<8; y++)
            {
                if(b.isLegalMove(x, y, b.PLAYER)||b.isLegalMove(x, y, b.COMPUTER))
                    return false;
            }
        }
        
		return true;
	}
	
	public String AlphaBeta(Board state, int depth, int depthLimit, long startTime, long timeSinceStart, long timeLimit, int alpha, int beta, char player)
    {

    	//Testing stuff:

    	//System.out.println(timeLimit);
    	System.out.println(timeSinceStart);
    	if(timeLimit - timeSinceStart < 10)
    	{
    		System.out.println("Time left is " + (timeLimit - timeSinceStart) );
    		System.out.println("TIME UP");
    		return "time";
    	}
    	//End Testing Stuff

        if(depth==depthLimit||(moveCount(state, player)+moveCount(state, state.not(player)))==0)
            return ""+Board.utilityOf(state);
        //if((timeLimit-timeSinceStart)<(10*depth/1000))
        //    return "time";
        int xcoord=0;
        int ycoord=0;
        if(player==COMPUTER)
        {
            for(int x = 0; x<8; x++)
            {
                for(int y = 0; y<8; y++)
                {
                    if(state.isLegalMove(x, y, COMPUTER))
                    {
                        Board board = new Board(state);
                        board.input(x, y, COMPUTER);
                        String next = (AlphaBeta(board, depth+1,  depthLimit, startTime, timeElapsed(startTime), timeLimit, alpha, beta, PLAYER).split(" "))[0];
                        if(next.equals("time"))
                            return next;
                        int temp =Integer.parseInt(next);
                        if(temp>alpha)
                        {
                            alpha=temp;
                            xcoord=x;
                            ycoord=y;
                        }
                        if(alpha>=beta)
                            return "" + alpha + " " + xcoord + " " + ycoord;
                    }
                }
            }
            return "" + alpha + " " + xcoord + " " + ycoord;
        }
        else
        {
            for(int x = 0; x<8; x++)
            {
                for(int y = 0; y<8; y++)
                {
                    if(state.isLegalMove(x, y, PLAYER))
                    {
                        Board board = new Board(state);
                        board.input(x, y, PLAYER);
                        String next = (AlphaBeta(board, depth+1,  depthLimit, startTime, timeElapsed(startTime), timeLimit, alpha, beta, PLAYER).split(" "))[0];
                        if(next.equals("time"))
                            return next;
                        int temp =Integer.parseInt(next);
                        if(temp<beta)
                        {
                            beta=temp;
                            xcoord=x;
                            ycoord=y;
                        }
                        if(alpha>=beta)
                            return "" + beta + " " + xcoord + " " + ycoord;
                    }
                }
            }
            return "" + beta + " " + xcoord + " " + ycoord;
        }
    }

    public static long timeElapsed(long then)
    {
    	return System.currentTimeMillis() - then;
    	
    }
    
	public void print()
	{
		System.out.println(" | 01234567");
		//System.out.println("-----------");
		for(int y = 0; y<8; y++)
		{
			System.out.print(y + "| ");
			for(int x = 0; x<8; x++)
			{
				System.out.print(chart[x][y]);
			}
			System.out.println();
		}
		System.out.println("-----------");
	}
    
}

