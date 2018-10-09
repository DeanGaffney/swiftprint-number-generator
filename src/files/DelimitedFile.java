package files;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DelimitedFile {

	private FileType fileType;
	private String filePath;

	public DelimitedFile() {
		this.fileType = FileType.CSV;
	}

	/**
	 * Generates a delimited file file
	 * @param filePath - the file path to use when creating the tsv file
	 */
	public void generateFile(PrintWriter printWriter, String filePath, String[][] transposedMatrix) throws IOException, NullPointerException{
		for (int i = 0; i < transposedMatrix.length; i++) {
			List<String> values = new ArrayList<String>();
			for (int j = 0; j < transposedMatrix[i].length; j++) {
				values.add(transposedMatrix[i][j]);
			}
			String line = values.stream().collect(Collectors.joining(getFileType().getDelimiter()));
			printWriter.write(line + "\n");
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

}
