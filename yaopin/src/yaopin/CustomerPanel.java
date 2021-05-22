package yaopin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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

import yaopin.MyTable;
import yaopin.SQL;
import yaopin.CustomerAddMess;
import yaopin.CustomerUpdateMess;

public class CustomerPanel extends JPanel implements ActionListener{
	private JTextField jt;
	private JComboBox box2;
	private static DefaultTableModel model=null;
	private JScrollPane scroll;
	private static MyTable table;
	private JButton AddButton,DelButton,SearchButton,OutputButton,UpdateButton;
	private static String cno,name,address,tell;
	private JLabel biaoti;
	public CustomerPanel() {
		init();
		setLayout(null); 
	}
	void init() {
		setcheck();
		setButton();
		checkTable();
		setListener();
		setOutput();
		this.setBackground(Color.white);
		
	}
	 void setOutput() {
		
		
	}

	void setcheck() {
		biaoti=new JLabel("�ͻ���Ϣ����");
		biaoti.setFont(new Font("���� ", Font.BOLD, 22));
		biaoti.setBounds(50, 0, 200, 95);
		add(biaoti);
		
	
		String check[]= {"��ѯ����","���","����","��ַ","�绰"};
		box2=new JComboBox(check);
		box2.setBounds(400,30,110,25);
		jt = new JTextField();
		jt.setBounds(520, 30, 120, 25);
		
		SearchButton = new JButton("����");
		SearchButton.setBounds(650, 30, 60, 25);
        add(box2);
		add(jt);
		add(SearchButton);
	}
	void setButton() {
		AddButton = new JButton("���");
		DelButton = new JButton("ɾ��");
		OutputButton = new JButton("��ʾ");
		UpdateButton = new JButton("�޸�");

		AddButton.setBounds(445, 530, 90, 30);
		DelButton.setBounds(555, 530, 90, 30);
		OutputButton.setBounds(665, 530, 90, 30);
		UpdateButton.setBounds(765, 530, 90, 30);
		
		add(AddButton);
		add(DelButton);
		add(OutputButton);
		add(UpdateButton);
	}
	void checkTable() {
		String[][] datas = {};
		String[] titles = { "���", "�ͻ����", "�ͻ�����", "��ַ", "��ϵ�绰" };

		model = new DefaultTableModel(datas, titles);
		table = new MyTable(model);

		scroll = new JScrollPane(table);
		scroll.setBounds(80, 60, 930, 445);
		add(scroll);

		RowSorter sorter = new TableRowSorter(model); // ˮƽ������
		table.setRowSorter(sorter);

		// ���ñ����
		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(30);
			} else if (i == 1 || i == 5)
				column.setPreferredWidth(90);
			else if (i == 2)
				column.setPreferredWidth(110);
			else if (i == 7)
				column.setPreferredWidth(30);
			else
				column.setPreferredWidth(50);
		}
		this.setBackground(Color.white);
		SQL.setTableCenter(table, table.getColumnCount());
	}
	public static MyTable GetTable() {
	
		return table;
	}
	void setListener() {
		SQL.setcustomermodel(model);

		AddButton.addActionListener(this);
		DelButton.addActionListener(this);
		UpdateButton.addActionListener(this);
		SearchButton.addActionListener(this);
		OutputButton.addActionListener(this);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					new CustomerUpdateMess();
//					new BookDetail_OnlyRead();
				} else if (e.getButton() == MouseEvent.BUTTON1) {
					getCustomerMess();
					CustomerUpdateMess.setCustomer(cno, name, address, tell);
				}
			}
		});

		// ���������ǰ����ڱ����ĵط�ȡ�����ѡ�м��ͻ��������
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					table.clearSelection();// ��ձ��ѡ��
					CustomerAddMess.setCustomer(null, null, null, null); // �ͻ��������
				}
			}
		});

	}
	static void getCustomerMess() {

		int customer_row = table.getSelectedRow();
		cno = (String) table.getValueAt(customer_row, model.findColumn("�ͻ����"));// ����ֵΪ row�� column�ĵ�Ԫ��ֵ��
		name = (String) table.getValueAt(customer_row, model.findColumn("�ͻ�����"));
		address = (String) table.getValueAt(customer_row, model.findColumn("��ַ"));
		tell = (String) table.getValueAt(customer_row, model.findColumn("��ϵ�绰"));

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == AddButton) {
			new CustomerAddMess();
		} else if (e.getSource() == UpdateButton) {
			new CustomerUpdateMess();
		} else if (e.getSource() == DelButton) {
			int result = JOptionPane.showConfirmDialog(null, "�ò����޷��ָ����Ƿ������", "ɾ������", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.CustomerDelete(cno);
					SQL.CustomerQuery();// ɾ�����ѯ���пͻ���Ϣ
				} catch (Exception e1) {// SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == SearchButton) {
			
			
			String conditions = jt.getText();
			
			try {
				SQL.CustomerSel(conditions);//����������ҩƷ��Ϣ

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if (e.getSource() == OutputButton) {

			try {
				SQL.CustomerQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
		
	}


