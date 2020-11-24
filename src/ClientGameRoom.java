import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientGameRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtInput;
	private String UserName;
	private JButton btnSend;
	private static final int BUF_LEN = 128; // Windows Ã³·³ BUF_LEN À» Á¤ÀÇ
	private Socket socket; // ¿¬°á¼ÒÄÏ
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtIpAdress;
	private JTextField txtPortNumber;

	/**
	 * Create the frame.
	 */
	public ClientGameRoom(String username,String ip_addr,String port_no) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(username);
		lblNewLabel.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 200, 32);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uB2D8 \uC548\uB155\uD558\uC138\uC694!");
		lblNewLabel_1.setFont(new Font("ÇÑÄÄ À±°íµñ 240", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(200, 0, 164, 32);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("1\uBC88\uBC29");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(12, 135, 400, 54);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("\uBC29\uBAA9\uB85D");
		lblNewLabel_2_2.setFont(new Font("ÇÑÄÄ ¼Ò¸Á B", Font.PLAIN, 25));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setBounds(404, 65, 200, 50);
		getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("2\uBC88\uBC29");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(596, 135, 400, 54);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_3 = new JLabel("3\uBC88\uBC29");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel_2_3.setBounds(12, 387, 400, 54);
		getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("4\uBC88\uBC29");
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel_2_4.setBounds(596, 387, 400, 54);
		getContentPane().add(lblNewLabel_2_4);
		contentPane = new JPanel();
		setVisible(true);

		AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendChatMsg(obcm);

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}

	}
		

	
	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		// AppendIcon(icon1);
		msg = msg.trim(); // ¾ÕµÚ blank¿Í \nÀ» Á¦°ÅÇÑ´Ù.
		/*
		int len = textArea.getDocument().getLength();
		// ³¡À¸·Î ÀÌµ¿
		textArea.setCaretPosition(len);
		textArea.replaceSelection(msg + "\n");
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

}
