public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Entrada entrada = new Entrada();
        sistema.carregarDados("dados.txt");

        int opcao;
        do {
            opcao = entrada.menu();
            switch (opcao) {
                case 1:
                    entrada.cadProf(sistema);
                    break;
                case 2:
                    entrada.cadAluno(sistema);
                    break;
                case 3:
                    entrada.cadTurma(sistema);
                    break;
                case 4:
                    sistema.listarTurmas();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}