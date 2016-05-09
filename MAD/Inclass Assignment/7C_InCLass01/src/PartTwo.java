

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartTwo implements Comparator<User> {


	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedReader brr = null;
		try {
			 
			String sCurrentLine;
			
			Map <String, User> map1 = new HashMap<String, User>();
			
			Map <String, User> map2 = new HashMap<String, User>();
			
			Map <String,User> map3 =new HashMap<String, User>();
			List<User> sortedKeys=new ArrayList<User>();
			
			br = new BufferedReader(new FileReader("userList1.txt"));
 
			while ((sCurrentLine = br.readLine()) != null) {
				User thisUser = new User(sCurrentLine);
			
				
				
					map1.put(thisUser.toString(), thisUser);
			
			}
			
			
			brr = new BufferedReader(new FileReader("userList2.txt"));
			 
			while ((sCurrentLine = brr.readLine()) != null) {
				User thisUser = new User(sCurrentLine);
				if(map1.containsKey(thisUser.toString())){
					
					sortedKeys.add(thisUser);
				} 
			}
			
			
			
	//		List<User> sortedKeys2=new ArrayList<User>(map3.values());
			Collections.sort(sortedKeys,new PartTwo());
			
			System.out.println("Repeated Records in Ascending Order of Age"+ "Size: "+sortedKeys.size());
		
		
			for (User entry : sortedKeys) {
			
					System.out.println("Record: "+entry.toString());
				
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

	@Override
	public int compare(User o1, User o2) {
		if(o1.getAge()>o2.getAge())
			return 1;
		else if(o1.getAge()<o2.getAge())
		return -1;
		
		else
			return 0;
	}	
}
