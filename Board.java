package othello;

public class Board 
{
	char[][] chart;
	final char BLANK = ' ';
	public Board()
	{
		chart = new char[8][8];
		for(int a = 0; a<8; a++)
		{
			for(int b = 0; b<8; b++)
			{
				chart[a][b]=BLANK;
			}
		}
	}
	public void input(int x, int y, char color)
	{
		chart[x][y]=color;
		flip(x, y, color);
	}
	private void flip(int x, int y, char color) 
	{
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
		int b = x;
		int c = y;
		for(int a = 1; b<7&&c<7; a++)
		{
			b=x+a;
			c=y+a;
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				upright=true;
				break;
			}
		}
		b=x;
		c=y;
		for(int a = 1; b>=0&&c<7; a++)
		{
			b=x-a;
			c=y+a;
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				upleft=true;
				break;
			}
		}
		b=x;
		c=y;
		for(int a = 1; b<7&&c>=0; a++)
		{
			b=x+a;
			c=y-a;
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				downright=true;
				break;
			}
		}
		b=x;
		c=y;
		for(int a = 1; b>=0&&c>=0; a++)
		{
			b=x-a;
			c=y-a;
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				downleft=true;
				break;
			}
		}
		//checks each direction
		if(left)
			for(int a = x; a>=0; a--)
			{
				if(chart[a][y]==color)
				{
					break;
				}
                chart[a][y] = color;
			}
		if(right)
			for(int a = x; a<8; a++)
			{
				if(chart[a][y]==color)
				{
					break;
				}
                chart[a][y] = color;
			}
		if(down)
			for(int a = y; a>=0; a--)
			{
				if(chart[x][a]==color)
				{
					break;
				}
                chart[x][a]=color;
			}
		if(up)
			for(int a = y; a<8; a++)
			{
				if(chart[x][a]==color)
				{
					break;
				}
                chart[x][a]=color;
			}
		if(upright)
		{
			b = x;
			c = y;
			for(int a = 1; b<7&&c<7; a++)
			{
				b=x+a;
				c=y+a;
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
			}
		}
		if(upleft)
		{
			b=x;
			c=y;
			for(int a = 1; b>=0&&c<7; a++)
			{
				b=x-a;
				c=y+a;
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
			}
		}
		if(downright)
		{
			b=x;
			c=y;
			for(int a = 1; b<7&&c>=0; a++)
			{
				b=x+a;
				c=y-a;
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
			}
		}
		if(downleft)
		{
			b=x;
			c=y;
			for(int a = 1; b>=0&&c>=0; a++)
			{
				b=x-a;
				c=y-a;
				if(chart[b][c]==color)
				{
					break;
				}
                chart[b][c]=color;
			}
		}
	}
	public void print()
	{
		for(int y = 0; y<8; y++)
		{
			for(int x = 0; x<8; x++)
			{
				System.out.print(chart[x][y]);
			}
			System.out.println();
		}
	}
	public boolean isLegalMove(int x, int y, char color)
	{
		for(int a = x-1; a>=0; a--)
		{
			if(chart[a][y]==BLANK)
				break;
			if(chart[a][y]==color)
			{
				return true;
			}
		}
		for(int a = x+1; a<8; a++)
		{
			if(chart[a][y]==BLANK)
				break;
			if(chart[a][y]==color)
			{
				return true;
			}
		}
		for(int a = y-1; a>=0; a--)
		{
			if(chart[x][a]==BLANK)
				break;
			if(chart[x][a]==color)
			{
				return true;
			}
		}
		for(int a = y+1; a<8; a++)
		{
			if(chart[x][a]==BLANK)
				break;
			if(chart[x][a]==color)
			{
				return true;
			}
		}
		int b = x;
		int c = y;
		for(int a = 1; b<7&&c<7; a++)
		{
			b=x+a;
			c=y+a;
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				return true;
			}
		}
		b=x;
		c=y;
		for(int a = 1; b>=0&&c<7; a++)
		{
			b=x-a;
			c=y+a;
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				return true;
			}
		}
		b=x;
		c=y;
		for(int a = 1; b<7&&c>=0; a++)
		{
			b=x+a;
			c=y-a;
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				return true;
			}
		}
		b=x;
		c=y;
		for(int a = 1; b>=0&&c>=0; a++)
		{
			b=x-a;
			c=y-a;
			if(chart[b][c]==BLANK)
				break;
			if(chart[b][c]==color)
			{
				return true;
			}
		}
		return false;
	}
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

}
