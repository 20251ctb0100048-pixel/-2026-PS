package ImplementacaonoQuadro;

public class maiorValor {

    static int encontrarMaiorValor(int[] numeros) {
        int maior = numeros[0];

        for (int valor : numeros) {
            if (valor > maior) {
                maior = valor;
            }
        }

        return maior;
    }
}