package com.map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JTextArea;
public class MapBoard extends JPanel implements MouseListener {
	public static int MARGIN=30;//�߾� 
	public static int GRID_SPAN=35;//������ 
	public static int ROWS=20;//�������� 
	public static int COLS=20;//�������� 
	Point[] pointsList=new Point[(ROWS+1)*(COLS+1)];//��ʼ��ÿ������Ԫ��Ϊnull 
	int pointsCount=0;//��ǰ���������Ŀ������ 
	int xIndex,yIndex;//��ǰ����Ŀ�������� 
	public JTextArea jTextArea;
	public MapBoard(JTextArea jt){
		jTextArea = jt;
		setBackground(Color.LIGHT_GRAY);//���ñ�����ɫΪ��ɫ 
		addMouseListener(this);//����¼������� 
		addMouseMotionListener(new MouseMotionListener(){
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				int x1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN; 
			    int y1=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;//����굥��������λ��ת��Ϊ�������� 
			    if(x1<0||x1>ROWS||y1<0||y1>COLS)//||gameOver||findChess(x1,y1)){//��Ϸ�Ѿ������������£����������⣬�����£�x��yλ���Ѿ���Ŀ�����ڣ������� 
			     {setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//���ó�Ĭ����״ 
			    }else{ 
			     setCursor(new Cursor(Cursor.HAND_CURSOR));//���ó����� 
			    }
			}
				@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
					
			}     
		});
	}
	
	/*����*/ 
	public void paintComponent(Graphics g){ 
		System.out.println("�������ѻ��á�����������������������");
		super.paintComponent(g);//����������
		for(int i=0;i<=ROWS;i++){//������ 
			g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN); 
		} 
		for(int i=0;i<=COLS;i++){//��ֱ�� 
			g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN,MARGIN+ROWS*GRID_SPAN); 
		} 
		/*������*/ 
		
		for(int i=0;i<pointsCount;i++){ 
			int xPos=pointsList[i].getX()*GRID_SPAN+MARGIN;//���񽻲��x���� 
			int yPos=pointsList[i].getY()*GRID_SPAN+MARGIN;//���񽻲��y���� 
			g.setColor(pointsList[i].getColor());//������ɫ 
			g.fillOval(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER); 
//			if(i==pointsCount-1){ 
//				g.setColor(Color.red);//������һ��Ŀ���Ϊ��ɫ 
//				g.drawRect(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER); 
//			} 
		} 
	} 
	public void paintPoints() throws NumberFormatException, IOException{
		FileReader fr1=new FileReader("D:/object.txt");
		BufferedReader br1=new BufferedReader(fr1);
		String line1="";
		String[] arrs1=null;
		while((line1=br1.readLine())!=null) {
			arrs1=line1.split(",");
			Point p=new Point(Integer.valueOf(arrs1[0]), Integer.valueOf(arrs1[1]), Integer.valueOf(arrs1[2]),Color.WHITE);
			//pointsList[pointsCount].setMessager();
			pointsList[pointsCount]=p;
			pointsCount++;
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
			pointsList[t].setWord(line2);
			pointsList[t].setMessager();
			t++;
		}
		br2.close();
		fr2.close();
		
		
		//��ȡ��ѯ��
		FileReader fr3=new FileReader("D:/query.txt");
		BufferedReader br3=new BufferedReader(fr3);
		String line3="";
		String[] arrs3=null;
		while((line3=br3.readLine())!=null) {
			arrs3=line3.split(",");
			Point q=new Point(Integer.valueOf(arrs3[0]),Integer.valueOf(arrs3[1]), Integer.valueOf(arrs3[2]),Color.BLACK);
			pointsList[pointsCount]=q;
			pointsCount++;
		}
		br3.close();
		fr3.close();
//		Point p1=new Point(1, 1, 1, Color.BLACK);
//		Point p2=new Point(2, 2, 2, Color.BLACK);
//		Point p3=new Point(3, 7, 8, Color.BLACK);
//		Point p4=new Point(4, 4, 5, Color.BLACK);
//		pointsList[0]=p1;
//		pointsList[1]=p2;
//		pointsList[2]=p3;
//		pointsList[3]=p4;
//		pointsCount=4;
    	repaint();
	}
	public void clearPoints() {
		System.out.println("������ա�������������");
		for(int i=0;i<pointsList.length;i++)
			pointsList[i]=null;
		pointsCount=0;
		repaint();
	}
	
	private boolean findPoints(int x,int y){ 
		  for(Point c:pointsList){ 
		   if(c!=null&&c.getX()==x&&c.getY()==y) 
		    return true; 
		  } 
		  return false; 
	}
	private int getPointID(int x,int y) {
		for(int i=0;i<pointsList.length;i++) {
			Point c=pointsList[i];
			   if(c!=null&&c.getX()==x&&c.getY()==y) 
			    return i; 
		}
		return 10000;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		xIndex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN; 
		yIndex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;//����굥��������λ��ת��Ϊ�������� 
		if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS)//�������������⣬������ 
		   return ; 
		if(findPoints(xIndex,yIndex))//x,yλ���Ѿ������Ӵ��ڣ������� 
			{System.out.println(xIndex+"   "+yIndex+"  "+pointsList[getPointID(xIndex, yIndex)].getColor()+"    "+pointsCount);
			System.out.println(getPointID(xIndex, yIndex));
				if(pointsList[getPointID(xIndex, yIndex)].getColor().equals(Color.BLACK))
					jTextArea.append("This is a QueryPoint. || "+pointsList[getPointID(xIndex, yIndex)].getMessager()+"\n");
				else
					jTextArea.append("This is a ObjectPoint. || "+pointsList[getPointID(xIndex, yIndex)].getMessager()+"\n");
			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
