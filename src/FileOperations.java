import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;


import java.io.BufferedReader;
import java.io.File;
public class FileOperations implements DataHandler{
	
	private String filename;
	private HashMap<String,String> dict;
	
	private HashMap<String, String> GetDictFromFile(String filename){
		HashMap<String, String> data = new HashMap<>(); 
        boolean all_paired = true;
        
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] parts = line.split(":", 2); 
                if (parts.length == 2) data.put(parts[0].trim(), parts[1].trim()); 
                else all_paired = false; 
            }
        } 
		catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
		
		if (!data.isEmpty() && all_paired) {dict = data;return data;}
		else {
			//System.out.println("Файл пуст или в нем присутствуют неполные пары ключ-значение");
			data.clear();
			dict = data;
			return data;
		}
	}
	
	private void WriteDictToFile(HashMap<String, String> data, String filename) {
		File file = new File(filename);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (FileWriter writer = new FileWriter(filename)) {
			
			for (HashMap.Entry<String, String> entry : data.entrySet()) {
				writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
	        }
	    } 
		catch (IOException e) {
	        System.out.println("Ошибка при записи в файл: " + e.getMessage());
	    }
	}

	@Override
	public HashMap<String, String> readFile() {
		return GetDictFromFile(filename);
	}

	
	@Override
	public void setFileName(String filename) {
		this.filename = filename;
	}

	@Override
	public void writeFile() {
		WriteDictToFile(dict, filename);
	}

}
