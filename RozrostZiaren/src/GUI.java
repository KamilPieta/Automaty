

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI extends JFrame implements MouseListener {

	Boolean immigration = false;
	Boolean periodic = false;
	DrawArray arr;
	
	static int licznik =0;
	int[][] tab1 = new int[301][301];
	

	private int sprawdzSasiadow(int[][] arr) {

		int liczba_sasiadow = 0;
		liczba_sasiadow = (arr[1][1] == 1 || arr[1][1] == 2) ? -1 : 0;

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] == 1) {
					liczba_sasiadow++;
					
				}
				if (arr[i][j] == 2) {
					liczba_sasiadow++;
				
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
			if(periodic){
			if(i==0)
			BCip=arr.arr.length-1;
			if(j==0)
			BCjp=arr.arr.length-1;
			if(i==arr.arr.length-1)
			BCik=0;
			if(j==arr.arr.length-1)
			BCjk=0;
			}
			else{
				if(i==0)
					BCip=300;
					if(j==0)
					BCjp=300;
					if(i==arr.arr.length-1)
					BCik=300;
					if(j==arr.arr.length-1)
					BCjk=300;
				
			}
			
				array[1][1]= arr.arr[i][j];
				array[0][0]=arr.arr[BCip][BCjp];
				array[2][2]=arr.arr[BCik][BCjk];		
				array[0][1]=arr.arr[BCip][j];
				array[1][0]=arr.arr[i][BCjp];
				array[1][2]=arr.arr[i][BCjk];
				array[2][1]=arr.arr[BCik][j];
				array[2][0]=arr.arr[BCik][BCjp];
				array[0][2]=arr.arr[BCip][BCjk];
		
				
					
			
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
	private void clean(){
		for (int i = 0; i < arr.arr.length; i++) {
			for (int j = 0; j < arr.arr.length; ++j)
				arr.arr[i][j] = 0;
		}
		licznik=0;
		arr.koloryMapa.clear();
	
	}

	public GUI() {

		JButton button_o = new JButton("Rozne kolory");
		JButton button_n = new JButton("niezmienne");
		JButton button_pn = new JButton("periodyczne On/Off");
		JButton button_c = new JButton("Czysc");
		JButton button_k = new JButton("symulacja >>");
		JButton button_w = new JButton("Wybierz");
		JButton button_d = new JButton("Darwinia on/off");
		
	    String[] selections = { "losowe", "rownomierne", "z promieniem" ,"periodyczne On/Off","Rozne kolory","Czysc"};
	    JComboBox lista = new JComboBox(selections);
	    lista.setSelectedIndex(1);
		
		
		JPanel main_panel = new JPanel();
		JPanel button_panel = new JPanel();
		//button_panel.add(button_o, new FlowLayout());
		//button_panel.add(button_n, new FlowLayout());
	//	button_panel.add(button_pn, new FlowLayout());
		//button_panel.add(button_c, new FlowLayout());
		
		button_panel.add(lista, new FlowLayout());
		button_panel.add(button_w, new FlowLayout());
		button_panel.add(button_k, new FlowLayout());
		arr = new DrawArray(tab1);

		button_d.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			

			}
		});

		button_w.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//  String[] selections = { "losowe", "rownomierne", "z promieniem" ,"periodyczne On/Off","Rozne kolory","Czysc"};
				int choose = lista.getSelectedIndex();
				System.out.println(choose);
			
				
				switch (choose){
					
				case 0:
				{
					Random rand = new Random();
				for(int i=0;i<20;++i)
					arr.arr[rand.nextInt(300)][rand.nextInt(300)]=++licznik;
				
				}break;
				
				case 1:
				{
				int period=	arr.arr.length/10;
				System.out.println(period+" ::");
				for(int i=0;i<arr.arr.length/20;++i){
					for(int j=0;j<arr.arr.length/30;++j)
						arr.arr[(period*i)%arr.arr.length][(period*j)%arr.arr.length]=++licznik;
				}}break;
				
				case 2:
				{
					int promien = 30;
				}break;
				
				case 3:
				{
					periodic=!periodic;
				}break;
				
				case 5:
					clean();
					break;
				}
				
				arr.repaint();

			}
		});

		button_k.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				simulate();

			}
		});

		button_o.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Random rand = new Random();

				for (int i = 0; i < arr.arr.length; i++) {
					for (int j = 0; j < arr.arr.length; j++)
					{	
				
				int x = rand.nextInt(256);
				
				arr.arr[i][j] =x;
				
					}
					arr.repaint();
			}}
		});

		button_n.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
			arr.arr[0][0]=1;
			arr.arr[1][1]=2;
			arr.arr[2][2]=7;
			arr.arr[3][3]=4;
			arr.arr[4][4]=1;
			arr.repaint();
			}
		});

		button_pn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				periodic=!periodic;
			}
		});

		arr.setSize(600,600);
		arr.repaint();

		main_panel.add(button_panel);
		addMouseListener(this);
		add(main_panel, BorderLayout.NORTH);
		add(arr, BorderLayout.CENTER);
		setSize(680, 650);
		setTitle("Rozrost");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int xCord, yCord;
			System.out.println("x :: "+ e.getX()+ "  y ::: "+e.getY());
		if (e.getX() >= 0 && e.getX() <= 600 && e.getY() >= 50
				&& e.getY() <= 650) {
			xCord = e.getX() / 2;
			yCord = (e.getY() - 50) / 2;
			licznik++;
			arr.arr[xCord][yCord] = licznik;
			arr.repaint();
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
