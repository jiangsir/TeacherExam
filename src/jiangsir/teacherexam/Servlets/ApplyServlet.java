package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Scopes.SessionScope;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Tables.Picture;
import jiangsir.teacherexam.Tables.User;
import jiangsir.teacherexam.Beans.AlertBean;
import jiangsir.teacherexam.DAOs.*;
import jiangsir.teacherexam.Tools.AlertDispatcher;
import jiangsir.teacherexam.Tools.PictureUploader;

@WebServlet(urlPatterns = { "/Apply.html" })
public class ApplyServlet extends HttpServlet implements IAccessFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		String examid = request.getParameter("examid");
		if (examid == null || !examid.matches("[0-9]+")) {
			throw new AccessException("沒有正確的指定甄選編號！");
		}
		Exam exam = new ExamDAO().getExamById(examid);
		if (!exam.isIsactive()) {
			throw new AccessException("本次甄選(" + exam.getTitle() + ") 並非進行中的甄選，不能進行報名！");
		}

		if (exam.Before_Applybegin()) {
//		if (new Timestamp(System.currentTimeMillis()).before(exam.getApplybegin())) {
			throw new AccessException("本次甄選(" + exam.getTitle() + ") 報名尚未開始，請稍候！報名開始時間：" + exam.getApplybegin() + "");
		}

		if (exam.After_Applyend()) {
//		if (new Timestamp(System.currentTimeMillis()).after(exam.getApplyend())) {
			throw new AccessException("本次甄選(" + exam.getTitle() + ") 報名時間已過，不再接受報名了！報名截止時間：" + exam.getApplyend() + "");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String examid = request.getParameter("examid");
		Application application = new Application();
		application.setExamid(examid);
		application.setBirthday("1981-11-04");
		request.setAttribute("application", application);
		request.getRequestDispatcher("Application.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// try {
		// this.AccessFilter("", request);
		// } catch (AccessException e1) {
		// e1.printStackTrace();
		// new AlertDispatcher(request, response).forward(new AlertBean(e1
		// .getLocalizedMessage()));
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
		// Exam activeexam = new ExamDAO().getActiveExam();
		Exam exam = new ExamDAO().getExamById(request.getParameter("examid"));

		User user = new User();
		user.setPid(uploader.getParameter("pid"));
		user.setExamid(exam.getId());
		user.setPasswd(uploader.getParameter("passwd"), uploader.getParameter("passwd1"));
		user.setRole(User.ROLE.USER);

		Application newapplication = new Application();
		newapplication.setExamid(exam.getId());
		newapplication.setSubjectid(uploader.getParameter("subjectid"));
		newapplication.setName(uploader.getParameter("name"));
		newapplication.setGender(uploader.getParameter("gender"));
		newapplication.setBirthday(uploader.getParameter("birthday"));
		newapplication.setPid(uploader.getParameter("pid"));
		// String[] specials = uploader.getParameterValues("special");
		// String special = "";
		// for (int i = 0; specials != null && i < specials.length; i++) {
		// special += special == "" ? "" : ", " + specials[i];
		// }
		newapplication.setSchool(uploader.getParameter("school"));
		newapplication.setPosition(uploader.getParameter("position"));
		newapplication.setTeloffice(uploader.getParameter("teloffice"));
		newapplication.setCellphone(uploader.getParameter("cellphone"));
		newapplication.setAddress(uploader.getParameter("address"));
		newapplication.setEmail(uploader.getParameter("email"));

		newapplication.setLicense(uploader.getParameter("license"));
		newapplication.setLicensedate(uploader.getParameter("licensedate"));
		newapplication.setLicenseid(uploader.getParameter("licenseid"));

		newapplication.setBachelorschool(uploader.getParameter("bachelorschool"));
		newapplication.setBachelormajor(uploader.getParameter("bachelormajor"));
		newapplication.setBachelorbegin(uploader.getParameter("bachelorbegin"));
		newapplication.setBachelorend(uploader.getParameter("bachelorend"));
		newapplication.setMasterschool(uploader.getParameter("masterschool"));
		newapplication.setMastermajor(uploader.getParameter("mastermajor"));
		newapplication.setMasterbegin(uploader.getParameter("masterbegin"));
		newapplication.setMasterend(uploader.getParameter("masterend"));
		newapplication.setPhdschool(uploader.getParameter("phdschool"));
		newapplication.setPhdmajor(uploader.getParameter("phdmajor"));
		newapplication.setPhdbegin(uploader.getParameter("phdbegin"));
		newapplication.setPhdend(uploader.getParameter("phdend"));

		newapplication.setSchool1(uploader.getParameter("school1"));
		newapplication.setPosition1(uploader.getParameter("position1"));
		newapplication.setSchool1begin(uploader.getParameter("school1begin"));
		newapplication.setSchool1end(uploader.getParameter("school1end"));
		newapplication.setSchool2(uploader.getParameter("school2"));
		newapplication.setPosition2(uploader.getParameter("position2"));
		newapplication.setSchool2begin(uploader.getParameter("school2begin"));
		newapplication.setSchool2end(uploader.getParameter("school2end"));
		newapplication.setSchool3(uploader.getParameter("school3"));
		newapplication.setPosition3(uploader.getParameter("position3"));
		newapplication.setSchool3begin(uploader.getParameter("school3begin"));
		newapplication.setSchool3end(uploader.getParameter("school3end"));

		newapplication.setSpecial1(uploader.getParameter("special1"));
		newapplication.setSpecial1date(uploader.getParameter("special1date"));
		newapplication.setSpecial2(uploader.getParameter("special2"));
		newapplication.setSpecial2date(uploader.getParameter("special2date"));
		newapplication.setLicense1(uploader.getParameter("license1"));
		newapplication.setLicense2(uploader.getParameter("license2"));
		newapplication.setLicense3(uploader.getParameter("license3"));
		newapplication.setOther(uploader.getParameter("other"));
		newapplication.setNote(uploader.getParameter("note"));
		System.out.println("reasons-----------------");
		String[] reasons = uploader.getParameterValues("reason");
		System.out.println("reasons=" + reasons.length);
		String[] awards = uploader.getParameterValues("award");
		System.out.println("awards=" + awards.length + "");
		Application.Honor[] honors = newapplication.getHonors();
		for (int i = 0; i < Math.min(reasons.length, awards.length); i++) {
			Application.Honor honor = new Application.Honor();
			honor.setReason(reasons[i]);
			honor.setAward(awards[i]);
			System.out.println("reason[" + i + "]=" + reasons[i]);
			System.out.println("award[" + i + "]=" + awards[i]);
			honors[i] = honor;
		}
		newapplication.setHonors(honors);

		for (FileItem item : uploader.getFileItemList("picture")) {
			if (item.getName() == null || item.getName().equals("")) {
				throw new DataException("未附照片檔！");
			}
		}

		// Iterator<?> it = uploader.getFileItemList("picture").iterator();
		// FileItem item = null;
		// while (it.hasNext()) {
		// item = (FileItem) it.next();
		// if (item.getName() == null || item.getName().equals("")) {
		// throw new DataException("未附照片檔！");
		// }
		// // IE 會傳上完整的路徑，不好
		// String filename = item.getName();
		// filename = filename.substring(filename.lastIndexOf("\\") + 1);
		// }
		// newapplication.setPicture(item.getInputStream());
		// newapplication.setFiletype(item.getContentType());

		PictureDAO pictureDao = new PictureDAO();
		Picture picture = new Picture();
		int pictureid = 0;
		for (FileItem item : uploader.getFileItemList("picture")) {
			// IE 會傳上完整的路徑，不好
			String filename = item.getName();
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			// picture.setApplicationid(id);
			picture.setBlob(item.getInputStream());
			picture.setFilename(filename);
			picture.setFiletype(item.getContentType());
			pictureid = pictureDao.insert(picture);
		}
		newapplication.setPictureid(pictureid);

		int applicationid = new ApplicationDAO().insert(newapplication);
		int userid = new UserDAO().insert(user);
		user.setId(userid);

		HttpSession session = request.getSession(false);
		SessionScope sessionScope = new SessionScope(session);
		sessionScope.setCurrentUser(new CurrentUserDAO().getCurrentUserById(user.getId(), session));

		session.setAttribute("applicationid", applicationid);
		// session.setAttribute("session_pid", newapplication.getPid());

		response.sendRedirect("." + PersonalServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]);
	}

}
