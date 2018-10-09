package files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.MatrixUtils;

public class DelimitedFileTest {
	
	private DelimitedFile delimitedFile;
	private static final String FILE_PATH = File.separator + "tmp" + File.separator + "test.csv";
	
	@Before
	public void setup(){
		this.delimitedFile = new DelimitedFile();
	}

	@After
	public void tearDown(){
		if(Files.exists(Paths.get(FILE_PATH))){
			try{
				Files.delete(Paths.get(FILE_PATH));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testDelimitedFileCreation(){
		assertEquals(FileType.CSV, this.delimitedFile.getFileType());
	}
	
	@Test
	public void testGenerateFile() throws Exception{
		String [][] transposedMatrix = MatrixUtils.createMatrix(1, 10, 2);
        PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_PATH));
		this.delimitedFile.generateFile(printWriter, FILE_PATH, transposedMatrix);
		assertTrue(Files.exists(Paths.get(FILE_PATH)));
	}
}
