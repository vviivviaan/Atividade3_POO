import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    public Scanner input;

    public Entrada() {
        try {
            this.input = new Scanner(new FileInputStream("input.txt"));
        } catch (FileNotFoundException e) {

            this.input = new Scanner(System.in);
        }
    }

    private String lerLinha(String msg) {
        System.out.print(msg);
        String linha = this.input.nextLine();

        while (linha.charAt(0) == '#') {
            linha = this.input.nextLine();
        }
        return linha;
    }

    private int lerInteiro(String msg) {
        while (true){
            try{
                String linha = this.lerLinha(msg);
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Insira um numero inteiro.");
            }
        }
    }

    private double lerDouble(String msg) {
        while (true) {
            try {
                String linha = this.lerLinha(msg);
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Insira um numero valido.");
            }
        }
    }

    public int menu() {

        String msg = "*********************\n" +
                "Escolha uma opcao:\n" +
                "1) Cadastrar professor:\n" +
                "2) Cadastrar aluno:\n" +
                "3) Cadastrar turma:\n" +
                "4) Listar turmas:\n" +
                "0) Sair\n";

        int op = this.lerInteiro(msg);

        while (op < 0 || op > 4) {
            System.out.println("Opção inválida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        return op;
    }


    public void cadProf(Sistema s) {
        System.out.println("\nCadastro de Professor:");
        String nome = this.lerLinha("Digite o nome do professor: ");
        String cpf = this.lerLinha("Digite o cpf do professor: ");
        double salario = this.lerDouble("Digite o salário do professor: R$");

        if (s.encontrarProfessor(cpf) == null) {
            Professor p = new Professor(nome, cpf, salario);
            s.novoProf(p);
            System.out.println("Professor cadastrado!");
        }
        else {
            System.out.println("Erro: CPF duplicado. Professor não adicionado.");
        }
    }

    public void cadAluno(Sistema s) {
        System.out.println("\nCadastro de Aluno:");
        String nome = this.lerLinha("Digite o nome do aluno: ");
        String cpf = this.lerLinha("Digite o cpf do aluno: ");
        String matricula = this.lerLinha("Digite a matrícula do aluno: ");

        if (s.encontrarAluno(matricula) == null) {
            Aluno a = new Aluno(nome, cpf, matricula);
            s.novoAluno(a);
            System.out.println("Aluno cadastrado!");
        }
        else {
            System.out.println("Erro: Matrícula duplicada. Aluno não adicionado.");
        }
    }

    public void cadTurma(Sistema s) {
    System.out.println("\nCadastro de Turma:");
    String nome = this.lerLinha("Nome da turma: ");
    int ano = this.lerInteiro("Ano da turma: ");
    int sem = this.lerInteiro("Semestre: ");
    String cpfProf = this.lerLinha("CPF do professor responsável: ");

    Professor prof = s.encontrarProfessor(cpfProf);
    if (prof == null) {
        System.out.println("Erro: professor não encontrado.");
        return;
    }

    int qtd = this.lerInteiro("Quantos alunos na turma? ");
    ArrayList<Aluno> alunos = new ArrayList<>();
    for (int i = 0; i < qtd; i++) {
        String mat = this.lerLinha("Matrícula do aluno " + (i + 1) + ": ");
        Aluno a = s.encontrarAluno(mat);
        if (a != null) {
            alunos.add(a);
        } else {
            System.out.println("Aluno não encontrado. Ignorado.");
        }
    }

    Turma t = new Turma(nome, ano, sem, prof, alunos);
    s.novaTurma(t);
    System.out.println("Turma cadastrada com sucesso!");
    }

}
