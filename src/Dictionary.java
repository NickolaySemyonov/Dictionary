import java.util.HashMap;

public class Dictionary implements IDictionary{
	
	private HashMap<String, String> dictionary = new HashMap<String, String>();
	private DictChecks checks = new DictChecks("^[А-Яа-яA-Za-z0-9_]+$", "^[А-Яа-яA-Za-z0-9_]+$");
	private IDictFileIO io;
	private String filename; 
	
	public Dictionary(String filename) throws CustomException {
		
		this.filename = filename;
		io = switch (filename.substring(filename.lastIndexOf("."))){
		case ".txt"-> new TxtDictIO();
		default -> new TxtDictIO();	
		};
		LoadDict(filename);
	}
	
	public Dictionary(String filename, String keyConstraint, String valueConstraint) throws CustomException {
		
		this.filename = filename;
		io = switch (filename.substring(filename.lastIndexOf("."))){
		case ".txt"-> new TxtDictIO();
		default -> new TxtDictIO();	
		};
		checks = new DictChecks(keyConstraint, valueConstraint);
		LoadDict(filename);
	}
	
	@Override
	public void LoadDict(String filename) throws CustomException {
	
		io.ReadDict(filename);
		dictionary.clear();
		dictionary = io.GetDictAsHashMap();
		if (!checks.ValidateDictionary(dictionary)) 
			CustomExceptions.InvalidContent.throwEx();
	}

	@Override
	public void ShowAllPairs() {
		
		for(var pair: dictionary.entrySet()) System.out.println(pair);
		System.out.println();
	}

	@Override
	public String Get(String key) throws CustomException {
		if (!dictionary.containsKey(key)) CustomExceptions.KeyNotFound.throwEx();
		return dictionary.get(key);
	}

	@Override
	public void Put(String key, String value) throws CustomException {
		
		if (!checks.ValidatePair(key, value)) CustomExceptions.InvalidPair.throwEx();
		dictionary.put(key, value);
		io.WriteDict(filename);	
	}

	@Override
	public void Remove(String key) throws CustomException {
		
		if (dictionary.containsKey(key)) dictionary.remove(key);
		else CustomExceptions.KeyNotFound.throwEx();
	}

}
