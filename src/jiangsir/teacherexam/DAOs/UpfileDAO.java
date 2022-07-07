/**
 * idv.jiangsir.DAOs - ImageDAO.java
 * 2012/1/12 上午9:04:29
 * nknush-001
 */
package jiangsir.teacherexam.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Upfile;

/**
 * @author nknush-001
 * 
 */
public class UpfileDAO extends SuperDAO<Upfile> {

	public UpfileDAO() {
	}

	/*
	 * qx Java：图像实体类、磁盘图像文件与MySql数据库中Blob字段中存储的图像
	 * 
	 * 2011-05-23 06:31:27| 分类： Java技术 | 标签：java 图像 |字号 订阅
	 * MySQL使用Blob数据类型存储图像，java (1.6)现在支持bmp,jpg, wbmp, png, gif格式的图像。
	 * 
	 * 1 从数据库中Blob转为java程序中的Image类实体：
	 * 
	 * java.sql.Blob blob = rs.getBlob("Logo"); InputStream fin =
	 * blob.getBinaryStream(); Image im = javax.imageio.ImageIO.read(fin);
	 * 
	 * 2 从数据库中Blob转存为硬盘中的图像文件：
	 * 
	 * java.sql.Blob blob = rs.getBlob("Logo"); InputStream fin =
	 * blob.getBinaryStream(); //用文件模拟输出流 File file = new
	 * File("d:\\output.gif"); OutputStream fout = new FileOutputStream(file);
	 * //将BLOB数据写入文件 byte[] b = new byte[1024]; int len = 0; while ((len =
	 * fin.read(b)) != -1) { fout.write(b, 0, len); }
	 * 
	 * 3 将磁盘中图像文件存入数据库的BLOB字段中（使用Bytes）：
	 * 
	 * FileInputStream fis = new FileInputStream(“D:\\logo.gif”); byte[] b = new
	 * byte[65000];// 限制图像体积小于65KB fis.read(b); rs.updateBytes("Logo", b);
	 * 
	 * 将Bytes数组转为Image：
	 * 
	 * BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));
	 * 
	 * 4 将磁盘中文件转为java程序中的Image类实体
	 * 
	 * Image image = Toolkit.getDefaultToolkit().getImage("D:\\logo.gif"); 或
	 * Image image = javax.imageio.ImageIO.read(new
	 * FileInputStream("D:\\logo.gif"));
	 * 
	 * 5 将java程序中的Image类实体转为磁盘中文件
	 * 
	 * Image image;
	 * 
	 * ....... BufferedImage bufferedImage = (BufferedImage) image; try {
	 * ImageIO.write(bufferedImage, "PNG", new
	 * File("d:\\yourImageName.PNG"));//输出到 png 文件 ImageIO.write(bufferedImage,
	 * "JPEG", new File("d:\\yourImageName.JPG"));//输出到 jpg 文件
	 * ImageIO.write(bufferedImage, "gif", new
	 * File("d:\\yourImageName.GIF"));//输出到 gif 文件 ImageIO.write(bufferedImage,
	 * "BMP", new File("d:\\yourImageName.BMP"));//输出到 bmp 文件 } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * 6 将java程序中的Image类实体转为Byte数值后，存入数据库Blob字段中：
	 * 
	 * 首先将Image实体转为BufferedImage，可以参考网上的程序，如
	 * http://www.a3gs.com/BookViews.asp?InfoID=3111&classID=953&InfoType=0
	 * 
	 * Image image;.......
	 * 
	 * BufferedImage bi=Converter.toBufferedImage(image);
	 * 
	 * ByteArrayOutputStream baos=new ByteArrayOutputStream(1024);
	 * ImageIO.write(bi, "jpg", baos); baos.flush(); String
	 * base64String=Base64.encode(baos.toByteArray()); baos.close(); byte[] b =
	 * Base64.decode(base64String); rs.updateBytes("Logo", b);
	 * 
	 * 7 得到屏幕中的图像：
	 * 
	 * robot = new Robot(); BufferedImage bimage = robot.createScreenCapture(new
	 * Rectangle(0, 0, 100, 100)); Image image=bimage;
	 */

	public ArrayList<Upfile> getFilesByExamid(int examid) {
		String sql = "SELECT * FROM upfiles WHERE examid=" + examid;
		return this.executeQuery(sql, Upfile.class);
	}

	// private synchronized ArrayList<Fileblob> executeQuery(String sql) {
	// ArrayList<Fileblob> executables = new ArrayList<Fileblob>();
	// ResultSet rs;
	// try {
	// PreparedStatement pstmt = new GeneralDAO().getConnection()
	// .prepareStatement(sql);
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// Fileblob fileblob = new Fileblob();
	// fileblob.setId(rs.getInt(1));
	// fileblob.setExamid(rs.getInt(2));
	// fileblob.setFilename(rs.getString(3));
	// fileblob.setFiletype(rs.getString(4));
	// // Blob blob = rs.getBlob(8);
	// fileblob.setFileblob(rs.getBlob(5).getBinaryStream());
	// executables.add(fileblob);
	// }
	// rs.close();
	// pstmt.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return executables;
	// }

	public synchronized int insert(Upfile upfile) throws DataException {
		String sql = "INSERT INTO upfiles(examid, descript, filename, filetype, "
				+ "fileblob) VALUES (?,?,?,?,?);";
		int id = 0;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, upfile.getExamid());
			pstmt.setString(2, upfile.getDescript());
			pstmt.setString(3, upfile.getFilename());
			pstmt.setString(4, upfile.getFiletype());
			pstmt.setBinaryStream(5, upfile.getFileblob(), upfile.getFileblob()
					.available());
			executeUpdate(pstmt);
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public Upfile getFileById(int id) {
		String sql = "SELECT * FROM upfiles WHERE id=" + id;
		for (Upfile upfile : this.executeQuery(sql, Upfile.class)) {
			return upfile;
		}
		return null;
	}

	// /*
	// * 單獨取出 Blob file
	// */
	// public byte[] getBytesById(int id) throws IOException {
	// String sql = "SELECT `fileblob` FROM upfiles WHERE id=" + id;
	// InputStream is = null;
	// ByteArrayOutputStream buffer;
	// ResultSet rs;
	// try {
	// PreparedStatement pstmt = getConnection().prepareStatement(sql);
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// is = rs.getBlob(1).getBinaryStream();
	// }
	// buffer = new ByteArrayOutputStream();
	// int nRead;
	// byte[] data = new byte[1024];
	// while ((nRead = is.read(data, 0, data.length)) != -1) {
	// buffer.write(data, 0, nRead);
	// }
	// buffer.flush();
	// is.close();
	// rs.close();
	// pstmt.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// return null;
	// }
	// return buffer.toByteArray();
	// }

	@Override
	public synchronized int update(Upfile upfile) throws DataException {
		String SQL = "UPDATE upfiles SET examid=?, descript=?, filename=?, filetype=?, "
				+ "fileblob=? WHERE id=?";
		int result = -1;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(SQL);
			pstmt.setInt(1, upfile.getExamid());
			pstmt.setString(2, upfile.getDescript());
			pstmt.setString(3, upfile.getFilename());
			pstmt.setString(4, upfile.getFiletype());
			pstmt.setBinaryStream(5, upfile.getFileblob(), upfile.getFileblob()
					.available());
			pstmt.setInt(6, upfile.getId());
			result = executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM upfiles WHERE id=" + id;
		return this.execute(sql);
	}

}
