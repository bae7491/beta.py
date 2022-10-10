package ch12;

import javax.swing.*;
import java.awt.*;

public class GraphicEx extends JFrame{
	JPanel panel = new BarPanel();
	
	public GraphicEx() {
		setTitle("Graphic Test - Bar Graphics");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		add(panel);
		
		setVisible(true);
	}
	
	class BarPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			int[] x = {10, 30, 20, 50, 40};
			int maxY = 300;
			
			
			for (int i = 0; i < x.length; i++) {
				g.setColor(Color.GRAY);
				g.fillRect(i * 60 + 50, maxY - x[i], 30, x[i]);
				g.drawString(Integer.toString(x[i]), i * 60 + 50, maxY - (x[i] + 10));
			}
		}
	}

	public static void main(String[] args) {
		new GraphicEx();
	}

}
