package spring.ck;

import org.junit.Test;
import ru.yandex.clickhouse.ClickHouseDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testCk {

    @Test
    public void testDataSource() throws Exception {
        String url = "jdbc:clickhouse://localhost:8123/test";
        String userName = "root";
        String passWord = "root";
        String sql = "select * from test";
        List<Map<String, Object>> mapList = queryDataByJDBC(url, userName, passWord, sql);
        System.out.println(mapList);
    }

    public List<Map<String, Object>> queryDataByJDBC(String url, String userName, String password, String sql) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<>();
        //处理驱动类
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");

        //jdbc获取数据
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement stmt = connection.createStatement()) {

            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Map<String, Object> tmpMap = new HashMap<>();
                    tmpMap.put("xAxis", rs.getString("xAxis"));
                    tmpMap.put("elementId", rs.getInt("elementId"));
                    tmpMap.put("tmpValue", rs.getObject("tmpValue"));
                    resultList.add(tmpMap);
                }
            }

        }

        return resultList;
    }

}
