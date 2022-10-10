package ch10;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComponentEx extends JFrame{
	Container c = getContentPane();
	JButton button1, button2, button3;
	
	public JComponentEx() {
		setTitle("Component Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460,200);
		
		button1 = new JButton("BUTTON1");
		button2 = new JButton("BUTTON2");
		button3 = new JButton("BUTTON3");
		
		c.add(button1);
		c.add(button2);
		c.add(button3);
		button3.addMouseListener(new MouseHandler());
		button3.addMouseWheelListener(new MouseHandler());
		
		c.setLayout(new FlowLayout());
		
		Font f = new Font("Arial", Font.ITALIC, 24);
		button1.setFont(f);
		button1.setForeground(new Color(255, 255, 0));
		button1.setBackground(Color.CYAN);
		button2.setEnabled(false);
		button3.setFont(f);
		
		setVisible(true);
	}
	
	class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			setTitle(e.getX() + ", " + e.getY());
		}
		
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() > 0) {
				button3.setFont(new Font(button3.getFont().getFontName(), Font.ITALIC, button3.getFont().getSize() + 1));
			}
			
			else {
				button3.setFont(new Font(button3.getFont().getFontName(), Font.ITALIC, button3.getFont().getSize() - 1));
			}
		}
	}
	
	public static void main(String[] args) {
		new JComponentEx();
	}

}
