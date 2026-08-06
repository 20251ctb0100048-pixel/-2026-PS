package ImplementacaonoQuadro;

public class menorValor {

    static int encontrarMenorValor(int[] numeros) {
        int menor = numeros[0];

        for (int valor : numeros) {
            if (valor < menor) {
                menor = valor;
            }
        }

        return menor;
    }
}