package yaopin;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;


//��񲻿ɱ༭
class MyTable extends JTable {//��дJTable��Ĺ��췽��
/*
* �����࣬����̳���JTable���Ϊ���
*/
	
	public MyTable(DefaultTableModel tableModel){//Vector rowData����ά���飩,Vector columnNames��һά���飩
		super(tableModel);//���ø���Ĺ��췽�� ���෽��Ϊ��Object[][] rowData,Object[] columnNames
		
	}
	//��дJTable���getTableHeader()����
	
	public JTableHeader getTableHeader(){//������ͷ
		JTableHeader tableHeader=super.getTableHeader();//��ñ��ͷ����
		tableHeader.setReorderingAllowed(false);//���ñ���в�������
		DefaultTableCellRenderer hr=(DefaultTableCellRenderer)tableHeader.getDefaultRenderer();//��ñ��ͷ�ĵ�Ԫ�����
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//��������������ʾ
		return tableHeader;
	}
	
	//��дJtbale���getDefaultRenderer(Class<?>columnClass)����
	public TableCellRenderer getDCellRenderer(Class<?> columnClass){//���嵥Ԫ��
		DefaultTableCellRenderer cr=(DefaultTableCellRenderer)super.getDefaultRenderer(columnClass);//��ñ��ĵ�Ԫ�����
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//���õ�Ԫ�����ݾ�����ʾ
		return cr;
		
	}
	//��дJtable���isCellEditable(int row,int column)����
	public boolean isCellEditable(int row,int column){//��񲻿ɱ༭
		return false;
	}
	
	
}