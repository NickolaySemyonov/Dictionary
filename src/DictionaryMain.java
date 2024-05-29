import java.util.HashMap;

public class DictionaryMain {

	public static void main(String[] args) {
		/*
		 * interface Dictionary
		 * 
		 * abstract class Dictionary
		 * class NumsDict extends Dictionary
		 * class LatinDict extends Dictionary
		 * 
		 * class FileOperations
		 * class DictionaryMain
		 * 
		 * 
		 * 
		 * */
		DataHandler file_opers = new FileOperations();
		file_opers.setFileName("C:\\Users\\nikol\\OneDrive\\Рабочий стол\\dic.txt");
		
		Dictionary dict = new Dictionary();
		dict.SetConstraints("[A-Za-z]+$", "[A-Za-z]+$");
		dict.LoadDictionary(file_opers);
		System.out.println(dict.FindValue("a"));
		
		
	}
	
}
