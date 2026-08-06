package ImplementacaonoQuadro;

public class calculaSoma {

    static int calcularSoma(int[] numeros) {
        int soma = 0;

        for (int valor : numeros) {
            soma += valor;
        }

        return soma;
    }
}