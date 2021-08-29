package pre_proj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfTitle;
	private JButton btnRent;
	private JButton btnReturn;
	private JButton btnSearch;
	private TablePanel pTable;
	private JPanel pSearch;
	private JPanel pRental;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));
		
		pRental = new JPanel();
		contentPane.add(pRental, BorderLayout.SOUTH);
		pRental.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnRent = new JButton("대여");
		pRental.add(btnRent);
		
		btnReturn = new JButton("반납");
		pRental.add(btnReturn);
		
		pSearch = new JPanel();
		contentPane.add(pSearch, BorderLayout.NORTH);
		pSearch.setLayout(new BorderLayout(10, 0));
		
		tfTitle = new JTextField();
		tfTitle.setText("제목입력");
		tfTitle.setToolTipText("");
		pSearch.add(tfTitle);
		tfTitle.setColumns(10);
		
		btnSearch = new JButton("검색");
		pSearch.add(btnSearch, BorderLayout.EAST);
		btnSearch.addActionListener(this);
		
		pTable = new TablePanel();
		contentPane.add(pTable, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		
	}

	private void actionPerformedBtnSearch(ActionEvent e) {
		String title = tfTitle.getText();
		try {
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\82109\\OneDrive\\바탕 화면\\도서 목록.txt"));

		List<String> list = new ArrayList<String>();
		String str;
		
			while ((str = reader.readLine()) != null) {
				list.add(str);
				System.out.println(str);
				System.out.println(list);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


}
