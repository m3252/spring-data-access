package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection() {
        try {
//            // DriverManager는 DataSource 인터페이스를 상속하지 않음
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 설정과 사용의 분리가 이뤄짐
            DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
            Connection connection = dataSource.getConnection();

            log.info("get connection={}, class={}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
