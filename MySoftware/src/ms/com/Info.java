package ms.com;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Info extends JFrame{
	private Container container;
	private JTextField text1,text2,text3,text4,text5,text6,text7;
	private JLabel label1,label2,label3,label4,label5,label6,label7;
	
	public Info(String T){
		super("Ա����Ϣ");
		container=getContentPane();
		container.setLayout(new FlowLayout());
		//T="1234 xxxx xxx xxx xxx xxx xxx";
		String[] s=T.split(" ");  
		
		label1=new JLabel("Ա��ID");
		container.add(label1);
		text1=new JTextField(39);
		text1.setText(s[0]);
		container.add(text1);
		
		label2=new JLabel("����");
		container.add(label2);
		text2=new JTextField(40);
		text2.setText(s[1]);
		container.add(text2);
		
		label3=new JLabel("�Ա�");
		container.add(label3);
		text3=new JTextField(40);
		text3.setText(s[2]);
		container.add(text3);
		
		label4=new JLabel("��������");
		container.add(label4);
		text4=new JTextField(38);
		text4.setText(s[3]);
		container.add(text4);
		
		label5=new JLabel("����");
		container.add(label5);
		text5=new JTextField(40);
		text5.setText(s[4]);
		container.add(text5);
		

		label6=new JLabel("�绰");
		container.add(label6);
		text6=new JTextField(40);
		text6.setText(s[5]);
		container.add(text6);
		

		label7=new JLabel("��ע");
		container.add(label7);
		text7=new JTextField(40);
		text7.setText(s[6]);
		container.add(text7);
		
		setSize(500,400);
//		setVisible(true);	
		
		
		
	}
//	public static void main(String args[]){
//		ModeC w=new ModeC();
//		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//	}   
}

