/**
 * idv.jiangsir.DAOs - UserDAO.java
 * 2008/4/29 下午 05:46:51
 * jiangsir
 */
package jiangsir.teacherexam.Config;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import jiangsir.teacherexam.Config.AppConfig.FIELD;
import jiangsir.teacherexam.DAOs.SuperDAO;
import jiangsir.teacherexam.Exceptions.DataException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author jiangsir
 * 
 */
public class AppConfigDAO extends SuperDAO<AppConfig> {
	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	@Override
	public synchronized int insert(AppConfig appConfig) {
		String sql = "INSERT INTO appconfigs (" + FIELD.title + ", " + FIELD.schoolname + ", " + FIELD.principal + ","
				+ FIELD.zhuji + "," + FIELD.chuna + "," + FIELD.renshi + "," + FIELD.renshiphone + ","
				+ FIELD.bankprefix + "," + FIELD.bankname + "," + FIELD.bankhuming + "," + FIELD.manager_ip + ","
				+ FIELD.allowed_ip + ", timestamp) VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?,?);";
		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, appConfig.getTitle());
			pstmt.setString(2, appConfig.getSchoolname());
			pstmt.setString(3, appConfig.getPrincipal());
			pstmt.setString(4, appConfig.getZhuji());
			pstmt.setString(5, appConfig.getChuna());
			pstmt.setString(6, appConfig.getRenshi());
			pstmt.setString(7, appConfig.getRenshiphone());
			pstmt.setString(8, appConfig.getBankprefix());
			pstmt.setString(9, appConfig.getBankname());
			pstmt.setString(10, appConfig.getBankhuming());
			pstmt.setString(11, appConfig.getManager_ip().toString());
			pstmt.setString(12, appConfig.getAllowed_ip().toString());
			pstmt.setTimestamp(13, appConfig.getTimestamp());
			return this.executeInsert(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public synchronized int update(AppConfig appConfig) {
		int result = 0;
		return result;
	}

	protected ArrayList<AppConfig> getAppConfigByFields(TreeMap<String, Object> fields, String orderby, int page) {
		String sql = "SELECT * FROM appconfigs " + this.makeFields(fields, orderby, page);
		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);
			int i = 1;
			for (String field : fields.keySet()) {
				pstmt.setObject(i++, fields.get(field));
			}
			return executeQuery(pstmt, AppConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException(e);
		}
	}

	/**
	 * 清空 appconfig
	 * 
	 * @return
	 */
	protected boolean truncate() {
		return this.execute("TRUNCATE TABLE `appconfigs`");
	}

	@Override
	public boolean delete(int i) {
		return false;
	}

}
