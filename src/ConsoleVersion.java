import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleVersion {
	static final String emptyName = "---------no-dict-------";
	static File lat = new File("latin.txt");
	static File num = new File("number.txt");
	static Dictionary latDict;
	static Dictionary numDict;
	static Dictionary currentDict;
	
	
	public static void main(String[] args) {
		
		ShowNotification();
		PrepareFiles();
		latDict = new Dictionary(lat.getAbsolutePath(), "^[A-Za-z]{1,5}$", "^[А-Яа-я]+$");
		numDict = new Dictionary(num.getAbsolutePath(), "^[0-9]{1,5}$", "^[А-Яа-я]+$");
		currentDict = latDict;
		
		
		Scanner sc = new Scanner(System.in);
		String in="";
		while (true) {
			ShowActions();
			String input = sc.nextLine().toLowerCase().trim();
			switch (input) {
			case "1" -> TryLoadDictionary(latDict);
			case "2" -> TryLoadDictionary(numDict);
			case "s" -> ShowDict();
			case "f" -> {
				do in = TryFindValue(sc);
				while (!in.equals("-"));
			}
			case "a" -> {
				do in = TryAddPair(sc);
				while (!in.equals("-"));
			}
			case "d" -> {
				do in = TryDeletePair(sc);
				while (!in.equals("-"));
			}
			default -> System.out.println();
			}
		}

	}

	
	private static void ShowNotification() {
		System.out.println("Предупреждение! При внесении изменений в файлы\nво время работы программы словарь может быть перезаписан");
	}


	public static void PrepareFiles() {
		try {
			if (!lat.exists()) lat.createNewFile();
			if (!num.exists()) num.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void ShowActions() {
		
		System.out.println((currentDict == null) ? emptyName : currentDict.GetCurrentFileName());
		System.out.println("1 - Выбрать словарь №1(латин.);");
		System.out.println("2 - Выбрать словарь №2(цифр.);");
		System.out.println("s - Показать все элементы словаря;");
		System.out.println("f - Найти значения, соответствующие ключам;");
		System.out.println("a - Добавить(Перезаписать) элементы в словарь;");
		System.out.println("d - Удалить элементы из словаря.");
	}

	public static void ShowDict() {
		TryLoadDictionary(currentDict);
		if (currentDict == latDict)
			System.out.println("---------lat-ru--------");
		else if (currentDict == numDict)
			System.out.println("---------num-ru--------");
		else
			System.out.println(emptyName);
		currentDict.ShowAllPairs();
	}
	
	private static String TryFindValue(Scanner sc) {
		String key = "";
		try {
			System.out.println("Введите ключ или \"-\" для выхода");
			if ((key = sc.nextLine().trim()).equals("-"))
				return key;
			System.err.println(currentDict.Get(key));
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		}
		return key;
	}

	public static void TryLoadDictionary(Dictionary dict) {
		try {
			currentDict = dict;
			currentDict.LoadDict(dict.GetCurrentFileName());
		} catch (CustomException e) {
			System.out.println(dict.GetCurrentFileName());
			System.out.println(e.getMessage());
		}
	}

	public static String TryAddPair(Scanner sc) {
		String input = "";
		String[] pair;
		try {
			System.out.println("Введите пару <ключ>=<значение> или \"-\" для выхода");
			if ((input = sc.nextLine().trim()).equals("-"))
				return input;
			if ((pair = input.split("=", 2)).length != 2)
				CustomExceptions.InvalidPair.throwEx(); 
			else currentDict.Put(pair[0].trim(), pair[1].trim());
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		}
		return input;
	}

	public static String TryDeletePair(Scanner sc) {
		String key = "";
		try {
			System.out.println("Введите ключ или \"-\" для выхода");
			if ((key = sc.nextLine().trim()).equals("-"))
				return key;
			currentDict.Remove(key);
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		}
		return key;
	}

}
