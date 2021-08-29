package pre_proj;

import java.awt.BorderLayout;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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

@SuppressWarnings("serial")
public class ConsumerListPanel<T> extends JPanel {
	private JTable table;
	private List<Consumer> list;// dao만들어야함. 만들고 service 구현 후 initlist 구현해야함.
	private String conName;

	public void setConName(String conName) {
		this.conName = conName;
	}

	public ConsumerListPanel() {
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

	public Consumer getItem() {
		int row = table.getSelectedRow();

		String conName = (String) table.getValueAt(row, 0);
		String conGrade = (String) table.getValueAt(row, 1);
		String conPhone = (String) table.getValueAt(row, 2);
		if (row == -1) {

			// exception을 던지고 그다음 메세지 출력해야돼 인환아
			JOptionPane.showMessageDialog(null, "선택을 안햇자나");
		}
		// 리턴이 문제임 인환아 indexOf메서드는 인덱스가 0인거랑 비교하는 듯?
		return new Consumer(conName, new GradeDc(conGrade), conPhone);

	}

	public DefaultTableModel getModel() {
		CustomTableModel model = new CustomTableModel(/* getData(), getColumnNames() */);
		return model;
	}

	public void loadData() {

		if (conName == null) {
			initList();

		} else {
			setList(conName);
		}
		setData();
	}

	public void initList() {
		list = service.showConsumers();
	}

	public void setList(String conName) {
		list = service.selectConsumersByName(conName);
	}

	// 아직까지 서비스 필요성 없음
	public void setService(ConsumerService service) {
		this.service = service;
	}

	public void setPopupMenu(JPopupMenu popMenu) {
		table.setComponentPopupMenu(popMenu);
	}

	// 테이블 만들기
	public void setData() {
		try {

			Object[][] data = new Object[list.size()][];
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

	private Object[] getColumnNames() {
		return new String[] { "고객명", "고객등급", "고객번호" };
	}

	private Object[] toArray(Consumer consumer) {
		return new Object[] { consumer.getConName(), consumer.getConGrade().getGrade(), consumer.getConPhone() };
	}

	// 모델
	private class CustomTableModel extends DefaultTableModel {

		public CustomTableModel() {
		}

		public CustomTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	// 정렬
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		// 컬럼별 너비 조정
		setTableCellWidth(90, 90, 210);
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