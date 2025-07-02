public class Trabalho extends Avaliacao {
    private int nIntegrantes;
    private GrupoTrabalho[] grupos;

    public Trabalho(String nome, Data dtAplic, double valor, int nIntegrantes, GrupoTrabalho[] grupos) {
        super(nome, dtAplic, valor);
        this.nIntegrantes = nIntegrantes;
        this.grupos = grupos;
    }

    @Override
    public double nota(String cpf) {
        for (GrupoTrabalho g : grupos) {
            for (Aluno a : g.getAlunos()) {
                if (a.getCpf().equals(cpf)) {
                    return g.getNota();
                }
            }
        }
        return 0;
    }

    public int getNItegrantes(){
        return nIntegrantes;
    }

    public GrupoTrabalho[] getGrupos(){
        return grupos;
    }
}