package jiangsir.teacherexam.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeMap;
import jiangsir.teacherexam.DAOs.GeneralDAO;
import jiangsir.teacherexam.Exceptions.DataException;

public class ConfigDAO {

	private Properties props;
	public File file;
	public final String FILENAME_SYSTEMCONFIG = "SystemConfig.xml";
	private static SystemConfig systemConfig = null;

	public ConfigDAO() {
		file = new File(Utils.parsePath(ENV.getPATH_METAINF()), FILENAME_SYSTEMCONFIG);
		try {
			props = Utils.readProperties(file);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ConfigDAO(File PATH) {
		file = new File(PATH.getPath() + "/META-INF/", FILENAME_SYSTEMCONFIG);
		try {
			props = Utils.readProperties(file);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得 SystemConfig.xml 內容
	 * 
	 * @return
	 * @throws DataException
	 */
	public SystemConfig getSystemConfig() {
		// 這裡要做 cache. 因為在 Filter 會用到，不斷讀取設定檔會影響效能。
		if (ConfigDAO.systemConfig != null) {
			return ConfigDAO.systemConfig;
		}
		SystemConfig systemConfig = new SystemConfig();

		TreeMap<String, String> map = this.getProperties();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			try {
				System.out.println("key=" + key);
				Method method = systemConfig.getClass().getMethod("set" + key, new Class[]{String.class});
				method.invoke(systemConfig, new Object[]{map.get(key)});
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		ConfigDAO.systemConfig = systemConfig;
		return systemConfig;
	}

	public int insert(String key, String value) {
		String SQL = "INSERT INTO configs (`key`, value) VALUES(?,?)";
		int id = 0;
		try {
			PreparedStatement pstmt = new GeneralDAO().getConnection().prepareStatement(SQL);
			// pstmt.setMaxFieldSize();
			pstmt.setString(1, key);
			pstmt.setString(2, value);
			new GeneralDAO().executeUpdate(pstmt);
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

	public int update(String key, String value) {
		String sql = "UPDATE configs SET value=? WHERE `key`=?";
		int result = -1;
		try {
			PreparedStatement pstmt = new GeneralDAO().getConnection().prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.setString(2, key);
			result = new GeneralDAO().executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void writeSystemConfig(SystemConfig systemConfig) {
		props.clear();
		Method[] methods = SystemConfig.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("set")) {
				try {
					Method getMethod = SystemConfig.class.getMethod(methods[i].getName().replaceFirst("set", "get"),
							Object[].class);
					props.setProperty(methods[i].getName().replaceFirst("set", ""),
							String.valueOf(getMethod.invoke(systemConfig, Object[].class)));
					System.out.println("getMethod= " + getMethod.getName() + ", value="
							+ String.valueOf(getMethod.invoke(systemConfig, Object[].class)));
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			props.storeToXML(fos, "系統參數");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	/**
	 * 取得所有 properties
	 */
	private TreeMap<String, String> getProperties() {
		TreeMap<String, String> list = new TreeMap<String, String>();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			props.loadFromXML(fis);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// qx Enumeration enumeration = props.propertyNames();
		Enumeration enumeration = props.keys();
		while (enumeration.hasMoreElements()) {
			// HashMap map = new HashMap();
			String name = enumeration.nextElement().toString();
			// map.put(name, props.getProperty(name));
			// qx list.add(name + " = " + props.getProperty(name) + " ");
			// list.add(map);
			list.put(name, props.getProperty(name));
		}
		return list;
	}

	/**
	 * 取得系統提供的 properties
	 * 
	 * @return
	 */
	public TreeMap<String, String> getSystemProperties() {
		TreeMap<String, String> list = new TreeMap<String, String>();
		Enumeration<?> enumeration = System.getProperties().keys();
		while (enumeration.hasMoreElements()) {
			// HashMap map = new HashMap();
			String name = enumeration.nextElement().toString();
			// map.put(name, System.getProperty(name));
			// list.add(map);
			list.put(name, System.getProperty(name));
		}
		return list;
	}

	/**
	 * 以 key 取得單一 property 的值
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return props.getProperty(key) == null ? "" : props.getProperty(key).trim();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void setProperty(String key, String value) {
		if (key == null || value == null) {
			return;
		}
		if (!props.containsKey(key)) {
			// props.put(key, "");
			try {
				throw new DataException("key=" + key + " 不存在！");
			} catch (DataException e) {
				e.printStackTrace();
			}
			return;
		}
		props.setProperty(key, value);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			props.storeToXML(fos, "系統參數");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeProperty(String key) {
		if (key == null) {
			return;
		}
		props.remove(key);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			props.storeToXML(fos, "系統參數");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
