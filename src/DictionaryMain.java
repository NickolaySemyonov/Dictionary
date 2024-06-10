import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryMain {

	public static void main(String[] args){
		
		File lat = new File("latin.txt");
		File num = new File("number.txt");
		Dictionary latDict = null;
		Dictionary numDict = null;
		Dictionary currentDict = null;
		
		try {
			if(!lat.exists()) lat.createNewFile();
			if(!num.exists()) num.createNewFile();
			latDict  = new Dictionary(lat.getAbsolutePath(), "^[A-Za-z]{1,5}$", "^[А-Яа-я]+$");
			numDict  = new Dictionary(num.getAbsolutePath(),"^[0-9]{1,5}$", "^[А-Яа-я]+$");
			currentDict = latDict;
		} 
		catch (CustomException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		Scanner sc = new Scanner(System.in);
		try {
			while (true) {
				ShowActions();
				String input = sc.next().toLowerCase().trim();
				switch(input) {
					case "1"-> currentDict = latDict;
					case "2"-> currentDict = numDict;
					case "s"-> {
						if(currentDict==latDict)
							System.out.println("---------lat-ru--------");
						if(currentDict==numDict)
							System.out.println("---------num-ru--------");
						currentDict.ShowAllPairs();
						}
					case "a"-> {
						System.out.print("Введите ключ: ");
						String key = sc.next();
						System.out.print("Введите значение: ");
						String value = sc.next();
						currentDict.Put(key, value);
						}
					case "d"->{
						System.out.print("Введите ключ: ");
						String key = sc.next();
						currentDict.Remove(key);
						}
					default-> System.out.println();
				}
			}
		}
		catch (CustomException e) {
			System.out.println(e.getMessage());
		}
		sc.close();
	}
	
	public static void ShowActions() {
		System.out.println("1 - Выбрать словарь №1(латин.)");
		System.out.println("2 - Выбрать словарь №2(цифр.)");
		System.out.println("s - Показать все элементы словаря");
		System.out.println("a - Добавить элемент в словарь");
		System.out.println("d - Удалить элемент из словаря");
	}
	
}
