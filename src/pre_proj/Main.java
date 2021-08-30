package pre_proj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
		btnRent.addActionListener(this);
		pRental.add(btnRent);

		btnReturn = new JButton("반납");
		btnReturn.addActionListener(this);
		pRental.add(btnReturn);

		pSearch = new JPanel();
		contentPane.add(pSearch, BorderLayout.NORTH);
		pSearch.setLayout(new BorderLayout(10, 0));

		tfTitle = new JTextField();
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
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		if (e.getSource() == btnRent) {
			try {
				actionPerformedBtnRent(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnReturn) {
			try {
				actionPerformedBtnReturn(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void actionPerformedBtnSearch(ActionEvent e) {
		String title = tfTitle.getText();
		pTable.setWords(title);
		pTable.loadData();
		pTable.revalidate();
	}

	private void actionPerformedBtnRent(ActionEvent e) throws IOException {
		String row = pTable.getItem()[0];

		File file = new File("C:\\Users\\3P003\\Desktop\\도서 목록.txt");

		FileInputStream inFile = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));

		String str;
		ArrayList list = new ArrayList<String>();
		try {
			while ((str = reader.readLine()) != null) {
				System.out.println(str.split("/")[0]);

				if (str.split("/")[0].equals(row/* 책번호 입력. */)) {

					String updateData = "";

					// 날짜....
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");

					// cal.add(Calendar.MONTH, 2);

					updateData += str.split("/")[0] + "/";
					updateData += str.split("/")[1] + "/";
					updateData += str.split("/")[2] + "/";
					updateData += str.split("/")[3] + "/";
					updateData += str.split("/")[4] + "/";
					updateData += "대여중/";
					updateData += df.format(cal.getTime()) + "/";

					cal.add(Calendar.DATE, 14);
					updateData += df.format(cal.getTime());

					list.add(updateData);
				} else {
					list.add(str);
				}

			}



			FileOutputStream outFile = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(outFile);
			
			for (int i = 0; i < list.size(); i++) {
				writer.println((String) list.get(i));

				System.out.println(list.get(i));
			}

			writer.close();
			reader.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		pTable.loadData();
		pTable.revalidate();
		contentPane.revalidate();
	}

	private void actionPerformedBtnReturn(ActionEvent e) throws IOException {
		String row = pTable.getItem()[0];

		File file = new File("C:\\Users\\3P003\\Desktop\\도서 목록.txt");

		FileInputStream inFile = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));
		//File temp = null;
		//temp = new File("C:\\Users\\3P003\\Desktop\\임시 도서 목록.txt");
		//if (!temp.exists()) {
			//temp.createNewFile();
		//}

		
		

		String str;
		ArrayList list = new ArrayList<String>();
		try {
			while ((str = reader.readLine()) != null) {
				System.out.println(str.split("/")[0]);

				if (str.split("/")[0].equals(row/* 책번호 입력. */)) {

					String updateData = "";

					// 날짜....
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");

					// cal.add(Calendar.MONTH, 2);

					updateData += str.split("/")[0] + "/";
					updateData += str.split("/")[1] + "/";
					updateData += str.split("/")[2] + "/";
					updateData += str.split("/")[3] + "/";
					updateData += str.split("/")[4] + "/";
					updateData += "대여가능/-/-";

					cal.add(Calendar.DATE, 14);

					list.add(updateData);
				} else {
					list.add(str);
				}

			}

			file.delete();
			file.createNewFile();
			FileOutputStream outFile = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(outFile);
			
			for (int i = 0; i < list.size(); i++) {
				writer.println((String) list.get(i));

				System.out.println(list.get(i));
			}

			writer.close();
			reader.close();
			//temp.renameTo(file);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		pTable.loadData();
		pTable.revalidate();
	}
}
