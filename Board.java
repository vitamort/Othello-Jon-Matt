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
		for(int a = 2; b<7&&c<7; a++)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				upright=true;
				break;
			}
			b=x+a;
			c=y+a;
		}
		b=x-1;
		c=y+1;
		for(int a = 2; b>=0&&c<7; a++)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				upleft=true;
				break;
			}
			b=x-a;
			c=y+a;
		}
		b=x+1;
		c=y-1;
		for(int a = 2; b<7&&c>=0; a++)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				downright=true;
				break;
			}
			b=x+a;
			c=y-a;
		}
		b=x-1;
		c=y-1;
		for(int a = 1; b>=0&&c>=0; a++)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				downleft=true;
				break;
			}
			b=x-a;
			c=y-a;
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
			for(int a = 1; b<7&&c<7; a++)
			{
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
				b=x+a;
				c=y+a;
			}
		}
		if(upleft)
		{
			b=x-1;
			c=y+1;
			for(int a = 1; b>=0&&c<7; a++)
			{
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
				b=x-a;
				c=y+a;
			}
		}
		if(downright)
		{
			b=x+1;
			c=y-1;
			for(int a = 1; b<7&&c>=0; a++)
			{
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
				b=x+a;
				c=y-a;
			}
		}
		if(downleft)
		{
			b=x-1;
			c=y-1;
			for(int a = 1; b>=0&&c>=0; a++)
			{
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
				b=x-a;
				c=y-a;
			}
		}
	}
	
	public static int utilityOf(Board x, char player)
	{
		/*
		 * Assuming that white is the player, and black is the opponent, here are the good and bad things:
		 * 
		 * GOOD
		 * 	# of white
		 * 	W corner
		 * 	W side
		 * 	W piece can capture Black
		 * 	W Wins
		 * 	# W moves - # B moves
		 * 
		 * BAD
		 * 	# of black
		 * 	B corner
		 * 	B side
		 * 	B can capture W
		 * 	B Wins
		 * 
		 * Neutral
		 * 	# of pieces on the board
		 */
		
		if(gameover(x))
        {}
        return 0;
		
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
		for(int a = 2; b<7&&c<7; a++)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				return true;
			}
			b=x+a;
			c=y+a;
		}
		b=x-1;
		c=y+1;
        if(x!=0&&y!=7)
        if(chart[b][c]!=BLANK&&chart[b][c]!=color)
		for(int a = 2; b>=0&&c<7; a++)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				return true;
			}
			b=x-a;
			c=y+a;
		}
        b=x+1;
        c=y-1;
        if(x!=7&&y!=0)
        if(chart[b][c]!=BLANK&&chart[b][c]!=color)
		for(int a = 2; b<7&&c>=0; a++)
		{
			
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				return true;
			}
            b=x+a;
            c=y-a;
		}
		b=x-1;
		c=y-1;
        if(x!=0&&y!=0)
        if(chart[b][c]!=BLANK&&chart[b][c]!=color)
		for(int a = 2; b>=0&&c>=0; a++)
		{
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				return true;
			}
			b=x-a;
			c=y-a;
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
	
	public String AlphaBeta(Board state, int depth, int depthLimit, int alpha, int beta, char player)
    {
        if(depth==depthLimit)
            return ""+(state.getCount(COMPUTER)-state.getCount(PLAYER));
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
                        Board board = new Board(PLAYER, COMPUTER, 8);
                        board =state;
                        board.input(x, y, COMPUTER);
                        int temp =Integer.parseInt((AlphaBeta(board, depth+1,  depthLimit, alpha, beta, PLAYER).split(" "))[0]);//may not compile, if it doesn't, move array out
                        if(temp>alpha)
                        {
                            alpha=temp;
                            xcoord=x;
                            ycoord=y;
                        }
                        
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
                        Board board = new Board(PLAYER, COMPUTER, 8);
                        board =state;
                        board.input(x, y, PLAYER);
                        int temp =Integer.parseInt((AlphaBeta(board, depth+1,  depthLimit, alpha, beta, COMPUTER).split(" "))[0]);//may not compile, if it doesn't, move array out
                        if(temp<beta)
                        {
                            beta=temp;
                            xcoord=x;
                            ycoord=y;
                        }
                    }
                }
            }
            return "" + beta + " " + xcoord + " " + ycoord;
        }
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

