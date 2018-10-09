package files;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileTypeTest {

    @Test
    public void testFileTypeExt(){
        assertEquals(FileType.CSV.getFileExtension(), ".csv");
        assertEquals(FileType.TSV.getFileExtension(), ".tsv");
    }
    
    @Test
    public void testFileTypeDelimiter(){
        assertEquals(FileType.CSV.getDelimiter(), ",");
        assertEquals(FileType.TSV.getDelimiter(), "\t");
    }
    
}