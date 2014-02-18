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
		chart[3][3]=PLAYER;
		chart[4][4]=PLAYER;
		chart[3][4]=COMPUTER;
		chart[4][3]=COMPUTER;
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

