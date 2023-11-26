public class ProgramacaoLinear {

    public static void main(String[] args) {
        double[][] tableau = {
                {2, 1, 1, 0, 0, 20},
                {-4, -5, 0, -1, 0, 10},
                {-3, -2, 0, 0, 1, 0}
        };

        resolverProgramacaoLinear(tableau);

        System.out.println("Solucao otima encontrada:");
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
        // Dividir a linha pivot pelo pivô para tornar o pivô igual a 1
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
        // Encontrar a coluna mais negativa na última linha (função objetivo)
        int colunaPivot = 0;
        for (int j = 1; j < tableau[0].length - 1; j++) {
            if (tableau[tableau.length - 1][j] < tableau[tableau.length - 1][colunaPivot]) {
                colunaPivot = j;
            }
        }
        return colunaPivot;
    }

    public static int encontrarLinhaPivot(double[][] tableau, int colunaPivot) {
        // Encontrar a linha pivot usando a regra da razão mínima
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
        // Verificar se existe alguma entrada negativa na última linha (função objetivo)
        for (int j = 0; j < tableau[0].length - 1; j++) {
            if (tableau[tableau.length - 1][j] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void imprimirTableau(double[][] tableau) {
        for (double[] row : tableau) {
            for (double value : row) {
                System.out.printf("%8.2f", value);
            }
            System.out.println();
        }
    }
}
