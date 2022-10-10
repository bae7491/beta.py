package GUI_Rreport;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class NumberClickEx extends JFrame{
	JLabel[] la = new JLabel[26]; // 게임에 추가할 Label을 25개 추가.
	JTextField time; // 시간을 나타낼 TextField.
	JButton btn; // "GAME AGAIN" 기능을 할 Button.
	JPanel p1, p2; // 게임 화면을 나타내는 Panel과 시간, GAME AGAIN 버튼을 넣을 Panel.
	Container c = getContentPane();
	
	int count = 1; // 클릭한 것을 측정하기 위한 값.
	
	public NumberClickEx() {
		setTitle("Mouse Test - Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		// Panel1 을 지정하고 Layout은 따로 지정하지 않음.
		p1 = new JPanel();
		p1.setLayout(null);		
		
		// Panel 2를 지정하고 Layout을 FlowLayout으로 지정.
		p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		
		// 시간을 나타낼 TextField를 추가.
		time = new JTextField("0 Sec", 15); // TextField에 "0 Sec" 문자열을 추가하고, 열의 길이를 15로 지정.
		time.setHorizontalAlignment(JLabel.CENTER); // TextField에 들어갈 문자열을 중앙에 나오게 배치.
		time.setEditable(false); // TextField 수정은 불가능함.
		
		setGame(); // 처음 게임을 초기화.
		
		btn = new JButton("GAME AGAIN"); // GAME AGAIN 기능을 수행할 Button을 추가.
		btn.addActionListener(new ActionHandler()); // Button에 ActionListener를 추가.
		
		p2.add(time); // Panel 2에 time(TextField)을 추가.
		p2.add(btn); // Panel 2에 btn(Button)을 추가.
		
		c.add(p1, BorderLayout.CENTER); // Panel 1을 BorderLayout의 중앙에 배치.
		c.add(p2, BorderLayout.SOUTH); // Panel 2를 BorderLayout의 남쪽에 배치.
		
		setVisible(true);
	}
	
	// 처음 실행 했을 때 Label들을 배치.
	void setGame() {
		for (int i = 1; i < la.length; i++) {
			la[i] = new JLabel(""); // Label의 수만큼 Label을 만듦.
			la[i].addMouseListener(new MouseHandler()); // Label을 클릭할 떄 수행하기 위해 MouseListener를 추가.
			
			la[i].setText("" + i); // Label에 숫자를 순서대로 추가.
			la[i].setSize(40, 30); // Label의 크기는 40x30으로 지정.
			la[i].setFont(new Font("", Font.BOLD, 20)); // Label을 지정된 폰트로 지정.
			la[i].setOpaque(true); // 투명한 Label에 불투명도를 적용.
			la[i].setBackground(Color.YELLOW); // Label의 색을 YELLOW로 변경.
			
			// 가로 길이 x와 세로 길이 y를 random으로 무작위로 만든 뒤, 배치.
			int x = (int)(Math.random() * 750);
			int y = (int)(Math.random() * 500);
			la[i].setLocation(x, y); // 무작위로 만들어진 x, y에 맞게 배치.
			
			p1.add(la[i]); // 각 Label들을 Panel 1에 배치.
		}
	}
	
	// 게임을 리셋 했을 때 Label을 새로 배치.
	void newGame() {
		count = 1; // 올라가 있을 수 있는 count의 값을 1로 초기화.
		time.setText("0 Sec"); // 시간이 지나가 있을 수 있는 time을 0초로 초기화.
		
		// 라벨을 새로 시작한 것에 맞게 다시 배치. (무작위)
		for (int i = 1; i < la.length; i++) {
			la[i].setVisible(true); // 새로운 게임이 시작되면서 사라진 Label을 보이게 바꿈.
			
			la[i].addMouseListener(new MouseHandler()); // Label을 클릭할 떄 수행하기 위해 MouseListener를 추가.
			la[i].setText("" + i); // Label에 숫자를 순서대로 추가.
			la[i].setSize(40, 30); // Label의 크기는 40x30으로 지정.
			la[i].setFont(new Font("", Font.BOLD, 20)); // Label을 지정된 폰트로 지정.
			la[i].setOpaque(true); // 투명한 Label에 불투명도를 적용.
			la[i].setBackground(Color.YELLOW); // Label의 색을 YELLOW로 변경.
			
			// 가로 길이 x와 세로 길이 y를 random으로 무작위로 만든 뒤, 배치.
			int x = (int)(Math.random() * 750);
			int y = (int)(Math.random() * 500);
			la[i].setLocation(x, y); // 무작위로 만들어진 x, y에 맞게 배치.
			
			p1.add(la[i]); // 각 Label들을 Panel 1에 배치.
		}
		
	}
	
	class ActionHandler implements ActionListener {	
		// GAME AGAIN Button을 누르면 실행됨.
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn) { // Action이 일어난게 Button이면,
				newGame(); // newGame()을 실행. (게임을 새로 시작.)
			}
		}
		
	}
	
	class MouseHandler extends MouseAdapter {
		long beforeTime = System.currentTimeMillis(); // 코드를 수행하기 전 시간 측정.
		
		// Mouse가 클릭되면,
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel la1 = (JLabel)e.getSource(); // Label 1에 Label의 값을 가져옴.
			
			
			// Label 1에 있는 값이 Label[i]와 값이 같다면,
			if(la[count] == la1) {
				la1.setVisible(false); // Label 1번을 보이지 않게 바꿈.
				count++; // count 값을 증가.
			}
			
			long afterTime = System.currentTimeMillis(); // 코드를 수행한 후 시간 측정.
			long secDiffTime = (afterTime - beforeTime) / 1000; // 수행전과 수행후의 시간 차이를 계산.
			
			time.setText(Long.valueOf(secDiffTime) + " Sec"); // 계산한 값을 String형으로 바꿈.
			
		}
		
	}

	public static void main(String[] args) {
		new NumberClickEx();
	}

}
