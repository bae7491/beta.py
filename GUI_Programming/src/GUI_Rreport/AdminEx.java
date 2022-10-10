package GUI_Rreport;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AdminEx extends JFrame {
	JPanel pp1, pp2; // p1, p2를 묶을 Panel(pp1), p2, p3를 묶을 Panel(pp2).
	JPanel p1, p2, p3, p4; // 각각의 기능을 넣을 Panel.
	JLabel la1, la2; // 단어(이름, 전화)를 출력할 Label.
	JTextField tf1, tf2; // 이름, 전화를 입력할 TextField.
	
	JRadioButton[] rbtn = new JRadioButton[2]; // 성벌(남자, 여자)를 체크할 RadioButton.
	String[] gen = {"남자", "여자"}; // 성별을 문자열로 저장.
	ButtonGroup group = new ButtonGroup(); // RadioButton 2개를 그룹으로 묶음.
	
	JCheckBox[] cb = new JCheckBox[5]; // 외국어(영어, 중국어, 일본어, 스페인어, 러시아어)를 중복 체크할 CheckBox.
	String[] str = {"영어", "중국어", "일본어", "스페인어", "러시아어"}; // 체크박스에 나타날 외국어 이름들을 문자열로 저장.
	
	JTextArea ta; // 입력할 리스트를 출력할 TextArea.
	
	JButton[] btn = new JButton[4]; // 입력, 수정 삭제, 취소 기능을 할 Button.
	String[] btnStr = {"입력", "수정", "삭제", "취소"}; // 버튼의 기능을 문자열로 저장.
	
	Container c;
	
	public AdminEx() {
		setTitle("응시자 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(380, 300);
		c = getContentPane();
		
		// p1과 p2를 붙일 pp1, p3와 p4를 붙일 pp2를 추가하고 Layout을 BorderLayout으로 지정.
		pp1 = new JPanel();
		pp1.setLayout(new BorderLayout());
		pp2 = new JPanel();
		pp2.setLayout(new BorderLayout());
		
		
		// Panel 1번에 이름, 전화를 입력할 TextField와 성별을 선택할 RadioButton Group을 추가.
		p1 = new JPanel(); // 이름, 전화, 성별을 선택할 Panel.
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); // Panel 1을 FlowLayout으로 지정하고 중앙에 배치, 좌우 간격을 0, 상하 간격을 10으로 지정.
		
		// label에 Text를 추가하고, TextField에 열의 간격을 배치.
		la1 = new JLabel("이름");
		la2 = new JLabel("전화");
		tf1 = new JTextField(5);
		tf1.setBackground(Color.YELLOW); // tf1의 색깔을 YELLOW로 지정.
		tf2 = new JTextField(10);
		
		// 만들어진 label과 TextField를 순서에 맞게 Panel 1에 붙임.
		p1.add(la1);
		p1.add(tf1);
		p1.add(la2);
		p1.add(tf2);
		
		// RadioButton을 그룹으로 묶고, Panel 1에 추가.
		for (int i = 0; i < rbtn.length; i++) {
			rbtn[i] = new JRadioButton(gen[i]); //RadioButton[i]에 성별을 차례대로 추가.
			group.add(rbtn[i]); // RadioButton을 그룹으로 묶음.
			rbtn[i].setBackground(Color.LIGHT_GRAY); // RadioButton의 바탕색을 LIGHT_GRAY로 지정.
			p1.add(rbtn[i]); // RadioButton을 Panel 1에 추가.
		}
		
		// Panel 2번에 외국어를 선택할 CheckBox를 추가.
		p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT)); // Panel 2를 FlowLayout으로 지정 후, 왼쪽으로 정렬.
		p2.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "가능 외국어 선택")); // Panel 2에 GRAY 색으로 테두리를 지정하고, 이름을 "가능 외국어 선택"으로 지정.
		
		// CheckBox 배치.
		for (int i = 0; i < cb.length; i++) {
			cb[i] = new JCheckBox(str[i]); // CheckBox[i]에 외국어를 차례대로 추가.
			p2.add(cb[i]); // CheckBox들을 Panel 2에 배치.
		}
		
		// Panel 3번에 입력된 값들을 출력할 TextArea를 추가.
		p3 = new JPanel();
		p3.setLayout(new FlowLayout()); // Panel 3번을 FlowLayout으로 지정.
		ta = new JTextArea(7, 34); // TextArea의 크기를 7열로 34줄로 만든다.
		ta.setEditable(false);
		
		p3.add(ta); // Panel 3번에 TextArea를 추가.
		
		// Panel 4번에 입력, 수정, 삭제, 취소 기능이 있는 Button을 추가다.
		p4  = new JPanel();
		p4.setLayout(new FlowLayout()); // Panel 4번을 FlowLayout으로 지정.
		
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(btnStr[i]); // Button[i]에 버튼의 기능을 Text를 넣어서 추가.
			btn[i].addActionListener(new ActionHandler()); // 각각의 Button에 ActionListener를 추가.
			p4.add(btn[i]);
		}
		
		// p1, p2를 pp1에, p3, p4를 pp2에 배치.
		pp1.add(p1, BorderLayout.NORTH);
		pp1.add(p2, BorderLayout.SOUTH);
		pp2.add(p3, BorderLayout.NORTH);
		pp2.add(p4, BorderLayout.SOUTH);
		
		// 묶어둔 pp1과 pp2를 c에 배치.
		c.add(pp1, BorderLayout.NORTH);
		c.add(pp2, BorderLayout.SOUTH);
		c.add(new JScrollPane(ta));
		
		setVisible(true);
	}
	
	// Button이 클릭될때, 반응.
	class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn[0]) { // Action이 일어난 것이 Button[0]번 이면,
				if (rbtn[0].isSelected()) { // RadioButton[0](성별이 남자)이 선택이 되면,
					// TextArea에 이름, 전화번호, 성별 순으로 각각의 TextField, RadioButton의 값을 추가.
					ta.append("이름 : " + tf1.getText() + ", 전화번호 : " + tf2.getText() + ", 성별 : " + rbtn[0].getText());
					ta.append(", 선택 과목 : ");
					
					// 선택한 CheckBox(외국어)를 TextArea에 추가.
					for (int i = 0; i < cb.length; i++) {
						if (cb[i].isSelected()) {
							ta.append(cb[i].getText() + " ");
						}
					}
					
					ta.append("\n"); // TextArea에 줄넘김을 적용하여, 다음에 입력되는 값은 다음 줄로 나오게 지정.
					
					// TextField들을 지움.
					tf1.setText("");
					tf2.setText("");
				}
				
				else if (rbtn[1].isSelected()) { // RadioButton[1](성별이 여자)이 선택이 되면,
					// TextArea에 이름, 전화번호, 성별 순으로 각각의 TextField, RadioButton의 값을 추가.
					ta.append("이름 : " + tf1.getText() + ", 전화번호 : " + tf2.getText() + ", 성별 : " + rbtn[1].getText());
					ta.append(", 선택 과목 : ");
					
					// 선택한 CheckBox(외국어)를 TextArea에 추가.
					for (int i = 0; i < cb.length; i++) {
						if (cb[i].isSelected()) {
							ta.append(cb[i].getText() + " ");
						}
					}
					
					ta.append("\n"); // TextArea에 줄넘김을 적용하여, 다음에 입력되는 값은 다음 줄로 나오게 지정.
					
					// TextField들을 지움.
					tf1.setText("");
					tf2.setText("");
				}
			}
			
			// Action이 일어난 Button이 1, 2, 3번이라면,
			else if ((e.getSource() == btn[1]) || (e.getSource() == btn[2]) || (e.getSource() == btn[3])) {
				ta.setText(" "); // TextArea의 내용을 모두 삭제.
			}
		}
		
	}
	
	public static void main(String[] args) {
		new AdminEx();
	}

}
