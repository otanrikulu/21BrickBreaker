package game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class BrickPanel extends JPanel implements Runnable
	{
		static final int WIDTH = 700, HEIGHT = 650;
		private final int numRows = 7, numCols = 7;
		private Image image;
		private Graphics graphics;
		private Brick[][] bricks;
		private Paddle paddle;
		private Ball ball;
		private Thread thread;
//		private Thread music;


		public BrickPanel()
		{
			this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			this.setFocusable(true);
			Music music = new Music();
			music.start();
			addKeyListener(new MyKey());
			gameSetup();
			this.thread = new Thread(this);
			thread.start();
		}


		private void gameSetup()
		{
			paddle = new Paddle();
			ball = new Ball();
			bricks = new Brick[numCols][numRows];//creating 2D array
			for(int row = 0; row < numRows; row++)
			{
				for(int col = 0; col < numCols; col++)
				{
					 bricks[row][col] = new Brick(row, col);
				}
			}
		}

		public void move()
		{
			paddle.move();
			ball.move();
		}
		
		private void checkCollision()
		{
			Rectangle ballRect = ball.getBounds();
			Rectangle pRect = paddle.getBounds();
			
			if(ballRect.y + ballRect.getHeight() > pRect.y && ballRect.getCenterX() > pRect.x && ballRect.getCenterX() < pRect.x + pRect.getWidth()) 
			{
				ball.ySpeed *= -1;
			}
			
			for(int row = 0; row < numRows; row++)
			{
				for(int col = 0; col < numCols; col++)
				{
					if(bricks[row][col].visible)
					{
						Rectangle brickRect = bricks[row][col].getBounds();
						if(ballRect.y <= brickRect.getHeight() + brickRect.y && 
								ballRect.getCenterX() > brickRect.x && 
								ballRect.getCenterX() < brickRect.width + brickRect.x)
						{
							bricks[row][col].visible = false;
							ball.ySpeed *= -1;
						}
					}
				}
			}
		}

		public void paint(Graphics g)
		{
			image = createImage(getWidth(), getHeight());
			graphics = image.getGraphics();
			draw(graphics);
			g.drawImage(image, 0, 0, this);
		}
		
		public void draw(Graphics g)
		{
			for(int row = 0; row < numRows; row++)
			{
				for(int col = 0; col < numCols; col++)
				{
					bricks[row][col].draw(g);
				}
			}
			ball.draw(g);
			paddle.draw(g);
		}
		
		@Override
		public void run() 
		{
			long lastTime = System.nanoTime();
			int fps = 60;
			double ns = 1000000000/fps;
			double delta = 0;
			while(true)
			{
				long now = System.nanoTime();
				delta += (now - lastTime)/ns;
				lastTime = now;
				if(delta >= 1) //updating movement, checking for collision, and redrawing the screen
				{
					move();
					checkCollision();
					repaint();
					delta--;
				}
			}
		}
		
		/*if (bricks[row][col].visible = false)
		{
			JOptionPane.showInputDialog("NEW HIGH SCORE! Enter your name");
		}*/

		public class MyKey extends KeyAdapter
		{
			public void keyPressed(KeyEvent e)
			{
				paddle.keyPressed(e);
			}
			
			public void keyReleased(KeyEvent e)
			{
				paddle.keyReleased(e);
			}
		}
		
		/*public void startOver()
		{
			Music music = new Music();
			music.start();
			addKeyListener(new MyKey());
			gameSetup();
			this.thread = new Thread(this);
			thread.start();
		}*/
	}
