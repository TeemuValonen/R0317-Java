import java.awt.BorderLayout;
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
import java.awt.event.InputEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Scanner;

public class Tekstieditori extends JFrame {

	
	
	private JPanel contentPane;
	private static JEditorPane editorPane;

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
		        valintaikkuna.showOpenDialog(null);
		        String rivi = "";
		        String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				Scanner lukija = null;
				File tiedosto = new File(uusiTiedosto);
				
				try {
					lukija = new Scanner(tiedosto);
					
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine()+"\n";
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
		mnMuokkaa.add(mntmEtsi);
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mnMuokkaa.add(mntmKorvaa);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(5, 5, 424, 29);
		contentPane.add(toolBar);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		toolBar.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Cut-Black.png")));
		toolBar.add(button_2);
	}
}
