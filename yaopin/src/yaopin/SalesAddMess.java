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

public class SalesAddMess extends JDialog implements ActionListener {
	JLabel sanoLabel, dateLabel, enoLabel, sumLabel, cnoLabel, accountLabel, noteLabel;
	JTextField sanoText, dateText, enoText;
	static JTextField sumText;
	JTextField cnoText;
	JTextField accountText;
	JTextField noteText;
	static String sano, date, eno, sum = "0", cno, account, note;
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

	SalesAddMess() {
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
		setTitle("���ۿ���");
		setVisible(true);

	}

	public static String returnSano() {
		String Sano = sano;
		return Sano;
	}

	private void setCurrentTime() {
		// TODO Auto-generated method stub

	}

	void init() {

		sanoLabel = new JLabel("���۵���:");
		sanoText = new JTextField();
		sanoLabel.setBounds(20, 50, 100, 20);
		sanoText.setBounds(80, 50, 100, 20);
		sanoText.setEditable(false);

		dateLabel = new JLabel("��������:");
		dateText = new JTextField();
		dateLabel.setBounds(20, 80, 100, 20);
		dateText.setBounds(80, 80, 100, 20);
//		dateLabel.setToolTipText("��ʽҪ��XXXX-XX-XX");   tip���ڸ�ʽ��ʾ������δʵ��

		cnoLabel = new JLabel("�ͻ����:"); // ���ۼ�
		cnoText = new JTextField();
		cnoLabel.setBounds(200, 50, 100, 20);
		cnoText.setBounds(275, 50, 100, 20);

		enoLabel = new JLabel("Ա�����:");
		enoText = new JTextField();
		enoLabel.setBounds(390, 50, 100, 20);
		enoText.setBounds(450, 50, 100, 20);

		sumLabel = new JLabel("�ܽ��:"); // ������
		sumText = new JTextField();
		sumLabel.setBounds(560, 50, 100, 20);
		sumText.setBounds(610, 50, 100, 20);
		sumText.setEditable(false);

		accountLabel = new JLabel("  ������:"); // ���
		accountText = new JTextField();
		accountLabel.setBounds(200, 80, 100, 20);
		accountText.setBounds(275, 80, 100, 20);

		noteLabel = new JLabel("  ��ע:");
		noteText = new JTextField();
		noteLabel.setBounds(390, 80, 100, 20);
		noteText.setBounds(450, 80, 200, 20);

		timeLabel = new JLabel("��ǰʱ��:");
		timeLabel.setBounds(430, 500, 60, 25);

		currentTimeLabel = new JLabel();
		currentTimeLabel.setBounds(490, 500, 100, 25);
		configTime();

		operatorLabel = new JLabel("����Ա:");
		operatorLabel.setBounds(590, 500, 100, 25);
		currentOperatorLabel = new JLabel("admin");
		currentOperatorLabel.setBounds(640, 500, 100, 25);

		add(sanoLabel);
		add(sanoText);
		add(dateLabel);
		add(dateText);
		add(enoLabel);
		add(enoText);
		add(sumLabel);
		add(sumText);
		add(cnoLabel);
		add(cnoText);
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

		createButton = new JButton("����");
		createButton.setBounds(50, 500, 60, 25);
		add(createButton);

		addButton = new JButton("���");
		addButton.setBounds(150, 500, 60, 25);
		addButton.setEnabled(false);
		add(addButton);

		deleteButton = new JButton("ɾ��");
		deleteButton.setBounds(250, 500, 60, 25);
		add(deleteButton);

		confirmButton = new JButton("ȷ��");
		confirmButton.setBounds(350, 500, 60, 25);
		add(confirmButton);
	}

