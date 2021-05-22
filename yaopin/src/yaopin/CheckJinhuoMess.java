package yaopin;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.omg.CORBA._PolicyStub;

public class CheckJinhuoMess extends JDialog implements ActionListener {
	JLabel pnoLabel, dateLabel, enoLabel, sumLabel, snoLabel, accountLabel, noteLabel;
	JTextField pnoText, dateText, enoText;
	static JTextField sumText;
	JTextField snoText;
	JTextField accountText;
	JTextField noteText;
	static String pno, date, eno, sum = "0", sno, account, note;
	static JButton addButton;
	JButton deleteButton;
	JButton confirmButton;
	static JButton createButton;
	private JScrollPane scrollPanel;
	private static MyTable table;
	private JLabel timeLabel, currentTimeLabel;
	private JLabel operatorLabel, currentOperatorLabel;
	private String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	private int ONE_SECOND = 1000;
	private String time;

	private static DefaultTableModel model = null;

	CheckJinhuoMess() {
		this.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width) / 2) - 300,
				((Toolkit.getDefaultToolkit().getScreenSize().height) / 2) - 300, 600, 600);
		init();
		setText();
		Setpno();
		setCurrentTime();

		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setTable();
		setButton();
		setListener();
		setSize(800, 600);
		setTitle("进货开单");
		setVisible(true);

	}

	public static String returnPno() {
		String Pno = pno;
		return Pno;
	}

	private void setCurrentTime() {
		// TODO Auto-generated method stub

	}

	void init() {

		pnoLabel = new JLabel("进货单号:");
		pnoText = new JTextField();
		pnoLabel.setBounds(20, 50, 100, 20);
		pnoText.setBounds(80, 50, 100, 20);
		pnoText.setEditable(false);

		dateLabel = new JLabel("进货日期:");
		dateText = new JTextField();
		dateLabel.setBounds(20, 80, 100, 20);
		dateText.setBounds(80, 80, 100, 20);
//		dateLabel.setToolTipText("格式要求XXXX-XX-XX");   tip日期格式提示功能尚未实现

		snoLabel = new JLabel("供应商编号:"); // 零售价
		snoText = new JTextField();
		snoLabel.setBounds(200, 50, 100, 20);
		snoText.setBounds(275, 50, 100, 20);

		enoLabel = new JLabel("员工编号:");
		enoText = new JTextField();
		enoLabel.setBounds(390, 50, 100, 20);
		enoText.setBounds(450, 50, 100, 20);

		sumLabel = new JLabel("总金额:"); // 批发价
		sumText = new JTextField();
		sumLabel.setBounds(560, 50, 100, 20);
		sumText.setBounds(610, 50, 100, 20);
		sumText.setEditable(false);

		dateLabel = new JLabel("进货日期:");
		dateText = new JTextField();
		dateLabel.setBounds(20, 80, 100, 20);
		dateText.setBounds(80, 80, 100, 20);

		accountLabel = new JLabel("  记账人:"); // 库存
		accountText = new JTextField();
		accountLabel.setBounds(200, 80, 100, 20);
		accountText.setBounds(275, 80, 100, 20);

		noteLabel = new JLabel("  备注:");
		noteText = new JTextField();
		noteLabel.setBounds(390, 80, 100, 20);
		noteText.setBounds(450, 80, 200, 20);

		timeLabel = new JLabel("当前时间:");
		timeLabel.setBounds(430, 500, 60, 25);

		currentTimeLabel = new JLabel();
		currentTimeLabel.setBounds(490, 500, 100, 25);
		configTime();

		operatorLabel = new JLabel("操作员:");
		operatorLabel.setBounds(590, 500, 100, 25);
		currentOperatorLabel = new JLabel(LoginWindow.getUser());
		currentOperatorLabel.setBounds(640, 500, 100, 25);

		add(pnoLabel);
		add(pnoText);
		add(dateLabel);
		add(dateText);
		add(enoLabel);
		add(enoText);
		add(sumLabel);
		add(sumText);
		add(snoLabel);
		add(snoText);
		add(accountLabel);
		add(accountText);
		add(noteLabel);
		add(noteText);
		add(timeLabel);
		add(currentTimeLabel);
		add(operatorLabel);
		add(currentOperatorLabel);

	}

	private void configTime() {
		// TODO Auto-generated method stub
		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), ONE_SECOND);
	}

	protected class JLabelTimerTask extends TimerTask {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);

		@Override
		public void run() {
			time = dateFormatter.format(Calendar.getInstance().getTime());
			currentTimeLabel.setText(time);
		}
	}

	void setButton() {

		createButton = new JButton("创建");
		createButton.setBounds(50, 500, 60, 25);
		add(createButton);

		addButton = new JButton("添加");
		addButton.setBounds(150, 500, 60, 25);
		addButton.setEnabled(false);
		add(addButton);

		deleteButton = new JButton("删除");
		deleteButton.setBounds(250, 500, 60, 25);
		add(deleteButton);

		confirmButton = new JButton("确认");
		confirmButton.setBounds(350, 500, 60, 25);
		add(confirmButton);
	}

	void setTable() {
		String[][] datas = {};
		String[] titles = { "序号", "药品编号", "药品名称", "规格", "数量", "单价", "总金额", "备注","进货序号" };
		model = new DefaultTableModel(datas, titles);
		table = new MyTable(model);

		scrollPanel = new JScrollPane(table);
		scrollPanel.setBounds(20, 110, 740, 380);
		add(scrollPanel);

		RowSorter sorter = new TableRowSorter(model); // 水平滚动条
		table.setRowSorter(sorter);

		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(30);
			} else if (i == 1 || i == 5)
				column.setPreferredWidth(90);
			else if (i == 2)
				column.setPreferredWidth(110);
			else if (i == 10)
				column.setPreferredWidth(30);
			else
				column.setPreferredWidth(50);
		}
		this.setBackground(Color.white);
		SQL.setTableCenter(table, table.getColumnCount());

	}

	static String Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note, Name;
	public static void setPurchaseDetails(String _Mno, String _Name, String _Specifications, String _Num, String _Jhprice,
			String _Sum, String _Note,String _Pdno) {
		Mno = _Mno;
		Name =_Name;
		Specifications =_Specifications;
		Num = _Num;
		Jhprice = _Jhprice;
		Sum = _Sum;
		Note = _Note;
		Pdno = _Pdno;
		

	}
	static void getPurchaseDetailsMess() {
//		{ "序号", "药品编号", "药品名称", "规格", "数量", "单价", "总金额", "备注" };
		int PurchaseDetails_row = table.getSelectedRow();
		Mno = (String) table.getValueAt(PurchaseDetails_row, model.findColumn("药品编号"));
		Name = (String) table.getValueAt(PurchaseDetails_row, model.findColumn("药品名称"));
		Specifications = (String) table.getValueAt(PurchaseDetails_row, model.findColumn("规格"));
		Num = (String) table.getValueAt(PurchaseDetails_row, model.findColumn("数量"));
		Jhprice = (String) table.getValueAt(PurchaseDetails_row, model.findColumn("单价"));
		Sum = (String) table.getValueAt(PurchaseDetails_row, model.findColumn("总金额"));
		Note = (String) table.getValueAt(PurchaseDetails_row, model.findColumn("备注"));
		Pdno =(String) table.getValueAt(PurchaseDetails_row, model.findColumn("进货序号"));

	}

	public static MyTable GetTable() {
		return table;
	}

	void setListener() {
		SQL.setPurchaseDetailsmodel(model);
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		createButton.addActionListener(this);
		confirmButton.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 if(e.getButton() == MouseEvent.BUTTON1) {
					getPurchaseDetailsMess();
					setPurchaseDetails(Mno, Name, Specifications, Num, Jhprice, Sum, Note,Pdno);
				}
			}
		});
	}

	public static void setPurchase(String _pno, String _date, String _eno, String _sum, String _sno, String _account,
			String _note) {
		pno = _pno;
		date = _date;
		eno = _eno;
		sum = _sum;
		sno = _sno;
		account = _account;
		note = _note;

	}

	void setText() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
