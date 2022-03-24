package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Brick
{
	private int xPos, yPos;
	private BufferedImage brick;
	private Random rand = new Random();
	public boolean visible = true;
	
	Brick(int row, int col)
	{
		createBrick(row, col);  
	}

	private void createBrick(int row, int col) 
	{
		try
		{
			int i = rand.nextInt(10);
			brick = ImageIO.read(new File("src/images/b" + i + ".png"));
			xPos = col * brick.getWidth();
			yPos = row * brick.getHeight();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g)
	{
		if(visible)
		{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(brick, xPos, yPos, null);
		}
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(xPos, yPos, brick.getWidth(), brick.getHeight());
	}
}
