package xx.com;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyClient1 extends  JFrame{

	private JButton command1;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket client;
	private JTextField text1=null;
	
	public MyClient1(){
		super("�Զ���ͻ�һ");
		Container con=getContentPane();
		command1=new JButton("���ӷ�����");
		command1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					client=new Socket(InetAddress.getByName("127.0.0.1"), 12553);  //��������IP��ַ���˿ںţ��˿ں��ǳ�����ڣ�
					input=new ObjectInputStream(client.getInputStream());//�׽���
					try {
						text1.setText(String.valueOf(input.readObject()));
						if(text1.getText().trim()!=null)
							JOptionPane.showConfirmDialog(null, "���ӷ������ɹ�","���ӷ�������...",JOptionPane.OK_OPTION);
						else
							JOptionPane.showConfirmDialog(null, "���ӷ�����ʧ��","���ӷ�������...",JOptionPane.WARNING_MESSAGE);
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		con.setLayout(new FlowLayout());
		con.add(command1);
		text1=new JTextField(30);
		con.add(text1);
		setSize(500,300);
		setVisible(true);
	}
	public  static void main(String[] args){
		MyClient1 w=new MyClient1();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
