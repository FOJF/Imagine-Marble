import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientInGame extends JFrame {

	JLabel[] LandName = new JLabel[22];
	MyPanel[] Land = new MyPanel[22];
	// JLabel[] SpecialLandName = new SpecialLandName[5];
	JLabel[] UserNames = new JLabel[4];
	JLabel[] UserMoneyImg = new JLabel[4];
	JLabel[] UserMoney = new JLabel[4];
	JLabel[] Dices = new JLabel[2];
	JLabel[] UserState = new JLabel[4];
	JLabel[] UserUnit = new JLabel[4];
	JLabel[] MoneyAni = new JLabel[4];
	JLabel[] bankruptcy = new JLabel[4];
	JButton Outbtn = new JButton();
	JButton Rollbtn = new JButton();
	JButton Readybtn = new JButton();
	JLabel Victory = new JLabel();
	int[] UserHaveMoney = { 0, 0, 0, 0 };

	String[] land = { "시작", "정문", "팀프로젝트", "낙산관", "중간고사", "미래관", "창의관", "인성관", "상상빌리지", "탐구관", "학송관", "대동제", "공학관",
			"진리관", "지선관", "기말고사", "연구관", "우촌관", "학군단", "낙산공원", "상상관", "후문" };

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

	public ClientInGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyPanel bg = new MyPanel();
		MyPanel UserInfoBg[] = new MyPanel[4];
		MyPanel UserInfo[] = new MyPanel[4];
		JLabel UserImage[] = new JLabel[4];
		setBounds(100, 100, 1024, 768);
		setContentPane(bg);
		bg.setIcon(new ImageIcon("Images/InGameBackground.png"));
		bg.setLayout(null);

		Rollbtn = new JButton("", new ImageIcon("Images/DiceRoll.PNG"));
		Rollbtn.setFont(new Font("문체부 제목 돋음체", Font.BOLD, 15));
		Rollbtn.setBounds(434, 468, 140, 40);
		bg.add(Rollbtn);
		Rollbtn.setVisible(false);

		Readybtn = new JButton("", new ImageIcon("Images/Readybtn.PNG"));
		Readybtn.setFont(new Font("문체부 제목 돋음체", Font.BOLD, 15));
		Readybtn.setBounds(434, 220, 140, 40);
		bg.add(Readybtn);

		for (int i = 0; i < Dices.length; i++) {
			Dices[i] = new JLabel();
			Dices[i].setIcon(new ImageIcon("Images/1.PNG"));
			Dices[i].setBounds(400 + (i * 150), 320, 50, 50);
			bg.add(Dices[i]);
			Dices[i].setVisible(false);
		}
		for (int i = 0; i < 4; i++) {
			UserInfoBg[i] = new MyPanel();
			UserInfo[i] = new MyPanel();
			UserInfo[i].setLayout(null);
			UserInfoBg[i].setLayout(null);
			UserInfoBg[i].setBounds(12, 50, 240, 100);

			if (i < 2) {
				UserInfoBg[i].setBounds(12 + (i * 750), 10, 240, 100);
				UserInfo[i].setBounds(105 + (i * 750), 20, 140, 80);
				// UserImage[i].setBounds(17 + (i * 750), 15, 85, 90);
			} else {
				UserInfoBg[i].setBounds(12 + ((i - 2) * 750), 620, 240, 100);
				UserInfo[i].setBounds(105 + ((i - 2) * 750), 630, 140, 80);
				// UserImage[i].setBounds(17 + ((i - 2) * 750), 625, 85, 90);
			}
			UserInfoBg[i].setIcon(new ImageIcon("Images/GameRoomUser.PNG"));
			UserInfo[i].setIcon(new ImageIcon("Images/UserInfo.png"));
			// UserImage[i].setIcon(new ImageIcon("Images/User" + (i + 1) + ".png"));
			// bg.add(UserImage[i]);
			bg.add(UserInfo[i]);
			bg.add(UserInfoBg[i]);

			UserNames[i] = new JLabel();
			UserNames[i].setBounds(5, 10, 200, 20);

			UserMoneyImg[i] = new JLabel();
			UserMoneyImg[i].setBounds(3, 28, 20, 20);
			UserMoneyImg[i].setIcon(new ImageIcon("Images/Money.png"));

			UserMoney[i] = new JLabel();
			UserMoney[i].setBounds(25, 30, 100, 20);

			UserState[i] = new JLabel();
			UserState[i].setBounds(45, 57, 53, 32);
			UserState[i].setIcon(new ImageIcon("Images/PlayerReady.png"));

			MoneyAni[i] = new JLabel();
			MoneyAni[i].setBounds(25, 50, 100, 10);

			bankruptcy[i] = new JLabel();
			bankruptcy[i].setBounds(40, 20, 100, 50);
			bankruptcy[i].setIcon(new ImageIcon("Images/bankruptcy.png"));
			bankruptcy[i].setVisible(false);

			UserImage[i] = new JLabel();
			UserImage[i].setBounds(5, 5, 85, 90);
			UserImage[i].setIcon(new ImageIcon("Images/UserImage" + (i + 1) + ".png"));

			UserInfo[i].add(bankruptcy[i]);
			UserInfo[i].add(UserMoneyImg[i]);
			UserInfo[i].add(UserMoney[i]);
			UserInfo[i].add(UserState[i]);
			UserInfo[i].add(UserNames[i]);
			UserInfo[i].add(MoneyAni[i]);
			UserInfoBg[i].add(UserImage[i]);
			UserState[i].setVisible(false);
		}

		for (int i = 0; i < land.length; i++) {
			LandName[i] = new JLabel();
			LandName[i].setBounds(10, 0, 100, 20);
			Land[i] = new MyPanel();
			Land[i].setLayout(null);
			Land[i].setIcon(new ImageIcon("Images/Frame.png"));
			if (i < 4 && i >= 0)
				Land[i].setBounds(100, 515 - (i * 100), 95, 95);
			else if (i >= 4 && i < 12)
				Land[i].setBounds(100 + ((i - 4) * 100), 115, 95, 95);
			else if (i >= 12 && i < 16)
				Land[i].setBounds(800, 115 + ((i - 11) * 100), 95, 95);
			else if (i >= 16)
				Land[i].setBounds(800 - ((i - 15) * 100), 515, 95, 95);
			bg.add(Land[i]);
			Land[i].add(LandName[i]);
			LandName[i].setText(land[i]);
		}
		for (int i = 0; i < 4; i++) {
			UserUnit[i] = new JLabel();
			if (i < 2)
				UserUnit[i].setBounds(13 + (i * 43), 25, 25, 25);
			else
				UserUnit[i].setBounds(13 + ((i - 2) * 43), 65, 25, 25);
			UserUnit[i].setIcon(new ImageIcon("Images/User" + (i + 1) + ".PNG"));

			Land[0].add(UserUnit[i]);
			UserUnit[i].setVisible(false);
		}
		Outbtn = new JButton("", new ImageIcon("Images/InGameOut.png"));
		Outbtn.setBounds(609, 642, 117, 35);
		bg.add(Outbtn);

		// Victory.setIcon(null);
		// Victory.setIcon( new ImageIcon("Images/Lose.png"));

		Victory.setBounds(450, 20, 99, 60);
		bg.add(Victory);

	}
}