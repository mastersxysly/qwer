package com.java1996.cn;

import java.util.Scanner;

import javax.lang.model.util.ElementKindVisitor6;

public class Elevator {
	private int condition;//����״̬
	private int ele_num;//���ݺ���
	private int currentpose;//���ݵ�ǰλ��
	private int weight_limit;//��������
	private int number_limit;//��������
 
	
	//����ֵ
	public int get_conditionvalue(){
		return condition;
	}
    public  String get_Condition(){
    	if(condition == -1) return "DOWN";
    	if(condition == 0) return "STOP";
    	if(condition == 1) return "UP";
		return null;
    }
	public int get_ele_num(){
		return ele_num;
	}
	public int get_currentpose(){
		return currentpose;
	}
	

	//����ֵ
	public void set_condition(int con){
		condition=con;
	}
	public void set_ele_num(int num){
		ele_num=num;
	}
	public void set_currentpose(int pose){
		currentpose=pose;
	}

	public  void elevatorset1( Elevator elevator){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("���õ��ݵ�ǰλ��:");
		elevator.currentpose = scanner.nextInt();
		System.out.println("���õ��ݵ�ǰ״̬��");
		elevator.condition = scanner.nextInt();
		
		System.out.println("��ǰ����");
		System.out.println("��ǰ���ڵ�"+elevator.get_currentpose()+"��¥");
		System.out.println("��������״̬Ϊ"+elevator.get_Condition()+"\n");
	}
	
}
