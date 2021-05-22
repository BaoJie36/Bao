package yaopin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MedicineAddMess extends JDialog implements ActionListener, ItemListener {
	JLabel mnoLabel, typeNoLabel, typeNameLabel, mnameLabel, jhPriceLabel, lsPriceLabel, amountLabel, specificationsLabel,
			validityLabel, noteLabel, batchnoLabel;
	JTextField mnoText, mnameText, msnameText, jhPriceText, lsPriceText, amountText, specificationsText, validityText,
			noteText, batchnoText, typeText;
	JButton addButton, cleanButton;
	String choice,result;
	protected JComboBox  typeNameBox;

		
	MedicineAddMess() {
		init();
		setText();
		setLayout(null);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setButton();

		setListener();
		setSize(800, 300);
		setTitle("药品信息管理");
		setVisible(true);

	}
	 public void itemStateChanged(ItemEvent e){  
	        if(e.getStateChange() == ItemEvent.SELECTED){  
	            String choice = (String) typeNameBox.getSelectedItem();//getItem()
	            try{   
	                result = SQL.typeNoSel(choice);
	                typeText.setText(result);
//	                String type = typeText.getText().trim();
//	                System.out.println(result); 
	            }catch(Exception ex){  
	                  
	            }  
	        }  
	    } 

	void init() {
		mnoLabel = new JLabel("药品编号:");
		mnoText = new JTextField();
		mnoLabel.setBounds(20, 50, 100, 20);
		mnoText.setBounds(80, 50, 100, 20);
		

		typeNoLabel = new JLabel("类    别：");
		typeText = new JTextField();
		typeText.setEditable(false);
		typeNoLabel.setBounds(200, 50, 100, 20);
		typeText.setBounds(260, 50, 100, 20);

		typeNameLabel = new JLabel("类别名称：");
		ArrayList<String> MedicineSortNameType = new ArrayList<String>();
		MedicineSortNameType = SQL.GetMedicineSortNameType();// 获取药品种类
		typeNameBox = new JComboBox(MedicineSortNameType.toArray());// 把列表元素转换成数组
		typeNameLabel.setBounds(380, 50, 100, 20);
		typeNameBox.setBounds(440, 50, 100, 20);
		

		mnameLabel = new JLabel("药名:");
		mnameText = new JTextField();
		mnameLabel.setBounds(560, 50, 100, 20);
		mnameText.setBounds(600, 50, 100, 20);

		jhPriceLabel = new JLabel("进货价:"); // 批发价
		jhPriceText = new JTextField();
		jhPriceLabel.setBounds(20, 80, 100, 20);
		jhPriceText.setBounds(80, 80, 100, 20);

		lsPriceLabel = new JLabel("零售价:"); // 零售价
		lsPriceText = new JTextField();
		lsPriceLabel.setBounds(20, 110, 100, 20);
		lsPriceText.setBounds(80, 110, 100, 20);

		batchnoLabel = new JLabel("批号:");
		batchnoText = new JTextField();
		batchnoLabel.setBounds(200, 80, 100, 20);
		batchnoText.setBounds(260, 80, 100, 20);

		amountLabel = new JLabel("库存量:"); // 库存
		amountText = new JTextField();
		amountLabel.setBounds(380, 80, 100, 20);
		amountText.setBounds(440, 80, 100, 20);

		specificationsLabel = new JLabel("规格:"); // 规格
		specificationsText = new JTextField();
		specificationsLabel.setBounds(560, 80, 100, 20);
		specificationsText.setBounds(600, 80, 100, 20);

		validityLabel = new JLabel("有效期:"); // 有效期
		validityText = new JTextField();
		validityLabel.setBounds(200, 110, 100, 20);
		validityText.setBounds(260, 110, 100, 20);

		noteLabel = new JLabel("备注:");
		noteText = new JTextField();
		noteLabel.setBounds(390, 110, 100, 20);
		noteText.setBounds(440, 110, 200, 20);

		add(mnoLabel);
		add(mnoText);
		
		add(typeNoLabel);
		add(typeText);
		
		add(typeNameLabel);

		add(typeNameBox);
		
		add(mnameLabel);
		add(mnameText);
		add(jhPriceLabel);
		add(jhPriceText);
		add(lsPriceLabel);
		add(lsPriceText);
		add(amountLabel);
		add(amountText);
		add(specificationsLabel);
		add(specificationsText);
		add(validityLabel);
		add(validityText);
		add(batchnoLabel);
		add(batchnoText);
		add(noteLabel);
		add(noteText);
	}

	void setButton() {
		addButton = new JButton("添加");
		addButton.setBounds(50, 140, 60, 25);
		add(addButton);

		cleanButton = new JButton("清空");
		cleanButton.setBounds(150, 140, 60, 25);
		add(cleanButton);

		
	}

	void setListener() {
		addButton.addActionListener(this);	
		cleanButton.addActionListener(this);
		typeNameBox.addItemListener(this);
	}

	static String mname, msname = null, mno, type, amount, jhPrice, lsPrice, batchno, specifications, validity, note;

	//
	public static void setMedicine(String _mno, String _name, String _type, String _msname, String _specifications,
			String _validity, String _batchno, String _amount, String _jhPrice, String _lsPrice, String _note) {
		mname = _name;
		msname = _msname;
		mno = _mno;
		type = _type;
		specifications = _specifications;
		jhPrice = _jhPrice;
		validity = _validity;
		batchno = _batchno;
		note = _note;
		lsPrice = _lsPrice;
		amount = _amount;

	}

	void setText() {

		mnameText.setText(mname);
		amountText.setText(amount);
		mnoText.setText(mno);
		jhPriceText.setText(jhPrice);
		lsPriceText.setText(lsPrice);
		validityText.setText(validity);
		noteText.setText(note);
		specificationsText.setText(specifications);
		batchnoText.setText(batchno);
		typeText.setText(type);
//		SQL.MedicineSortNameTypeSelect(msname, typeNameBox);
	}

	void cleanText() {

		mnoText.setText("");
		lsPriceText.setText("");
		jhPriceText.setText("");
		amountText.setText("");
		batchnoText.setText("");
		specificationsText.setText("");
		validityText.setText("");
		noteText.setText("");
		typeText.setText("");
		typeNameBox.setSelectedIndex(0);

	}

	public void actionPerformed(ActionEvent e) {
		String mname = mnameText.getText().trim();
		String mno = mnoText.getText().trim();
		String type = typeText.getText().trim();
		String jhPrice = jhPriceText.getText().trim();
		String amount = amountText.getText().trim();
		String lsPrice = lsPriceText.getText().trim();
		String batchno = batchnoText.getText().trim();
		String specifications = specificationsText.getText().trim();
		String validity = validityText.getText().trim();
		String note = noteText.getText().trim();

		// 药品编码校验
		if (e.getSource() == addButton) {
			cleanText();
			try {

				if (SQL.isMNO(mno) && !mno.equals("") && !mname.equals("") && !type.equals("") 
						&& !specifications.equals("") && !amount.equals("") && !jhPrice.equals("")
						&& !batchno.equals("") && !lsPrice.equals("") && !validity.equals("")) {
					System.out.println(mno + " " + mname + " " + type +  " " + specifications + " "
							+ amount + " " + jhPrice + " " + batchno + " " + lsPrice + " " + validity + " " + note);

					SQL.MedicineInsert(mno, mname, type, specifications, amount, jhPrice, batchno, lsPrice, validity,
							note);// 添加药品信息
					dispose();
				} else {
					if (!mno.equals(mno))
						JOptionPane.showMessageDialog(null, "对不起，请输入另一个的药品编号！");
					else

						JOptionPane.showMessageDialog(null, "对不起，请输入药品编号！");
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		} else if (e.getSource() == cleanButton) {
			cleanText();
		}
	}
}
