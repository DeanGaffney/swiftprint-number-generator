package utils;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils {

	/**
	 * Creates a matrix of all values needed for the delimited file
	 * @return String [][] - a 2d array representing a matrix of all the values required for the delimited file
	 * @throws Exception 
	 */
	public static String[][] createMatrix(int startNum, int endNum, int numOfCols) throws NumberFormatException{
		List<List<String>> matrix = new ArrayList<List<String>>();
		String[][] transposedMatrix = null;
		int decreasingNum = endNum;
		int total = endNum - startNum;
		int currentColumnNumber = 1;
		double itemsPerColumn = Math.ceil((double)(total + 1) / (double)numOfCols);
		List<String> colValues = new ArrayList<String>();
		for(int i = 0; i <= total; i++){
			if(i % itemsPerColumn == 0){
				if(i != 0){
					List<String> temp = new ArrayList<String>(colValues);
					matrix.add(temp);
					colValues.clear();
				}
				colValues.add("Number " + currentColumnNumber++);
			}
			colValues.add(Integer.toString(decreasingNum--));
		}

		if(!colValues.isEmpty())matrix.add(colValues);
		transposedMatrix = transpose(matrix);

		return transposedMatrix;
	}

	/**
	 * Transposes a matrix of values
	 * @param matrix - the matrix to transpose
	 * @return String [][] - the transposed matrix represented as a 2d array
	 * @throws Exception
	 */
	private static String [][] transpose(List<List<String>> matrix){
		String [][] transposedMatrix = new String [matrix.get(0).size()][matrix.size()];
		for(int i = 0; i < matrix.size(); i++){
			for(int j = 0; j < matrix.get(0).size(); j++){
				try{
					transposedMatrix[j][i] = matrix.get(i).get(j);
				}catch(IndexOutOfBoundsException e){
					transposedMatrix[j][i] = "";
				}
			}
		}
		return transposedMatrix;
	}
}
