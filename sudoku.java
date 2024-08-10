package Sudoku;

import java.util.Scanner;

public class sudoku {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[][] tablo = new int[3][3];
		int hak = 3;
		TabloYazdirma(tablo);
		boolean kontrol = true;
		Long startTime = System.nanoTime();
		while (kontrol) {
			System.out.print("\nLütfen hamle yapmak istediğiniz satır numarasını giriniz (1-3): ");
			int satir = scanner.nextInt();
			System.out.print("\nLütfen hamle yapmak istediğiniz sütun numarasını giriniz (1-3): ");
			int sutun = scanner.nextInt();
			System.out.print("\nLütfen hamle yapmak istediğiniz sayıyı giriniz (1-9): ");
			int hamle = scanner.nextInt();
			System.out.println(
					"\n\n----------------------------------------------------------------------------------\n\n");

			if (satir < 1 || satir > 3 || sutun < 1 || sutun > 3 || hamle < 1 || hamle > 9) {
				System.out.println(
						"\nYanlış giriş yaptınız. Satır, sütun numaraları 1 ile 3, hamle 1 ile 9 arasında olmalıdır.");
				hak--;
				System.out.println("\nKalan hakkınız: " + hak);
				System.out.println();
				TabloYazdirma(tablo);
				if (hak == 0) {
					System.out.println("\nHakkınız bitti. Oyun sona erdi.");
					Long endTime = System.nanoTime();
					System.out.println("\nGeçen süreç: " + ((endTime - startTime) / 1_000_000_000.0) + " Saniye");
					break;
				}
				continue;
			}

			satir--;
			sutun--;

			if (HamleKontrol(tablo, satir, sutun, hamle)) {
				tablo[satir][sutun] = hamle;
				System.out.println();
				TabloYazdirma(tablo);

				if (OyunKontrol(tablo)) {
					System.out.println("\nOyun bitti! Tebrikler, sudoku'yu başarıyla çözdünüz!");
					Long endTime = System.nanoTime();
					System.out.println("\nOyunu çözene kadar geçen süreç: " + ((endTime - startTime) / 1_000_000_000.0)
							+ " Saniye");
					kontrol = false;
				}
			} else {
				hak--;
				System.out.println("Kalan hakkınız: " + hak);
				System.out.println();
				if (hak == 0) {
					System.out.println("Hakkınız bitti. Oyun sona erdi.");
					Long endTime = System.nanoTime();
					System.out.println("\nGeçen süreç: " + ((endTime - startTime) / 1_000_000_000.0) + " Saniye");
					break;
				}
				TabloYazdirma(tablo);
			}
		}
	}

	public static void TabloYazdirma(int[][] tablo) {
		System.out.print("\t");
		for (int i = 1; i < 4; i++) {
			System.out.print(i + "   ");
		}
		System.out.println();
		System.out.print("\t");
		for (int i = 1; i < 4; i++) {
			System.out.print("-   ");
			
		}
		System.out.println();

		for (int i = 0; i < 3; i++) {
			System.out.print((i + 1)+"-)" + "\t");
			for (int j = 0; j < 3; j++) {
				System.out.print((tablo[i][j] == 0 ? "_" : tablo[i][j]) + "   ");
			}
			System.out.println();
		}
	}

	public static boolean HamleKontrol(int[][] tablo, int satir, int sutun, int hamle) {
		if (tablo[satir][sutun] != 0) {
			System.out.println("\nYanlış hamle yaptınız: Belirttiğiniz satır ve sütun zaten dolu.\n");
			return false;
		}

		for (int j = 0; j < 3; j++) {
			if (tablo[satir][j] == hamle) {
				System.out.println("\nHamle geçersiz. Bu sayıyı zaten kullandınız.\n");
				return false;
			}
		}

		for (int i = 0; i < 3; i++) {
			if (tablo[i][sutun] == hamle) {
				System.out.println("\nHamle geçersiz. Bu sayıyı zaten kullandınız.\n");
				return false;
			}
		}

		boolean[] kontrol = new boolean[10];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int sayi = tablo[i][j];
				if (sayi == hamle) {
					System.out.println("\nHamle geçersiz. Bu sayıyı zaten kullandınız\n");
					return false;
				}
			}
		}

		return true;
	}

	public static boolean OyunKontrol(int[][] tablo) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tablo[i][j] == 0) {
					return false;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			if (!SatirKontrol(tablo, i) || !SutunKontrol(tablo, i)) {
				return false;
			}
		}

		if (!BlokKontrol(tablo)) {
			return false;
		}

		return true;
	}

	public static boolean SatirKontrol(int[][] tablo, int satir) {
		boolean[] kontrol = new boolean[10];
		for (int j = 0; j < 3; j++) {
			int sayi = tablo[satir][j];
			if (sayi == 0 || kontrol[sayi]) {
				return false;
			}
			kontrol[sayi] = true;
		}
		return true;
	}

	public static boolean SutunKontrol(int[][] tablo, int sutun) {
		boolean[] kontrol = new boolean[10];
		for (int i = 0; i < 3; i++) {
			int sayi = tablo[i][sutun];
			if (sayi == 0 || kontrol[sayi]) {
				return false;
			}
			kontrol[sayi] = true;
		}
		return true;
	}

	public static boolean BlokKontrol(int[][] tablo) {
		boolean[] kontrol = new boolean[10];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int sayi = tablo[i][j];
				if (sayi == 0 || kontrol[sayi]) {
					return false;
				}
				kontrol[sayi] = true;
			}
		}
		return true;
	}
}
