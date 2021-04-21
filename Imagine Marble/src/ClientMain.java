import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ClientMain extends JFrame {

	private MyPanel mp = new MyPanel();
	private JTextField txtUserName;
	private JTextField txtIpAdress;
	private JTextField txtPortNumber;

	private Socket socket; // 연결소켓
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private ClientGameRoom GameRoom = new ClientGameRoom();
	private ClientInGame InGame = new ClientInGame();
	private PopUp pop = new PopUp();

	private String myName;
	private int myRoomNumber;
	private int UserIndex;
	private boolean readyBool = true;
	private int UserLocation[] = new int[4];

	private Random random = new Random();

	private Frame frame;
	private FileDialog fd;

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
	class MyPanel extends JPanel {
		private ImageIcon icon = new ImageIcon("images/Main.PNG");
		private Image img = icon.getImage();

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public ClientMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		setContentPane(mp);
		mp.setBorder(new EmptyBorder(5, 5, 5, 5));
		mp.setLayout(null);

		pop.setVisible(false);

		JLabel GameName = new JLabel("상상 마블");
		GameName.setForeground(Color.BLUE);
		GameName.setFont(new Font("양재블럭체", Font.BOLD, 60));
		GameName.setHorizontalAlignment(SwingConstants.CENTER);
		GameName.setBounds(334, 41, 340, 88);
		mp.add(GameName);

		JLabel MainImage = new JLabel("");
		MainImage.setHorizontalAlignment(SwingConstants.CENTER);
		MainImage.setIcon(new ImageIcon("Images/MainBoogie.png"));
		MainImage.setBounds(342, 150, 324, 260);
		mp.add(MainImage);

		JLabel NickName = new JLabel("닉네임");
		NickName.setForeground(Color.WHITE);
		NickName.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 20));
		NickName.setHorizontalAlignment(SwingConstants.CENTER);
		NickName.setBounds(357, 457, 109, 36);
		mp.add(NickName);

		txtUserName = new JTextField();
		txtUserName.setHorizontalAlignment(SwingConstants.CENTER);
		txtUserName.setBounds(463, 457, 166, 36);
		txtUserName.setColumns(10);
		mp.add(txtUserName);

		JLabel IpAdress = new JLabel("Ip Adress");
		IpAdress.setForeground(Color.WHITE);
		IpAdress.setFont(new Font("굴림", Font.BOLD, 15));
		IpAdress.setHorizontalAlignment(SwingConstants.CENTER);
		IpAdress.setBounds(367, 502, 82, 30);
		mp.add(IpAdress);

		JLabel PortNumber = new JLabel("Port Number");
		PortNumber.setForeground(Color.WHITE);
		PortNumber.setHorizontalAlignment(SwingConstants.CENTER);
		PortNumber.setFont(new Font("굴림", Font.BOLD, 13));
		PortNumber.setBounds(367, 535, 82, 30);
		mp.add(PortNumber);

		txtIpAdress = new JTextField();
		txtIpAdress.setHorizontalAlignment(SwingConstants.CENTER);
		txtIpAdress.setText("127.0.0.1");
		txtIpAdress.setBounds(461, 507, 168, 21);
		txtIpAdress.setColumns(10);
		mp.add(txtIpAdress);

		txtPortNumber = new JTextField();
		txtPortNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtPortNumber.setText("30000");
		txtPortNumber.setColumns(10);
		txtPortNumber.setBounds(461, 540, 168, 21);
		mp.add(txtPortNumber);

		JButton btnStart = new JButton("\uC785\uC7A5");
		btnStart.setBounds(434, 640, 140, 46);
		mp.add(btnStart);

		Myaction action = new Myaction();
		btnStart.addActionListener(action);
		txtUserName.addActionListener(action);
		txtIpAdress.addActionListener(action);
		txtPortNumber.addActionListener(action);

		for (int i = 0; i < 4; i++) {
			PressJoin pj = new PressJoin();
			GameRoom.JoinBtns[i].addActionListener(pj);
			UserLocation[i] = 0;
		}

		GameRoom.NameLabel.setForeground(Color.white);

		PressOut po = new PressOut();
		PressReady pr = new PressReady();
		PressDice pd = new PressDice();
		PopupClose pc = new PopupClose();
		PopupYes py = new PopupYes();
		TextSendAction action2 = new TextSendAction();
		ImageSendAction action3 = new ImageSendAction();
		InGame.Outbtn.addActionListener(po);
		InGame.Readybtn.addActionListener(pr);
		InGame.Rollbtn.addActionListener(pd);
		pop.Nobtn.addActionListener(pc);
		pop.Okbtn.addActionListener(py);
		GameRoom.btnSend.addActionListener(action2);
		GameRoom.imgBtn.addActionListener(action3);

	}

	public void AppendText(String msg) {
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		int len = GameRoom.textArea.getDocument().getLength();

		StyledDocument doc = GameRoom.textArea.getStyledDocument();
		SimpleAttributeSet left = new SimpleAttributeSet();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(left, Color.BLACK);
		doc.setParagraphAttributes(doc.getLength(), 1, left, false);
		try {
			doc.insertString(doc.getLength(), msg + "\n", left);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AppendTextR(String msg) {
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		StyledDocument doc = GameRoom.textArea.getStyledDocument();
		SimpleAttributeSet right = new SimpleAttributeSet();
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setForeground(right, Color.BLUE);
		doc.setParagraphAttributes(doc.getLength(), 1, right, false);
		try {
			doc.insertString(doc.getLength(), msg + "\n", right);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AppendImage(ImageIcon ori_icon) {
		int len = GameRoom.textArea.getDocument().getLength();
		GameRoom.textArea.setCaretPosition(len); // place caret at the end (with no selection)
		Image ori_img = ori_icon.getImage();
		int width, height;
		double ratio;
		width = ori_icon.getIconWidth();
		height = ori_icon.getIconHeight();
		// Image가 너무 크면 최대 가로 또는 세로 200 기준으로 축소시킨다.
		if (width > 200 || height > 200) {
			if (width > height) { // 가로 사진
				ratio = (double) height / width;
				width = 200;
				height = (int) (width * ratio);
			} else { // 세로 사진
				ratio = (double) width / height;
				height = 200;
				width = (int) (height * ratio);
			}
			Image new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon new_icon = new ImageIcon(new_img);
			GameRoom.textArea.insertIcon(new_icon);

		} else
			GameRoom.textArea.insertIcon(ori_icon);
		len = GameRoom.textArea.getDocument().getLength();
		GameRoom.textArea.setCaretPosition(len);
		GameRoom.textArea.replaceSelection("\n");
	}

	public void AppendImageBytes(byte[] imgbytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(imgbytes);
		BufferedImage ori_img = null;
		try {
			ori_img = ImageIO.read(bis);
			bis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon new_icon = new ImageIcon(ori_img);
		AppendImage(new_icon);
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
		}

		return cm;
	}

	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				ChatMsg cm = ReadChatMsg();
				if (cm == null)
					break;
				if (socket == null)
					break;
				String msg;
				msg = String.format("[%s] %s", cm.UserName, cm.data);
				System.out.println(cm.data + " " + cm.UserName + " " + cm.code);
				switch (cm.code) {
				case "200": // chat message
					if (cm.UserName.equals(myName))
						AppendTextR(msg); // 내 메세지는 우측에
					else
						AppendText(msg);
					break;
				case "300": // Image 첨부
					if (cm.UserName.equals(myName)) {
						AppendTextR("[" + cm.UserName + "]" + " " + cm.data);
						// AppendImage(cm.img);
						AppendImageBytes(cm.imgbytes);
					} else {
						AppendText("[" + cm.UserName + "]" + " " + cm.data);
						// AppendImage(cm.img);
						AppendImageBytes(cm.imgbytes);
					}
					break;

				case "602": // 게임룸 정보 받아오기
					String[] RoomData = cm.data.split(" ");
					int RoomNumberData = Integer.parseInt(RoomData[0]);
					int PlayerCntData = Integer.parseInt(RoomData[1]);
					if (PlayerCntData == 4) // 방이 가득 차면 버튼 비활성화
						GameRoom.JoinBtns[RoomNumberData - 1].setEnabled(false);
					else
						GameRoom.JoinBtns[RoomNumberData - 1].setEnabled(true);
					GameRoom.PlayerCnt[RoomNumberData - 1].setText(PlayerCntData + "   /   4");
					break;
				case "603": // 게임 대기실 게임중인지 아닌지 구분
					String[] RoomStateData = cm.data.split(" ");

					if (Integer.parseInt(RoomStateData[1]) >= 1) {
						GameRoom.JoinBtns[Integer.parseInt(RoomStateData[0]) - 1].setEnabled(false);
						GameRoom.GameRoomState[Integer.parseInt(RoomStateData[0]) - 1]
								.setText("게임중" + " " + "(" + RoomStateData[1] + "턴)");
						InGame.Readybtn.setVisible(false);
						InGame.Rollbtn.setVisible(true);
						InGame.Outbtn.setVisible(false);
						readyBool = true;
						for (int i = 0; i < 2; i++)
							InGame.Dices[i].setVisible(true);
						InGame.UserNames[0].setForeground(Color.RED);
						InGame.UserNames[1].setForeground(Color.YELLOW);
						InGame.UserNames[2].setForeground(Color.BLUE);
						InGame.UserNames[3].setForeground(Color.GREEN);
					} else if (RoomStateData[1].equals("0")) {

						GameRoom.JoinBtns[Integer.parseInt(RoomStateData[0]) - 1].setEnabled(true);
						InGame.Rollbtn.setVisible(false);
						InGame.Readybtn.setIcon(new ImageIcon("Images/Readybtn.PNG"));
						InGame.Readybtn.setVisible(true);
						InGame.Outbtn.setVisible(true);
						for (int i = 0; i < 2; i++)
							InGame.Dices[i].setVisible(false);
						for (int i = 0; i < 4; i++) {
							InGame.UserUnit[i].setVisible(false);
							InGame.Land[0].add(InGame.UserUnit[i]);
							UserLocation[i] = 0;
							InGame.UserHaveMoney[i] = 0;
							InGame.UserNames[i].setForeground(Color.BLACK);
							InGame.bankruptcy[i].setVisible(false);
						}
						InGame.Victory.setVisible(false);
						if (GameRoom.GameRoomState[Integer.parseInt(RoomStateData[0]) - 1].getText().matches("게임중.*")) {

							InGame.Victory.setVisible(true);
							try {
								sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							InGame.Victory.setVisible(false);

						}
						GameRoom.GameRoomState[Integer.parseInt(RoomStateData[0]) - 1].setText("대기중");
						repaint();
						/*
						 * InGame.Rollbtn.setVisible(false); InGame.Readybtn.setVisible(true);
						 */
					}
					break;
				case "604": // 유저이름 인게임에서 동기화
					String[] RoomInData = cm.data.split(" ");
					UserIndex = Integer.parseInt(RoomInData[0]);
					if (RoomInData.length == 1)
						InGame.UserNames[Integer.parseInt(RoomInData[0])].setText("");
					else
						InGame.UserNames[Integer.parseInt(RoomInData[0])].setText(RoomInData[1]);
					break;
				case "605": // 유저의 레디상태
					String[] UserReadyData = cm.data.split(" ");
					InGame.UserState[Integer.parseInt(UserReadyData[0])].setVisible(true);
					break;
				case "606": // 유저의 레디 안한 상태
					String[] UserUnreadyData = cm.data.split(" ");
					InGame.UserState[Integer.parseInt(UserUnreadyData[0])].setVisible(false);
					break;
				case "607": // 인게임에서 게임 시작시 돈 부여 및 돈관리
					String[] MoneyData = cm.data.split(" ");
					int add = 0;
					String num = "0";
					if (InGame.UserHaveMoney[Integer.parseInt(MoneyData[0])] == Integer.parseInt(MoneyData[1])) {
						InGame.UserHaveMoney[Integer.parseInt(MoneyData[0])] = Integer.parseInt(MoneyData[1]);
						add = Integer.parseInt(MoneyData[1]);
						InGame.UserMoney[Integer.parseInt(MoneyData[0])].setVisible(true);
						InGame.bankruptcy[Integer.parseInt(MoneyData[0])].setVisible(false);
					}

					else if (InGame.UserHaveMoney[Integer.parseInt(MoneyData[0])] > Integer.parseInt(MoneyData[1])) {
						add = InGame.UserHaveMoney[Integer.parseInt(MoneyData[0])] - Integer.parseInt(MoneyData[1]);
						InGame.UserMoney[Integer.parseInt(MoneyData[0])].setVisible(true);
						InGame.bankruptcy[Integer.parseInt(MoneyData[0])].setVisible(false);
						// InGame.MoneyAni[Integer.parseInt(MoneyData[0])].setText("-" +
						// Integer.toString(add));
						num = ("-" + Integer.toString(add));
					} else if (InGame.UserHaveMoney[Integer.parseInt(MoneyData[0])] < Integer.parseInt(MoneyData[1])) {
						add = Integer.parseInt(MoneyData[1]) - InGame.UserHaveMoney[Integer.parseInt(MoneyData[0])];
						InGame.UserMoney[Integer.parseInt(MoneyData[0])].setVisible(true);
						InGame.bankruptcy[Integer.parseInt(MoneyData[0])].setVisible(false);
						// InGame.MoneyAni[Integer.parseInt(MoneyData[0])].setText("+" +
						// Integer.toString(add));
						num = ("+" + Integer.toString(add));
					}
					if (Integer.parseInt(MoneyData[1]) <= 0) {
						InGame.UserMoney[Integer.parseInt(MoneyData[0])].setVisible(false);
						InGame.UserUnit[Integer.parseInt(MoneyData[0])].setVisible(false);
						InGame.bankruptcy[Integer.parseInt(MoneyData[0])].setVisible(true);
					} else {
						InGame.UserHaveMoney[Integer.parseInt(MoneyData[0])] = Integer.parseInt(MoneyData[1]);
						MoneyAni ma = new MoneyAni(Integer.parseInt(MoneyData[0]), num);
						ma.start();
						InGame.UserMoney[Integer.parseInt(MoneyData[0])].setText(MoneyData[1]);
						InGame.UserUnit[Integer.parseInt(MoneyData[0])].setVisible(true);
						InGame.bankruptcy[Integer.parseInt(MoneyData[0])].setVisible(false);
					}
					break;
				case "608": // 유저 턴
					if (cm.data.equals(myName))
						InGame.Rollbtn.setEnabled(true);
					else
						InGame.Rollbtn.setEnabled(false);
					break;
				case "609": // 주사위 애니메이션

					String[] DiceData = cm.data.split(" ");
					DiceAni da = new DiceAni(Integer.parseInt(DiceData[0]), Integer.parseInt(DiceData[1]));
					da.start();

					break;

				case "610": // 말판 움직이기
					String[] UserUnitData = cm.data.split(" ");
					try {
						sleep(1800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					InGame.UserUnit[Integer.parseInt(UserUnitData[0])].setVisible(false);
					if (Integer.parseInt(UserUnitData[0]) < 2)
						InGame.UserUnit[Integer.parseInt(UserUnitData[0])]
								.setBounds(13 + (Integer.parseInt(UserUnitData[0]) * 43), 25, 25, 25);
					else
						InGame.UserUnit[Integer.parseInt(UserUnitData[0])]
								.setBounds(13 + ((Integer.parseInt(UserUnitData[0]) - 2) * 43), 65, 25, 25);
					InGame.UserUnit[Integer.parseInt(UserUnitData[0])]
							.setIcon(new ImageIcon("Images/User" + ((Integer.parseInt(UserUnitData[0])) + 1) + ".PNG"));

					InGame.Land[Integer.parseInt(UserUnitData[1])]
							.add(InGame.UserUnit[Integer.parseInt(UserUnitData[0])]);
					UserLocation[Integer.parseInt(UserUnitData[0])] = Integer.parseInt(UserUnitData[1]);
					InGame.UserUnit[Integer.parseInt(UserUnitData[0])].setVisible(true);
					break;
				case "611":
					String[] LandData = cm.data.split(" ");
					String Land = InGame.land[UserLocation[Integer.parseInt(LandData[0])]];
					pop.LandBuyLabel.setText(Land + "를(을) 구입하시겠습니까??");
					pop.Price.setText("가격: 50000상추");
					if (Integer.parseInt(LandData[1]) == 5) {
						if ((InGame.UserNames[Integer.parseInt(LandData[0])].getText()).equals(myName)) {
							pop.setLocation(InGame.getLocation().x + 300, InGame.getLocation().y + 100);
							pop.setVisible(true);
						}
					} else if (Integer.parseInt(LandData[1]) == Integer.parseInt(LandData[0]))
						System.out.println("내땅");
					else {
						pop.LandBuyLabel.setText(Land + "를(을) "
								+ InGame.UserNames[Integer.parseInt(LandData[1])].getText() + "로 부터 인수하시겠습니까??");
						pop.Price.setText("가격: 150000상추");
						if ((InGame.UserNames[Integer.parseInt(LandData[0])].getText()).equals(myName)) {

							pop.setLocation(InGame.getLocation().x + 300, InGame.getLocation().y + 100);
							pop.setVisible(true);
						}
					}
					break;
				case "612":
					String[] LandHaveData = cm.data.split(" ");
					// System.out.println("612: " + LandHaveData[0] + " " + LandHaveData[1]);
					if (Integer.parseInt(LandHaveData[0]) == 0) {
						InGame.LandName[Integer.parseInt(LandHaveData[1])].setForeground(Color.RED);
					} else if (Integer.parseInt(LandHaveData[0]) == 1) {
						InGame.LandName[Integer.parseInt(LandHaveData[1])].setForeground(Color.YELLOW);
					} else if (Integer.parseInt(LandHaveData[0]) == 2) {
						InGame.LandName[Integer.parseInt(LandHaveData[1])].setForeground(Color.BLUE);
					} else if (Integer.parseInt(LandHaveData[0]) == 3) {
						InGame.LandName[Integer.parseInt(LandHaveData[1])].setForeground(Color.GREEN);
					} else if (Integer.parseInt(LandHaveData[0]) == 5) {
						InGame.LandName[Integer.parseInt(LandHaveData[1])].setForeground(Color.BLACK);
					}
					break;

				case "613":
					if (myName.equals(cm.data))
						InGame.Victory.setIcon(new ImageIcon("Images/Victory.png"));

					else
						InGame.Victory.setIcon(new ImageIcon("Images/Lose.png"));
					break;

				}

			}
		}
	}

	class DiceAni extends Thread {
		int d1, d2;

		public DiceAni(int d1, int d2) {
			this.d1 = d1;
			this.d2 = d2;
		}

		public void run() {

			for (int i = 0; i < 6; i++) {
				int n1, n2;
				n1 = random.nextInt(6) + 1;
				n2 = random.nextInt(6) + 1;
				InGame.Dices[0].setIcon(new ImageIcon("Images/" + n1 + ".png"));
				InGame.Dices[1].setIcon(new ImageIcon("Images/" + n2 + ".png"));
				// 주사위 이미지 n1, n2 표시
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
			// 마지막에 d1, d2 로 주사위 이미지 보여준다.
			InGame.Dices[0].setIcon(new ImageIcon("Images/" + d1 + ".png"));
			InGame.Dices[1].setIcon(new ImageIcon("Images/" + d2 + ".png"));
			repaint();
		}
	}

	class MoneyAni extends Thread {
		int Uindex;
		String Umoney;

		public MoneyAni(int index, String money) {
			this.Uindex = index;
			this.Umoney = money;
		}

		public void run() {

			if (Integer.parseInt(Umoney) < 0) {
				InGame.MoneyAni[Uindex].setText(Umoney);
				InGame.MoneyAni[Uindex].setForeground(Color.RED);
				InGame.MoneyAni[Uindex].setVisible(true);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				InGame.MoneyAni[Uindex].setForeground(Color.BLACK);
				InGame.MoneyAni[Uindex].setVisible(false);
			} else if (Integer.parseInt(Umoney) > 0) {
				InGame.MoneyAni[Uindex].setText(Umoney);
				InGame.MoneyAni[Uindex].setForeground(Color.GREEN);
				InGame.MoneyAni[Uindex].setVisible(true);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				InGame.MoneyAni[Uindex].setForeground(Color.BLACK);
				InGame.MoneyAni[Uindex].setVisible(false);
			} else {
				InGame.MoneyAni[Uindex].setText(Umoney);
				InGame.MoneyAni[Uindex].setVisible(true);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				InGame.MoneyAni[Uindex].setVisible(false);
			}

		}
	}

	class Myaction implements ActionListener // 맨 처음 입장 버튼
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			myName = txtUserName.getText().trim();

			String ip_addr = txtIpAdress.getText().trim();
			String port_no = txtPortNumber.getText().trim();
			try {
				socket = new Socket(ip_addr, Integer.parseInt(port_no));
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.flush();
				ois = new ObjectInputStream(socket.getInputStream());

				ChatMsg obcm = new ChatMsg(myName, "100", "Hello");
				SendChatMsg(obcm);

				ListenNetwork net = new ListenNetwork();
				net.start();
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				AppendText("connect error");
			}

			GameRoom.NameLabel.setText(myName + " 님 안녕하세요!");

			setVisible(false);
			GameRoom.setVisible(true);
		}
	}

	class PressJoin implements ActionListener { // 방 입장 버튼
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 4; i++) {
				if (e.getSource() == GameRoom.JoinBtns[i]) {
					myRoomNumber = i + 1;
					ChatMsg obcm = new ChatMsg(myName, "600", myRoomNumber + "");
					SendChatMsg(obcm);
					break;
				}

			}
			GameRoom.setVisible(false);
			InGame.setVisible(true);
		}
	}

	class PressOut implements ActionListener { // 나가기 버튼
		public void actionPerformed(ActionEvent e) {
			ChatMsg obcm = new ChatMsg(myName, "601", myRoomNumber + "");
			SendChatMsg(obcm);
			myRoomNumber = 0;
			UserIndex = -1;
			InGame.setVisible(false);
			GameRoom.setVisible(true);
		}
	}

	class PressDice implements ActionListener { // 주사위 굴리기 버튼
		public void actionPerformed(ActionEvent e) {
			ChatMsg obcm = new ChatMsg(myName, "609", "0");
			SendChatMsg(obcm);
		}

	}

	class PressReady implements ActionListener { // 게임준비
		public void actionPerformed(ActionEvent e) {
			if (readyBool == true) {
				ChatMsg obcm = new ChatMsg(myName, "605", myRoomNumber + "");
				SendChatMsg(obcm);
				InGame.Readybtn.setIcon(new ImageIcon("Images/Readybtn2.png"));
				readyBool = false;
			} else {
				ChatMsg obcm = new ChatMsg(myName, "606", myRoomNumber + "");
				SendChatMsg(obcm);
				InGame.Readybtn.setIcon(new ImageIcon("Images/Readybtn.png"));
				readyBool = true;
			}

		}
	}

	class PopupYes implements ActionListener { // 팝업창 예버튼
		public void actionPerformed(ActionEvent e) {
			ChatMsg obcm = new ChatMsg(myName, "612", " ");
			SendChatMsg(obcm);
			pop.setVisible(false);
			// ispop.setVisible(false);
		}

	}

	class PopupClose implements ActionListener { // 팝업창 아니오버튼
		public void actionPerformed(ActionEvent e) {
			pop.setVisible(false);
			// ispop.setVisible(false);
		}

	}

	// keyboard enter key 치면 서버로 전송
	class TextSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Send button을 누르거나 메시지 입력하고 Enter key 치면
			if (e.getSource() == GameRoom.btnSend || e.getSource() == GameRoom.txtInput) {
				String msg = null;
				// msg = String.format("[%s] %s\n", UserName, txtInput.getText());
				msg = GameRoom.txtInput.getText();
				ChatMsg obcm = new ChatMsg(myName, "200", msg);
				SendChatMsg(obcm);
				GameRoom.txtInput.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
				GameRoom.txtInput.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다
				if (msg.contains("/exit")) // 종료 처리
					System.exit(0);
			}
		}
	}

	class ImageSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == GameRoom.imgBtn) {
				frame = new Frame("이미지첨부");
				fd = new FileDialog(frame, "이미지 선택", FileDialog.LOAD);
				fd.setVisible(true);
				if (fd.getDirectory().length() > 0 && fd.getFile().length() > 0) {
					ChatMsg obcm = new ChatMsg(myName, "300", fd.getFile());

					BufferedImage bImage = null;
					String filename = fd.getDirectory() + fd.getFile();
					try {
						bImage = ImageIO.read(new File(filename));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					try {
						ImageIO.write(bImage, "jpg", bos);
						byte[] data = bos.toByteArray();
						bos.close();
						obcm.imgbytes = data;
						// AppendImageBytes(data);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					SendChatMsg(obcm);
				}
			}
		}
	}
}