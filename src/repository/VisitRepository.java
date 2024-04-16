package repository;

import entity.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitRepository implements RepoVisit {
    private static final Logger log= LoggerFactory.getLogger(VisitRepository.class);

    @Override
    public void insert(Connection connection, Visit visit) throws SQLException {
        String query = "SELECT * FROM visit WHERE id_patient = ? AND  id_doctor = ? AND date=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, visit.getId_patient());
        statement.setInt(2, visit.getId_doctor());
        statement.setString(3, visit.getDate());
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()){
            String q = "INSERT INTO visit (id_patient, id_doctor, id_disease, date) VALUES (?, ?, ?, ?)";
            PreparedStatement st  = connection.prepareStatement(q);
            st.setInt(1, visit.getId_patient());
            st.setInt(2, visit.getId_doctor());
            st.setInt(3, visit.getId_disease());
            st.setString(4, visit.getDate());
            st.executeUpdate();
            log.info("Визит добавлен.");
        }else{
            log.info("Визит не добавлен.");
        }
    }

    @Override
    public void delete(Connection connection, Visit visit) throws SQLException {
        String query = "DELETE FROM visit WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, visit.getId());
        int resultSet = statement.executeUpdate();
        if(resultSet>0){
            log.info("Визит удален.");
        }else{
            log.info("Визит не был удалена.");
        }
    }

    @Override
    public void update(Connection connection, Visit visit) throws SQLException {
        String query = "UPDATE visit SET id_patient = ?, id_doctor = ?, id_disease = ?, date = ? WHERE  id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, visit.getId_patient());
        statement.setInt(2,  visit.getId_doctor());
        statement.setInt(3,  visit.getId_disease());
        statement.setString(4, visit.getDate());
        statement.setString(5, visit.getId());
        int resultSet = statement.executeUpdate();
        if(resultSet>0){
            log.info("Визит изменен.");
        }else{
            log.info("Визит не был изменен.");
        }
    }

    @Override
    public List<Visit> getAll(Connection connection) throws SQLException {
        List<Visit> visits = new ArrayList<>();
        String query = "SELECT * FROM visit";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs=st.executeQuery();
        while (rs.next()) {
            int id_patient = rs.getInt("id_patient");
            int id_doctor = rs.getInt("id_doctor");
            int id_disease = rs.getInt("id_disease");
            String date = rs.getString("date");
            String id = rs.getString("id");
            Visit visit = new Visit(id_patient, id_doctor,id_disease, date,id);
            visits.add(visit);
        }
        return visits;
    }




}
