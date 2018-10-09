package utils;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * MatrixUtilsTest for testing the MatrixUtil.java class
 */
public class MatrixUtilsTest {

    private static final int START = 0;
    private static final int END = 10;
    private static final int NUM_OF_COLS = 2;

    @Test
    public void testCreatingMatrix(){
        String [][] transposedMatrix = MatrixUtils.createMatrix(START, END, NUM_OF_COLS);
        assertEquals(transposedMatrix[0].length, NUM_OF_COLS);
        assertEquals(transposedMatrix[1].length, NUM_OF_COLS);
    }
    
    @Test
    public void testMatrixHeaders(){
        String [][] transposedMatrix = MatrixUtils.createMatrix(START, END, NUM_OF_COLS);
        assertEquals(transposedMatrix[0].length, NUM_OF_COLS);
        for(int i = 1; i <= NUM_OF_COLS; i++){
            assertEquals(transposedMatrix[0][i - 1], "Number " + i);
        }
    }
    
}