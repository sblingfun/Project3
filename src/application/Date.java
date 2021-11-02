/**
 * 
 */
package application;

import java.util.Calendar;
import java.util.StringTokenizer;


/**
 * This class defines the Date class with a day, month, and year.
 * @author Alex 
 */
public class Date implements Comparable<Date>{
	private int year;
	private int month;
	private int day;

	/**
	 * This constructor takes the form "mm/dd/yyyy".
	 * @param date in the form of"mm/dd/yyyy"
	 */
	public Date( String date ) {
		StringTokenizer st = new StringTokenizer( date,"/" );
		month = Integer.parseInt( st.nextToken() );
		day = Integer.parseInt( st.nextToken() );
		year = Integer.parseInt( st.nextToken() );
	}

	/**
	 * This constructor uses today's date.
	 * 
	 */
	public Date() {
		Calendar current = Calendar.getInstance();
		month = current.get(Calendar.MONTH) + 1;
		day = current.get(Calendar.DATE);
		year = current.get(Calendar.YEAR);
	}

	/**
	 * This method checks if the date is valid.
	 * @return returns true if valid, false otherwise.
	 */
	public boolean isValid() {
		Date cmpDate = new Date();
		if ( invalidYear( this.year ) ) return false;
		if ( invalidMonth( this.month ) || ( this.month > cmpDate.month && this.year == cmpDate.year ) ) return false;
		if ( invalidDay( this.day, this.month, this.year ) || ( this.day > cmpDate.day
				&& this.year == cmpDate.year && this.month == cmpDate.month ) ) return false;		
		else return true;
	}
	
	/**
	 * This helper method checks if the year is invalid.
	 * @param integer value for year
	 * @return returns true if invalid, false otherwise.
	 */
	private static boolean invalidYear( int year ) {
		Date currentDate = new Date();
		return year < ConstantIdentifiers.OLDEST_YEAR || year > currentDate.year;	
	}
	
	/**
	 * This helper method checks if the year is a leap year.
	 * @param integer value for year
	 * @return returns true if a leap year, false otherwise.
	 */
	private static boolean leapYear( int year ) {
		if ( year % ConstantIdentifiers.QUADRENNIAL == 0 ) {
			if ( year % ConstantIdentifiers.CENTENNIAL == 0 ) {
				if ( year % ConstantIdentifiers.QUARTERCENTENNIAL == 0 )
					return true;
				else
					return false;				
			}
			else
				return true;
		}
		else
			return false;
	}

	/**
	 * This helper method checks if the month is invalid.
	 * @param integer value for month
	 * @return returns true if invalid, false otherwise.
	 */
	private static boolean invalidMonth( int month ) {
		return month > ConstantIdentifiers.NUMMONTHS || month < ConstantIdentifiers.FIRSTMONTH;
	}
	
	/**
	 * This helper method checks if the month is day.
	 * @param integer value for day
	 * @return returns true if invalid, false otherwise.
	 */
	private static boolean invalidDay( int day, int month, int year ) {
		if ( day < ConstantIdentifiers.FIRSTDAY ) return true;
		else {
		     switch ( month ) {
	              case 2:
	        	      if ( leapYear(year) && day > ConstantIdentifiers.LEAPFEB ) return true;
	        	      else if ( !leapYear(year) && day > ConstantIdentifiers.FEBDAYS ) return true;
	        	      break;
	              case 4:
	              case 6:
	              case 9:
	              case 11:
	        	      if ( day > ConstantIdentifiers.THIRTYDAYMAX ) return true;
	        	      break;
	              case 1:
	              case 3:
	              case 5:
	              case 7:
	              case 8:
	              case 10:
	              case 12:
	        	      if ( day > ConstantIdentifiers.THIRTYONEMAX ) return true;
	        	      break;
	              default: return true;       	 
	         }
		     return false;
		}
	}
	
	/**
	 * This method prints the date in the form "mm/dd/yyyy".
	 * @return returns date in form "mm/dd/yyyy".
	 */
	public String toString() {
		return String.format( month + "/" + day + "/" + year );
	}
	
	/**
	 * This method gets the day value for a date.
	 * @return int value for day.
	 */
	public int getDay() {
		return this.day;
	}
	
	/**
	 * This method gets the month value for a date.
	 * @return int value for month.
	 */
	public int getMonth() {
		return this.month;
	}
	
	/**
	 * This method gets the year value for a date.
	 * @return int value for Year.
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * This method overrides the compareTo value for a Date.
	 * @param a date object
	 * @return 0 if the same, 1 if the compared date is larger, -1 if smaller.
	 */
	@Override
	public int compareTo(Date date) {
		if ( this.getYear() > date.getYear() ) {
			return 1;
		}
		else if ( this.getYear() < date.getYear() ) {
			return -1;
		}
		else {
			if ( this.getMonth() > date.getMonth() ) {
				return 1;
			}
			else if ( this.getMonth() < date.getMonth() ) {
				return -1;
			}
			else {
				if ( this.getDay() > date.getDay() ) {
					return 1;
				}
				else if ( this.getDay() < date.getDay() ) {
					return -1;
				}
				else {
					return 0;
				}
			}
		}
	}
	
		
	/** Testbed main for the Date class...**/
	public static void main(String[] args) {

		//test case #1 invalid year before 1980
		Date test = new Date("11/1/1979");
		boolean expectedResult = false;
		boolean result = test.isValid();
		System.out.print("Test case #1: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #2 invalid year after current
		test = new Date("11/1/2022");
		result = test.isValid();
		System.out.print("Test case #2: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #3 invalid month less than 1
		test = new Date("0/6/2000");
		result = test.isValid();
		System.out.print("Test case #3: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #4 invalid month greater than 12
		test = new Date("13/6/2000");
		result = test.isValid();
		System.out.print("Test case #4: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #5 invalid day less than 0
		test = new Date("6/0/2000");
		result = test.isValid();
		System.out.print("Test case #5: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #6 invalid day greater than 31 in May
		test = new Date("6/32/2000");
		result = test.isValid();
		System.out.print("Test case #6: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #7 invalid day greater than 28 in Feb on a non-leap year
		test = new Date("2/29/2001");
		result = test.isValid();
		System.out.print("Test case #7: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #8 invalid day greater than 29 in Feb on a leap year
		test = new Date("2/30/2000");
		result = test.isValid();
		System.out.print("Test case #8: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #9 valid day of 29 in Feb on a leap year
		test = new Date("2/29/2000");
		result = test.isValid();
		System.out.print("Test case #9: ");
		if (!result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
					
		//test case #10 valid day of 30 in April
		test = new Date("4/30/2000");
		result = test.isValid();
		System.out.print("Test case #10: ");
		if (!result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");

		//test case #11 invalid day of 32 in January
		test = new Date("1/32/2000");
		result = test.isValid();
		System.out.print("Test case #11: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #12 invalid day of 31 in September
		test = new Date("9/31/2000");
		result = test.isValid();
		System.out.print("Test case #12: ");
		if (result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
		//test case #13 current date is valid
		test = new Date();
		result = test.isValid();
		System.out.print("Test case #13: ");
		if (!result == expectedResult)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		
	}
}
