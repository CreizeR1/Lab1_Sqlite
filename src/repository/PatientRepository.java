package repository;

import entity.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository implements RepoPatient {
    private static final Logger log= LoggerFactory.getLogger(PatientRepository.class);

    @Override
    public void insert(Connection connection, Patient patient) throws SQLException {
        String query = "SELECT * FROM patient WHERE name = ? AND birthday = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, patient.getName());
        statement.setString(2, patient.getBirthday());
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()){
            String q = "INSERT INTO patient (name, card, birthday, male) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(q);
            st.setString(1, patient.getName());
            st.setString(2, patient.getCard());
            st.setString(3, patient.getBirthday());
            st.setBoolean(4, patient.getMale());
            st.executeUpdate();
            log.info("Пациент добавлен.");
        }else{
            log.info("Пациент уже существует.");
        }
    }

    @Override
    public void delete(Connection connection, Patient patient) throws SQLException {
        String query = "DELETE FROM patient WHERE card = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, patient.getCard());
        int resultSet = statement.executeUpdate();
        if(resultSet>0){
            log.info("Пациент удален.");
        }else{
            log.info("Пациент не был удален.");
        }
    }

    @Override
    public void update(Connection connection, Patient patient) throws SQLException {
        String query = "UPDATE patient SET name = ?, birthday = ?, male = ? WHERE  card = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, patient.getName());
        statement.setString(2, patient.getBirthday());
        statement.setBoolean(3, patient.getMale());
        statement.setString(4, patient.getCard());
        int resultSet = statement.executeUpdate();
        if(resultSet>0){
            log.info("Пациент изменен.");
        }else{
            log.info("Пациент не был изменен.");
        }
    }

    @Override
    public List<Patient> getAll(Connection connection) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs=st.executeQuery();
        while (rs.next()) {
            String card = rs.getString("card");
            String name = rs.getString("name");
            String birthDay = rs.getString("birthday");
            boolean male = rs.getBoolean("male");
            Patient patient = new Patient(card, name, birthDay, male);
            patients.add(patient);
        }
        return patients;
    }

    @Override
    public void getVisitByDate(int year, int month, int day, String card,Connection connection) throws SQLException {
        String query = "SELECT * FROM visit WHERE strftime('%Y', date) = ? AND strftime('%m', date) = ? AND strftime('%d', date) = ? AND id_patient=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(year));
        statement.setString(2, String.format("%02d", month));
        statement.setString(3, String.format("%02d", day));
        statement.setString(4, card);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты Пациента в этот день:");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient")+" | Doctor ID: " + resultSet.getInt("id_doctor")+" | Disease ID: " + resultSet.getInt("id_disease") + " | Visit Date: " + resultSet.getString("date"));
        }
    }

    @Override
    public void getVisitByMonth(int month, String card, Connection connection) throws SQLException {
        String query = "SELECT * FROM visit WHERE strftime('%m', date) = ? AND id_patient=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.format("%02d", month));
        statement.setString(2,card);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты Пациента в этот месяц:");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient")+
                    " | Doctor ID: " + resultSet.getInt("id_doctor")+
                    " | Disease ID: " + resultSet.getInt("id_disease")+
                    " | Visit Date: " + resultSet.getString("date"));
        }
    }

    @Override
    public void getVisitByYear(int year, String card, Connection connection) throws SQLException {
        String query = "SELECT * FROM visit WHERE strftime('%Y', date) = ? AND id_patient=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(year));
        statement.setString(2,card);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты Пациента в этот Год:");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient") +
                    " | Doctor ID: " + resultSet.getInt("id_doctor")+
                    " | Disease ID: " + resultSet.getInt("id_disease")+
                    " | Visit Date: " + resultSet.getString("date"));
        }
    }

}
