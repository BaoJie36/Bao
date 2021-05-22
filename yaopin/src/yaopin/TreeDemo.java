package yaopin;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;



public class TreeDemo {
    JFrame f;

    TreeDemo() {
    	
    	
        f = new JFrame();
        Container container = getContentPane();
        DefaultMutableTreeNode sort= new DefaultMutableTreeNode("药品进销存管理系统");
        DefaultMutableTreeNode ypgl = new DefaultMutableTreeNode("药品管理");
        DefaultMutableTreeNode zdcx = new DefaultMutableTreeNode("账单查询");
        DefaultMutableTreeNode gysgl = new DefaultMutableTreeNode("供应商管理");
        DefaultMutableTreeNode khgl = new DefaultMutableTreeNode("客户管理");
        DefaultMutableTreeNode xsgl = new DefaultMutableTreeNode("销售管理");

        sort.add(ypgl);
        sort.add(zdcx);
        sort.add(gysgl);
        sort.add(xsgl);
        sort.add(khgl);
        
        DefaultMutableTreeNode cxypxx = new DefaultMutableTreeNode("查询药品信息");
        DefaultMutableTreeNode tjypxx = new DefaultMutableTreeNode("添加药品信息");
        DefaultMutableTreeNode xghscypxx = new DefaultMutableTreeNode("修改或删除药品信息");
        DefaultMutableTreeNode ypjhrk = new DefaultMutableTreeNode("药品进货入库");
        DefaultMutableTreeNode ypxskd = new DefaultMutableTreeNode("药品销售开单");
        DefaultMutableTreeNode ypbskd = new DefaultMutableTreeNode("药品报损开单");
        DefaultMutableTreeNode ypthcl = new DefaultMutableTreeNode("药品退货处理");
        DefaultMutableTreeNode ypkccx = new DefaultMutableTreeNode("药品库存查询");
        DefaultMutableTreeNode yplbgl = new DefaultMutableTreeNode("药品类别管理");
        ypgl.add(cxypxx);
        ypgl.add(tjypxx);
        ypgl.add(xghscypxx);
        ypgl.add(ypjhrk);
        ypgl.add(ypxskd);
        ypgl.add(ypbskd);
        ypgl.add(ypthcl);
        ypgl.add(ypkccx);
        ypgl.add(yplbgl);
        

        DefaultMutableTreeNode jhdcx = new DefaultMutableTreeNode("进货单查询");
        DefaultMutableTreeNode xsdcx = new DefaultMutableTreeNode("销售单查询");
        DefaultMutableTreeNode bsdcx = new DefaultMutableTreeNode("报损单查询");
        DefaultMutableTreeNode thdcx = new DefaultMutableTreeNode("退货单查询");
        zdcx.add(jhdcx);
        zdcx.add(xsdcx);
        zdcx.add(bsdcx);
        zdcx.add(thdcx);

        
        DefaultMutableTreeNode tjgys = new DefaultMutableTreeNode("添加供应商");
        DefaultMutableTreeNode gyscx = new DefaultMutableTreeNode("供应商查询");
      DefaultMutableTreeNode xgyscgys = new DefaultMutableTreeNode("修改与删除供应商");
        gysgl.add(tjgys);
        gysgl.add(gyscx);
        gysgl.add(xgyscgys);

        DefaultMutableTreeNode xscx = new DefaultMutableTreeNode("销售查询");
        DefaultMutableTreeNode xstjcx = new DefaultMutableTreeNode("销售统计查询");
        xsgl.add(xscx);
        xsgl.add(xstjcx);
        
        DefaultMutableTreeNode khcx = new DefaultMutableTreeNode("客户查询");
        DefaultMutableTreeNode scyxgkhxx = new DefaultMutableTreeNode("删除与修改客户信息");
        khgl.add(khcx);
        khgl.add(scyxgkhxx);
        
        

        JTree jt = new JTree(sort);
        f.add(jt);
        f.setSize(1200, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private Container getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
        new TreeDemo();
    }
}