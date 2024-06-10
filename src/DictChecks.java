import java.util.HashMap;

public class DictChecks {
	
	private String keyRegEx;
	private String valueRegEx;
	
	public DictChecks(String keyConstr, String valConstr) {
		keyRegEx = keyConstr;
		valueRegEx = valConstr;
	}
	
	public boolean DoesMatch(String str, String regEx ) {
		return str.matches(regEx);
	}
	
	public boolean ValidatePair(String key, String value) {
		return DoesMatch(key, keyRegEx) && DoesMatch(value, valueRegEx);
	}
	
	public boolean ValidateDictionary(HashMap<String, String> hm) {
		for (String key: hm.keySet()) {
			if (!ValidatePair(key, hm.get(key))) return false;
		}
		return true;
	}
	
	
}
