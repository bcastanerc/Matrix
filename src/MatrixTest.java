import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void add() {
        double[][] mat1, mat2;

        mat1 = new double[][]{ {2} };
        mat2 = new double[][]{ {3} };
        assertArrayEquals(new double[][]{
                {5}
        }, Matrix.add(mat1, mat2));

        mat1 = new double[][]{ {1,2}, {3,4} };
        mat2 = new double[][]{ {5,3}, {2,8} };
        assertArrayEquals(new double[][]{
                {6,5}, {5,12}
        }, Matrix.add(mat1, mat2));

        mat1 = new double[][]{ {1,2,3}, {4,5,6}, {7,8,9} };
        mat2 = new double[][]{ {3,4,7}, {5,9,1}, {9,7,3} };
        assertArrayEquals(new double[][]{
                {4,6,10}, {9,14,7}, {16,15,12}
        }, Matrix.add(mat1, mat2));

        mat1 = new double[][]{ { 5,6,3,7}, {5,2,1,8}, {8,12,36,2}, {4,23,98,11} };
        mat2 = new double[][]{ { 6,3,7,8}, {12,84,3,21}, {99,33,6,24}, {10,15,12,83} };
        assertArrayEquals(new double[][]{
                {11,9,10,15}, {17,86,4,29}, {107,45,42,26}, {14,38,110,94}
        }, Matrix.add(mat1, mat2));

    }

    @Test
    public void trace() {
        double[][] mat1 = { {1,2,3}, {4,5,6}, {7,8,9} };
        assertEquals(15, Matrix.trace(mat1), 0.01);

        mat1 = new double[][]{ { 6,3,7,8}, {12,84,3,21}, {99,33,6,24}, {10,15,12,83} };
        assertEquals(179, Matrix.trace(mat1), 0.01);
    }

    @Test
    public void transpose() {
        double[][] mat1;

        mat1 = new double[][]{ {5} };
        assertArrayEquals(new double[][]{
                {5}
        }, Matrix.transpose(mat1));

        mat1 = new double[][]{ {1,2,3}, {4,5,6}, {7,8,9} };
        assertArrayEquals(new double[][]{
                {1,4,7}, {2,5,8}, {3,6,9}
        }, Matrix.transpose(mat1));

        mat1 = new double[][]{ {9,8,7,6}, {4,6,5,3}, {9,5,4,7}, {4,8,2,0} };
        assertArrayEquals(new double[][]{
                {9,4,9,4}, {8,6,5,8}, {7,5,4,2}, {6,3,7,0}
        }, Matrix.transpose(mat1));
    }

    @Test
    public void mult() {
        double[][] mat1 = { {1,2,3}, {4,5,6}, {7,8,9} };
        double[][] mat2 = { {3,4,7}, {5,9,1}, {9,7,3} };
        assertArrayEquals(new double[][]{
                {40,43,18}, {91,103,51}, {142,163,84}
        }, Matrix.mult(mat1, mat2));

        assertArrayEquals(new double[][]{
                {15,20,35}, {25,45,5}, {45,35,15}
        }, Matrix.mult(mat2, 5));

        mat1 = new double[][]{ { 5,6,3,7}, {5,2,1,8}, {8,12,36,2}, {4,23,98,11} };
        mat2 = new double[][]{ { 6,3,7,8}, {12,84,3,21}, {99,33,6,24}, {10,15,12,83} };
        assertArrayEquals(new double[][]{
                {469,723,155,819}, {233,336,143,770}, {3776,2250,332,1346}, {10112,5343,817,3780}
        }, Matrix.mult(mat1, mat2));
    }

    @Test
    public void power() {
        double[][] mat = { {3,4,7}, {5,9,1}, {9,7,3} };
        assertArrayEquals(new double[][]{
                {294178, 388259, 196952}, {281601,371646,185767}, {369637, 485478,243491}
        }, Matrix.power(mat, 5));

        assertArrayEquals(new double[][] {
                {1,0,0}, {0,1,0}, {0,0,1}
        }, Matrix.power(mat, 0));

        mat = new double[][]{ {1,2}, {3,4} };
        assertArrayEquals(new double[][]{
                {37,54}, {81, 118}
        }, Matrix.power(mat, 3));

        assertArrayEquals(new double[][] {
                {1,0}, {0,1}
        }, Matrix.power(mat, 0));

        mat = new double[][] { {3} };
        assertArrayEquals(new double[][]{ { 9 }}, Matrix.power(mat, 2));
        assertArrayEquals(new double[][]{ { 1 }}, Matrix.power(mat, 0));


    }

    @Test
    public void subMatrix() {
        double[][] mat = {{1, 2, 3, 5}, {4, 5, 6, 8}, {7, 8, 9, 3}};
        assertArrayEquals(new double[][]{
                {2, 3}, {5, 6}, {8, 9}
        }, Matrix.submatrix(mat, 1, 0, 2, 2));

        assertArrayEquals(new double[][]{
                {2, 3}, {5, 6}, {8, 9}
        }, Matrix.submatrix(mat, 1, 0, 2, 2));

        assertArrayEquals(new double[][]{
                {1}
        }, Matrix.submatrix(mat, 0, 0, 0, 0));
    }

    @Test
    public void getMinor() {
        double[][] mat = { {1,2,3}, {4,5,6}, {7,8,9} };
        assertArrayEquals( new double[][]{{5,6}, {8,9}}, Matrix.getMinor(mat, 0, 0));
        assertArrayEquals( new double[][]{{1,3}, {7,9}}, Matrix.getMinor(mat, 1, 1));
        assertArrayEquals( new double[][]{{2,3}, {8,9}}, Matrix.getMinor(mat, 1, 0));
        assertArrayEquals( new double[][]{{4,5}, {7,8}}, Matrix.getMinor(mat, 0, 2));
    }

    @Test
    public void testDeterminant() {
        double[][] mat = { {1,5}, {-6,3}};
        assertEquals(33, Matrix.determinant(mat), 0.001);

        mat = new double[][]{ {1,2,3}, {4,5,6}, {7,2,7} };
        assertEquals(-30, Matrix.determinant(mat), 0.001);

        mat = new double[][]{ {1,2,3}, {4,5,6}, {7,8,9} };
        assertEquals(0, Matrix.determinant(mat), 0.001);

        mat = new double[][]{ { 5,6,3,7}, {5,2,1,8}, {8,12,36,2}, {4,23,98,11} };
        assertEquals(-20848, Matrix.determinant(mat), 0.001);

        mat = new double[][]{ {8} };
        assertEquals(8, Matrix.determinant(mat), 0.001);
    }

    @Test
    public void testInv() {
        double[][] mat = { {1,2,3}, {4,5,6}, {7,2,7} };
        compareArraysDouble(new double[][]{{-0.77, 0.26, 0.1}, {-0.47, 0.47, -0.2}, {0.9, -0.4, 0.1}},
                Matrix.invert(mat), 0.01);

        mat = new double[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        assertNull(Matrix.invert(mat));

        mat = new double[][]{ {1,2,3,3}, {4,5,6,2}, {7,2,7,7}, {1,6,5,2} };
        compareArraysDouble(new double[][]{{-1.55, -0.63, 0.60, 0.84}, {-1.84, -1.10, 0.68, 1.47}, {2.81, 1.78, -1.13, -2.05}, {-0.74, -0.84, 0.47, 0.79}},
                Matrix.invert(mat), 0.01);
    }

    @Test
    public void div() {
        double[][] mat1 = { {13,-22,63}, {4,95,6}, {7,8,9} };
        double[][] mat2 = { {53,47,7}, {5,29,11}, {91,72,3} };
        Matrix.printMat(Matrix.div(mat1, mat2));
        compareArraysDouble(new double[][]{
                {25.00,-6.35,-14.07}, {-11.06,5.90,6.16}, {2.52,-0.41,-1.37}
        }, Matrix.div(mat1, mat2), 0.01);
    }

    @Test
    public void cramer() {
        double[][] mat1 = { {2,1,1,3}, {1,-1,-1,0}, {1,2,1,0} };
        compare(new double[]{1,-2,3}, Matrix.cramer(mat1));

        double[][] mat2 = { {2,-1,6,10}, {-3,4,-5,11}, {8,-7,-9,12} };
        compare(new double[]{10.63, 10.58, -0.11}, Matrix.cramer(mat2));
    }

    @Test
    public void isOrtho() {
        double[][] mat = { {2,1,1}, {1,-1,-1}, {1,2,1} };
        assertFalse(Matrix.isOrtho(mat));

        mat = new double[][]{ {1,0,0}, {0,1,0}, {0,0,1}};
        assertTrue(Matrix.isOrtho(mat));
    }

    private void compareArraysDouble(double[][] doubles, double[][] invert, double v) {
        for (int i = 0; i < doubles.length; i++) {
            for (int j = 0; j < doubles.length; j++) {
                assertEquals(doubles[i][j], invert[i][j], v);
            }
        }
    }

    private void compare(double[] m1, double[] m2) {
        if (m1.length != m2.length) {
            assertEquals(true, false);
        }

        for (int i = 0; i < m1.length; i++) {
            assertEquals(m1[i], m2[i], 0.01);
        }
    }

}