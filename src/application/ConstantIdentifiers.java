/**
 * 
 */
package application;

import java.util.Calendar;
import application.Date;
/**
 * This class defines all of the constants used for implementing our Tuition Manager
 * @author Alex *
 */
public class ConstantIdentifiers {
	public static final int OLDEST_YEAR = 2021;
	public static final int QUADRENNIAL = 4;
	public static final int CENTENNIAL = 100;
	public static final int QUARTERCENTENNIAL = 400;
	public static final int NUMMONTHS = 12;
	public static final int FIRSTMONTH = 1; //might be able to just use 1
	public static final int FIRSTDAY = 1; //might be able to just use 1
	public static final int THIRTYONEMAX = 31;
	public static final int THIRTYDAYMAX = 30;
	public static final int FEBDAYS = 28;
	public static final int LEAPFEB = 29;
	public static final int FEB = 2;
	public static final int APRIL = 4;
	public static final int JUNE = 6;
	public static final int SEPTEMBER = 9;
	public static final int NOVEMBER = 11;
	public static final int NONE = 0;
	public static final Date FIRSTVALIDDATE = new Date( "01/01/2021" ) ;
	
	
	public static final int CAPACITY = 4;
	public static final int FULLTIMEHRS = 12;
	public static final int MAXCREDITHR = 24;
	public static final int MINCREDITHR = 3;
	public static final int NORMALPRICEHRS = 16;
	public static final int UNIVERSITYFEE = 3268;
	public static final int INTERNATIONALFEE = 2650;
	public static final int ADDITIONALFEE = 2650;
	public static final int NYDISCOUNT = 4000;
	public static final int CTDISCOUNT = 5000;
	public static final int RESIDENTPRICE = 12536;
	public static final int NONRESIDENTPRICE = 29737;
	public static final float PARTTIMEFEERATE = (float) .8;
	public static final int RESIDENTRATE = 404;
	public static final int NONRESIDENTRATE = 966;
	public static final int EMPTY = 0;
	public static final int MAXFINAID = 10000;
}
