
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {

	private static void computeAutomat() {

		System.out.println("Witaj w programie liczacym automaty, podaj regule");

		int regula = 0;

		Scanner in = new Scanner(System.in);

		regula = in.nextInt();

		System.out.println("wybrales regule:  " + regula);

		String liczba = Integer.toString(regula, 2);

		System.out.println("Binarnie liczba to " + liczba);

		int[] binaryArr = new int[8];
		if (liczba.length() <= 8) {
			int c = 8 - liczba.length();
			for (int i = 0; i < liczba.length(); i++) {
				binaryArr[c] = Integer.parseInt(liczba.substring(i, i + 1));
				c++;
			}
			// System.out.println(Arrays.toString(binaryArr));
		}

		else {
			System.out.println("Blad! za duza regula");
		}

		int size = 12;
		int[] arr = { 0, 1, 0 };
		System.out.println("Sasiedstwo to : " + sprawdzSasiedctwo(arr, binaryArr));

		int[] stan1 = new int[size];
		Random rand = new Random();
		for (int i = 0; i < stan1.length; ++i)
			stan1[i] = rand.nextInt(2);
		System.out.println("Stan poczatkowy " + Arrays.toString(stan1));

		System.out.println("Podaj czy warunki brzegowe maja sie rownac 1 czy 0");
		int bc;
		in = new Scanner(System.in);
		bc = in.nextInt();
		System.out.println("Warunki brzegowe ustawione na: " + bc);

		int[] stan2 = new int[size];

		for (int xi = 0; xi < 70; ++xi) {

			for (int i = 0; i < stan1.length; i++) {

				if (i == 0) {
					arr[0] = bc;
					arr[1] = stan1[i];
					arr[2] = stan1[i + 1];
				} else if (i == stan1.length - 1) {
					arr[0] = stan1[i - 1];
					arr[1] = stan1[i];
					arr[2] = bc;
				} else {
					arr[0] = stan1[i - 1];
					arr[1] = stan1[i];
					arr[2] = stan1[i + 1];
				}

				stan2[i] = sprawdzSasiedctwo(arr, binaryArr);
				// System.out.print("zwraca "+ Integer.toString(stan2[i]));
				// System.out.println(Arrays.toString(arr) + " i
				// "+Integer.toString(i));

			}
			// System.out.println("Stan1 "+Arrays.toString(stan1));
			System.out.println("Stan" + xi + "\t" + Arrays.toString(stan2));
			for (int a = 0; a < stan1.length; a++)
				stan1[a] = stan2[a];

		}

	}

	private static int sprawdzSasiedctwo(int[] arr, int[] binaryArr) {

		int[][] wzor = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 }, { 1, 0, 0 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 1 },
				{ 0, 0, 0 } };
		int c = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[j] == wzor[i][j])
					c++;
				if (c == 3) {
					return binaryArr[i];
				}
			}
			c = 0;
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		computeAutomat();

	}

}
