package src.model;

public enum PetGender {
    MACHO,
    FEMEA;

    public static PetGender genderFromString(String value){
        if(value == null || value.trim().isEmpty()){
            throw new IllegalArgumentException("Gênero não pode ser nulo ou vazio");
        }

        try{
            return PetGender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Gênero do pet inválido " + value);
        }
    }
}
