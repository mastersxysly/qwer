package com.java1996.cn;

import java.awt.geom.FlatteningPathIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.print.attribute.standard.PrinterLocation;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Test {
	public static void main(String[] arg){
		Elevator elevator1 = new Elevator();
		System.out.println("������elevator1��");
		elevator1.elevatorset1(elevator1);
		
		Elevator elevator2 = new Elevator();
		System.out.println("������elevator2��");
		elevator2.elevatorset1(elevator2);
		
		Elevator elevator3 = new Elevator();
		System.out.println("������elevator3��");
		elevator3.elevatorset1(elevator3);
		
		Elevator  elevator4 = new Elevator();
		System.out.println("������elevator4��");
		elevator4.elevatorset1(elevator4);
		
		/*elevator1.set_ele_num(1);
		elevator1.set_currentpose(20);
		elevator1.set_condition(0);
		System.out.println("��"+elevator1.get_ele_num()+"�ŵ���");
		System.out.println("��ǰ���ڵ�"+elevator1.get_currentpose()+"��¥");
		System.out.println("��������״̬Ϊ"+elevator1.get_Condition()+"\n");


		Elevator elevator2 = new Elevator();
		elevator2.set_ele_num(2);
		elevator2.set_currentpose(5);
		elevator2.set_condition(0);
		System.out.println("��"+elevator2.get_ele_num()+"�ŵ���");
		System.out.println("��ǰ���ڵ�"+elevator2.get_currentpose()+"��¥");
		System.out.println("��������״̬Ϊ"+elevator2.get_Condition()+"\n");

		
		Elevator elevator3 = new Elevator();
		elevator3.set_ele_num(3);
		elevator3.set_currentpose(6);
		elevator3.set_condition(0);
		System.out.println("��"+elevator3.get_ele_num()+"�ŵ���");
		System.out.println("��ǰ���ڵ�"+elevator3.get_currentpose()+"��¥");
		System.out.println("��������״̬Ϊ"+elevator3.get_Condition()+"\n");
	
		Elevator elevator4 = new Elevator();
		elevator4.set_ele_num(4);
		elevator4.set_currentpose(12);
		elevator4.set_condition(0);
		System.out.println("��"+elevator4.get_ele_num()+"�ŵ���");
		System.out.println("��ǰ���ڵ�"+elevator4.get_currentpose()+"��¥");
		System.out.println("��������״̬Ϊ"+elevator4.get_Condition()+"\n");
	*/

		
		People people = new People();
		people.peopleset(people);
		//people.set_location(19);
		//people.set_Goto(6);
		/*
		boolean temp;//�˿�����¥���
		if(people.get_Goto()-people.get_location()>=0)
			temp = true;
		else
			temp = false;
		*/
		//System.out.println("�˿��ڵ�"+people.get_location()+"��¥�ȵ���");
		//System.out.println("�˿�Ҫǰ����"+people.get_Goto()+"��¥");
		
		boolean flag1; //�˿�����¥����ż���
		
		if(people.get_location()%2==1){
			flag1 = true;
			System.out.println("\n����¥�㴦�������㣬����3���ܵ���");
		}else{
			flag1 = false;
			System.out.println("\n����¥�㴦��ż ���㣬����2���ܵ���");
		}
		boolean flag2; //Ŀ�ĵ�¥����ż���
		if(people.get_Goto()%2==1){
			flag2 = true;
			System.out.println("Ŀ�ĵ�¥��Ϊ�����㣬����3���ܵ���\n");
		}else{
			flag2 = false;
			System.out.println("Ŀ�ĵ�¥��Ϊż���㣬����2���ܵ���\n");
		}
		//�����鸳��ֵ�����ڴ�ų˿�����ݵľ���
		int[] a = new int[5];//ͬ�����ֹͣ�ĵ���
		for(int i=0;i<5;i++)
			a[i]=50;
		int[] b = new int[5];//���������
		for(int i=0;i<5;i++)
			b[i]=50;
			

		if(flag1 && flag2 ){
			if(people.get_condition()){
				if(elevator1.get_currentpose() <= people.get_location()&&(elevator1.get_conditionvalue()==1||elevator1.get_conditionvalue()==0))
				{
					a[1]=people.get_location()-elevator1.get_currentpose();
				}else{
					b[1]=elevator1.get_currentpose()-people.get_location();
				}
				/////
				if(elevator2.get_currentpose()<=people.get_location()&&(elevator2.get_conditionvalue()==1||elevator2.get_conditionvalue()==0))
				{
					a[2]=people.get_location()-elevator2.get_currentpose();
				}else{
					b[2]=elevator2.get_currentpose()-people.get_location();
				}
				////
				if(elevator4.get_currentpose()<=people.get_location()&&(elevator4.get_conditionvalue()==1||elevator4.get_conditionvalue()==0))
				{
					a[4]=people.get_location()-elevator4.get_currentpose();
				}else{
					b[4]=elevator4.get_currentpose()-people.get_location();
				}
				////
				Map<String, Integer>map = new HashMap<String,Integer>();
				int k1 = Math.min(Math.min(a[1],a[2]),Math.min(a[3], a[4]));
				int	k2 = Math.min(Math.min(b[1],b[2]),Math.min(b[3], b[4]));

				if(k1<50)
				{
					map.put("elevator1",a[1]);
					map.put("elevator2",a[2]);
					map.put("elevator4",a[4]);
					int value=k1;
					String key="";  
					for (Map.Entry<String, Integer> entry : map.entrySet()) {  
						if(value==entry.getValue()){  
							key=entry.getKey();  
						}  
					}  
					System.out.println(key+"��������");	
				}//endif
				else{
					map.put("elevator1",b[1]);
					map.put("elevator2",b[2]);
					map.put("elevator4",b[4]);
					int value=k2;
					String key="";  
					for (Map.Entry<String, Integer> entry : map.entrySet()) {  
						if(value==entry.getValue()){  
							key=entry.getKey();  
						}  
					}   
					System.out.println(key+"��������");	
				}
			}else{
				if(elevator1.get_currentpose() >= people.get_location()&&(elevator1.get_conditionvalue()==-1||elevator1.get_conditionvalue()==0))
				{
					a[1]=elevator1.get_currentpose()-people.get_location();
				}else{
					b[1]=people.get_location()-elevator1.get_currentpose();
				}	   
				/////
				if(elevator2.get_currentpose()>=people.get_location()&&(elevator2.get_conditionvalue()==-1||elevator2.get_conditionvalue()==0))
				{
					a[2]=elevator2.get_currentpose()-people.get_location();
				}else{
					b[2]=people.get_location()-elevator2.get_currentpose();
				}
				////
				if(elevator4.get_currentpose()>=people.get_location()&&(elevator4.get_conditionvalue()==-1||elevator4.get_conditionvalue()==0))
				{
					a[4]=elevator4.get_currentpose()-people.get_location();
				}else{
					b[4]=people.get_location()-elevator4.get_currentpose();
				}
				////
				Map<String, Integer>map = new HashMap<String,Integer>();
				int k1 = Math.min(Math.min(a[1],a[2]),Math.min(a[3], a[4]));
				int	k2 = Math.min(Math.min(b[1],b[2]),Math.min(b[3], b[4]));

				if(k1<50)
				{
					map.put("elevator1",a[1]);
					map.put("elevator2",a[2]);
					map.put("elevator4",a[4]);
					int value=k1;
					String key="";  
					for (Map.Entry<String, Integer> entry : map.entrySet()) {  
						if(value==entry.getValue()){  
							key=entry.getKey();  
						}  
					}  
	     
					System.out.println(key+"��������");	
				}
				else{
					map.put("elevator1",b[1]);
					map.put("elevator2",b[2]);
					map.put("elevator4",b[4]);
					int value=k2;
					String key="";  
					for (Map.Entry<String, Integer> entry : map.entrySet()) {  
						if(value==entry.getValue()){  
							key=entry.getKey();  
						}  
					}   
					System.out.println(key+"��������");	
		  	    	}    		
			}//endelse
			}
			/////////////////////////////////////////////////////////////////////////////////
		else if(!flag1 && !flag2){
		
			if(people.get_condition()){
				if(elevator1.get_currentpose() <= people.get_location()&&(elevator1.get_conditionvalue()==1||elevator1.get_conditionvalue()==0))
				{
					a[1]=people.get_location()-elevator1.get_currentpose();
				}else{
					b[1]=elevator1.get_currentpose()-people.get_location();
				}
				/////
				if(elevator3.get_currentpose()<=people.get_location()&&(elevator3.get_conditionvalue()==1||elevator3.get_conditionvalue()==0))
				{
					a[3]=people.get_location()-elevator3.get_currentpose();
				}else{
					b[3]=elevator3.get_currentpose()-people.get_location();
				}
				/////
	
				if(elevator4.get_currentpose()<=people.get_location()&&(elevator4.get_conditionvalue()==1||elevator4.get_conditionvalue()==0))
				{
					a[4]=people.get_location()-elevator4.get_currentpose();
				}else{
					b[4]=elevator4.get_currentpose()-people.get_location();
				}
				////

	
				Map<String, Integer>map = new HashMap<String,Integer>();
				int k1 = Math.min(Math.min(a[1],a[2]),Math.min(a[3], a[4]));
				int	k2 = Math.min(Math.min(b[1],b[2]),Math.min(b[3], b[4]));

				if(k1<50)
				{
					map.put("elevator1",a[1]);
					map.put("elevator3",a[3]);
					map.put("elevator4",a[4]);
					int value=k1;
					String key="";  
					for (Map.Entry<String, Integer> entry : map.entrySet()) {  
						if(value==entry.getValue()){  
							key=entry.getKey();  
						}  
					}  
 
					System.out.println(key+"��������");	
				}//endif
				else{
					map.put("elevator1",b[1]);
					map.put("elevator3",b[3]);
					map.put("elevator4",b[4]);
					int value=k2;
					String key="";  
					for (Map.Entry<String, Integer> entry : map.entrySet()) {  
						if(value==entry.getValue()){  
							key=entry.getKey();  
						}  
					}   
					System.out.println(key+"��������");	
				}
			}else{
				if(elevator1.get_currentpose() >= people.get_location()&&(elevator1.get_conditionvalue()==-1||elevator1.get_conditionvalue()==0))
				{
					a[1]=elevator1.get_currentpose()-people.get_location();
				}else{
					b[1]=people.get_location()-elevator1.get_currentpose();
				}
	   
				///
				if(elevator3.get_currentpose()>=people.get_location()&&(elevator3.get_conditionvalue()==-1||elevator3.get_conditionvalue()==0))
				{
					a[3]=elevator3.get_currentpose()-people.get_location();
				}else{
					b[3]=people.get_location()-elevator3.get_currentpose();
				}
				/////
		
				if(elevator4.get_currentpose()>=people.get_location()&&(elevator4.get_conditionvalue()==-1||elevator4.get_conditionvalue()==0))
				{
					a[4]=elevator4.get_currentpose()-people.get_location();
				}else{
					b[4]=people.get_location()-elevator4.get_currentpose();
				}
				////
	
				Map<String, Integer>map = new HashMap<String,Integer>();
				int k1 = Math.min(Math.min(a[1],a[2]),Math.min(a[3], a[4]));
				int	k2 = Math.min(Math.min(b[1],b[2]),Math.min(b[3], b[4]));

				if(k1<50)
				{
					map.put("elevator1",a[1]);
					map.put("elevator3",a[3]);
					map.put("elevator4",a[4]);
					int value=k1;
					String key="";  
					for (Map.Entry<String, Integer> entry : map.entrySet()) {  
						if(value==entry.getValue()){  
							key=entry.getKey();  
						}  
					}  
     
					System.out.println(key+"��������");	
				}
				else{
					map.put("elevator1",b[1]);
					map.put("elevator3",b[3]);
					map.put("elevator4",b[4]);
					int value=k2;
					String key="";  
					for (Map.Entry<String, Integer> entry : map.entrySet()) {  
						if(value==entry.getValue()){  
							key=entry.getKey();  
						}  
					}   
					System.out.println(key+"��������");	
	  	    }    		
			}//endelse
		
			}//!flag1 && !flag2
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else {
				if(people.get_condition()){
					if(elevator1.get_currentpose() <= people.get_location()&&(elevator1.get_conditionvalue()==1||elevator1.get_conditionvalue()==0))
					{
						a[1]=people.get_location()-elevator1.get_currentpose();
					}else{
						b[1]=elevator1.get_currentpose()-people.get_location();
					}
					/////
					if(elevator4.get_currentpose()<=people.get_location()&&(elevator4.get_conditionvalue()==1||elevator4.get_conditionvalue()==0))
					{
						a[4]=people.get_location()-elevator4.get_currentpose();
					}else{
						b[4]=elevator4.get_currentpose()-people.get_location();
					}
					////

					Map<String, Integer>map = new HashMap<String,Integer>();
					int k1 = Math.min(a[1], a[4]);
					int	k2 = Math.min(b[1], b[4]);

					if(k1<50)
					{
						map.put("elevator1",a[1]);
						map.put("elevator4",a[4]);
						int value=k1;
						String key="";  
						for (Map.Entry<String, Integer> entry : map.entrySet()) {  
							if(value==entry.getValue()){  
								key=entry.getKey();  
							}  
						}  
 
						System.out.println(key+"��������");	
					}//endif
					else{
						map.put("elevator1",b[1]);
						map.put("elevator4",b[4]);
						int value=k2;
						String key="";  
						for (Map.Entry<String, Integer> entry : map.entrySet()) {  
							if(value==entry.getValue()){  
								key=entry.getKey();  
							}  
						}   
						System.out.println(key+"��������");	
					}
				}else{
					if(elevator1.get_currentpose() >= people.get_location()&&(elevator1.get_conditionvalue()==-1||elevator1.get_conditionvalue()==0))
					{
						a[1]=elevator1.get_currentpose()-people.get_location();
					}else{
						b[1]=people.get_location()-elevator1.get_currentpose();
					}
					////
					if(elevator4.get_currentpose()>=people.get_location()&&(elevator4.get_conditionvalue()==-1||elevator4.get_conditionvalue()==0))
					{
						a[4]=elevator4.get_currentpose()-people.get_location();
					}else{
						b[4]=people.get_location()-elevator4.get_currentpose();
					}
					////
	
		
					Map<String, Integer>map = new HashMap<String,Integer>();
					int k1 = Math.min(a[1],a[4]);
					int	k2 = Math.min(b[1],b[4]);

					if(k1<50)
					{
						map.put("elevator1",a[1]);
						map.put("elevator4",a[4]);
						int value=k1;
						String key="";  
						for (Map.Entry<String, Integer> entry : map.entrySet()) {  
							if(value==entry.getValue()){  
								key=entry.getKey();  
							}  
						}  
     
						System.out.println(key+"��������");	
					}
					else{
						map.put("elevator1",b[1]);
						map.put("elevator4",b[4]);
						int value=k2;
						String key="";  
						for (Map.Entry<String, Integer> entry : map.entrySet()) {  
							if(value==entry.getValue()){  
								key=entry.getKey();  
							}  
						}   
						System.out.println(key+"��������");	
					}    		
				}//endelse
			}//
	}  
}
