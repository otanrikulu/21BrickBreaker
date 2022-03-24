package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BrickFrame extends JFrame 
{


	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					BrickFrame frame = new BrickFrame();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}


	public BrickFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Brick Breaker Game");
		ImageIcon icon = new ImageIcon("src/images/icons8-potted-plant-100.png");
		this.setIconImage(icon.getImage());
		this.setLocation(300, 0);
		this.setResizable(false);
		setContentPane(new BrickPanel());
		this.pack();
	}

}
