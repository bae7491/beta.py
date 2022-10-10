package ch11;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonEx extends JFrame{
	public ButtonEx() {
		setTitle("제리 감사");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		ImageIcon jerry1 = new ImageIcon("images/Jerry1.jpg");
		ImageIcon jerry2 = new ImageIcon("images/Jerry2.gif");
		
		JButton btn = new JButton(jerry1);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setPressedIcon(jerry2);
		
		c.add(btn, BorderLayout.CENTER);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new ButtonEx();
	}

}