//		System.out.println(dateTime.format(formatter));

		dateText.setText(dateTime.format(formatter));
		pnoText.setText(pno);
		enoText.setText(eno);
		sumText.setText(sum);
		snoText.setText(sno);
		accountText.setText(account);
		noteText.setText(note);

	}
	 static void SetSumText() {
		SQL.GetPurchaseSum(table, sumText);
		Float changeSum= Float.valueOf(sumText.getText().trim());
		SQL.PurchaseSumUpdate(changeSum,pno);
	}
	static void SetDelSumText() {
		
	}

	void cleanText() {

		
		enoText.setText("");
		sumText.setText("0");
		snoText.setText("");
		accountText.setText("");
		noteText.setText("");

	}

	SimpleDateFormat pnoTime = new SimpleDateFormat("YYYYMMddHHmmSSS");
	String subjectno = pnoTime.format(new Date());

	protected void Setpno() {
		pnoText.setText(pno);
	}
	static void checkPurchaseDetailsDelete() {
		createButton.setEnabled(false);
		addButton.setEnabled(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		Icon img = new ImageIcon("tip.png");
//		static String pno, date, eno, sum, sno, account, note;  
		String pno = pnoText.getText().trim();
		String date = dateText.getText().trim();
		String eno = enoText.getText().trim();
		String sum = sumText.getText().trim();
		String sno = snoText.getText().trim();
		String account = accountText.getText().trim();
		String note = noteText.getText().trim();
		
		

		if (e.getSource() == addButton) {
			setPurchase(pno, date, eno, sum, sno, account, note);
			new AddMedicine();
			
		} else if (e.getSource() == createButton) {
			if (!pno.equals("") && !date.equals("") && !eno.equals("") && !sum.equals("") && !sno.equals("")
					&& !account.equals("")) {
				SQL.PurchaseInsert(pno, date, eno, sum, sno, account, note);// 添加进货信息
				createButton.setEnabled(false);
				addButton.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "对不起，请输入完整信息！");
			}

		} else if (e.getSource() == confirmButton) {
			
			dispose();
			pno = returnPno();
			sum = sumText.getText().trim();
			SQL.PurchaseUpdate(pno, date, eno, sum, sno, account, note);
			setPurchase(null, null, null, "0", null, null, null);
			cleanText();
		}else if(e.getSource() == deleteButton) {
			int result = JOptionPane.showConfirmDialog(null, "请注意，此操作无法恢复!", "删除警告", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.deleteNum(Mno,Num);	
					SQL.PurchaseDetailsDelete(Pdno, pno);
					SetSumText(); //计算总金额
					
				} catch (Exception e1) {//SQLException
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public static void setChangeNum()  {
		// TODO Auto-generated method stub
		try {
			SQL.ChangeNum(Mno, Num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	public static void getMnoNum(String _Mno, String _Num) {
		Mno = _Mno;
		Num = _Num;	
		System.out.println(Mno+""+Num);
		setChangeNum();
	}
}