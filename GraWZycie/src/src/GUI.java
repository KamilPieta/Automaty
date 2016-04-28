package src;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI extends JFrame implements MouseListener {

	Boolean immigration = false;
	Boolean darwinia = false;
	static DrawArray arr;
	int greenN = 0;
	int redN = 0;
	int total_redN = 0;
	int total_greenN = 0;
	int[][] tab1 = new int[20][20];
	static int[][] lifetime = new int[20][20];

	private int sprawdzSasiadow(int[][] arr) {

		int liczba_sasiadow = 0;
		liczba_sasiadow = (arr[1][1] == 1 || arr[1][1] == 2) ? -1 : 0;

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] == 1) {
					liczba_sasiadow++;
					redN++;
				}
				if (arr[i][j] == 2) {
					liczba_sasiadow++;
					greenN++;
				}
			}
		}

		return liczba_sasiadow;

	}

	private void algorytm(){
		
		
		
		int[][] array= new int[3][3];
		

		for(int i=0;i<arr.arr.length;i++){
		for(int j=0; j<arr.arr.length;++j)
		{	
		
			int BCip=i-1, BCjp=j-1,BCik=i+1,BCjk=j+1;
			if(i==0)
			BCip=arr.arr.length-1;
			if(j==0)
			BCjp=arr.arr.length-1;
			if(i==arr.arr.length-1)
			BCik=0;
			if(j==arr.arr.length-1)
			BCjk=0;
			
				array[1][1]= arr.arr[i][j];
				array[0][0]=arr.arr[BCip][BCjp];
				array[2][2]=arr.arr[BCik][BCjk];		
				array[0][1]=arr.arr[BCip][j];
				array[1][0]=arr.arr[i][BCjp];
				array[1][2]=arr.arr[i][BCjk];
				array[2][1]=arr.arr[BCik][j];
				array[2][0]=arr.arr[BCik][BCjp];
				array[0][2]=arr.arr[BCip][BCjk];
		
				if(array[1][1]==0 && sprawdzSasiadow(array)==3)
				
					{
					if(greenN==0)
					{	tab1[i][j]=1; total_redN++;}
					
					else if (redN==greenN)
					{
						if(total_redN>total_greenN) tab1[i][j]=1;
						else
							tab1[i][j]=2;
					}
					else if(redN>greenN)
						{tab1[i][j]=1; total_redN++;}
					
					else
					{ tab1[i][j]=2;total_greenN++;}
					
					redN=0;greenN=0;
					}
				
				else if((array[1][1]==1 ||array[1][1]==2) && (sprawdzSasiadow(array)==3 || sprawdzSasiadow(array)==2))
					{
					if(greenN==0)
					tab1[i][j]=1;
				
					
					else if(redN>greenN)
							tab1[i][j]=1;
					 else
						 tab1[i][j]=2;
					 redN=0;greenN=0;
					}
				else if(array[1][1]==1 ||array[1][1]==2){total_redN--; total_greenN--;	}
					
			
		}
		
	}
		for(int i=0;i<arr.arr.length;i++){
			for(int j=0; j<arr.arr.length;++j)
			{	arr.arr[i][j]=tab1[i][j];
			tab1[i][j]=0;
			
				
			}}
		arr.repaint();
		
		
		
	}

	private void simulate() {
		Thread t1 = new Thread() {

			public void run() {
				try {
					for (int i = 0; i < 51; ++i) {
						Thread.sleep(50);
						algorytm();
					}

				} catch (Exception e) {
				}
			}

		};
		t1.start();

	}

	public GUI() {

		JButton button_o = new JButton("Oscylator");
		JButton button_n = new JButton("niezmienne");
		JButton button_g = new JButton("Glider");
		JButton button_c = new JButton("Czysc");
		JButton button_k = new JButton("symulacja >>");
		JButton button_i = new JButton("Immigration on/off");
		JButton button_d = new JButton("Darwinia on/off");
		JPanel main_panel = new JPanel();
		JPanel button_panel = new JPanel();
		button_panel.add(button_o, new FlowLayout());
		button_panel.add(button_n, new FlowLayout());
		button_panel.add(button_g, new FlowLayout());
		button_panel.add(button_c, new FlowLayout());
		button_panel.add(button_k, new FlowLayout());
		button_panel.add(button_i, new FlowLayout());
	
		arr = new DrawArray(tab1);

		button_d.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				darwinia = !darwinia;

			}
		});

		button_i.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				immigration = !immigration;

			}
		});

		button_k.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				simulate();
				arr.repaint();

			}
		});

		button_c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < arr.arr.length; i++) {
					for (int j = 0; j < arr.arr.length; ++j)
						arr.arr[i][j] = 0;
				}

			}

		});
		button_o.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();

				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++)
						arr.arr[i][j] = 0;
				}
				int x = rand.nextInt(20);
				int y = rand.nextInt(17);
				arr.arr[x][y] = 1;
				arr.arr[x][y + 1] = 1;
				arr.arr[x][y + 2] = 1;

			}
		});

		button_n.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();

				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++)
						arr.arr[i][j] = 0;
				}
				int losowo = rand.nextInt(2);

				if (losowo == 1) {

					int x = rand.nextInt(17) + 1;
					int y = rand.nextInt(17) + 1;
					arr.arr[x - 1][y] = 1;
					arr.arr[x - 1][y + 1] = 1;
					arr.arr[x][y - 1] = 1;
					arr.arr[x + 1][y] = 1;
					arr.arr[x + 1][y + 1] = 1;
					arr.arr[x][y + 2] = 1;

					arr.repaint();
				}

				else if (losowo == 0) {

					int x = rand.nextInt(17) + 1;
					int y = rand.nextInt(17) + 1;
					arr.arr[x][y - 1] = 1;
					arr.arr[x + 1][y - 1] = 1;
					arr.arr[x - 1][y] = 1;
					arr.arr[x][y + 1] = 1;
					arr.arr[x + 1][y + 1] = 1;
					arr.arr[x + 2][y] = 1;
					arr.repaint();
				}

			}
		});

		button_g.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++)
						arr.arr[i][j] = 0;
				}

				int losowo = rand.nextInt(2);
				System.out.println(losowo);
				if (losowo == 1) {
					int x = rand.nextInt(18) + 1;
					int y = rand.nextInt(18) + 1;
					arr.arr[x][y - 1] = 1;
					arr.arr[x + 1][y] = 1;
					arr.arr[x - 1][y - 1] = 1;
					arr.arr[x - 1][y] = 1;
					arr.arr[x - 1][y + 1] = 1;
					;
					arr.repaint();
				} else if (losowo == 0) {

					int x = rand.nextInt(17) + 1;
					int y = rand.nextInt(17) + 1;
					arr.arr[x - 1][y] = 1;
					arr.arr[x][y + 1] = 1;
					arr.arr[x - 1][y - 1] = 1;
					arr.arr[x][y - 1] = 1;
					arr.arr[x + 1][y - 1] = 1;
					arr.repaint();
				}
				/*
				 * else if (losowo == 2) {
				 * 
				 * int x = rand.nextInt(18)+1; int y = rand.nextInt(18)+1;
				 * arr.arr[x - 1][y] = 1; arr.arr[x][y - 1] = 1; arr.arr[x -
				 * 1][y - 1] = 1; arr.arr[x+1][y - 1] = 1; arr.arr[x + 1][y ] =
				 * 1; arr.repaint(); }
				 */

			}
		});

		arr.setSize(400, 400);
		arr.repaint();

		main_panel.add(button_panel);
		addMouseListener(this);
		// setLayout(new FlowLayout());
		add(main_panel, BorderLayout.NORTH);
		add(arr, BorderLayout.CENTER);
		setSize(680, 550);
		setTitle("Gra w Zycie");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int xCord, yCord;

		if (e.getX() >= 0 && e.getX() <= 500 && e.getY() >= 50
				&& e.getY() <= 550) {
			xCord = e.getX() / 25;
			yCord = (e.getY() - 50) / 25;

			arr.arr[xCord][yCord] = 1;
			arr.repaint();
		}
		if (immigration) {
			if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
				xCord = e.getX() / 25;
				yCord = (e.getY() - 50) / 25;

				arr.arr[xCord][yCord] = 2;
				arr.repaint();
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
