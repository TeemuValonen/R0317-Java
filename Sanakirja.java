import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class Sanakirja {

    public static void vieXML(HashMap<String, String> kirja) throws IOException {
        FileOutputStream fos = new FileOutputStream("sanasto.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.writeObject(kirja);
        encoder.close();
        fos.close();
    }

    private static HashMap<String, String> tuoXML() throws IOException {
        FileInputStream fis = new FileInputStream("sanasto.xml");
        XMLDecoder decoder = new XMLDecoder(fis);
        HashMap<String, String> purettukirja = (HashMap) decoder.readObject();
        decoder.close();
        fis.close();
        return purettukirja;
    }

    // println -metodi helpottaa kirjoittamista ja ehkä siistii koodia, mikäli printlineja kertyy.
    private static void println(String Syote) {
        System.out.println(Syote);
    }

    public static void main(String[] args)  throws IOException{
        String[] suomi = {"kissa", "koira", "hevonen", "auto", "vene"};
        String[] englanti = {"cat", "dog", "horse", "car", "boat"};
        HashMap<String, String> sanasto;

        File kirjasto = new File("sanasto.xml");
        if (kirjasto.createNewFile()) {
            System.out.println("Tallennettua sanakirjaa ei löytynyt, luodaan...");
            sanasto = new HashMap<String, String>();
            // syötetään sanakirjaan sanat ja käännökset

            for (int i = 0; i < suomi.length; i++) {
                sanasto.put(suomi[i], englanti[i]);
            }
        }else {
            System.out.println("Tallennettu sanakirja löytyi, ladataan...");
            sanasto = tuoXML();
        }

        Scanner lukija = new Scanner(System.in);


        println("Sanakirjan sisältö: ");

        System.out.println(sanasto + "\n");

        while (true) {
            println("Valitse toiminto: "
                    + "\n 1 - käännä sana"
                    + "\n 2 - lisää uuden sanan käännös sanakirjaan"
                    + "\n 3 - näytä sanakirjan sisältö"
                    + "\n 4 - lopeta ohjelma");
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
                    println("Sanaasi ei löydy sanakirjasta!");
                    break;
                }

            } else if (valinta == 2) {
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

                }
            } else if (valinta == 3) {
                System.out.println(sanasto);

            } else if (valinta == 4) {
                vieXML(sanasto);
                System.exit(0);
            }
        }
    }
}