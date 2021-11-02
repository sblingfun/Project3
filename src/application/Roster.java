package application;

import application.ConstantIdentifiers;

/**
 * 
 */

/**
 * @author Alex
 * This class defines a roster class, an array of students used to hold students of various types
 */
public class Roster {
	private Student[] roster;
	private int size;
	
	/**
	 * This default constructor creates a default Roster with no students and arraysize 4.
	 */
	public Roster() {
		roster = new Student[ ConstantIdentifiers.CAPACITY ];
		size = ConstantIdentifiers.EMPTY;
	}
	
		
	/**
	 * This method checks if the given student is in the roster.
	 * @param an Student object
	 * @return returns array index if object exists, -1 if it doesnt.
	 */
	private int find( Student student ) {
		for ( int count = 0; count < size; count++ ) {
			Profile test = roster[ count ].getProfile();
			if ( test.equals( student.getProfile() ) ) return count;
		}
		return -1;
	}
	
	
	/**
	 * This helper method grows the array if it is too small.
	 * 
	 */
	private void grow() {
		Student[] biggerRoster = new Student[ roster.length + ConstantIdentifiers.CAPACITY ];
		for ( int count = 0; count < size; count++ ) {
			biggerRoster[ count ] = roster[ count ];
		}
		roster = biggerRoster;
	}
	
	/**
	 * This method adds the given Student to its collection.
	 * @param an Student object
	 * @return returns true if the Student was added, false if it wasnt.
	 */
	public void add(Student student) throws IllegalArgumentException {
		if ( this.find( student ) != -1 ) {
			throw new IllegalArgumentException( "A student by this name already exists." );
		}
		else if ( roster.length < size + 1 ) {
			this.grow();
		}
		roster[ size ] = student;
		size = size + 1;
	}
	
	/**
	 * This method removes a student from the roster.
	 * @param an Student object
	 * @return returns true if the Student was removed, false if it wasnt.
	 */
	public void remove( Student student ) throws IllegalArgumentException {
		if ( this.find( student ) == -1 ) {
			throw new IllegalArgumentException( "Student is not in roster." );
		}
		else {
			for ( int count = this.find( student ); count < size - 1; count++ ) {
				roster[ count ] = roster[ count + 1 ];
			}
			size = size - 1;
		}
	}
	

	
	/**
	 * This method calculates all tuition dues for all Students on the roster.
	 */
	public void calcTuition() {
		for ( int count = 0; count < size; count++ ) {
			roster[ count ].tuitionDue();
		}
		System.out.println( "Calculation completed." );
	}
	
	/**
	 * This method calculates the tuition dues for a specific Student on the roster.
	 */
	public float calcTempTuition( Student student ) {
		student.tuitionDue();
		return student.getTuitionDue();		
		//System.out.println( "Calculation completed." );
	}
	
	public void isValidCreditHrs( int creditHrs ) throws IllegalArgumentException {
		if ( creditHrs < ConstantIdentifiers.EMPTY ) {
			throw new IllegalArgumentException( "Credit hours cannot be negative." );
			//System.out.println( "Credit hours cannot be negative." );
 		}		     			
 		else if ( creditHrs < ConstantIdentifiers.MINCREDITHR ) {
 			throw new IllegalArgumentException( "Minimum credit hours is 3." );
 		}		     			
 		else if ( creditHrs > ConstantIdentifiers.MAXCREDITHR ) {
 			throw new IllegalArgumentException( "Credit hours exceed the maximum 24." );
 		}
 	}
	
	/**
	 * This helper method swaps to locations in the array.
	 * 
	 */
	private void swap( int x, int y ) {
		Student temp = roster[ x ];
		roster[ x ] = roster[ y ];
		roster[ y ] = temp;
	}
	
	/**
	 * This helper method sorts the roster in order by name.
	 * 
	 */
	public void sortByName() {
		for ( int counter1 = 0; counter1 < size - 1; counter1++ ) {
			for ( int counter2 = 0; counter2 < size - counter1 - 1; counter2++ ) {
				if ( roster[ counter2 ].getProfile().getName().compareToIgnoreCase( roster[ counter2 + 1 ].getProfile().getName() ) > 0 ) {
					swap( counter2 + 1, counter2 );
				}
			}
		}
	}
	
	/**
	 * This helper method sorts the roster in order by date paid.
	 * 
	 */
	private void sortByDate() {
		for ( int counter1 = 0; counter1 < size - 1; counter1++ ) {
			for ( int counter2 = 0; counter2 < size - counter1 - 1; counter2++ ) {
				if ( roster[ counter2 ].getLastPayment().compareTo( roster[ counter2 + 1 ].getLastPayment() ) > 0 ) {
					swap( counter2 + 1, counter2 );
				}
			}
		}
	}
	
	/**
	 * This helper checks if the roster is empty.
	 * 
	 */
	private boolean isEmpty() {
		if ( this.size == ConstantIdentifiers.EMPTY ) return true;		
		else return false;
	}

	/**
	 * This method prints all of the students in the array to console in their current order.
	 * 
	 */
	public String print() {
		String output;
		if ( this.isEmpty() ) {
			output = "Student roster is empty!\n";
		}
		else {
			output = "* list of students in the roster. **\n";
			for ( int count = 0; count < size; count++ ) {
				 output = output + roster [ count ] + "\n";
			}
			output = output + "* end of roster **\n";
		}
		return output;
	}
	
