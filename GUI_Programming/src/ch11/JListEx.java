package ch11;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class JListEx extends JFrame{
	JList<String> list;
	JTextField tf;	
	
	public JListEx() {
		setTitle("리스트 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new FlowLayout());
		
		String[] cities = {"서울", "부산", "대구", "대전", "광주", "인천", "강릉"};
		list = new JList<String>(cities);		
		tf = new JTextField(10);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(5);
		
		list.addListSelectionListener(new ListSelectionHandler());
		
		add(new JScrollPane(list));
		add(tf);
		
		setVisible(true);
	}
	
	class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			tf.setText(list.getSelectedValue());
			
		}
		
	}

	public static void main(String[] args) {
		new JListEx();
	}

}
