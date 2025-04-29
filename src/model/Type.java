package src.model;

public enum Type {
    GATO,
    CACHORRO;

    public static Type fromString(String value){
        if(value == null || value.trim().isEmpty()){
            throw new IllegalArgumentException("Tipo não pode ser nulo ou vazio");
        }

        try{
            return Type.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Tipo de pet inválido " + value);
        }
    }
}
