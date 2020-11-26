import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class ClientInGame extends JFrame {

	private String UserName;
	private String SendIpAddr;
	private String SendPort;
	private String RoomNumber;

	private Socket socket; // ¿¬°á¼ÒÄÏ
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private JLabel Dice1 = new JLabel("");
	private JLabel Dice2 = new JLabel("");

	public ClientInGame(String username, String ip_addr, String port_no, String roomnumber, Socket SOCKET,
			ObjectOutputStream OOS, ObjectInputStream OIS) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		getContentPane().setLayout(null);

		UserName = username;
		SendIpAddr = ip_addr;
		SendPort = port_no;
		RoomNumber = roomnumber;
		socket = SOCKET;
		oos = OOS;
		ois = OIS;

		JButton btnRoll = new JButton("\uC8FC\uC0AC\uC704 \uAD74\uB9AC\uAE30");
		btnRoll.setFont(new Font("¹®Ã¼ºÎ Á¦¸ñ µ¸À½Ã¼", Font.BOLD, 15));
		btnRoll.setBounds(434, 466, 140, 40);
		getContentPane().add(btnRoll);
		Rollaction ra = new Rollaction();
		btnRoll.addActionListener(ra);

		JLabel lblNewLabel = new JLabel(username);
		lblNewLabel.setBounds(12, 10, 57, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("user2");
		lblNewLabel_1.setBounds(939, 10, 57, 15);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("user3");
		lblNewLabel_2.setBounds(12, 642, 57, 15);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("user4");
		lblNewLabel_3.setBounds(939, 642, 57, 15);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("105012353 \uB9C8\uBE14");
		lblNewLabel_5.setBounds(12, 35, 91, 15);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("105012353 \uB9C8\uBE14");
		lblNewLabel_5_1.setBounds(905, 35, 91, 15);
		getContentPane().add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_2 = new JLabel("105012353 \uB9C8\uBE14");
		lblNewLabel_5_2.setBounds(0, 667, 91, 15);
		getContentPane().add(lblNewLabel_5_2);

		JLabel lblNewLabel_5_3 = new JLabel("105012353 \uB9C8\uBE14");
		lblNewLabel_5_3.setBounds(905, 667, 91, 15);
		getContentPane().add(lblNewLabel_5_3);

		JLabel lblNewLabel_4_1 = new JLabel("\uBBF8\uB798\uAD00");
		lblNewLabel_4_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setBounds(204, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("\uCC3D\uC758\uAD00");
		lblNewLabel_4_2.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setBounds(305, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_3 = new JLabel("\uC778\uC131\uAD00");
		lblNewLabel_4_3.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3.setBounds(405, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_3);

		JLabel lblNewLabel_4_4 = new JLabel("\uC0C1\uC0C1\uBE4C\uB9AC\uC9C0");
		lblNewLabel_4_4.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_4.setBounds(506, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_4);

		JLabel lblNewLabel_4_5 = new JLabel("\uD0D0\uAD6C\uAD00");
		lblNewLabel_4_5.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_5.setBounds(606, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_5);

		JLabel lblNewLabel_4_6 = new JLabel("\uD559\uC1A1\uAD00");
		lblNewLabel_4_6.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_6.setBounds(706, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_6);

		JLabel lblNewLabel_4_8 = new JLabel("\uACF5\uD559\uAD00");
		lblNewLabel_4_8.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_8.setBounds(806, 206, 100, 100);
		getContentPane().add(lblNewLabel_4_8);

		JLabel lblNewLabel_4_9 = new JLabel("\uC9C4\uB9AC\uAD00");
		lblNewLabel_4_9.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_9.setBounds(806, 312, 100, 100);
		getContentPane().add(lblNewLabel_4_9);

		JLabel lblNewLabel_4_10 = new JLabel("\uC9C0\uC120\uAD00");
		lblNewLabel_4_10.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_10.setBounds(806, 406, 100, 100);
		getContentPane().add(lblNewLabel_4_10);
		JLabel lblNewLabel_4_12 = new JLabel("\uC5F0\uAD6C\uAD00");
		lblNewLabel_4_12.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_12.setBounds(706, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_12);

		JLabel lblNewLabel_4_13 = new JLabel("\uC6B0\uCD0C\uAD00");
		lblNewLabel_4_13.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_13.setBounds(606, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_13);

		JLabel lblNewLabel_4_14 = new JLabel("\uD559\uAD70\uB2E8");
		lblNewLabel_4_14.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_14.setBounds(506, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_14);

		JLabel lblNewLabel_4_15 = new JLabel("\uB099\uC0B0\uACF5\uC6D0");
		lblNewLabel_4_15.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_15.setBounds(405, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_15);

		JLabel lblNewLabel_4_16 = new JLabel("\uC0C1\uC0C1\uAD00");
		lblNewLabel_4_16.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_16.setBounds(305, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_16);

		JLabel lblNewLabel_4_17 = new JLabel("\uD6C4\uBB38");
		lblNewLabel_4_17.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_17.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_17.setBounds(204, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_17);

		JLabel lblNewLabel_4_18 = new JLabel("\uC2DC\uC791");
		lblNewLabel_4_18.setForeground(Color.RED);
		lblNewLabel_4_18.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 30));
		lblNewLabel_4_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18.setBounds(103, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_18);

		JLabel lblNewLabel_4_19 = new JLabel("\uC815\uBB38");
		lblNewLabel_4_19.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_19.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_19.setBounds(103, 406, 100, 100);
		getContentPane().add(lblNewLabel_4_19);

		JLabel lblNewLabel_4_21 = new JLabel("\uB099\uC0B0\uAD00");
		lblNewLabel_4_21.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_21.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_21.setBounds(103, 206, 100, 100);
		getContentPane().add(lblNewLabel_4_21);

		// JLabel Dice1 = new JLabel("");
		Dice1.setIcon(new ImageIcon("Images/6.PNG"));
		Dice1.setBounds(545, 312, 50, 50);
		getContentPane().add(Dice1);

		// JLabel Dice2 = new JLabel("");
		Dice2.setIcon(new ImageIcon("Images/6.PNG"));
		Dice2.setBounds(400, 312, 50, 50);
		getContentPane().add(Dice2);

		JLabel lblNewLabel_4_18_1 = new JLabel("\uB300\uB3D9\uC81C");
		lblNewLabel_4_18_1.setForeground(Color.RED);
		lblNewLabel_4_18_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 30));
		lblNewLabel_4_18_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18_1.setBounds(806, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_18_1);

		JLabel lblNewLabel_4_18_3 = new JLabel("\uAE30\uB9D0\uACE0\uC0AC");
		lblNewLabel_4_18_3.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_18_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18_3.setBounds(806, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_18_3);

		JLabel lblNewLabel_4_18_4 = new JLabel("\uD140\uD504\uB85C\uC81D\uD2B8");
		lblNewLabel_4_18_4.setForeground(Color.WHITE);
		lblNewLabel_4_18_4.setFont(new Font("±Ã¼­", Font.BOLD, 18));
		lblNewLabel_4_18_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_18_4.setBounds(103, 306, 100, 100);
		getContentPane().add(lblNewLabel_4_18_4);

		JLabel lblNewLabel_4_1_1 = new JLabel("\uC911\uAC04\uACE0\uC0AC");
		lblNewLabel_4_1_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setBounds(103, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_17_1 = new JLabel("");
		lblNewLabel_4_17_1.setIcon(new ImageIcon("Images/Start.png"));
		lblNewLabel_4_17_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_17_1.setBounds(103, 505, 100, 100);
		getContentPane().add(lblNewLabel_4_17_1);

		JLabel lblNewLabel_4_17_2 = new JLabel("");
		lblNewLabel_4_17_2.setIcon(new ImageIcon("Images/bono.jpg"));
		lblNewLabel_4_17_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_17_2.setBounds(103, 306, 100, 100);
		getContentPane().add(lblNewLabel_4_17_2);

		JLabel lblNewLabel_4_8_1 = new JLabel("");
		lblNewLabel_4_8_1.setIcon(new ImageIcon("Images/boogie2.jpg"));
		lblNewLabel_4_8_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_8_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 20));
		lblNewLabel_4_8_1.setBounds(806, 106, 100, 100);
		getContentPane().add(lblNewLabel_4_8_1);

		JButton btnOutButton = new JButton("\uB098\uAC00\uAE30");
		btnOutButton.setBounds(609, 642, 140, 44);
		getContentPane().add(btnOutButton);
		Out out = new Out();
		btnOutButton.addActionListener(out);
		setVisible(true);

	}

	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		// AppendIcon(icon1);
		msg = msg.trim(); // ¾ÕµÚ blank¿Í \nÀ» Á¦°ÅÇÑ´Ù.
		/*
		 * int len = textArea.getDocument().getLength(); // ³¡À¸·Î ÀÌµ¿
		 * textArea.setCaretPosition(len); textArea.replaceSelection(msg + "\n");
		 */
	}

	public void SendChatMsg(ChatMsg obj) {
		try {
			oos.writeObject(obj.code);
			oos.writeObject(obj.UserName);
			oos.writeObject(obj.data);
			if (obj.code.equals("300")) { // ÀÌ¹ÌÁö Ã·ºÎ ÀÖ´Â °æ¿ì
				oos.writeObject(obj.imgbytes);
			}
			oos.flush();
		} catch (IOException e) {
			AppendText("SendChatMsg Error");
			e.printStackTrace();
			try {
				oos.close();
				socket.close();
				ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// textArea.append("¸Þ¼¼Áö ¼Û½Å ¿¡·¯!!\n");
			// System.exit(0);
		}
	}

	public ChatMsg ReadChatMsg() {
		Object obj = null;
		String msg = null;
		ChatMsg cm = new ChatMsg("", "", "");
		// Android¿Í È£È¯¼ºÀ» À§ÇØ °¢°¢ÀÇ Field¸¦ µû·Îµû·Î ÀÐ´Â´Ù.

		try {
			obj = ois.readObject();
			cm.code = (String) obj;
			obj = ois.readObject();
			cm.UserName = (String) obj;
			obj = ois.readObject();
			cm.data = (String) obj;
			if (cm.code.equals("300")) {
				obj = ois.readObject();
				cm.imgbytes = (byte[]) obj;
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			AppendText("ReadChatMsg Error");
			e.printStackTrace();
			try {
				oos.close();
				socket.close();
				ois.close();
				socket = null;
				return null;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					oos.close();
					socket.close();
					ois.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				socket = null;
				return null;
			}

			// textArea.append("¸Þ¼¼Áö ¼Û½Å ¿¡·¯!!\n");
			// System.exit(0);
		}

		return cm;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	class Rollaction implements ActionListener // ³»ºÎÅ¬·¡½º·Î ¾×¼Ç ÀÌº¥Æ® Ã³¸® Å¬·¡½º
	{
		int time = 0;
		int num1 = 0;
		int num2 = 0;
		Random rd = new Random();

		@Override
		public void actionPerformed(ActionEvent e) {
			num1 = rd.nextInt(6) + 1;
			num2 = rd.nextInt(6) + 1;
			System.out.println(num1 + " " + num2);
			Dice1.setIcon(new ImageIcon("Images/" + num1 + ".PNG"));
			Dice2.setIcon(new ImageIcon("Images/" + num2 + ".PNG"));

		}
	}

	class Out implements ActionListener // ³»ºÎÅ¬·¡½º·Î ¾×¼Ç ÀÌº¥Æ® Ã³¸® Å¬·¡½º
	{
		public void actionPerformed(ActionEvent e) {

			ChatMsg obcm1 = new ChatMsg(UserName, "601", RoomNumber);
			// SendChatMsg(obcm1);

			ClientGameRoom cgr = new ClientGameRoom(UserName, SendIpAddr, SendPort);
			dispose();
			SendChatMsg(obcm1);
		}

	}

}
