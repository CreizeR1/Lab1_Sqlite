package repository;

import entity.Visit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RepoVisit {
    void insert(Connection connection, Visit visit) throws SQLException;
    void delete(Connection connection, Visit visit) throws SQLException;
    void update(Connection connection, Visit visit) throws SQLException;
    List<Visit> getAll(Connection connection) throws SQLException;

}
