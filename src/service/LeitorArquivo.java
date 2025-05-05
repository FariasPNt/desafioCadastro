package src.service;

import java.io.File;
import java.util.List;

public class LeitorArquivo {
    public static void lendoArquivo(List<File> file){
        if(file == null || file.isEmpty()){
            System.out.println("O arquivo está vazio");
            return;
        }

        System.out.println("Lista de Pets: ");
        for(int i = 0; i < file.size(); i++){
            List<String> dados = SearchPet.lerArquivo(file.get(i));
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
    }
}
