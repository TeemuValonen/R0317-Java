package tietokantasovellus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LevytSQL {

	public static void levyTalteen(Levy l‰tty) {
	
		String nimi = l‰tty.getLevynNimi();
		String artisti = l‰tty.getArtisti();
		int vuosi = l‰tty.getJulkaisuVuosi();
		
		try {

			// Luodaan tietokantayhteys
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7267468",
					"sql7267468", "eSF3VubUmD");
			if (con != null) {
				System.out.println("Yhteys muodostettu");
			}

			
			// tallennetaan SQL:n insert-lause valmiiksi muuttujaan
			String sql = "INSERT INTO Levyt(Levy,Artisti,Julkaisuvuosi) values(?,?,?)";

			// Luodaan Statement-olio, joka keskustelee tietokannan kanssa

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nimi);
			stmt.setString(2, artisti);
			stmt.setInt(3, vuosi);

			stmt.execute();
			
			stmt.close();
			con.close();
			
			System.out.println("Uusi tieto on tallennettu");

		} catch (SQLException f) {
			System.out.println(f);
			f.printStackTrace();
		}
		
	}
	
	public static ArrayList<Object[]> kiekotKannasta() {
		ArrayList<Object[]> tiedot = new ArrayList<Object[]>();
		try {

			// Luodaan tietokantayhteys
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7267468",
					"sql7267468", "eSF3VubUmD");
			if (con != null) {
				System.out.println("Yhteys muodostettu");
			}

			// Luodaan Statement-olio, joka keskustelee tietokannan kanssa
			Statement stmt = con.createStatement();

			// Luodaan tulosjoukko, johon sijoitetaan kyselyn tulos
			ResultSet tulokset = stmt
					.executeQuery("SELECT Levy, Artisti, Julkaisuvuosi FROM Levyt ORDER BY Artisti, Julkaisuvuosi");

			// Tulosjoukko k‰yd‰‰n silmukassa l‰pi

			while (tulokset.next()) {
				System.out.println(tulokset.getString(1) + "  " + tulokset.getString(2) + "  " + tulokset.getInt(3));

				tiedot.add(new Object[] { tulokset.getString(1), tulokset.getString(2), tulokset.getString(3) });

			}
			tulokset.close();
			stmt.close();
			con.close();

			// Varaudutaan virheisiin
		} catch (Exception h) {
			System.out.println(h);
		}
		
		
		return tiedot;
	}
	
	public static boolean rieskaRoskiin(Levy rieska) {
		
		try {

			// Luodaan tietokantayhteys
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7267468",
					"sql7267468", "eSF3VubUmD");
			if (con != null) {
				System.out.println("Yhteys muodostettu");
			}

			
			// tallennetaan SQL:n insert-lause valmiiksi muuttujaan
			String sql = "DELETE FROM Levyt WHERE Levy=? AND Artisti=? and Julkaisuvuosi=?";

			// Luodaan Statement-olio, joka keskustelee tietokannan kanssa
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, rieska.getLevynNimi());
			stmt.setString(2, rieska.getArtisti());
			stmt.setInt(3, rieska.getJulkaisuVuosi());

			stmt.execute();
			
			stmt.close();
			con.close();
			

			System.out.println("Levy on poistettu tietokannasta");
			return true;
			
		} catch (SQLException k) {
			System.out.println(k);
			k.printStackTrace();
			return false;
		}
		
	}
	
}