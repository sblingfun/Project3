package application;
/**
 * 
 */

/**
 * This class defines the Profile class used to identify students.
 * @author Alex 
 */
public class Profile {
	private String name;
	private Major major;
	
	/**
	 * This method overrides the toString method.
	 * @return a string in the format name:major.
	 */
	@Override
	public String toString() {
		return String.format(name + ":" + major );
	}
	
	/**
	 * This method overrides the equals method.
	 * @return a boolean true if the two profiles are the same and false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Profile)) {
			return false;
		}
		
		Profile p = (Profile) o;
		 return this.name.equals( p.name ) && this.major.equals( p.major );
	}
	
	/**
	 * This parameterized constructor takes input in the form (name,major).
	 * @param string name, and a enum Major major
	 */
	public Profile( String name, Major major ) {
		this.major = major;
		this.name = name;		
	}
	
	/**
	 * public getter method for getting the name of the object.
	 * @return string name
	 */
	public String getName() {
		return name;
	}
	
}
