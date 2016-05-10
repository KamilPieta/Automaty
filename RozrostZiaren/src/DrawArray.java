
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import javax.swing.*;

public class DrawArray extends JPanel {

	int[][] arr;

	Map<Integer, Integer[]> koloryMapa = new HashMap<Integer, Integer[]>();
	private Integer[] kolors;
	Random rand;

	public DrawArray(int[][] arr) {
		this.arr = arr;
		 repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.setBackground(Color.GRAY);

		Color randomColor = null;
		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr[0].length; ++j) {
				
				Boolean setColor = false;
				if (arr[i][j] == 0) {
					g.setColor(Color.WHITE);
					g.fillRect(i * 2, j * 2, 2, 2);
				} else {
					for (Integer key : koloryMapa.keySet()) {
						if (key == arr[i][j]) {
							randomColor = new Color((int) koloryMapa.get(arr[i][j])[0],
									(int) koloryMapa.get(arr[i][j])[1], (int) koloryMapa.get(arr[i][j])[2]);
							setColor = true;
							break;
						}
					}
					if (setColor == false) {
						rand = new Random();
						int R = rand.nextInt(256);
						int G = rand.nextInt(256);
						int B = rand.nextInt(256);
						kolors = new Integer[3];
						kolors[0] = (Integer) R;
						kolors[1] = (Integer) G;
						kolors[2] = (Integer) B;
						koloryMapa.put(arr[i][j], kolors);
						randomColor = new Color(kolors[0], kolors[1], kolors[2]);
						setColor = false;
					}
					g.setColor(randomColor);
					g.fillRect(i * 2, j * 2, 2, 2);
				}
			}
		}
	}
}
