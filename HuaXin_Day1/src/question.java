import javax.swing.JOptionPane;

public class question {
	public static void main(String[] args){
		
		        float x =Float.parseFloat(JOptionPane.showInputDialog("������x"));//����
		        float y =Float.parseFloat(JOptionPane.showInputDialog("������y"));//����
				
				//if((x-2)*(x-2)+(y-2)*(y-2)<=1 ||(x-2)*(x-2)+(y+2)*(y+2)<=1 ||(x+2)*(x+2)+(y-2)*(y-2)<=1||(x+2)*(x+2)+(y+2)*(y+2)<=1)
				if(Math.sqrt(Math.pow(Math.abs(x)-2,2)+Math.pow(Math.abs(y)-2,2))<=1)
					JOptionPane.showMessageDialog(null, "yes");//���
				else 
					JOptionPane.showMessageDialog(null, "no");//���

	}
}
