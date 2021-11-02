package application;
/**
 * 
 */

/**
 * @author Alex
 *
 */
public class NonResident extends Student {
	
	public NonResident ( String name, Major major, int creditHours ) {
		super( name, major, creditHours );
	}
	
	@Override
	public String toString() {
		return String.format( super.toString() + ":non-resident");
	}
	
	@Override
	public void tuitionDue() {
		float dollarAmt;
		if ( !super.isFullTime( this ) ) {
			dollarAmt = super.getCreditHours() * ConstantIdentifiers.NONRESIDENTRATE;
			dollarAmt = dollarAmt + ( ConstantIdentifiers.UNIVERSITYFEE * ConstantIdentifiers.PARTTIMEFEERATE );
		}
		else if ( super.getCreditHours() <= ConstantIdentifiers.NORMALPRICEHRS ) {
			dollarAmt = ConstantIdentifiers.NONRESIDENTPRICE + ConstantIdentifiers.UNIVERSITYFEE;
		}
		else {
			dollarAmt = ConstantIdentifiers.NONRESIDENTPRICE + ConstantIdentifiers.UNIVERSITYFEE;
			dollarAmt = dollarAmt + ( ( super.getCreditHours() - ConstantIdentifiers.NORMALPRICEHRS ) * ConstantIdentifiers.NONRESIDENTRATE );
		}	
		
		super.setTuitionDue( dollarAmt );
	}
		
		public static void main(String[] args) {
			NonResident n = new NonResident( "Alex", Major.EE, 12);
			System.out.println(n);
			n.tuitionDue();
			System.out.println(n);
		}
	
}
