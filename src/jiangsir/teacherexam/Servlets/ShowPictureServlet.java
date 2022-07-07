package jiangsir.teacherexam.Servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jiangsir.teacherexam.DAOs.PictureDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.Picture;

@WebServlet(urlPatterns = { "/ShowPicture" })
public class ShowPictureServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		return;
	}

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pictureid = Integer.parseInt(request.getParameter("id"));
		// ApplicationDAO applicationDao = new ApplicationDAO();
		// ApplicationTable application = applicationDao.getApplication(id);

		// EditApplication 的時候圖片讀取有誤。
		PictureDAO pictureDao = new PictureDAO();
		Picture picture = pictureDao.getPictureById(pictureid);

		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType(picture.getFiletype()); // 讓瀏覽器直接顯示圖片。
			out.write(picture.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
