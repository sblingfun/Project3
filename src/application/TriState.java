package application;
/**
 * 
 */

/**
 * @author Alex
 *
 */
public class TriState extends NonResident {
	private String state; 

	public TriState ( String name, Major major, int creditHours, String state ) {
		super( name, major, creditHours );
		this.state = state;
	}

	@Override
	public String toString() {
		return String.format( super.toString() + "(tri-state):" + state);
	}
	
	@Override
	public void tuitionDue() {
		super.tuitionDue();
		float dollarAmt = super.getTuitionDue();
		if ( super.isFullTime( this ) ) {
			if ( state.equalsIgnoreCase("NY") ) {
				dollarAmt = dollarAmt - ConstantIdentifiers.NYDISCOUNT;
			}
			if ( state.equalsIgnoreCase("CT") ) {
				dollarAmt = dollarAmt - ConstantIdentifiers.CTDISCOUNT;
			}
		}
		
		super.setTuitionDue( dollarAmt );
	}
	
	public static void main(String[] args) {
		Student x = new TriState("alex",Major.EE,9,"NY");
		System.out.println(x);
		x.tuitionDue();
		System.out.println(x);
	}
	
}
