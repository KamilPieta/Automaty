package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Image;

import javax.swing.ImageIcon;




import javax.swing.*;
public class DrawArray extends JPanel {
 
	int[][] arr;
	Image death;
	Image life;
	Image life1;
	private Graphics dbGraphics;
	private Image dbImage;
	public DrawArray(int[][] arr) {
		//super();
		
		this.arr=arr;
		ImageIcon deathIcon= new ImageIcon("src/src/death.png");
		ImageIcon lifeIcon = new ImageIcon("src/src/life.png");
		ImageIcon life1Icon = new ImageIcon("src/src/life2.png");
		life1=life1Icon.getImage();
		death = deathIcon.getImage();
		life = lifeIcon.getImage();
	}
	@Override
	public void paint(Graphics g) {
		// super.paint(g);

		if (dbGraphics == null) {
			dbImage = createImage(500,500);
			dbGraphics = dbImage.getGraphics();
		}

		paintComponent(dbGraphics);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.setBackground(Color.WHITE);



		for (int i = 0; i < 20; ++i) {
			for (int j = 0; j < 20; ++j) {

				if (arr[i][j] == 1) {
			g.drawImage(life, i*25, j*25, 25, 25, null);

				} 
				
				else if(arr[i][j]==2)
					g.drawImage(life1, i*25, j*25, 25, 25, null);
				
				else {
			g.drawImage(death, i*25, j*25, 25, 25, null);
				}
				

				
			}
			
		}
	
		repaint();

	}

}
