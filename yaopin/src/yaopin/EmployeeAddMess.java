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

public class EmployeeAddMess extends JDialog implements ActionListener {
	JLabel enoLabel, nameLabel, sexLabel, ageLabel, xueliLabel, zhiwuLabel, addressLabel;
	JTextField enoText, nameText, sexText, ageText, xueliText, zhiwuText, addressText;
	JButton addButton, updateButton, cleanButton,resetButton;
	protected JComboBox typeBox;
	EmployeeAddMess() {
		init();
		resetText();
		setLayout(null);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setButton();
		setListener();
		setSize(800, 300);
		setTitle("Ա����Ϣ����");
		setVisible(true);

	}

	void init() {
		enoLabel = new JLabel("Ա�����:");
		enoText = new JTextField();
		enoLabel.setBounds(20, 50, 100, 20);
		enoText.setBounds(80, 50, 100, 20);
	
		nameLabel = new JLabel("Ա��������");
		nameText = new JTextField();
		nameLabel.setBounds(200, 50, 100, 20);
		nameText.setBounds(260, 50, 100, 20);
			
		
		sexLabel = new JLabel("�Ա�:");
		sexText = new JTextField();
		sexLabel.setBounds(380, 50, 100, 20);
		sexText.setBounds(410, 50, 100, 20);

		ageLabel = new JLabel("����:"); // ������
		ageText = new JTextField();
		ageLabel.setBounds(530, 50, 100, 20);
		ageText.setBounds(580, 50, 100, 20);

		xueliLabel = new JLabel("ѧ��:"); // ���ۼ�
		xueliText = new JTextField();
		xueliLabel.setBounds(20, 80, 100, 20);
		xueliText.setBounds(80, 80, 100, 20);

		zhiwuLabel = new JLabel("ְ��:"); // ���
		zhiwuText = new JTextField();
		zhiwuLabel.setBounds(200, 80, 100, 20);
		zhiwuText.setBounds(260, 80, 100, 20);

		addressLabel = new JLabel("��ַ:"); // ���
		addressText = new JTextField();
		addressLabel.setBounds(380, 80, 100, 20);
		addressText.setBounds(410, 80, 200, 20);

		//eno, name, sex, age, xueli, zhiwu, address	
		add(enoLabel);
		add(enoText);
		add(nameLabel);
		add(nameText);
		add(nameLabel);
		add(nameText);
		add(sexLabel);
		add(sexText);
		add(ageLabel);
		add(ageText);
		add(xueliLabel);
		add(xueliText);
		add(zhiwuLabel);
		add(zhiwuText);
		add(addressLabel);
		add(addressText);

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
		resetButton.addActionListener(this);
		cleanButton.addActionListener(this);
	}

	static String eno, name, sex, age, xueli, zhiwu, address;
	//    
	public static void setEmployee( String _eno,String _name,String  _sex,String _age,
			String  _xueli,String _zhiwu,String _address) {
		name = _name;
		eno = _eno;
		sex = _sex;
		age = _age;
		xueli = _xueli;
		zhiwu = _zhiwu;
		address = _address;

	}
	
	
	void resetText() {
		nameText.setText(name);
		sexText.setText(sex);
		enoText.setText(eno);
		ageText.setText(age);
		xueliText.setText(xueli);
		zhiwuText.setText(zhiwu);
		addressText.setText(address);

	}

	
	
	
	void cleanText() {

		
		enoText.setText("");
		nameText.setText("");
		sexText.setText("");
		ageText.setText("");
		addressText.setText("");
		zhiwuText.setText("");
		xueliText.setText("");


	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String name = nameText.getText().trim();
		String eno = enoText.getText().trim();
		String age = ageText.getText().trim();
		String xueli = xueliText.getText().trim();
		String zhiwu = zhiwuText.getText().trim();
		String sex = sexText.getText().trim();
		String address = addressText.getText().trim();


		
		if (e.getSource() == addButton) {
			if(SQL.isCNO(eno) && !eno.equals("") && !name.equals("")&&!sex.equals("") && !age.equals("")
					&& !xueli.equals("")&& !zhiwu.equals("")&& !address.equals("")) {//Ա������У��
				
				SQL.EmployeeInsert(eno, name, sex, age, xueli, zhiwu, address);//���ͼ����Ϣ
				dispose();
			}else {
				if(enoText.getText().equals(""))
					JOptionPane.showMessageDialog(null, "�Բ�����������һ����Ա����ţ�");
				else
					JOptionPane.showMessageDialog(null, "�Բ���������Ա����ţ�");
			}
	
		} else if(e.getSource() == resetButton) {
		
		try {
			EmployeePanel.getEmployeeMess();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		resetText();
	} else if(e.getSource() == cleanButton) {
		cleanText();
	}
}
}