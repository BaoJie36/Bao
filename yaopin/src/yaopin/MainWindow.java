package yaopin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements ActionListener{
	JLabel MainIcon,employeeLabel;
	JTabbedPane Pane;
	
	MainWindow(){
		init();
		setLayout(null);
		setVisible(true);
		setTitle("ҩƷ���������ϵͳ");
		setResizable(false);
		setSize(1000,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void init() {
		
		MainIcon =new JLabel();
		MainIcon.setBounds(0,0,1000,150);
		MainIcon.setIcon(new ImageIcon("MainIcon.jpg"));
		this.add(MainIcon);
		
		MedicinePanel medicinePanel = null;  //ҩƷ�������
		SupplierPanel supplierPanel = null;  //��Ӧ�̹���
		CustomerPanel customerPanel = null;	//�ͻ�����
		EmployeePanel employeePanel = new EmployeePanel();	//Ա������
		  //����Ա����
		SalesPanel salesPanel = null;			//���ۿ���
		JinhuoPanel jinhuoPanel = null;		//��������
		Kucun kucun = new Kucun();			//����̵�		
		 
		
		Pane = new JTabbedPane();
		Pane.setFont(new Font("����", Font.BOLD, 15));
		Pane.setBounds(5, 160, 985, 600);
		Pane.addTab(" ҩƷ����",new ImageIcon("ypglIcon.png"), medicinePanel);
		Pane.addTab(" �ͻ�����" ,new ImageIcon("khglIcon.png"), customerPanel);
		Pane.addTab(" Ա������", new ImageIcon("ygglIcon.png"), employeePanel);
		Pane.addTab("��Ӧ�̹���", new ImageIcon("gysglIcon.png"), supplierPanel);
		Pane.addTab(" ��������", new ImageIcon("jhkdIcon.png"), jinhuoPanel);
		Pane.addTab(" ���ۿ���", new ImageIcon("xskdIcon.png"), salesPanel);
       
        
        
		
		this.add(Pane);
		this.getContentPane().setBackground(Color.white);
	}



	public void actionPerformed(ActionEvent e) {
		
	}

}
