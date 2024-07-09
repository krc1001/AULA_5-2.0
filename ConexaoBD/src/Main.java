public class Main {
    public static void main(String[] args) {
        Cadastro cadastro = new Cadastro();
        boolean executar = true;
        while (executar) {
            cadastro.imprimirMenu();
            String opcao = cadastro.scanner.nextLine();
            switch (opcao) {
                case "1":
                    cadastro.adicionarVarios();
                    break;
                case "2":
                    cadastro.retornarMaioresDeIdade();
                    break;
                case "3":
                    cadastro.sinalizarMenoresDeIdade();
                    break;
                case "4":
                    cadastro.limparBancoDeDados();
                    break;
                case "5":
                    cadastro.deletarClient();
                case "6":
                    cadastro.scanner.close();
                    executar = false;
                    break;
                default:
                    System.out.println("Opção inválida, escolha outra opção.");
                    break;
            }
        }
    }
}