public class OccurrenceBoletin {

    private Person Person_Victim;
    private Person Person_accused;
    private String Desciption_accused;
    private String Local;
    private boolean Using_Weapon; //Yes or Not
    private String Weapon;
    private boolean Name_Responsible_For_Case;
    private boolean StatusCase;

    public OccurrenceBoletin(Person person_Victim, Person person_accused, String desciption_accused, String local, boolean using_Weapon, String weapon, boolean name_Responsible_For_Case, boolean statusCase) {
        Person_Victim = person_Victim;
        Person_accused = person_accused;
        Desciption_accused = desciption_accused;
        Local = local;
        Using_Weapon = using_Weapon;
        Weapon = weapon;
        Name_Responsible_For_Case = name_Responsible_For_Case;
        StatusCase = statusCase;
    }

    public OccurrenceBoletin() {
    }

    public Person getPerson_Victim() {
        return Person_Victim;
    }

    public void setPerson_Victim(Person person_Victim) {
        Person_Victim = person_Victim;
    }

    public Person getPerson_accused() {
        return Person_accused;
    }

    public void setPerson_accused(Person person_accused) {
        Person_accused = person_accused;
    }

    public String getDesciption_accused() {
        return Desciption_accused;
    }

    public void setDesciption_accused(String desciption_accused) {
        Desciption_accused = desciption_accused;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public boolean isUsing_Weapon() {
        return Using_Weapon;
    }

    public void setUsing_Weapon(boolean using_Weapon) {
        Using_Weapon = using_Weapon;
    }

    public String getWeapon() {
        return Weapon;
    }

    public void setWeapon(String weapon) {
        Weapon = weapon;
    }

    public boolean isName_Responsible_For_Case() {
        return Name_Responsible_For_Case;
    }

    public void setName_Responsible_For_Case(boolean name_Responsible_For_Case) {
        Name_Responsible_For_Case = name_Responsible_For_Case;
    }

    public boolean isStatusCase() {
        return StatusCase;
    }

    public void setStatusCase(boolean statusCase) {
        StatusCase = statusCase;
    }

    @Override
    public String toString() {
        return "OccurrenceBoletin{" +
                "Person_Victim=" + Person_Victim +
                ", Person_accused=" + Person_accused +
                ", Desciption_accused='" + Desciption_accused + '\'' +
                ", Local='" + Local + '\'' +
                ", Using_Weapon=" + Using_Weapon +
                ", Weapon='" + Weapon + '\'' +
                ", Name_Responsible_For_Case=" + Name_Responsible_For_Case +
                ", StatusCase=" + StatusCase +
                '}';
    }
}
