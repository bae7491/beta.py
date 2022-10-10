package GUI_Rreport;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class KeyEx extends JFrame {
	JPanel p1, p2, p3; // Panel 1, 2, 3을 만든다.
	JButton btn1, btn2, btn3; // ALT, CONTROL, SHIFT 키의 기능을 할 Button을 3개 추가.
	JTextField tf1, tf2; // TextField 1, 2를 추가.
	JLabel label1, label2; // Label 1, 2를 추가.
	Font font = new Font("San Serif", Font.BOLD, 20); // Font를 지정된 Font로 지정.
	Container c = getContentPane();

	// apple, banana, cherry를 이미지 파일에서 가져온다.
	ImageIcon apple = new ImageIcon("images/apple.png");
	ImageIcon banana = new ImageIcon("images/banana.png");
	ImageIcon cherry = new ImageIcon("images/cherry.png");

	public KeyEx() {
		setTitle("Key Event Test - with CTRL/SHIFT key");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		c.addKeyListener(new KeyHandler());

		// Panel 1을 만들어서 FlowLayout으로 지정.
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());

		// TextField의 길이를 10으로 지정.
		tf1 = new JTextField(10);
		tf2 = new JTextField(10);

		tf1.addKeyListener(new KeyHandler()); // tf1에 키를 입력받기 위해 keyListener를 사용한다.
		tf2.setEditable(false); // tf2는 수정이 불가능하다.

		// Panel1에 TextField 2개를 붙인다.
		p1.add(tf1);
		p1.add(tf2);

		// Button 3개에 이름을 각각 Alt, Ctrl, Shift으로 만든다.
		btn1 = new JButton("Alt");
		btn2 = new JButton("Ctrl");
		btn3 = new JButton("Shift");

		// Panel1에 만들어둔 버튼 3개를 붙인다.
		p1.add(btn1);
		p1.add(btn2);
		p1.add(btn3);

		// Panel 2을 만들어서 FlowLayout으로 지정.
		p2 = new JPanel();
		p2.setLayout(new FlowLayout());

		// Label 1을 만듦.
		label1 = new JLabel();
		label1.addKeyListener(new KeyHandler()); // 키를 입력받기 위해 KeyListener를 추가.

		p2.add(label1); // Label 1을 Panel 2에 붙임.

		// Panel 3번을 만들어서 FlowLayout으로 지정.
		p3 = new JPanel();
		p3.setLayout(new FlowLayout());

		// Label 2를 만듦.
		label2 = new JLabel();
		label2.addKeyListener(new KeyHandler()); // 키를 입력받기 위해 KeyListener를 추가.

		p3.add(label2); // Label 2를 Panel 3에 붙임.

		// Panel 3개를 ContentPane에 위치에 맞게 배치.
		c.add(p1, BorderLayout.NORTH);
		c.add(p2, BorderLayout.CENTER);
		c.add(p3, BorderLayout.SOUTH);

		setVisible(true);

		c.setFocusable(true);
		c.requestFocus();
	}

	class KeyHandler extends KeyAdapter {
		// 키가 눌러지면,
		public void keyPressed(KeyEvent e) {
			int KeyCode = e.getKeyCode(); // 키가 눌러졌을 때, KeyCode값으로 받아옴.
			char keycode = e.getKeyChar(); // 입력된 키값을 keycode로 받아옴.

			// 입력받은 키가 'a'이면,
			if (keycode == 'a') {
				label1.setIcon(apple); // Label 1에 저장해둔 apple 이미지를 추가.

				label2.setText("Apple"); // Label 2에 "Apple" Text를 추가.
				label2.setFont(font); // Font를 지정해둔 Font로 변경.

				tf2.setText(Integer.toString(KeyCode)); // TextField 2에 입력받은 키를 유니코드값으로 출력.
			}

			// 입력받은 키가 'b'라면,
			else if (keycode == 'b') {
				label1.setIcon(banana); // Label 1에 저장해둔 banana 이미지를 추가.

				label2.setText("Banana"); // Label 2에 "Banana" Text를 추가.
				label2.setFont(font); // Font를 지정해둔 Font로 변경.

				tf2.setText(Integer.toString(KeyCode)); // TextField 2에 입력받은 키를 유니코드값으로 출력.
			}

			// 입력받은 키가 'c'라면,
			else if (keycode == 'c') {
				label1.setIcon(cherry); // Label 1에 저장해둔 cherry 이미지를 추가.

				label2.setText("Cherry"); // Label 2에 "Cherry" Text를 추가.
				label2.setFont(font); // Font를 지정해둔 Font로 변경.

				tf2.setText(Integer.toString(KeyCode)); // TextField 2에 입력받은 키를 유니코드값으로 출력.
			}

			// ALT키가 눌러지면,
			else if (KeyCode == KeyEvent.VK_ALT) {
				// ALT키가 Button 1에 배치되어있으니, Button1은 RED색으로, 나머지 Button은 WHITE색으로 지정.
				btn1.setBackground(Color.RED);
				btn2.setBackground(Color.WHITE);
				btn3.setBackground(Color.WHITE);
				tf2.setText("18"); // TextField 2에 ALT키의 값인 18을 지정.
			}

			// CONTROL키가 눌러지면,
			else if (KeyCode == KeyEvent.VK_CONTROL) {
				// CONTROL키가 Button 2에 배치되어있으니, Button2은 RED색으로, 나머지 Button은 WHITE색으로 지정.
				btn1.setBackground(Color.WHITE);
				btn2.setBackground(Color.RED);
				btn3.setBackground(Color.WHITE);
				tf2.setText("17"); // TextField 2에 CONTROL의 값인 17을 지정.
			}

			// SHIFT키가 눌러지면,
			else if (KeyCode == KeyEvent.VK_SHIFT) {
				// SHIFT키가 Button 3에 배치되어있으니, Button3은 RED색으로, 나머지 Button은 WHITE색으로 지정.
				btn1.setBackground(Color.WHITE);
				btn2.setBackground(Color.WHITE);
				btn3.setBackground(Color.RED);
				tf2.setText("16"); // TextField 2에 SHITF의 값인 16을 지정.
			}

			// 입력받은 키가 'a', 'b', 'c'가 아니라면,
			else {
				label1.setIcon(null); // 이미지를 null로 만들어서 이미지를 없앰.
				label2.setText(null); // Text를 null로 지정해서 지움.

				tf2.setText(Integer.toString(KeyCode)); // TextField 2에 입력받은 키를 유니코드값으로 출력.
			}

		}

	}

	public static void main(String[] args) {
		new KeyEx();
	}

}
