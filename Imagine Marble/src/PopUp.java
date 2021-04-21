import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PopUp extends JFrame {
	JButton Okbtn = new JButton("예");
	JButton Nobtn = new JButton("아니오");
	JLabel LandBuyLabel = new JLabel();
	JLabel Price = new JLabel();


	public PopUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(312, 250, 400, 200);
		getContentPane().setLayout(null);
		
		Okbtn.setBounds(48, 121, 100, 30);
		getContentPane().add(Okbtn);
		Nobtn.setBounds(241, 121, 100, 30);
		
		getContentPane().add(Nobtn);
		LandBuyLabel.setFont(new Font("양재튼튼체B", Font.PLAIN, 15));
		LandBuyLabel.setBounds(12, 24, 400, 39);
		getContentPane().add(LandBuyLabel);

		Price.setFont(new Font("양재튼튼체B", Font.PLAIN, 15));
		Price.setBounds(12, 73, 250, 38);
		
		getContentPane().add(Price);

	}
}