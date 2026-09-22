package bibliotech;

public class Usuario {
    private String nome;
    private String matricula;

    public Usuario(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean entrar() {
        return true;
    }

    @Override
    public String toString() {
        return "Usuario " + nome + " (" + matricula + ")";
    }
}