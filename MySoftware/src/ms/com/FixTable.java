package ms.com;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FixTable extends JFrame implements ActionListener{
	private Container con;
	private JLabel labelId,labelName,labelSex,labelDay,labelHobby,labelNum,labelNote;
	private JTextField textId,textName,textNum;
	private JRadioButton rbBoy,rbGirl;
	private ButtonGroup chSex;
	private JComboBox chY,chM,chD;
	private JTextArea textNote;
	private JPanel p1,p2,p3;
	private JCheckBox hb1,hb2,hb3,hb4,hb5;
	private JButton btSave;
	private String tt="";
	private String[] a={"1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003"};
	private String[] m={"1","2","3","4","5","6","7","8","9","10","11","12"};
	private String[] dd={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
			"18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String[] xd={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
			"18","19","20","21","22","23","24","25","26","27","28","29","30"};
	private String[] r2d={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
			"18","19","20","21","22","23","24","25","26","27","28","29"};
	private String[] p2d={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
			"18","19","20","21","22","23","24","25","26","27","28"};
	private File f=new File("d:/data.txt");//��ָ���ı�����д��
	
	
	public FixTable(String str){
		super("���Ա����Ϣ");
		p1=new JPanel();
		p1.setBackground(Color.lightGray);
		p1.setPreferredSize(new Dimension(60,400));
		p2=new JPanel();
		p2.setPreferredSize(new Dimension(190,400));
		p3=new JPanel();
		p3.setLayout(new FlowLayout());
		p3.setPreferredSize(new Dimension(255,60));
		
		con=getContentPane();
		con.setLayout(new FlowLayout());
		GridLayout gly=new GridLayout(7,1);
		p1.setLayout(gly);
		p2.setLayout(gly);
		con.add(p1);
		con.add(p2);
		con.add(p3);
		
		labelId=new JLabel("id");
		p1.add(labelId);
		
		textId=new JTextField(17);
		p2.add(textId);
		textId.setText(String.valueOf((int)(Math.random()*10000)));
		
		labelName=new JLabel("����");
		p1.add(labelName);
		
		textName=new JTextField(17);
		textName.setText("");
		p2.add(textName);
		
		labelSex=new JLabel("�Ա�");
		p1.add(labelSex);
		
		rbBoy=new JRadioButton("��");
		rbGirl=new JRadioButton("Ů");
		chSex=new ButtonGroup();
		chSex.add(rbBoy);
		chSex.add(rbGirl);
		JPanel pa=new JPanel();
		pa.setLayout(new FlowLayout());
		p2.add(pa);
		pa.add(rbBoy);
		pa.add(rbGirl);
		
		labelDay=new JLabel("��������");
		p1.add(labelDay);
		JPanel pb=new JPanel();
		pb.setLayout(new FlowLayout());
		p2.add(pb);
		
		chY=new JComboBox<>(a);
		pb.add(chY);
		chM=new JComboBox<>(m);
		pb.add(chM);
		chD=new JComboBox<>(dd);
		chD.addActionListener(this);
		pb.add(chD);
		
		labelHobby=new JLabel("����");
		p1.add(labelHobby);
		
		hb1=new JCheckBox("����");
		hb2=new JCheckBox("����");
		hb3=new JCheckBox("��ë��");
		hb4=new JCheckBox("��Ӿ");
		hb5=new JCheckBox("�羺");
		JPanel pc=new JPanel();
		pc.setLayout(new FlowLayout());
		p2.add(pc);
		pc.add(hb1);
		pc.add(hb2);
		pc.add(hb3);
		pc.add(hb4);
		pc.add(hb5);
		
		labelNum=new JLabel("�绰");
		p1.add(labelNum);
		
		textNum=new JTextField();
		textNum.setText("");
		p2.add(textNum);
		
		labelNote=new JLabel("��ע");
		p1.add(labelNote);
		
		textNote=new JTextArea();
		textNote.setText("");
		p2.add(textNote);
		
		btSave=new JButton("����");
		btSave.addActionListener(this);
		p3.add(btSave);
		
		info(str);
		
		setSize(280,520);
//		setVisible(true);
	}
	public void del(){
		File file =new File("d:\\data.txt");
		String temp;
		tt=getId()+getName()+getSex()+getDay()+getHobby()+getNum()+getNote();
		String[] a=tt.split(" ");
		temp=a[0];
		try {
			BufferedReader input=new BufferedReader(new FileReader(file.getAbsolutePath()));
			
			String tempV=null;
			ArrayList list =new ArrayList();
			int flag=0;
			while((tempV=input.readLine())!=null){
				a=tempV.split(" ");
				System.out.println(a[0]);
				if(temp.equals(a[0])){
//					System.out.println(tempV);
					flag=1;
					list.add(tt);
				}
				else{
					list.add(tempV);
				}
				
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			for( int i=0;i<list.size();i++ ){
			    // System.out.println("list["+i+"]"+list.get(i));
			     output.write(list.get(i).toString());
			     output.newLine();
			    }
			    output.flush();
			    output.close();
			    
				if(flag==1){JOptionPane.showConfirmDialog(null,"��Ա����Ϣ���޸�", "������ʾ",JOptionPane.OK_OPTION);}
				else  JOptionPane.showConfirmDialog(null, "����Ա��IDδע����ID�Ƿ� ","������ʾ",JOptionPane.WARNING_MESSAGE);
				
		} catch (FileNotFoundException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		Object source=e.getSource();
		if(source==btSave){
			del();
			JOptionPane.showMessageDialog(null,"�����ѱ���","���",JOptionPane.DEFAULT_OPTION);
		}
		
//		else if(source==chD){
//			int y=Integer.parseInt(a[chY.getSelectedIndex()]);
//			if((y%4==0&&y%100!=0)||y%400==0){
//				int x=Integer.parseInt(m[chM.getSelectedIndex()]);
//				if(x==2){
//					chD.removeAllItems();
//					chD.addItem(r2d);
//				}
//				else if(x==1||x==3||x==5||x==7||x==8||x==10||x==12){
//					chD.removeAllItems();
//					chD.addItem(dd);
//				}
//				else{
//					chD.removeAllItems();
//					chD.addItem(xd);
//				}
//			}
//			else{
//				int x=Integer.parseInt(m[chM.getSelectedIndex()]);
//				if(x==2){
//					chD.removeAllItems();
//					chD.addItem(r2d);
//				}
//				else if(x==1||x==3||x==5||x==7||x==8||x==10||x==12){
//					chD.removeAllItems();
//					chD.addItem(dd);
//				}
//				else{
//					chD.removeAllItems();
//					chD.addItem(xd);
//				}
//			}
//		}
	}
	
	public String getId(){
		return textId.getText()+" ";
	}
	public String getName(){
		return textName.getText()+" ";
	}
	public String getSex(){
		if(rbBoy.isSelected())
			return "Boy ";
		else if(rbGirl.isSelected())
			return "Girl ";
		else
			return "null ";
	}
	public String getDay(){
		String str="";
		str=chY.getSelectedItem().toString()+"-"+chM.getSelectedItem().toString()+"-"+chD.getSelectedItem().toString()+" ";
		return str;
	}
	public String getHobby(){
		String str="";
		if(hb1.isSelected())
			str+=hb1.getText();
		if(hb2.isSelected()){
			if(str.length()>0)
				str+=","+hb2.getText();
			else if(str.length()==0)
				str+=hb2.getText();
		}
		if(hb3.isSelected()){
			if(str.length()>0)
			str+=","+hb3.getText();
			else if(str.length()==0)
				str+=hb3.getText();
		}
		if(hb4.isSelected()){
			if(str.length()>0)
			str+=","+hb4.getText();
			else if(str.length()==0)
				str+=hb4.getText();
		}
		if(hb5.isSelected()){
			if(str.length()>0)
			str+=","+hb5.getText();
			else if(str.length()==0)
				str+=hb5.getText();
		}
		return str+" ";
	}
	public String getNum(){
		if(textNum.getText().length()>0)
			return textNum.getText()+" ";
		else 
			return "null ";
	}
	public String getNote(){
		return textNote.getText().toString();
	}
	
	public void info(String str){
		String[] a=str.split(" ");
		textId.setText(a[0]);
		textName.setText(a[1]);
		if(a[2].equals("Boy"))
			rbBoy.setSelected(true);
		else if(a[2].equals("Girl"))
			rbGirl.setSelected(true);
		String[] d=a[3].split("-");
		for(int i=0;i<a.length;i++){
			if(a[i].equals(d[0])){
				chY.setSelectedIndex(i);
				break;
			}
		}
		for(int i=0;i<m.length;i++){
			if(m[i].equals(d[1])){
				chM.setSelectedIndex(i);
				break;
			}
		}
		for(int i=0;i<dd.length;i++){
			if(dd[i].equals(d[2])){
				chD.setSelectedIndex(i);
				break;
			}
		}
		
		String[] e=a[4].split(",");
		for(int i=0;i<e.length;i++){
			if(e[i].equals("����"))
				hb1.setSelected(true);
			else if(e[i].equals("����"))
				hb2.setSelected(true);
			else if(e[i].equals("��ë��"))
				hb3.setSelected(true);
			else if(e[i].equals("��Ӿ"))
				hb4.setSelected(true);
			else if(e[i].equals("�羺"))
				hb5.setSelected(true);
		}
		textNum.setText(a[5]);
		textNote.setText(a[6]);
	}
	
	public void fun() {
		
		write(getId());
		if(getName().equals(" ")){
			JOptionPane.showMessageDialog(null,"����������~","����",JOptionPane.ERROR_MESSAGE);
		}
		else{ 
			write(getName());
			if(getSex().compareTo("null ")==0)
				JOptionPane.showMessageDialog(null,"�������Ա�~","����",JOptionPane.ERROR_MESSAGE);
		    else{ 
		    	write(getSex());
				write(getDay());
				write(getHobby());
				if(getNum().compareTo("null ")==0)
					JOptionPane.showMessageDialog(null,"������绰����~","����",JOptionPane.ERROR_MESSAGE);
				else{
					write(getNum());
					write(getNote());
				}
		    }
		}
	}
		public void write(String line){
			  try{
				 FileWriter fw=new FileWriter(f,true);
				 fw.write(line);
			   fw.close();
			  }catch(Exception e){
			 
			  }
			 }
	
//	public static void main(String[] args) {
//		AddTable a=new AddTable();
//		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}
