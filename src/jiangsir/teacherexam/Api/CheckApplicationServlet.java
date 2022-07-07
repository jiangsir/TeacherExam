package jiangsir.teacherexam.Api;

import java.io.IOException;
import java.util.LinkedHashSet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.codehaus.jackson.map.ObjectMapper;

import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.User;

@WebServlet(urlPatterns = { "/CheckApplication.json" })
public class CheckApplicationServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2063956476723607816L;
	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Application application = new Application();
		User user = new User();
		LinkedHashSet<String> checkSet = new LinkedHashSet<String>();
		try {
			application.setSubjectid(request.getParameter("subjectid"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setName(request.getParameter("name"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setGender(request.getParameter("gender"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setBirthday(request.getParameter("birthday"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setPid(request.getParameter("pid"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setCellphone(request.getParameter("cellphone"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setAddress(request.getParameter("address"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setEmail(request.getParameter("email"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setBachelorschool(request
					.getParameter("bachelorschool"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setBachelormajor(request.getParameter("bachelormajor"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setBachelorbegin(request.getParameter("bachelorbegin"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		try {
			application.setBachelorend(request.getParameter("bachelorend"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		String picture = request.getParameter("picture");
		int pictureid;
		try {
			pictureid = Integer.parseInt(request.getParameter("pictureid"));
			if (pictureid == 0 && "".equals(picture)) {
				try {
					throw new DataException("請選擇相片！");
				} catch (DataException e) {
					checkSet.add(e.getLocalizedMessage());
				}
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			try {
				throw new DataException("相片編號不是整數！("
						+ request.getParameter("pictureid") + ")");
			} catch (DataException e) {
				e.printStackTrace();
				checkSet.add(e.getLocalizedMessage());
			}
		}
		try {
			user.setPasswd(request.getParameter("passwd"),
					request.getParameter("passwd1"));
		} catch (DataException e) {
			checkSet.add(e.getLocalizedMessage());
		}
		response.getWriter().print(mapper.writeValueAsString(checkSet));
	}

}
