package yaopin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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




public class SupplierPanel extends JPanel implements ActionListener {
//1.����������������
	
	//������:ѡ����������
	private JComboBox suppliersBox;
	//������:�����Ҫ�鿴������Ϣ
	private JScrollPane scrollPanel;
	//��ť����ӡ�ɾ������ѯ����ʾ����ҩƷ
	private JButton addButton, delButton, searchButton, outputButton,updateButton; 
	//��Ӧ����Ϣ�ֶα�����:
	private static String sno, name, address, tell, cardno, bank;
	//���:��ʾ��ѯҩƷ��Ϣ���
	private static MyTable table;
	private static DefaultTableModel model = null;
	//���������ı���
	private JTextField cxText;
	private ImageIcon searchIcon;
	private JLabel searchLabel;
		
//2.��Ӧ�̹���ģ����崰�幹���췽��
	public SupplierPanel() {
		// TODO Auto-generated constructor stub
		init();
		setLayout(null);
		Color c = new Color(153,204,204);
		this.setBackground(c);
		}
		
//3.��������ķ���
	private void init() {
		// TODO Auto-generated method stub
		setIcon();
		setButton();
		setText();
		setTable();
		setListener();
		setOutput();
		
	}
	
private void setText() {
		cxText = new JTextField(18);
		cxText.setBounds(130, 30, 120, 25);
		add(cxText);


JLabel employeeLabel = new JLabel("���ã�"+LoginWindow.getUser());
		employeeLabel.setBounds(880, 20, 60, 60);
		add(employeeLabel);
	}

private void setIcon() {
		// TODO Auto-generated method stub
		searchIcon = new ImageIcon("search.png");
		searchLabel = new JLabel(searchIcon);
		searchLabel.setBounds(100, 28, 32, 32);
		this.add(searchLabel);
	}

private void setOutput() {
	// TODO Auto-generated method stub
	try {
		SQL.SupplierQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


	
//5.������ť�ķ���
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
	
//7.������ʾ��ѯ����ı��ķ���
	private void setTable() {
		// TODO Auto-generated method stub
		String[][] datas = {};
		String[] titles = { "���","��Ӧ�̱��", "��Ӧ������", "��ַ", "�绰", "�����˺�", "������"};
		model = new DefaultTableModel(datas, titles);	//��ģ��
		table = new MyTable(model);
		this.setBackground(Color.white);				//������ɫ
		scrollPanel = new JScrollPane(table);			//��ֱ������
		scrollPanel.setBounds(100, 60, 750, 445);
		add(scrollPanel);

		RowSorter sorter = new TableRowSorter(model); 	//ˮƽ������
		table.setRowSorter(sorter);

		TableColumn column = null;						//���ÿһ��
		
		//���ÿһ�б����ֶγ���
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(30);
			} else if (i == 1 || i == 4)
				column.setPreferredWidth(80);
			else
				column.setPreferredWidth(110);
		}
	}
	
//8.��ȡ���ķ���
	public static MyTable GetTable() {
		return table;
		
	}
	
//9.����������ť���¼��ķ���
	private void setListener() {
		// TODO Auto-generated method stub
		SQL.setsuppliermodel(model);
		addButton.addActionListener(this);
		delButton.addActionListener(this);
		searchButton.addActionListener(this);
		outputButton.addActionListener(this);
		updateButton.addActionListener(this);
		
		
		//˫��������꣬�Զ���ȡ��Ϣ���޸Ĳ����޸ĵ�������
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					new SuppliersUpdateMess();
//					
				}else if(e.getButton() == MouseEvent.BUTTON1) {
					getSupplierMess();
					SuppliersUpdateMess.setSupplier(sno, name, address, tell, cardno, bank);
				}
			}
		});
		
		//���������ǰ����ڱ����ĵط�ȡ�����ѡ�м��鼮�������
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							table.clearSelection();// ��ձ��ѡ��
							SuppliersUpdateMess.setSupplier(null, null, null, null, null, null); // �鼮�������
						}
					}
				});
		
	}
		
//String sno, name, address, tell, cardno, bank;
//10.��ȡ��Ӧ����Ϣ
	static void getSupplierMess() {

		int Suppliers_row = table.getSelectedRow();
		sno = (String) table.getValueAt(Suppliers_row, model.findColumn("��Ӧ�̱��"));// ����ֵΪ row�� column�ĵ�Ԫ��ֵ��
		name = (String) table.getValueAt(Suppliers_row, model.findColumn("��Ӧ������"));
		address = (String) table.getValueAt(Suppliers_row, model.findColumn("��ַ"));
		tell = (String) table.getValueAt(Suppliers_row, model.findColumn("�绰"));
		cardno = (String) table.getValueAt(Suppliers_row, model.findColumn("�����˺�"));
		bank = (String) table.getValueAt(Suppliers_row, model.findColumn("������"));
		

	}
	
//11.��ť�¼��Ĵ�������
	public void actionPerformed(ActionEvent e) {
		//��ģ�͵Ľ���
		SQL.setsuppliermodel(model);
		//(1)"���"��ť����:��ӹ�Ӧ����Ϣ����
		if(e.getSource() == addButton) {
			new SuppliersAddMess();	
		}
		//(2)"ɾ��"��ť����:ɾ����Ӧ����Ϣ
		else if(e.getSource() == delButton) {
			int result = JOptionPane.showConfirmDialog(null, "ɾ�����޷��ָ����Ƿ������", "ɾ������", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.SupplierDelete(sno);
					SQL.SupplierQuery();	//ɾ����ͨ�����ݿ��ѯ���й�Ӧ����Ϣ,�൱��ˢ��һ��
				} catch (Exception e1) {	//SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//(3)"��ѯ"��ť����:��ѯ��Ӧ����Ϣ
		else if(e.getSource() == searchButton) {
			
			
			String conditions = cxText.getText();
			
			try {
				SQL.SupplierSel(conditions);//����������ҩƷ��Ϣ

			} catch (Exception e1) {//SQLException
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if (e.getSource() == updateButton) {
			new SuppliersUpdateMess();
		}else if(e.getSource() == outputButton) {
			
			try {
				SQL.SupplierQuery();	//ִ��SQL���
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
}