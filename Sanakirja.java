import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Sanakirja {

	public static void main(String[] args) {

		String[] suomi = {"kissa", "koira", "hevonen", "auto", "vene"};
		String[] englanti = {"cat", "dog", "horse", "car", "boat"};
		String[] sisalto = new String[suomi.length];
		
		HashMap<String, String> kaannokset = new HashMap<>();
		
		for (int i = 0; i<suomi.length;i++) {
			
			kaannokset.put(suomi[i], englanti[i]);
			
		}
		

		// HashMapin ja iteraattorin harjoittelua videoluennon mukana
		int j = 0;
		Iterator<Entry<String, String>> it = kaannokset.entrySet().iterator();
		while(it.hasNext()) {

			HashMap.Entry<String, String> alkio = (HashMap.Entry<String, String>) it.next();
			// System.out.println(alkio.getKey() + " = " + alkio.getValue());
			
			sisalto[j] = alkio.getKey() + " = " + alkio.getValue();
			System.out.println(sisalto[j]);
			j++;
		}
		
	}

}