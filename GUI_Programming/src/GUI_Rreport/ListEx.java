package GUI_Rreport;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Arrays;

public class ListEx extends JFrame {
	JList<String> list; // List를 추가.
	JTextField tf1; // List에 추가된 값들을 출력해줄 TextField 추가.
	String[] cities = {"서울", "런던", "파리", "시드니", "로스엔젤레스", "뉴욕", "모스크바", "쮜리히", "벤쿠버"}; // List에 추가될 도시 이름들을 지정.
	
	public ListEx() {
		setTitle("List Test - Multi Selection Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// List 설정.
		list =  new JList<String>(cities); // List에 cities String배열의 값들을 추가함.
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // List를 다중 선택이 가능하게 설정.
		list.setVisibleRowCount(7); // 보이는 List의 값은 7개로 설정.
		list.addListSelectionListener(new ListSelectionHandler()); // List의 값들이 선택될때마다 ListSelectionListener가 실행.
		add(new JScrollPane(list)); // List에 Scroll을 추가.
		
		// TextField 설정.
		tf1 = new JTextField(28); // TextField1 의 열의 길이를 28로 설정.
		add(tf1); // TextField1을 추가.
		tf1.setEditable(false); // TextField 1은 수정이 불가능함.
		tf1.setBackground(Color.WHITE); // TextField 1의 바탕을 WHITE색으로 지정.
		
		setVisible(true);
	}
	
	// List의 값들이 선택될 때 사용.
	class ListSelectionHandler implements ListSelectionListener {
		
		// List의 값이 바꾸면,
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int[] arr = list.getSelectedIndices(); // List의 값을 받아와서 정수 배열로 바꾼뒤 arr에 저장.
			String[] str = new String[arr.length]; // 정수 배열 arr의 값을 String 배열인 str로 변환.
			String str1; // String 배열인 str을 String형으로 변환할때 사용.
			
			// 두번 출력되는 것을 방지하기 위해 사용.
			if (e.getValueIsAdjusting()) {				
				for (int i = 0; i < arr.length; i++) {
					str[i] = cities[arr[i]]; // 저장된 도시들의 값을 str배열에 Index에 맞게 저장.
				}
				
				str1 = Arrays.toString(str); // 배열을 String형으로 변환.
				tf1.setText(str1); // 변환된 String 값들을 TextField에 출력.
			}
			
		}
		
	}

	public static void main(String[] args) {
		new ListEx();
	}

}
