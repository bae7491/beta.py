package ch11;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseListenerEX extends JFrame{
	JLabel la = new JLabel("Hello");
	Container c = getContentPane();
	
	public MouseListenerEX() {
		setTitle("Mouse 이벤트 예제");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c.addMouseListener(new MyMouseListener());
		c.addKeyListener(new KeyListener());
		
		c.setLayout(null);
		la.setSize(100, 20);
		la.setLocation(30, 30);
		c.add(la);
		
		setSize(250, 250);
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus();
	}
	
	class KeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				la.setLocation(la.getX() - 1, la.getY());
			}
			
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				la.setLocation(la.getX() + 1, la.getY());
			}
			
			else if (e.getKeyCode() == KeyEvent.VK_UP) {
				la.setLocation(la.getX(), la.getY() + 1);
			}
			
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				la.setLocation(la.getX(), la.getY() - 1);
			}
			
			la.setText("x = " + la.getX() + ", y = " + la.getY());
		}
	}
	
	class MyMouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			la.setText("x = " + x + ", y = " + y);
			la.setLocation(x, y);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			c.setBackground(Color.LIGHT_GRAY);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			c.setBackground(Color.DARK_GRAY);
		}
		
	}
	
	public static void main(String[] args) {
		new MouseListenerEX();
	}

}
