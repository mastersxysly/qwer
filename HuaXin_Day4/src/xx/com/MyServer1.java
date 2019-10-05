package xx.com;

import java.awt.Container;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.omg.PortableInterceptor.IORInterceptor_3_0Holder;

public class MyServer1 extends JFrame{

	private JTextArea edit1;
	private JScrollPane scroll1;
	private int count;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private ServerSocket server;
	private Socket connection;
	private StringBuffer dispS=new StringBuffer();
	public MyServer1(){
		super("�Զ��������һ");
		Container con =getContentPane();
		edit1=new JTextArea();
		scroll1=new JScrollPane(edit1);
		Font myfont=new Font("����", Font.PLAIN, 20);
		edit1.setFont(myfont);
		edit1.setEditable(false);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		con.add(scroll1);
		setSize(500,600);
		setVisible(true);
	}
	public void runServer(){
		try {
			server=new ServerSocket(12553,100);//�������˿ں� 0-65535�����鲻ʹ��1024���µĶ˿ںţ�����ϵͳʹ�� ����������������
			while(true){
				waitForConnection();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void waitForConnection() throws IOException{
		dispS.append("���ڵȴ��û�������...\n");
		edit1.setText(dispS.toString());//��ʾ״̬�����п���
		connection=server.accept();//���� һ��������䣬�ȴ��ͻ����뱾��������������
		output=new ObjectOutputStream(connection.getOutputStream());//���������
		output.writeObject("Welcome here!");//д���� 
		output.flush();
		dispS.append("�����û���������\n");
		edit1.setText(dispS.toString());
		
	}

	public static void main(String[] args){
		MyServer1 w=new MyServer1();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.runServer();
		
	}
	
}
