import java.util.HashMap;

public class Dictionary implements IDictionary{
	
	private HashMap<String, String> dictionary = new HashMap<String, String>();
	private DictChecks checks = new DictChecks("^[А-Яа-яA-Za-z0-9_]+$", "^[А-Яа-яA-Za-z0-9_]+$");
	private IDictFileIO io;
	private String filename; 
	
	public Dictionary(String filename){
		
		this.filename = filename;
		io = switch (filename.substring(filename.lastIndexOf("."))){
		case ".txt"-> new TxtDictIO();
		default -> new TxtDictIO();	
		};
		
		try {
			LoadDict(filename);
		}
		catch(CustomException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public Dictionary(String filename, String keyConstraint, String valueConstraint) {
		this.filename = filename;
		io = switch (filename.substring(filename.lastIndexOf("."))){
		case ".txt"-> new TxtDictIO();
		default -> new TxtDictIO();	
		};
		checks = new DictChecks(keyConstraint, valueConstraint);
		
		try {
			LoadDict(filename);
		}
		catch(CustomException e) {
			System.out.println(filename);
			System.out.println(e.getMessage());
		}
	}

	public String GetCurrentFileName() {
		return filename;
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
