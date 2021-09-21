package spring.ck;

import org.junit.Test;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testCk {

    @Test
    public void testDataSource() throws Exception {
        String url = "jdbc:clickhouse://localhost:8123/test";
        String userName = "test";
        String passWord = "test";
        String sql = "select * from test";
        List<Map<String, Object>> mapList = queryDataByJDBC(url, userName, passWord, sql);
        System.out.println(mapList);
    }

    private List<Map<String, Object>> queryDataByJDBC(String url, String userName, String password, String sql) throws Exception {
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

    @Test
    public void multThread() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    getConnectNotClose();
                    Thread.sleep(60 * 1000);
                } catch (Exception e) {
                    System.out.println("哦豁，完蛋" + Thread.currentThread().getName());
                    e.printStackTrace();
                }
            }).start();
        }
        while (true) {}
    }

    private void getConnectNotClose() throws SQLException, ClassNotFoundException {
        String sql = "select * from test";

        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setClientName("Agent #1");
        properties.setCompress(false);

        ClickHouseDataSource dataSource = new ClickHouseDataSource("jdbc:clickhouse://localhost:8123/test",
                properties);

        Connection connection = dataSource.getConnection("test", "test");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("思密达" + rs.getString("xAxis"));
        }
    }
}
