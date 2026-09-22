package bibliotech;

public class Bibliotecario extends Usuario {
    private String matriculaFuncional;

    public Bibliotecario(String nome, String matricula, String matriculaFuncional) {
        super(nome, matricula);
        this.matriculaFuncional = matriculaFuncional;
    }

    public String getMatriculaFuncional() {
        return matriculaFuncional;
    }

    public boolean consultarAcervo() {
        return true;
    }

    @Override
    public String toString() {
        return "Bibliotecario(a) " + getNome() + " (" + getMatricula()
                + ", funcional " + matriculaFuncional + ")";
    }
}