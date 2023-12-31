

import java.util.Scanner;

public class PesquisaOperacional1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o tipo de problema:");
        System.out.println("1. Programa��o Linear");
        

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                resolverProgramacaoLinear(scanner);
                break;
           default:
                System.out.println("Escolha inv�lida.");
        }

        scanner.close();
    }

    public static void resolverProgramacaoLinear(Scanner scanner) {
        System.out.println("Voc� escolheu resolver um problema de Programa��o Linear.");

        System.out.println("Digite os coeficientes da fun��o objetivo (c1 c2): ");
        int c1 = scanner.nextInt();
        int c2 = scanner.nextInt();

        System.out.println("Digite os coeficientes das restri��es (a1 a2 b1 a3 a4 b2): ");
        int a1 = scanner.nextInt();
        int a2 = scanner.nextInt();
        int b1 = scanner.nextInt();
        int a3 = scanner.nextInt();
        int a4 = scanner.nextInt();
        int b2 = scanner.nextInt();

        double[][] tableau = {
                {a1, a2, 1, 0, b1},
                {a3, a4, 0, 1, b2},
                {-c1, -c2, 0, 0, 0}
        };

        resolverProgramacaoLinear(tableau);

        System.out.println("Solu��o �tima encontrada:");
        imprimirTableau(tableau);
    }

    public static void resolverProgramacaoLinear(double[][] tableau) {
        while (!eSolucaoOtima(tableau)) {
            pivotagem(tableau);
        }
    }

    public static void pivotagem(double[][] tableau) {
        int colunaPivot = encontrarColunaPivot(tableau);
        int linhaPivot = encontrarLinhaPivot(tableau, colunaPivot);

        double pivo = tableau[linhaPivot][colunaPivot];

        // Dividir a linha pivot pelo piv� para tornar o piv� igual a 1
        for (int j = 0; j < tableau[0].length; j++) {
            tableau[linhaPivot][j] /= pivo;
        }

        // Tornar todas as outras entradas na coluna pivot igual a zero
        for (int i = 0; i < tableau.length; i++) {
            if (i != linhaPivot) {
                double fator = tableau[i][colunaPivot];
                for (int j = 0; j < tableau[0].length; j++) {
                    tableau[i][j] -= fator * tableau[linhaPivot][j];
                }
            }
        }
    }

    public static int encontrarColunaPivot(double[][] tableau) {
        // Encontrar a coluna mais negativa na �ltima linha (fun��o objetivo)
        int colunaPivot = 0;
        for (int j = 1; j < tableau[0].length - 1; j++) {
            if (tableau[tableau.length - 1][j] < tableau[tableau.length - 1][colunaPivot]) {
                colunaPivot = j;
            }
        }
        return colunaPivot;
    }

    public static int encontrarLinhaPivot(double[][] tableau, int colunaPivot) {
        // Encontrar a linha pivot usando a regra da raz�o m�nima
        int linhaPivot = 0;
        double razaoMinima = Double.MAX_VALUE;

        for (int i = 0; i < tableau.length - 1; i++) {
            if (tableau[i][colunaPivot] > 0) {
                double razao = tableau[i][tableau[0].length - 1] / tableau[i][colunaPivot];
                if (razao < razaoMinima) {
                    razaoMinima = razao;
                    linhaPivot = i;
                }
            }
        }

        return linhaPivot;
    }
        
    public static boolean eSolucaoOtima(double[][] tableau) {
        // �ltima linha do tableau (fun��o objetivo)
        double[] funcaoObjetivo = tableau[tableau.length - 1];

        // Verificar se todos os coeficientes s�o n�o negativos
        for (double coeficiente : funcaoObjetivo) {
            if (coeficiente < 0) {
                return false;  // Ainda n�o � uma solu��o �tima
            }
        }

        return true;  // Todos os coeficientes s�o n�o negativos, � uma solu��o �tima
    }
   
    public static void imprimirTableau(double[][] tableau) {
        System.out.println("Tableau:");

        for (double[] row : tableau) {
            for (double value : row) {
                System.out.printf("%8.2f", value);
            }
            System.out.println();
        }

        System.out.println();
    }
}
    
    
    
 