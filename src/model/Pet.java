package src.model;

public class Pet {
    private String name;
    private Enum Type;
    private Enum PetGender;
    private String address;
    private String age;
    private String weight;
    private String breed;

    public static final String NAO_INFORMADO = "Não Informado";

    public Pet(String name, Enum type, Enum petGender, String address, String age, String weight, String breed) {
        this.name = (name == null || name.trim().isEmpty()) ? NAO_INFORMADO : name ;
        this.Type = type;
        this.PetGender = petGender;
        this.address = (address == null || address.trim().isEmpty()) ? NAO_INFORMADO : address;
        this.age = (age == null || age.trim().isEmpty()) ? NAO_INFORMADO : age;
        this.weight = (weight == null || weight.trim().isEmpty()) ? NAO_INFORMADO : weight;
        this.breed = (breed == null || breed.trim().isEmpty()) ? NAO_INFORMADO : breed;
    }
}
//1 - Qual o nome e sobrenome do pet?
//
//        2 - Qual o tipo do pet (Cachorro/Gato)?
//
//        3 - Qual o sexo do animal?
//
//        4 - Qual endereço e bairro que ele foi encontrado?
//
//        5 - Qual a idade aproximada do pet?
//
//        6 - Qual o peso aproximado do pet?
//
//        7 - Qual a raça do pet?