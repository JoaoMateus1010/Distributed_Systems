import org.json.*;
public class Person {
    private String Name;
    private String CPF;
    private String RG;
    private String Date_Of_Birth;
    private String Sex;

    public Person(String name, String CPF, String RG, String date_Of_Birth, String sex) {
        Name = name;
        this.CPF = CPF;
        this.RG = RG;
        Date_Of_Birth = date_Of_Birth;
        this.Sex = sex;
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
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    public JSONObject getJSONPerson(Person OBJ_Person){
        JSONObject obj_to_return = new JSONObject();
        try{
            obj_to_return.put("Name",OBJ_Person.getName());
            obj_to_return.put("CPF",OBJ_Person.getCPF());
            obj_to_return.put("RG",OBJ_Person.getRG());
            obj_to_return.put("Date_Of_Birth",OBJ_Person.getDate_Of_Birth());
            obj_to_return.put("Sex",OBJ_Person.getSex());
        }catch (JSONException e){
            System.out.println(e);
        }
        return obj_to_return;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", CPF='" + CPF + '\'' +
                ", RG='" + RG + '\'' +
                ", Date_Of_Birth='" + Date_Of_Birth + '\'' +
                ", sex='" + Sex + '\'' +
                '}';
    }
}
