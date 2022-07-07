package jiangsir.teacherexam.Tables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jiangsir.teacherexam.Annotations.Persistent;
import jiangsir.teacherexam.Exceptions.DataException;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "fileblob" })
public class Upfile {
	@Persistent(name = "id")
	private Integer id = 0;
	@Persistent(name = "examid")
	private Integer examid = 0;
	@Persistent(name = "descript")
	private String descript = "";
	@Persistent(name = "filename")
	private String filename = "";
	@Persistent(name = "filetype")
	private String filetype = "";
	@Persistent(name = "fileblob")
	private ByteArrayInputStream fileblob;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamid() {
		return examid;
	}

	public void setExamid(Integer examid) {
		this.examid = examid;
	}

	public String getDescript() {
		if ("".equals(descript)) {
			this.descript = "NO_DESCRIPT";
		}
		return descript;
	}

	public void setDescript(String descript) throws DataException {
		if (descript == null || "".equals(descript)) {
			throw new DataException("檔案說明不可為空！");
		}
		this.descript = descript;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public ByteArrayInputStream getFileblob() {
		return fileblob;
	}

	public void setFileblob(ByteArrayInputStream fileblob) {
		this.fileblob = fileblob;
	}

	public void setFileblob(InputStream fileblob) {
		InputStream in = fileblob;
		// convert the inpustream to a byte array
		byte[] buf = null;
		try {
			buf = new byte[in.available()];
			while (in.read(buf) != -1) {
			}
		} catch (Exception e) {
			System.out.println("Got exception while is -> bytearr conversion: "
					+ e);
		}
		// now convert it to a bytearrayinputstream
		this.fileblob = new ByteArrayInputStream(buf);
	}

	public byte[] getBytes() {
		InputStream is = null;
		ByteArrayOutputStream buffer;
		// ResultSet rs;
		try {
			// PreparedStatement pstmt = getConnection().prepareStatement(sql);
			// rs = pstmt.executeQuery();
			// while (rs.next()) {
			// is = rs.getBlob(1).getBinaryStream();
			// }
			is = this.getFileblob();
			buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			is.close();
			// rs.close();
			// pstmt.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toByteArray();
	}

}
