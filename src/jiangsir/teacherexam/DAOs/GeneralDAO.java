package jiangsir.teacherexam.DAOs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.sql.DataSource;

import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Log;
import jiangsir.teacherexam.Tools.ENV;

public class GeneralDAO {

	public static Connection conn = null;
	private ServletContext sc = null;
	private DataSource ds = null;

	/**
	 * 
	 * @param config
	 */
	public GeneralDAO(Connection conn) {
		GeneralDAO.conn = conn;
	}

	/**
	 * 只有 ContextListener 能夠呼叫
	 * 
	 * @param event
	 */
	public GeneralDAO(ServletContextEvent event) {
		this.sc = event.getServletContext();
	}

	public GeneralDAO(ServletContext context) {
		this.sc = context;
	}

	public GeneralDAO() {
		this.sc = ENV.context;
	}

	/**
	 * 供 Local application 使用
	 * 
	 * @param driver
	 *            Example: com.mysql.jdbc.Driver
	 * @param jdbc
	 *            Example: jdbc:mysql://localhost:3306/myDB
	 * @param dbaccount
	 * @param dbpasswd
	 */
	public GeneralDAO(String driver, String jdbc, String dbaccount,
			String dbpasswd) {
		// //連結驅動程式
		try {
			// example String driver = "com.mysql.jdbc.Driver"; // 設定驅動程式
			Class.forName(driver); // 連結驅動程式
			// example String jdbc = "jdbc:mysql://localhost:3306/myDB"; //
			GeneralDAO.conn = DriverManager.getConnection(jdbc, dbaccount,
					dbpasswd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() {
		try {
			if (GeneralDAO.conn != null && !GeneralDAO.conn.isClosed()) {
				return GeneralDAO.conn;
			} else {
				System.out.println(ENV.logHeader()
						+ "資料庫連結失效，到 initConnection 重抓");
				this.initConnection(ENV.context);
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 由 Initialized Listener 來取得 connection <br>
	 * 資料庫在應用程式初始化中放入 connection
	 * 
	 * @param event
	 * @return
	 */
	public void initConnection(ServletContext context) {
		if (context == null) {
			context = this.sc;
		}
		String JDBC = context.getInitParameter("JDBC");

		try {
			if (ds == null) {
				InitialContext icontext = new InitialContext();
				ds = (DataSource) icontext.lookup("java:comp/env/" + JDBC);
				try {
					GeneralDAO.conn = ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				synchronized (context) {
					context.setAttribute("conn", GeneralDAO.conn);
					// ENV.setConnection(this.conn);
					System.out.println(ENV.logHeader() + "取得conn並放入 "
							+ "ServletContext & ENV. conn=" + GeneralDAO.conn);
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 只用來獲取總筆數
	 * 
	 * @param sql
	 * @return
	 */
	public int executeCount(String sql) {
		int result = 0;
		if (sql.matches("^SELECT.+FROM.*")) {
			sql = sql.replaceFirst("^SELECT.+FROM",
					"SELECT COUNT(*) AS COUNT FROM");
		} else {
			return -1;
		}
		System.out.println(sql);
		try {
			PreparedStatement pstmt;
			pstmt = this.getConnection().prepareStatement(sql);
			if (sql.contains("ORDER")) {
				sql = sql.substring(0, sql.indexOf("ORDER") - 1);
			}
			if (!sql.toUpperCase().startsWith("SELECT COUNT(*) AS COUNT FROM")) {
				return -1;
			}
			ResultSet rs = pstmt.executeQuery(sql);
			rs.next();
			result = rs.getInt("COUNT");
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	public ArrayList<?> executeQuery(PreparedStatement pstmt, Class<?> theclass) {
		ResultSet rs = null;
		ArrayList<Object> list = new ArrayList<Object>(); // 数组来处理
		try {
			rs = pstmt.executeQuery(); // 执行查询
			ResultSetMetaData rsmd = rs.getMetaData(); // 取得数据表中的字段数目，类型等返回结果
			// 是以ResultSetMetaData对象保存
			int columnCount = rsmd.getColumnCount(); // 列的总数
			while (rs.next()) {
				Object object = theclass.newInstance(); // 类的实例化
				for (int i = 1; i <= columnCount; i++) {
					String methodname = this.getSetMethodName(rsmd
							.getColumnName(i));
					Object value;
					// System.out.println(rsmd.getColumnName(i) + ": "
					// + rsmd.getColumnTypeName(i) + "("
					// + rsmd.getColumnType(i) + ")");
					if (rsmd.getColumnType(i) == Types.BOOLEAN) {
						value = rs.getBoolean(rsmd.getColumnName(i));
					} else if (rsmd.getColumnType(i) == Types.LONGVARBINARY) {
						continue;
					} else if (rsmd.getColumnType(i) == Types.DATE) {
						value = rs.getDate(rsmd.getColumnName(i));
					} else if (rsmd.getColumnType(i) == Types.TIMESTAMP) {
						value = rs.getDate(rsmd.getColumnName(i));
					} else if (rsmd.getColumnType(i) == Types.TINYINT) {
						value = rs.getBoolean(rsmd.getColumnName(i));
					} else {
						value = rs.getObject(rsmd.getColumnName(i));
					}

					try {
						Method m = object.getClass().getMethod(methodname,
								new Class[] { value.getClass() });
						m.invoke(object, new Object[] { value });
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						continue;
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
						continue;
					}
				}
				list.add(object);
			}
			System.out.println(pstmt.toString() + ": list.size=" + list.size());
		} catch (SQLException ex) {
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 新通用型 query 直接返回所需要的 Object 形態，而不需經由 HashMap 逐一設定 field
	 * 
	 * @param <E>
	 * 
	 * @param sql
	 * @param theclass
	 * @return
	 */
	public ArrayList<?> executeQuery(String sql, Class<?> theclass) {
		PreparedStatement pstmt;
		try {
			pstmt = this.getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return this.executeQuery(pstmt, theclass);
	}

	private String getSetMethodName(String columnName) {
		String firstchar = columnName.substring(0, 1);
		firstchar = firstchar.toUpperCase();
		return "set" + firstchar + columnName.substring(1);
	}

	/**
	 * 執行 SQL 指令
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public boolean execute(String sql) {
		boolean result = false;
		Connection conn = this.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			result = stmt.execute(sql);
			stmt.close();
			// conn.close();
		} catch (SQLException e) {
			Log log = new Log();
			log.setUri(this.getClass().getSimpleName());
			log.setTitle(e.getLocalizedMessage());
			log.setStacktrace(e);
			log.setDebug(sql);
			new LogDAO().insert(log);
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * UPDATE 專用
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public synchronized int executeUpdate(String sql) {
		int result = 0;
		Connection conn = this.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			stmt.close();
			// conn.close();
		} catch (SQLException e) {
			System.out.println(ENV.logHeader() + "SQL ERROR:" + sql);
			Log log = new Log();
			log.setUri(this.getClass().getName());
			log.setTitle(e.getLocalizedMessage());
			log.setStacktrace(e);
			log.setDebug(sql);
			new LogDAO().insert(log);
			e.printStackTrace();
		}
		System.out.println(ENV.logHeader() + "SQL=" + sql);
		return result;
	}

	public synchronized int executeUpdate(PreparedStatement pstmt)
			throws SQLException {
		long starttime = System.currentTimeMillis();
		int result = -1;
		try {
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getLocalizedMessage().contains(
					"Packet for query is too large")) {
				// throw new DataException("檔案太大不能上傳！");
				throw new SQLException("檔案太大不能上傳！");
			} else {
				// throw new DataException(e.getLocalizedMessage());
				throw e;
			}
		}
		System.out.println("PSTMT_SQL=" + pstmt.toString() + " 共耗時 "
				+ (System.currentTimeMillis() - starttime) + " ms");
		return result;
	}

	/**
	 * 專門提供 INSERT 使用, 並回傳新增最後一筆的 ID
	 * 
	 * @return
	 */
	public synchronized int executeInsert(String sql) {
		Connection conn = this.getConnection();
		Statement stmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int colCount = rsmd.getColumnCount();
				do {
					for (int i = 1; i <= colCount; i++) {
						String key = rs.getString(i);
						result = Integer.parseInt(key);
					}
				} while (rs.next());
			} else {
				System.out.println(ENV.logHeader() + "錯誤!! 沒有找到 自動遞增的 key");
				result = -1;
			}
		} catch (SQLException e) {
			System.out.println(ENV.logHeader() + "SQL ERROR:" + sql);
			Log log = new Log();
			log.setUri(this.getClass().getName());
			log.setTitle(e.getLocalizedMessage());
			log.setStacktrace(e);
			log.setDebug("sql: " + sql);
			new LogDAO().insert(log);
			e.printStackTrace();
			return -1;
		}
		return result;
	}

}
