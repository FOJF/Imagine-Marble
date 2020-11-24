import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class ClientInGame extends JFrame {

	private JFrame frame;
	private JLabel Dice1 = new JLabel("");
	private JLabel Dice2 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private String ip_addr;
			private String port_no;
			private String username;

			public void run() {
				try {
					ClientInGame window = new  ClientInGame(username, ip_addr,port_no);
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
	public  ClientInGame(String username, String ip_addr, String port_no) {
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
		
		JButton btnRoll = new JButton("\uC8FC\uC0AC\uC704 \uAD74\uB9AC\uAE30");
		btnRoll.setFont(new Font("¹®Ã¼ºÎ Á¦¸ñ µ¸À½Ã¼", Font.BOLD, 15));
		btnRoll.setBounds(434, 466, 140, 40);
		frame.getContentPane().add(btnRoll);
		Rollaction ra = new Rollaction();
		btnRoll.addActionListener(ra);
		
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
		
		JLabel lblNewLabel_4_1 = new JLabel("\uBBF8\uB798\uAD00");
		lblNewLabel_4_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setBounds(204, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("\uCC3D\uC758\uAD00");
		lblNewLabel_4_2.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setBounds(305, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("\uC778\uC131\uAD00");
		lblNewLabel_4_3.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3.setBounds(405, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_4_4 = new JLabel("\uC0C1\uC0C1\uBE4C\uB9AC\uC9C0");
		lblNewLabel_4_4.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_4.setBounds(506, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_4);
		
		JLabel lblNewLabel_4_5 = new JLabel("\uD0D0\uAD6C\uAD00");
		lblNewLabel_4_5.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_5.setBounds(606, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_5);
		
		JLabel lblNewLabel_4_6 = new JLabel("\uD559\uC1A1\uAD00");
		lblNewLabel_4_6.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_6.setBounds(706, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_6);
		
		JLabel lblNewLabel_4_8 = new JLabel("\uACF5\uD559\uAD00");
		lblNewLabel_4_8.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_8.setBounds(806, 206, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_8);
		
		JLabel lblNewLabel_4_9 = new JLabel("\uC9C4\uB9AC\uAD00");
		lblNewLabel_4_9.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_9.setBounds(806, 306, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_9);
		
		JLabel lblNewLabel_4_10 = new JLabel("\uC9C0\uC120\uAD00");
		lblNewLabel_4_10.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_10.setBounds(806, 406, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_10);
		JLabel lblNewLabel_4_12 = new JLabel("\uC5F0\uAD6C\uAD00");
		lblNewLabel_4_12.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_12.setBounds(706, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_12);
		
		JLabel lblNewLabel_4_13 = new JLabel("\uC6B0\uCD0C\uAD00");
		lblNewLabel_4_13.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_13.setBounds(606, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_13);
		
		JLabel lblNewLabel_4_14 = new JLabel("\uD559\uAD70\uB2E8");
		lblNewLabel_4_14.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_14.setBounds(506, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_14);
		
		JLabel lblNewLabel_4_15 = new JLabel("\uB099\uC0B0\uACF5\uC6D0");
		lblNewLabel_4_15.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_15.setBounds(405, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_15);
		
		JLabel lblNewLabel_4_16= new JLabel("\uC0C1\uC0C1\uAD00");
		lblNewLabel_4_16.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_16.setBounds(305, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_16);
		
		JLabel lblNewLabel_4_17 = new JLabel("\uD6C4\uBB38");
		lblNewLabel_4_17.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_17.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_17.setBounds(204, 505, 100,100);
		frame.getContentPane().add(lblNewLabel_4_17);
		
		JLabel lblNewLabel_4_18 = new JLabel("\uC2DC\uC791");
		lblNewLabel_4_18.setForeground(Color.RED);
		lblNewLabel_4_18.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 30));
		lblNewLabel_4_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18.setBounds(103, 505,100,100);
		frame.getContentPane().add(lblNewLabel_4_18);
		
		JLabel lblNewLabel_4_19 = new JLabel("\uC815\uBB38");
		lblNewLabel_4_19.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_19.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_19.setBounds(103, 406, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_19);
		
		JLabel lblNewLabel_4_21 = new JLabel("\uB099\uC0B0\uAD00");
		lblNewLabel_4_21.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_21.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_21.setBounds(103, 206, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_21);
		
		//JLabel Dice1 = new JLabel("");
		Dice1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\6.PNG"));
		Dice1.setBounds(545, 312, 50, 50);
		frame.getContentPane().add(Dice1);
		
		//JLabel Dice2 = new JLabel("");
		Dice2.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\1.PNG"));
		Dice2.setBounds(400, 312, 50, 50);
		frame.getContentPane().add(Dice2);
		
		JLabel lblNewLabel_4_18_1 = new JLabel("\uB300\uB3D9\uC81C");
		lblNewLabel_4_18_1.setForeground(Color.RED);
		lblNewLabel_4_18_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 30));
		lblNewLabel_4_18_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18_1.setBounds(806, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_18_1);
		
		JLabel lblNewLabel_4_18_3 = new JLabel("\uAE30\uB9D0\uACE0\uC0AC");
		lblNewLabel_4_18_3.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_18_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18_3.setBounds(806, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_18_3);
		
		JLabel lblNewLabel_4_18_4 = new JLabel("\uD140\uD504\uB85C\uC81D\uD2B8");
		lblNewLabel_4_18_4.setForeground(Color.WHITE);
		lblNewLabel_4_18_4.setFont(new Font("±Ã¼­", Font.BOLD, 18));
		lblNewLabel_4_18_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18_4.setBounds(103, 306, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_18_4);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("\uC911\uAC04\uACE0\uC0AC");
		lblNewLabel_4_1_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setBounds(103, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_17_1 = new JLabel("");
		lblNewLabel_4_17_1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\Start.png"));
		lblNewLabel_4_17_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_17_1.setBounds(103, 505, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_17_1);
		
		JLabel lblNewLabel_4_17_2 = new JLabel("");
		lblNewLabel_4_17_2.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\bono.jpg"));
		lblNewLabel_4_17_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_17_2.setBounds(103, 306, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_17_2);
		
		JLabel lblNewLabel_4_8_1 = new JLabel("");
		lblNewLabel_4_8_1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\boogie2.jpg"));
		lblNewLabel_4_8_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_8_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_8_1.setBounds(806, 106, 100, 100);
		frame.getContentPane().add(lblNewLabel_4_8_1);
		setVisible(true);
		
	}
	class Rollaction implements ActionListener // ³»ºÎÅ¬·¡½º·Î ¾×¼Ç ÀÌº¥Æ® Ã³¸® Å¬·¡½º
	{
		int time=0;
		int num1=0;
		int num2=0;
		Random rd1 = new Random();
		Random rd2 = new Random();
		@Override
		public void actionPerformed(ActionEvent e) {

				num1 = rd1.nextInt(5)+1;
				num2 = rd1.nextInt(5)+1;
				System.out.println(num1+" "+num2);
				
				if(num1 == 1)
					Dice1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\1.PNG"));
				if(num1 == 2)
					Dice1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\2.PNG"));
				if(num1 == 3)
					Dice1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\3.PNG"));
				if(num1 == 4)
					Dice1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\4.PNG"));
				if(num1 == 5)
					Dice1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\5.PNG"));
				if(num1 == 6)
					Dice1.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\6.PNG"));
				if(num2 == 1)
					Dice2.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\1.PNG"));
				if(num2 == 2)
					Dice2.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\2.PNG"));
				if(num2 == 3)
					Dice2.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\3.PNG"));
				if(num2 == 4)
					Dice2.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\4.PNG"));
				if(num2 == 5)
					Dice2.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\5.PNG"));
				if(num2 == 6)
					Dice2.setIcon(new ImageIcon("C:\\Users\\PJC\\Desktop\\Imagine-Marble\\Images\\6.PNG"));
					
				try {
					time++;
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			
		}
	}
}
