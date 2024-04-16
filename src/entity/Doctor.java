package entity;

public class Doctor {
    String id;
    String name;
    String phone;
    String speciality;
    String date_in;
    int room;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDate_in() {
        return date_in;
    }

    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Doctor( String name, String phone, String speciality, String date_in, int room,String id){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.speciality=speciality;
        this.date_in=date_in;
        this.room=room;
    }
}
