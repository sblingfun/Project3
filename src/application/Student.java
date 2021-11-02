package application;

import java.text.DecimalFormat;

/**
 * 
 */

/**
 * @author Alex
 * This class defines a student superclass
 */
public class Student {
	private int creditHours;
	private Profile profile;
	private float tuitionDue;
	private float tuitionPaid;
	private Date lastPayment;
	
	
	public static final DecimalFormat df = new DecimalFormat( "0.00" );
	
	/**
	 * This method checks if a student is full time.
	 * @return a boolean true if the the student is full time and false otherwise
	 */
	public boolean isFullTime( Student student) {
		if ( student.creditHours >= ConstantIdentifiers.FULLTIMEHRS ) return true;
		else return false;
	}
	
	/**
	 * This method overrides toString to create a format for student string as (name:major:credithours:tuition due:total payment:last payment date).
	 * @return a String
	 */
	@Override
	public String toString() {
		return String.format( profile + ":" + creditHours + " credit hours:tuition due:" + df.format( tuitionDue ) + ":total payment:"
				+ df.format( tuitionPaid ) + ":last payment date: " + lastPayment );
	}
	
	/**
	 * This default constructor creates a new student object.
	 * @param A string name, an enum Major major, and an integer credithours
	 */
	public Student( String name, Major major, int creditHours ) {
		profile = new Profile( name, major );
		this.creditHours = creditHours;
		this.tuitionDue = 0;
		this.tuitionPaid = 0;
		this.lastPayment = new Date( "00/00/00" );		
	}
	
	/**
	 * This helper method gets the corresponding profile for a student
	 * @return a Profile object identifying the student
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * This blank method allows subclasses to inherit its call and call their own tuition dues
	 * 
	 */
	public void tuitionDue() {
		
	}
	
	/**
	 * This helper method sets the corresponding tuition due for a student
	 * @param a floating value for the tuition due
	 */
	public void setTuitionDue( float tuition ) {
		this.tuitionDue = tuition;
	}
	
	/**
	 * This helper method gets the corresponding last payment for a student
	 * @return a Date object for the lastPayment
	 */
	public Date getLastPayment( ) {
		return this.lastPayment;
	}
	
	/**
	 * This helper method sets the corresponding last payment for a student
	 * @param a Date object for the tuition due
	 */
	public void setLastPayment( Date date ) {
		this.lastPayment = date;
	}
	
	/**
	 * This helper method sets the corresponding tuition due for a student
	 * @param a floating value for the tuition due
	 */
	public float getTuitionDue( ) {
		return this.tuitionDue;
	}
	
	public void setTuitionPaid( float tuition ) {
		this.tuitionPaid = tuition;
	}
	
	public float getTuitionPaid( ) {
		return this.tuitionPaid;
	}
	
	public void setCreditHours( int newHours ) {
		this.creditHours = newHours ;
	}
	
	public int getCreditHours() {
		return this.creditHours;
	}
	
	public static void main(String[] args) {
		Student n = new Student( "Alex", Major.EE, 15);
		System.out.println(n);
	}

}
