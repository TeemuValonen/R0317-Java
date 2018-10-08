import java.util.Scanner;

public class JuomaAutomaatti {

	private int tee;
	private int kahvi;
	private int kaakao;

	@Override
	public String toString() {
		return "Teetä jäljellä " + tee + "\nKahvia jäljellä " + kahvi + "\nKaakaota jäljellä " + kaakao;
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
	// konstruktori (kaikki kentät)
	public JuomaAutomaatti(int tee, int kahvi, int kaakao) {
		super();
		this.tee = tee;
		this.kahvi = kahvi;
		this.kaakao = kaakao;
	}

	// Konstruktori ilman kenttiä
	public JuomaAutomaatti() {
		super();
	}

	// Metodit, jotka valmistavat juomia

	public void valmistaKahvi() {
		if (this.kahvi > 0) {
			this.kahvi -= 10;

			System.out.println("\nOdota hetki, kahvisi valmistuu. Kahvia on jäljellä " + this.kahvi + " yksikköä.");
		} else {
			System.out.println("Kahvia ei enää ole! Täytä säiliö.");
		}
	}

	public void valmistaTee() {
		if (this.tee > 0) {
			this.tee -= 10;

			System.out.println("\nOdota hetki, teesi valmistuu. Teetä on jäljellä " + this.tee + " yksikköä.");
		} else {
			System.out.println("Teetä ei enää ole! Täytä säiliö.");
		}
	}

	public void valmistaKaakao() {
		if (this.kaakao > 0) {
			this.kaakao -= 10;
			System.out.println("\nOdota hetki, kaakaosi valmistuu. Kaakaota on jäljellä " + this.kaakao + " yksikköä.");
		} else {
			System.out.println("Kaakaota ei enää ole! Täytä säiliö.");
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
					System.out.println("Syöte ei kelpaa. Anna numero 1-4.");
				}
			} else {
				System.out.println("Ei onnistu, kiitos kuitenkin maksustasi.");
			}
		}
	}

}