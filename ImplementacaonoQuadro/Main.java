package ImplementacaonoQuadro;

public class Main {

    static int contarAcima(int[] numeros, int limite) {
        int contador = 0;

        for (int valor : numeros) {
            if (valor > limite) {
                contador++;
            }
        }

        return contador;
    }

    public static void main(String[] args) {
        int[] numeros = {8, 3, 10, 5, 12};
        System.out.println(contarAcima(numeros, 6));
    }
}