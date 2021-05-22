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
		setTitle("药品进销存管理系统");
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
		
		MedicinePanel medicinePanel = null;  //药品管理面板
		SupplierPanel supplierPanel = null;  //供应商管理
		CustomerPanel customerPanel = null;	//客户管理
		EmployeePanel employeePanel = new EmployeePanel();	//员工管理
		  //管理员管理
		SalesPanel salesPanel = null;			//销售开单
		JinhuoPanel jinhuoPanel = null;		//进货开单
		Kucun kucun = new Kucun();			//库存盘点		
		 
		
		Pane = new JTabbedPane();
		Pane.setFont(new Font("宋体", Font.BOLD, 15));
		Pane.setBounds(5, 160, 985, 600);
		Pane.addTab(" 药品管理",new ImageIcon("ypglIcon.png"), medicinePanel);
		Pane.addTab(" 客户管理" ,new ImageIcon("khglIcon.png"), customerPanel);
		Pane.addTab(" 员工管理", new ImageIcon("ygglIcon.png"), employeePanel);
		Pane.addTab("供应商管理", new ImageIcon("gysglIcon.png"), supplierPanel);
		Pane.addTab(" 进货开单", new ImageIcon("jhkdIcon.png"), jinhuoPanel);
		Pane.addTab(" 销售开单", new ImageIcon("xskdIcon.png"), salesPanel);
       
        
        
		
		this.add(Pane);
		this.getContentPane().setBackground(Color.white);
	}



	public void actionPerformed(ActionEvent e) {
		
	}

}
