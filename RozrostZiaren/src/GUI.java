
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame implements MouseListener {

	Boolean periodic = true;
	DrawArray arr;
	int[][] tab = new int[387][300];
	static int licznik = 0;

	protected int chooseSimultaion = -1;

	private void algorytm(int choose) {

		int[][] tab1 = new int[387][300];
		for (int i = 0; i < arr.arr.length; i++) {
			for (int j = 0; j < arr.arr[0].length; ++j) {
				int[][] array = new int[3][3];
				int BCip = i - 1, BCjp = j - 1, BCik = i + 1, BCjk = j + 1;
				if (periodic) {
					if (i == 0)
						BCip = arr.arr.length - 1;
					if (j == 0)
						BCjp = arr.arr[0].length - 1;
					if (i == arr.arr.length - 1)
						BCik = 0;
					if (j == arr.arr[0].length - 1)
						BCjk = 0;

				} 
				array[1][1] = arr.arr[i][j];
				array[0][0] = arr.arr[BCip][BCjp];
				array[2][2] = arr.arr[BCik][BCjk];
				array[0][1] = arr.arr[BCip][j];
				array[1][0] = arr.arr[i][BCjp];
				array[1][2] = arr.arr[i][BCjk];
				array[2][1] = arr.arr[BCik][j];
				array[2][0] = arr.arr[BCik][BCjp];
				array[0][2] = arr.arr[BCip][BCjk];

				if (array[1][1] == 0) {
					int[] neight = new int[8];
					switch (choose) {
					case 1: { // hexagonalne prawostronna
						neight[0] = array[0][1];
						neight[1] = array[0][2];
						neight[2] = array[1][0];
						neight[3] = array[2][0];
						neight[4] = array[2][1];
						neight[5] = array[1][2];
					}
						break;
					case 2: { // VonNeumana
						neight[0] = array[0][1];
						neight[1] = array[1][0];
						neight[2] = array[1][2];
						neight[3] = array[2][1];
					}
						break;
					case 3: { // Moora
						neight[0] = array[0][0];
						neight[1] = array[0][1];
						neight[2] = array[0][2];
						neight[3] = array[1][0];
						neight[4] = array[1][2];
						neight[5] = array[2][0];
						neight[6] = array[2][2];
						neight[7] = array[2][1];

					}
						break;

					case 4: {// pentagonalne dol
						neight[0] = array[1][0];
						neight[1] = array[1][2];
						neight[2] = array[2][2];
						neight[3] = array[2][0];
						neight[4] = array[2][1];
					}
						break;

					case 5: {// pentagonalne lewa
						neight[0] = array[0][0];
						neight[1] = array[0][1];
						neight[2] = array[1][0];
						neight[3] = array[2][0];
						neight[4] = array[2][1];
					}
						break;
					case 6: {// pentagonalne prawa

						neight[0] = array[0][1];
						neight[1] = array[0][2];
						neight[2] = array[1][2];
						neight[3] = array[2][1];
						neight[4] = array[2][2];
					}
						break;
					case 7: {// pentagonalne gora
						neight[0] = array[0][1];
						neight[1] = array[0][2];
						neight[2] = array[0][0];
						neight[3] = array[1][0];
						neight[4] = array[1][2];
					}
						break;
					case 8: { // hexagonalne lewostronna
						neight[0] = array[0][0];
						neight[1] = array[0][1];
						neight[2] = array[1][0];
						neight[3] = array[1][2];
						neight[4] = array[2][1];
						neight[5] = array[2][2];
					}
						break;
					}
					Map<Integer, Integer> powtorki = new HashMap<Integer, Integer>();
					Integer powtorzono = 0;
					Boolean dodano = false;

					for (int n = 0; n < neight.length; ++n) {
						if (neight[n] != 0) {
							for (Integer key : powtorki.keySet())
								if (key == neight[n]) {
									powtorki.put(key, ++powtorzono);
									dodano = true;
								}

							if (dodano == false) {
								dodano = false;
								powtorzono = 1;
								powtorki.put(neight[n], powtorzono);
							}

							Map.Entry<Integer, Integer> maxEntry = null;
							int maxValueInMap = (Collections.max(powtorki.values()));
							for (Entry<Integer, Integer> entry : powtorki.entrySet()) {
								if (entry.getValue() == maxValueInMap) {
									tab1[i][j] = entry.getKey();
								}
							}
						}
					}
					arr.arr[i][j] = array[1][1];
					arr.arr[BCip][BCjp] = array[0][0];
					arr.arr[BCik][BCjk] = array[2][2];
					arr.arr[BCip][j] = array[0][1];
					arr.arr[i][BCjp] = array[1][0];
					arr.arr[i][BCjk] = array[1][2];
					arr.arr[BCik][j] = array[2][1];
					arr.arr[BCik][BCjp] = array[2][0];
					arr.arr[BCip][BCjk] = array[0][2];
				}
			}
		}
		for (int i = 0; i < arr.arr.length; ++i)
			for (int j = 0; j < arr.arr[0].length; ++j)
				arr.arr[i][j] += tab1[i][j];
		arr.repaint();
	}

	private void simulate(int choose) {
		Thread t1 = new Thread() {

			public void run() {
				try {
					for (int i = 0; i < 100; ++i) {
						Thread.sleep(50);
						algorytm(choose);
					}
				} catch (Exception e) {
				}
			}
		};
		t1.start();
	}

	private void clean() {
		for (int i = 0; i < arr.arr.length; i++) {
			for (int j = 0; j < arr.arr[0].length; ++j)
				arr.arr[i][j] = 0;
		}
		licznik = 0;
		arr.koloryMapa.clear();
	}

	public GUI() {
		arr = new DrawArray(tab);
		JButton button_k = new JButton("symulacja >>");
		JButton button_w = new JButton("Wybierz");
		JButton button_p = new JButton("Rysuj z promieniem");
		String[] selections = { "losowe", "rownomierne", "heksagonalna prawo", "periodyczne On/Off",
				"heksagonalna lewostr", "Von Neumana", "Moore'a", "pentagonalnie(losowo)", "heksogonalna (losowo)",
				"Czysc" };
		JComboBox lista = new JComboBox(selections);
		lista.setSelectedIndex(1);
		JSlider slider = new JSlider();
		JLabel sliderVal = new JLabel();
		JPanel slider_panel = new JPanel(new BorderLayout());
		JPanel main_panel = new JPanel();
		JPanel button_panel = new JPanel();
		slider.setMaximum(150);
		slider_panel.add(slider, BorderLayout.NORTH);
		slider_panel.add(sliderVal, BorderLayout.CENTER);
		button_panel.add(lista, new FlowLayout());
		button_panel.add(button_w, new FlowLayout());
		button_panel.add(button_k, new FlowLayout());
		button_panel.add(slider_panel);
		button_panel.add(button_p, new FlowLayout());

		button_w.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int choose = lista.getSelectedIndex();

				switch (choose) {

				case 0: {
					Random rand = new Random();
					for (int i = 0; i < 20; ++i)
						arr.arr[rand.nextInt(387)][rand.nextInt(300)] = ++licznik;
				}
					break;

				case 1: {
					int period1 = arr.arr[0].length / 10;
					int period2 = arr.arr.length / 10;
					for (int i = 0; i < arr.arr.length / 30; ++i) {
						for (int j = 0; j < arr.arr[0].length / 30; ++j)
							if(period2*i<arr.arr.length && period1*j<arr.arr[0].length)
							arr.arr[(period2 * i) ][(period1 * j)] = ++licznik;
					}
				}
					break;

				case 2:
					chooseSimultaion = 1;
					break;

				case 3:
					periodic =periodic;
					break;

				case 4:
					chooseSimultaion = 8;
					break;
				case 5:
					chooseSimultaion = 2;
					break;
				case 6:
					chooseSimultaion = 3;
					break;
				case 7: {
					Random losuj = new Random();
					chooseSimultaion = losuj.nextInt(4) + 4;
				}
					break;
				case 8: {
					Random los = new Random();
					if (los.nextInt(2) == 0)
						chooseSimultaion = 1;
					else
						chooseSimultaion = 8;
				}
					break;

				case 9:
					clean();
					break;
				}
				arr.repaint();
			}
		});

		button_k.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				simulate(chooseSimultaion);

			}
		});

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sliderVal.setText("promien rowny : " + String.valueOf(slider.getValue()));

			}
		});

		button_p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int minDist = (int) (((slider.getValue() * Math.sqrt(2)) / 2) + 1);
				Random pointX = new Random();
				Random pointY = new Random();
				int giveAShot = 100;
				int pX;
				int pY;
				boolean isAvalible = true;
				
				while (giveAShot != 0) {
					do {
						pX = pointX.nextInt(arr.arr.length);
						pY = pointY.nextInt(arr.arr[0].length);
					} while ((pX - minDist) < 0 || (pY - minDist) < 0 || (pX + minDist) > arr.arr.length
							|| (pY + minDist) > arr.arr[0].length);
				
					
						for (int i = (pX - minDist); i < (pX + minDist); ++i)
								for (int j = (pY - minDist); j < (pY + minDist); ++j)
								{	if (arr.arr[i][j] != 0)
								{isAvalible = false;break;}}
					
					if (isAvalible)
					{	arr.arr[pX][pY] = ++licznik;
							}	
					
					isAvalible = true;
					giveAShot--;
				}
				
				arr.repaint();
				
			}
		});

		// arr.setSize(760,600);
		arr.repaint();

		main_panel.add(button_panel);
		addMouseListener(this);
		add(main_panel, BorderLayout.PAGE_START);
		add(arr, BorderLayout.CENTER);
		setSize(780, 676);
		setTitle("Rozrost");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int xCord, yCord;
		System.out.println("x :: " + e.getX() + "  y :: " + e.getY());
		if (e.getX() >= 0 && e.getX() <= 600 && e.getY() >= 50 && e.getY() <= 650) {
			xCord = (e.getX() - 5) / 2;
			yCord = (e.getY() - 75) / 2;
			
			System.out.println("xCord= "+xCord +" yCord= "+yCord);
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
