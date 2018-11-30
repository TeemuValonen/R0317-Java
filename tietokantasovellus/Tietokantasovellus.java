package tietokantasovellus;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Tietokantasovellus extends JFrame {
	private JPanel contentPane;
	private JTable taulu;
	private DefaultTableModel malli;
	private JScrollPane scrollPane;
	
	// Luodaan tietorakenne, jonne tietokannasta haetut rivit voidaan tallentaa
	private static ArrayList<Object[]> tiedot = new ArrayList<Object[]>();

	public Tietokantasovellus() throws ParseException {

		// GUI:n alustaminen
		setTitle("Levytietokanta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		scrollPane = new JScrollPane(taulu);

		// taulukon alustaminen

		malli = new DefaultTableModel();
		malli.addColumn("Levyn nimi");
		malli.addColumn("Artisti");
		malli.addColumn("Julkaisuvuosi");
		contentPane.setLayout(null);

		taulu = new JTable(malli);

		scrollPane.setBounds(0, 0, 434, 250);
		scrollPane.setPreferredSize(new Dimension(275, 250));
		contentPane.add(scrollPane);

		Label label = new Label("Levyn nimi: ");
		label.setBounds(10, 256, 87, 23);
		contentPane.add(label);

		Label label_1 = new Label("Artisti: ");
		label_1.setBounds(10, 285, 87, 23);
		contentPane.add(label_1);

		Label label_2 = new Label("Julkaisuvuosi: ");
		label_2.setBounds(10, 314, 87, 23);
		contentPane.add(label_2);

		JTextField txtLevy = new JTextField();
		txtLevy.setBounds(100, 256, 250, 23);
		contentPane.add(txtLevy);
		txtLevy.setColumns(10);

		JTextField txtArtisti = new JTextField();
		txtArtisti.setBounds(100, 285, 250, 23);
		contentPane.add(txtArtisti);
		txtArtisti.setColumns(10);

		JTextField txtVuosi = new JTextField();
		txtVuosi.setBounds(100, 314, 100, 23);
		contentPane.add(txtVuosi);
		txtVuosi.setColumns(10);

		JButton btnUusiLevy = new JButton("Lisää uusi levy");
		btnUusiLevy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				//varaudutaan tyhjiin syötteisiin
				if ((txtLevy.getText().equals(null)) || (txtLevy.getText().equals("")) || (txtLevy.getText().equals(" "))) {
					JPanel varmistus = new JPanel();

					JTextPane varoitus = new JTextPane();
					varoitus.setText("Oletko varma että syötit levyn nimen?");
					varmistus.add(varoitus);

					JOptionPane.showMessageDialog(null, varmistus, "Syötä levyn nimi!",
							JOptionPane.ERROR_MESSAGE);
				} else if ((txtArtisti.getText().equals(null)) || (txtArtisti.getText().equals("")) || (txtArtisti.getText().equals(" "))) {
					JPanel varmistus = new JPanel();

					JTextPane varoitus = new JTextPane();
					varoitus.setText("Oletko varma että syötit artistin nimen?");
					varmistus.add(varoitus);

					JOptionPane.showMessageDialog(null, varmistus, "Syötä artistin nimi!",
							JOptionPane.ERROR_MESSAGE);
				} else if ((txtVuosi.getText().equals(null))|| (txtVuosi.getText().equals("")) || (txtVuosi.getText().equals(" "))) {
					JPanel varmistus = new JPanel();

					JTextPane varoitus = new JTextPane();
					varoitus.setText("Oletko varma että syötit julkaisuvuoden?");
					varmistus.add(varoitus);

					JOptionPane.showMessageDialog(null, varmistus, "Syötä julkaisuvuosi!",
							JOptionPane.ERROR_MESSAGE);
					
				} else {
					
				// tallennetaan tekstikenttien syötteet
				String uusiLevy = txtLevy.getText();
				String uusiArtisti = txtArtisti.getText();

				// JTextFieldin getText() palauttaa vain String-muuttujan, joten se tulee parsia
				int intVuosi = Integer.parseInt(txtVuosi.getText());
				
				Levy uusiKiekko = new Levy(uusiLevy, uusiArtisti, intVuosi);

				
				// kutsutaan metodia LevytSQL -luokasta, joka on vastuussa ohjelman kaikista tietokantatoiminnoista
				LevytSQL.levyTalteen(uusiKiekko);

				// lisätään uusi levy arraylistiin
				tiedot.add(new Object[] { uusiKiekko.getLevynNimi(), uusiKiekko.getArtisti(), uusiKiekko.getJulkaisuVuosi() });
				System.out.println(uusiKiekko.toString());

				tulostaTaulu();
				
				}
			}
		});
		setVisible(true);
		btnUusiLevy.setBounds(12, 351, 196, 26);
		contentPane.add(btnUusiLevy);

		JButton btnPoistaLevy = new JButton("Poista valittu levy");
		btnPoistaLevy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// tallennetaan halutun rivin numero muuttujaan
				int valittuRivi = taulu.getSelectedRow();
				
				// tallennetaan rivin kolumnien tiedot erillisiin muuttujiin ja käytetään uuden olion parametreinä
				String poistettavanNimi = taulu.getValueAt(valittuRivi, 0).toString();
				String poistettavaArtisti = taulu.getValueAt(valittuRivi, 1).toString();
				String stringVuosi = taulu.getValueAt(valittuRivi, 2).toString();
				int poistuvaVuosi = Integer.parseInt(stringVuosi);
				
				Levy huonoKiekko = new Levy(poistettavanNimi, poistettavaArtisti,poistuvaVuosi);
				
				
				// avataan vielä varoittava valintaikkuna, jottei käyttäjä poista kannasta tetoa vahingossa
				JPanel varmistus = new JPanel();

				JTextPane varoitus = new JTextPane();
				varoitus.setText("Oletko varma että haluat poistaa valitun levyn tietokannasta?");
				varmistus.add(varoitus);

				int vastaus = JOptionPane.showConfirmDialog(null, varmistus, "Poista levy: ",
						JOptionPane.YES_NO_OPTION);
				
				
				/* mikäli käyttäjä vastaa "kyllä", kutsutaan taas LevytSQL -luokasta metodia. Tällä kertaa metodi
				   palauttaa boolean-arvon, mikäli rivin poisto tietokannasta onnistuu. 
				   Näin JTablesta ei poisteta turhaan */
				if (vastaus == JOptionPane.YES_OPTION) {
					if (LevytSQL.rieskaRoskiin(huonoKiekko)) {
			
						malli.removeRow(valittuRivi);
						
					} else {
						
						varmistus = new JPanel();
						varoitus = new JTextPane();
						varoitus.setText("Jotain meni pieleen; tietoa ei poistettu."); 
						JOptionPane.showMessageDialog(null, varmistus, "Hupsista", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnPoistaLevy.setBounds(226, 351, 196, 26);
		contentPane.add(btnPoistaLevy);

		JButton btnShowAll = new JButton("Lataa levyt tietokannasta");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * Kutsutaan metodia, joka palauttaa ArrayListinä tietokantahaun tulokset. Sen
				 * jälkeen tiedot lisätään JTable-komponenttiin.
				 */
				tiedot = LevytSQL.kiekotKannasta();
				
				tulostaTaulu();

			}
		});
		menuBar.add(btnShowAll);

		JButton btnLopeta = new JButton("Lopeta");
		btnLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(btnLopeta);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			Tietokantasovellus ikkuna = new Tietokantasovellus();

			ikkuna.setSize(new Dimension(450, 450));
			ikkuna.setPreferredSize(new Dimension(450, 450));
			ikkuna.setVisible(true);

		} catch (Exception g) {
			g.printStackTrace();
		}

	}
	
	private void tulostaTaulu () {
			
		// nollataan taulu (mikäli levyt halutaan ladata uudestaan, tämä päivittää näkymän)
		malli.setRowCount(0);
			
		// Lisätään levyt JTableen for-loopilla
		for (int i = 0; i < tiedot.size(); i++) {
			
		malli.addRow(tiedot.get(i));

		scrollPane.setViewportView(taulu);
		}
			
	}
		
}