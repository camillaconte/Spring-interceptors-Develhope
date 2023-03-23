package interceptors.entities;


public class User {

    private long id;
    private String firtName;
    private String lastName;
    private String placeOfBirth;

    public User(){}

    public User(long id, String firtName, String lastName, String placeOfBirth) {
        this.id = id;
        this.firtName = firtName;
        this.lastName = lastName;
        this.placeOfBirth = placeOfBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
}
