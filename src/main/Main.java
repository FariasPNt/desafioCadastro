package src.main;

import src.model.Pet;
import src.service.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("\n=== MENU PET ===");
            System.out.println("1. Cadastrar um novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6. Sair");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao){
                case 1:
                    CreateQuestionnaire.createForm();
                    File file = new File("Formulario");
                    List<String> form = CreateQuestionnaire.readForm(file);
                    Pet pet = CreateQuestionnaire.novoPet(form);
                    SavePet.savingPet(pet);
                    break;
                case 2:
                    File file2 = new File("Formulario");
                    List<String> form2 = CreateQuestionnaire.readForm(file2);
                    Pet pet2 = AltPet.changePet(form2);
                    SavePet.savingPet(pet2);
                    break;
                case 3:
                    DeletePet.deletingPet();
                    break;
                case 4:
                    File pasta = new File("petsCadastrados");
                    File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith(".txt"));
                    List<File> fileList = Arrays.asList(arquivos);
                    LeitorArquivo.lendoArquivo(fileList);
                    break;
                case 5:
                    List<File> listaTexto = SearchPet.searchPets();
                    LeitorArquivo.lendoArquivo(listaTexto);
                    break;
            }
        } while (opcao != 6);

        input.close();
    }
}
