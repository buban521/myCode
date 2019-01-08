package myCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BatchExecutionDemo {

	public static void main(String[] args) {
		testMysql();
	}
	
	public static void testMysql() {
        Connection con = null;// 创建一个数据库连接
        PreparedStatement pstmt = null;// 创建预编译语句对象
        ResultSet result = null;// 创建一个结果集对象
        
        long currentTimeMillis = System.currentTimeMillis();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库!");
            System.out.println("Begin>>>>" + currentTimeMillis);
            String url = "jdbc:mysql://192.168.0.167:3306/test?rewriteBatchedStatements=true";
            String user = "root";// 用户名
            String password = "root";// 密码
            con = DriverManager.getConnection(url, user, password);// 获得连接
            System.out.println("连接成功!");
            String sql = "insert into stu values(?,?,?,?)";
            
            pstmt = con.prepareStatement(sql);// 实例化编译语句
            
            for(int i = 0; i < 10000; i++) {
				pstmt.setString(1, "S_10" + i);
				pstmt.setString(2, "stu" + i);
				pstmt.setInt(3, 20 + i);
				pstmt.setString(4, i % 2 == 0 ? "male" : "female");
				pstmt.addBatch();
			}

//            result = pstmt.executeQuery();// 执行查询
            pstmt.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { // 逐一将上面的几个对象关闭,因为不关闭的话会影响性能,并且占用资源
                    // 注意关闭的顺序,最后使用的最先关闭
                if (result != null) {
                    result.close();
                }
                if (pstmt != null) {
                	pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
                System.out.println("End>>>>" + System.currentTimeMillis());
                System.out.println("Used>>>>" + (System.currentTimeMillis() - currentTimeMillis));
                System.out.println("数据库连接已经关闭!");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
