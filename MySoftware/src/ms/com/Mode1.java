package ms.com;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Mode1 extends JFrame implements ActionListener{
	private AddTable window1=null;
	private DeleteTable window2=null;
	private Search window4=null;
	private JButton command1,command2,command3,command4;
	private Container con;
	
	public Mode1(){
		super("Ա����Ϣ����");
		con=getContentPane();
		con.setLayout(new FlowLayout());
		
		window1=new AddTable();
		window2=new DeleteTable();
		
		command1=new JButton("�����Ա����Ϣ");
		command1.addActionListener(this);
		command2=new JButton("ɾ��Ա����Ϣ");
		command2.addActionListener(this);
		command3=new JButton("�޸�Ա����Ϣ");
		command3.addActionListener(this);
		command4=new JButton("����Ա����Ϣ");
		command4.addActionListener(this);
		con.add(command1);
		con.add(command2);
		con.add(command3);
		con.add(command4);

		setSize(500,300);
		setVisible(true);
	}
	public static void main(String[] args){
		Mode1 w=new Mode1();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		Object s=e.getSource();
		if(s==command1)
			window1.setVisible(true);
		else if(s==command2)
			window2.setVisible(true);
		else if(s==command3){
			window4=new Search(0);
			window4.setVisible(true);
		}
		else if(s==command4){
			window4=new Search(1);
			window4.setVisible(true);
		}
		
	}
}
