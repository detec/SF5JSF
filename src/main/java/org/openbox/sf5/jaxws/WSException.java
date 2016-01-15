package org.openbox.sf5.jaxws;

import javax.xml.ws.WebFault;

// http://stackoverflow.com/questions/13596260/how-to-throw-a-custom-fault-on-a-jax-ws-web-service

@WebFault(name="CheckVerifyFault",
targetNamespace="http://wsmodel.sf5.openbox.org/")
public class WSException extends Exception {

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	private int errorCode;

    public WSException(String message, Throwable cause) {
        super(message, cause);
    }

    public WSException(String message) {
        super(message);
    }

    public WSException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public WSException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

	private static final long serialVersionUID = -999628554564895178L;

}
