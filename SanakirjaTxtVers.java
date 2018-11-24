import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class SanakirjaTxtVers {

	// println -metodi helpottaa kirjoittamista ja ehk� siistii koodia, mik�li
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
			println("Tallennettua sanastoa ei l�ytynyt, luodaan...");
			sanasto = new HashMap<String, String>();
			// sy�tet��n sanakirjaan sanat ja k��nn�kset
			for (int i = 0; i < suomi.length; i++) {
				sanasto.put(suomi[i], englanti[i]);
			}

		} else {
			System.out.println("Tallennettu sanakirja l�ytyi, ladataan...");

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

		println("Sanakirjan sis�lt�: ");
		System.out.println(sanasto + "\n");

		while (true) {
			println("Valitse toiminto: " + "\n 1 - k��nn� sana" + "\n 2 - lis�� uuden sanan k��nn�s sanakirjaan"
					+ "\n 3 - n�yt� sanakirjan sis�lt�" + "\n 4 - lopeta ohjelma");
			int valinta = lukija.nextInt();
			lukija.nextLine();

			if (valinta == 1) {
				println("Sy�t� k��nnett�v� sana (tyhj� sy�te palauttaa valikkoon)");
				String sana = lukija.nextLine();

				if ((lukija.hasNextLine()) && (sana.isEmpty())) {
					println("Palataan takaisin.");

				} else if (sanasto.containsKey(sana)) {
					System.out.println("Sanan \"" + sana + "\" k��nn�s on \"" + sanasto.get(sana) + "\"\n");

				} else if (!sanasto.containsKey(sana)) {
					println("Sanaasi ei l�ydy sanakirjasta! Palataan takaisin.");

				}

			} else if (valinta == 2) {
				// t�m� rakenne tallentaa k�ytt�j�n sy�tteet muuttujiin ja tallentaa ne sek�
				// tiedostoihin, ett� hashmapiin
				// koin ett� t�ll� tavoin oli helppoa antaa k�ytt�j�n jatkaa sanakirjan k�ytt��
				// ja lis�t� uusia sanoja
				try {
					FileWriter finWriter = new FileWriter(sanastoSuomi, true);
					FileWriter engWriter = new FileWriter(sanastoEnglanti, true);

					println("Sy�t� uusi sana suomeksi (palaa takaisin tyhj�ll� sy�tteell�)");
					String uusiSu = lukija.nextLine();

					if (uusiSu.isEmpty()) {
						println("Palataan takaisin.");

					} else if (sanasto.containsKey(uusiSu)) {
						println("Sy�tt�m�si sana l�ytyy jo sanakirjasta! Palataan takaisin.");

					} else {
						println("Sy�t� sanan englanninkielinen k��nn�s: ");
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
					println("Tiedostoa ei l�ytynyt, palataan takaisin.");
				}
			} else if (valinta == 3) {
				System.out.println("\n" + sanasto + "\n");

			} else if (valinta == 4) {
				System.exit(0);
			}
		}
	}
}