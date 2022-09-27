package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Exceptions.RoleException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Scopes.SessionScope;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Tables.Picture;
import jiangsir.teacherexam.Tables.User;
import jiangsir.teacherexam.Tables.User.ROLE;
import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.Beans.AlertBean;
import jiangsir.teacherexam.DAOs.*;
import jiangsir.teacherexam.Tools.AlertDispatcher;
import jiangsir.teacherexam.Tools.PictureUploader;

//@MultipartConfig(maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
@WebServlet(urlPatterns = { "/EditApplication.html" })
@RoleSetting(allowHigherThen = ROLE.USER)
public class EditApplicationServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1474740332617684970L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		if (currentUser.getIsAdmin()) {
			return;
		}
		String applicationid = request.getParameter("applicationid");
		if (applicationid == null || !applicationid.matches("[0-9]+")) {
			throw new DataException("參數錯誤! 未指定【報名表】編號或編號錯誤!");
		}

		Application application = new ApplicationDAO().getApplicationById(Integer.parseInt(applicationid));
		if (!currentUser.getPid().equals(application.getPid())) {
			throw new AccessException("您不能修改他人資料！");
		}

		Exam exam = application.getExam();
		if (exam.getIsApplicationAlwaysEditable()) {
			return;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date(exam.getApplyend().getTime()));
		calendar.add(Calendar.DATE, 1);
		Date deadline_nextday = new Date(calendar.getTime().getTime());

		if (deadline_nextday.before(new Date(new java.util.Date().getTime()))) {
			throw new AccessException("報名時間已過(" + exam.getApplyend() + ")，報名表單不能修改了。");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession(false);
		// String session_pid = (String) session.getAttribute("session_pid");
		CurrentUser currentUser = UserFactory.getCurrentUser(request);

		String applicationid = request.getParameter("applicationid");
		if (applicationid == null || !applicationid.matches("[0-9]+")) {
			throw new DataException("參數錯誤! 未指定【報名表】編號或編號錯誤!");
		}
		Application application = new ApplicationDAO().getApplicationById(Integer.parseInt(applicationid));
		request.setAttribute("application", application);
		// User user = new UserDAO().getUser(session_pid);
		// User user = currentUser;
		request.setAttribute("user", currentUser);
		request.getRequestDispatcher("Application.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession(false);
		// String session_pid = (String) session.getAttribute("session_pid");
		// try {
		// this.AccessFilter(session_pid, request);
		// } catch (AccessException e) {
		// e.printStackTrace();
		// new AlertDispatcher(request, response).forward(new AlertBean(e));
		// return;
		// }

		PictureUploader uploader = new PictureUploader();
		try {
			uploader.parse(request, "UTF-8");
		} catch (FileUploadException e) {
			e.printStackTrace();
			new AlertDispatcher(request, response).forward(new AlertBean(e));
			return;
		}
		System.out.println("applicationid=[" + uploader.getParameter("applicationid") + "]");
		System.out.println("applicationid=[" + request.getParameter("applicationid") + "]");
		System.out.println("sss=[" + uploader.getParameter("subjectid") + "]");
		System.out.println("name=[" + uploader.getParameter("name") + "]");
		int id = Integer.parseInt(uploader.getParameter("applicationid"));
		ApplicationDAO applicationDao = new ApplicationDAO();
		Application application = applicationDao.getApplicationById(id);
		application.setSubjectid(uploader.getParameter("subjectid"));
		application.setName(uploader.getParameter("name"));
		application.setGender(uploader.getParameter("gender"));
		application.setBirthday(uploader.getParameter("birthday"));
		// application.setPid(uploader.getParameter("pid")); // pid 不准修改！
		String[] specials = uploader.getParameterValues("special");
		String special = "";
		for (int i = 0; specials != null && i < specials.length; i++) {
			special += special == "" ? "" : ", " + specials[i];
		}
		application.setSchool(uploader.getParameter("school"));
		application.setPosition(uploader.getParameter("position"));
		application.setTeloffice(uploader.getParameter("teloffice"));
		application.setCellphone(uploader.getParameter("cellphone"));
		application.setAddress(uploader.getParameter("address"));
		application.setEmail(uploader.getParameter("email"));

		application.setLicense(uploader.getParameter("license"));
		application.setLicensedate(uploader.getParameter("licensedate"));
		application.setLicenseid(uploader.getParameter("licenseid"));

		application.setBachelorschool(uploader.getParameter("bachelorschool"));
		application.setBachelormajor(uploader.getParameter("bachelormajor"));
		application.setBachelorbegin(uploader.getParameter("bachelorbegin"));
		application.setBachelorend(uploader.getParameter("bachelorend"));

		application.setMasterschool(uploader.getParameter("masterschool"));
		application.setMastermajor(uploader.getParameter("mastermajor"));
		application.setMasterbegin(uploader.getParameter("masterbegin"));
		application.setMasterend(uploader.getParameter("masterend"));

		application.setPhdschool(uploader.getParameter("phdschool"));
		application.setPhdmajor(uploader.getParameter("phdmajor"));
		application.setPhdbegin(uploader.getParameter("phdbegin"));
		application.setPhdend(uploader.getParameter("phdend"));

		application.setSchool1(uploader.getParameter("school1"));
		application.setPosition1(uploader.getParameter("position1"));
		application.setSchool1begin(uploader.getParameter("school1begin"));
		application.setSchool1end(uploader.getParameter("school1end"));
		application.setSchool2(uploader.getParameter("school2"));
		application.setPosition2(uploader.getParameter("position2"));
		application.setSchool2begin(uploader.getParameter("school2begin"));
		application.setSchool2end(uploader.getParameter("school2end"));
		application.setSchool3(uploader.getParameter("school3"));
		application.setPosition3(uploader.getParameter("position3"));
		application.setSchool3begin(uploader.getParameter("school3begin"));
		application.setSchool3end(uploader.getParameter("school3end"));

		application.setSpecial1(uploader.getParameter("special1"));
		application.setSpecial1date(uploader.getParameter("special1date"));
		application.setSpecial2(uploader.getParameter("special2"));
		application.setSpecial2date(uploader.getParameter("special2date"));
		application.setLicense1(uploader.getParameter("license1"));
		application.setLicense2(uploader.getParameter("license2"));
		application.setLicense3(uploader.getParameter("license3"));
		application.setOther(uploader.getParameter("other"));
		application.setNote(uploader.getParameter("note"));
		System.out.println("reasons-----------------");

		String[] reasons = uploader.getParameterValues("reason");
		System.out.println("reasons=" + reasons.length);
		String[] awards = uploader.getParameterValues("award");
		System.out.println("awards=" + awards.length + "");
		Application.Honor[] honors = application.getHonors();
		for (int i = 0; i < Math.min(reasons.length, awards.length); i++) {
			Application.Honor honor = new Application.Honor();
			honor.setReason(reasons[i]);
			honor.setAward(awards[i]);
			System.out.println("reason[" + i + "]=" + reasons[i]);
			System.out.println("award[" + i + "]=" + awards[i]);
			honors[i] = honor;
		}
		application.setHonors(honors);
		// Picture picture = new Picture();
		PictureDAO pictureDao = new PictureDAO();
		Iterator<?> it = uploader.getFileItemList("picture").iterator();
		FileItem item = null;
		while (it.hasNext()) {
			item = (FileItem) it.next();
			if (item.getName() == null || item.getName().equals("")) {
				// throw new DataException("未附照片檔！");
				continue;
			}
			// IE 會傳上完整的路徑，不好
			String filename = item.getName();
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			// picture.setApplicationid(application.getId());
			if (application.getPictureid() == 0) {
				Picture newpicture = new Picture();
				newpicture.setId(application.getPictureid());
				newpicture.setBlob(item.getInputStream());
				newpicture.setFilename(filename);
				newpicture.setFiletype(item.getContentType());
				int pictureid = pictureDao.insert(newpicture);
				application.setPictureid(pictureid);
			} else {
				Picture picture = pictureDao.getPictureById(application.getPictureid());
				picture.setBlob(item.getInputStream());
				picture.setFilename(filename);
				picture.setFiletype(item.getContentType());
				pictureDao.update(picture);
			}

			// application.setPicture(item.getInputStream());
			// application.setFiletype(item.getContentType());
			// applicationDao.updateBlob(application.getPicture(),
			// application.getFiletype(), application.getId());
		}
		applicationDao.update(application);

		HttpSession session = request.getSession(false);
		session.setAttribute("applicationid", id);

		SessionScope sessionScope = new SessionScope(session);
		CurrentUser currentUser = sessionScope.getCurrentUser();
		User user = new UserDAO().getUserByPid(application.getPid(), application.getExamid());

		if (currentUser.getIsAdmin()) {
			try {
				user.setPasswd(uploader.getParameter("passwd"), uploader.getParameter("passwd1"));
			} catch (DataException e) {
				e.printStackTrace();
			}
			new UserDAO().update(user);
			response.sendRedirect("." + ManageExamServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]
					+ "?examid=" + application.getExamid());
		} else {
			user.setPasswd(uploader.getParameter("passwd"), uploader.getParameter("passwd1"));
			new UserDAO().update(user);
			response.sendRedirect("." + PersonalServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]
					+ "?examid=" + application.getExamid());
		}
		return;
	}

}
