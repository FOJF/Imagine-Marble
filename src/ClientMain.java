import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientMain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMain frame = new ClientMain();
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
	public ClientMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		//test
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC0C1\uC0C1\uB9C8\uBE14");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("�������ü", Font.BOLD, 60));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(334, 41, 340, 88);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\sdfsdfsdfsd.png"));
		lblNewLabel.setBounds(342, 150, 324, 260);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\uD604\uC7AC \uC778\uC6D0 / \uCD5C\uB300 \uC778\uC6D0");
		label.setFont(new Font("���� ������ 240", Font.PLAIN, 30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(320, 402, 368, 52);
		contentPane.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("2 / 4");
		lblNewLabel_2.setFont(new Font("���� ������ 240", Font.PLAIN, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(410, 464, 184, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC9C4\uD589 \uC911 (17 \uD134)");
		lblNewLabel_3.setFont(new Font("���� ������ 250", Font.BOLD, 15));
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(420, 510, 174, 36);
		contentPane.add(lblNewLabel_3);
		
		JLabel label_1 = new JLabel("\uB2C9\uB124\uC784");
		label_1.setFont(new Font("���� ������ 240", Font.PLAIN, 20));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(357, 556, 109, 36);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(463, 556, 166, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\uC785\uC7A5");
		btnNewButton.setBounds(434, 640, 140, 46);
		contentPane.add(btnNewButton);
	}
}
