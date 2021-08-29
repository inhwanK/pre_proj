package pre_proj;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TablePanel extends JPanel {
	
	private JTable table;
	private String bookName;
	private List<String> list;
	private String words = null;
	/**
	 * Create the panel.
	 */
	
	public void setWords(String words) {
		this.words = words;
	}
	
	
	public TablePanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(getModel());
		loadData();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}

	public DefaultTableModel getModel() {
		CustomTableModel model = new CustomTableModel(/* getData(), getColumnNames() */);
		return model;
	}

	public void initList(String words) {

		try {
			if(words == null) {
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\82109\\OneDrive\\바탕 화면\\도서 목록.txt"));
				list = new ArrayList<String>();
				String str;
				while ((str = reader.readLine()) != null) {
					list.add(str);
					System.out.println(str);
					System.out.println(list);
				}
			}else {
				// 책제목에 words를 포함하는 것만 리스트에 추가.
				System.out.println("흠");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadData() {
		initList(words);
		setData();
	}

	// 테이블 만들기
	public void setData() {
		try {

			String[][] data = new String[list.size()][];
			for (int i = 0; i < data.length; i++) {
				data[i] = toArray(list.get(i));
			}

			CustomTableModel model = new CustomTableModel(data, getColumnNames());
			table.setModel(model);
			RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			table.setRowSorter(sorter);
			setAlignAndWidth();

		} catch (NullPointerException e) {
		}

	}

	private String[] toArray(String str) {
		String[] strArr = str.split("/");
		return strArr;
	}

	private String[] getColumnNames() {
		return new String[] { "책번호", "제목", "분류", "위치", "언어", "대여정보"};
	}

	// 모델
	private class CustomTableModel extends DefaultTableModel {

		public CustomTableModel() {
		}

		public CustomTableModel(String[][] data, String[] objects) {
			super(data, objects);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	// 정렬
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);
		// 컬럼별 너비 조정
		setTableCellWidth(100, 170, 100, 100, 100, 100 );
	}

	protected void setTableCellWidth(int... width) {
		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	protected void setTableCellAlign(int align, int... idx) {
		TableColumnModel tcm = table.getColumnModel();

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

}
