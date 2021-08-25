package com.gamble.jas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class Main extends JFrame {

	private JPanel contentPane;
	private static ImageIcon image1 = new ImageIcon("img/img1.png");
	private static ImageIcon image2 = new ImageIcon("img/img2.png");
	private static ImageIcon image3 = new ImageIcon("img/img3.png");
	private static ImageIcon image4 = new ImageIcon("img/img4.png");
	private static int winRate = 10;
	private int width = 70, height = 70;
	private int bet = 50;
	private static int sleepTime = 50;
	private static int tabCount = 0;
	private static boolean canStart = true;
	private JLabel playerBet;
	private int[] score = new int[3];
	private static ImageIcon[] image = new ImageIcon[4];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		image[0] = image1;
		image[1] = image2;
		image[2] = image3;
		image[3] = image4;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		
		for (ImageIcon x : image)
		{
			x.setImage(x.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dimension dim = getToolkit().getScreenSize();
//		setSize((int)dim.getWidth() / 2, (int)dim.getHeight() / 2);
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel img1 = new JLabel();
		img1.setBounds(35, 10, width, height);
		contentPane.add(img1);
		
		JLabel img2 = new JLabel();
		img2.setBounds(180, 10, width, height);
		contentPane.add(img2);
		
		JLabel img3 = new JLabel();
		img3.setBounds(325, 10, width, height);
		contentPane.add(img3);
		
		img1.setIcon(image1);
		img2.setIcon(image1);
		img3.setIcon(image1);
		
		JLabel currentBet = new JLabel("目前籌碼 : ");
		currentBet.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		currentBet.setBounds(35, 111, 76, 15);
		contentPane.add(currentBet);
		
		playerBet = new JLabel("" + this.bet);
		playerBet.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		playerBet.setBounds(112, 111, 96, 15);
		contentPane.add(playerBet);
		
		JSpinner userInputBet = new JSpinner();
		userInputBet.setBounds(112, 152, 96, 21);
		contentPane.add(userInputBet);
		
		JLabel betCount = new JLabel("下注金額 : ");
		betCount.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		betCount.setBounds(35, 154, 76, 15);
		contentPane.add(betCount);
		
		JLabel currentBet_1 = new JLabel("贏得籌碼 : ");
		currentBet_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		currentBet_1.setBounds(245, 111, 76, 15);
		contentPane.add(currentBet_1);
		
		JLabel winBet = new JLabel("0");
		winBet.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		winBet.setBounds(325, 111, 96, 15);
		contentPane.add(winBet);
		
		JLabel msg = new JLabel("");
		msg.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		msg.setBounds(245, 155, 176, 15);
		contentPane.add(msg);
		
		JButton clamBtn = new JButton("領取籌碼");
		clamBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clamBtn.setEnabled(false);
				playerBet.setText(clam(playerBet.getText(), winBet.getText()));
				winBet.setText("0");
			}
		});
		clamBtn.setEnabled(false);
		clamBtn.setBounds(308, 199, 87, 23);
		contentPane.add(clamBtn);
		
		JButton startBtn = new JButton("開始遊戲");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canStart)
				{		
					if (canBet(playerBet.getText(), userInputBet.getValue().toString()))
					{
						JButton stopBtn = new JButton("Stop");
						stopBtn.setBounds(173, 199, 87, 23);
						contentPane.add(stopBtn);
						canStart = false;
						playerBet.setText("" + startBet(playerBet.getText(), userInputBet.getValue().toString()));
						msg.setText("");
						Thread t1 = new Thread(()->{
							
							for ( ;  ; )
							{
								try {
									Thread.sleep(sleepTime);
									int i = changeIcon();
									img1.setIcon(image[i]);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
						
						Thread t2 = new Thread(()->{
							
							for ( ;  ; )
							{
								try {
									Thread.sleep(sleepTime);
									int i = changeIcon();
									img2.setIcon(image[i]);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
						
						Thread t3 = new Thread(()->{
							
							for ( ;  ; )
							{
								try {
									Thread.sleep(sleepTime);
									int i = changeIcon();
									img3.setIcon(image[i]);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
						t1.start();
						t2.start();
						t3.start();
						
						stopBtn.addActionListener(new ActionListener() {
							@SuppressWarnings("deprecation")
							public void actionPerformed(ActionEvent e) {
								if (tabCount == 0)
								{
									t1.stop();
									tabCount++;
								}
								else if (tabCount == 1)
								{
									t2.stop();
									tabCount++;
								}
								else if (tabCount == 2)
								{
									t3.stop();
									tabCount = 0;
									stopBtn.setVisible(false);
									contentPane.remove(stopBtn);
									canStart = true;
									if (img1.getIcon() == img2.getIcon() && img1.getIcon() == img3.getIcon())
									{
										JOptionPane.showMessageDialog(null, "恭喜中獎, 請點選領取籌碼取得您的獎勵 !");
										winBet.setText("" + doubleBet(winBet.getText(), userInputBet.getValue().toString()));
										clamBtn.setEnabled(true);
									}
									else
									{
										msg.setText("可惜, 請再接再厲 !");
									}
								}
							}
						});
					}
					else
						JOptionPane.showMessageDialog(null, "無法開始賭注, 請確認您輸入的下注金額或是已無籌碼 !");
				}
				else
					JOptionPane.showMessageDialog(null, "賭注正在進行中, 請勿重複開始 !");
			}
		});
		startBtn.setBounds(35, 199, 87, 23);
		contentPane.add(startBtn);
		
	}
	
	public boolean canBet(String userOwnBet, String userInputBet) {
		if (userInputBet.equals("0"))
			return false;
		
		if (userOwnBet.equals("0"))
			return false;
		
		if (userInputBet.equals(""))
			return false;
					
		int inputBet = Integer.parseInt(userInputBet);
		
		if (inputBet < 0)
			return false;
		
		int ownBet = Integer.parseInt(userOwnBet);
		
		if (ownBet < inputBet)
			return false;
		
		return true;
	}
	
	public int startBet(String userOwnBet, String userInputBet) {
		int inputBet = Integer.parseInt(userInputBet);
		int ownBet = Integer.parseInt(userOwnBet);
		
		return ownBet - inputBet;
	}
	
	public int changeIcon() {
		int i = (int)(Math.random() * 4);
		
		return i;
	}
	
	public int doubleBet(String wonBet, String userInputBet) {
		int i = Integer.parseInt(userInputBet) * winRate;
		
		if (wonBet.equals("0"))
			return i;
		else
		{
			int j = Integer.parseInt(wonBet);
			return i + j;
		}
			
	}
	
	public String clam(String ownBet, String winBet) {
		int i = Integer.parseInt(ownBet);
		int j = Integer.parseInt(winBet);
		
		String clamBet = "" + (i+j);
		
		return clamBet;
	}
}

class Tdely extends Thread {
	
	public void run() {
		for (int i = 0 ; i < 10 ; i++)
		{
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}