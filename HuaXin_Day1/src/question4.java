import java.util.Scanner;

public class question4 {
	public static void main(String[] args){
		/*Scanner input = new Scanner(System.in);
		int a=input.nextInt();*/
		int sum;
		for(int i=1;i<=1000;i++){
			sum=0;
			for(int j=1;j<i;j++)
			if(i%j==0) 
				sum+=j;
			if(sum==i)
			{
				System.out.print(i+"=");
			    for(int n=1;n<sum;n++)
			    	if(sum%n==0)
			    		System.out.print(n+"+");
			    System.out.println();
			}
		}
		System.out.println();
	}
}

/*�Ż�
 j<i/2+1;��������һ�룬�Ͳ��ܱ������� ������ѭ������
*/