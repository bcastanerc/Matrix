import java.util.Arrays;

/**
 * La clase matrix contiene todos los sitemas de operaciones con matrices del documento, esta enlazado al MatrixTest.java
 */
class Matrix {

    /**
     * Comprueba que la matriz que entra por parámetro sea cuadrada
     * @param mat Matriz enviada por parámetro.
     * @return devuelve un boolean false si no es cuadrada.
     */
    private static boolean matrizEsCuadrada(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            if ((mat.length != mat[i].length) || (mat[0].length != mat[i].length)) return false;
        }
        return true;
    }

    /**
     * Comprueba si una Array puede ser considerada una matriz comprobando la longitud de sus filas y columnas.
     * @param mat Matriz enviada por parámetro.
     * @return devuelve boolean false si no los es.
     */
    private static boolean esUnaMatriz(double[][] mat) {
        if (mat.length == 0) return false;
        for (int i = 0; i < mat.length; i++) {
            if ((mat[0].length != mat[i].length) || (mat[i].length == 0)) return false;
        }
        return true;
    }

    /**
     * Comprueba que las Arrays sean compatibles para poder sumarlas tienen que tener la misma cantidad de filas y columnas
     *
     * @param mat1 Matriz enviada por parámetro.
     * @param mat2 Matriz enviada por parámetro.
     * @return devuelve un boolean false si no son del mismo tamaño.
     */
    private static boolean matrizMismoTamano(double[][] mat1, double[][] mat2) {
        for (int i = 0; i < mat1.length; i++) {
            if ((mat1[i].length != mat2[i].length) || (mat1.length != mat2.length)) return false;
        }
        return true;
    }

    /**
     * Crea una Array de tipo double la cual es una copia de la que entra por parametro.
     *
     * @param mat Mat es la matriz de entrada definida por el usuario.
     * @return Devuelve una Array de tipo double.
     */
    private static double[][] arrayCopy(double[][] mat) {

        double[][] copy = new double[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            System.arraycopy(mat[i], 0, copy[i], 0, mat[0].length);
        }
        return copy;
    }

    /**
     * Esta función crea una matriz identidad cuando la llamamos, la va a crear tan grande como necesitemos.
     *
     * @param mat Mat es la matriz de entrada definida por el usuario.
     * @return Devuelve una Matrix identidad dependiendo del tamaño de entrada.
     */
    private static double[][] identityMatrix(double[][] mat) {
        double[][] identityMatrix = new double[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            identityMatrix[i][i] = 1;
        }
        return identityMatrix;
    }

    /**
     * La función "trace" recorre las arrays diagonalmente sumando los valores progresivamente actualizando el total.
     *
     * @param mat Mat es la matriz de entrada definida por el usuario.
     * @return Devuelve la suma de los números en la diagonal de la matriz.
     */
    static double trace(double[][] mat) {

        if (!esUnaMatriz(mat)) return Double.NaN;
        if (!matrizEsCuadrada(mat)) return Double.NaN;

        double total = 0;
        for (int i = mat.length - 1; i >= 0; i--) {
            total += mat[i][i];
        }
        return total;
    }

    /**
     * La función "add" recorre las  3 arrays a la vez sumando las mismas posiciones de cada uno y guardándolo en la nueva array "solución",
     * para que no falle en diferentes test hay que tener en cuenta que las Arrays no sean diferentes de tamaño y tengan más de una posición.
     *
     * @param mat1 Mat1 és la primera matriz de entrada definida por el usuario.
     * @param mat2 Mat2 és la segunda matriz de entrada definida por el usuario.
     * @return la Array resultado de tipo double con los numeros ya sumados.
     */
    static double[][] add(double[][] mat1, double[][] mat2) {

        //Comprueba que ambas arrays sean matrices.
        if (!esUnaMatriz(mat1) || (!esUnaMatriz(mat2))) return null;

        //Comprueba que ambas matrices tengan el mismo tamaño.
        if (!matrizMismoTamano(mat1, mat2)) return null;


        double[][] solucion = new double[mat1.length][mat1[0].length];

        /*Suma los valores de la misma posición en las matrices de entrada, los valores se guardan en la misma posición
        en una matriz de salida.*/
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                solucion[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
        return solucion;
    }

    /**
     * En esta función multiplicamos una matriz por otra, las matrices estan definidas por el usuario.
     *
     * @param mat1 Mat2 és la segunda matriz de entrada definida por el usuario.
     * @param mat2 Mat2 és la segunda matriz de entrada definida por el usuario.
     * @return Devuelve un Array de tipo double
     */
    static double[][] mult(double[][] mat1, double[][] mat2) {

        if ((!esUnaMatriz(mat1)) || (!esUnaMatriz(mat2))) return null;

        int filas1 = mat1.length, columnas1 = mat1[0].length;
        int columnas2 = mat2[0].length;

        /*Comprueba que tengan las filas y columnas compatibles*/
        if (filas1 != columnas2) return null;

        /*Creamos una array de las filas de la 1ra "mat1[0].length"y las columnas "mat2.length" de la 2nda*/
        double[][] solucion = new double[filas1][columnas2];

        for (int i = 0; i < filas1; i++) {
            for (int j = 0; j < columnas2; j++) {
                for (int l = 0; l < columnas1; l++) {
                    solucion[i][j] += (mat1[i][l] * mat2[l][j]);
                    System.out.println( mat1[i][l] + " * " + mat2[i][l] + "=" + solucion[i][j]);
                }
            }
        }
        return solucion;
    }

    /**
     * Eleva una matriz a un exponente. Para hacerlo se multiplica por la original tantas veces como sea el num del exponente
     *
     * @param mat Mat2 és la segunda matriz de entrada definida por el usuario.
     * @param p   P determina el exponente de la función, cuantas veces se va a multiplicar por si mismo.
     * @return devuelve una Array de tipo double.
     */
    static double[][] power(double[][] mat, int p) {

        if (!esUnaMatriz(mat)) return null;
        if (!matrizEsCuadrada(mat)) return null;

        //Si la potencia es 0 devuelve la matriz identidad.
        if (p == 0) {
            return identityMatrix(mat);
        }

        //Si la potencia a la que se eleva es negativa cambiamos el signo a la potecnia e invertimos la matriz.
        if (p < 0) {
            p = p * -1;
            mat = invert(mat);
            if (mat == null) {
                return null;
            }
        }

        double[][] copia = arrayCopy(mat);

        /*Enviamos a la funcion la Array de entrada y una copia de ella para hacer las operaciones,
        La array copia se sobreescribe con la nueva, así no modificamos la array base*/
        for (int exponente = p; exponente > 1; exponente--) {
            copia = mult(mat, copia);
        }
        return copia;
    }

    /**
     * Esta función invierte una matriz de entrada y a continuación la multiplica por otra matiz de entrada.
     *
     * @param mat1 és la matriz de entrada definida por el usuario.
     * @param mat2 és la matriz de entrada definida por el usuario.
     * @return devuelve una Array de tipo double.
     */
    static double[][] div(double[][] mat1, double[][] mat2) {

        if ((!esUnaMatriz(mat1)) || (!esUnaMatriz(mat2))) return null;
        if (!matrizEsCuadrada(mat2) || (mat1[0].length != mat2.length)) return null;

        double[][] matInvert = invert(mat2);
        if (matInvert == null) return null;
        matInvert = mult(mat1, matInvert);
        return matInvert;
    }

    /**
     * Multiplica todas las posiciones de la matriz por n.
     *
     * @param mat és la matriz de entrada definida por el usuario.
     * @param n   és el número por el cual se van a multiplicar todas las posiciones.
     * @return Devuelve un Array bidimensional de doubles.
     */
    static double[][] mult(double[][] mat, double n) {

        if (Double.isInfinite(n)) return null;
        if (!esUnaMatriz(mat)) return null;

        double[][] solucion = new double[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                solucion[i][j] = mat[i][j] * n;
            }
        }
        return solucion;
    }

    /**
     * Selecciona dos posiciones dentro de una matriz, luego trazará una matriz dentro de esos puntos, a si
     * que eliminara las posiciones que no se encuentre dentro.
     *
     * @param mat es la matriz en la cual se hará la selección de los dos puntos.
     * @param x1  primera posición en el eje de las x.
     * @param y1  primera posición en el eje de las y.
     * @param x2  segunda posición en el eje de las x.
     * @param y2  segunda posición en el eje de las y.
     * @return devuelve una matriz de tipo double del tamaño del interiror de los dos puntos.
     */
    static double[][] submatrix(double[][] mat, int x1, int y1, int x2, int y2) {

        if (!esUnaMatriz(mat)) return null;

        // Se comprueba que x1,x2,y1,y2 estén en el rango de la matriz
        if ((x1 < 0) || (y1 < 0) || (x2 < 0) || (y2 < 0)) return null;
        if ((x1 > mat[0].length - 1) || (y1 > mat.length - 1) ||
                (x2 > mat[0].length - 1) || (y2 > mat.length - 1)) return null;

        // Comprobamos si x1 es mayor que x2, si es así los cambiamos
        if (x1 > x2) {
            int tempNum = x1;
            x1 = x2;
            x2 = tempNum;
        }

        // Comprobamos si y1 es mayor que y2, si es así los cambiamos
        if (y1 > y2) {
            int tempNum = y1;
            y1 = y2;
            y2 = tempNum;
        }

        int diferenciaX = x2 - x1 + 1, diferenciaY = y2 - y1 + 1;
        double[][] subMatrix = new double[diferenciaY][diferenciaX];

        for (int i = y1, y = 0; i <= y2; i++, y++) {
            for (int j = x1, x = 0; j <= x2; j++, x++) {
                subMatrix[y][x] = mat[i][j];
            }
        }
        return subMatrix;
    }

    /**
     * Calculamos el determinante de la matriz de entrada sin modificar, una vez tenemos el determinante tenemos que crear una
     * matriz copia, la cual vamos a transponer y calcular el determinante de cada una de sus posiciones.
     * Una vez tenemos el determinante en cada posición multiplicamos la matriz de determinantes por el numero 1/determinantePrincipal
     *
     * @param mat és la matriz de entrada definida por el usuario.
     * @return devuelve una matriz de tipo double.
     */
    static double[][] invert(double[][] mat) {

        if (!esUnaMatriz(mat)) return null;
        if (!matrizEsCuadrada(mat)) return null;

        double[][] matInvertida = new double[mat.length][mat[0].length];
        double[][] matTransposed = transpose(mat);
        double deter = determinant(mat);

        if (deter == 0) {
            return null;
        }

        double determinantePrincipal = 1 / deter;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                double[][] copy = getMinor(matTransposed, i, j);
                /*comprueba si la suma de la posición de i+j es par o inpar, si és par, hará la operación
                con el signo positivo, sí no negativo.*/
                if (((j + i) % 2 == 0) || (determinant(copy) == 0)) {
                    matInvertida[i][j] = (determinantePrincipal * (Math.pow(-1, 0) * determinant(copy)));
                } else {
                    matInvertida[i][j] = (determinantePrincipal * (Math.pow(-1, 1) * determinant(copy)));
                }
            }
        }
        return matInvertida;
    }

    /**
     * Esta función crea una matriz de salida 1x1 inferior a la de entrada, para crear esta matriz usa la posición x e y
     * para seleccionar que coordenadas en paralelo y vertical no se llevarán a la matriz de salida.
     *
     * @param mat Matriz de entrada definida por el usuario.
     * @param x   Declara la posición de la fila que no se cogerá.
     * @param y   Declara la posición de la columna que no se cogerá.
     * @return devuelve una matriz de 1x1 inferior a la de entrada.
     */
    static double[][] getMinor(double[][] mat, int x, int y) {

        if (!esUnaMatriz(mat)) return null;
        if (!matrizEsCuadrada(mat)) return null;
        if ((x < 0) || (y < 0) || (x > mat[0].length) || (y > mat.length)) return null;

        //Crea una array 1x1 inferior a la original
        double[][] minor = new double[mat.length - 1][mat[0].length - 1];
        int filas = 0;
        int columnas = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                /*Cuando i y j coincidan con las posiciones que no queremos no se copiará a la Array final*/
                if ((i != x) && (j != y)) {
                    minor[filas][columnas] = mat[i][j];
                    columnas++;
                    if (columnas == minor.length) {
                        filas++;
                        columnas = 0;
                    }
                }
            }
        }
        return minor;
    }

    /**
     * LLama a la función de getMinor para hacer 1X1 más pequeña la matriz, esta se guarda en copy, una vez tiene la
     * matriz coje el índice de la matriz y lo guarda. Una vez se ha guardado llama otra vez a determinant,
     * dándole la Array que acabamos de obtener la cual es mat pero decrementando la posición.
     * Esto lo va a hacer todas las veces que necesite hasta que la mat copy sea de 2x2 y luego calculará el resultado.
     * El Math.pow(-1,contador) tiene en cuenta cuantas veces se ha hecho esta operación para asignar un signo diferente
     * a cada posición (alterna + y -).
     *
     * @param mat Matriz de entrada definida por el usuario.
     * @return resultado; el cual es la suma de todas las operciones en diagonal multiplicado por el índice
     */
    static double determinant(double[][] mat) {

        double resultado = 0;
        int indiceColumna = 0;

        if (!esUnaMatriz(mat)) return Double.NaN;
        if (!matrizEsCuadrada(mat)) return Double.NaN;
        if (mat.length == 1) return mat[0][0];

        //Comprueba que la matriz es de 2x2
        if (mat.length == 2) {
            return mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
        }

        for (int contador = 0; contador < mat.length; contador++) {
            double[][] copy = getMinor(mat, indiceColumna, contador);
            resultado += (Math.pow(-1, contador) * mat[indiceColumna][contador]) * determinant(copy);
        }
        return resultado;
    }

    /**
     * La funcion traspose lo que hace es reemplazar las filas por las columnas, para esto crearemos una Array de salida,
     * la cual tendra los numero de la Array de entrada transpuesto, es decir, las filas serán las columnas.
     *
     * @param mat Matriz de entrada definida por el usuario.
     * @return devuelve una Array bidimensional de tipo double con los valores trasnpuestos.
     */
    static double[][] transpose(double[][] mat) {

        if (!esUnaMatriz(mat)) return null;
        // Comprueba que el array es menor que 1, si es así se devuelve a sí misma */
        if ((mat.length == 1) && (mat[0].length == 1)) return mat;

        double[][] solucion = new double[mat[0].length][mat.length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                solucion[j][i] = mat[i][j];
            }
        }
        return solucion;
    }

    /**
     * Para comprobar si una Matriz es Orthogonal tenemos que invertirla, si una vez invertida los numeros concuerdan con
     *
     * @param mat Mat es la matriz de entrada definida por el usuario.
     * @return devuelve una variable boolean.
     */
    static boolean isOrtho(double[][] mat) {

        if (!esUnaMatriz(mat)) return false;
        if (!matrizEsCuadrada(mat)) return false;

        double[][] matrizTranspuesta = transpose(mat);
        double[][] matrizInversa = invert(mat);

        return Arrays.deepEquals(matrizInversa, matrizTranspuesta);
    }

    /**
     * Cramer consiste en aislar las incognitas de la ecuacion, pasando esta ecuación en forma de matriz y luego dividiendola
     * en matriz de resultados  y matriz de incognitas, en esa matriz de incognitas tendremos que sustituir cada incognita por
     * su valor de resultado y luego calcular el determinante de esa matriz con los resultados
     *
     * @param mat Mat es la matriz de entrada definida por el usuario.
     * @return devuelve una Array hnidimensional con el valor de "x", "y" y "num"
     */
    static double[] cramer(double[][] mat) {

        if (!esUnaMatriz(mat)) return null;

        // Comprueba que la matriz tenga una columna más que filas.
        if (mat.length + 1 != mat[0].length) return null;

        // Creamos una matriz de una columna inferior.
        double[][] matrizPrincipal = new double[mat.length][mat[0].length - 1];
        double[] matrizIncognitas = new double[mat.length];

        // Crea la matriz de 1 columna inferior.
        for (int j = 0; j < matrizPrincipal.length; j++) {
            System.arraycopy(mat[j], 0, matrizPrincipal[j], 0, matrizPrincipal[0].length);
        }

        double determinantePrincipal = determinant(matrizPrincipal);

        for (int i = 0; i < matrizPrincipal[0].length; i++) {
            //Reestablece la matriz sin las incognitas cambiadas.
            for (int k = 0; k < matrizPrincipal.length; k++) {
                System.arraycopy(mat[k], 0, matrizPrincipal[k], 0, matrizPrincipal[0].length);
            }
            // Cambia las incognitas por el resultado.
            for (int j = 0; j < matrizPrincipal.length; j++) {
                matrizPrincipal[j][i] = mat[j][mat[0].length - 1];
            }
            // Calcula el determinante de la matriz cambiada.
            double determinanteInferior = determinant(matrizPrincipal);
            matrizIncognitas[i] = determinanteInferior / determinantePrincipal;
        }
        return matrizIncognitas;
    }

    // Funció que mostra una matriu per pantalla
    static void printMat(double[][] mat) {
        for (double[] doubles : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%06.2f ", doubles[j]);
            }
            System.out.println();
        }
    }
}
