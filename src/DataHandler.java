import java.util.HashMap;

public interface DataHandler {
	HashMap<String, String> readFile(); 
	void setFileName(String filename);
	void writeFile();
}
