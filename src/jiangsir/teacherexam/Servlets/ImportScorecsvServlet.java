package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.DAOs.ScorecsvDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.Scorecsv;
import jiangsir.teacherexam.Tools.ConfigDAO;
import jiangsir.teacherexam.Tools.SystemConfig;
import jiangsir.teacherexam.Tools.Utils;

@WebServlet(urlPatterns = { "/ImportScorecsv" })
@RoleSetting
public class ImportScorecsvServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);

		String ip = request.getRemoteAddr();
		SystemConfig systemConfig = new ConfigDAO().getSystemConfig();
		if (currentUser.getIsAdmin() && Utils.isSubnetwork(systemConfig.getMANAGER_DOMAINS(), ip)) {
			return;
		}
		throw new AccessException("您無權使用這個區域。");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int examid = 0;
		try {
			examid = Integer.parseInt(request.getParameter("examid"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			throw new DataException("競賽編號有誤(examid=" + request.getParameter("examid") + ")！無法匯入成績。");
		}
		request.setAttribute("scorecsv", new ScorecsvDAO().getScorecsvByExamid(examid));
		request.getRequestDispatcher("ImportScorecsv.jsp").forward(request, response);
	}

	private void importCsvlines(int step, int examid, String scorecsv_step) {
		if (scorecsv_step == null || scorecsv_step.trim().equals("")) {
			return;
		}
		String[] step_lines = scorecsv_step.split("\\n");

		ScorecsvDAO scorecsvDao = new ScorecsvDAO();

		String colnames = step_lines[0];
		for (int i = 1; i < step_lines.length; i++) {
			try {
				if (step_lines[i] == null || step_lines[i].trim().equals("") || step_lines[i].startsWith("#")) {
					continue;
				}
				String[] s = step_lines[i].trim().split(",");

				Scorecsv scorecsv = scorecsvDao.getScorecsvByExamidSeatid(examid, s[0].trim());
				if (scorecsv == null) {
					scorecsv = new Scorecsv();
					scorecsv.setExamid(examid);
					scorecsv.setSeatid(s[0]);
					if (step == 1) {
						scorecsv.setStep1(colnames + "\n" + step_lines[i].trim());
					} else {
						scorecsv.setStep2(colnames + "\n" + step_lines[i].trim());
					}
					scorecsvDao.insert(scorecsv);
				} else {
					if (step == 1) {
						scorecsv.setStep1(colnames + "\n" + step_lines[i].trim());
					} else {
						scorecsv.setStep2(colnames + "\n" + step_lines[i].trim());
					}
					scorecsvDao.update(scorecsv);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int examid = 0;
		try {
			examid = Integer.parseInt(request.getParameter("examid"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			throw new DataException("競賽編號有誤(examid=" + request.getParameter("examid") + ")！無法匯入成績。");
		}
		this.importCsvlines(1, examid, request.getParameter("scorecsv_step1"));
		this.importCsvlines(2, examid, request.getParameter("scorecsv_step2"));
		response.sendRedirect("." + AdminServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]);
	}

}
