package yaopin;

public class NullPointerExceptionTest {
	 
    /**
     * ʵ��˼·��
     * 1.��ָ���쳣�ı���֮һ���Կյ��ַ��������˲���
     * 2.����һ���յ��ַ�����Ȼ�������в��Ҳ���������ʹ��charAt()������
     * 3.�Խ�Ҫ���ֵ��쳣���в���
     * 4.��ӡ������̨
     * */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
         
        //����һ���յ��ַ���
        String str = null;
         
        //���ԶԿ��ַ������в������������쳣������쳣
        try{
            System.out.println(str.charAt(0));
        }catch(Exception e){
            System.out.println("�쳣��Ϣ:");
            e.printStackTrace();            
        }
    }
 
}