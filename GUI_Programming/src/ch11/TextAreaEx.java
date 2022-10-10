package ch11;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextAreaEx extends JFrame implements ActionListener{
	JLabel label;
	JTextField tf;
	JTextArea ta;
	Container c;
	
	public TextAreaEx() {
		setTitle("텍스트 영역 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		
		c = getContentPane();
		
		label = new JLabel("<ENTER>키를 누르면 추가됩니다.");
		tf = new JTextField(20);
		ta = new JTextArea(7, 20);
		ta.setEditable(false);
		ta.setBackground(Color.LIGHT_GRAY);
		c.setLayout(new FlowLayout());
		
		c.add(label);
		c.add(tf);
		c.add(new JScrollPane(ta));
		tf.addActionListener(this);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ta.setText(tf.getText() + "\n" + ta.getText());
		ta.setBackground(Color.WHITE);
		tf.setText("");
	}

	public static void main(String[] args) {
		new TextAreaEx();
	}
}
