package jiangsir.teacherexam.DAOs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Picture;

public class PictureDAO extends SuperDAO<Picture> {

	public synchronized int insert(Picture picture) throws DataException {
		String sql = "INSERT INTO pictures (`filename`, `filetype`, `blob`, "
				+ "`timestamp`) VALUES(?,?,?,?);";
		int id = 0;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			// pstmt.setInt(1, picture.getApplicationid());
			pstmt.setString(1, picture.getFilename());
			pstmt.setString(2, picture.getFiletype());
			pstmt.setBinaryStream(3, picture.getBlob(), picture.getBlob()
					.available());
			pstmt.setTimestamp(4, new Timestamp(picture.getTimestamp()
					.getTime()));
			executeUpdate(pstmt);
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			picture.getBlob().close();
			rs.close();
			pstmt.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DataException(e);
		}
	}

	public synchronized int update(Picture picture) throws DataException {
		int result = -1;
		String SQL = "UPDATE pictures SET `blob`=?, filetype=?, filename=? WHERE id=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(SQL);
			pstmt.setBinaryStream(1, picture.getBlob(), picture.getBlob()
					.available());
			pstmt.setString(2, picture.getFiletype());
			pstmt.setString(3, picture.getFilename());
			pstmt.setInt(4, picture.getId());
			result = executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException(e);
		}
		return result;
	}

	/*
	 * 單獨取出 Blob
	 */
	public byte[] getBytes(int pictureid) throws IOException {
		String sql = "SELECT `blob` FROM pictures WHERE id=" + pictureid;
		InputStream is = null;
		ByteArrayOutputStream buffer;
		ResultSet rs;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				is = rs.getBlob(1).getBinaryStream();
			}
			buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			is.close();
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toByteArray();
	}

	@Override
	public boolean delete(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	public Picture getPictureById(int pictureid) {
		String sql = "SELECT * FROM pictures WHERE id=" + pictureid;
		for (Picture picture : executeQuery(sql, Picture.class)) {
			return picture;
		}
		return new Picture();
	}

}
