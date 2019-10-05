
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyWindow1 extends JFrame{
	private JLabel label1,label2,lable3,lable4;
	private JButton command1,command2,command3;
	private JTextField text1,text2,text3,text4;
	private Container container;
	
	public MyWindow1(){
		super("���Դ���");
		container=getContentPane();
		container.setLayout(new FlowLayout());
		
		label1=new JLabel("������");
		container.add(label1);
		text1=new JTextField(10);
		container.add(text1);
		
		label2=new JLabel("������");
		container.add(label2);
		text2=new JTextField(9);
		container.add(text2);
		
		lable3=new JLabel("������");
		container.add(lable3);
		text3=new JTextField(9);
		container.add(text3);
		
		lable4=new JLabel("��ǰ��ʾ");
		container.add(lable4);
		text4=new JTextField(35);
		container.add(text4);
		
		
		
		command1=new JButton("����");
		command1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		container.add(command1);
		
		command2=new JButton("����");
		command2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n;
				if(text1.getText().matches("[0-9]*{0,4}"))
					n=Integer.parseInt(text1.getText());
					
			}
		});
		container.add(command2);
		
		command3=new JButton("����");
		command3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				double n1,n2,result;
				if(text1.getText().matches("[0-9]*\\.?[0-9]*")&&text2.getText().matches("[0-9]*\\.?[0-9]*")){
					n1=Double.parseDouble(text1.getText());
					n2=Double.parseDouble(text1.getText());
					result=n1+n2;
					text3.setText(result+"");
				}
				else{
					JOptionPane.showMessageDialog(null, "���������������");
				}
			}
		});
		container.add(command3);
		
		
		setSize(500,300);
		setVisible(true);
	}
	
	
	public static void main(String args[]){
		MyWindow1 w=new MyWindow1();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
