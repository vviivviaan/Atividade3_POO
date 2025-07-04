import java.util.ArrayList;

public class Turma implements Comparable<Turma> {
    private String nome;
    private int ano;
    private int sem;
    private Professor prof;
    private ArrayList<Aluno> alunos;
    private ArrayList<Avaliacao> avs;

    public Turma(String nome, int ano, int sem, Professor prof, ArrayList<Aluno> alunos) {
        this.nome = nome;
        this.ano = ano;
        this.sem = sem;
        this.prof = prof;
        this.alunos = alunos;
        this.avs = new ArrayList<>();
    }

    public String getNome(){
        return nome;
    }

    public int getAno(){
        return ano;
    }

    public int getSem(){
        return sem;
    }

    public Professor getProf(){
        return prof;
    }

    public ArrayList<Aluno> getAlunos(){
        return alunos;
    }

    public ArrayList<Avaliacao> getAvs(){
        return avs;
    }

    public void adicionarAvaliacao(Avaliacao av) {
        avs.add(av);
    }

    //usado para comparar as notas 
    @Override
    public int compareTo(Turma outra){
        if (this.ano != outra.ano){
            return outra.ano - this.ano;
        } 
        if (this.sem != outra.sem){
            return outra.sem - this.sem;
        }
         

        int compNome = this.nome.compareTo(outra.nome);
        if (compNome != 0) return compNome;

        return this.prof.getNome().compareTo(outra.prof.getNome());
    }

    //calcula media final do alno
    public double mediaFinal(Aluno a){
        double total = 0;
        for (Avaliacao av : avs) {
            total += av.nota(a.getCpf());
        }
        return total;
    }

    //ordena os alunos por nota
    public void ordenarAlunos(){
        alunos.sort((a1, a2) -> {
            double nota1 = mediaFinal(a1);
            double nota2 = mediaFinal(a2);

            if (nota1 != nota2) return Double.compare(nota2, nota1);

            int nomeComp = a1.getNome().compareTo(a2.getNome());
            if (nomeComp != 0) return nomeComp;

            return a1.getMatricula().compareTo(a2.getMatricula());
        });
    }
}