package repository;

import entity.Doctor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RepoDoctor {
    void insert(Connection connection, Doctor doctor) throws SQLException;
    void delete(Connection connection, Doctor doctor) throws SQLException;
    void update(Connection connection, Doctor doctor) throws SQLException;
    List<Doctor> getAll(Connection connection) throws SQLException;
    void getVisitByDate(int year, int month, int day,String id,Connection connection) throws SQLException;
    void getVisitByMonth( int month,String id,Connection connection) throws SQLException;
    void getVisitByYear( int year,String id,Connection connection) throws SQLException;
    void getVisitByDateSpeciality(int year, int month, int day,String speciality,Connection connection) throws SQLException;
    void getVisitByMonthSpeciality( int month,String speciality,Connection connection) throws SQLException;
    void getVisitByYearSpeciality( int year,String speciality,Connection connection) throws SQLException;
}
