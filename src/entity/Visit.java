package entity;

public class Visit {
    int id_patient;
    int id_doctor;
    int id_disease;
    String date;
    String id;
    public Visit(int id_patient, int id_doctor, int id_disease, String date, String id){
        this.id=id;
        this.id_patient=id_patient;
        this.id_doctor=id_doctor;
        this.id_disease=id_disease;
        this.date=date;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_disease() {
        return id_disease;
    }

    public void setId_disease(int id_disease) {
        this.id_disease = id_disease;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
