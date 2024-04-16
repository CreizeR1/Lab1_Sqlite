package entity;

public class Disease {
    String id_disease;
    String name;
    String sign;
    public Disease(String id_disease, String name, String sign){
        this.id_disease=id_disease;
        this.name=name;
        this.sign=sign;
    }

    public String getId_disease() {
        return id_disease;
    }

    public void setId_disease(String id_disease) {
        this.id_disease = id_disease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
