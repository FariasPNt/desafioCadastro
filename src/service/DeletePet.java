package src.service;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class DeletePet {

    public static void deletingPet(){
        Scanner input = new Scanner(System.in);
        List<File> pets = SearchPet.searchPets();
        if (pets == null || pets.isEmpty()){
            System.out.println("Pet não encontrado");
            return;
        }

        System.out.println("Pets encotrados: ");
        for(int i = 0; i < pets.size(); i++){
            List<String> dados = SearchPet.lerArquivo(pets.get(i));
            String linha = String.format("%d. %s - %s - %s - %s - %s - %s - %s",
                    i + 1,
                    dados.get(0), // nome
                    dados.get(1), // tipo
                    dados.get(2), // sexo
                    dados.get(3), // endereço
                    dados.get(4), // idade
                    dados.get(5), // peso
                    dados.get(6)  // raça
            );
            System.out.println(linha);
        }

        int escolha = -1;
        while(true) {
            System.out.println("Digite o Pet que deseja deletar: ");
            String entrado = input.nextLine();
            try {
                escolha = Integer.parseInt(entrado);
                if (escolha < 1 || escolha > pets.size()) {
                    System.out.println("Número inválido");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido, apenas números");
            }
        }

        File petSelecionado = pets.get(escolha - 1);
        System.out.println("Tem certeza que deseja deletar esse pet ?");
        String opcao = input.nextLine();
        if(opcao.equalsIgnoreCase("sim")){
            if(petSelecionado.delete()){
                System.out.println("Pet deletado com sucesso!");
            } else{
                System.out.println("Falha ao deletar pet");
            }
        } else if (opcao.equalsIgnoreCase("nao")) {
            System.out.println("Pet não será deletado");
        } else{
            System.out.println("Entrada inválida");
        }
    }
}
