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
        DefaultMutableTreeNode sort= new DefaultMutableTreeNode("ҩƷ���������ϵͳ");
        DefaultMutableTreeNode ypgl = new DefaultMutableTreeNode("ҩƷ����");
        DefaultMutableTreeNode zdcx = new DefaultMutableTreeNode("�˵���ѯ");
        DefaultMutableTreeNode gysgl = new DefaultMutableTreeNode("��Ӧ�̹���");
        DefaultMutableTreeNode khgl = new DefaultMutableTreeNode("�ͻ�����");
        DefaultMutableTreeNode xsgl = new DefaultMutableTreeNode("���۹���");

        sort.add(ypgl);
        sort.add(zdcx);
        sort.add(gysgl);
        sort.add(xsgl);
        sort.add(khgl);
        
        DefaultMutableTreeNode cxypxx = new DefaultMutableTreeNode("��ѯҩƷ��Ϣ");
        DefaultMutableTreeNode tjypxx = new DefaultMutableTreeNode("���ҩƷ��Ϣ");
        DefaultMutableTreeNode xghscypxx = new DefaultMutableTreeNode("�޸Ļ�ɾ��ҩƷ��Ϣ");
        DefaultMutableTreeNode ypjhrk = new DefaultMutableTreeNode("ҩƷ�������");
        DefaultMutableTreeNode ypxskd = new DefaultMutableTreeNode("ҩƷ���ۿ���");
        DefaultMutableTreeNode ypbskd = new DefaultMutableTreeNode("ҩƷ���𿪵�");
        DefaultMutableTreeNode ypthcl = new DefaultMutableTreeNode("ҩƷ�˻�����");
        DefaultMutableTreeNode ypkccx = new DefaultMutableTreeNode("ҩƷ����ѯ");
        DefaultMutableTreeNode yplbgl = new DefaultMutableTreeNode("ҩƷ������");
        ypgl.add(cxypxx);
        ypgl.add(tjypxx);
        ypgl.add(xghscypxx);
        ypgl.add(ypjhrk);
        ypgl.add(ypxskd);
        ypgl.add(ypbskd);
        ypgl.add(ypthcl);
        ypgl.add(ypkccx);
        ypgl.add(yplbgl);
        

        DefaultMutableTreeNode jhdcx = new DefaultMutableTreeNode("��������ѯ");
        DefaultMutableTreeNode xsdcx = new DefaultMutableTreeNode("���۵���ѯ");
        DefaultMutableTreeNode bsdcx = new DefaultMutableTreeNode("���𵥲�ѯ");
        DefaultMutableTreeNode thdcx = new DefaultMutableTreeNode("�˻�����ѯ");
        zdcx.add(jhdcx);
        zdcx.add(xsdcx);
        zdcx.add(bsdcx);
        zdcx.add(thdcx);

        
        DefaultMutableTreeNode tjgys = new DefaultMutableTreeNode("��ӹ�Ӧ��");
        DefaultMutableTreeNode gyscx = new DefaultMutableTreeNode("��Ӧ�̲�ѯ");
      DefaultMutableTreeNode xgyscgys = new DefaultMutableTreeNode("�޸���ɾ����Ӧ��");
        gysgl.add(tjgys);
        gysgl.add(gyscx);
        gysgl.add(xgyscgys);

        DefaultMutableTreeNode xscx = new DefaultMutableTreeNode("���۲�ѯ");
        DefaultMutableTreeNode xstjcx = new DefaultMutableTreeNode("����ͳ�Ʋ�ѯ");
        xsgl.add(xscx);
        xsgl.add(xstjcx);
        
        DefaultMutableTreeNode khcx = new DefaultMutableTreeNode("�ͻ���ѯ");
        DefaultMutableTreeNode scyxgkhxx = new DefaultMutableTreeNode("ɾ�����޸Ŀͻ���Ϣ");
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