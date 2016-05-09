


public class User implements Comparable<User>{
	private String firstName, middleInitial, lastName;
	private int age;
	private String city, state;
	
	public User(String line)
	{
		userParse(line);
	}
	
	private void userParse(String line)
	{
		String[] useArray= line.split(",");
		this.firstName=useArray[0];
		this.middleInitial=useArray[1];
		this.lastName=useArray[2];
		this.age=Integer.parseInt(useArray[3]);
		this.city=useArray[4];
		this.state=useArray[5];
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return firstName+","+middleInitial+","+lastName+","+age+","+city+","+state;
	}

	public int compareTo(User userObj) {

		String lastName1 = userObj.getLastName().toUpperCase();

		//ascending order
		return this.lastName.toUpperCase().compareTo(lastName1);

	}
	
	public boolean equals(User u)
	{
	
		if(u.hashCode()==this.hashCode())
			return true;
		else
			return false;
	}
	
	@Override
    public int hashCode() {
		
		int hash;
		hash = this.toString().hashCode();
		return hash;
	}
	

}