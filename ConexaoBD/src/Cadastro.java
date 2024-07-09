import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cadastro {
    Scanner scanner = new Scanner(System.in);
    Clientes clientes = new Clientes();

    void adicionarVarios() {
        clientes.connect();
        clientes.createTable();
        List<Cliente> clientesList = new ArrayList<>();
        while (true) {
            System.out.println("Digite o nome do cliente (ou 'fim' para terminar):");
            String nome = scanner.nextLine();
            if (nome.equalsIgnoreCase("fim")) {
                break;
            }
            System.out.println("Digite a idade do cliente:");
            int idade = Integer.parseInt(scanner.nextLine());
            clientesList.add(new Cliente(nome, idade));
        }
        if (!clientesList.isEmpty()) {
            clientes.adicionarVarios(clientesList);
        }
        clientes.close();
    }

    void retornarMaioresDeIdade() {
        clientes.connect();
        clientes.retornarMaioresDeIdade();
        clientes.close();
    }

    void sinalizarMenoresDeIdade() {
        clientes.connect();
        clientes.sinalizarMenoresDeIdade();
        clientes.close();
    }

    void limparBancoDeDados() {
        clientes.connect();
        clientes.limparBancoDeDados();
        clientes.close();
    }
    void deletarClient(){
        clientes.deleteClient(1);
    }

    void imprimirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1- Adicionar vários clientes");
        System.out.println("2- Retornar maiores de idade");
        System.out.println("3- Sinalizar menores de idade");
        System.out.println("4- Limpar banco de dados");
        System.out.println("5- Deletar cliente");
        System.out.println("6- Sair");
    }
}