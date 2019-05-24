import java.util.ArrayList;

public class Person {
    private String Name;
    private String CPF;
    private String RG;
    private String Date_Of_Birth;
    private String sex;
    private ArrayList<OccurrenceBoletin> List_Occurrence_Boletin;

    public Person(String name, String CPF, String RG, String date_Of_Birth, String sex, ArrayList<OccurrenceBoletin> list_Occurrence_Boletin) {
        Name = name;
        this.CPF = CPF;
        this.RG = RG;
        Date_Of_Birth = date_Of_Birth;
        this.sex = sex;
        List_Occurrence_Boletin = list_Occurrence_Boletin;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getDate_Of_Birth() {
        return Date_Of_Birth;
    }

    public void setDate_Of_Birth(String date_Of_Birth) {
        Date_Of_Birth = date_Of_Birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ArrayList<OccurrenceBoletin> getList_Occurrence_Boletin() {
        return List_Occurrence_Boletin;
    }

    public void setList_Occurrence_Boletin(ArrayList<OccurrenceBoletin> list_Occurrence_Boletin) {
        List_Occurrence_Boletin = list_Occurrence_Boletin;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", CPF='" + CPF + '\'' +
                ", RG='" + RG + '\'' +
                ", Date_Of_Birth='" + Date_Of_Birth + '\'' +
                ", sex='" + sex + '\'' +
                ", List_Occurrence_Boletin=" + List_Occurrence_Boletin +
                '}';
    }
}
