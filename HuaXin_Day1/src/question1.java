import javax.swing.JOptionPane;

public class question1 {
	public static void main(String[] args){
		int n1,n2;
        int a =Integer.parseInt(JOptionPane.showInputDialog("������a"));//����
        int b =Integer.parseInt(JOptionPane.showInputDialog("������b"));//����
        //��С������
		for(n2=a;;n2++){
			if(n2%a==0&&n2%b==0){
				break;
			}
		}
        //���Լ��
        while(b!=0)
        {
        int r=a%b;
        a=b;
        b=r;
        }
        n1=a;
			JOptionPane.showMessageDialog(null, "��С������ �� "+String.valueOf(n2)+","+"���Լ����"+String.valueOf(n1));//���
		
	}
}

/*
 	int a=12;
 	int b=14;
 	int c;
 	c=a<b?a:b;
 	while(a%c!=0||b%c!=0)
 	c--;
 	
 	
 	while
*/

/*
 		c=a*b;
 		while(b!=0){
 		t=a%b;
 		a=b;
 		b=t;
 		}
 		
 		min=c/a;
 		max=a
 */