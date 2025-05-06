package src.service;

import src.model.Address;
import src.model.Pet;
import src.model.PetGender;
import src.model.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateQuestionnaire {
    public static void createForm() {
        File file = new File("Formulario");
        if(file.exists()){
            System.out.println("Fórmulário já existe");
            return;
        }

        List<String> perguntas = new ArrayList<>();
        perguntas.add("1 - Qual o nome e sobrenome do pet?");
        perguntas.add("2 - Qual o tipo do pet (Cachorro/Gato)?");
<<<<<<< HEAD
        perguntas.add("3 - Qual o sexo do animal? (Macho/Femea");
=======
        perguntas.add("3 - Qual o sexo do animal?");
>>>>>>> 6ec14db60a3efe0318d92becbdd109fc4e9c8710
        perguntas.add("4 - Qual endereço e bairro que ele foi encontrado?");
        perguntas.add("5 - Qual a idade aproximada do pet?");
        perguntas.add("6 - Qual o peso aproximado do pet?");
        perguntas.add("7 - Qual a raça do pet?");


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(String pergunta : perguntas){
                writer.write(pergunta);
                writer.newLine();
            }
            System.out.println("Formulário criado com sucesso");
        } catch (IOException e){
            throw new RuntimeException("Erro ao criar o arquivo: " + e.getMessage());
        }
    }
    public static List<String> readForm(File file){
        List<String> form = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
<<<<<<< HEAD
                form.add(line.trim());
=======
                if(!line.isBlank()){
                    form.add(line.trim());
                }
>>>>>>> 6ec14db60a3efe0318d92becbdd109fc4e9c8710
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o formulário" + e.getMessage());
        }
        return form;
    }

    public static Pet novoPet(List<String> form){
        Scanner input = new Scanner(System.in);
        Pet pet = new Pet();

        for (int i = 0; i < form.size(); i++) {
            boolean validAnswer = false;

            while (!validAnswer) {
                try {
                    System.out.println(" - " + form.get(i));
                    String answer = input.nextLine();

                    switch (i) {
                        case 0 -> {
                            pet.setName(Validations.validateName(answer));
                            validAnswer = true;
                        }
                        case 1 -> {
                            pet.setType(Type.fromString(answer));
                            validAnswer = true;
                        }
                        case 2 -> {
                            pet.setPetGender(PetGender.genderFromString(answer));
                            validAnswer = true;
                        }
                        case 3 -> {
                            System.out.print("Rua: ");
                            String rua = input.nextLine();

                            System.out.print("Cidade: ");
                            String cidade = input.nextLine();

                            System.out.print("Número: ");
                            String numero = input.nextLine();

                            Address endereco = new Address(rua, cidade, numero);
                            pet.setAddress(endereco);
                            validAnswer = true;
                        }
                        case 4 -> {
                            pet.setAge(Validations.validateAge(answer));
                            validAnswer = true;
                        }
                        case 5 -> {
                            pet.setWeight(Validations.validateWeight(answer));
                            validAnswer = true;
                        }
                        case 6 -> {
                            pet.setBreed(answer);
                            validAnswer = true;
                        }

                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(" Erro: " + e.getMessage());
                    System.out.println("Tente novamente.");
                }
            }
        }
        return pet;
    }

}
