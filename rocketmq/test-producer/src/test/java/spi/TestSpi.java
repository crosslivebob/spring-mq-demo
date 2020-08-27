package spi;

import org.junit.Test;
import spi.model.Developer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.ServiceLoader;

public class TestSpi {
    @Test
    public void testSpi() {
        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        Iterator<Developer> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Developer developer = iterator.next();
            developer.sayHi();
        }
    }

    @Test
    public void testJdbc() throws Exception {
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=GMT", "root", "root");
        //3.通过数据库的连接操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        conn.setAutoCommit(false);
        String insert = "insert into student values(111, 'test', 'test')";
        stmt.execute(insert);
//        conn.commit();
//        conn.setAutoCommit(true);

//        conn.setAutoCommit(false);
//        String update = "update student set name='嘻嘻' where number=222";
//        stmt.execute(update);
//        conn.rollback();
//        conn.close();

//        ResultSet rs = stmt.executeQuery("select * from test");//选择import java.sql.ResultSet;
//        while(rs.next()){//如果对象中有数据，就会循环打印出来
//            System.out.println(rs.getInt("flag")+","+rs.getString("name"));
//        }
    }
}
