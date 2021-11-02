package application;
/**
 * 
 */

/**
 * @author Alex
 *
 */
public class International extends NonResident {
	private boolean studyAbroadStatus;
	
	public International ( String name, Major major, int creditHours, boolean abroadStatus ) {
		super( name, major, creditHours );
		this.studyAbroadStatus = abroadStatus;
	}

	@Override
	public String toString() {
		if ( studyAbroadStatus ) {
			return String.format( super.toString() + ":international:study abroad");
		}
		else {
			return String.format( super.toString() + ":international");
		}		
	}
	
	public void tuitionDue() {
		super.tuitionDue();
		float dollarAmt = super.getTuitionDue();
		if ( studyAbroadStatus ) {
			dollarAmt = dollarAmt - ConstantIdentifiers.NONRESIDENTPRICE;
		}		
		dollarAmt = dollarAmt + ConstantIdentifiers.INTERNATIONALFEE;
		super.setTuitionDue( dollarAmt );
	}

	public void setStudyAbroadStatus( boolean status ) {
		studyAbroadStatus = status;
		if ( studyAbroadStatus && this.getCreditHours() > ConstantIdentifiers.FULLTIMEHRS ) {
			this.setCreditHours( ConstantIdentifiers.FULLTIMEHRS );
			this.setTuitionPaid( 0 );
			this.setLastPayment( new Date("00/00/00") );
			this.tuitionDue();
		}
	}
	
	public static void main(String[] args) {
		Student x = new International("alex",Major.EE,12,false);
		System.out.println(x);
		x.tuitionDue();
		System.out.println(x);
		Student y = new International("alex",Major.EE,12,true);
		System.out.println(y);
		y.tuitionDue();
		System.out.println(y);
	}
}
