package com.java1996.cn;

import java.util.Scanner;

public class People {
	private int location;//����λ��
	private int Goto; //ǰ���ڼ���
	private boolean condition;//����¥���
	private int people_count;//������ 
	private int quality;//������
	public void set_location(int loc){
		location = loc;
	}
	public void set_Goto(int Go){
		Goto = Go;
	}
	public int get_location(){
		return location;
	}
	public int get_Goto(){
		return Goto;
	}
	public boolean get_condition(){
		return condition;
	} 
	public void peopleset(People people){
		Scanner scanner = new Scanner(System.in);
		/*String loc = scanner.nextLine();
		try{
			people.location = Integer.parseInt(loc);
		}catch (Exception e) {
			System.out.println("help");
		}finally {
		}*/
		System.out.println("������˿͵�ǰ����¥�㣺");
		people.location = scanner.nextInt();
		System.out.println("������˿�Ҫǰ����Ŀ��¥�㣺");
		people.Goto = scanner.nextInt();
		if(people.get_Goto()-people.get_location()>=0)
			people.condition = true;
		else
			people.condition = false;
		
		System.out.println("�˿��ڵ�"+people.get_location()+"��¥�ȵ���");
		System.out.println("�˿�Ҫǰ����"+people.get_Goto()+"��¥");
	}
	
}
