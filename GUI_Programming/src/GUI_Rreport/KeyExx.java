package GUI_Rreport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.JFrame;

public class KeyExx extends JFrame {
	 Container c=getContentPane();
	   JPanel p1,p2,p3;
	   JTextField[] tf=new JTextField[2];
	   JButton[] bu=new JButton[3];
	   JLabel label=new JLabel();
	
	public KeyExx() {
		setTitle("Ket event");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        setLayout(new BorderLayout());
        String[] BuName= {"Alt","Ctrl","Shift"};
        
        p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        
        p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        
        p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        
        for (int i = 0; i < tf.length; i++) {
        	tf[i] = new JTextField(10);
        	p1.add(tf[i]);
        }
        
        tf[1].setEditable(false);
        
        for(int i=0;i<bu.length;i++) {
            bu[i]=new JButton(BuName[i]);
            bu[i].addKeyListener(new KeyListener());
            p1.add(bu[i]);
        }
        
        
        ImageIcon apple=new ImageIcon("images/apple.png");
        ImageIcon banana=new ImageIcon("images/banana.png");
        ImageIcon cherry=new ImageIcon("images/cherry.png");

        
        
        
        this.add(p1,BorderLayout.NORTH);
        this.add(p2,BorderLayout.CENTER);
        this.add(p3,BorderLayout.SOUTH);
        label.setFont(new Font("고딕체", Font.BOLD, 30));

        p2.add(label);

        setVisible(true);
	}
	
	 class KeyListener extends KeyAdapter{
         public void keyPressed(KeyEvent e) {
             int KeyCode = e.getKeyCode();
             if(e.getKeyCode()==e.VK_ALT) 
                 bu[0].setBackground(Color.RED);
             else if(e.getKeyCode()==e.VK_CONTROL)
                 bu[1].setBackground(Color.RED);
             else if(e.getKeyCode()==e.VK_SHIFT)
                 bu[2].setBackground(Color.RED);
             
             
             }
         }

	public static void main(String[] args) {
		new KeyExx();
	}

}
