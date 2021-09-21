[CK-Docker镜像](https://hub.docker.com/r/yandex/clickhouse-server)

## 镜像安装

```shell
docker pull yandex/clickhouse-server
```

## 映射本地端口启动

[docker映射参考](https://www.cnblogs.com/sohuhome/p/9847935.html)

```shell
docker run -itd -p 8123:8123 --name some-clickhouse-server yandex/clickhouse-server
```

| 参数                   | 实例                     | 描述                                     |
| ---------------------- | ------------------------ | ---------------------------------------- |
| HostPort:ContainerPort | 8123:8123                | 将本地的8123端口映射到容器的8123端口     |
| name                   | some-clickhouse-server   | 启动容器的名称，不与已存在的名称冲突即可 |
| image                  | yandex/clickhouse-server | 镜像名称，取镜像安装时候的名称           |

## docker虚拟环境安装常用命令

[参考地址](https://cdmana.com/2021/05/20210522083613825l.html)

```shell
# 更新
apt-get update

# vim
apt install vim

# weget
apt install weget

# yum
apt install yum

# ifconfig
apt install net-tools

# ping
apt install iputils-ping
```

## 命令行登录CK

```shell
# 默认default账户登录
clickhouse-client
# 指定账户密码登录
clickhouse-client --user test --password test
```

## 添加用户及权限

[权限管理参考链接](https://www.cnblogs.com/DBArtist/p/clickhouse_access.html)

首先需要编辑`users.xml`文件，一般在`/etc/clickhouse-server/users.xml`，需要给默认用户`default`开启权限，也就是设置`<access_management>1</access_management>`

```xml
<users>
    <!-- If user name was not specified, 'default' user is used. -->
    <default>
        <!-- 为空，表示不需要密码 -->
        <password></password>
        <!-- User can create other users and grant rights to them. -->
        <access_management>1</access_management>

        <!-- List of networks with open access.

             To open access from everywhere, specify:
                <ip>::/0</ip>

             To open access only from localhost, specify:
                <ip>::1</ip>
                <ip>127.0.0.1</ip>
        -->
        <networks>
            <ip>::1</ip>
        </networks>

        <!-- Settings profile for user. -->
        <profile>default</profile>

        <!-- Quota for user. -->
        <quota>default</quota>
    </default>
    <test>
        <password>test</password>
        <access_management>1</access_management>
        <networks>
            <ip>::/0</ip>
        </networks>
        <profile>default</profile>
        <quota>default</quota>
    </test>
</users>
```

添加用户有两种方式（**无法同时使用两个配置的方式来管理同一个权限实体**）：

- **SQL工作流方式**：使用传统`RBAC`方式，使用`SQL`的方式

  ```sql
  -- 基本创建方式
  CREATE USER root IDENTIFIED WITH PLAINTEXT_PASSWORD BY 'root' HOST ANY SETTINGS PROFILE 'default';
  -- 赋予所有权限
  GRANT ALL ON *.* TO root WITH GRANT OPTION;
  
  -- 详细参数
  CREATE USER [IF NOT EXISTS | OR REPLACE] name [ON CLUSTER cluster_name]
      [IDENTIFIED [WITH {NO_PASSWORD|PLAINTEXT_PASSWORD|SHA256_PASSWORD|SHA256_HASH|DOUBLE_SHA1_PASSWORD|DOUBLE_SHA1_HASH}] BY {'password'|'hash'}]
      [HOST {LOCAL | NAME 'name' | REGEXP 'name_regexp' | IP 'address' | LIKE 'pattern'} [,...] | ANY | NONE]
      [DEFAULT ROLE role [,...]]
      [SETTINGS variable [= value] [MIN [=] min_value] [MAX [=] max_value] [READONLY|WRITABLE] | PROFILE 'profile_name'] [,...]
  ```

- `users.xml`配置文件方式，以添加`test`账户为例：

  ```xml
  <users>
      <!-- 用户test -->
      <test>
          <password>test</password>
          <access_management>1</access_management>
          <networks>
              <ip>::/0</ip>
          </networks>
          <profile>default</profile>
          <quota>default</quota>
      </test>
  </users>
  ```

## Java测试

### 创建表

```sql
create table test
(
    xAxis     DateTime,
    elementId String,
    tmpValue  String
) ENGINE = ReplacingMergeTree(xAxis) partition by xAxis order by xAxis;
```

### 简单JDBC测试类

`maven`导包：

```xml
<dependency>
	<groupId>ru.yandex.clickhouse</groupId>
	<artifactId>clickhouse-jdbc</artifactId>
	<version>0.3.1</version>
</dependency>
```

`Java`测试类：

```java
import org.junit.Test;
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

    public List<Map<String, Object>> queryDataByJDBC(String url, String userName, String password, String sql) throws Exception{
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
```



