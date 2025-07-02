public class GrupoTrabalho {
    private Aluno[] alunos;
    private double nota;

    public GrupoTrabalho(Aluno[] alunos, double nota) {
        this.alunos = alunos;
        this.nota = nota;
    }
    public double getNota(){
        return nota;
    }

    public Aluno[] getAlunos(){
        return alunos;
    }
}
