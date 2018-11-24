
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Tekstieditori extends JFrame {

	private JPanel contentPane;
	private JTextField txtEtsiKorvaa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tekstieditori frame = new Tekstieditori();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tekstieditori() {
		JEditorPane editorPane = new JEditorPane();
		txtEtsiKorvaa = new JTextField();
		txtEtsiKorvaa.setText("(Kirjoita t\u00E4h\u00E4n ja klikkaa vasemmalta)");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				try {
				Scanner tiedonLukija = null;
				File tiedosto = new File(uusiTiedosto);
				tiedonLukija = new Scanner(tiedosto);
				
				while (tiedonLukija.hasNextLine()) {
					rivi += tiedonLukija.nextLine() + "\n";
					System.out.println(rivi);
				}
				
				} catch (FileNotFoundException p) {
					System.out.println("Tiedostoasi ei löytynyt.");
				}
				
				editorPane.setText(rivi);
			}
		});
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				System.out.println("Kirjoitettava tiedosto: " +uusiTiedosto);
				
				try {
					PrintWriter kirjoittaja = new PrintWriter(uusiTiedosto);
					String sisalto = editorPane.getText();
					
					kirjoittaja.println(sisalto);
					
					kirjoittaja.flush();
					kirjoittaja.close();
				} catch (Exception e1) {
					System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
					e1.printStackTrace();
				}
				
			}
		});
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmTallenna);
		
		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmLopeta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnTiedosto.add(mntmLopeta);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JMenuItem mntmListietojaSovelluksesta = new JMenuItem("Lis\u00E4tietoja sovelluksesta");
		mntmListietojaSovelluksesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TekstiEditoriTietoja uusi = new TekstiEditoriTietoja();
				
				uusi.tietojaTekijasta();

			}
		});
		mntmListietojaSovelluksesta.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		mnTietoja.add(mntmListietojaSovelluksesta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		editorPane.setBounds(0, 44, 434, 346);
		contentPane.add(editorPane);
		txtEtsiKorvaa.setBounds(148, 0, 250, 42);
		contentPane.add(txtEtsiKorvaa);
		txtEtsiKorvaa.setColumns(10);
		
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/IncreaseIndent_16x16_JFX.png")));
		mntmEtsi.setBounds(10, 0, 128, 22);
		contentPane.add(mntmEtsi);
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sisalto = editorPane.getText();
				
				String haettava = txtEtsiKorvaa.getText();
				int indeksi = sisalto.indexOf(haettava);
				System.out.println("Indeksi: "+indeksi);
				
				editorPane.setSelectionColor(Color.CYAN);
				
				editorPane.setSelectionStart(indeksi);
				editorPane.setSelectionEnd(indeksi + haettava.length());
				
			}
		});
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		mntmKorvaa.setBounds(10, 21, 128, 21);
		contentPane.add(mntmKorvaa);
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uusiTeksti = txtEtsiKorvaa.getText();
				
				editorPane.replaceSelection(uusiTeksti);
				
			}
		});
		mntmKorvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
	}
}
