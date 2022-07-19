package com.api.meli.apimeli.exceptions;
/**
 * 
 * @author jnmartinez
 *
 */
public class NegocioException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533539765619627302L;

	public NegocioException (String message) {
        super (message);
    }

    public NegocioException (Throwable cause) {
        super (cause);
    }

    public NegocioException (String message, Throwable cause) {
        super (message, cause);       
    }
}
