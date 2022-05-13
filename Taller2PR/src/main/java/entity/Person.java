package entity;

public class Person {

    private String name;
    private int id;
    private String natId;

    public Person(int id, String name, String natId) {
        this.name = name;
        this.id = id;
        this.natId = natId;
    }

    public Person(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNatId() {
        return natId;
    }

    public void setNatId(String natId) {
        this.natId = natId;
    }
}

