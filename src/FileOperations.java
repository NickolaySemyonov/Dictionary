import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
public class FileOperations {
	
	String filename;
	
	
	public HashMap<String, String> GetDictFromFile(String filename){
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
		
		if (!data.isEmpty() && all_paired) return data;
		else {
			System.out.println("Файл пуст или в нем присутствуют неполные пары ключ-значение");
			data.clear();
			return data;
		}
	}
	
	public void WriteDictToFile(HashMap<String, String> data, String filename) {
		
		try (FileWriter writer = new FileWriter(filename)) {
			for (HashMap.Entry<String, String> entry : data.entrySet()) {
				writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
	        }
	    } 
		catch (IOException e) {
	        System.out.println("Ошибка при записи в файл: " + e.getMessage());
	    }
	}

	
	
}
