package application;
/**
 * 
 */

/**
 * @author Alex
 *
 */
public class Resident extends Student {
	private float finAidAwarded = 0;
	
	public Resident ( String name, Major major, int creditHours ) {
		super( name, major, creditHours );
	}
	
	@Override
	public String toString() {
		return String.format( super.toString() + ":resident");
	}
	
	@Override
	public void tuitionDue() {
		float dollarAmt;
		if ( !super.isFullTime( this ) ) {
			dollarAmt = super.getCreditHours() * ConstantIdentifiers.RESIDENTRATE;
			dollarAmt = dollarAmt + ( ConstantIdentifiers.UNIVERSITYFEE * ConstantIdentifiers.PARTTIMEFEERATE );
		}
		else if ( super.getCreditHours() <= ConstantIdentifiers.NORMALPRICEHRS ) {
			dollarAmt = ConstantIdentifiers.RESIDENTPRICE + ConstantIdentifiers.UNIVERSITYFEE;
		}
		else {
			dollarAmt = ConstantIdentifiers.RESIDENTPRICE + ConstantIdentifiers.UNIVERSITYFEE;
			dollarAmt = dollarAmt + ( ( super.getCreditHours() - ConstantIdentifiers.NORMALPRICEHRS ) * ConstantIdentifiers.RESIDENTRATE );
		}
		dollarAmt = dollarAmt - finAidAwarded;		
		super.setTuitionDue( dollarAmt );
	}
	
	public float getFinAidAwarded() {
		return this.finAidAwarded;
	}
	
	public void setFinAidAwarded( float awardAmt ) {
		this.finAidAwarded = awardAmt;
	}
	
	//DELETE THIS
	public static void main(String[] args) {
		Resident n = new Resident( "Alex", Major.EE, 18);
		System.out.println(n);
		n.tuitionDue();
		System.out.println(n);
		Resident n2 = new Resident( "Bob", Major.EE, 1);
		System.out.println(n2);
		n2.tuitionDue();
		System.out.println(n2);
		n2.setTuitionPaid(1000);
		System.out.println(n2);
	}
	
	
}
