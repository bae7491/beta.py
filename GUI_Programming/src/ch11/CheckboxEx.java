package ch11;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckboxEx extends JFrame{
	JCheckBox[] check = new JCheckBox[4];
	JLabel label;
	int sum = 0;
	int[] price = {2000, 2800, 3300, 3000};
	
	public CheckboxEx() {
		setTitle("Checkbox Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460, 200);
		setLayout(new FlowLayout());
		
		String[] menu = {"아메리카노", "카페라떼", "망고쥬스", "사과쥬스"};		
		
		ItemHandler itemHandler = new ItemHandler();
		
		for (int i = 0; i < 4; i++) {
			check[i] = new JCheckBox(menu[i]);
			add(check[i]);
			check[i].addItemListener(itemHandler);
		}
		
		label = new JLabel("합계는 " + sum + "입니다");
		label.setFont(new Font("고딕체", Font.BOLD, 36));
		add(label);
		
		setVisible(true);
	}
	
	class ItemHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (e.getItem() == check[0])
					sum += price[0];
				else if (e.getItem() == check[1])
					sum += price[1];
				else if (e.getItem() == check[2])
					sum += price[2];
				else if (e.getItem() == check[3])
					sum += price[3];
				else
					sum += price[4];
			}
			
			else {
				if (e.getItem() == check[0])
					sum -= price[0];
				else if (e.getItem() == check[1])
					sum -= price[1];
				else if (e.getItem() == check[2])
					sum -= price[2];
				else if (e.getItem() == check[3])
					sum -= price[3];
				else
					sum -= price[4];
			}
			
			label.setText("합계는 " + sum + "입니다.");
		}
		
	}

	public static void main(String[] args) {
		new CheckboxEx();
	}

}