	/**
	 * This method sorts the students by name then prints the output to console.
	 * 
	 */
	public String printByName() {
		String output;
		if ( this.isEmpty() ) {
			output = "Student roster is empty!\n";
		}
		else {
			output = "* list of students ordered by name. **\n";
			this.sortByName();
			for ( int count = 0; count < size; count++ ) {
				 output = output + roster [ count ] + "\n";
			}
			output = output + "* end of roster **\n";
		}
		return output;
	}
	
	/**
	 * This method makes a payment for a student in the form (payment, student).
	 * @param a floating value for payment and a Student object
	 * @exception Throws illegal argument exception
	 */
	public void makePayment ( float payment, Student student, Date date ) throws IllegalArgumentException {
		if ( this.find( student ) == -1 ) {
			throw new IllegalArgumentException( "Student is not in roster." );
		}
		else if ( !date.isValid() ) {
			throw new IllegalArgumentException( "Invalid date." );
		}
		else {
			this.isValidPayment( payment, student );
			roster[ this.find( student ) ].setTuitionPaid( payment );
			if ( roster[ this.find( student ) ].getLastPayment().compareTo( date ) < 0 ) { //roster[ this.find( student ) ].getLastPayment().isValid() 
				roster[ this.find( student ) ].setLastPayment( date );
			}
			//System.out.println( "Payment Applied.");
		}
	}
	
	/**
	 * This method checks if the payment value for a student is valid in the form (payment, student).
	 * @param a floating value for payment and a Student object
	 * @exception Throws illegal argument exception
	 */
	public void isValidPayment ( float payment, Student student ) throws IllegalArgumentException {
		if ( payment <= ConstantIdentifiers.NONE ) {
			throw new IllegalArgumentException( "Payment must be greater than 0." );
		}		
		else if ( payment > ( roster[ this.find( student ) ].getTuitionDue() - roster[ this.find( student ) ].getTuitionPaid() ) ) {
			throw new IllegalArgumentException( "Amount is greater than amount due." );
		}
	}

	
	/**
	 * This method sorts the array in order of date paid and then prints out those who have paid.
	 */
	public String printByPaymentDate() {
		String output;
		boolean paymentMade = false;
		if ( this.isEmpty() ) {
			output = "Student roster is empty!\n";
		}
		for ( int count = 0; count < size; count++ ) {
			if ( roster [ count ].getLastPayment().compareTo( ConstantIdentifiers.FIRSTVALIDDATE ) >= 0 ) {
				paymentMade = true;
				break;
			}
		}
		
		if ( !paymentMade ) {
			output = "No students have made payments.\n";
		}
		else {
			this.sortByDate();
			output = "* list of students made payments ordered by payment date. **\n";
			for ( int count = 0; count < size; count++ ) {
				if ( roster [ count ].getLastPayment().compareTo( ConstantIdentifiers.FIRSTVALIDDATE ) >= 0 ) {
					output = output + roster [ count ] + "\n";
				}				
			}
			output = output + "* end of roster **\n";
		}
		return output;		
	}
	
	/**
	 * This method checks if the financial aid value for a student is valid in the form (aid, student).
	 * @param a floating value for financial aid and a Student object.
	 * @exception Throws illegal argument exception
	 */
	public void isValidFinAid ( float aid, Student student ) throws IllegalArgumentException {
		if ( aid <= ConstantIdentifiers.NONE ) {
			throw new IllegalArgumentException( "Financial aid must be greater than 0." );
		}
		else if ( aid > ConstantIdentifiers.MAXFINAID ) {
			throw new IllegalArgumentException( "Financial aid cannot be greater than 10,000." );
		}
		else if ( aid > ( roster[ this.find( student ) ].getTuitionDue() - roster[ this.find( student ) ].getTuitionPaid() ) ) { //may need to reduce fin aid if larger than amount due
			throw new IllegalArgumentException( "Amount is greater than amount due." );
		}
	}
	
	/**
	 * This method awards a floating financial aid value to a student.
	 * @param a floating value for financial aid and Student object.
	 * @exception Throws illegal argument exception
	 */
	public void awardFinAid( float aidAmt, Student student ) throws IllegalArgumentException {
		//part time doesnt qualify
		//has to be resident
		if ( this.find( student ) == -1 ) {
			throw new IllegalArgumentException( "Student is not in roster." );
		}		
		else if ( !roster[ this.find( student ) ].toString().contains( ":resident")  ) { //checking if student is resident
			throw new IllegalArgumentException( "Only residents can recieve financial aid." );
		}
		else if ( roster[ this.find( student ) ].getCreditHours() < ConstantIdentifiers.FULLTIMEHRS ) { //checking if student is part time
			throw new IllegalArgumentException( "Only fulltime students can recieve financial aid." );
		}
		else if ( ( (Resident) roster[ this.find( student ) ] ).getFinAidAwarded() != ConstantIdentifiers.EMPTY ) { //checking if student is part time
			throw new IllegalArgumentException( "Financial aid can only be awarded once." );
		}
		else {
			this.isValidFinAid( aidAmt, student );
			( (Resident) roster[ this.find( student ) ]).setFinAidAwarded( aidAmt );
		}
	}
	
	/**
	 * This method returns the size of the roster.
	 */
	public int getSize() {
		return this.size;
	}

	
}
