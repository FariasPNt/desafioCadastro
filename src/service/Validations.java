package src.service;

import javax.print.attribute.standard.MediaSize;

public class Validations {

    public static final String NAO_INFORMADO = "Não Informado";

    public static String validateName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("O nome não pode ser branco");
        }
        String[] parts = name.trim().split(" ");
        if(parts.length < 2){
            throw new IllegalArgumentException("Deve conter nome e sobrenome");
        }
        if (!name.matches("^[A-Za-z ]+$")) {
            throw new IllegalArgumentException("O nome do pet deve conter apenas letras.");
        }
        return name.trim();
    }

    public static String validateWeight(String weight){
        if(weight == null || weight.isBlank()){
            return NAO_INFORMADO;
        }

        String value1 = weight.replace(",",".");
        if(!value1.matches("^[\\d,.]+$")){
            throw new IllegalArgumentException("Deve conter apenas números");
        }

        double value2 = Double.parseDouble(value1);
        if(value2 > 60 || value2 < 0.5){
            throw new IllegalArgumentException("Peso inválido");
        }

        return value1;
    }

    public static String validateAge(String age){
        if(age == null || age.isBlank()){
            return NAO_INFORMADO;
        }
        if(!age.matches("^[\\d,.]+$")){
            throw new IllegalArgumentException("Deve conter apenas números");
        }
        int num1 = Integer.parseInt(age);
        if(num1 < 0 || num1 > 20 ){
            throw new IllegalArgumentException("Idade inválida");
        }
        return age;
    }
}
