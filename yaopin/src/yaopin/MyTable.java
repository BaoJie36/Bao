package yaopin;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;


//表格不可编辑
class MyTable extends JTable {//重写JTable类的构造方法
/*
* 创建类，该类继承自JTable类成为表格
*/
	
	public MyTable(DefaultTableModel tableModel){//Vector rowData（二维数组）,Vector columnNames（一维数组）
		super(tableModel);//调用父类的构造方法 父类方法为：Object[][] rowData,Object[] columnNames
		
	}
	//重写JTable类的getTableHeader()方法
	
	public JTableHeader getTableHeader(){//定义表格头
		JTableHeader tableHeader=super.getTableHeader();//获得表格头对象
		tableHeader.setReorderingAllowed(false);//设置表格列不可重排
		DefaultTableCellRenderer hr=(DefaultTableCellRenderer)tableHeader.getDefaultRenderer();//获得表格头的单元格对象
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//设置列名居中显示
		return tableHeader;
	}
	
	//重写Jtbale类的getDefaultRenderer(Class<?>columnClass)方法
	public TableCellRenderer getDCellRenderer(Class<?> columnClass){//定义单元格
		DefaultTableCellRenderer cr=(DefaultTableCellRenderer)super.getDefaultRenderer(columnClass);//获得表格的单元格对象
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//设置单元格内容居中显示
		return cr;
		
	}
	//重写Jtable类的isCellEditable(int row,int column)方法
	public boolean isCellEditable(int row,int column){//表格不可编辑
		return false;
	}
	
	
}