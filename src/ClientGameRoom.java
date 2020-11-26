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
	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String PlayerNum = "4";
	private String InPlayerNum;
	private String RoomNumber;
	private String SendIpAddr;
	private String SendPort;
	private String InRoomCnt;
	private int[] RoomCnt=new int[5];
	private String[] RoomState = new String[] {"대기중","대기중","대기중","대기중","대기중"};
	private JLabel[] InPlayerNumLabel = new JLabel[4];

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JPanel contentPane;
	private JButton btnInButton1;
	private JButton btnInButton2;
	private JButton btnInButton3;
	private JButton btnInButton4;
	
	private static int count = 0;

	/**
	 * Create the frame.
	 */
	public ClientGameRoom(String username,String ip_addr,String port_no) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		getContentPane().setLayout(null);
		
		Inaction ia = new Inaction();

		
		JLabel NameLabel = new JLabel(username);
		NameLabel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NameLabel.setBounds(0, 0, 200, 32);
		getContentPane().add(NameLabel);
		
		JLabel HiLabel = new JLabel("\uB2D8 \uC548\uB155\uD558\uC138\uC694!");
		HiLabel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		HiLabel.setHorizontalAlignment(SwingConstants.LEFT);
		HiLabel.setBounds(200, 0, 164, 32);
		getContentPane().add(HiLabel);
		
		JLabel RoomListLabel = new JLabel("\uBC29\uBAA9\uB85D");
		RoomListLabel.setFont(new Font("한컴 소망 B", Font.PLAIN, 25));
		RoomListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RoomListLabel.setBounds(404, 65, 200, 50);
		getContentPane().add(RoomListLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.DARK_GRAY);
		panel_1.setBounds(12, 135, 464, 244);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel Room1PlayerLabel = new JLabel("\uC778\uC6D0\uC218");
		Room1PlayerLabel.setBounds(58, 86, 57, 15);
		panel_1.add(Room1PlayerLabel);
		
		JLabel Room1Label = new JLabel("1번방");
		Room1Label.setBounds(181, 10, 64, 39);
		panel_1.add(Room1Label);
		Room1Label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		InPlayerNumLabel[0] = new JLabel("0");
		InPlayerNumLabel[0].setBounds(127, 86, 57, 15);
		panel_1.add(InPlayerNumLabel[0]);
		
		JLabel PlayerNumLabel1 = new JLabel(PlayerNum);
		PlayerNumLabel1.setBounds(248, 86, 57, 15);
		panel_1.add(PlayerNumLabel1);
		
		JLabel Slash1 = new JLabel("/");
		Slash1.setHorizontalAlignment(SwingConstants.CENTER);
		Slash1.setBounds(179, 86, 57, 15);
		panel_1.add(Slash1);
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(532, 135, 464, 244);
		getContentPane().add(panel_2);
		
		JLabel Room2PlayerLabel = new JLabel("\uC778\uC6D0\uC218");
		Room2PlayerLabel.setBounds(58, 86, 57, 15);
		panel_2.add(Room2PlayerLabel);
		
		JLabel Room2Label = new JLabel("2번방");
		Room2Label.setHorizontalAlignment(SwingConstants.CENTER);
		Room2Label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		Room2Label.setBounds(200, 10, 64, 39);
		panel_2.add(Room2Label);
		
		JLabel GameState2 = new JLabel(" ");
		GameState2.setBounds(58, 111, 57, 15);
		panel_2.add(GameState2);
		
		InPlayerNumLabel[1] = new JLabel("0");
		InPlayerNumLabel[1].setBounds(127, 86, 57, 15);
		panel_2.add(InPlayerNumLabel[1]);
		
		JLabel PlayerNumLabel2 = new JLabel(PlayerNum);
		PlayerNumLabel2.setBounds(248, 86, 57, 15);
		panel_2.add(PlayerNumLabel2);
		
		btnInButton2 = new JButton("입장");
		btnInButton2.setBounds(176, 196, 97, 23);
		panel_2.add(btnInButton2);
		btnInButton2.addActionListener(ia);
		
		JLabel Slash2 = new JLabel("/");
		Slash2.setHorizontalAlignment(SwingConstants.CENTER);
		Slash2.setBounds(179, 86, 57, 15);
		panel_2.add(Slash2);
		btnInButton1 = new JButton("입장");
		btnInButton1.setBounds(181, 195, 97, 23);
		panel_1.add(btnInButton1);
		btnInButton1.addActionListener(ia);
		
		
		JLabel GameState1 = new JLabel("");
		GameState1.setBounds(58, 111, 57, 15);
		panel_1.add(GameState1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(12, 409, 464, 244);
		getContentPane().add(panel_3);
		
		JLabel Room3PlayerLabel = new JLabel("\uC778\uC6D0\uC218");
		Room3PlayerLabel.setBounds(58, 86, 57, 15);
		panel_3.add(Room3PlayerLabel);
		
		JLabel Room3Label = new JLabel("3\uBC88\uBC29");
		Room3Label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		Room3Label.setBounds(181, 10, 64, 39);
		panel_3.add(Room3Label);
		
		JLabel PlayerNumLabel3 = new JLabel("4");
		PlayerNumLabel3.setBounds(248, 86, 57, 15);
		panel_3.add(PlayerNumLabel3);
		
		JLabel Slash3 = new JLabel("/");
		Slash3.setHorizontalAlignment(SwingConstants.CENTER);
		Slash3.setBounds(179, 86, 57, 15);
		panel_3.add(Slash3);
		
		btnInButton3 = new JButton("입장");
		btnInButton3.setBounds(181, 195, 97, 23);
		panel_3.add(btnInButton3);
		btnInButton3.addActionListener(ia);
		
		JLabel GameState3 = new JLabel("");
		GameState3.setBounds(58, 111, 57, 15);
		panel_3.add(GameState3);
		
		InPlayerNumLabel[2] = new JLabel("0");
		InPlayerNumLabel[2].setBounds(127, 86, 57, 15);
		panel_3.add(InPlayerNumLabel[2]);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(532, 409, 464, 244);
		getContentPane().add(panel_4);
		
		JLabel Room4PlayerLabel = new JLabel("\uC778\uC6D0\uC218");
		Room4PlayerLabel.setBounds(58, 86, 57, 15);
		panel_4.add(Room4PlayerLabel);
		
		JLabel Room4Label = new JLabel("4\uBC88\uBC29");
		Room4Label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		Room4Label.setBounds(181, 10, 64, 39);
		panel_4.add(Room4Label);
		
		JLabel PlayerNumLabel4 = new JLabel("4");
		PlayerNumLabel4.setBounds(248, 86, 57, 15);
		panel_4.add(PlayerNumLabel4);
		
		JLabel Slash4 = new JLabel("/");
		Slash4.setHorizontalAlignment(SwingConstants.CENTER);
		Slash4.setBounds(179, 86, 57, 15);
		panel_4.add(Slash4);
		
		btnInButton4 = new JButton("입장");
		btnInButton4.setBounds(181, 195, 97, 23);
		panel_4.add(btnInButton4);
		btnInButton4.addActionListener(ia);
		
		JLabel GameState4 = new JLabel("");
		GameState4.setBounds(58, 111, 57, 15);
		panel_4.add(GameState4);

		InPlayerNumLabel[3] = new JLabel("0");
		InPlayerNumLabel[3].setBounds(127, 86, 57, 15);
		panel_4.add(InPlayerNumLabel[3]);
		
		
		
		
		
		contentPane = new JPanel();
		setVisible(true);

		AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;
		SendIpAddr = ip_addr;
		SendPort = port_no;
		if(count ==0 ) {
		 
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendChatMsg(obcm);
			ListenNetwork net = new ListenNetwork();
			net.start();

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}
		count++;
		}
	}

	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		// AppendIcon(icon1);
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		/*
		 * int len = textArea.getDocument().getLength(); // 끝으로 이동
		 * textArea.setCaretPosition(len); textArea.replaceSelection(msg + "\n");
		 */
	}

	public void SendChatMsg(ChatMsg obj) {
		try {
			oos.writeObject(obj.code);
			oos.writeObject(obj.UserName);
			oos.writeObject(obj.data);
			if (obj.code.equals("300")) { // 이미지 첨부 있는 경우
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

			// textArea.append("메세지 송신 에러!!\n");
			// System.exit(0);
		}
	}
	public ChatMsg ReadChatMsg() {
		Object obj = null;
		String msg = null;
		ChatMsg cm = new ChatMsg("", "", "");
		// Android와 호환성을 위해 각각의 Field를 따로따로 읽는다.

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

				// textArea.append("메세지 송신 에러!!\n");
				// System.exit(0);
			}


		return cm;
	}
	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				ChatMsg cm = ReadChatMsg();
				if (cm==null)
					break;
				if (socket == null)
					break;
				String msg;
				msg = String.format("[%s] %s", cm.UserName, cm.data);
				switch (cm.code) {
				case "200": // chat message
					AppendText(msg);
					break;
				case "300": // Image 첨부
					AppendText("[" + cm.UserName + "]" + " " + cm.data);
					//AppendImage(cm.img);
					//AppendImageBytes(cm.imgbytes);
					break;
				case "602":
					String[] RoomData = cm.data.split(" ");
					RoomCnt[Integer.parseInt(RoomData[0])] = Integer.parseInt(RoomData[1]); 
					InPlayerNumLabel[Integer.parseInt(RoomData[0])-1].setText(RoomData[1]);
					InRoomCnt = RoomData[1];
					break;
				case "404":
					
					
				}

			}
		}
	}
	class Inaction implements ActionListener // 내부클래스로 액션 이벤트 처리 클래스.
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = new JButton();
			if (e.getSource() == btnInButton1) 
				RoomNumber = "1";
			else if(e.getSource() == btnInButton2)
				RoomNumber = "2";
			else if(e.getSource() == btnInButton3)
				RoomNumber = "3";
			else if(e.getSource() == btnInButton4)
				RoomNumber = "4";
			btn = (JButton) e.getSource();
			ChatMsg obcm = new ChatMsg(UserName, "600", RoomNumber);
			SendChatMsg(obcm);

				// ClientInGame game = new ClientInGame(username,ip_addr,port_no);

			Socket SOCKET = socket;
			ObjectInputStream OIS = ois;
			ObjectOutputStream OOS = oos;
				ClientInGame cig = new ClientInGame(UserName, SendIpAddr, SendPort, RoomNumber,SOCKET,OOS,OIS);
				dispose();
				
			}
			
		
	}
}
