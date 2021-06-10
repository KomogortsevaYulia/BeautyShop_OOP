package main.essence;

public class Employee {
    private int id;
    private String surname;
    private String name;
    private String middle;
    private String birthdate;
    private String post;
    private String phone;

    public Employee(int id,String surname,String name,String middle,String birthdate,String post,String phone){
        this.id=id;
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.post=post;
        this.phone=phone;
    }
    public Employee(String surname,String name,String middle,String birthdate,String post,String phone){
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.post=post;
        this.phone=phone;
    }
    public boolean equals(Employee employee
    ) {
        if(this.getId()==employee.getId()
               /* &this.getPost().equals(employee.getPost())
                &this.getBirthdate().equals(employee.getBirthdate())
                &this.getName().equals(employee.getName())
                &this.getMiddle().equals(employee.getMiddle())
                &this.getSurname().equals(employee.getSurname())
                &this.getPhone().equals(employee.getPhone())*/
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

    public String getSurname() {
        return surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getMiddle() {
        return middle;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public String getPhone() {
        return phone;
    }

}
