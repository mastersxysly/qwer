import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewClass4 extends JFrame{

	private Container con;
	private JTextArea edit;
	private JScrollPane scroll;
	private JLabel label1;
	private JTextField text1;
	private JButton command1;
	private Font  myfont1;
	private int currentindex;
	private JCheckBox check1;
	
	 public NewClass4(){
		super("���Դ���һ");
		myfont1=new Font("����",Font.PLAIN,20);
		currentindex=0;
		con=getContentPane();
		con.setLayout(new FlowLayout());
		edit=new JTextArea(10,45);
		edit.setFont(myfont1);
		scroll=new JScrollPane(edit);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		con.add(scroll);
		
		label1=new JLabel("����");
		label1.setFont(myfont1);
		con.add(label1);
		text1=new JTextField(25);
		text1.setFont(myfont1);
		con.add(text1);
		command1=new JButton("������һ��");
		command1.setFont(myfont1);
		command1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tempS=edit.getText().trim();
				String tempS1=text1.getText().trim();
				
				int index=tempS.indexOf(tempS1,currentindex);
				if(index>=0){
					edit.setSelectionStart(index);
					edit.setSelectionEnd(index+tempS1.length());
					edit.requestFocus();
					currentindex=index+tempS1.length();
				}else{
					JOptionPane.showMessageDialog(null, "���ҽ������������δ�ҵ���");
				}
				}
			
		});
		
		check1=new JCheckBox("�����ִ�Сд");
		check1.setFont(myfont1);
		con.add(command1);
		con.add(check1);
		

		
		setSize(500,400);
		setVisible(true);	
		
		
		
	}
	public static void main(String args[]){
		NewClass4 w=new NewClass4();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}   
}
