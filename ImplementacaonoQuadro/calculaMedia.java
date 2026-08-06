package ImplementacaonoQuadro;

public class calculaMedia {

    static int calcularMedia(int[] numeros) {
        int soma = 0;

        for (int valor : numeros) {
            soma += valor;
        }

        return soma / numeros.length;
    }
}