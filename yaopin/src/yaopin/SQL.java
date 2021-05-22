package yaopin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SQL {
	// ������ݾ���
	public static void setTableCenter(MyTable table, int length) {

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JTextField.CENTER);
		for (int i = 0; i < length; i++)
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);

	}

	// ��ʾ������
	public static void OutputNumber(JTable table) {

		for (int i = 0; i < table.getRowCount(); i++) {
			DecimalFormat df = new DecimalFormat("000");
			table.setValueAt(df.format(i + 1), i, 0);
		}
	}

	/********************** ��¼���� ****************************/

	// ����û���������
	public static boolean LoginCheck(String username, String password) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean m = false;
		String sql;

		sql = "select password from admin where username = '" + username + "'";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String pwd = rs.getString("password");
				// System.out.println(pwd+"aaa");
				if (pwd.trim().equals(password)) {
					m = true;
				} else {
					JOptionPane.showMessageDialog(null, "�������");
				}
			} else {
				JOptionPane.showMessageDialog(null, "�û��������ڣ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}

	/********************** ҩƷ������ *************************/

	private static DefaultTableModel medicinemodel = null;

	public static void setmedicinemodel(DefaultTableModel _medicinemodel) {
		medicinemodel = _medicinemodel;
	}

	// ��ʾҩƷ��Ϣ
	private static void MedicineOutput(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (medicinemodel.getRowCount() > 0) {
				medicinemodel.removeRow(medicinemodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
					medicinemodel.addRow(new String[] { "", rs.getString("[G _C]"), rs.getString("G_P"),
							rs.getString("G_NA"), rs.getString("G_SC"), rs.getString("G_M"), rs.getString("G_D"),
							rs.getString("G_AC"), rs.getString("G_ST"), rs.getString("G_SCODE"),
							rs.getString("G_TOV"),rs.getString("G_BN"), rs.getString("G_PR"),rs.getString("G_PT"),});
//					[G _C]��G_P��G_NA��G_SC��G_M��G_D��G_AC��G_ST��G_SCODE��G_TOV��G_BN��G_PR��G_PT��
				}
				try {
					OutputNumber(MedicinePanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					OutputNumber(MedicinePanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// ��ѯ����ҩƷ��Ϣ
	public static void MedicineQuery() throws SQLException {
		String SQL = "SELECT * FROM GOODS";
		MedicineOutput(SQL);

	}

	// ����ҩƷ��Ϣ
	public static void MedicineSel(String conditions) throws SQLException {

		String SQL = "select * from GOODS\r\n" + "where [G_C] LIKE '%" + conditions + "%' or G_P LIKE '%"
				+ conditions + "%' or G_NA LIKE '%" + conditions + "%' or G_SC LIKE '%" + conditions
				+ "%' or G_M LIKE '%" + conditions + "%' or G_D LIKE '%" + conditions + "%' or G_AC LIKE '%"
				+ conditions + "%' or G_ST LIKE '%" + conditions + "%' or G_SCODE LIKE '%" + conditions
				+ "%' or G_TOV LIKE '%" + conditions + "%'  or G_BN LIKE '%" + conditions + "%'or G_PR '%" + 
				conditions + "%' or G_PT '%" + conditions + "%' ";
		//[G_C]��G_P��G_NA��G_SC��G_M��G_D��G_AC��G_ST��G_SCODE��G_TOV��G_BN��G_PR��G_PT��
		MedicineOutput(SQL);
	}

	// ҩƷ����У��
	public static boolean isMNO(String mno) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "select * from GOODS";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			if (rs != null) {
				while (rs.next()) {
					String mno1 = rs.getString("[G_C]");
					if (mno1.equals(mno)) {
						return false;
					}
				}
			}
		} catch (SQLException ee) {

		}
		return true;

	}

	// ���ҩƷ��Ϣ
	public static void MedicineInsert(String mno, String name, String type, String specifications, String _amount,
			String _jhPrice, String batchno, String _lsPrice, String _validity, String note) {

		int amount = 0;
		float jhPrice = 0f;
		float lsPrice = 0f;
		int validity = 0;
		try {
			amount = Integer.parseInt(_amount);
			jhPrice = Float.parseFloat(_jhPrice);
			lsPrice = Float.parseFloat(_lsPrice);
			validity = Integer.parseInt(_validity);

			System.out.println(mno + " " + name + " " + type + " " + specifications + " " + amount + " " + jhPrice + " "
					+ batchno + " " + lsPrice + " " + validity + " " + note);
			try {
				String SQL = "INSERT INTO GOODS([G_C], PHOTEFILE,GOODS_NAME, [SORT _CODE],"
						+ "MODEL, DOSE, AERO_CODE, STOCK, SUPP_CODE, TERM_OF_VALIDITY,BATCH_NUMBER,PRICE_RETAIL,PRICE_TRADE)"
						+ " VALUES ('" + mno + "','" + type + "','" + name + "','" + specifications + "'," + validity
						+ ",'" + batchno + "'," + jhPrice + "," + lsPrice + "," + amount + ",'" + note + "') ";
				// System.out.println(SQL);
				//[G_C]��G_P��G_NA��G_SC��G_M��G_D��G_AC��G_ST��G_SCODE��G_TOV��G_BN��G_PR��G_PT��
				if (!Sql1.executeUpdate(SQL)) {
					if (mno.length() > 6) {
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ� ҩƷ��ų���6λ");

					} else {
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");

					}
				} else {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
				}
				MedicineQuery();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���������");
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}
	}

	// ɾ��ҩƷ��Ϣ
	public static void MedicineDelete(String Mno) {
		try {
			String SQL = "DELETE FROM GOODS WHERE [G_C] = ' + [G_C] + '";
			if (!Sql1.executeUpdate(SQL)) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			}
			MedicineQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ����ҩƷ��Ϣ
	public static void MedicineUpdate(String mno, String name, String type, String specifications, String _amount,
			String _jhPrice, String batchno, String _lsPrice, String _validity, String note) {
//		mno, name, type, specifications, amount, jhPrice, batchno, lsPrice, validity, note
		int amount = 0;
		float jhPrice = 0f;
		float lsPrice = 0f;
		int validity = 0;
		try {
			amount = Integer.parseInt(_amount);
			jhPrice = Float.parseFloat(_jhPrice);
			lsPrice = Float.parseFloat(_lsPrice);
			validity = Integer.parseInt(_validity);
			try {
				String SQL = "UPDATE GOODS SET G_NA = '" + name + "'," + " [G_C] = '" + type + "'," + " M_Gg = '"
						+ specifications + "'," + " M_Yxq = " + validity + "," + " M_Ph = '" + batchno + "',"
						+ " M_Jhj  = " + jhPrice + "," + " M_Lsj  = " + lsPrice + "," + " M_Kcl = " + amount + ","
						+ " M_Bz  = '" + note + "'" + " WHERE M_No = '" + mno + "'";
				//[G_C]��G_P��G_NA��G_SC��G_M��G_D��G_AC��G_ST��G_SCODE��G_TOV��G_BN��G_PR��G_PT��
				System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				}
				MedicineQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}

	}

	// ��ȡ���
//	public static String getAmount(String mno) {
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		String amount = null;
//		
//		String SQL = "SELECT M_Kcl FROM Medicine WHERE M_No =  '" + mno + "'";
//		
//		try {
//			conn = Sql1.getCoonection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(SQL);
//	
//			while(rs.next()) {
//				amount = rs.getString("amount").trim();				
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		finally {
//			try {
//				rs.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return amount;
//	}

//	// ��ȡҩƷ����
//	public static ArrayList<String> GetMedicineType() {
//
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList<String> MedicineType = new ArrayList<String>();
//		// ArrayListһ��ʵ���࣬����ʵ�������С�Ŀɱ䣬���Ժܷ���Ľ������Ӻ�ɾ��������Ԫ�صĲ���
//
//		MedicineType.add("");
//		String SQL = "SELECT DISTINCT MS_No FROM Medicine ORDER BY MS_No ASC";
//
//		try {
//			conn = Sql1.getCoonection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(SQL);
//
//			while (rs.next()) {
//				MedicineType.add(rs.getString("MS_No").trim());// .trim�õ�һ���µ�String����
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			try {
//				rs.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		return MedicineType;
//	}
//
//	// ��ȡҩƷ�����±�
//	public static int GetMedicineTypeInedx(ArrayList<String> MedicineType, String type) {
//		return MedicineType.indexOf(type);
//	}
//	
//
//	// ҩƷ����ѡ��
//	public static void MedicineTypeSelect(String type, JComboBox typeBox) {
//		if (type == null)
//			typeBox.setSelectedIndex(0);
//		else
//			typeBox.setSelectedIndex(GetMedicineTypeInedx(GetMedicineType(), type));
//	}
//	

	// ��ȡҩƷ�������
	public static ArrayList<String> GetMedicineSortNameType() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> MedicineSortNameType = new ArrayList<String>();
		// ArrayListһ��ʵ���࣬����ʵ�������С�Ŀɱ䣬���Ժܷ���Ľ������Ӻ�ɾ��������Ԫ�صĲ���

		MedicineSortNameType.add("");
		String SQL = "SELECT DISTINCT G_NA FROM GOODS ORDER BY G_NA ASC";

		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				MedicineSortNameType.add(rs.getString("G_NA").trim());// .trim�õ�һ���µ�String����
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return MedicineSortNameType;
	}

	// ��ȡҩƷ��������±�
	public static int GetMedicineSortNameTypeInedx(ArrayList<String> MedicineSortNameType, String msname) {
		return MedicineSortNameType.indexOf(msname);
	}

	// ҩƷ�������ѡ��
	public static void MedicineSortNameTypeSelect(String msname, JComboBox typeNameBox) {
		if (msname == null)
			typeNameBox.setSelectedIndex(0);
		else
			typeNameBox.setSelectedIndex(GetMedicineSortNameTypeInedx(GetMedicineSortNameType(), msname));
	}

	// ����������ҩƷ��Ϣ
	public static String typeNoSel(String choice) throws SQLException {
		String SQL = null;
		String result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (choice != null) {
			SQL = "SELECT [G_C] FROM GOODS WHERE G_NA = '" + choice + "'";
		}
		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				result = rs.getString("[G_C]").trim();// .trim�õ�һ���µ�String����
			}
		} catch (Exception e) {

		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/********************** �ͻ������� *************************/

	private static DefaultTableModel customermodel = null;

	public static void setcustomermodel(DefaultTableModel _customermodel) {
		customermodel = _customermodel;
	}

	// ��ʾ�ͻ���Ϣ
	private static void CustomerOutput(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (customermodel.getRowCount() > 0) {
				customermodel.removeRow(customermodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
					customermodel.addRow(new String[] { "", rs.getString("C_No"), rs.getString("C_Name"),
							rs.getString("C_Dz"), rs.getString("C_Dh"), });

				}
				try {
					OutputNumber(CustomerPanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					OutputNumber(CustomerPanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// ��ѯ���пͻ���Ϣ
	public static void CustomerQuery() throws SQLException {
		String SQL = "SELECT * FROM Customer";
		CustomerOutput(SQL);

	}

	// �����������ͻ���Ϣ
	public static void CustomerSel(String conditions) throws SQLException {

		String SQL = "select * from Customer\r\n" + "where C_No LIKE '%" + conditions + "%' or C_Name LIKE '%"
				+ conditions + "%' or C_Dz LIKE '%" + conditions + "%' or C_Dh LIKE '%" + conditions + "%' ";
		CustomerOutput(SQL);
	}

	// �ͻ�����У��
	public static boolean isCNO(String cno) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "select * from Customer";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			if (rs != null) {
				while (rs.next()) {
					String cno1 = rs.getString("C_No");
					if (cno1.equals(cno)) {
						return false;
					}
				}
			}
		} catch (SQLException ee) {

		}
		return true;

	}

	// ��ӿͻ���Ϣ cno, name, address, tell
	public static void CustomerInsert(String cno, String name, String address, String tell) {

		try {
			try {
				String SQL = "INSERT INTO Customer(C_No, C_Name, C_Dz,C_Dh)" + " VALUES ('" + cno + "','" + name + "','"
						+ address + "','" + tell + "') ";
				// System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					if (cno.length() > 6)
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ� �ͻ����볬��6λ");
					else
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
				}
				CustomerQuery();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���������");
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}
	}

	// ɾ���ͻ���Ϣ
	public static void CustomerDelete(String C_No) {
		try {
			String SQL = "DELETE FROM Customer WHERE C_No = '" + C_No + "'";
			if (!Sql1.executeUpdate(SQL)) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			}
			CustomerQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ���¿ͻ���Ϣ
	public static void CustomerUpdate(String cno, String name, String address, String tell) {

		try {

			try {
//				System.out.println(isbn + type + name + writer + amount + press + price);
				String SQL = "UPDATE Customer SET C_Name = '" + name + "'," + " C_Dz = '" + address + "'," + " C_Dh = '"
						+ tell + "'" + " where C_No='" + cno + "'";

				if (!Sql1.executeUpdate(SQL)) {
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				}
				CustomerQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}

	}

	/********************** Ա�������� *************************/

	private static DefaultTableModel employeemodel = null;

	public static void setemployeemodel(DefaultTableModel _employeemodel) {
		employeemodel = _employeemodel;
	}

	// Ա��ҩƷ��Ϣ
	private static void EmployeeOutput(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (employeemodel.getRowCount() > 0) {
				employeemodel.removeRow(employeemodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null&&rs.next()) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
					employeemodel.addRow(new String[] { "", rs.getString("E_C"), rs.getString("E_NA"),
							rs.getString("E_G"), rs.getString("E_AGE"), rs.getString("E_E"), rs.getString("E_P") });

				}
				try {
					OutputNumber(EmployeePanel.GetTable());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ʧ��1��");// TODO: handle exception
				}
				try {
					OutputNumber(EmployeePanel.GetTable());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ʧ��2��");// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// ��ѯ����Ա����Ϣ
	public static void EmployeeQuery() throws SQLException {
		String SQL = "SELECT * FROM EMPLOYEES";
		EmployeeOutput(SQL);

	}

	// ����������Ա����Ϣ
	public static void EmployeeSel(String conditions) throws SQLException {

		String SQL = "select * from EMPLOYEES\r\n" + "where E_C LIKE '%" + conditions + "%' or E_NA LIKE '%"
				+ conditions + "%' or E_G LIKE '%" + conditions + "%'or E_AGE LIKE '%" + conditions
				+ "%' or E_E LIKE '%" + conditions + "%' or E_P LIKE '%" + conditions + "%'";

		EmployeeOutput(SQL);
	}

	// Ա������У��
	public static boolean isENO(String eno) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "select * from EMPLOYEES";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			if (rs != null) {
				while (rs.next()) {
					String eno1 = rs.getString("E_C");
					if (eno1.equals(eno)) {
						return false;
					}
				}
			}
		} catch (SQLException ee) {

		}
		return true;

	}


	// ���Ա����Ϣ
	public static void EmployeeInsert(String eno, String name, String sex, String _age, String xueli, String zhiwu,
			String address) {

		int age = 0;
		try {
			age = Integer.parseInt(_age);
			try {
				String SQL = "INSERT INTO EMPLOYEES(E_C��E_NA��E_G��E_AGE��E_E��E_P)" + " VALUES ('" + eno
						+ "','" + name + "','" + sex + "'," + age + ",'" + xueli + "','" + zhiwu + "',' ) ";
				// System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					if (eno.length() > 6)
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ� Ա����ų���6λ");
					else
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
				}
				EmployeeQuery();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���������");
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}
	}

	// ɾ��Ա����Ϣ
	public static void EmployeeDelete(String Eno) {
		try {
			String SQL = "DELETE FROM EMPLOYEES WHERE E_C = '" + Eno + "'";
			if (!Sql1.executeUpdate(SQL)) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			}
			EmployeeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
//	E_C��E_NA��E_G��E_AGE��E_E��E_P��

	// ����Ա����Ϣ
	public static void EmployeeUpdate(String eno, String name, String sex, String _age, String xueli, String zhiwu,
			String address) {

		int age = 0;

		try {
			age = Integer.parseInt(_age);
			try {
				String SQL = "UPDATE EMPLOYEES SET E_C = '" + eno + "'," + " E_NA = '" + name + "'," + " E_G = '"
						+ sex + "'," + " E_AGE = " + age + "," + " E_E  = '" + xueli + "'," + " E_P  = '" + zhiwu + "',"
								+ "" + " WHERE E_C = '" + eno + "'";
				System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				}
				EmployeeQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}

	}

	/********************** ��Ӧ�̹����� *************************/
	private static DefaultTableModel suppliermodel = null;

	public static void setsuppliermodel(DefaultTableModel _suppliermodel) {
		suppliermodel = _suppliermodel;
	}

	// ��Ӧ����Ϣ����������
	private static void SupplierOutput(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (suppliermodel.getRowCount() > 0) {
				suppliermodel.removeRow(suppliermodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
					suppliermodel.addRow(
							new String[] { "", rs.getString("S_No"), rs.getString("S_Name"), rs.getString("S_Dz"),
									rs.getString("S_Dh"), rs.getString("S_Yhzh"), rs.getString("S_Khh") });
//					
				}
				try {
					OutputNumber(SupplierPanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					OutputNumber(SupplierPanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ʾ������Ϣʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// ��ѯ���й�Ӧ����Ϣ
	public static void SupplierQuery() throws SQLException {
		String SQL = "SELECT * FROM Supplier";
		SupplierOutput(SQL);

	}

	// ������������Ϣ
	public static void SupplierSel(String conditions) throws SQLException {
		String SQL = "select * from Supplier\r\n" + "where S_No LIKE '%" + conditions + "%' or S_Name LIKE '%"
				+ conditions + "%' or S_Dz LIKE '%" + conditions + "%'or S_Dh LIKE '%" + conditions
				+ "%' or S_Yhzh LIKE '%" + conditions + "%' or S_Khh LIKE '%" + conditions + "%'";

		SupplierOutput(SQL);
	}

	// ��Ӧ�̱���У��
	public static boolean isSNO(String sno) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQL = "select * from Supplier";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			if (rs != null) {
				while (rs.next()) {
					String sno1 = rs.getString("S_No");
					if (sno1.equals(sno)) {
						return false;
					}
				}
			}
		} catch (SQLException ee) {

		}
		return true;

	}

//	sno, name, address, tell, cardno, bank;
	// ��ӹ�Ӧ����Ϣ
	public static void SupplierInsert(String sno, String name, String address, String tell, String cardno,
			String bank) {

		// int age = 0;
		try {
			// age = Integer.parseInt(_age);
			try {
				String SQL = "INSERT INTO Supplier(S_No, S_Name, S_Dz, S_Dh, S_Yhzh, S_Khh)" + " VALUES ('" + sno
						+ "','" + name + "','" + address + "'," + tell + ",'" + cardno + "','" + bank + "') ";
				// System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					if (sno.length() > 7)
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ� ��Ӧ�̱�ų���7λ");
					else
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
				}
				SupplierQuery();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���������");
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}
	}

	// ɾ����Ӧ����Ϣ
	public static void SupplierDelete(String Sno) {
		try {
			String SQL = "DELETE FROM Supplier WHERE S_No = '" + Sno + "'";
			if (!Sql1.executeUpdate(SQL)) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			}
			SupplierQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

//	sno, name, address, tell, cardno, bank;
	// ���¹�Ӧ����Ϣ
	public static void SupplierUpdate(String sno, String name, String address, String tell, String cardno,
			String bank) {

		// int age = 0;

		try {
			// age = Integer.parseInt(_age);
			try {
				String SQL = "UPDATE Supplier SET S_No = '" + sno + "'," + " S_Name = '" + name + "'," + " S_Dz = '"
						+ address + "'," + " S_Dh = '" + tell + "'," + " S_Yhzh = '" + cardno + "'," + " S_Khh  = '"
						+ bank + "' " + " WHERE S_No = '" + sno + "'";
				System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				}
				SupplierQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}

	}

	/********************** ���������� *****************************/

	private static DefaultTableModel Purchasemodel = null;

	public static void setPurchasemodel(DefaultTableModel _Purchasemodel) {
		Purchasemodel = _Purchasemodel;
	}

	// ��ʾ������Ϣ
	private static void PurchaseOutput(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (Purchasemodel.getRowCount() > 0) {
				Purchasemodel.removeRow(Purchasemodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
					Purchasemodel.addRow(new String[] { "", rs.getString("[I_GC]"), rs.getString("I_P"),
							rs.getString("I_GNA"), rs.getString("I_WT"), rs.getString("I_A"), rs.getString("I_PR"),
							rs.getString("I_PT"),rs.getString("I_SC"),rs.getString("I_EC") });

					
				}
				try {
					OutputNumber(JinhuoPanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					OutputNumber(JinhuoPanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	

	// ��ѯ���н�����Ϣ
	public static void PurchaseQuery() throws SQLException {
		String SQL = "SELECT * FROM INSTOCK";
		PurchaseOutput(SQL);

	}

	// ����������Ϣ
	public static void PurchaseSel(String conditions) throws SQLException {

		String SQL = "select * from INSTOCK\r\n" + "where [I_GC] LIKE '%" + conditions + "%' or I_P LIKE '%"
				+ conditions + "%' or I_GNA LIKE '%" + conditions + "%' or I_WT LIKE '%" + conditions
				+ "%' or I_A LIKE '%" + conditions + "%' or I_PR LIKE '%" + conditions + "%' or I_PT LIKE '%"
				+ conditions + "%'or I_SC LIKE '%"+ conditions + "%'or I_EC LIKE '%"
				+ conditions + "%'";
		PurchaseOutput(SQL);
	}
	
	// ��ӽ�����Ϣ pno, date, eno, sum, sno, account, note;
	public static void PurchaseInsert(String I_GC, String I_P, String I_GNA, String I_WT, String I_A, String I_PR,
			String I_PT,String I_SC,String I_EC) {

		float sum = 0f;
		try {

			sum = Float.parseFloat(I_PT);

//			System.out.println(mno + " " + name + " " + type + " " + specifications + " " + amount + " " + jhPrice + " "
//					+ batchno + " " + lsPrice + " " + validity + " " + note);
			try {
				String SQL = "INSERT INTO INSTOCK(I_GC,I_P,I_GNA,I_WT,I_A,I_PR,I_PT,I_SC,I_EC)" + " VALUES ('" + I_GC
						+ "','" + I_P + "','" + I_GNA + "','" + I_WT + "','" + I_A + "','" + I_PR + "','" + sum + "','" + I_SC + "','" + I_EC + "') ";
				// System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "�����ɹ���");
				}
				PurchaseQuery();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���������");
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}
	}

	// ɾ��������Ϣ
	public static void PurchaseDelete(String I_GC) {
		try {
			String SQL = "DELETE FROM INSTOCK WHERE I_GC = '" + I_GC + "'";
			if (!Sql1.executeUpdate(SQL)) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			}
			PurchaseQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//[I_GC];I_P;I_GNA;I_WT;I_A;I_PR;I_PT;I_SC;I_EC;
	// ����ҩƷ��Ϣ
	public static void PurchaseUpdate(String pno, String date, String eno, String _sum, String sno, String account,
			String note) {
//		mno, name, type, specifications, amount, jhPrice, batchno, lsPrice, validity, note

		float sum = 0f;

		try {

			sum = Float.parseFloat(I_PT);
//			(P_No, P_Rq, E_No, P_Zje, S_No, P_Jzr, P_Bz)
			try {
				String SQL = "UPDATE INSTOCK SET P_Rq = '" + date + "'," + " E_No = '" + eno + "'," + " S_No = '" + sno
						+ "'," + " P_Jzr = '" + account + "'," + " P_Bz = '" + note + "'," + "P_Zje ="+ sum +" WHERE P_No = '" + pno + "'";
				System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				}
				PurchaseQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}

	}

	public static void GetPurchaseSum(MyTable table, JTextField sumText) {
		float sum = 0f, price = 0f;

		try {
			for (int i = 0; i < table.getRowCount(); i++) {
				price = Float.parseFloat((String) table.getValueAt(i, PurchaseDetailsmodel.findColumn("�ܽ��")));
				sum += price;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		sumText.setText(String.valueOf(sum));
	}

	// ���½����ܽ��
	public static void PurchaseSumUpdate(float sum, String pno) {

		try {

			try {
//				System.out.println(isbn + type + name + writer + amount + press + price);
				String SQL = "UPDATE Purchase SET P_Zje = " + sum + "WHERE P_No ='" + pno + "'";

				CustomerQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}

	}

	// ��ȡ���충������Զ�������һ���������
	public static String getPDN(String date) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String MaxNumber = "000";
		String NextNumber = "001";

		String SQL = "select max(right(PD_No, 3)) " + "as 'Max' from PurchaseDetails  where PD_No like '" + date + "%'";// (right(order_id,
		// 3)ѡȡ����λ
		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				MaxNumber = rs.getString("Max").trim();
			}

			int nextnumber = Integer.parseInt(MaxNumber) + 1;
			DecimalFormat df = new DecimalFormat("000");// ��ʽ��ʮ�������� ��ʽΪ��λ��
			NextNumber = df.format(nextnumber);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			NextNumber = date + NextNumber;
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return NextNumber;
	}

	// *************************************������ϸ������***************************************//

	private static DefaultTableModel PurchaseDetailsmodel = null;

	public static void setPurchaseDetailsmodel(DefaultTableModel _PurchaseDetailsmodel) {
		PurchaseDetailsmodel = _PurchaseDetailsmodel;
	}

	// ��ʾ������¼
	private static void PurchaseDetailsOutput(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (PurchaseDetailsmodel.getRowCount() > 0) {
				PurchaseDetailsmodel.removeRow(PurchaseDetailsmodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
					PurchaseDetailsmodel.addRow(new String[] { "", rs.getString("PD_No") , rs.getString("M_No"), rs.getString("M_Name"),
							rs.getString("PD_Gg"), rs.getString("PD_Sl"), rs.getString("PD_Dj"), rs.getString("PD_Je"),
							rs.getString("PD_Bz")});
				}
				try {
					OutputNumber(JinhuoAddMess.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					OutputNumber(JinhuoAddMess.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private static void CheckPurchaseDetails(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (PurchaseDetailsmodel.getRowCount() > 0) {
				PurchaseDetailsmodel.removeRow(PurchaseDetailsmodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
					PurchaseDetailsmodel.addRow(new String[] { "", rs.getString("PD_No"), rs.getString("M_No"), rs.getString("M_Name"),rs.getString("PD_Gg"), rs.getString("PD_Sl"), rs.getString("PD_Dj"), rs.getString("PD_Je"),
							rs.getString("PD_Bz") });
				}
				try {
					OutputNumber(CheckJinhuoMess.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					OutputNumber(CheckJinhuoMess.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	

	// ��ѯ���������ϸ��¼
	public static void PurchaseDetailsQuery(String Pno) throws SQLException {
		String SQL = "select PD_No, Medicine.M_No,M_Name,PD_Gg,PD_Sl,PD_Dj,PD_Je,PD_Bz\r\n"
				+ "from Medicine,PurchaseDetails\r\n" + "where Medicine.M_No =PurchaseDetails.M_No and P_No ='" + Pno
				+ "'";
		PurchaseDetailsOutput(SQL);
		CheckPurchaseDetails(SQL);
	}

//		static String Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note,Name; 

	// ���ҩƷ������¼ Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note
	public static void PurchaseDetailsInsert(String Pdno, String Pno, String Mno, String Specifications, String _Num,
			String _Jhprice, String _Sum, String Note) {

		int Num = 0;
		float Jhprice = 0f;
		float Sum = 0f;

		try {
			Num = Integer.parseInt(_Num);
			Jhprice = Float.parseFloat(_Jhprice);
			Sum = Float.parseFloat(_Sum);

			try {
				String SQL = "INSERT INTO PurchaseDetails(PD_No, P_No, M_No, PD_Sl, PD_Dj, PD_Je, PD_Bz, PD_Gg)"
						+ " VALUES ('" + Pdno + "','" + Pno + "','" + Mno + "'," + Num + "," + Jhprice + "," + Sum
						+ ",'" + Note + "','" + Specifications + "') ";
				System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					if (Pdno.length() > 15) {
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ� ������ų���15λ");

					} else {
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");

					}
				} else {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
				}
				PurchaseDetailsQuery(Pno);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���������");
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}
	}

	// ɾ��������¼
	public static void PurchaseDetailsDelete(String Pdno, String Pno) {

		try {
			String SQL = "DELETE FROM PurchaseDetails WHERE PD_No = '" + Pdno + "'";
			System.out.println(SQL);
			if (!Sql1.executeUpdate(SQL)) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			}
			PurchaseDetailsQuery(Pno);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static ArrayList<String> GetmnoType() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> mnoType = new ArrayList<String>();
		// ArrayListһ��ʵ���࣬����ʵ�������С�Ŀɱ䣬���Ժܷ���Ľ������Ӻ�ɾ��������Ԫ�صĲ���

		mnoType.add("");
		String SQL = "SELECT DISTINCT M_No FROM Medicine ORDER BY M_No ASC";

		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				mnoType.add(rs.getString("M_No").trim());// .trim�õ�һ���µ�String����
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mnoType;
	}

//	// ��ȡҩƷ��������±�
//	public static int GetmnoTypeInedx(ArrayList<String> MedicineSortNameType, String mno) {
//		return MedicineSortNameType.indexOf(mno);
//	}
//
//	// ҩƷ�������ѡ��
//	public static void mnoTypeSelect(String mno, JComboBox typeNameBox) {
//		if (mno == null)
//			typeNameBox.setSelectedIndex(0);
//		else
//			typeNameBox.setSelectedIndex(GetmnoTypeInedx(GetMedicineSortNameType(), mno));
//	}

	// �������б�ѡ���ѯҩƷ������Ϣ
	public static String nameTypeSel(String choice) throws SQLException {
		String SQL = null;
		String result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (choice != null) {
			SQL = "SELECT M_Name  FROM Medicine WHERE M_No = '" + choice + "'";
		}
		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				result = rs.getString("M_Name").trim();// .trim�õ�һ���µ�String����
			}
		} catch (Exception e) {

		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// �������б�ѡ���ѯҩƷ�����Ϣ
	public static String specificationsTypeSel(String choice) throws SQLException {
		String SQL = null;
		String result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (choice != null) {
			SQL = "SELECT M_Gg  FROM Medicine WHERE M_No = '" + choice + "'";
		}
		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				result = rs.getString("M_Gg").trim();// .trim�õ�һ���µ�String����
			}
		} catch (Exception e) {

		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// �������б�ѡ���ѯҩƷ�����Ϣ
		public static String amountTypeSel(String choice) throws SQLException {
			String SQL = null;
			String result = null;
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			if (choice != null) {
				SQL = "SELECT M_Kcl  FROM Medicine WHERE M_No = '" + choice + "'";
			}
			try {
				conn = Sql1.getCoonection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(SQL);
				while (rs.next()) {
					result = rs.getString("M_Kcl").trim();// .trim�õ�һ���µ�String����
				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
	
	

	// ���ӿ��
	public static void ChangeNum(String mno, String _amount) throws SQLException {
		Connection con;
		Statement stmt;
		ResultSet rs;

		String SQL = "SELECT M_Kcl FROM Medicine WHERE M_No = '" + mno + "'";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			if (rs != null) {
				while (rs.next()) {
					int num = Integer.parseInt(rs.getString("M_Kcl"));
					int amount = Integer.parseInt(_amount);
					num = num + amount;
					SQL = "UPDATE Medicine SET M_Kcl = " + num + " WHERE M_No = '" + mno + "'";
//				System.out.println(SQL);
					Sql1.executeUpdate(SQL);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException eee) {

		}
	}

	// ɾ����¼���¿��
	public static void deleteNum(String mno, String _amount) throws SQLException {
		Connection con;
		Statement stmt;
		ResultSet rs;

		String SQL = "SELECT M_Kcl FROM Medicine WHERE M_No = '" + mno + "'";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			if (rs != null) {
				while (rs.next()) {
					int num = Integer.parseInt(rs.getString("M_Kcl"));

					int amount = Integer.parseInt(_amount);

					num = num - amount;
					SQL = "UPDATE Medicine SET M_Kcl = " + num + " WHERE M_No = '" + mno + "'";
//					System.out.println(SQL);
					Sql1.executeUpdate(SQL);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException eee) {

		}
	}
	
	
	
	//*****************************************************************************
	/********************** ���۹����� *****************************/

	private static DefaultTableModel Salesmodel = null;

	public static void setSalesmodel(DefaultTableModel _Salesmodel) {
		Salesmodel = _Salesmodel;
	}

	// ��ʾ������Ϣ
	private static void SalesOutput(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (Salesmodel.getRowCount() > 0) {
				Salesmodel.removeRow(Salesmodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
//					{ "���", "���۵���", "����", "Ա�����", "�ͻ����", "������" , "�ܽ��","��ע"};
					Salesmodel.addRow(new String[] { "", rs.getString("SA_No"), rs.getString("SA_Date"),
							rs.getString("E_No"), rs.getString("C_No"), rs.getString("SA_Jzr"), rs.getString("SA_Zje")
							,rs.getString("SA_Bz") });
//					M_No, MS_No, M_Name, M_Gg, M_Yxq, M_Ph, M_Jhj, M_Lsj, M_Kcl, M_Bz
				}
				try {
					OutputNumber(SalesPanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					OutputNumber(SalesPanel.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
	
	
	// ��ѯ���н�����Ϣ
	public static void SalesQuery() throws SQLException {
		String SQL = "SELECT * FROM Sales";
		SalesOutput(SQL);

	}

	// ����������Ϣ
	public static void SalesSel(String conditions) throws SQLException {

		String SQL = "select * from Sales\r\n" + "where SA_No LIKE '%" + conditions + "%' or SA_Date LIKE '%"
				+ conditions + "%' or E_No LIKE '%" + conditions + "%' or SA_Zje LIKE '%" + conditions
				+ "%' or C_No LIKE '%" + conditions + "%' or SA_Jzr LIKE '%" + conditions + "%' or SA_Bz LIKE '%"
				+ conditions + "%'";
		SalesOutput(SQL);
	}

	// ��ӽ�����Ϣ sano, date, eno, sum, cno, account, note;
	public static void SalesInsert(String sano, String date, String eno, String _sum, String cno, String account,
			String note) {
//		SQL.SalesInsert(sano, date, eno, sum, cno, account, note);// ��ӽ�����Ϣ
		float sum = 0f;
		try {

			sum = Float.parseFloat(_sum);

//			System.out.println(mno + " " + name + " " + type + " " + specifications + " " + amount + " " + jhPrice + " "
//					+ batchno + " " + lsPrice + " " + validity + " " + note);
			try {
				String SQL = "INSERT INTO Sales(SA_No, SA_Date, E_No, SA_Zje, C_No, SA_Jzr, SA_Bz)" + " VALUES ('" + sano
						+ "','" + date + "','" + eno + "'," + sum + ",'" + cno + "','" + account + "','" + note + "') ";
				 System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "�����ɹ���");
				}
				SalesQuery();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���������");
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}
	}

	// ɾ��������Ϣ
	public static void SalesDelete(String Sano) {
		try {
			String SQL = "DELETE FROM Sales WHERE SA_No = '" + Sano + "'";
			if (!Sql1.executeUpdate(SQL)) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			}
			SalesQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// �������۵���Ϣ
	public static void SalesUpdate(String sano, String date, String eno,  String cno, String account,String _sum,
			String note) {
//		mno, name, type, specifications, amount, jhPrice, batchno, lsPrice, validity, note

		float sum = 0f;

		try {

			sum = Float.parseFloat(_sum);

			try {
				String SQL = "UPDATE Sales SET SA_Date = '" + date + "'," + " E_No = '" + eno + "'," + " C_No = '" + cno
						+ "'," + " SA_Jzr = '" + account + "'," + " SA_Bz = '" + note + "'," +"SA_Zje="+ sum + " WHERE SA_No = '" + sano + "'";
				System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				}
				SalesQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}

	}

	public static void GetSalesSum(MyTable table, JTextField sumText) {
		float sum = 0f, price = 0f;

		try {
			for (int i = 0; i < table.getRowCount(); i++) {
				price = Float.parseFloat((String) table.getValueAt(i, SalesDetailsmodel.findColumn("�ܽ��")));
				sum += price;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		sumText.setText(String.valueOf(sum));
	}

	// ���½����ܽ��
	public static void SalesSumUpdate(float sum, String sano) {

		try {

			try {
//				System.out.println(isbn + type + name + writer + amount + press + price);
				String SQL = "UPDATE Sales SET SA_Zje = " + sum + "WHERE SA_No ='" + sano + "'";

				SalesQuery(); ///////??
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}

	}

	// ��ȡ���충������Զ�������һ���������
	public static String getSDN(String date) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String MaxNumber = "000";
		String NextNumber = "001";

		String SQL = "select max(right(SD_No, 3)) " + "as 'Max' from SalesDetails  where SD_No like '" + date + "%'";// (right(order_id,
		// 3)ѡȡ����λ
		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				MaxNumber = rs.getString("Max").trim();
			}

			int nextnumber = Integer.parseInt(MaxNumber) + 1;
			DecimalFormat df = new DecimalFormat("000");// ��ʽ��ʮ�������� ��ʽΪ��λ��
			NextNumber = df.format(nextnumber);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			NextNumber = date + NextNumber;
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return NextNumber;
	}

	// *************************************������ϸ������***************************************//

	private static DefaultTableModel SalesDetailsmodel = null;

	public static void setSalesDetailsmodel(DefaultTableModel _SalesDetailsmodel) {
		SalesDetailsmodel = _SalesDetailsmodel;
	}

	// ��ʾ���ۼ�¼
	private static void SalesDetailsOutput(String SQL) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (SalesDetailsmodel.getRowCount() > 0) {
				SalesDetailsmodel.removeRow(SalesDetailsmodel.getRowCount() - 1);
			} // ��ձ��

			if (rs != null) {
				while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
					SalesDetailsmodel.addRow(new String[] { "", rs.getString("SD_No"), rs.getString("M_No"), rs.getString("M_Name"),rs.getString("SD_Gg"), rs.getString("SD_Sl"), rs.getString("SD_Dj"), rs.getString("SD_Je"),
							rs.getString("SD_Bz") });
				}
				try {
					OutputNumber(SalesAddMess.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					OutputNumber(SalesAddMess.GetTable());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	// �鿴���۵���ϸ��¼
		private static void CheckSalesDetails(String SQL) {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				con = Sql1.getCoonection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(SQL);

				while (SalesDetailsmodel.getRowCount() > 0) {
					SalesDetailsmodel.removeRow(SalesDetailsmodel.getRowCount() - 1);
				} // ��ձ��

				if (rs != null) {
					while (rs.next()) {// rs.next()��ʹָ��ָ����һ�����ݡ�
						SalesDetailsmodel.addRow(new String[] { "", rs.getString("SD_No"), rs.getString("M_No"), rs.getString("M_Name"),rs.getString("SD_Gg"), rs.getString("SD_Sl"), rs.getString("SD_Dj"), rs.getString("SD_Je"),
								rs.getString("SD_Bz") });
					}
					try {
						OutputNumber(CheckSalesMess.GetTable());
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						OutputNumber(CheckSalesMess.GetTable());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
			} finally {
				try {
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	

	// ��ѯ����������ϸ��¼   !!!!
	public static void SalesDetailsQuery(String Sano) throws SQLException {
		String SQL = "select SD_No,Medicine.M_No,M_Name,SD_Gg,SD_Sl,SD_Dj,SD_Je,SD_Bz\r\n"
				+ "from Medicine,SalesDetails\r\n" + "where Medicine.M_No =SalesDetails.M_No and Sa_No ='" + Sano
				+ "'";
		SalesDetailsOutput(SQL);
		CheckSalesDetails(SQL);

	}

//		static String Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note,Name; 

	// ���ҩƷ������ϸ��¼ Pdno, Pno, Mno, Specifications, Num, Jhprice, Sum, Note
	public static void SalesDetailsInsert(String Sdno, String Sano, String Mno, String Specifications, String _Num,
			String _Lsprice, String _Sum, String Note) {

		int Num = 0;
		float Lsprice = 0f;
		float Sum = 0f;

		try {
			Num = Integer.parseInt(_Num);
			Lsprice = Float.parseFloat(_Lsprice);
			Sum = Float.parseFloat(_Sum);

			try {
				String SQL = "INSERT INTO SalesDetails(SD_No, SA_No, M_No, SD_Sl, SD_Dj, SD_Je, SD_Bz, SD_Gg)"
						+ " VALUES ('" + Sdno + "','" + Sano + "','" + Mno + "'," + Num + "," + Lsprice + "," + Sum
						+ ",'" + Note + "','" + Specifications + "') ";
				System.out.println(SQL);
				if (!Sql1.executeUpdate(SQL)) {
					if (Sdno.length() > 15) {
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ� ������ų���15λ");

					} else {
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");

					}
				} else {
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
				}
				SalesDetailsQuery(Sano);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���������");
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��������Ч��Ϣ��");
		}
	}

	// ɾ��������¼
	public static void SalesDetailsDelete(String Sdno, String Sano) {

		try {
			String SQL = "DELETE FROM SalesDetails WHERE SD_No = '" + Sdno + "'";
			System.out.println(SQL);
			if (!Sql1.executeUpdate(SQL)) {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			}
			SalesDetailsQuery(Sano);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
/*
	public static ArrayList<String> SGetmnoType() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> mnoType = new ArrayList<String>();
		// ArrayListһ��ʵ���࣬����ʵ�������С�Ŀɱ䣬���Ժܷ���Ľ������Ӻ�ɾ��������Ԫ�صĲ���

		mnoType.add("");
		String SQL = "SELECT DISTINCT M_No FROM Medicine ORDER BY M_No ASC";

		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				mnoType.add(rs.getString("M_No").trim());// .trim�õ�һ���µ�String����
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mnoType;
	}

//	// ��ȡҩƷ��������±�
//	public static int GetmnoTypeInedx(ArrayList<String> MedicineSortNameType, String mno) {
//		return MedicineSortNameType.indexOf(mno);
//	}
//
//	// ҩƷ�������ѡ��
//	public static void mnoTypeSelect(String mno, JComboBox typeNameBox) {
//		if (mno == null)
//			typeNameBox.setSelectedIndex(0);
//		else
//			typeNameBox.setSelectedIndex(GetmnoTypeInedx(GetMedicineSortNameType(), mno));
//	}

	// �������б�ѡ���ѯҩƷ������Ϣ
	public static String SAnameTypeSel(String choice) throws SQLException {
		String SQL = null;
		String result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (choice != null) {
			SQL = "SELECT M_Name  FROM Medicine WHERE M_No = '" + choice + "'";
		}
		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				result = rs.getString("M_Name").trim();// .trim�õ�һ���µ�String����
			}
		} catch (Exception e) {

		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// �������б�ѡ���ѯҩƷ�����Ϣ
	public static String SAspecificationsTypeSel(String choice) throws SQLException {
		String SQL = null;
		String result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (choice != null) {
			SQL = "SELECT M_Gg  FROM Medicine WHERE M_No = '" + choice + "'";
		}
		try {
			conn = Sql1.getCoonection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				result = rs.getString("M_Gg").trim();// .trim�õ�һ���µ�String����
			}
		} catch (Exception e) {

		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	*/

	// ���ۺ���ٿ��
	public static void SChangeNum(String mno, String _amount) throws SQLException {
		Connection con;
		Statement stmt;
		ResultSet rs;

		String SQL = "SELECT M_Kcl FROM Medicine WHERE M_No = '" + mno + "'";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			if (rs != null) {
				while (rs.next()) {
					int num = Integer.parseInt(rs.getString("M_Kcl"));
					int amount = Integer.parseInt(_amount);
					num = num - amount;
					SQL = "UPDATE Medicine SET M_Kcl = " + num + " WHERE M_No = '" + mno + "'";
//				System.out.println(SQL);
					Sql1.executeUpdate(SQL);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException eee) {

		}
	}

	// ɾ�����ۼ�¼���ָ�ԭ�����
	public static void SdeleteNum(String mno, String _amount) throws SQLException {
		Connection con;
		Statement stmt;
		ResultSet rs;

		String SQL = "SELECT M_Kcl FROM Medicine WHERE M_No = '" + mno + "'";
		try {
			con = Sql1.getCoonection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			if (rs != null) {
				while (rs.next()) {
					int num = Integer.parseInt(rs.getString("M_Kcl"));

					int amount = Integer.parseInt(_amount);

					num = num + amount;
					SQL = "UPDATE Medicine SET M_Kcl = " + num + " WHERE M_No = '" + mno + "'";
//					System.out.println(SQL);
					Sql1.executeUpdate(SQL);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException eee) {

		}
	}


}
