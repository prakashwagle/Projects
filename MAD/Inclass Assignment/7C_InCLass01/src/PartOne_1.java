import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class PartOne_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<User> userList = new ArrayList<User>();
		Map<Integer,User> uniqueUser = new HashMap<Integer,User>();
		BufferedReader buff = null;
		String line="";
		
		try
		{
			buff=new BufferedReader(new FileReader("userList1.txt"));
			if(buff!=null)
			{
				while((line=buff.readLine())!=null)
				{
					User user = new User(line);
					if(uniqueUser.containsKey(user.hashCode()))
					{
						userList.add(user);
					}
					else
					{
						uniqueUser.put(user.hashCode(), user);
					}
				}
			}
			
			Collections.sort(userList);
			for(User u:userList)
			{
				System.out.println(u.toString());
			}
			
		}
		catch(IOException e)
		{
			
		}
		

	}

}
