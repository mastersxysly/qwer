package com.map;

import java.awt.Color;

public class Point {
	private int id;//Ŀ���id
	private int x;//Ŀ����������е�x����ֵ 
	private int y;//Ŀ����������е�y����ֵ 
	private Color color;//��ɫ 
	private String word;
	private String messager;
	public static int DIAMETER=30;//ֱ�� 
	public Point(int id,int x,int y,Color color){ 
		this.id=id;
		this.x=x; 
		this.y=y; 
		this.color=color; 
		this.messager ="PointID="+id+"  ������x="+x+"  ������y="+y;
	} 
	 //�õ�Ŀ����������е�x����ֵ 
	public int getX(){ 
	  return x; 
	} 
	//�õ�Ŀ����������е�y����ֵ 
	public int getY(){ 
	  return y; 
	} 
	 //�õ�Ŀ���ı�ע��ɫ 
	public Color getColor(){ 
	  return color; 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public static int getDIAMETER() {
		return DIAMETER;
	}
	public static void setDIAMETER(int dIAMETER) {
		DIAMETER = dIAMETER;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getMessager() {
		return messager;
	}
	public void setMessager() {
		this.messager=this.messager+"  �ı�Id��Ȩ��:"+word;
	}  
	
	
}
