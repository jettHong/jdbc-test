package com.jett.q1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Q：不使用commit也能修改数据成功？
 * A：这取决于使用什么引擎。
 * 测试 Innodb、MyISAM 不同引擎。
 *
 * @author jett
 */
public class MySQLTransactionTest {
    public static void main(String[] args) {
        MySQLTransactionTest test = new MySQLTransactionTest();
        // 测试 Innodb 引擎
        test.process("account");
        
        // 测试 MyISAM 引擎
        test.process("account_myisam");
    }
    
    public void process(String tableName) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 1、加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // 2、截取连接
            String url = "jdbc:mysql://127.0.0.1:3308/my_jdbc?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
            String username = "myroot";
            String password = "myroot";
            Connection connection = DriverManager.getConnection(url, username, password);
            // 关闭默认自动事务提交，重要！！！
            connection.setAutoCommit(false);
        
            // 3、创建 Statement 实例
            Statement statement = connection.createStatement();
        
            // 4、执行 SQL 语句
            statement.addBatch("update " + tableName + " set money = money-1 where card_id = '1234567890'");
            // 下面语句会出错，用于事务回滚，可以下断点，先检查下数据库是否变更。
            statement.addBatch("update xxx where xxx = 'xxx'");
        
            statement.executeBatch();//5、执行语句
            connection.commit();//提交事务
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
}
