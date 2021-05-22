package yaopin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;








public class MedicinePanel extends JPanel implements ActionListener {
	private JComboBox medicineBox;
	private JScrollPane scrollPanel;
	private JButton addButton, delButton, searchButton, outputButton,updateButton;
	private static String mno,name,type,specifications,validity,batchno,amount,jhPrice,lsPrice,note;
	private static  MyTable table;
	private static DefaultTableModel model = null;
	private JTextField SelText;
	private ImageIcon searchIcon;
	private JLabel searchLabel;
	

	MedicinePanel() {
		init();
		setLayout(null);
		Color c = new Color(153,204,204);
		this.setBackground(c);

	}

	void init() {
		setIcon();
		setButton();
		setText();
		setTable();
		setListener();
		setOutput();
		
		this.setBackground(Color.white);
	}

	private void setIcon() {
		// TODO Auto-generated method stub
		searchIcon = new ImageIcon("search.png");
		searchLabel = new JLabel(searchIcon);
		searchLabel.setBounds(100, 28, 32, 32);
		this.add(searchLabel);
	}

	private void setOutput() {
		try {
			SQL.MedicineQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void setTable() {
		String[][] datas = {};
		String[] titles = { "���","ҩƷ���", "���ͱ��","ҩƷ����",  "���", "��Ч��", "����", "������", "���ۼ�", "�����", "��ע" };
		model = new DefaultTableModel(datas, titles);
		table = new MyTable(model);

		scrollPanel = new JScrollPane(table);
		scrollPanel.setBounds(100, 60, 750, 445);
		add(scrollPanel);

		RowSorter sorter = new TableRowSorter(model); // ˮƽ������
		table.setRowSorter(sorter);

		TableColumn column = null;


		
		table.getColumn("���").setPreferredWidth(30);
		table.getColumn("ҩƷ���").setPreferredWidth(60);
		table.getColumn("���ͱ��").setPreferredWidth(60);
		table.getColumn("ҩƷ����").setPreferredWidth(110);
		table.getColumn("���").setPreferredWidth(60);
		table.getColumn("��Ч��").setPreferredWidth(60);
		table.getColumn("����").setPreferredWidth(140);
		table.getColumn("������").setPreferredWidth(60);
		table.getColumn("���ۼ�").setPreferredWidth(60);
		table.getColumn("�����").setPreferredWidth(60);
		 this.setBackground(Color.white);

		
		SQL.setTableCenter(table, table.getColumnCount());

	}
	
	


	public static MyTable GetTable() {
		return table;
	}
	
	
	private void setText() {
		SelText = new JTextField(18);
		SelText.setBounds(130, 30, 120, 25);
		add(SelText);


JLabel employeeLabel = new JLabel("���ã�"+LoginWindow.getUser());
		employeeLabel.setBounds(880, 20, 60, 60);
		add(employeeLabel);
	}

	private void setButton() {

		searchButton = new JButton("����");
		addButton = new JButton("���");
		delButton = new JButton("ɾ��");
		outputButton = new JButton("��ʾ������Ϣ");
		updateButton = new JButton("�޸�");

		Font font = new Font("΢���ź�", Font.PLAIN, 12);
		searchButton.setFont(font);
		addButton.setFont(font);
		delButton.setFont(font);
		outputButton.setFont(font);
		updateButton.setFont(font);

		searchButton.setBounds(260, 30, 60, 25);
		addButton.setBounds(500, 510, 60, 30);
		updateButton.setBounds(575, 510, 60, 30);
		delButton.setBounds(650, 510, 60, 30);
		outputButton.setBounds(725, 510, 120, 30);

		add(searchButton);
		add(updateButton);
		add(addButton);
		add(delButton);
		add(outputButton);

	}


	void setListener() {
		SQL.setmedicinemodel(model);
		
		addButton.addActionListener(this);
		delButton.addActionListener(this);
		searchButton.addActionListener(this);
		updateButton.addActionListener(this);
		outputButton.addActionListener(this);
	
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					new MedicineUpdateMess();
				}else if(e.getButton() == MouseEvent.BUTTON1) {
					getMedicineMess();
					MedicineUpdateMess.setMedicine(mno,name,type,specifications,validity,batchno,amount,jhPrice,lsPrice,note);
				}
			}
		});
		 
		//���������ǰ����ڱ����ĵط�ȡ�����ѡ�м�ҩƷ�������
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					table.clearSelection();// ��ձ��ѡ��
					MedicineUpdateMess.setMedicine(null, null, null, null, null, null, null,null,null,null); // ҩƷ�������
				}
			}
		});
		
	}
	



	

	static void getMedicineMess() {

		int Medicine_row = table.getSelectedRow();
		mno = (String) table.getValueAt(Medicine_row, model.findColumn("ҩƷ���"));// ����ֵΪ row�� column�ĵ�Ԫ��ֵ��
		name = (String) table.getValueAt(Medicine_row, model.findColumn("ҩƷ����"));
		type = (String) table.getValueAt(Medicine_row, model.findColumn("���ͱ��"));
		specifications = (String) table.getValueAt(Medicine_row, model.findColumn("���"));
		batchno = (String) table.getValueAt(Medicine_row, model.findColumn("����"));
		validity = (String) table.getValueAt(Medicine_row, model.findColumn("��Ч��"));
		jhPrice = (String) table.getValueAt(Medicine_row, model.findColumn("������"));
		lsPrice = (String) table.getValueAt(Medicine_row, model.findColumn("���ۼ�"));
		amount = (String) table.getValueAt(Medicine_row, model.findColumn("�����"));
		note = (String) table.getValueAt(Medicine_row, model.findColumn("��ע"));
		

	}
	


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			new MedicineAddMess();
			
		}else if(e.getSource() == updateButton) {
			new MedicineUpdateMess();
		}
		else if(e.getSource() == delButton) {
			int result = JOptionPane.showConfirmDialog(null, "�ò����޷��ָ����Ƿ������", "ɾ������", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.MedicineDelete(mno);
					SQL.MedicineQuery();//ɾ�����ѯ����ҩƷ��Ϣ
				} catch (Exception e1) {//SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == searchButton) {
			
			
			String conditions = SelText.getText();
			
			try {
				SQL.MedicineSel(conditions);//����������ҩƷ��Ϣ

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if(e.getSource() == outputButton) {
			
			try {
				SQL.MedicineQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
