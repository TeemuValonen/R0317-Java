import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class SanakirjaTxtVers {

	// println -metodi helpottaa kirjoittamista ja ehkä siistii koodia, mikäli
	// printlineja kertyy.
	private static void println(String Syote) {
		System.out.println(Syote);
	}

	public static void main(String[] args) throws IOException {
		String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
		String[] englanti = { "cat", "dog", "horse", "car", "boat" };
		HashMap<String, String> sanasto;

		Scanner lukijaSuomi;
		Scanner lukijaEnglanti;
		File sanastoSuomi = new File("suomeksi.txt");
		File sanastoEnglanti = new File("englanniksi.txt");

		if (sanastoSuomi.createNewFile()) {
			println("Tallennettua sanastoa ei löytynyt, luodaan...");
			sanasto = new HashMap<String, String>();
			// syötetään sanakirjaan sanat ja käännökset
			for (int i = 0; i < suomi.length; i++) {
				sanasto.put(suomi[i], englanti[i]);
			}

		} else {
			System.out.println("Tallennettu sanakirja löytyi, ladataan...");

			lukijaSuomi = new Scanner(sanastoSuomi);
			lukijaEnglanti = new Scanner(sanastoEnglanti);
			sanasto = new HashMap<String, String>();

			String fin, eng;

			while (lukijaSuomi.hasNextLine()) {
				fin = lukijaSuomi.nextLine();
				eng = lukijaEnglanti.nextLine();
				sanasto.put(fin, eng);
			}

			lukijaSuomi.close();
			lukijaEnglanti.close();
		}

		Scanner lukija = new Scanner(System.in);

		println("Sanakirjan sisältö: ");
		System.out.println(sanasto + "\n");

		while (true) {
			println("Valitse toiminto: " + "\n 1 - käännä sana" + "\n 2 - lisää uuden sanan käännös sanakirjaan"
					+ "\n 3 - näytä sanakirjan sisältö" + "\n 4 - lopeta ohjelma");
			int valinta = lukija.nextInt();
			lukija.nextLine();

			if (valinta == 1) {
				println("Syötä käännettävä sana (tyhjä syöte palauttaa valikkoon)");
				String sana = lukija.nextLine();

				if ((lukija.hasNextLine()) && (sana.isEmpty())) {
					println("Palataan takaisin.");

				} else if (sanasto.containsKey(sana)) {
					System.out.println("Sanan \"" + sana + "\" käännös on \"" + sanasto.get(sana) + "\"\n");

				} else if (!sanasto.containsKey(sana)) {
					println("Sanaasi ei löydy sanakirjasta! Palataan takaisin.");

				}

			} else if (valinta == 2) {
				// tämä rakenne tallentaa käyttäjän syötteet muuttujiin ja tallentaa ne sekä
				// tiedostoihin, että hashmapiin
				// koin että tällä tavoin oli helppoa antaa käyttäjän jatkaa sanakirjan käyttöä
				// ja lisätä uusia sanoja
				try {
					FileWriter finWriter = new FileWriter(sanastoSuomi, true);
					FileWriter engWriter = new FileWriter(sanastoEnglanti, true);

					println("Syötä uusi sana suomeksi (palaa takaisin tyhjällä syötteellä)");
					String uusiSu = lukija.nextLine();

					if (uusiSu.isEmpty()) {
						println("Palataan takaisin.");

					} else if (sanasto.containsKey(uusiSu)) {
						println("Syöttämäsi sana löytyy jo sanakirjasta! Palataan takaisin.");

					} else {
						println("Syötä sanan englanninkielinen käännös: ");
						String uusiEng = lukija.nextLine();
						sanasto.put(uusiSu, uusiEng);
						println("");
						finWriter.write("\n" + uusiSu);
						engWriter.write("\n" + uusiEng);
					}

					engWriter.flush();
					engWriter.close();
					finWriter.flush();
					finWriter.close();

				} catch (FileNotFoundException p) {
					println("Tiedostoa ei löytynyt, palataan takaisin.");
				}
			} else if (valinta == 3) {
				System.out.println("\n" + sanasto + "\n");

			} else if (valinta == 4) {
				System.exit(0);
			}
		}
	}
}