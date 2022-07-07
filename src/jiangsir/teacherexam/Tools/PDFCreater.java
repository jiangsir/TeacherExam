package jiangsir.teacherexam.Tools;

import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.Exam;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreater {

	// System.getenv("TMP") 在  windows 及 linux 上都正常，但 MAC 上不正常，會抓到 null。
	// System.getProperty("java.io.tmpdir") MAC 用
	String tmp = Utils.parsePath(System.getProperty("java.io.tmpdir"));

	public PDFCreater() {
	}

	public void test1() throws DocumentException, FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(new File("D:/Hello world.pdf"));
		// 建立一個Document物件，並設定頁面大小及左、右、上、下的邊界
		Document document = new Document(PageSize.A4, 10, 20, 30, 40);
		// 設定要輸出的Stream
		PdfWriter.getInstance(document, fos);
		document.open();
		// 設定作者
		document.addAuthor("Author");
		// 設定建立者
		document.addCreator("createor");
		// 設定主題
		document.addSubject("subject");
		// 設定標題
		document.addTitle("title");
		// 設定建立時間(為當下時間)
		document.addCreationDate();
		document.add(new Phrase("Hello world\n"));
		document.close();
	}

	public void test2() throws DocumentException, IOException {
		FileOutputStream fos = new FileOutputStream(new File("D:/test2.pdf"));
		// 建立一個Document物件，並設定頁面大小及左、右、上、下的邊界
		Document document = new Document(PageSize.A4, 10, 20, 30, 40);
		// 設定要輸出的Stream
		PdfWriter.getInstance(document, fos);
		document.open();
		// 設定作者
		document.addAuthor("Author");
		// 設定建立者
		document.addCreator("createor");
		// 設定主題
		document.addSubject("subject");
		// 設定標題
		document.addTitle("title");

		// 指定要使用的字型(KAIU.TTF為Windows內建的標楷體)
		BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\KAIU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		// 設定中文字型(BaseFont、字型大小、字型型態)
		Font chineseFont = new Font(bf, 12, Font.NORMAL);
		// 套用中文字型
		document.add(new Phrase("這是中文,許功蓋喆", chineseFont));
		document.close();
	}

	public void test3() throws DocumentException, FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(new File("D:/test3.pdf"));
		// 建立一個Document物件，並設定頁面大小及左、右、上、下的邊界
		Document document = new Document(PageSize.A4, 10, 20, 30, 40);
		// 設定要輸出的Stream
		PdfWriter.getInstance(document, fos);
		document.open();
		// 設定作者
		document.addAuthor("Author");
		// 設定建立者
		document.addCreator("createor");
		// 設定主題
		document.addSubject("subject");
		// 設定標題
		document.addTitle("title");

		// 建立PdfPTable物件並設定其欄位數
		PdfPTable table = new PdfPTable(2);
		// 設定table的寬度
		table.setWidthPercentage(100f);
		// 設定每個欄位的寬度
		table.setWidths(new float[] { 0.20f, 0.90f });

		PdfPCell title = new PdfPCell();
		// 合併儲存格
		title.setColspan(2);
		title.addElement(new Phrase("Table's Title"));
		table.addCell(title);

		// 設定第一個欄位的內容
		PdfPCell cell_1 = new PdfPCell();
		cell_1.addElement(new Phrase("Column 1"));
		table.addCell(cell_1);

		// 設定第二個欄位的內容
		PdfPCell cell_2 = new PdfPCell();
		cell_2.addElement(new Phrase("Column 2"));
		table.addCell(cell_2);

		document.add(table);
		document.close();
	}

	public void test4() throws MalformedURLException, IOException, DocumentException {
		FileOutputStream fos = new FileOutputStream(new File("D:/test4.pdf"));
		// 建立一個Document物件，並設定頁面大小及左、右、上、下的邊界
		Document document = new Document(PageSize.A4, 10, 20, 30, 40);
		// 設定要輸出的Stream
		PdfWriter.getInstance(document, fos);
		document.open();
		// 設定作者
		document.addAuthor("Author");
		// 設定建立者
		document.addCreator("createor");
		// 設定主題
		document.addSubject("subject");
		// 設定標題
		document.addTitle("title");
		Image img = Image.getInstance("D:/29901606.jpg");
		// Image image = Image.getInstance(new URL(http://xxx.com/logo.jpg));
		img.setAbsolutePosition(0, 0);
		document.add(img);
		document.close();
	}

	public void test5() throws DocumentException, IOException {
		FileOutputStream fos = new FileOutputStream(new File("D:/test5.pdf"));
		// 建立一個Document物件，並設定頁面大小及左、右、上、下的邊界
		Document document = new Document(PageSize.A4, 10, 20, 30, 40);
		// 設定要輸出的Stream
		PdfWriter.getInstance(document, fos);
		document.open();
		// 設定作者
		document.addAuthor("Author");
		// 設定建立者
		document.addCreator("createor");
		// 設定主題
		document.addSubject("subject");
		// 設定標題
		document.addTitle("title");

		// 指定要使用的字型(KAIU.TTF為Windows內建的標楷體)
		BaseFont bf = BaseFont.createFont("D:\\TomcatWebapps\\teacherexam\\files\\barcode39.ttf", BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		// 設定中文字型(BaseFont、字型大小、字型型態)
		Font barcodeFont = new Font(bf, 12, Font.NORMAL);
		// 套用中文字型
		String barcode = "4525542";
		document.add(new Phrase(barcode + "\n", barcodeFont));
		document.add(new Phrase(barcode + "\n"));
		document.close();
	}

	public void test6() {
		try {
			Document document = new Document(PageSize.A4, 0, 0, 12, 12);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Temp/page.pdf"));
			document.open();
			Reader reader;
			// reader = new FileReader("C:/Temp/itext.xhtml");
			String str = "<html><head><title></title></head><body>" + "HelloWorld" + "</body></html>";
			reader = new CharArrayReader(str.toCharArray());
			// com.lowagie.text.html.HtmlParser.parse(document, reader);
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void test7() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("results/walden1.pdf"));
		document.open();
		// XMLWorkerHelper.getInstance().parseXHtml(writer, document,
		// HTMLParsingDefault.class.getResourceAsStream("/html/walden.html"),
		// null);
		document.close();
	}

	public void testTemplate() throws IOException, DocumentException {
		PdfReader reader;
		PdfStamper stamper;
		// reader = new PdfReader(Utils.parsePath(ENV.APP_REAL_PATH +
		// "\\files\\"));
		reader = new PdfReader("D:\\TomcatWebapps\\teacherexam\\files\\PaymentTemplate.pdf");
		stamper = new PdfStamper(reader, new FileOutputStream(Utils.parsePath("D:\\") + "Template.pdf"));
		AcroFields form = stamper.getAcroFields();
		BaseFont unicode = BaseFont.createFont("D:\\TomcatWebapps\\teacherexam\\files\\msjhbd.ttf", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);
		form.setFieldProperty("name", "textfont", unicode, null);
		System.out.println(form.setField("name", "Jiang 中文測試"));
		stamper.close();
	}

	public File createApplicationForm(Application application) throws IOException, DocumentException {
		PdfReader reader;
		PdfStamper stamper;
		// reader = new PdfReader(Utils.parsePath(ENV.APP_REAL_PATH +
		// "\\files\\"));
		reader = new PdfReader(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "ApplicationFormTemplate.pdf");
		File file = new File(Utils.parsePath(tmp), "ApplicationForm_" + application.getId() + ".pdf");
		stamper = new PdfStamper(reader, new FileOutputStream(file));

		AcroFields form = stamper.getAcroFields();
		// BaseFont chineseForm = BaseFont.createFont(Utils
		// .parsePath(ENV.APP_REAL_PATH + "\\files\\")
		// + "msjhbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		// BaseFont baseFont = BaseFont.createFont(Utils
		// .parsePath(ENV.APP_REAL_PATH + "\\files\\")
		// + "kaiu.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		BaseFont baseFont = BaseFont.createFont(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "arialuni.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		// 上傳 apps 後就失靈。
		// FontFactory.registerDirectories();
		// Font f = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H,
		// BaseFont.EMBEDDED);
		// BaseFont baseFont = f.getBaseFont();

		form.addSubstitutionFont(baseFont);

		form.setFieldProperty("name", "textfont", baseFont, null);
		form.setFieldProperty("address", "textfont", baseFont, null);
		form.setFieldProperty("gender", "textfont", baseFont, null);
		form.setFieldProperty("subject", "textfont", baseFont, null);
		form.setFieldProperty("school", "textfont", baseFont, null);
		form.setFieldProperty("position", "textfont", baseFont, null);
		form.setFieldProperty("birthday", "textfont", baseFont, null);
		form.setFieldProperty("pid", "textfont", baseFont, null);
		form.setFieldProperty("cellphone", "textfont", baseFont, null);
		form.setFieldProperty("teloffice", "textfont", baseFont, null);

		form.setField("name", application.getName());
		form.setField("address", application.getAddress());
		form.setField("pid", application.getPid());
		form.setField("birthday", application.getBirthday().toString());
		form.setField("gender", application.getGender());
		form.setField("subject", application.getSubject().getName());
		form.setField("school", application.getSchool());
		form.setField("position", application.getPosition());
		form.setField("cellphone", application.getCellphone());
		form.setField("teloffice", application.getTeloffice());

		form.setFieldProperty("license", "textfont", baseFont, null);
		form.setField("license", application.getLicense());
		form.setFieldProperty("licensedate", "textfont", baseFont, null);
		form.setField("licensedate", application.getLicensedate());
		form.setFieldProperty("licenseid", "textfont", baseFont, null);
		form.setField("licenseid", application.getLicenseid());
		form.setFieldProperty("bachelor", "textfont", baseFont, null);
		form.setField("bachelor", application.getBachelormajor());
		form.setFieldProperty("bachelorbegin", "textfont", baseFont, null);
		form.setField("bachelorbegin", application.getBachelorbegin());
		form.setFieldProperty("bachelorend", "textfont", baseFont, null);
		form.setField("bachelorend", application.getBachelorend());
		form.setFieldProperty("master", "textfont", baseFont, null);
		form.setField("master", application.getMastermajor());
		form.setFieldProperty("masterbegin", "textfont", baseFont, null);
		form.setField("masterbegin", application.getMasterbegin());
		form.setFieldProperty("masterend", "textfont", baseFont, null);
		form.setField("masterend", application.getMasterend());
		form.setFieldProperty("phd", "textfont", baseFont, null);
		form.setField("phd", application.getPhdmajor());
		form.setFieldProperty("phdbegin", "textfont", baseFont, null);
		form.setField("phdbegin", application.getPhdbegin());
		form.setFieldProperty("phdend", "textfont", baseFont, null);
		form.setField("phdend", application.getPhdend());
		form.setFieldProperty("school1", "textfont", baseFont, null);
		form.setField("school1", application.getSchool1());
		form.setFieldProperty("position1", "textfont", baseFont, null);
		form.setField("position1", application.getPosition1());
		form.setFieldProperty("school1begin", "textfont", baseFont, null);
		form.setField("school1begin", application.getSchool1begin());
		form.setFieldProperty("school1end", "textfont", baseFont, null);
		form.setField("school1end", application.getSchool1end());

		form.setFieldProperty("school2", "textfont", baseFont, null);
		form.setField("school2", application.getSchool2());
		form.setFieldProperty("position2", "textfont", baseFont, null);
		form.setField("position2", application.getPosition2());
		form.setFieldProperty("school2begin", "textfont", baseFont, null);
		form.setField("school2begin", application.getSchool2begin());
		form.setFieldProperty("school2end", "textfont", baseFont, null);
		form.setField("school2end", application.getSchool2end());

		form.setFieldProperty("school3", "textfont", baseFont, null);
		form.setField("school3", application.getSchool3());
		form.setFieldProperty("position3", "textfont", baseFont, null);
		form.setField("position3", application.getPosition3());
		form.setFieldProperty("school3begin", "textfont", baseFont, null);
		form.setField("school3begin", application.getSchool3begin());
		form.setFieldProperty("school3end", "textfont", baseFont, null);
		form.setField("school3end", application.getSchool3end());

		form.setFieldProperty("special1", "textfont", baseFont, null);
		form.setField("special1", application.getSpecial1());
		form.setFieldProperty("special1date", "textfont", baseFont, null);
		form.setField("special1date", application.getSpecial1date());
		form.setFieldProperty("special2", "textfont", baseFont, null);
		form.setField("special2", application.getSpecial2());
		form.setFieldProperty("special2date", "textfont", baseFont, null);
		form.setField("special2date", application.getSpecial1date());
		form.setFieldProperty("license1", "textfont", baseFont, null);
		form.setField("license1", application.getLicense1());
		form.setFieldProperty("license2", "textfont", baseFont, null);
		form.setField("license2", application.getLicense2());
		form.setFieldProperty("license3", "textfont", baseFont, null);
		form.setField("license3", application.getLicense3());

		form.setFieldProperty("examid", "textfont", baseFont, null);
		form.setField("examid", application.getSeatid());

		PdfContentByte content = stamper.getOverContent(reader.getNumberOfPages());
		Image image;
		try {
			image = Image.getInstance(application.getPicture().getBytes());
			image.setAbsolutePosition(58, 600);
			image.scaleAbsolute(100, 150);
			content.addImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		stamper.close();
		return file;
	}

	public File createPidForm(Application application) throws IOException, DocumentException {
		PdfReader reader;
		PdfStamper stamper;
		reader = new PdfReader(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "PidFormTemplate.pdf");
		File file = new File(tmp, "PidForm_" + application.getId() + ".pdf");
		stamper = new PdfStamper(reader, new FileOutputStream(file));

		AcroFields form = stamper.getAcroFields();
		// BaseFont chineseForm = BaseFont.createFont(Utils
		// .parsePath(ENV.APP_REAL_PATH + "\\files\\")
		// + "msjhbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		BaseFont baseFont = BaseFont.createFont(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "msjhbd.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		stamper.close();
		return file;
	}

	public File createGuaranteeForm(Application application) throws IOException, DocumentException {
		PdfReader reader;
		PdfStamper stamper;
		reader = new PdfReader(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "GuaranteeFormTemplate.pdf");
		File file = new File(tmp, "GuaranteeForm_" + application.getId() + ".pdf");
		stamper = new PdfStamper(reader, new FileOutputStream(file));

		AcroFields form = stamper.getAcroFields();
		// BaseFont chineseForm = BaseFont.createFont(Utils
		// .parsePath(ENV.APP_REAL_PATH + "\\files\\")
		// + "msjhbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		BaseFont baseFont = BaseFont.createFont(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "msjhbd.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		stamper.close();
		return file;
	}

	public File createPaymentForm(Application application) throws IOException, DocumentException {
		PdfReader reader;
		PdfStamper stamper;
		// reader = new PdfReader(Utils.parsePath(ENV.APP_REAL_PATH +
		// "\\files\\"));
		reader = new PdfReader(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "PaymentFormTemplate.pdf");
		// System.out.println("System.getenv(TMP)=" + System.getenv("TMP"));
		// System.out.println("System.getProperty(java.io.tmpdir)="
		// + System.getProperty("java.io.tmpdir"));
		// String tmp = Utils.parsePath(System.getProperty("java.io.tmpdir"));

		File file = new File(tmp, "PaymentForm_" + application.getId() + ".pdf");
		stamper = new PdfStamper(reader, new FileOutputStream(file));

		AcroFields form = stamper.getAcroFields();
		// BaseFont chineseForm = BaseFont.createFont(Utils
		// .parsePath(ENV.APP_REAL_PATH + "\\files\\")
		// + "msjhbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		// BaseFont baseFont = BaseFont.createFont(Utils
		// .parsePath(ENV.APP_REAL_PATH + "\\files\\")
		// + "msjhbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		BaseFont baseFont = BaseFont.createFont(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "arialuni.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		form.addSubstitutionFont(baseFont);

		BaseFont barcodeFont = BaseFont.createFont(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "barcode39.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		Exam exam = application.getExam();
		SystemConfig systemConfig = new ConfigDAO().getSystemConfig();
		NumberFormat moneyformat6 = new DecimalFormat("000000");
		NumberFormat moneyformat10 = new DecimalFormat("0000000000");
		// String bankaccount = application.getBankaccount();
		form.setFieldProperty("name1", "textfont", baseFont, null);
		form.setField("name1", application.getName());
		form.setFieldProperty("subject1", "textfont", baseFont, null);
		form.setField("subject1", application.getSubject().getName());
		form.setFieldProperty("bank_deadline1", "textfont", baseFont, null);
		form.setField("bank_deadline1", exam.getDeadline().toString());
		form.setFieldProperty("applicationid1", "textfont", baseFont, null);
		form.setField("applicationid1", moneyformat6.format(application.getId()));
		form.setFieldProperty("barcode_account1", "textfont", barcodeFont, null);
		form.setFieldProperty("barcode_account1", "textsize", new Float(12), null);
		form.setField("barcode_account1", "*" + application.getBankaccount() + "*");
		form.setFieldProperty("bank_account1", "textfont", baseFont, null);
		form.setField("bank_account1", "*" + application.getBankaccount() + "*");
		form.setFieldProperty("bank_account", "textfont", baseFont, null);
		form.setField("bank_account", application.getBankaccount());
		form.setFieldProperty("barcode_money1", "textfont", barcodeFont, null);
		form.setFieldProperty("barcode_money1", "textsize", new Float(12), null);
		form.setField("barcode_money1", "*" + moneyformat10.format(exam.getMoney()) + "*");

		form.setFieldProperty("name2", "textfont", baseFont, null);
		form.setField("name2", application.getName());
		form.setFieldProperty("subject2", "textfont", baseFont, null);
		form.setField("subject2", application.getSubject().getName());
		form.setFieldProperty("bank_deadline2", "textfont", baseFont, null);
		form.setField("bank_deadline2", exam.getDeadline().toString());
		form.setFieldProperty("applicationid2", "textfont", baseFont, null);
		form.setField("applicationid2", moneyformat6.format(application.getId()));
		form.setFieldProperty("barcode_account2", "textfont", barcodeFont, null);
		form.setFieldProperty("barcode_account2", "textsize", new Float(12), null);
		form.setField("barcode_account2", "*" + application.getBankaccount() + "*");
		form.setFieldProperty("bank_account2", "textfont", baseFont, null);
		form.setField("bank_account2", "*" + application.getBankaccount() + "*");
		form.setFieldProperty("barcode_money2", "textfont", barcodeFont, null);
		form.setFieldProperty("barcode_money2", "textsize", new Float(12), null);
		form.setField("barcode_money2", "*" + moneyformat10.format(exam.getMoney()) + "*");

		form.setFieldProperty("name3", "textfont", baseFont, null);
		form.setField("name3", application.getName());
		form.setFieldProperty("subject3", "textfont", baseFont, null);
		form.setField("subject3", application.getSubject().getName());
		form.setFieldProperty("bank_deadline3", "textfont", baseFont, null);
		form.setField("bank_deadline3", exam.getDeadline().toString());
		form.setFieldProperty("applicationid3", "textfont", baseFont, null);
		form.setField("applicationid3", moneyformat6.format(application.getId()));
		form.setFieldProperty("barcode_account3", "textfont", barcodeFont, null);
		form.setFieldProperty("barcode_account3", "textsize", new Float(12), null);
		form.setField("barcode_account3", "*" + application.getBankaccount() + "*");
		form.setFieldProperty("bank_account3", "textfont", baseFont, null);
		form.setField("bank_account3", "*" + application.getBankaccount() + "*");
		form.setFieldProperty("barcode_money3", "textfont", barcodeFont, null);
		form.setFieldProperty("barcode_money3", "textsize", new Float(12), null);
		form.setField("barcode_money3", "*" + moneyformat10.format(exam.getMoney()) + "*");

		stamper.close();
		return file;
	}

	/**
	 * 准考證
	 * 
	 * @param application
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public File createExamForm(Application application) throws IOException, DocumentException {
		PdfReader reader;
		PdfStamper stamper;
		reader = new PdfReader(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "ExamFormTemplate.pdf");
		File file = new File(tmp, "ExamForm_" + application.getId() + ".pdf");
		stamper = new PdfStamper(reader, new FileOutputStream(file));

		AcroFields form = stamper.getAcroFields();

		BaseFont baseFont = BaseFont.createFont(Utils.parsePath(ENV.APP_REAL_PATH + "/files/") + "arialuni.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		form.addSubstitutionFont(baseFont);
		form.setFieldProperty("name", "textfont", baseFont, null);
		form.setField("name", application.getName());
		form.setFieldProperty("subject", "textfont", baseFont, null);
		form.setField("subject", application.getSubject().getName());
		form.setFieldProperty("examid", "textfont", baseFont, null);
		form.setField("examid", application.getSeatid());

		PdfContentByte content = stamper.getOverContent(reader.getNumberOfPages());
		Image image;
		try {
			image = Image.getInstance(application.getPicture().getBytes());
			image.setAbsolutePosition(125, 580);
			image.scaleAbsolute(100, 150);
			content.addImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}

		stamper.close();
		return file;
	}

	Font chineseFont;

	public static void main(String[] agrs) throws DocumentException, IOException {
		PDFCreater pdfcreater = new PDFCreater();
		pdfcreater.testTemplate();
	}
}
