import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.InputEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Scanner;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;

public class Tekstieditori extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * @wbp.nonvisual location=-48,99
	 */
	private final JPopupMenu popupMenu = new JPopupMenu();

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);

		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.setApproveButtonText("Avaa tiedosto");
				valintaikkuna.setDialogTitle("Valitse avattava tiedosto");
				valintaikkuna.showOpenDialog(null);
				
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();

				try {
					Scanner lukija = null;
					File tiedosto = new File(uusiTiedosto);

					lukija = new Scanner(tiedosto);

					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine() + "\n";
						System.out.println(rivi);
					}

				} catch (FileNotFoundException p) {
					System.out.println("Tiedostoa ei löydy");
				}
				editorPane.setText(rivi);

			}
		});
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);

		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.setApproveButtonText("Tallenna tiedosto");
				valintaikkuna.setDialogTitle("Valitse tallennuspaikka");
				valintaikkuna.showSaveDialog(null);
				
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();

				System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);

				try {
					PrintWriter writer = new PrintWriter(uusiTiedosto);
					String sisalto = editorPane.getText();

					writer.println(sisalto);
					
					writer.flush();
					writer.close();

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
		mnTiedosto.add(mntmLopeta);

		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mnTiedosto.add(mntmSulje);

		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);

		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Scanner lukija = new Scanner(System.in);				
				
				String haettava = lukija.next();
				
				String sisalto = editorPane.getText();
				sisalto = sisalto.toLowerCase();
				

				int indeksi = sisalto.indexOf(haettava);
				
				System.out.println("Indeksi: "+indeksi);
				
				editorPane.setSelectionColor(Color.MAGENTA);
				
				editorPane.setSelectionStart(indeksi);
				editorPane.setSelectionEnd( indeksi + haettava.length() );
				
			}
		});
		mnMuokkaa.add(mntmEtsi);

		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mnMuokkaa.add(mntmKorvaa);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				String haettava = textField.getText();
				
				String sisalto = editorPane.getText();
				sisalto = sisalto.toLowerCase();
				

				int indeksi = sisalto.indexOf(haettava);
				
				System.out.println("Indeksi: "+indeksi);
				
				editorPane.setSelectionColor(Color.MAGENTA);
				
				editorPane.setSelectionStart(indeksi);
				editorPane.setSelectionEnd( indeksi + haettava.length() );
				
			}
		});
		menuBar.add(textField);
		textField.setColumns(10);

		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);

		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 429, 34);
		contentPane.add(toolBar);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(button);

		JButton button_1 = new JButton("");
		button_1.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		toolBar.add(button_1);

		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(
				Tekstieditori.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Cut-Black.png")));
		toolBar.add(button_2);
		
		editorPane.setBounds(0, 32, 434, 200);
		contentPane.add(editorPane);
	}
}