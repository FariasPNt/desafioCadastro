package src.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.*;

public class SearchPet {
    private static final Scanner input = new Scanner(System.in);

    public static List<File> searchPets() {
        File directory = new File("petsCadastrados");

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Nenhum pet cadastrado ainda.");
            return Collections.emptyList();
        }

        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("Nenhum pet cadastrado ainda.");
            return Collections.emptyList();
        }

        // Primeiro: escolher tipo de animal
        System.out.print("Escolha o tipo do animal (Cachorro ou Gato): ");
        String tipoAnimal = normalizeString(input.nextLine());

        // Escolher 1 ou 2 critérios adicionais
        System.out.println("\nSelecione os critérios de busca (até 2):");
        System.out.println("1 - Nome ou Sobrenome");
        System.out.println("2 - Sexo");
        System.out.println("3 - Idade");
        System.out.println("4 - Peso");
        System.out.println("5 - Raça");
        System.out.println("6 - Endereço");

        List<Integer> criteriosEscolhidos = new ArrayList<>();
        while (criteriosEscolhidos.size() < 2) {
            System.out.print("Digite o número do critério ou 0 para continuar: ");
            int criterio = Integer.parseInt(input.nextLine());
            if (criterio == 0) break;
            if (criterio >= 1 && criterio <= 6 && !criteriosEscolhidos.contains(criterio)) {
                criteriosEscolhidos.add(criterio);
            } else {
                System.out.println("Critério inválido ou já selecionado.");
            }
        }

        List<String> termosBusca = new ArrayList<>();
        for (int criterio : criteriosEscolhidos) {
            System.out.print("Digite o valor para o critério " + criterioParaTexto(criterio) + ": ");
            String termo = input.nextLine();
            termosBusca.add(termo);
        }

        List<String> resultados = new ArrayList<>();
        List<File> arquivosFiltrados = new ArrayList<>();

        int contador = 1;
        for (File file : files) {
            List<String> dados = lerArquivo(file);

            if (dados.size() < 7) continue; // dados incompletos

            String tipo = normalizeString(dados.get(1));
            if (!tipo.equals(tipoAnimal)) continue;

            boolean corresponde = true;
            for (int i = 0; i < criteriosEscolhidos.size(); i++) {
                int campo = mapearCampoArquivo(criteriosEscolhidos.get(i));
                String valorArquivo = normalizeString(dados.get(campo));
                String termoBusca = normalizeString(termosBusca.get(i));

                if (!valorArquivo.contains(termoBusca)) {
                    corresponde = false;
                    break;
                }
            }

            if (corresponde) {
                arquivosFiltrados.add(file);
            }
        }
        return arquivosFiltrados;
    }

    public static List<String> lerArquivo(File file) {
        List<String> dados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                dados.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + file.getName());
        }
        return dados;
    }

    private static String normalizeString(String input) {
        String normalized = Normalizer.normalize(input.toLowerCase(Locale.ROOT), Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\p{ASCII}]", "");
    }

    private static int mapearCampoArquivo(int criterio) {
        return switch (criterio) {
            case 1 -> 0; // Nome
            case 2 -> 2; // Sexo
            case 3 -> 4; // Idade
            case 4 -> 5; // Peso
            case 5 -> 6; // Raça
            case 6 -> 3; // Endereço
            default -> -1;
        };
    }

    private static String criterioParaTexto(int criterio) {
        return switch (criterio) {
            case 1 -> "Nome ou Sobrenome";
            case 2 -> "Sexo";
            case 3 -> "Idade";
            case 4 -> "Peso";
            case 5 -> "Raça";
            case 6 -> "Endereço";
            default -> "Desconhecido";
        };
    }
}
