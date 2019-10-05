package com.JdbcConn.cn;

import java.sql.*;

public class DataBaseConn {
	public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");//��������
            System.out.println("\n"+"�ɹ�����sql������");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("�Ҳ���sql������");
            e.printStackTrace();
        }
        String url="jdbc:mysql://localhost:3306/521?serverTimezone=UTC&useSSL=false"; 
        //mysql�������ݿ�ʱ��ʾϵͳʱ�����ִ���,��������������ݿ�������url�����serverTimezone=UTC����
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url,"root","123456");//��ȡ����
            System.out.println("�ɹ����ӵ����ݿ� 521��");
            String sql = "select * from emp";//����sql����
            ps = conn.prepareStatement(sql); //����sql�������
            rs = ps.executeQuery();//ִ��sql
            //emp Ϊ��������
            System.out.println(rs.toString());
            while (rs.next()) {//�������
                System.out.println(rs.getString("name"));
            }
            ps.close();
            conn.close();//�ر�����
        } catch (SQLException e){
            System.out.println("Fail to connect the database/521!");
            e.printStackTrace();
        }
    }
}
