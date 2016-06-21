package model.viewtables;

public class Sites {
    private Integer id;
    private String name;
    private String street;
    private Integer number;
    private String city;
    private String state;
    private String zip;
    private String company;

    public Sites(Integer id, String name, String street, Integer number, String city, String state, String zip, String company) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
