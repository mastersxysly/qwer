import javax.swing.JOptionPane;

public class Class01 {
	public static void main(String[] args){
		
	String number1=JOptionPane.showInputDialog("������һ����");//����

			int n1=Integer.parseInt(number1);

			float n2=Float.parseFloat(number1);
			
			int b =Integer.parseInt(JOptionPane.showInputDialog("��������һ����"));//����

			JOptionPane.showMessageDialog(null, "Hello");//���

			JOptionPane.showMessageDialog( null, "Hello","�ҵ���Ϣ",JOptionPane.ERROR_MESSAGE);//���
}
}