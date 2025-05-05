package src.service;

import src.model.Address;
import src.model.Pet;
import src.model.PetGender;
import src.model.Type;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AltPet {
    public static Pet changePet(List<String> form){
        Scanner input = new Scanner(System.in);

        List<File> files = SearchPet.searchPets();
        if(files == null || files.isEmpty()){
            System.out.println("Nenhum pet encontrado");
            return null;
        }

        for(int i = 0; i < files.size(); i++){
            List<String> dados = SearchPet.lerArquivo(files.get(i));
            String linha = String.format("%d. %s - %s - %s - %s - %s anos - %skg - %s",
                    i + 1,
                    dados.get(0),
                    dados.get(1),
                    dados.get(2),
                    dados.get(3),
                    dados.get(4),
                    dados.get(5),
                    dados.get(6)
            );
            System.out.println(linha);
        }

        int choice = -1;
        while (true){
            System.out.println("Número do pet a ser alterado: ");
            String entry = input.nextLine();
            try{
                choice = Integer.parseInt(entry);
                if (choice < 1 || choice > files.size()) {
                    System.out.println("Número inválido");
                }
                else{
                    break;
                }
            } catch (NumberFormatException e){
                System.out.println("Formato inválido. Apenas números");
            }
        }

        File selectedPet = files.get(choice - 1);
        List<String> originalData = SearchPet.lerArquivo(selectedPet);
        String precedingType = originalData.get(1);
        String precedingSex = originalData.get(2);
        if(selectedPet.delete()){
            System.out.println("deletado antigo");
        } else{
            System.out.println("Erro ao deletar antigo");
        }

        Pet pet = new Pet();

        for (int i = 0; i < form.size(); i++) {
            boolean validAnswer = false;

            while (!validAnswer) {
                try {
                    if (i == 1) {
                        pet.setType(Type.fromString(precedingType)); // mantém tipo anterior
                        validAnswer = true;
                        continue;
                    }

                    if (i == 2) {
                        pet.setPetGender(PetGender.genderFromString(precedingSex)); // mantém sexo anterior
                        validAnswer = true;
                        continue;
                    }

                    System.out.println(" - " + form.get(i));
                    String answer = input.nextLine();

                    switch (i) {
                        case 0 -> {
                            pet.setName(Validations.validateName(answer));
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
                    System.out.println("Erro: " + e.getMessage());
                    System.out.println("Tente novamente.");
                }
            }
        }
        File pasta = new File("petsCadastrados");
        if (!pasta.exists()) {
            boolean criada = pasta.mkdirs();
            if (!criada) {
                System.out.println("Não foi possível criar a pasta de pets.");
                return null;
            }
        }
        return pet;

//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
//
//        String nomePet = pet.getName().replaceAll(" ", "").toUpperCase();
//        String fileName = formatter.format(now) + "-" + nomePet + ".txt";
//
//        File novoArquivo = new File("petsCadastrados", fileName);
//
//        try (PrintWriter writer = new PrintWriter(novoArquivo)) {
//            writer.println(pet.getName());
//            writer.println(pet.getType().toString());
//            writer.println(pet.getPetGender().toString());
//            writer.println(pet.getAddress().toString()); // Rua - Cidade - Número
//            writer.println(pet.getAge());
//            writer.println(pet.getWeight());
//            writer.println(pet.getBreed());
//            System.out.println("Pet atualizado com sucesso!");
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar o novo pet: " + e.getMessage());
//        }
    }
}
