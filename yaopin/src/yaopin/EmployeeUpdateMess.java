package yaopin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;






public class EmployeeUpdateMess extends JDialog implements ActionListener {
	JLabel enoLabel, nameLabel, sexLabel, ageLabel, xueliLabel, zhiwuLabel, addressLabel;
	JTextField enoText, nameText, sexText, ageText, xueliText, zhiwuText, addressText;
	JButton updateButton, cleanButton,resetButton;
	protected JComboBox typeBox;
	EmployeeUpdateMess() {
		init();
		resetText();
		setLayout(null);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setButton();
		setListener();
		setSize(800, 300);
		setTitle("员工信息管理");
		setVisible(true);

	}

	void init() {
		enoLabel = new JLabel("员工编号:");
		enoText = new JTextField();
		enoLabel.setBounds(20, 50, 100, 20);
		enoText.setBounds(80, 50, 100, 20);
	
		nameLabel = new JLabel("员工姓名：");
		nameText = new JTextField();
		nameLabel.setBounds(200, 50, 100, 20);
		nameText.setBounds(260, 50, 100, 20);
			
		
		sexLabel = new JLabel("性别:");
		sexText = new JTextField();
		sexLabel.setBounds(380, 50, 100, 20);
		sexText.setBounds(410, 50, 100, 20);

		ageLabel = new JLabel("年龄:"); // 批发价
		ageText = new JTextField();
		ageLabel.setBounds(530, 50, 100, 20);
		ageText.setBounds(580, 50, 100, 20);

		xueliLabel = new JLabel("学历:"); // 零售价
		xueliText = new JTextField();
		xueliLabel.setBounds(20, 80, 100, 20);
		xueliText.setBounds(80, 80, 100, 20);

		zhiwuLabel = new JLabel("职务:"); // 库存
		zhiwuText = new JTextField();
		zhiwuLabel.setBounds(200, 80, 100, 20);
		zhiwuText.setBounds(260, 80, 100, 20);

		addressLabel = new JLabel("地址:"); // 规格
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
		

		updateButton = new JButton("修改");
		updateButton.setBounds(50, 140, 60, 25);
		add(updateButton);


		cleanButton = new JButton("清空");
		cleanButton.setBounds(150, 140, 60, 25);
		add(cleanButton);
		
		resetButton = new JButton("重置");
		resetButton.setBounds(250, 140, 60, 25);
		add(resetButton);
	}

	void setListener() {
		resetButton.addActionListener(this);
		updateButton.addActionListener(this);
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


		
		 if (e.getSource() == updateButton) {
			

			int result = JOptionPane.showConfirmDialog(null, "确定修改？", "修改提醒", JOptionPane.YES_NO_OPTION);
			switch (result) {
				case JOptionPane.YES_OPTION:
					
					SQL.EmployeeUpdate(eno, name, sex, age, xueli, zhiwu, address);
					
			}
			
			dispose();
		

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