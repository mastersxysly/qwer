package com.java1996;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.Iterator;

import javax.management.Query;

import org.omg.PortableInterceptor.IORInterceptor_3_0Holder;

public class Main {
    
    /**
     * ͨ��AB�㾭γ�Ȼ�ȡŷʽ����d(A,B)
     * @param pointA A��(����γ)
     * @param pointB B��(����γ)
     * @return ����(��λ����)
     */
	
	private static int []wordId= {3,5,12,13}; //��Ų�ѯ�ı�
	private static  final double EARTH_RADIUS = 6371393; // ƽ���뾶,��λ��m
    public static double getDistance(QueryPoint pointA, UserPoint pointB) {
        // ��γ�ȣ��Ƕȣ�ת���ȡ����������������Ե���Math.cos��Math.sin
        double radiansAX = Math.toRadians(pointA.getLongitude()); // A������
        double radiansAY = Math.toRadians(pointA.getLatitude()); // Aγ����
        double radiansBX = Math.toRadians(pointB.getLongitude()); // B������
        double radiansBY = Math.toRadians(pointB.getLatitude()); // Bγ����
 
        // ��ʽ�С�cos��1cos��2cos����1-��2��+sin��1sin��2���Ĳ��֣��õ���AOB��cosֵ
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
//        System.out.println("cos = " + cos); // ֵ��[-1,1]
        double acos = Math.acos(cos); // ������ֵ
//        System.out.println("acos = " + acos); // ֵ��[0,��]
//        System.out.println("��AOB = " + Math.toDegrees(acos)); // ���Ľ� ֵ��[0,180]
        return EARTH_RADIUS * acos; // ���ս��
    }
    public static double getD(QueryPoint pointA,UserPoint pointB)
    {
    	return Math.sqrt(Math.pow(pointA.getLongitude()-pointB.getLongitude(),2)+Math.pow(pointA.getLatitude()-pointB.getLatitude(),2));
    }

    //��ȡ����������ı������w(A,B)
    public static double getWeight(UserPoint pointB) {
    	double w=1.0;
    	int points=0;
    	for(int i=0;i<wordId.length;i++)
    	{
    		int key=wordId[i];
    		if(pointB.gettextMap().get(key)!=0) {
    			points++;
    			w*=pointB.gettextMap().get(key);
    		}
    	}
    	if(points==0)
    		return 0;
    	else 
    		return Math.pow(w*0.02,1.0/(points+1));
   
    }
  
