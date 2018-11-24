import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;

public class TekstiEditoriTietoja extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void tietojaTekijasta() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TekstiEditoriTietoja frame = new TekstiEditoriTietoja();
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
	public TekstiEditoriTietoja() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Tekstieditori by Teemu V.");
		label.setFont(new Font("Trebuchet MS", Font.ITALIC, 30));
		label.setBounds(35, 10, 389, 52);
		contentPane.add(label);
		
		Label label_1 = new Label("1601488, Teemu Valonen");
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		label_1.setBounds(35, 56, 156, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("T\u00E4ll\u00E4 sivulla ei oikeastaan ole yht\u00E4\u00E4n mit\u00E4\u00E4n.");
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		label_2.setBounds(35, 84, 259, 22);
		contentPane.add(label_2);
	}
}
