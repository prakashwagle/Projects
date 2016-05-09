

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartOne {


	public static void main(String[] args) {
		
		BufferedReader br = null;
		try {
			 
			String sCurrentLine;
			
			Map <String, User> map1 = new HashMap<String, User>();
			
			List<User> sortedKeys=new ArrayList<User>();
 
			br = new BufferedReader(new FileReader("userList1.txt"));
 
			while ((sCurrentLine = br.readLine()) != null) {
				User thisUser = new User(sCurrentLine);
				if(map1.containsKey(thisUser.toString())){
					sortedKeys.add(thisUser);
					
				} else {
					map1.put(thisUser.toString(), thisUser);
				}
				
			}
			Collections.sort(sortedKeys);
			
			System.out.println("Repeated Records "+sortedKeys.size());
			for (User entry : sortedKeys) {
			
					System.out.println("Record: "+entry.toString()+"  HashCode"+entry.hashCode());
				
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}	
}
