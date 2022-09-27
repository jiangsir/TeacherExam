/**
 * idv.jiangsir.Exceptions - ServerException.java
 * 2011/7/31 下午1:27:15
 * nknush-001
 */
package jiangsir.teacherexam.Exceptions;

/**
 * @author nknush-001
 * 
 */
public class DataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DATE_FORMAT_EXCEPTION = "日期格式不正確！";

	public DataException() {
		super();
	}

	public DataException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataException(Throwable cause) {
		super(cause);
	}

	public DataException(String message) {
		super(message);
	}

	public DataException(String session_pid, String message) {
		super(message);
		System.out.println("session_pid=" + session_pid);
	}
}
