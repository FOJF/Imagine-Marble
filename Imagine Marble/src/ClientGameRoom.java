
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ClientGameRoom extends JFrame {

	JLabel NameLabel = new JLabel();

	MyPanel[] RoomList = new MyPanel[4]; // 방 목록 JPanel
	JLabel[] RoomNames = new JLabel[4]; // 방 이름
	JLabel[] PlayerMax = new JLabel[4]; // 최대 인원수
	JLabel[] PlayerCnt = new JLabel[4]; // 방 참가 인원수
	JButton[] JoinBtns = new JButton[4]; // 입장 버튼
	JLabel[] GameRoomState = new JLabel[4];
	JTextPane textArea;
	JTextField txtInput;
	JButton imgBtn;
	JButton btnSend;

	/**
	 * Create the frame.
	 */
	class MyPanel extends JPanel {
		private ImageIcon icon;

		public void paintComponent(Graphics g) {
			Image img = icon.getImage();
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}

		public void setIcon(ImageIcon icon) {
			this.icon = icon;
		}

	}

	public ClientGameRoom() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);

		MyPanel bg = new MyPanel();
		setContentPane(bg);
		bg.setLayout(null);
		bg.setIcon(new ImageIcon("images/GameRoomBackground.jpg"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(312, 470, 400, 200);
		bg.add(scrollPane);

		textArea = new JTextPane();
		textArea.setEditable(true);
		textArea.setFont(new Font("굴림체", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);

		txtInput = new JTextField();
		txtInput.setBounds(312, 680, 400, 40);
		bg.add(txtInput);
		txtInput.setColumns(10);

		imgBtn = new JButton("+");
		imgBtn.setFont(new Font("굴림", Font.PLAIN, 16));
		imgBtn.setBounds(250, 680, 50, 40);
		bg.add(imgBtn);

		btnSend = new JButton("Send");
		btnSend.setFont(new Font("굴림", Font.PLAIN, 14));
		btnSend.setBounds(720, 680, 70, 40);
		bg.add(btnSend);

		NameLabel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NameLabel.setBounds(0, 0, 200, 32);
		bg.add(NameLabel);

		JLabel RoomListLabel = new JLabel("상상 마블");
		RoomListLabel.setFont(new Font("한컴 소망 B", Font.PLAIN, 25));
		RoomListLabel.setForeground(Color.BLUE);
		RoomListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RoomListLabel.setBounds(404, 50, 200, 50);
		bg.add(RoomListLabel);

		JLabel EarthLabel = new JLabel("New label");
		EarthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EarthLabel.setIcon(new ImageIcon("Images/earth.png"));
		EarthLabel.setBounds(450, 0, 150, 150);
		bg.add(EarthLabel);

		for (int i = 0; i < 4; i++) {
			RoomList[i] = new MyPanel();
			if (i < 2)
				RoomList[i].setBounds(12 + i * 532, 135, 464, 150);
			else
				RoomList[i].setBounds(12 + (i - 2) * 532, 300, 464, 150);

			RoomList[i].setIcon(new ImageIcon("images/GameRoomImg.png"));
			RoomList[i].setForeground(Color.DARK_GRAY);
			RoomList[i].setLayout(null);

			RoomNames[i] = new JLabel();
			RoomNames[i].setHorizontalAlignment(SwingConstants.CENTER);
			RoomNames[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			RoomNames[i].setBounds(144, 23, 64, 39);
			RoomNames[i].setText((i + 1) + " 번방");
			RoomList[i].add(RoomNames[i]);

			PlayerMax[i] = new JLabel();
			PlayerMax[i].setBounds(151, 86, 57, 15);
			PlayerMax[i].setText("인원수");
			RoomList[i].add(PlayerMax[i]);

			PlayerCnt[i] = new JLabel();
			PlayerCnt[i].setBounds(253, 86, 57, 15);
			PlayerCnt[i].setText("0   /   4");
			RoomList[i].add(PlayerCnt[i]);

			JoinBtns[i] = new JButton("", new ImageIcon("Images/GameIn.PNG"));
			JoinBtns[i].setBounds(21, 108, 97, 23);
			RoomList[i].add(JoinBtns[i]);

			GameRoomState[i] = new JLabel();
			GameRoomState[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			GameRoomState[i].setBounds(220, 35, 100, 15);
			GameRoomState[i].setText("대기중");
			RoomList[i].add(GameRoomState[i]);

			bg.add(RoomList[i]);
		}
	}

}