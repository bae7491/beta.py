package ch11;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LabelEx extends JFrame{
	JLabel label1, label2, label3;
	
	public LabelEx() {
		setTitle("레이블 처리하기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		
		ImageIcon apple = new ImageIcon("images/apple.jpg");
		ImageIcon image2 = new ImageIcon("images/키퍼.jpg");
		ImageIcon image3 = new ImageIcon("images/코드51(매일하는점심).jpg");
		
		label1 = new JLabel(apple);
		label2 = new JLabel(image2);
		label3 = new JLabel(image3);
		
		setLayout(new FlowLayout());
		
		label2.setFont(new Font("고딕체", Font.PLAIN, 48));
		
		add(label1);
		add(label2);
		add(label3);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new LabelEx();
	}

}