	void setTable() {
		String[][] datas = {};
		String[] titles = { "���", "��ϸ���", "ҩƷ���", "ҩƷ����", "���", "����", "����", "�ܽ��", "��ע" };
		model = new DefaultTableModel(datas, titles);
		table = new MyTable(model);

		scrollPanel = new JScrollPane(table);
		scrollPanel.setBounds(20, 110, 740, 380);
		add(scrollPanel);

		RowSorter sorter = new TableRowSorter(model); // ˮƽ������
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

	static String Sdno, Sano, Mno, Specifications, Num, Lsprice, Sum, Note, Name;
	public static void setSalesDetails(String _Sdno, String _Mno, String _Name, String _Specifications, String _Num, String _Lsprice,
			String _Sum, String _Note) {
		
		Sdno = _Sdno;
		Mno = _Mno;
		Name =_Name;
		Specifications =_Specifications;
		Num = _Num;
		Lsprice = _Lsprice;
		Sum = _Sum;
		Note = _Note;
		

	}
	static void getSalesDetailsMess() {
//		{ "���", "ҩƷ���", "ҩƷ����", "���", "����", "����", "�ܽ��", "��ע" };
		int SalesDetails_row = table.getSelectedRow();
		
		Sdno =(String) table.getValueAt(SalesDetails_row, model.findColumn("��ϸ���"));
		Mno = (String) table.getValueAt(SalesDetails_row, model.findColumn("ҩƷ���"));
		Name = (String) table.getValueAt(SalesDetails_row, model.findColumn("ҩƷ����"));
		Specifications = (String) table.getValueAt(SalesDetails_row, model.findColumn("���"));
		Num = (String) table.getValueAt(SalesDetails_row, model.findColumn("����"));
		Lsprice = (String) table.getValueAt(SalesDetails_row, model.findColumn("����"));
		Sum = (String) table.getValueAt(SalesDetails_row, model.findColumn("�ܽ��"));
		Note = (String) table.getValueAt(SalesDetails_row, model.findColumn("��ע"));
		

	}

	public static MyTable GetTable() {
		return table;
	}

	void setListener() {
		SQL.setSalesDetailsmodel(model);
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		createButton.addActionListener(this);
		confirmButton.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 
					getSalesDetailsMess();
					setSalesDetails(Sdno, Mno, Name, Specifications, Num, Lsprice, Sum, Note);
			
			}
		});
	}

	public static void setSales(String _sano, String _date, String _eno, String _sum, String _cno, String _account,
			String _note) {
		sano = _sano;
		date = _date;
		eno = _eno;
		sum = _sum;
		cno = _cno;
		account = _account;
		note = _note;

	}

	void setText() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
//		System.out.println(dateTime.format(formatter));

		dateText.setText(dateTime.format(formatter));
		sanoText.setText(sano);
		enoText.setText(eno);
		sumText.setText(sum);
		cnoText.setText(cno);
		accountText.setText(account);
		noteText.setText(note);

	}
	 static void SetSumText() {
		SQL.GetSalesSum(table, sumText);
		Float changeSum= Float.valueOf(sumText.getText().trim());
		SQL.SalesSumUpdate(changeSum,sano);
	}
	static void SetDelSumText() {
		
	}

	void cleanText() {

		
		enoText.setText("");
		sumText.setText("0");
		cnoText.setText("");
		accountText.setText("");
		noteText.setText("");

	}

	SimpleDateFormat pnoTime = new SimpleDateFormat("YYYYMMddHHmmSSS");
	String subjectno = pnoTime.format(new Date());

	protected void Setpno() {
		sanoText.setText(subjectno);
	}
	static void checkSalesDetailsDelete() {
		createButton.setEnabled(false);
		addButton.setEnabled(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		Icon img = new ImageIcon("tip.png");
//		static String pno, date, eno, sum, sno, account, note;  
		String sano = sanoText.getText().trim();
		String date = dateText.getText().trim();
		String eno = enoText.getText().trim();
		String sum = sumText.getText().trim();
		String cno = cnoText.getText().trim();
		String account = accountText.getText().trim();
		String note = noteText.getText().trim();
		
		

		if (e.getSource() == addButton) {
			setSales(sano, date, eno, sum, cno, account, note);
			new AddMedicine2();
			
		} else if (e.getSource() == createButton) {
			if (!sano.equals("") && !date.equals("") && !eno.equals("") && !sum.equals("") && !cno.equals("")
					&& !account.equals("")) {
				SQL.SalesInsert(sano, date, eno, sum, cno, account, note);// ��ӽ�����Ϣ
				createButton.setEnabled(false);
				addButton.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "�Բ���������������Ϣ��");
			}

		} else if (e.getSource() == confirmButton) {
			
			dispose();
			sano = returnSano();
			sum = sumText.getText().trim();
			SQL.SalesUpdate(sano, date, eno, cno, account, sum, note);
			setSales(null, null, null, "0", null, null, null);
			try {
				SQL.MedicineQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cleanText();
		}else if(e.getSource() == deleteButton) {
			int result = JOptionPane.showConfirmDialog(null, "��ע�⣬�˲����޷��ָ�!", "ɾ������", JOptionPane.YES_NO_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				try {
					SQL.SdeleteNum(Mno,Num);	
					SQL.SalesDetailsDelete(Sdno, sano);
					SetSumText(); //�����ܽ��
					
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
			SQL.SChangeNum(Mno, Num);
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