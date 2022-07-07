package jiangsir.teacherexam.Api;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.DAOs.ExamDAO;
import jiangsir.teacherexam.DAOs.UpfileDAO;
import jiangsir.teacherexam.DAOs.UserDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Servlets.AdminServlet;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.Exam;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(urlPatterns = { "/Admin.api" })
@RoleSetting
public class AdminApiServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6748832105697593305L;
	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	public enum ACTION {
		setAllBankaccount, // 設定所有的 bankaccount
		deleteUpfile, // 刪除已上傳的檔案。
		setApplicationAlwaysEditable, // 無條件開放使用者修改報名表。
		deleteApplication, // 刪除一個報名表，通常是用來刪除測試報名表。
		deleteExam, // 刪除一個甄試
		disactiveExam, // 結束一個教師甄試。
		setActiveExam, // 啓動一個教師甄試。
		setExamformprintable, // 設定考生列印准考證
		getApplication, // 取得 application 資訊。以 JSON 回傳。
		setPaid, // 設定為已繳費！
		unPaid, // 取消繳費
		getMemory, //
		makeSeatid;// 製作准考證號碼！
	}

	@Override
	public void AccessFilter(HttpServletRequest request) throws AccessException {
		new AdminServlet().AccessFilter(request);
	}

	private String getMemory() {
		HashMap<String, String> memorymap = new HashMap<String, String>();
		Runtime runtime = Runtime.getRuntime();
		int danwei = 1024 * 1024;
		memorymap.put("danwei", "MB");
		memorymap.put("freeMemory", String.valueOf(runtime.freeMemory() / danwei));
		memorymap.put("totalMemory", String.valueOf(runtime.totalMemory() / danwei));
		try {
			return mapper.writeValueAsString(memorymap);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void makeSeatid(String examid) {
		if (examid == null || !examid.matches("[0-9]+")) {
//			exam = new ExamDAO().getActiveExam();
			throw new DataException("參數有誤(examid=" + examid + ")");
		}

		Exam exam = new ExamDAO().getExamById(Integer.parseInt(examid));
		ApplicationDAO applicationDao = new ApplicationDAO();
		applicationDao.cleanSeatid(exam.getId());

		// String[] seatpatterns = exam.getSeatpatterns();
		// ArrayList<Subject> subjects = exam.getSubjects();
		// System.out.println(Arrays.toString(seatpatterns));
		// System.out.println(Arrays.toString(subjects));
		for (Application application : exam.getApplications()) {
			// int subjectid = Arrays.binarySearch(subjects,
			// application.getSubject());
			// int subjectid = -1;
			// int serialnumber = 0;
			// Subject subject = null;
			// for (Subject sub : subjects) {
			// if (sub.getSubject().equals(application.getSubject())) {
			// // subjectid = ++serialnumber;
			// subject = sub;
			// break;
			// }
			// }
			// for (int i = 0; i < subjects.length; i++) {
			// if (subjects[i].equals(application.getSubject())) {
			// subjectid = i;
			// break;
			// }
			// }
			// if (subject == null) {
			// continue;
			// }
			int seatnumber = applicationDao.getSeatnumber(exam.getId(), application.getId(), application.getSubject());
			if (seatnumber != 0) {
				application.setSeatid(application.getSubject().getSeatpattern().charAt(0)
						+ new DecimalFormat(application.getSubject().getSeatpattern().substring(1)).format(seatnumber));
				applicationDao.update(application);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (ACTION.valueOf(action)) {
		case setAllBankaccount:
			new ApplicationDAO().updateAllBankaccount();
			return;
		case disactiveExam:
			new ExamDAO().disactiveExam(Integer.parseInt(request.getParameter("examid")));
			return;
		case setActiveExam:
			int examid = Integer.parseInt(request.getParameter("examid"));
			new ExamDAO().setActiveExam(examid);
			return;
		case setExamformprintable:
			Exam exam = new ExamDAO().getExamById(request.getParameter("examid"));
			exam.setIsexamformprintable(true);
			try {
				new ExamDAO().update(exam);
			} catch (DataException e) {
				e.printStackTrace();
			}
		case setPaid:

			new ApplicationDAO().setPaid(request.getParameter("bankaccount"));
			return;
		case unPaid:
			new ApplicationDAO().setUnPaid(request.getParameter("bankaccount"));
			return;
		case makeSeatid:
			this.makeSeatid(request.getParameter("examid"));
			break;
		case getMemory:
			response.getWriter().print(this.getMemory());
			return;
		case getApplication:
			Application application = new ApplicationDAO().getApplicationById(request.getParameter("applicationid"));
			response.getWriter().print(mapper.writeValueAsString(application));
			return;
		case deleteApplication:
			application = new ApplicationDAO().getApplicationById(request.getParameter("applicationid"));
			new UserDAO().delete(application.getPid(), application.getExamid());
			new ApplicationDAO().delete(application.getId());
			return;
		case deleteExam:
			exam = new ExamDAO().getExamById(request.getParameter("examid"));
			exam.setVisible(0);
			new ExamDAO().update(exam);
			return;
		case setApplicationAlwaysEditable:
			exam = new ExamDAO().getExamById(request.getParameter("examid"));
			exam.setIsApplicationAlwaysEditable(true);
			new ExamDAO().update(exam);
			return;
		case deleteUpfile:
			new UpfileDAO().delete(Integer.parseInt(request.getParameter("upfileid")));
		default:
			break;
		}
	}

}
