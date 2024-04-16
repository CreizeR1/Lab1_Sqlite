import entity.Doctor;
import entity.Patient;
import entity.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DiseaseRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.VisitRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final Logger log= LoggerFactory.getLogger(Hospital.class);
    public static void main(String[] args) throws SQLException {
        Hospital hp = new Hospital();

        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        VisitRepository visitRepository = new VisitRepository();

        log.info(patientRepository.getAll(hp.getConnection()).toString());
        patientRepository.insert(hp.getConnection(), new Patient(null,"Петров Иван Петрович","1980-04-10",true));
        doctorRepository.insert(hp.getConnection(),new Doctor("Петров Иван Иванович","79125294655","Дерматолог","2023-05-18",305,null));
        visitRepository.insert(hp.getConnection(),new Visit(2,1,2,"2024-04-01",null));

        patientRepository.getVisitByDate(2024,4,1,"1",hp.getConnection());
        patientRepository.getVisitByMonth(4,"1",hp.getConnection());
        patientRepository.getVisitByYear(2024,"1",hp.getConnection());

        doctorRepository.getVisitByDate(2024,4,1,"1",hp.getConnection());
        doctorRepository.getVisitByMonth(4,"1",hp.getConnection());
        doctorRepository.getVisitByYear(2024,"1",hp.getConnection());

        doctorRepository.getVisitByDateSpeciality(2024,4,1,"Терапевт",hp.getConnection());
        doctorRepository.getVisitByMonthSpeciality(4,"Терапевт",hp.getConnection());
        doctorRepository.getVisitByYearSpeciality(2024,"Терапевт",hp.getConnection());


    }
}