package repository;

import entity.Patient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RepoPatient {
    void insert(Connection connection, Patient patient) throws SQLException;
    void delete(Connection connection, Patient patient) throws SQLException;
    void update(Connection connection, Patient patient) throws SQLException;
    List<Patient> getAll(Connection connection) throws SQLException;
    void getVisitByDate(int year, int month, int day,String card,Connection connection) throws SQLException;
    void getVisitByMonth( int month,String card,Connection connection) throws SQLException;
    void getVisitByYear( int year,String card,Connection connection) throws SQLException;
}
