// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package ebay.apis.eblbasecomponents;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;
import java.util.List;

/**
 * 
 * A variable that contains specific information about the context of this error.
 * For example, if you pass in an attribute set ID that does not match
 * the specified category, the attribute set ID might be returned as an error parameter.
 * Use error parameters to flag fields that users need to correct.
 * Also use error parameters to distinguish between errors when multiple
 * errors are returned.
 * 
 */
public class ErrorParameterType implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element(name = "Value")
	@Order(value=0)
	public String value;	
	
	@AnyElement
	@Order(value=1)
	public List<Object> any;	
	
	@Attribute(name = "ParamID")
	@Order(value=2)
	public String paramID;	
	
    
}