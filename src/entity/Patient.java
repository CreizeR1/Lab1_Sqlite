package entity;

public class Patient {
    String card;
    String name;
    String birthday;
    boolean male;//1-мужчина 0-женщина
    public  Patient(String card, String name, String birthday, boolean male){
        this.card=card;
        this.name=name;
        this.birthday=birthday;
        this.male=male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean getMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
