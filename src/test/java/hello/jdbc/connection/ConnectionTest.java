package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws Exception {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={},class={}", con1, con1.getClass());
        log.info("connection={},class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws Exception{
        DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws Exception {
        //커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000L);
    }

    private void useDataSource(DataSource dataSource) throws Exception{
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
//        Connection con3 = dataSource.getConnection();
//        Connection con4 = dataSource.getConnection();
//        Connection con5 = dataSource.getConnection();
//        Connection con6 = dataSource.getConnection();
//        Connection con7 = dataSource.getConnection();
//        Connection con8 = dataSource.getConnection();
//        Connection con9 = dataSource.getConnection();
//        Connection con10 = dataSource.getConnection();
//        Connection con11 = dataSource.getConnection();
        // MyPool - Fill pool skipped, pool is at sufficient level.
        // java.sql.SQLTransientConnectionException: MyPool - Connection is not available, request timed out after 30002ms.
        log.info("connection={},class={}", con1, con1.getClass());
        log.info("connection={},class={}", con2, con2.getClass());
    }
}
