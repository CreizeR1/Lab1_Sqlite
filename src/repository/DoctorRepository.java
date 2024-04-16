package repository;

import entity.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements RepoDoctor {
    private static final Logger log= LoggerFactory.getLogger(DoctorRepository.class);

    @Override
    public void insert(Connection connection, Doctor doctor) throws SQLException {
        String query = "SELECT * FROM doctor WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, doctor.getName());
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()){
            String q = "INSERT INTO doctor (name,phone,speciality,date_in,room) VALUES (?, ?, ?, ?,?)";
            PreparedStatement st  = connection.prepareStatement(q);
            st.setString(1, doctor.getName());
            st.setString(2, doctor.getPhone());
            st.setString(3, doctor.getSpeciality());
            st.setString(4, doctor.getDate_in());
            st.setInt(5, doctor.getRoom());
            st.executeUpdate();
            log.info("Док добавлен.");
        }else{
            log.info("Такой док уже есть.");
        }
    }

    @Override
    public void delete(Connection connection, Doctor doctor) throws SQLException {
        String query = "DELETE FROM doctor WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, doctor.getId());
        int resultSet = statement.executeUpdate();
        if(resultSet>0){
            log.info("Доктор удален.");
        }else{
            log.info("Доктор не был удален.");
        }
    }

    @Override
    public void update(Connection connection, Doctor doctor) throws SQLException {
        String query = "UPDATE doctor SET name = ?, phone = ?, speciality = ?, date_in = ?, room = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, doctor.getName());
        statement.setString(2, doctor.getPhone());
        statement.setString(3, doctor.getSpeciality());
        statement.setString(4, doctor.getDate_in());
        statement.setInt(   5, doctor.getRoom());
        statement.setString(6, doctor.getId());
        int resultSet = statement.executeUpdate();
        if(resultSet>0){
            log.info("Доктор изменен.");
        }else{
            log.info("Доктор не был изменен.");
        }
    }

    @Override
    public List<Doctor> getAll(Connection connection) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs=st.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String speciality = rs.getString("speciality");
            String date_in = rs.getString("date_in");
            int room = rs.getInt("room");
            Doctor doctor = new Doctor( name, phone, speciality,date_in,room,id);
            doctors.add(doctor);
        }
        return doctors;
    }

    @Override
    public void getVisitByDate(int year, int month, int day, String id, Connection connection) throws SQLException {
        String query = "SELECT * FROM visit WHERE strftime('%Y', date) = ? AND strftime('%m', date) = ? AND strftime('%d', date) = ? AND id_doctor=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(year));
        statement.setString(2, String.format("%02d", month));
        statement.setString(3, String.format("%02d", day));
        statement.setString(4, id);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты Доктора в этот день:");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient")+
                    " | Doctor ID: " + resultSet.getInt("id_doctor")+
                    " | Disease ID: " + resultSet.getInt("id_disease")+
                    " | Visit Date: " + resultSet.getString("date"));
        }
    }

    @Override
    public void getVisitByMonth(int month, String id, Connection connection) throws SQLException {
        String query = "SELECT * FROM visit WHERE strftime('%m', date) = ? AND id_doctor=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.format("%02d", month));
        statement.setString(2,id);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты Доктора в этот месяц:");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient")+
                    " | Doctor ID: " + resultSet.getInt("id_doctor")+
                    " | Disease ID: " + resultSet.getInt("id_disease")+
                    " | Visit Date: " + resultSet.getString("date"));
        }
    }

    @Override
    public void getVisitByYear(int year, String id, Connection connection) throws SQLException {
        String query = "SELECT * FROM visit WHERE strftime('%Y', date) = ? AND id_doctor=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(year));
        statement.setString(2,id);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты Доктора в этот Год:");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient")+
                    " | Doctor ID: " + resultSet.getInt("id_doctor")+
                    " | Disease ID: " + resultSet.getInt("id_disease")+
                    " | Visit Date: " + resultSet.getString("date"));
        }
    }
    @Override
    public void getVisitByDateSpeciality(int year, int month, int day, String speciality, Connection connection) throws SQLException {
        String query = "SELECT * FROM visit JOIN doctor ON visit.id_doctor=doctor.id WHERE strftime('%Y', date) = ? AND strftime('%m', date) = ? AND strftime('%d', date) = ? AND doctor.speciality=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(year));
        statement.setString(2, String.format("%02d", month));
        statement.setString(3, String.format("%02d", day));
        statement.setString(4,  speciality);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты к :"+ speciality + " за день");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient")+
                    " | Doctor ID: " + resultSet.getInt("id_doctor")+
                    " | Disease ID: " + resultSet.getInt("id_disease")+
                    " | Visit Date: " + resultSet.getString("date"));
        }
    }

    @Override
    public void getVisitByMonthSpeciality(int month, String speciality, Connection connection) throws SQLException {
        String query = "SELECT * FROM visit JOIN doctor ON visit.id_doctor=doctor.id WHERE strftime('%m', date) = ? AND doctor.speciality=?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, String.format("%02d", month));
        statement.setString(2,  speciality);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты к :"+ speciality+" за месяц");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient")+
                    " | Doctor ID: " + resultSet.getInt("id_doctor")+
                    " | Disease ID: " + resultSet.getInt("id_disease")+
                    " | Visit Date: " + resultSet.getString("date"));
        }
    }

    @Override
    public void getVisitByYearSpeciality(int year, String speciality, Connection connection) throws SQLException {
        String query = "SELECT * FROM visit JOIN doctor ON visit.id_doctor=doctor.id WHERE strftime('%Y', date) = ? AND doctor.speciality=?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, String.valueOf(year));
        statement.setString(2,  speciality);
        ResultSet resultSet = statement.executeQuery();
        log.info("Визиты к :"+ speciality+" за год");
        while (resultSet.next()) {
            log.info("Patient ID: " + resultSet.getInt("id_patient")+
                    " | Doctor ID: " + resultSet.getInt("id_doctor")+
                    " | Disease ID: " + resultSet.getInt("id_disease")+
                    " | Visit Date: " + resultSet.getString("date"));
        }
    }

}
