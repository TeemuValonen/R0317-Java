import java.util.Scanner;

public class JuomaAutomaatti {

	private int tee;
	private int kahvi;
	private int kaakao;

	@Override
	public String toString() {
		return "Teet� j�ljell� " + tee + "\nKahvia j�ljell� " + kahvi + "\nKaakaota j�ljell� " + kaakao;
	}

	// getterit ja setterit

	// tee
	public int getTee() {
		return tee;
	}

	public void setTee(int tee) {
		this.tee = tee;
	}

	// kahvi
	public int getKahvi() {
		return kahvi;
	}

	public void setKahvi(int kahvi) {
		this.kahvi = kahvi;
	}

	// kaakao
	public int getKaakao() {
		return kaakao;
	}

	public void setKaakao(int kaakao) {
		this.kaakao = kaakao;
	}

	// Konstruktorit
	// konstruktori (kaikki kent�t)
	public JuomaAutomaatti(int tee, int kahvi, int kaakao) {
		super();
		this.tee = tee;
		this.kahvi = kahvi;
		this.kaakao = kaakao;
	}

	// Konstruktori ilman kentti�
	public JuomaAutomaatti() {
		super();
	}

	// Metodit, jotka valmistavat juomia

	public void valmistaKahvi() {
		if (this.kahvi > 0) {
			this.kahvi -= 10;

			System.out.println("\nOdota hetki, kahvisi valmistuu. Kahvia on j�ljell� " + this.kahvi + " yksikk��.");
		} else {
			System.out.println("Kahvia ei en�� ole! T�yt� s�ili�.");
		}
	}

	public void valmistaTee() {
		if (this.tee > 0) {
			this.tee -= 10;

			System.out.println("\nOdota hetki, teesi valmistuu. Teet� on j�ljell� " + this.tee + " yksikk��.");
		} else {
			System.out.println("Teet� ei en�� ole! T�yt� s�ili�.");
		}
	}

	public void valmistaKaakao() {
		if (this.kaakao > 0) {
			this.kaakao -= 10;
			System.out.println("\nOdota hetki, kaakaosi valmistuu. Kaakaota on j�ljell� " + this.kaakao + " yksikk��.");
		} else {
			System.out.println("Kaakaota ei en�� ole! T�yt� s�ili�.");
		}
	}

	private static boolean onnistuuko() {
		boolean onnistuuko = false;
		int tulos = (int) (Math.random() * 100 + 1);
		if (tulos > 25) {
			onnistuuko = true;
		}

		return onnistuuko;
	}

	public static void main(String[] args) {
		int valinta = 0;

		Scanner lukija = new Scanner(System.in);

		JuomaAutomaatti omaKone = new JuomaAutomaatti(100, 100, 100);

		// A-osan esimerkkiajoa varten:
		// System.out.print(omaKone);
		// omaKone.valmistaKahvi();

		while (valinta != 4) {
			System.out.print("\n*******Juoma-Automaatti*******\n\n" + "1. Kahvi\n" + "2. Tee\n" + "3. Kaakao\n"
					+ "4. Lopeta\n\n" + "******************************\n");

			valinta = lukija.nextInt();
			boolean bonus = onnistuuko();
			if (bonus == true) {
				if (valinta == 1) {
					omaKone.valmistaKahvi();
				} else if (valinta == 2) {
					omaKone.valmistaTee();
				} else if (valinta == 3) {
					omaKone.valmistaKaakao();
				} else if (valinta == 4) {
					System.out.println("Lopetetaan.\n");
					System.out.println(omaKone);
				} else {
					System.out.println("Sy�te ei kelpaa. Anna numero 1-4.");
				}
			} else {
				System.out.println("Ei onnistu, kiitos kuitenkin maksustasi.");
			}
		}
	}

}