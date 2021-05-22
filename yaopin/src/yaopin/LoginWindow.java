package yaopin;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;





public class LoginWindow extends JFrame implements ActionListener {
	Container contain;
	JPanel loginPanel;  
    JLabel loginBackground,userNameLabel,passwordLabel,captchaLabel;  
    JLabel xianmuName;
    ImageIcon loginBackgroundIcon;
//    String username = "admin";
//	String password = "123";  
	static JTextField userName;
	JTextField captcha;
	JPasswordField password;
	JButton loginButton,resetButton;
	Box boxHZero,boxHOne,boxHTwo,boxHThree,boxHFour,boxV;
	ValidCode vcode = new ValidCode();
	/*��¼����
	 * 
	 * 
	 * */
	public LoginWindow(){
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		loginBackgroundIcon = new ImageIcon("dljm1.jpg");
		loginPanel = new JPanel();
		/*����ͼƬ*/
		loginBackground =new JLabel(loginBackgroundIcon); 
		this.getLayeredPane().add(loginBackground, new Integer(Integer.MIN_VALUE));
		loginBackground.setBounds(0, 0, loginBackgroundIcon.getIconWidth(), loginBackgroundIcon.getIconHeight());
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
		
		loginButton = new JButton("��¼");
		resetButton = new JButton("����");
		userNameLabel =new JLabel("�û���");
		passwordLabel =new JLabel("����");
		captchaLabel =new JLabel("��֤��");
		xianmuName = new JLabel("ҩƷ���������ϵͳ");
		
	
		userNameLabel.setFont(new Font("΢���ź�", Font.BOLD, 15));
		passwordLabel.setFont(new Font("΢���ź�", Font.BOLD, 15));
		captchaLabel.setFont(new Font("΢���ź�", Font.BOLD, 15));
		xianmuName.setFont(new Font("������κ",Font.PLAIN,30));
		loginButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		resetButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		
		
		
		
		
		userName = new JTextField(12);
		captcha = new JTextField(5);
		password = new JPasswordField(12);
		password.setEchoChar('*');
		
		boxHZero = Box.createHorizontalBox();
		boxHZero.add(xianmuName);
		
		
		boxHOne = Box.createHorizontalBox(); 
		boxHOne.add(userNameLabel);
		boxHOne.add(Box.createHorizontalStrut(20));
		boxHOne.add(userName);
		
		boxHTwo = Box.createHorizontalBox();
		boxHTwo.add(passwordLabel);
		boxHTwo.add(Box.createHorizontalStrut(33));
		boxHTwo.add(password);
		
		boxHThree = Box.createHorizontalBox();
		boxHThree.add(captchaLabel);
		boxHThree.add(Box.createHorizontalStrut(18));
		boxHThree.add(captcha);
		boxHThree.add(Box.createHorizontalStrut(20));
		boxHThree.add(vcode);
		
		
		
		boxHFour = Box.createHorizontalBox();
		boxHFour.add(loginButton);
		loginButton.addActionListener(this);
		boxHFour.add(Box.createHorizontalStrut(10));
		boxHFour.add(resetButton);
		resetButton.addActionListener(this);
		
		boxV = Box.createVerticalBox();
		boxV.add(boxHZero);
		boxV.add(Box.createVerticalStrut(30));
		boxV.add(boxHOne);
		boxV.add(Box.createVerticalStrut(10));
		boxV.add(boxHTwo);
		boxV.add(Box.createVerticalStrut(10));
		boxV.add(boxHThree);
		boxV.add(Box.createVerticalStrut(10));
		boxV.add(boxHFour);
		contain.add(boxV);
	}
		

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "��¼") {
			if(captcha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "��������֤��!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			}else {
				if(!isValidCodeRight()) {
					JOptionPane.showMessageDialog(null, "��֤�����,����������!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					clear();
				}else if(isValidCodeRight()) {
					
						//JOptionPane.showMessageDialog(null, "��¼�ɹ�!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					System.out.println(userName.getText() + password.getText());
						if(SQL.LoginCheck(userName.getText(), password.getText())) {
							dispose();
							new MainWindow();
						}
					}else if(userName.getText().isEmpty()&&password.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "�������û���������!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
						clear();
					}else if(userName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "�������û���!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
						clear();
					}else if(password.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "����������!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
						clear();
					}else {
						JOptionPane.showMessageDialog(null, "�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
						clear();
					}
				}
			}
			
			}
			
	
	
	
	private void clear() {
		// TODO Auto-generated method stub
		userName.setText("");
		password.setText("");
		captcha.setText("");
	}
	//��֤���ȷ��
	public boolean isValidCodeRight() {
		if(password == null) {
			return false;
		}else if(vcode == null) {
			return true;
		}else if(vcode.getCode().equals(captcha.getText())) {
			return true;
		}else 
			return false;
	}

	public static String getUser() {
		return userName.getText();
	}
	
	
	 
}
