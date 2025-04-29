package src.model;

public class Address {
    private String number;
    private String city;
    private String street;

    public static final String NAO_INFORMADO = "Não Informado";

    public Address(){

    }
    public Address(String number, String city, String street) {
        this.number = (number == null || number.trim().isEmpty() ? NAO_INFORMADO : number);
        this.city = (city == null || city.trim().isEmpty() ? NAO_INFORMADO : city);
        this.street = (street == null || street.trim().isEmpty() ? NAO_INFORMADO : street);
    }

    @Override
    public String toString() {
        return number + ", " + city + ", " + street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
