package com.map;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import query.Skyline;

public class mainJFrame extends JFrame{
	private MapBoard mapBoard;
	private Panel toolBar;
	private Button inputdataButton;
	private Button topkButton;
	private Button skylineButton;
	private	Button clearButton;
	private JTextField querydata;
	private JTextArea expresion;
	private JTextArea result;
	private JScrollPane scroll;
	
	public mainJFrame() {
		setTitle("�������������ͼ");
		MyItemListener lis=new MyItemListener();
		result=new JTextArea(30,50);
		mapBoard=new MapBoard(result);
		toolBar=new Panel();
		inputdataButton=new Button("input");
		inputdataButton.addActionListener(lis);//
		inputdataButton.setBackground(Color.WHITE);
		topkButton=new Button("topk_Points");
		topkButton.setBackground(Color.ORANGE);
		topkButton.addActionListener(lis);//
		skylineButton=new Button("skyline_Points");
		skylineButton.setBackground(Color.RED);
		skylineButton.addActionListener(lis);//
		clearButton=new Button("clear");
		clearButton.addActionListener(lis);//
		expresion=new JTextArea("Usage-Expresion:"+"\n\n"
				+ "The white coordinate point represents the merchant location,"+"\n"
				+ "the black coordinate point represents the query coordinate point, "+"\n"
				+ "the orange coordinate point represents the result point obtained by topk algorithm, "+"\n"
				+ "the red coordinate point represents the result point obtained by skyline algorithm",10,50);
		expresion.setBackground(Color.LIGHT_GRAY);
		expresion.setEditable(false);
		scroll=new JScrollPane(result); 
		toolBar.setPreferredSize(new Dimension(600, 800));//ǿ�ƹ̶����λ�ô�С
		toolBar.add(expresion);
		toolBar.add(inputdataButton);
		toolBar.add(topkButton);
		toolBar.add(skylineButton);
		toolBar.add(clearButton);
		toolBar.add(scroll);
		add(toolBar,BorderLayout.EAST);//��������岼�ֵ����涫��Ҳ�����ұ�
		add(mapBoard);//����������ӵ������� 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ý���ر��¼� 
		pack(); //����Ӧ��С 
	}
	private class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		if(e.getSource()==inputdataButton) {
			try {
				mapBoard.paintPoints();
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("input�¼��ѵ��");
		}else if(e.getSource()==topkButton) {
			mapBoard.painttopkpoints();
		}else if(e.getSource()==skylineButton) {
			mapBoard.paintskylinepoints();
		}else if(e.getSource()==clearButton) {
			mapBoard.clearPoints();
		}
	}
	}
	public static void main(String[] args){
		mainJFrame map=new mainJFrame();
		map.setSize(1360, 800);
		map.setResizable(false);
		map.setVisible(true);
	}
}
