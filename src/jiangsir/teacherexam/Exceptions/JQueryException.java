/**
 * idv.jiangsir.Exceptions - ServerException.java
 * 2011/7/31 下午1:27:15
 * jiangsir
 */
package jiangsir.teacherexam.Exceptions;

import jiangsir.teacherexam.Exceptions.Alert.TYPE;

/**
 * @author jiangsir <br/>
 *         專門轉給 jQuery 前端處理的 Exception error
 * 
 */
public class JQueryException extends DataException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Alert alert = new Alert();

	// public JQueryException() {
	// super();
	// }

	public static String StackTraceToString(StackTraceElement[] stackTrace) {
		String s = "\n";
		for (StackTraceElement e : stackTrace) {
			s += e.toString() + "\n";
		}
		return s;
	}

	public JQueryException(Throwable cause) {
		super(cause.getLocalizedMessage() == null
				? cause.getClass().getName() + StackTraceToString(cause.getStackTrace())
				: cause.getLocalizedMessage(), cause);
	}

	public JQueryException(Alert alert) {
		super(alert.getTitle(), alert);
		this.alert = alert;
	}

	public JQueryException(String message) {
		super(message, new Alert(TYPE.EXCEPTION, message, "", "", null));
		this.alert = new Alert(TYPE.EXCEPTION, message, "", "", null);
	}

	public JQueryException(String message, Alert alert) {
		super(message, alert);
		this.alert = alert;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

}
