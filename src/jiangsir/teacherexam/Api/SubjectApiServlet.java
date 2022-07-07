package jiangsir.teacherexam.Api;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.SubjectDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Servlets.EditExamServlet;
import jiangsir.teacherexam.Tables.User.ROLE;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(urlPatterns = { "/Subject.api" })
@RoleSetting(allowHigherThen = ROLE.USER)
public class SubjectApiServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4544330360425906644L;
	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	public enum ACTION {
		deleteSubject; // 刪除 Subject
	}

	@Override
	public void AccessFilter(HttpServletRequest request) throws AccessException {
		String action = request.getParameter("action");
		switch (ACTION.valueOf(action)) {
		case deleteSubject:
			new EditExamServlet().AccessFilter(request);
			break;
		default:
			break;

		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (ACTION.valueOf(action)) {
		case deleteSubject:
			String subjectid = request.getParameter("subjectid");
			if (subjectid == null || !subjectid.matches("[0-9]+")) {

			} else {
				new SubjectDAO().delete(Integer.parseInt(subjectid));
			}
			return;
		}
	}
}
