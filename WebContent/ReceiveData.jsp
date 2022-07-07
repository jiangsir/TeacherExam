<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="jiangsir.teacherexam.Tables.Bankdata"%>

<%
	ServletInputStream in = request.getInputStream();
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
		String EntityBody = ""; //XML內容
		//取得檔名
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
			//FileName = "NotGetFileName_" + sYear + sMonth + sDay + sHours + sMinutes + sSeconds + ".txt";
		} else {
			FileName = all_data.substring(0, sTmpIdx - 1);
			EntityBody = all_data.substring(sTmpIdx + 1);
		}

		/* 		Bankdata bankdata = new Bankdata();
		 bankdata.setBankname(Bankdata.Bankname.BOT);
		 bankdata.setFilename(FileName);
		 bankdata.setRowdata(EntityBody);
		 try {
		 new BankdataDAO().insert(bankdata);
		 } catch (DataException e) {
		 e.printStackTrace();
		 }
		 */
		//寫入文字檔
		File f1 = new File("/tmp/BotRecvData");
		f1.mkdir();
		File output = new File("/tmp/BotRecvData", FileName);
		output.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				output.getPath(), true));

		bw.write(EntityBody);
		bw.close();

		//接收完畢，回應成功訊息
		//response.setStatus(200);
		out.print("200 OK");
	}
%>