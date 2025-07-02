import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ArrayList;

public class Sistema{
    private ArrayList<Professor> professores;
    private ArrayList<Aluno> alunos;
    private ArrayList<Turma> turmas;

    public Sistema(){
        professores = new ArrayList<>();
        alunos = new ArrayList<>();
        turmas = new ArrayList<>();
    }

    public void novoProf(Professor p) {
        professores.add(p);
    }

    public void novoAluno(Aluno a) {
        alunos.add(a);
    }

    public void novaTurma(Turma t) {
        turmas.add(t);
    }

    public Professor encontrarProfessor(String cpf) {
        for (Professor p : this.professores) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Professor> getProfessores(){
        return professores;
    }

    public ArrayList<Aluno> getAlunos(){
        return alunos;
    }

    public ArrayList<Turma> getTurma(){
        return turmas;
    }

    // .......................................................

    public void listarTurmas(){
        turmas.sort(null);
        
        for (Turma t : turmas) {
            System.out.println("Media da Turma " + t.getNome() + " (" + t.getAno() + "/" + t.getSem() + "):");

            t.ordenarAlunos();
            double somaNotas = 0;

            for (Aluno a : t.getAlunos()) {
                double notaFinal = t.mediaFinal(a);
                System.out.printf("%s: %.1f\n", a.toString(), notaFinal);
                somaNotas += notaFinal;
            }

            double mediaTurma = t.getAlunos().isEmpty() ? 0 : somaNotas / t.getAlunos().size();
            System.out.printf("Media da turma: %.2f\n", mediaTurma);
        }
    }

    // metodo para carregar dados

    public void carregarDados(String caminho){
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha;

            while ((linha = br.readLine()) != null){
                switch (linha){
                    case 'PROF':
                        String nomeProf = br.readLine();
                        String cpfProf = br.readLine();
                        double salario = Double.parseDouble(br.readLine());
                        professores.add(new Professor(nomeProf, cpfProf, salario));
                        break;

                    case 'ALU':
                        String nomeAlu = br.readLine();
                        String cpfAlu = br.readLine();
                        String matricula = br.readLine();
                        alunos.add(new Aluno(nomeAlu, cpfAlu, matricula));
                        break;
                    
                    case 'TUR':
                        Turma turma = lerTurma(br);
                        turmas.add(turma);
                        break;
                    
                    case 'FIM':
                        return;
                }
            }
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    //metodo para ler turma
    private Turma lerTurma(BufferedReader br) throws IOException {
        String nome = br.readLine();
        int ano = Integer.parseInt(br.readLine());
        int sem = Integer.parseInt(br.readLine());
        String cpfProf = br.readLine();
        
        Professor prof = encontrarProfessor(cpfProf);

        int qtdAlunos = Integer.parseInt(br.readLine());
        ArrayList<Aluno> alunosTurma = new ArrayList<>();
        for (int i = 0; i <qtdAlunos; i++){
            String matricula = br.readLine();
            Aluno aluno = encontrarAluno(matricula);
            if (aluno != null) {
                alunosTurma.add(aluno);
            }
        }

        Turma turma = new Turma(nome, ano, sem, prof, alunosTurma);
        
        int qtdAvs = Integer.parseInt(br.readLine());
        for (int i = 0; i < qtdAvs; i++) {
            String tipo = br.readLine();
            if (tipo.equals('PROV')){
                turma.adicionarAvaliacao(lerProva(br, alunosTurma));
            } else if (tipo.equals('TRAB')) {
                turma.adicionarAvaliacao(lerTrabalho(br, alunosTurma));
            }
        }
        return turma;
    }

    //metodo para ler prova
    private Prova lerProva(BufferedReader br, ArrayList<Aluno> alunos) throws IOException {
        String nome = br.readLine();
        int dia = Integer.parseInt(br.readLine());
        int mes = Integer.parseInt(br.readLine());
        int ano = Integer.parseInt(br.readLine());
        double valor = Double.parseDouble(br.readLine());

        int nQuestoes = Integer.parseInt(br.readLine());
        AlunoProva[] notas = new AlunoProva[alunos.size()];

        for (int i = 0; i < alunos.size(); i++){
            double[] notasAluno = new double[nQuestoes];
            for (int j = 0; j < nQuestoes; j++) {
                notasAluno[j] = Double.parseDouble(br.readLine());
            }
            notas[i] = new AlunoProva(alunos.get(i), notasAluno);
        }
        return new Prova(nome, new Data(dia, mes, ano), valor, nQuestoes, notas);
    }


    //metoddo para ler trabalho
    private Trabalho lerTrabalho(BufferedReader br, ArrayList<Aluno> alunos) throws IOException {
        String nome = br.readLine();
        int dia = Integer.parseInt(br.readLine());
        int mes = Integer.parseInt(br.readLine());
        int ano = Integer.parseInt(br.readLine());
        double valor = Double.parseDouble(br.readLine());

        int maxIntegrantes = integer.parseInt(br.readLine());
        int numGrupos = Integer.parseInt(br.readLine());
        GrupoTrabalho[] grupos = new GrupoTrabalho[numGrupos];


        for (int i = 0; i < numGrupos; i++) {
            int qtd = integer.parseInt(br.readLine());
            Aluno[] grupo = nw Aluno[qtd];
            for (int j = 0; j < qtd; j++) {
                String matricula = br.readLine();
                grupo[j] = encontrarAluno(matricula);
            }
            double nota = Double.parseDouble(br.readLine());
            grupos[i] = new GrupoTrabalho(grupo, nota);
        }
        return new Trabalho(nome, new Data(dia, mes, ano), valor, maxIntegrantes, grupos);
    }
}
