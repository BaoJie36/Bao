package yaopin;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class CustomerAddMess extends JDialog implements ActionListener {

	JLabel cnoLabel, nameLabel, addressLabel, tellLabel;
	protected JTextField cnoText, nameText, addressText, tellText;
	protected JButton addbutton, updatebutton, resetbutton, cleanbutton;


	public CustomerAddMess() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		setResizable(false);
		init();
		setSize(600,300);
		setModal(true);	//�ײ㴰�ڲ��ɱ༭
		setLocationRelativeTo(null);
		setTitle("�ͻ���Ϣ����");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	void init() {

		setText();
		setButton();
		setListener();
		resetText();
	}

	void setText() {

		
		cnoLabel = new JLabel("�ͻ����:");
		cnoText = new JTextField();
		cnoLabel.setBounds(20, 50, 100, 20);
		cnoText.setBounds(80, 50, 100, 20);
		add(cnoLabel);
		add(cnoText);
		
		
		nameLabel = new JLabel("�ͻ�����:");
		nameText = new JTextField();
		nameLabel.setBounds(200, 50, 100, 20);
		nameText.setBounds(260, 50, 100, 20);
		add(nameLabel);
		add(nameText);
		
		
		tellLabel = new JLabel("��ϵ�绰:");
		tellText = new JTextField();
		tellLabel.setBounds(380, 50, 100, 20);
		tellText.setBounds(440, 50, 100, 20);
		add(tellLabel);
		add(tellText);
		
		
		addressLabel = new  JLabel("��ַ:");
		addressText = new JTextField();
		addressLabel.setBounds(20, 80, 100, 20);
		addressText.setBounds(70, 80, 200, 20);
		add(addressLabel);
		add(addressText);

		
	}

	void setButton() {
		addbutton = new JButton("���");
		addbutton.setBounds(50, 120, 60, 25);
		add(addbutton);

		resetbutton = new JButton("����");
		resetbutton.setBounds(150, 120, 60, 25);
		add(resetbutton);
		
		cleanbutton = new JButton("���");
		cleanbutton.setBounds(250, 120, 60, 25);
		add(cleanbutton);
	}

	void setListener() {
		addbutton.addActionListener(this);
		resetbutton.addActionListener(this);
		cleanbutton.addActionListener(this);
	}

	static String cno, name, address, tell;

	public static void setCustomer(String _cno,String _name,String _address,String _tell) {
		cno = _cno;
		name   = _name;
		address = _address;
		tell = _tell;
	}

	void resetText() {

		nameText.setText(name);
		cnoText.setText(cno);
		addressText.setText(address);
		tellText.setText(tell);

	}
	
	void cleanText() {
		
		nameText.setText("");
		tellText.setText("");
		cnoText.setText("");
		addressText.setText("");

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String name = nameText.getText().trim();
		String cno = cnoText.getText().trim();
		String address = addressText.getText().trim();
		String tell = tellText.getText().trim();


		if (e.getSource() == addbutton) {
			if(SQL.isCNO(cno)&& !cno.equals("") && !name.equals("")&&!address.equals("") && !tell.equals("") && name.length() < 4) {//�ͻ�����У��				
				SQL.CustomerInsert(cno, name, address, tell);//��ӿͻ���Ϣ
				dispose();
			}else {
				if(!SQL.isCNO(cno))
				JOptionPane.showMessageDialog(null, "�Բ�����������һ���Ŀͻ�����!");
				else
				JOptionPane.showMessageDialog(null, "�Բ�����������Ч��Ϣ��");
			}
	
		} else if(e.getSource() == resetbutton) {
			
			try {
				CustomerPanel.getCustomerMess();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			resetText();
		} else if(e.getSource() == cleanbutton) {
			cleanText();
		}
}
}