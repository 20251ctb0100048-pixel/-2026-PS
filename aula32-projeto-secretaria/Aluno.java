/*
 * Disciplina: Programacao Orientada a Objetos / Estrutura de Dados
 * Nome: ALCIDES DINIZ VEIGA
 * Data: 2026.08.27
 * Projeto: Projeto Secretaria (Aula 34)
 * Arquivo: Aluno.java
 */

public class Aluno {

    private String nome;
    private String matricula;
    private String curso;
    private String cidade;

    // Construtor
    public Aluno(String nome, String matricula, String curso, String cidade) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.cidade = cidade;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public String getCidade() {
        return cidade;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return matricula + " | "
            + nome + " | "
            + curso + " | "
            + cidade;
    }
}