    //��ȡ���������st(A,B)
    public static double getSTS(QueryPoint pointA,UserPoint pointB) {
    	if (getWeight(pointB)==0)
    		return 0;
    	return getD(pointA, pointB)/getWeight(pointB);
    }
  
    
    //�ж�n����ѯ�������k��Ŀ������skyline��
    public static void main(String[] args) throws NumberFormatException, IOException {
    	ArrayList<UserPoint> Users=new ArrayList<UserPoint>();
    	ArrayList<QueryPoint>Querys=new ArrayList<QueryPoint>();
    	
    	
    	//��ȡobject��id�;�γ��
    	FileReader fr1=new FileReader("D:/object.txt");
    	BufferedReader br1=new BufferedReader(fr1);
    	String line1="";
    	String[] arrs1=null;
    	while((line1=br1.readLine())!=null) {
    		arrs1=line1.split(",");
    		//System.out.println(arrs[0]+" "+arrs[1]+" "+arrs[2]);
    		UserPoint u=new UserPoint(Integer.valueOf(arrs1[0]), Float.valueOf(arrs1[1]), Float.valueOf(arrs1[2]));
    		//System.out.println( Integer.valueOf(arrs[0])+" "+Float.valueOf(arrs[1])+" "+Float.valueOf(arrs[2]));
    		Users.add(u);
    	}
    	br1.close();
    	fr1.close();
    	//��ȡobject���word�ı�
    	FileReader fr2=new FileReader("D:/word.txt");
    	BufferedReader br2=new BufferedReader(fr2);
    	String line2="";
    	String[] arrs2=null;
    	int t=0;
    	while((line2=br2.readLine())!=null) {
    		Users.get(t).settextMap(line2);
    		t++;
    	}
    	br2.close();
    	fr2.close();
/* 	
    	//Iterator����arrayList
    	Iterator<UserPoint> its=Users.iterator();
    	while(its.hasNext()) {
    		UserPoint u=its.next();
    		//System.out.println(u.getId()+" "+u.getLongitude()+" "+u.getLatitude());
    		System.out.println(u.gettextMap().size()+" "+u.gettextMap().get(1)+" "+u.gettextMap().get(2));
    	}
*/
    	//��ȡ��ѯ��ľ�γ���Լ���ѯ�ı�
    	FileReader fr3=new FileReader("D:/query.txt");
    	BufferedReader br3=new BufferedReader(fr3);
    	String line3="";
    	String[] arrs3=null;
    	while((line3=br3.readLine())!=null) {
    		arrs3=line3.split(",");
    		QueryPoint q=new QueryPoint(Integer.valueOf(arrs3[0]),Float.valueOf(arrs3[1]), Float.valueOf(arrs3[2]));
    		Querys.add(q);
    	}
    	br3.close();
    	fr3.close();
 /* 
    	Iterator<QueryPoint> it=Querys.iterator();
    	while(it.hasNext()) {
    		QueryPoint q=it.next();
    		System.out.println(q.getId()+","+q.getLongitude()+","+q.getLatitude()+",");
    	}
 */
 //�ж��Ƿ���skyline��   
    	//���˵��ı�Ȩ��Ϊ��ĵ�
    	for(int i=0;i<Users.size();i++)
    	{
    		if(getWeight(Users.get(i))==0)
    		{
    			//System.out.println(Users.get(i).getId());
    			Users.remove(i);
    			i--;//�Ƴ�����ʱע��������һ��
    		}
    	}
    	
    	//���˵���֧��ĵĵ�
    	for(int i=0;i<Users.size();i++)
    		for(int j=i+1;j<Users.size();j++)
    		{
    			int c=0;
    			for(int k=0;k<Querys.size();k++)
    			{
    				
    				if( getSTS(Querys.get(k),Users.get(i))<=getSTS(Querys.get(k),Users.get(j)) )
    					{
    					System.out.println("st(Q"+k+",P"+Users.get(i).getId()+")="+getSTS(Querys.get(k),Users.get(i))+"<="+"st(Q"+k+","+"P"+Users.get(j).getId()+")="+getSTS(Querys.get(k),Users.get(j)));
    					c++;
    					continue;
    					}
    				else
    					break;
    			}
    			if(c==Querys.size())
    				Users.remove(j);
    			System.out.println();
    		}
    	
    	
    	for(int i=0;i<Users.size();i++)
    		for(int j=i+1;j<Users.size();j++)
    		{
    			int c=0;
    			for(int k=0;k<Querys.size();k++)
    			{
    				
    				if( getSTS(Querys.get(k),Users.get(i))>=getSTS(Querys.get(k),Users.get(j)) )
    					{
    					System.out.println("st(Q"+k+",P"+Users.get(i).getId()+")="+getSTS(Querys.get(k),Users.get(i))+">="+"st(Q"+k+","+"P"+Users.get(j).getId()+")="+getSTS(Querys.get(k),Users.get(j)));
    					c++;
    					continue;
    					}
    				else
    					break;
    			}
    			if(c==Querys.size())
    				Users.remove(i);
    			System.out.println();
    		}

    	//Iterator����arrayList
    	Iterator<UserPoint> its=Users.iterator();
    	while(its.hasNext()) {
    		UserPoint u=its.next();
    		System.out.println(u.getId()+" "+u.getLongitude()+" "+u.getLatitude());
    	}

 }
}
