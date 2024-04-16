package repository;

import entity.Disease;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiseaseRepository implements RepoDisease {
    private static final Logger log= LoggerFactory.getLogger(DiseaseRepository.class);

    @Override
    public void insert(Connection connection, Disease disease) throws SQLException {
        String query = "SELECT * FROM disease WHERE id_disease = ? AND  name= ? ";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, disease.getId_disease());
        statement.setString(2, disease.getName());
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()){
            String q = "INSERT INTO disease (id_disease, name, sign) VALUES (?, ?, ?)";
            PreparedStatement st  = connection.prepareStatement(q);
            st.setString(1, disease.getId_disease());
            st.setString(2, disease.getName());
            st.setString(3, disease.getSign());
            st.executeUpdate();
            log.info("Болезнь добавлена.");
        }else{
            log.info("Болезнь не добавлен.");
        }
    }

    @Override
    public void delete(Connection connection, Disease disease) throws SQLException {
        String query = "DELETE FROM disease WHERE id_disease = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, disease.getId_disease());
        int resultSet = statement.executeUpdate();
        if(resultSet>0){
            log.info("Болезнь удалена.");
        }else{
            log.info("Болезнь не была удалена.");
        }
    }

    @Override
    public void update(Connection connection, Disease disease) throws SQLException {
        String query = "UPDATE disease SET name = ?, sign = ? WHERE id_disease = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, disease.getName());
        statement.setString(2, disease.getSign());
        statement.setString(3, disease.getId_disease());
        int resultSet = statement.executeUpdate();
        if(resultSet>0){
            log.info("Болезнь изменена.");
        }else{
            log.info("Болезнь не была изменена.");
        }

    }

    @Override
    public List<Disease> getAll(Connection connection) throws SQLException {
        List<Disease> diseases = new ArrayList<>();
        String query = "SELECT * FROM doctor";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs=st.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String sign = rs.getString("sign");
            Disease disease= new Disease(id, name, sign);
            diseases.add(disease);
        }
        return diseases;
    }


}
