package yaopin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SuppliersAddMess extends JDialog implements ActionListener {

	JLabel snoLabel, nameLabel, addressLabel, tellLabel, cardnoLabel, bankLabel;
	JTextField snoText, nameText, addressText, tellText, cardnoText, bankText;
	JButton addButton, updateButton, cleanButton,resetButton;
	protected JComboBox typeBox;
	SuppliersAddMess() {
		init();
		resetText();
		setLayout(null);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setButton();
		setListener();
		setSize(800, 300);
		setTitle("��Ӧ����Ϣ����");
		setVisible(true);

	}

	void init() {
		snoLabel = new JLabel("��Ӧ�̱��:");
		snoText = new JTextField();
		snoLabel.setBounds(20, 50, 100, 20);
		snoText.setBounds(90, 50, 100, 20);
	
		nameLabel = new JLabel("��Ӧ�����ƣ�");
		nameText = new JTextField();
		nameLabel.setBounds(220, 50, 100, 20);
		nameText.setBounds(290, 50, 100, 20);
			
		
		addressLabel = new JLabel("��ַ:");
		addressText = new JTextField();
		addressLabel.setBounds(410, 50, 100, 20);
		addressText.setBounds(440, 50, 100, 20);

		tellLabel = new JLabel("�绰:"); 
		tellText = new JTextField();
		tellLabel.setBounds(590, 50, 100, 20);
		tellText.setBounds(620, 50, 100, 20);

		cardnoLabel = new JLabel("���п���:"); 
		cardnoText = new JTextField();
		cardnoLabel.setBounds(20, 80, 100, 20);
		cardnoText.setBounds(90, 80, 100, 20);

		bankLabel = new JLabel("������:"); 
		bankText = new JTextField();
		bankLabel.setBounds(240, 80, 100, 20);
		bankText.setBounds(290, 80, 100, 20);

		

		//sno, name, address, tell, cardno, bank
		add(snoLabel);
		add(snoText);
		add(nameLabel);
		add(nameText);
		add(addressLabel);
		add(addressText);
		add(tellLabel);
		add(tellText);
		add(cardnoLabel);
		add(cardnoText);
		add(bankLabel);
		add(bankText);

	}

	void setButton() {
		addButton = new JButton("���");
		addButton.setBounds(50, 140, 60, 25);
		add(addButton);

		updateButton = new JButton("�޸�");
		updateButton.setBounds(150, 140, 60, 25);
		add(updateButton);


		cleanButton = new JButton("���");
		cleanButton.setBounds(250, 140, 60, 25);
		add(cleanButton);
		
		resetButton = new JButton("����");
		resetButton.setBounds(350, 140, 60, 25);
		add(resetButton);
	}

	void setListener() {
		addButton.addActionListener(this);
		updateButton.addActionListener(this);
		cleanButton.addActionListener(this);
	}

	static String sno, name, address, tell, cardno, bank;
	//    
	
	public static void setSupplier(String _sno,String _name,String  _address,String _tell,
			String  _cardno,String _bank) {
		// TODO Auto-generated method stub
		name = _name;
		sno = _sno;
		address = _address;
		tell = _tell;
		cardno = _cardno;
		bank = _bank;
		
	}
	
	
	void resetText() {
		nameText.setText(name);
		addressText.setText(address);
		snoText.setText(sno);
		tellText.setText(tell);
		cardnoText.setText(cardno);
		bankText.setText(bank);

	}

	
	
	
	void cleanText() {

		
		nameText.setText("");
		addressText.setText("");
		snoText.setText("");
		tellText.setText("");
		cardnoText.setText("");
		bankText.setText("");


	}

	//sno, name, address, tell, cardno, bank
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String name = nameText.getText().trim();
		String sno = snoText.getText().trim();
		String address = addressText.getText().trim();
		String tell = tellText.getText().trim();
		String cardno = cardnoText.getText().trim();
		String bank = bankText.getText().trim();


		
		if (e.getSource() == addButton) {
			if(SQL.isSNO(sno) && !sno.equals("")&& !name.equals("")&& !address.equals("")
					&& !tell.equals("")&& !cardno.equals("")&& !bank.equals("")) {//��Ӧ�̱���У��
				
				SQL.SupplierInsert(sno, name, address, tell, cardno, bank);//��ӹ�Ӧ����Ϣ
				dispose();
			}else {
				if(snoText.getText().equals(""))
					JOptionPane.showMessageDialog(null, "�Բ�����������һ���Ĺ�Ӧ�̱�ţ�");
				else
					JOptionPane.showMessageDialog(null, "�Բ�����������Ч��Ϣ��");
			}
	
		} else if (e.getSource() == updateButton) {
			

			int result = JOptionPane.showConfirmDialog(null, "ȷ���޸ģ�", "�޸�����", JOptionPane.YES_NO_OPTION);
			switch (result) {
				case JOptionPane.YES_OPTION:
					
					SQL.SupplierUpdate(sno, name, address, tell, cardno, bank);
			}
			dispose();
		

	} else if(e.getSource() == resetButton) {
		
		try {
			SupplierPanel.getSupplierMess();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		resetText();
	} else if(e.getSource() == cleanButton) {
		cleanText();
	}
}

	

}
