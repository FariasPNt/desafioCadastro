package src.service;

import src.model.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SavePet {

    public static void savingPet(Pet pet){
        String pasta = "petsCadastrados";
        File directory = new File(pasta);
        if(!directory.exists()){
            directory.mkdir();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String timestamp = LocalDateTime.now().format(formatter);

        String nomeFormatado = pet.getName().replaceAll("\\s+", "").toUpperCase();
        String nomeArquivo = timestamp + "-" + nomeFormatado + ".txt";

        File file = new File(directory, nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(pet.getName());
            writer.newLine();
            writer.write(pet.getType().toString());
            writer.newLine();
            writer.write(pet.getPetGender().toString());
            writer.newLine();
            writer.write(pet.getAddress().toString());
            writer.newLine();
            writer.write(pet.getAge());
            writer.newLine();
            writer.write(pet.getWeight());
            writer.newLine();
            writer.write(pet.getBreed());
        } catch (IOException e) {
            System.err.println("Erro ao salvar o pet: " + e.getMessage());
        }
    }
<<<<<<< HEAD
=======



>>>>>>> 6ec14db60a3efe0318d92becbdd109fc4e9c8710
}
