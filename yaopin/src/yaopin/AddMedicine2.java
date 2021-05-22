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


public class AddMedicine2 extends JDialog implements ActionListener, ItemListener,DocumentListener  {
	JLabel mnoLabel, typeLabel, nameLabel, lsPriceLabel, specificationsLabel,
			noteLabel, numLabel, sumLabel;
	JTextField mnoText, typeText, nameText, lsPriceText, specificationsText,
			noteText, numText, sumText, amountText;
	JButton addButton, queryButton, cleanButton, resetButton;
	private int x, y;
	protected JComboBox mnoTypeBox;
	String nameResult, specificationsResult, amountResult;
	
	
	static String Sdno, Sano, Mno, Specifications, Num, Lsprice, Sum, Note,Name; //进货明细单里的属性（开头大写）

	AddMedicine2() {

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

		lsPriceLabel = new JLabel("零售价:"); // 批发价
		lsPriceText = new JTextField();
		lsPriceLabel.setBounds(530, 50, 100, 20);
		lsPriceText.setBounds(580, 50, 100, 20);

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
		add(lsPriceLabel);
		add(lsPriceText);
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
	                                
	                specificationsResult = SQL.specificationsTypeSel(choice);
	                specificationsText.setText(specificationsResult);
	                
	                amountResult = SQL.amountTypeSel(choice);
	                
	                
	            }catch(Exception ex){  
	                  
	            }  
	        }  
	    } 
	 
	 
//	 static String Sdno, Sano, Mno, Specifications, Num, Lsprice, Sum, Note,Name;
	 
	 
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
				String Lsprice = lsPriceText.getText().trim();
				float lsprice = Float.valueOf(Lsprice);
				
				float sum =(float)num * lsprice;
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

	public static void setSalesDetails(String _Sdno,String _Sano, String _Mno,
			String _Specifications, String _Num, String _Lsprice, String _Sum,String _Note,String _Name) {
		Sano =_Sano;
		Name = _Name;
		Num = _Num;
		Sum = _Sum;
		Specifications = _Specifications;
		Lsprice = _Lsprice;
		Note = _Note;
		Sdno = _Sdno;


	}
	 

	

	void cleanText() {

//		mnoText.setText("");
		nameText.setText("");
		lsPriceText.setText("");
		specificationsText.setText("");
		noteText.setText("");
		mnoTypeBox.setSelectedIndex(0);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
		String Specifications = specificationsText.getText().trim();
		String Mno = mnoTypeBox.getSelectedItem().toString().trim();
		String Name = nameText.getText().trim();
		String Lsprice = lsPriceText.getText().trim();
		String Note = noteText.getText().trim();
		String Num = numText.getText().trim();
		int num = Integer.valueOf(Num);
		float lsprice = Float.valueOf(Lsprice); 
		float sum =num * lsprice;
		String Sum = String.valueOf(sum);
		String Sano=SalesAddMess.returnSano();
		
		String sdnDate =SalesAddMess.returnSano().substring(0, 12);
		
		
		System.out.println("sdnDate:"+sdnDate);
		
		String Sdno =SQL.getSDN(sdnDate).trim();
		
		System.out.println("sdno="+Sdno);
		
		int a= Integer.valueOf(amountResult) - num;
		

		
		// 药品编码校验
		if (e.getSource() == addButton) {
			if(!mnoTypeBox.getSelectedItem().equals("") && (a>0)) {
			

				SQL.SalesDetailsInsert(Sdno, Sano, Mno, Specifications, Num, Lsprice, Sum, Note);//添加订单记录
				 Sdno =SQL.getSDN(sdnDate).trim();
				 System.out.println("Sdno:"+Sdno);

				 SalesAddMess.SetSumText(); //计算总金额
				 
				 ReturnMnoNum(Mno,Num);
				 
				 
				dispose();
			}else {

				JOptionPane.showMessageDialog(null, "对不起，输入信息有误！");
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
		SalesAddMess.getMnoNum(_Mno,_Num);
		
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