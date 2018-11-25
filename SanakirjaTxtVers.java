import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class SanakirjaTxtVers {

	// println -metodi helpottaa kirjoittamista ja ehkä siistii koodia, mikäli
	// printlineja kertyy.
	private static void println(String Syote) {
		System.out.println(Syote);
	}

	public static void main(String[] args) throws IOException {
		HashMap<String, String> sanasto;

		FileWriter finWriter;
		FileWriter engWriter;
		Scanner lukijaSuomi;
		Scanner lukijaEnglanti;
		File sanastoSuomi = new File("suomeksi.txt");
		File sanastoEnglanti = new File("englanniksi.txt");

		if (sanastoSuomi.createNewFile()) {
			// mikäli ohjeöma ei löydä oikeannimisiä tiedostoja, se luo sellaiset ja tallentaa niihin oletussanaston
			println("Tallennettua sanastoa ei löytynyt, luodaan...");
			sanasto = new HashMap<String, String>();

			// syötetään HashMapiin sanakirjaan sanat ja käännökset
			String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
			String[] englanti = { "cat", "dog", "horse", "car", "boat" };
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
					+ "\n 3 - näytä sanakirjan sisältö" + "\n 4 - tallenna ja lopeta");
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

				println("Syötä uusi sana suomeksi (palaa takaisin tyhjällä syötteellä):\n");
				String uusiSu = lukija.nextLine();

				if (uusiSu.isEmpty()) {
					println("Palataan takaisin.");

				} else if (sanasto.containsKey(uusiSu)) {
					println("Syöttämäsi sana löytyy jo sanakirjasta! Palataan takaisin.");

				} else {
					println("Syötä sanan englanninkielinen käännös:\n");
					String uusiEng = lukija.nextLine();
					sanasto.put(uusiSu, uusiEng);


				}


			} else if (valinta == 3) {
				System.out.println("\n" + sanasto + "\n");

			} else if (valinta == 4) {

				lukija.close();

				// iteraattori käy HashMapin läpi ja tallentaa kaikki sanat yksi kerrallaan kahteen eri tekstitiedostoon
				finWriter = new FileWriter(sanastoSuomi);
				engWriter = new FileWriter(sanastoEnglanti);

				Iterator<Entry<String,String>> tallentaja = sanasto.entrySet().iterator();
				while(tallentaja.hasNext()) {
					HashMap.Entry<String, String> alkio = (HashMap.Entry<String, String>) tallentaja.next();

					finWriter.write(alkio.getKey() + "\n");
					engWriter.write(alkio.getValue()+ "\n");
				}

				engWriter.flush();
				engWriter.close();
				finWriter.flush();
				finWriter.close();
				System.exit(0);
			} else {
				println("Virheellinen syöte. Syötä luku 1-4.");
			}
		}
	}
}