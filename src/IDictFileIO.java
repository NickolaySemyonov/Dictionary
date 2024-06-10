import java.util.HashMap;

public interface IDictFileIO {
	void ReadDict(String filepath) throws CustomException;
	void WriteDict(String filepath) throws CustomException;
	void ClearBuff();
	boolean HasEmptyBuff();
	void SetHashMapBuff(HashMap<String, String> dictionary);
	HashMap<String, String> GetDictAsHashMap();
}
