package register;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ModeC extends JFrame{
	private Container container;
	private JButton command1;
    private JTextField text1;
    private JLabel label1;
    
    public ModeC(){
    	super("����Ա����Ϣ");
    	container=getContentPane();
    	container.setLayout(new FlowLayout());
    	
    	label1=new JLabel("����Ա��ID");
    	container.add(label1);
    	text1=new JTextField(10);
    	container.add(text1);
    	command1=new JButton("�������");
    	command1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String temp=text1.getText().trim();
				if(temp.length()!=4)
				{
					JOptionPane.showConfirmDialog(null,"����Ա��ID����", "������ʾ",JOptionPane.WARNING_MESSAGE);
				}else{
					
					File file =new File("E:\\mydata.txt");
					
					try {
						BufferedReader input=new BufferedReader(new FileReader(file));
						String tempV=new String();
						boolean flag=false;
						while((tempV=input.readLine())!=null){
							if(temp.equals(tempV.substring(0,4)))
							{
								System.out.println(tempV);
								Info info=new Info(tempV);
								flag=true;
							}
						}
							if(flag){;}
							else  JOptionPane.showConfirmDialog(null, "����Ա��IDδע���ID�Ƿ� ","������ʾ",JOptionPane.WARNING_MESSAGE);
							
					} catch (FileNotFoundException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				}
			}
		});
    	container.add(command1);
    	
    	setSize(500,300);
    	setVisible(true);
    }
    
    public static void main(String args[]){
    	ModeC w=new ModeC();
    	w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
}
