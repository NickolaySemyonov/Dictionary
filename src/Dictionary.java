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
