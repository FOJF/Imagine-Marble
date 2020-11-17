import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ClientMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMain window = new ClientMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\uC8FC\uC0AC\uC704 \uAD74\uB9AC\uAE30");
		btnNewButton.setBounds(434, 466, 140, 40);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("user1");
		lblNewLabel.setBounds(12, 10, 57, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("user2");
		lblNewLabel_1.setBounds(939, 10, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("user3");
		lblNewLabel_2.setBounds(12, 642, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("user4");
		lblNewLabel_3.setBounds(939, 642, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("105012353 \uB9C8\uBE14");
		lblNewLabel_5.setBounds(12, 35, 91, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("105012353 \uB9C8\uBE14");
		lblNewLabel_5_1.setBounds(905, 35, 91, 15);
		frame.getContentPane().add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("105012353 \uB9C8\uBE14");
		lblNewLabel_5_2.setBounds(0, 667, 91, 15);
		frame.getContentPane().add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("105012353 \uB9C8\uBE14");
		lblNewLabel_5_3.setBounds(905, 667, 91, 15);
		frame.getContentPane().add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_4_1 = new JLabel("New label");
		lblNewLabel_4_1.setBounds(204, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("New label");
		lblNewLabel_4_2.setBounds(305, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("New label");
		lblNewLabel_4_3.setBounds(405, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_4_4 = new JLabel("New label");
		lblNewLabel_4_4.setBounds(506, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_4);
		
		JLabel lblNewLabel_4_5 = new JLabel("New label");
		lblNewLabel_4_5.setBounds(606, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_5);
		
		JLabel lblNewLabel_4_6 = new JLabel("New label");
		lblNewLabel_4_6.setBounds(706, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_6);
		
		JLabel lblNewLabel_4_8 = new JLabel("New label");
		lblNewLabel_4_8.setBounds(806, 206, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_8);
		
		JLabel lblNewLabel_4_9 = new JLabel("New label");
		lblNewLabel_4_9.setBounds(806, 307, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_9);
		
		JLabel lblNewLabel_4_10 = new JLabel("New label");
		lblNewLabel_4_10.setBounds(806, 406, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_10);
		
		JLabel lblNewLabel_4_12 = new JLabel("New label");
		lblNewLabel_4_12.setBounds(706, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_12);
		
		JLabel lblNewLabel_4_13 = new JLabel("New label");
		lblNewLabel_4_13.setBounds(606, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_13);
		
		JLabel lblNewLabel_4_14 = new JLabel("New label");
		lblNewLabel_4_14.setBounds(506, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_14);
		
		JLabel lblNewLabel_4_15 = new JLabel("New label");
		lblNewLabel_4_15.setBounds(405, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_15);
		
		JLabel lblNewLabel_4_16 = new JLabel("New label");
		lblNewLabel_4_16.setBounds(305, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_16);
		
		JLabel lblNewLabel_4_17 = new JLabel("New label");
		lblNewLabel_4_17.setBounds(204, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_17);
		
		JLabel lblNewLabel_4_18 = new JLabel("\uC2DC\uC791");
		lblNewLabel_4_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18.setBounds(103, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_18);
		
		JLabel lblNewLabel_4_19 = new JLabel("New label");
		lblNewLabel_4_19.setBounds(103, 406, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_19);
		
		JLabel lblNewLabel_4_21 = new JLabel("New label");
		lblNewLabel_4_21.setBounds(92, 206, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_21);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\3.PNG"));
		lblNewLabel_7.setBounds(546, 307, 50, 50);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("");
		lblNewLabel_7_1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\3.PNG"));
		lblNewLabel_7_1.setBounds(400, 312, 50, 50);
		frame.getContentPane().add(lblNewLabel_7_1);
		

		
		JLabel lblNewLabel_4_18_3 = new JLabel("\uAE30\uB9D0\uACE0\uC0AC");
		lblNewLabel_4_18_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18_3.setBounds(806, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_18_3);
		
		JLabel lblNewLabel_4_18_4 = new JLabel("\uD300\uD504\uB85C\uC81D\uD2B8");
		lblNewLabel_4_18_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18_4.setBounds(103, 307, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_18_4);
	}
}
