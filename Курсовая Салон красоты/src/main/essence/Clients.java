package main.essence;

public class Clients {
    private int id;
    private String surname;
    private String name;
    private String middle;
    private String birthdate;
    private String email;
    private String phone;
    private float point;

    public Clients(int id,String surname,String name,String middle,String birthdate,String email,String phone,float point){
        this.id=id;
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.email=email;
        this.phone=phone;
        this.point=point;
    }
    public Clients(String surname,String name,String middle,String birthdate,String email,String phone,float point){
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.email=email;
        this.point=point;
        this.phone=phone;
    }
    public boolean equals(Clients c) {
        if(this.getId()==c.getId()
               // & this.getPoint()==c.getPoint()
               // &this.getBirthdate().equals(c.getBirthdate())
                //&this.getName().equals(c.getName())
               // &this.getMiddle().equals(c.getMiddle())
               // &this.getEmail().equals(c.getEmail())
                //&this.getSurname().equals(c.getSurname())
                //&this.getPhone().equals(c.getPhone())
        ){
            return true;
        }
        else{
            return false;
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public float getPoint() {
        return point;
    }

    public int getId() {
        return id;
    }

    public String getMiddle() {
        return middle;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
