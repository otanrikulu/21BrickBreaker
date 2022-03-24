package game;

import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Ball
{
	private int xPos = 300, yPos = 250;
	public int xSpeed = 4, ySpeed = 4;
	private BufferedImage ball;// image that keeps moving

	Ball()
	{
		loadImage();
	}

	private void loadImage()
	{
		try
		{
			ball = ImageIO.read(new File("src/images/redball.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void move()
	{
		xPos += xSpeed;
		//checking if ball is bouncing off left side
		if(xPos < 0)
		{
			xPos = 0;
			xSpeed *= -1;
		}

		//checking if ball is bouncing off right side
		if(xPos > BrickPanel.WIDTH - ball.getWidth())
		{
			xPos = BrickPanel.WIDTH - ball.getWidth();
			xSpeed *= -1;
		}

		//checking if ball is bouncing off top
		yPos += ySpeed;
		if(yPos < 0)
		{
			yPos = 0;
			ySpeed *= -1;
		}

		//checking if ball is bouncing off bottom
		if(yPos > BrickPanel.HEIGHT - ball.getHeight())
		{
			yPos = BrickPanel.HEIGHT - ball.getHeight();
			ySpeed *= -1;
			
		/*	int answer = JOptionPane.showConfirmDialog(null,"Play again?","Game Over!",
					JOptionPane.YES_NO_OPTION);
			if(answer == 0) {
				startOver();
			}
			else {
				System.exit(0);
			}*/
		}

	}

	private void startOver()
	{
		loadImage();
	}

	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(ball, xPos, yPos, null);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(xPos, yPos, ball.getWidth(), ball.getHeight());
	}
}
