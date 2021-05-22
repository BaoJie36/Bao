package yaopin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class AddMedicine extends JDialog implements ActionListener, ItemListener,DocumentListener  {
	JLabel mnoLabel, typeLabel, nameLabel, jhPriceLabel, specificationsLabel,
			noteLabel, numLabel, sumLabel;
	JTextField mnoText, typeText, nameText, jhPriceText, specificationsText,
			noteText, numText, sumText;
	JButton addButton, queryButton, cleanButton, resetButton;
	private int x, y;
	protected JComboBox mnoTypeBox;
	String nameResult,specificationsResult;
	
	
	static String Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note,Name; //进货明细单里的属性（开头大写）

	AddMedicine() {

		init();
		setLayout(null);
		setResizable(false);
		this.setBounds(x / 2, y / 2, 800, 280);
		setButton();
		setListener();
//		setSize(800, 300);
		setTitle("药品信息管理");
		setVisible(true);

	}

	

	void init() {
		x = (int) this.getToolkit().getScreenSize().getWidth();
		y = (int) this.getToolkit().getScreenSize().getHeight();

		
		
		mnoLabel = new JLabel("药品编号:");
//		mnoText = new JTextField();
		mnoLabel.setBounds(20, 50, 100, 20);
//		mnoText.setBounds(80, 50, 100, 20);

		ArrayList<String> mnoType = new ArrayList<String>();
		mnoType = SQL.GetmnoType();// 获取药品种类
		mnoTypeBox = new JComboBox(mnoType.toArray());
		mnoTypeBox.setBounds(80, 50, 100, 20);
		
		
		
		nameLabel = new JLabel("药名:");
		nameText = new JTextField();
		nameLabel.setBounds(200, 50, 100, 20);
		nameText.setBounds(240, 50, 100, 20);
		nameText.setEditable(false);

		jhPriceLabel = new JLabel("进货价:"); // 批发价
		jhPriceText = new JTextField();
		jhPriceLabel.setBounds(530, 50, 100, 20);
		jhPriceText.setBounds(580, 50, 100, 20);

		specificationsLabel = new JLabel("规格:"); // 规格
		specificationsText = new JTextField();
		specificationsLabel.setBounds(370, 50, 100, 20);
		specificationsText.setBounds(410, 50, 100, 20);
		specificationsText.setEditable(false);
		
		numLabel = new JLabel("数量");
		numText = new JTextField();
		numLabel.setBounds(20, 80, 100, 20);
		numText.setBounds(80, 80, 100, 20);
		

		sumLabel = new JLabel("金额");
		sumText = new JTextField();
		sumLabel.setBounds(200, 80, 100, 20);
		sumText.setBounds(240, 80, 100, 20);
		sumText.setEditable(false);
		
		noteLabel = new JLabel("备注:");
		noteText = new JTextField();
		noteLabel.setBounds(370, 80, 100, 20);
		noteText.setBounds(410, 80, 200, 20);


		add(mnoLabel);
//		add(mnoText);
		add(mnoTypeBox);
		add(nameLabel);
		add(nameText);
		add(jhPriceLabel);
		add(jhPriceText);
		add(specificationsLabel);
		add(specificationsText);
		add(numLabel);
		add(numText);
		add(sumLabel);
		add(sumText);
		add(noteLabel);
		add(noteText);

	}
	
	 public void itemStateChanged(ItemEvent e){  
	        if(e.getStateChange() == ItemEvent.SELECTED){  
	            String choice = (String) mnoTypeBox.getSelectedItem();//getItem()
	          
	            try{   
	            	nameResult = SQL.nameTypeSel(choice);
	                nameText.setText(nameResult);
	                String name = nameText.getText().trim();                
	                specificationsResult = SQL.specificationsTypeSel(choice);
	                specificationsText.setText(specificationsResult);
	                String specifications = specificationsText.getText().trim();
	            }catch(Exception ex){  
	                  
	            }  
	        }  
	    } 
	 
	 
//	 static String Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note,Name;
	 
	 
	void setButton() {
		addButton = new JButton("添加");
		addButton.setBounds(50, 110, 60, 25);
		add(addButton);
		cleanButton = new JButton("清空");
		cleanButton.setBounds(150, 110, 60, 25);
		add(cleanButton);

	}
	void textChange() {
		// TODO Auto-generated method stub
		
				String Num = numText.getText().trim();
				if(!Num.equals("")) {
				int num = Integer.valueOf(Num);
				String Jhprice = jhPriceText.getText().trim();
				float jhprice = Float.valueOf(Jhprice);
				
				float sum =(float)num * jhprice;
				String Sum = String.valueOf(sum);
				sumText.setText(Sum);
//				
				}else {
					sumText.setText("0.00");
					
				}
					
					
				
	}

	void setListener() {
		mnoTypeBox.addItemListener(this);
		addButton.addActionListener(this);
		cleanButton.addActionListener(this);
		numText.getDocument().addDocumentListener(new DocumentListener() {
		

	public void changedUpdate(DocumentEvent e) {
		
		textChange();
	}
	
	public void insertUpdate(DocumentEvent e) {
		textChange();
	}

	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		textChange();
	}
});

	}
//	static String Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note,Name; 
	public static void setPurchaseDetails(String _Pdno,String _Pno, String _Mno,
			String _Specifications, String _Num, String _Jhprice, String _Sum,String _Note,String _Name) {
		Pno =_Pno;
		Name = _Name;
		Num = _Num;
		Sum = _Sum;
		Specifications = _Specifications;
		Jhprice = _Jhprice;
		Note = _Note;
		Pdno = _Pdno;


	}
	 

	

	void cleanText() {

//		mnoText.setText("");
		nameText.setText("");
		jhPriceText.setText("");
		specificationsText.setText("");
		noteText.setText("");
		mnoTypeBox.setSelectedIndex(0);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		static String Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note,Name; 
		
		String Specifications = specificationsText.getText().trim();
		String Mno = mnoTypeBox.getSelectedItem().toString().trim();
		String Name = nameText.getText().trim();
		String Jhprice = jhPriceText.getText().trim();
		String Note = noteText.getText().trim();
		String Num = numText.getText().trim();
		int num = Integer.valueOf(Num);
		float jhprice = Float.valueOf(Jhprice); 
		float sum =num * jhprice;
		String Sum = String.valueOf(sum);
		String Pno=JinhuoAddMess.returnPno();
		
		String pdnDate =JinhuoAddMess.returnPno().substring(0, 12);
		
		
		System.out.println("pdnDate:"+pdnDate);
		
		String Pdno =SQL.getPDN(pdnDate).trim();
		
		System.out.println("pdno="+Pdno);
		

		
		// 药品编码校验
		if (e.getSource() == addButton) {
			if(!mnoTypeBox.getSelectedItem().equals("")) {
			

				SQL.PurchaseDetailsInsert(Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note);//添加订单记录
				 Pdno =SQL.getPDN(pdnDate).trim();
				 System.out.println("Pdno:"+Pdno);

				 JinhuoAddMess.SetSumText(); //计算总金额
				 
				 ReturnMnoNum(Mno,Num);
				 
				 
				dispose();
			}else {

				JOptionPane.showMessageDialog(null, "对不起，选择药品编号！");
			}

		} else if (e.getSource() == resetButton) {

			try {
				MedicinePanel.getMedicineMess();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		} else if (e.getSource() == cleanButton) {
			cleanText();
		}
	}



	private void ReturnMnoNum(String _Mno,String _Num) {
		// TODO Auto-generated method stub
		JinhuoAddMess.getMnoNum(_Mno,_Num);
		
	}



	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}