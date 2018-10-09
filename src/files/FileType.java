package files;

public enum FileType {

	CSV("csv", ","), 
	TSV("tsv", "\t");
	
	private String fileType;
	private String delimiter;
	
	private FileType(String fileType, String delimiter){
		this.fileType = fileType;
		this.delimiter = delimiter;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	public String getFileExtension(){
		return "." + this.getFileType();
	}
	
}
