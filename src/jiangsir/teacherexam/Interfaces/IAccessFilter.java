/**
 * jiangsir.zerobb.Interfaces - IAccessible.java
 * 2012/4/17 下午4:28:49
 * nknush-001
 */
package jiangsir.teacherexam.Interfaces;

import javax.servlet.http.HttpServletRequest;

import jiangsir.teacherexam.Exceptions.AccessException;

/**
 * @author jiangsir
 * 
 */
public interface IAccessFilter {
	public void AccessFilter(HttpServletRequest request) throws AccessException;
}
