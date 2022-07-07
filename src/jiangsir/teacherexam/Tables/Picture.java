package jiangsir.teacherexam.Tables;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;

import jiangsir.teacherexam.Annotations.Persistent;
import jiangsir.teacherexam.DAOs.PictureDAO;
import jiangsir.teacherexam.Exceptions.DataException;

public class Picture {
	@Persistent(name = "id")
	private Integer id;
	@Persistent(name = "filename")
	private String filename;
	@Persistent(name = "filetype")
	private String filetype;
	@Persistent(name = "blob")
	private ByteArrayInputStream blob;
	@Persistent(name = "timestamp")
	private Timestamp timestamp = new Timestamp(Calendar.getInstance()
			.getTimeInMillis());

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setFiletype(String filetype) throws DataException {
		if (filetype == null || "".equals(filetype)) {
			throw new DataException("檔案格式未設定。");
		}
		String[] allows = { "image/jpeg", "image/pjpeg", "image/png",
				"image/x-png" };
		if (Arrays.binarySearch(allows, filetype) < 0) {
			throw new DataException("本系統僅支援 jpg or png 檔案作為相片圖檔。(type="
					+ filetype + ")");
		}
		this.filetype = filetype;
	}

	public ByteArrayInputStream getBlob() {
		return blob;
	}

	public void setBlob(String picturepath) throws DataException {
		if (picturepath == null || "".equals(picturepath)) {
			throw new DataException("您並未選擇相片。");
		}
	}

	public void setBlob(InputStream blob) throws DataException {
		if (blob == null) {
			throw new DataException("上傳照片有誤！(blob==null)");
		}
		InputStream in = blob;
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
		this.blob = new ByteArrayInputStream(buf);
	}

	// public void setBlob(InputStream blob) throws DataException {
	// if (blob == null) {
	// throw new DataException("上傳照片有誤！(blob==null)");
	// }
	// this.blob = blob;
	// }

	public void setBlob(ByteArrayInputStream blob) throws DataException {
		if (blob == null) {
			throw new DataException("上傳照片有誤！(blob==null)");
		}
		this.blob = blob;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 取得相片 byte[]
	 * 
	 * @return
	 */
	public byte[] getBytes() {
		try {
			return new PictureDAO().getBytes(this.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
