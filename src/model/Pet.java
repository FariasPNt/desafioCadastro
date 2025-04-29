package src.model;

public class Pet {
    private String name;
    private Enum Type;
    private Enum PetGender;
    private Address address;
    private String age;
    private String weight;
    private String breed;

    public static final String NAO_INFORMADO = "Não Informado";

    public Pet(){

    }

    public Pet(String name, Enum type, Enum petGender, Address address, String age, String weight, String breed) {
        this.name = name;
        this.Type = type;
        this.PetGender = petGender;
        this.address = address;
        this.age = (age == null || age.trim().isEmpty()) ? NAO_INFORMADO : age;
        this.weight = (weight == null || weight.trim().isEmpty()) ? NAO_INFORMADO : weight;
        this.breed = (breed == null || breed.trim().isEmpty()) ? NAO_INFORMADO : breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum getType() {
        return Type;
    }

    public void setType(Enum type) {
        Type = type;
    }

    public Enum getPetGender() {
        return PetGender;
    }

    public void setPetGender(Enum petGender) {
        PetGender = petGender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
