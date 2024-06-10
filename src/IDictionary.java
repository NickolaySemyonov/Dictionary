
public interface IDictionary {
	void LoadDict(String filename) throws CustomException;
	void ShowAllPairs();
	void Put(String key, String value) throws CustomException;
	void Remove(String key) throws CustomException;
	String Get(String key) throws CustomException;
}
