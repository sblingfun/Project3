package application;
/**
 * 
 */

/**
 * This enum class defines the types of Majors which our profiles can have.
 * @author Alex 
 */
public enum Major {
	CS, IT, BA, EE, ME;
	
	public static Major lookup(final String id) {
        for(Major enumValue: values()){
           if(enumValue.name().equalsIgnoreCase(id)){
              return enumValue;
           }
        }  
        throw new IllegalArgumentException("'" + id + "' is not a valid major");
       }
}
