import entity.Disease;
import entity.Doctor;
import entity.Patient;
import entity.Visit;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.*;

import static java.lang.Integer.parseInt;

public class Hospital {
    private Connection connection = null;
    private static final Logger log= LoggerFactory.getLogger(Hospital.class);

    public Hospital() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:hospital.db");
            log.debug("Connection successful");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    Connection getConnection(){
        return connection;
    }

}
