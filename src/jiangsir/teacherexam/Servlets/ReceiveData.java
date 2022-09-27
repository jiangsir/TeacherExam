package jiangsir.teacherexam.Servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.DAOs.BankdataDAO;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Bankdata;

/**
 * Servlet implementation class ReceiveData
 */
@WebServlet(urlPatterns = { "/ReceiveData" })
public class ReceiveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Bot 給的範例程式！
	 * 
	 * @throws IOException
	 */
	private void BotExample() throws IOException {
		ServletInputStream in = request.getInputStream();
		PrintWriter out = response.getWriter();
		int len = request.getContentLength();
		if (len == 0) {
			out.print("406 Not Acceptable" + "\n");
			out.print("Content-type or Entity body is missing");
		} else {
			byte[] postedBytes = new byte[len];
			int offset = 0;

			while ((len - offset) > 0) {
				int inputLen = in.read(postedBytes, offset, len - offset);
				if (inputLen <= 0) {
					throw new IllegalArgumentException("Error");
				}
				offset += inputLen;
			}

			String all_data = new String(postedBytes, "big5");
			String EntityBody = ""; // XML§∫Æe
			// ®˙±o¿…¶W
			String FileName = "";
			java.util.Date DateObj = new java.util.Date();
			String sYear = String.valueOf(DateObj.getYear() - 1900);
			String sMonth = String.valueOf(DateObj.getMonth());
			String sDay = String.valueOf(DateObj.getDay());
			String sHours = String.valueOf(DateObj.getHours());
			String sMinutes = String.valueOf(DateObj.getMinutes());
			String sSeconds = String.valueOf(DateObj.getSeconds());
			int sTmpIdx = all_data.indexOf("\n");
			if (sTmpIdx == -1) {
				// FileName = "NotGetFileName_" + sYear + sMonth + sDay + sHours
				// + sMinutes + sSeconds + ".txt";
			} else {
				FileName = all_data.substring(0, sTmpIdx - 1);
				EntityBody = all_data.substring(sTmpIdx + 1);
			}
			// ºg§J§Â¶r¿…
			File f1 = new File("C:/BotRecvData");
			f1.mkdir();
			File output = new File("C:/BotRecvData", FileName);
			output.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(output.getPath(), true));

			bw.write(EntityBody);
			bw.close();
			// ±µ¶¨ßπ≤¶°A¶^¿≥¶®•\∞TÆß
			// response.setStatus(200);
			out.print("200 OK");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("GET ReceiveData: 對 ReceiveData 進行 GET request");
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("POST ReceiveData: 對 ReceiveData 進行 POST request");
		this.request = request;
		this.response = response;

		ServletInputStream in = request.getInputStream();
		PrintWriter out = response.getWriter();
		try {
			int len = request.getContentLength();
			if (len == 0) {
				out.print("406 Not Acceptable" + "\n");
				out.print("Content-type or Entity body is missing");
				return;
			} else {
				byte[] postedBytes = new byte[len];
				int offset = 0;

				while ((len - offset) > 0) {
					int inputLen = in.read(postedBytes, offset, len - offset);
					if (inputLen <= 0) {
						throw new IllegalArgumentException("Error");
					}
					offset += inputLen;
				}

				String all_data = new String(postedBytes, "big5");
				logger.info("接收「台灣銀行」資料 開始:");
				logger.info(all_data);
				logger.info("接收「台灣銀行」資料 結束!!");
				String EntityBody = ""; //XML內容
				//取得檔名
				String FileName = "";
				int sTmpIdx = all_data.indexOf("\n");
				if (sTmpIdx == -1) {
					logger.info("sTmpIdx == -1 TRUE");
					// FileName = "NotGetFileName_" + sYear + sMonth + sDay + sHours
					// + sMinutes + sSeconds + ".txt";
				} else {
					FileName = all_data.substring(0, sTmpIdx - 1);
					EntityBody = all_data.substring(sTmpIdx + 1);
				}
				String[] fields = EntityBody.split(",");
				Bankdata bankdata = new Bankdata();
				bankdata.setBankaccount(fields[3]);
				bankdata.setBankname(Bankdata.BANK.BOT);
				bankdata.setFilename(FileName);
				bankdata.setRowdata(EntityBody);
				try {
					new BankdataDAO().insert(bankdata);
				} catch (DataException e) {
					e.printStackTrace();
					out.print("ERROR: " + e.getLocalizedMessage());
					logger.info("ERROR WHEN insert bankdata: " + e.getLocalizedMessage());
					return;
				}
				new ApplicationDAO().setPaid(bankdata.getBankaccount());
				logger.info("200 OK");
				out.print("200 OK");
			}
		} catch (Exception e1) {
			StringWriter errors = new StringWriter();
			e1.printStackTrace(new PrintWriter(errors));
			logger.info("ReceiveData 未收到訊息: " + errors.toString());
			e1.printStackTrace();
			out.print("ReceiveData 未收到訊息: " + errors.toString());
		}
	}

}
