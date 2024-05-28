import java.util.HashMap;

public class Dictionary {
	
	private HashMap<String, String> dict;
	
	private String KeyRegEx;
	private String ValueRegEx;
	
	public boolean CheckKey(String key) {return key.matches(KeyRegEx);}
	public boolean CheckValue(String value) {return value.matches(ValueRegEx);}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> GetDictionary() {return (HashMap<String, String>) dict.clone();}

	
	public void SetConstraints(String keyConstr, String valConstr) {
		dict.clear();
		KeyRegEx = keyConstr;
		ValueRegEx = valConstr;
	}
	
	public String FindValue(String key) {
		
		if(CheckKey(key) && dict.containsKey(key)) return dict.get(key);
		else {
			System.out.println("Ключ не найден");
			return "";
		}
	}
	
	public void SetValue(String key, String value) {
		
		if (CheckKey(key) || CheckValue(value)) 
			dict.put(key, value); 
		else 
			System.out.println("Введенные ключ и значение не удовлетворяют ограничениям словаря");
	}
	
	public void RemoveItem(String key) {
		
		if (CheckKey(key) && dict.containsKey(key)) dict.remove(key);
		else System.out.println("Ключ не найден");
	}
	
	public void LoadDictionary(HashMap<String, String> hm) {
		
		if (ValidateDictionary(hm)) {
			dict.clear(); 
			dict = hm;
		}
		else System.out.println("Элементы загружаемого словаря не удовлетворяют ограничениям");
	}
	
	private boolean ValidateDictionary(HashMap<String, String> hm) {
		
		for (String key: hm.keySet()) {
			if (!CheckKey(key) || !CheckValue(hm.get(key))) return false;
		}
		return true;
	}
	
	
	
}
