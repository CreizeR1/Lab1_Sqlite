package repository;

import entity.Disease;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RepoDisease {
    void insert(Connection connection, Disease disease) throws SQLException;
    void delete(Connection connection, Disease disease) throws SQLException;
    void update(Connection connection, Disease disease) throws SQLException;
    List<Disease> getAll(Connection connection) throws SQLException;
}
