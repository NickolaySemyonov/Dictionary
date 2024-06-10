import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.regex.Pattern;

public class TxtDictIO implements IDictFileIO{

	private HashMap<String, String> txtDictBuff = new HashMap<String, String>();
	
	@Override
	public void ReadDict(String filepath) throws CustomException {
		
		HashMap<String, String> pairs = new HashMap<String, String>();
		final String lineFormat = "^[А-Яа-яA-Za-z0-9_]+:[А-Яа-яA-Za-z0-9_]+$";
		final String extentionFormat = "^[А-Яа-яA-Za-z0-9_]+[.]txt$"; 
		String line;
		
		if (Pattern.matches(extentionFormat, filepath)) CustomExceptions.FileTypeMismatch.throwEx();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			while ((line = br.readLine())!=null) {
				 line = line.trim();
				 if (Pattern.matches(lineFormat, line)) 
					 pairs.put(line.split(":")[0], line.split(":")[1]);
				 else {
					 CustomExceptions.DictPairNotRead.throwEx();
				 }
			}
			br.close();
			SetHashMapBuff(pairs);
		}
		catch (FileNotFoundException e) {
			CustomExceptions.FileNotFound.throwEx();
		}
		catch (IOException e) {
			CustomExceptions.IOFailed.throwEx();
		}
		
	}

	@Override
	public void WriteDict(String filepath) throws CustomException{
		
		final String extentionFormat = "^[\\w]+[.]txt$"; 
		
		if (Pattern.matches(extentionFormat, filepath)) CustomExceptions.FileTypeMismatch.throwEx();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
			var keyset = txtDictBuff.keySet();
			for(String key: keyset) {
				bw.write(key+":"+txtDictBuff.get(key)+"\n");
			}
			bw.close();
		}
		catch (IOException e) {
			CustomExceptions.IOFailed.throwEx();
		}	
	}

	@Override
	public HashMap<String, String> GetDictAsHashMap() {
		return txtDictBuff;
	}
	
	@Override
	public void SetHashMapBuff(HashMap<String, String> dictionary) {
		if (dictionary==null) return;
		ClearBuff();
		txtDictBuff = dictionary;
	}
	
	@Override
	public void ClearBuff() {
		txtDictBuff.clear();
	}

	@Override
	public boolean HasEmptyBuff() {
		return txtDictBuff.isEmpty();
	}

}
