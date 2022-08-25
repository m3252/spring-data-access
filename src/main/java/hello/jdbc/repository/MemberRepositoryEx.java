package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

/**
 * JDBC에 종속적인 인터페이스
 * 특정 기술에 의존되며 인터페이스의 목적이 오염
 */
public interface MemberRepositoryEx {
    Member save(Member member) throws SQLException;

    Member findById(String memberId) throws SQLException;

    void update(String memberId, int money) throws SQLException;

    void delete(String memberId) throws SQLException;
}
