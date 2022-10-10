package GUI_Rreport;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterestEx extends JFrame {	
	JTextField tf1, tf2, tf3, tfCalc; // 텍스트를 입력할 TextField 생성.
	JLabel label1, label2, label3; // TextField의 이름을 나타낼 Label 생성.
	JButton btn; // 계산 기능을 수행할 Button 생성.
	Container c;
	
	public InterestEx() {
		setTitle("이자계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // c의 Layout을 FlowLayout으로 지정하고 중앙에 배치, 좌우간격을 20, 상하간격을 10으로 배치.
		
		// Label에 나올 Text를 추가.
		label1 = new JLabel("원금을 입력하세요");
		label2 = new JLabel("이자를 입력하세요");
		label3 = new JLabel("년수를 입력하세요");
		
		// TextField의 열을 10으로 지정해서 추가.
		tf1 = new JTextField(10);
		tf2 = new JTextField(10);
		tf3 = new JTextField(10);
		
		// label과 TextField를 ContentPane에 붙인다.
		c.add(label1);
		c.add(tf1);
		c.add(label2);
		c.add(tf2);
		c.add(label3);
		c.add(tf3);
		// label을 붙인 뒤, TextField를 붙여야 순서가 제대로 나옴.
		
		// Button을 ContentPane에 붙인다.
		btn = new JButton("계산");
		c.add(btn);
		btn.addActionListener(new ActionHandler()); // Button에 ActionListener를 추가.
		
		// 계산이 끝난 결과를 지정된 TextField에 붙인다.(값은 오른쪽 정렬.)
		tfCalc = new JTextField(20); // 계산 결과를 보여주는 JTextField.
		tfCalc.setEditable(false); // 수정이 불가능 하게 설정.
		tfCalc.setBackground(Color.WHITE); // tfCalc TextField의 바탕을 하얀색으로 표시.
		c.add(tfCalc); // tfCalc를 붙임.
		tfCalc.setHorizontalAlignment(JLabel.RIGHT); // tfCalc TextField를 우측으로 정렬시킨다.
		
		setVisible(true);
	}
	
	// 계산 Button을 눌렸을 때 ActionListener 작동.
	class ActionHandler implements ActionListener {
		double money, rate, year, sum;
		
		// Action이 일어났을 때 수행.
		@Override
		public void actionPerformed(ActionEvent e) {
			money = Double.parseDouble(tf1.getText()); // TextField 1번의 값을 Double형으로 변환.
			rate = Double.parseDouble(tf2.getText()) / 100.0; // TextField 2번의 값을 Double형으로 변환 후, %값이므로 100.0을 나눔.
			year = Double.parseDouble(tf3.getText()); // TextField 3번의 값을 Double형으로 변환.
			
			sum = money * Math.pow(1 + rate, year); // 복리로 계산한 값을 sum에 추가.
			String str = String.valueOf(sum); // TextField에는 int형으로 들어갈 수 없으니, String으로 변환.
			
			tfCalc.setText(str); // String형으로 변환된 복리값을 tfCalc로 출력.
		}
	}

	public static void main(String[] args) {
		new InterestEx();
	}
}
